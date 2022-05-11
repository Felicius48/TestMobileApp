package com.example.testmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private Button btTouch;
    private EditText edit_user;
    private TextView resultfield;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btTouch = findViewById(R.id.btTouch);
       edit_user = findViewById(R.id.edit_user); //Определение элемента EditText
       resultfield = findViewById(R.id.resfield);

        btTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_user.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.Toast, Toast.LENGTH_LONG).show();
                }
                else {
                    String sity = edit_user.getText().toString();
                    String key = "126aebdc6d9c185ea1b990ea809eb0db";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="+ sity +"&units=metric&appid="+ key;

                    new GetURLData().execute(url);
                }
            }
        });

    }
    protected class GetURLData extends AsyncTask<String, String, String>{
        protected void onPreExecute(){
            super.onPreExecute();
            resultfield.setText("Wait....");
        }


        @Override
        protected String doInBackground(String... strings) {
            HttpsURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null){
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                resultfield.setText("Температура: " + jsonObject.getJSONObject("main").getDouble("temp")+"°"+"\n" + "Ощущается как: " + jsonObject.getJSONObject("main").getDouble("feels_like")+"°");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}