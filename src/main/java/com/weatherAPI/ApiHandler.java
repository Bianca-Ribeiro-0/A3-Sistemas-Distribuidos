package com.weatherAPI;

import controller.services.OpenWeatherAPI;
import controller.services.ViaCepAPI;
import model.OpenWeather;
import model.States;
import model.ViaCep;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ApiHandler {
    private ViaCepAPI viaCepAPI;
    private OpenWeatherAPI openWeatherAPI;
    private Coordenates coordenates;

    public ApiHandler() {
        this.viaCepAPI = new ViaCepAPI();
        this.openWeatherAPI = new OpenWeatherAPI();
        this.coordenates = new Coordenates();
    }

    public void consultarEnderecoPorCep(Scanner scanner) {
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();

        ViaCep viaCep = viaCepAPI.getCepPorCep(cep);

        if (viaCep != null) {
            System.out.println("\nInformações do CEP:");
            System.out.println("CEP: " + viaCep.getCep());
            System.out.println("Logradouro: " + viaCep.getLogradouro());
            System.out.println("Complemento: " + viaCep.getComplemento());
            System.out.println("Bairro: " + viaCep.getBairro());
            System.out.println("Localidade: " + viaCep.getLocalidade());
            System.out.println("UF: " + viaCep.getUf());
            System.out.println("IBGE: " + viaCep.getIbge());
            System.out.println("GIA: " + viaCep.getGia());
            System.out.println("DDD: " + viaCep.getDdd());
            System.out.println("SIAFI: " + viaCep.getSiafi());
        } else {
            System.out.println("\nNão foi possível obter informações para o CEP especificado.");
        }
    }

    public void consultarClimaPorEstado(Scanner scanner) {
        List<String> states = Arrays.asList("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");


        System.out.println("Estados: " + states);
        System.out.print("Digite o estado: ");
        
        //entrada do usuario, remove espaços em brancos e converte para upperCase
        String countryName = scanner.nextLine().trim().toUpperCase();
        States state = coordenates.buildCoordinates(countryName);
        String latitude = state.getLatitude();
        String longitude = state.getLongitude();
        
        OpenWeather climaTempo = openWeatherAPI.obterInformacoesClima(latitude, longitude);


        if (climaTempo != null) {
            System.out.println("\nInformações do clima:" + "\n");
            if (climaTempo.getSys() != null && climaTempo.getSys().get("country") != null) {
                System.out.println("País: " + climaTempo.getSys().get("country"));
            } else {
                System.out.println("País não disponível");
            }
            if (climaTempo.getTimezone() != null) {
                int timezoneSeconds = Integer.parseInt(climaTempo.getTimezone());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
               
                LocalDateTime datahoraBR = LocalDateTime.now();
                LocalDateTime datahoraUTC = LocalDateTime.now().plusSeconds(timezoneSeconds);

    
                System.out.println("Data(UTC): " + datahoraUTC.format(formatter));
                System.out.println("Data Local: " + datahoraBR.format(formatter) + "\n");

            } else {
                System.out.println("Data não disponível");
            }
            if (climaTempo.getWeather() != null && !climaTempo.getWeather().isEmpty()) {
                
                System.out.println("Análise sinótica:");
                System.out.println("Condições do tempo: " + climaTempo.getWeather().get(0).get("description"));

                
                // Convertendo as string para double 
                double temperaturaKelvin = Double.parseDouble(climaTempo.getMain().get("temp"));
                double temperaturaMaxKelvin = Double.parseDouble(climaTempo.getMain().get("temp_max").toString());
                double temperaturaMinKelvin = Double.parseDouble(climaTempo.getMain().get("temp_min").toString());

                double temperaturaCelsius = temperaturaKelvin - 273.15;
                double temperaturaMaxCelsius = temperaturaMaxKelvin - 273.15;
                double temperaturaMinCelsius = temperaturaMinKelvin  - 273.15;

                DecimalFormat df = new DecimalFormat("#.##");
                temperaturaCelsius = Double.parseDouble(df.format(temperaturaCelsius));
                temperaturaMaxCelsius = Double.parseDouble(df.format(temperaturaMaxCelsius));
                temperaturaMinCelsius = Double.parseDouble(df.format(temperaturaMinCelsius));

                System.out.println("Temperatura atual: " + temperaturaCelsius + "°C");
                System.out.println("Temperatura máxima: " + temperaturaMaxCelsius + "°C");
                System.out.println("Temperatura mínima: " + temperaturaMinCelsius + "°C");
                System.out.println("Pressão atmosférica : " + climaTempo.getMain().get("pressure") + " hPa");   
                System.out.println("Umidade: " + climaTempo.getMain().get("humidity") + "%");  
                System.out.println("Velocidade do vento: " + climaTempo.getWind().get("speed") + " m/s");
                System.out.println("Direção do vento: " + climaTempo.getWind().get("deg") + " graus");
              

            } else {
                System.out.println("Análise sinótica não disponível");
            }
        } else {
            System.out.println("\nNão foi possível obter informações do clima para o estado especificado.");
        }

    }

    public void consultarDados(Scanner scanner) {
        System.out.println("Escolha a operação:");
        System.out.println("1. Consultar Endereço por CEP");
        System.out.println("2. Consultar Clima por Estado");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpando o buffer do teclado

        switch (opcao) {
            case 1:
                consultarEnderecoPorCep(scanner);
                break;
            case 2:
                consultarClimaPorEstado(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void main(String[] args) {
        ApiHandler apiHandler = new ApiHandler();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Consulta de Endereço por CEP e Clima por Estado");
        System.out.println("===============================================");

        apiHandler.consultarDados(scanner);

        scanner.close();
    }
}
