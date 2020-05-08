package com.example.imagedownloading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button downloadImagebtn ;
    ImageView img ;
    int i= 0 ;
    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url ;
            HttpURLConnection httpURLConnection = null;
            try {

                url = new URL(strings[i]);
                i++;
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();

                InputStream in = httpURLConnection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(in);

                return mybitmap;

            }catch(Exception e){
                e.printStackTrace();
                return null;

            }



        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadImagebtn = findViewById(R.id.ImageButton);
        img = findViewById(R.id.imageView);
               downloadImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadImage dn = new DownloadImage();
                try{
                    Bitmap myimg= dn.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png","https://upload.wikimedia.org/wikipedia/en/0/02/Homer_Simpson_2006.png").get();
                    img.setImageBitmap(myimg);

                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });


    }
}
