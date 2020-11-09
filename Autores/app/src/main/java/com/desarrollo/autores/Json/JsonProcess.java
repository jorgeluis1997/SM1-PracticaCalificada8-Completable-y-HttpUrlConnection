package com.desarrollo.autores.Json;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.desarrollo.autores.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class JsonProcess extends AsyncTask<String, Integer, String> {

    private MainActivity mainActivity;
    private ProgressDialog progressDialog;

    public JsonProcess(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setTitle("Busqueda de libro");
        progressDialog.setMessage("Buscando...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        return download(strings[0]);
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        progressDialog.dismiss();

        if(jsonData!=null){
            JsonAnalyze jsonAnalyze = new JsonAnalyze(mainActivity, jsonData);
            jsonAnalyze.execute();
        }else {
            Toast.makeText(mainActivity, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    private String download(String endPoint)
    {
        try {
            HttpURLConnection connection = Conexion.connect(endPoint);
            assert connection != null;
            if(connection.getResponseCode() == 200)
            {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder jsonData = new StringBuilder();
                //read
                while ((line=bufferedReader.readLine()) != null){
                    jsonData.append(line).append("\n");
                }
                //close resources
                bufferedReader.close();
                inputStream.close();
                //return json
                return jsonData.toString();
            }
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
