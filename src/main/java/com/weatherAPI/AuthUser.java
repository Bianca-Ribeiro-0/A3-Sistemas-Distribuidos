package com.weatherAPI;

import model.User;

import java.util.HashMap;
import java.util.Scanner;

public class AuthUser {
    private final HashMap<String, User> users;

    public AuthUser() {
        this.users = new HashMap<>();
    }

    public void inicializacaoSistema(Scanner scanner) {
        System.out.println("Bem-vindos! Por favor insira uma das opções abaixo: ");
        System.out.println("(1) - Login");
        System.out.println("(2) - Criar conta");
        System.out.println("(3) - Sair");
        System.out.println();

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    cadastrar(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um número válido para escolher uma opção.");
            inicializacaoSistema(scanner);
        }
    }

    public void cadastrar(Scanner scanner) {
        while (true) {
            User user = new User();

            System.out.println("Insira as informações para realizar seu cadastro:");
            try {
                System.out.printf("CPF (sem pontuação) --> ");
                boolean cpfValido = user.setCpf(scanner.nextLine());

                System.out.printf("Nome --> ");
                boolean nomeValido = user.setNome(scanner.nextLine());

                System.out.printf("Telefone (sem pontuação) --> ");
                boolean telefoneValido = user.setTelefone(scanner.nextLine());

                System.out.printf("Senha --> ");
                String senha = scanner.nextLine();

                System.out.printf("Confirme sua senha --> ");
                String confirmSenha = scanner.nextLine();

                boolean senhaValida = senha.equals(confirmSenha) && user.setSenha(senha);

                if (!cpfValido || !nomeValido || !telefoneValido || !senhaValida) {
                    System.out.println("Por favor, verifique as informações inseridas e tente novamente.");
                    System.out.println("Deseja tentar novamente? (s/n)");
                    String tentarNovamente = scanner.nextLine();
                    if (!tentarNovamente.equalsIgnoreCase("s")) {
                        System.out.println("Saindo do cadastro...");
                        return;
                    }
                } else {
                    users.put(user.getCpf(), user);
                    System.out.println("\n>> CADASTRO REALIZADO COM SUCESSO!\n");
                    new ApiHandler().consultaAmbos(scanner);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro durante o cadastro. Por favor, tente novamente.");
            }
        }
    }

    public void login(Scanner scanner) {
        while (true) {
            System.out.println("Insira as informações para realizar o login:");
            try {
                System.out.printf("CPF (sem pontuação) --> ");
                String cpf = scanner.nextLine();

                System.out.printf("Senha --> ");
                String senha = scanner.nextLine();

                if (users.containsKey(cpf)) {
                    User user = users.get(cpf);
                    if (user.getSenha().equals(senha)) {
                        System.out.println("Login realizado com sucesso!");
                        new ApiHandler().consultaAmbos(scanner);
                        return;
                    }
                }

                System.out.println("CPF ou senha incorretos. Deseja tentar novamente? (s/n)");
                String tentarNovamente = scanner.nextLine();
                if (!tentarNovamente.equalsIgnoreCase("s")) {
                    System.out.println("Saindo do login...");
                    inicializacaoSistema(scanner);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro durante o login. Por favor, tente novamente.");
            }
        }
    }
}
