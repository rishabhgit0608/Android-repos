package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//
//                //Insert code to be run every second
//                Log.i("Runnable has run !", "A second is passed ?");
//                handler.postDelayed(this, 1000);
//
//            }
//        };
//        handler.post(run);
        new CountDownTimer(10000,1000){
            public void onTick(long MillisecondsUntilDone){
                //count down is counting down(Every second)
                Log.i("Seconds left" , String.valueOf(MillisecondsUntilDone / 1000) );

            }
            public void onFinish(){
                //counter is finished
                Log.i("Done", "onFinish ");
            }
        }.start();

    }
}
