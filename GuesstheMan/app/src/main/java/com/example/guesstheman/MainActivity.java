package com.example.guesstheman;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class MainActivity extends AppCompatActivity {
    ImageView Dimg;
    Button op1,op2,op3,op4;
    int i=0;

    public class DownloadImage extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            String result ="";
            URL url;
            HttpURLConnection httpURLConnection = null;
            try {
                url = new URL(urls[0]);

                httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.connect();

                InputStream in =httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                     char character = (char)data;
                     result+=character;
                     data = reader.read();
                 }
                return result;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dimg = findViewById(R.id.Dimg);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);

        String result = null;
                DownloadImage dn = new DownloadImage();

                try{
                    result = dn.execute("https://www.therichest.com/top-lists/top-100-richest-celebrities/").get();

                    Log.i("Ans", "onCreate: "+result);
                }
                catch (Exception e ){
                    e.printStackTrace();
                }



    }}
