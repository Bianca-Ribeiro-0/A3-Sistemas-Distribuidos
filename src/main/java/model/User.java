package model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class User {
    private String cpf;
    private String nome;
    private String telefone;
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public boolean setCpf(String cpf) {

        if (cpf.matches(
                "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")) {
            this.cpf = cpf;
            return true;
        } else {
            return false;
        }

    }

    public String getNome() {
        return nome;
    }

    public boolean setNome(String nome) {

        if (nome.matches(
                "^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$")) {
            String nomeCapitalized = Arrays.stream(nome.split("\\s"))
                    .map(palavra -> Character.toTitleCase(palavra.charAt(0)) + palavra.substring(1))
                    .collect(Collectors.joining(" "));
            this.nome = nomeCapitalized;
            return true;
        } else {
            return false;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean setTelefone(String telefone) {
        if (telefone.matches(
                "^\\d{10,11}$")) {
            this.telefone = telefone;
            return true;
        } else {
            return false;
        }
    }

    public User() {
    }

    public User(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public User(String cpf, String nome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public boolean setSenha(String senha) {

        if (senha.isBlank()) {
            return false;
        }

        final String regex = "\\|";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(senha);

        if (matcher.find()) {
            return false;
        } else {
            this.senha = senha;
            return true;
        }

    }

    }


