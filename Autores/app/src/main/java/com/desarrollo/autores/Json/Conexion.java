package com.desarrollo.autores.Json;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexion {
    public static HttpURLConnection connect(String jsonURL)
    {
        try
        {
            URL url = new URL(jsonURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
