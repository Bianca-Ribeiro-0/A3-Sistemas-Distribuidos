package util;

import java.io.BufferedReader;
import java.io.IOException;

public class convertJson {
    public static String convertJsonToString(BufferedReader buffereReader) throws IOException {

        String result, jsonString = "";

        try {
            while ((result = buffereReader.readLine()) != null) {
                jsonString += result;
            }

        } catch (Exception ex) {
            System.out.println("Não foi possível converter o json," + ex.getMessage());
        }


        return jsonString;
    }
}
