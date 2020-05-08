package com.example.weatherapp2;

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
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button ;
    TextView textView ;
    EditText editText;
    public class DownloadContent extends AsyncTask<String, Void , String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            HttpURLConnection httpURLConnection = null ;
            URL url ;
            try{
                url = new URL(strings[0]);
                httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read() ;

                while(data != -1){
                    char character = (char)data;
                    result+=character;
                    data= reader.read();
                }
                Log.i("main",result);
                return result;
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        @Override
        public void onPostExecute(String results){
            super.onPostExecute(results);

            try {
                JSONObject obj = new JSONObject(results);
                String a = obj.getString("weather");
                JSONArray arr =new JSONArray(a);
                String main = "";
                String message = "";
                String description = "" ;
                for(int i = 0 ;i<arr.length();i++){
                    JSONObject jsonpart = arr.getJSONObject(i);
                    main = jsonpart.getString("main");
                    description=jsonpart.getString("description");
                    if (main != "" && description != "") {
                        message+=main+" "+description;
                    }
                    if(message!=""){
                        textView.setText(message);
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res ;

                DownloadContent dn = new DownloadContent() ;

                try{
                    res = dn.execute("https://api.openweathermap.org/data/2.5/weather?q=" + editText.getText().toString() + "&appid=a0016ecbcb2935ee98c9667c649962a4").get();
                    Log.i("Result", res);
                }
                catch(Exception e ){
                    e.printStackTrace();
                }


            }
        });

    }
}
