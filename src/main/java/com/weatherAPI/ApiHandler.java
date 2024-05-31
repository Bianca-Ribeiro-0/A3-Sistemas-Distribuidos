package com.weatherAPI;

import controller.services.OpenWeatherAPI;
import controller.services.ViaCepAPI;
import model.OpenWeather;
import model.ViaCep;

import java.util.Scanner;

public class ApiHandler {
    private ViaCepAPI viaCepAPI;
    private OpenWeatherAPI openWeatherAPI;

    public ApiHandler() {
        this.viaCepAPI = new ViaCepAPI();
        this.openWeatherAPI = new OpenWeatherAPI();
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
        System.out.print("Digite o estado: ");
        String countryName = scanner.nextLine();

        OpenWeather climaTempo = openWeatherAPI.obterInformacoesClima(countryName);

        if (climaTempo != null) {
            System.out.println("\nInformações do clima:");
            if (climaTempo.getSys() != null && climaTempo.getSys().get("country") != null) {
                System.out.println("País: " + climaTempo.getSys().get("country"));
            } else {
                System.out.println("País não disponível");
            }
            if (climaTempo.getTimezone() != null) {
                System.out.println("Data: " + climaTempo.getTimezone());
            } else {
                System.out.println("Data não disponível");
            }
            if (climaTempo.getWeather() != null && !climaTempo.getWeather().isEmpty()) {
                System.out.println("Análise sinótica: " + climaTempo.getWeather().get(0).get("description"));
            } else {
                System.out.println("Análise sinótica não disponível");
            }
        } else {
            System.out.println("\nNão foi possível obter informações do clima para o país especificado.");
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
