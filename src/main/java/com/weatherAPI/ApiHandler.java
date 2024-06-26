package com.weatherAPI;

import controller.services.OpenWeatherAPI;
import controller.services.ViaCepAPI;
import model.OpenWeather;
import model.States;
import model.ViaCep;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ApiHandler {
    private static final ViaCepAPI viaCepAPI = new ViaCepAPI();
    private static final OpenWeatherAPI openWeatherAPI = new OpenWeatherAPI();
    private static final Coordenates coordenates = new Coordenates();

    public static ViaCep consultarEnderecoPorCep(String cep) {
        try {
            return viaCepAPI.getCepPorCep(cep);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao consultar o CEP: " + e.getMessage());
            return null;
        }
    }

    public static States consultarClimaPorEstado(String countryName) {
        try {
            return coordenates.buildCoordinates(countryName.trim().toUpperCase());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao consultar o clima por estado: " + e.getMessage());
            return null;
        }
    }
    //Tratamento das infos de clima tragas pela API
    private static void obterInformacoesClimaticas(String latitude, String longitude) {
        try {
            OpenWeather climaTempo = openWeatherAPI.obterInformacoesClima(latitude, longitude);

            if (climaTempo != null) {
                System.out.println("\nInformações do clima:\n");
                System.out.println("País: " + (climaTempo.getSys() != null ? climaTempo.getSys().getOrDefault("country", "não disponível") : "não disponível"));

                int timezoneSeconds = climaTempo.getTimezone() != null ? Integer.parseInt(climaTempo.getTimezone()) : 0;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                LocalDateTime datahoraBR = LocalDateTime.now();
                LocalDateTime datahoraUTC = LocalDateTime.now().plusSeconds(timezoneSeconds);

                System.out.println("Data(UTC): " + datahoraUTC.format(formatter));
                System.out.println("Data Local: " + datahoraBR.format(formatter) + "\n");

                if (climaTempo.getWeather() != null && !climaTempo.getWeather().isEmpty()) {
                    System.out.println("Condições do tempo: " + climaTempo.getWeather().get(0).get("description"));

                    double temperaturaKelvin = Double.parseDouble(climaTempo.getMain().get("temp").toString());
                    double temperaturaMaxKelvin = Double.parseDouble(climaTempo.getMain().get("temp_max").toString());
                    double temperaturaMinKelvin = Double.parseDouble(climaTempo.getMain().get("temp_min").toString());

                    DecimalFormat df = new DecimalFormat("#.##");
                    System.out.println("Temperatura atual: " + df.format(temperaturaKelvin - 273.15) + "°C");
                    System.out.println("Temperatura máxima: " + df.format(temperaturaMaxKelvin - 273.15) + "°C");
                    System.out.println("Temperatura mínima: " + df.format(temperaturaMinKelvin - 273.15) + "°C");

                    System.out.println("===============================================");
                    System.out.println("Informações adicionais sobre o clima no CEP: ");
                    System.out.println("Pressão atmosférica: " + climaTempo.getMain().getOrDefault("pressure", "Dados não disponíveis") + " hPa");
                    System.out.println("Umidade: " + climaTempo.getMain().getOrDefault("humidity", "Dados não disponíveis") + "%");
                    System.out.println("Velocidade do vento: " + climaTempo.getWind().getOrDefault("speed", "Dados não disponíveis") + " m/s");
                    System.out.println("Direção do vento: " + climaTempo.getWind().getOrDefault("deg", "Dados não disponíveis") + " graus");
                }
            } else {
                System.out.println("\nNão foi possível obter informações do clima para o estado especificado.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao obter as informações climáticas: " + e.getMessage());
        }
    }

    //Consulta Clima a partir do CEP
    public static void consultaAmbos(Scanner scanner) {
        try {
            System.out.println("Consulta de clima através do CEP enviado");
            System.out.println("===============================================");
            System.out.print("Digite o CEP: ");
            String cep = scanner.nextLine();

            ViaCep viaCep = consultarEnderecoPorCep(cep);

            if (viaCep != null) {
                System.out.println("\nInformações do CEP:");
                System.out.println("CEP: " + viaCep.getCep() + " Localidade: " + viaCep.getLocalidade() + " UF: " + viaCep.getUf());

                States state = consultarClimaPorEstado(viaCep.getUf());

                if (state != null) {
                    obterInformacoesClimaticas(state.getLatitude(), state.getLongitude());
                } else {
                    System.out.println("Não foi possível encontrar coordenadas para o estado.");
                }
            } else {
                System.out.println("\nNão foi possível obter informações para o CEP especificado.");
            }

            // Após consulta de clima
            System.out.println("\nDeseja realizar outra operação?");
            System.out.println("(1) - Voltar para Login/Cadastro");
            System.out.println("(2) - Digite novamente");
            System.out.println("(3) - Sair");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    AuthUser authUser = new AuthUser();
                    authUser.inicializacaoSistema(scanner);
                    break;
                case 2:
                    ApiHandler apiHandler = new ApiHandler();
                    apiHandler.consultaAmbos(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Saindo do sistema...");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a consulta: " + e.getMessage());
        }
    }
}
