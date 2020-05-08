package com.example.processingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadContent extends AsyncTask<String ,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String results = "";

            URL url;

            HttpURLConnection httpURLConnection = null;
            try{
                url = new URL(urls[0]);

                httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.connect();

                InputStream in = httpURLConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1){

                    char character = (char) data;

                    results+=character;

                    data = reader.read();

                }
                return results;

            }
            catch(Exception e){
                e.printStackTrace();
                return null ;

            }



        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String res ;
        DownloadContent dn = new DownloadContent();

        try{
            res = dn.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").get();

            JSONObject obj = new JSONObject(res);

            String a =obj.getString("weather");

            Log.i("Json", a);

            JSONArray arr = new JSONArray(a);

            for (int i = 0 ;i<arr.length();i++){

                JSONObject jsonpart = arr.getJSONObject(i);

                Log.i("main", jsonpart.getString("main"));

                Log.i("description ",jsonpart.getString("description"));


            }

        }
        catch(Exception e ){
            e.printStackTrace();
        }

    }
}
