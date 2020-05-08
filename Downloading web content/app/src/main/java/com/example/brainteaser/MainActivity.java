package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class Downloadweb extends AsyncTask<String, Void , String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url ;

            HttpURLConnection httpURLConnection =   null;
            try{
                url = new URL(urls[0]);
                httpURLConnection= (HttpURLConnection)url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char ch =(char)data;
                    result+=ch;
                    data = reader.read();
                }
                return result;
            }catch(Exception e){
                e.printStackTrace();
                return "failed";
            }



        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Downloadweb webcontent = new Downloadweb();
        String result = null;
        try {
            result = webcontent.execute("https://www.stackoverflow.com/").get();
            Log.i("Result", result);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
