package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    EditText cityName;
    TextView weatherText;
    Button weatherBTN;


    public class DownloadContent extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String results = "";

            URL url;

            HttpURLConnection httpURLConnection = null;
            try {
                url = new URL(urls[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();

                InputStream in = httpURLConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char character = (char) data;

                    results += character;

                    data = reader.read();

                }
                return results;

            } catch (Exception e) {
                e.printStackTrace();
                return null;

            }


        }
        @Override
        public void onPostExecute(String results){
            super.onPostExecute(results);

            Log.i("Re",results);



            try{

                JSONObject obj = new JSONObject(results);

                String a =obj.getString("weather");
                JSONArray arr = new JSONArray(a);

                String main = "" ;

                String description = "" ;

                String message = "";

                for (int i = 0 ;i<arr.length();i++){

                    JSONObject jsonpart = arr.getJSONObject(i);

                    main = jsonpart.getString("main");

                    description = jsonpart.getString("description");

                    if(main != "" && description != ""){

                        message += main +": "+description;

                    }


                }
                if(message != ""){

                    weatherText.setText(message);

                }

            }
            catch(Exception e ){
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherText = findViewById(R.id.weather);
        weatherBTN = findViewById(R.id.weatherBTN);

        cityName = findViewById(R.id.cityName);

        weatherBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String res;

                Log.i("weather", cityName.getText().toString());

                DownloadContent dn = new DownloadContent();

                try {

                    res = dn.execute("https://api.openweathermap.org/data/2.5/weather?q=" + cityName.getText().toString() + "&appid=a0016ecbcb2935ee98c9667c649962a4").get();

                    Log.i("weather", res);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

    }

}
