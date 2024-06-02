package com.weatherAPI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindos! Para ver como está o clima, faça login no sistema: ");

        Scanner scanner = new Scanner(System.in);

        // Instanciando a classe AuthUser
        AuthUser authUser = new AuthUser();

        try {
            // Exibindo as opções de inicialização do sistema
            authUser.inicializacaoSistema(scanner);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execução do programa." + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
