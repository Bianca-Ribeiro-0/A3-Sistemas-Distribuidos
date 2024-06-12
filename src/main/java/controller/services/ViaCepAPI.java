package controller.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ViaCep;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ViaCepAPI {

    //tratando informações e conexão com a api publica do ViaCep
    public static ViaCep getCepPorCep(String cep) {
        String jsonResponse = getCepResponse(cep);
        ViaCep viaCep = null;

        if (!jsonResponse.contains("\"erro\":true")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                viaCep = objectMapper.readValue(jsonResponse, ViaCep.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return viaCep;
    }

    public static Map<String, String> getMapPorCep(String cep) {
        String jsonResponse = getCepResponse(cep);

        Map<String, String> mapa = null;
        if (!jsonResponse.contains("\"erro\":true")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                mapa = objectMapper.readValue(jsonResponse, HashMap.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mapa;
    }

    private static String getCepResponse(String cep) {
        StringBuilder response = new StringBuilder();

        try {
            if (!validaCep(cep)) {
                throw new IllegalArgumentException("Formato de CEP inválido");
            }

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(String.format("https://viacep.com.br/ws/%s/json", cep));
                try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                    HttpEntity entity = httpResponse.getEntity();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if (httpResponse.getStatusLine().getStatusCode() != 200) {
                        throw new IllegalStateException("Falha ao obter resposta do servidor");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar API ViaCep", e);
        }

        return response.toString();
    }

    private static boolean validaCep(String cep) {
        return cep.matches("\\d{8}");
    }
}
