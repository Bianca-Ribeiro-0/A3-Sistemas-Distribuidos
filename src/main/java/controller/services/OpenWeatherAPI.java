package controller.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.OpenWeather;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenWeatherAPI {
    //tratando informações e conexão com a api publica do WheaterAPI
    public OpenWeather obterInformacoesClima(String latitude, String longitude) {
        try {
            String key = "f03bc3941a1a17b8ad431281c40400de";
            String completeUrl = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s", latitude, longitude, key);

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(completeUrl);
            HttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Opa, ocorreu um erro: " + statusCode);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            String json = result.toString();

            ObjectMapper mapper = new ObjectMapper();
            OpenWeather openWeather = mapper.readValue(json, OpenWeather.class);

            System.out.println(json);
            return openWeather;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
