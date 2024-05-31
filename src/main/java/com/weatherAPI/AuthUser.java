package com.weatherAPI;

import model.User;

import java.util.HashMap;
import java.util.Scanner;

public class AuthUser {
    private HashMap<String, User> users;

    public AuthUser() {
        this.users = new HashMap<>();
    }

    public void inicializacaoSistema(Scanner sc) {
        System.out.println("Bem-vindos! Por favor insira uma das opções abaixo: ");
        System.out.println("(1) - Login");
        System.out.println("(2) - Criar conta");
        System.out.println("(3) - Sair");
        System.out.println();

        int opcao = Integer.parseInt(sc.nextLine());
        switch (opcao) {
            case 1:
                login(sc);
                break;
            case 2:
                cadastrar(sc);
                break;
            case 3:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void cadastrar(Scanner sc) {
        while (true) {
            User user = new User();

            // Solicitando e validando os campos de cadastro usando métodos de acesso de User
            System.out.println("Insira as informações para realizar seu cadastro:");
            System.out.printf("CPF (sem pontuação) --> ");
            boolean cpfValido = user.setCpf(sc.nextLine());

            System.out.printf("Nome --> ");
            boolean nomeValido = user.setNome(sc.nextLine());

            System.out.printf("Telefone (sem pontuação) --> ");
            boolean telefoneValido = user.setTelefone(sc.nextLine());

            System.out.printf("Senha --> ");
            String senha = sc.nextLine();

            System.out.printf("Confirme sua senha --> ");
            String confirmSenha = sc.nextLine();

            boolean senhaValida = senha.equals(confirmSenha) && user.setSenha(senha);

            // Validando se todos os campos são válidos
            if (!cpfValido || !nomeValido || !telefoneValido || !senhaValida) {
                // Se algum campo for inválido, imprime mensagem de erro e pergunta se deseja tentar novamente
                System.out.println("Por favor, verifique as informações inseridas e tente novamente.");
                System.out.println("Deseja tentar novamente? (s/n)");
                String tentarNovamente = sc.nextLine();
                if (!tentarNovamente.equalsIgnoreCase("s")) {
                    System.out.println("Saindo do cadastro...");
                    return;
                }
            } else {
                // Adicionando o usuário ao HashMap e saindo do loop se o cadastro for bem-sucedido
                users.put(user.getCpf(), user);
                System.out.println("\n>> CADASTRO REALIZADO COM SUCESSO!\n");
                ApiHandlerWeather apiHandler = new ApiHandlerWeather();
                apiHandler.consultarClima(sc);
                return;
            }
        }
    }

    public void login(Scanner sc) {
        while (true) {
            System.out.println("Insira as informações para realizar o login:");
            System.out.printf("CPF (sem pontuação) --> ");
            String cpf = sc.nextLine();

            System.out.printf("Senha --> ");
            String senha = sc.nextLine();

            // Verificando se o usuário existe no HashMap e se a senha está correta (obs: só irá existir no hash enquanto o programa estiver sendo executado, caso finalizado não terá mais acesso. Sem BD)
            if (users.containsKey(cpf)) {
                User user = users.get(cpf);
                if (user.getSenha().equals(senha)) {
                    System.out.println("Login realizado com sucesso!");
                    ApiHandlerWeather apiHandler = new ApiHandlerWeather();
                    apiHandler.consultarClima(sc);  //obs: to-do -> deve ser criada uma nova classe para juntar as duas apis, qnd criadas colocar esse método aqui!!
                    return;
                }
            }

            System.out.println("CPF ou senha incorretos. Deseja tentar novamente? (s/n)");
            String tentarNovamente = sc.nextLine();
            if (!tentarNovamente.equalsIgnoreCase("s")) {
                System.out.println("Saindo do login...");
                inicializacaoSistema(sc);
                return;
            }
        }
    }
}