package com.example.appegg;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.lang.String;



public class MainActivity extends AppCompatActivity {
    SeekBar seekBar ;
    ImageView  img ;
    static TextView txt ;
    Button btn ;
    long text ;
    int check = 1;
//    public void updateTimer(int secondsleft){
//        int minutes = (int) secondsleft / 60 ;
//        int seconds = secondsleft - minutes * 60 ;
//        String secondString = Integer.toString(seconds);
//        if(secondString== "0"){
//            secondString = "00";
//        }
//        txt.setText(Integer.toString(minutes)+":"+secondString);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img  =  findViewById(R.id.egg);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView  txt = findViewById(R.id.timerTextView);
        btn = (Button)findViewById(R.id.startreset);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                updateTimer(progress);
                int minutes =(int) progress/60 ;
                int seconds = progress - minutes *60 ;
                String secondString  = Integer.toString(seconds);
                if(seconds<=9){
                    secondString = "0"+secondString;
                }

                txt.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(check ==1) {
                    btn.setText("Stop");
                    check  = 0 ;
                    seekBar.setEnabled(false);
                    new CountDownTimer(seekBar.getProgress()*1000,1000){
                        @Override
                        public void onTick(long millisUntilFinished) {
//                        updateTimer((int)millisUntilFinished/1000);
                            long minutes =(int) millisUntilFinished/60000;

                            long seconds = millisUntilFinished - minutes *60000;
                            String secondString  = Long.toString(seconds);

                            if(seconds<=9){
                                secondString = "0"+secondString;
                            }

                            txt.setText(Long.toString(minutes)+":"+Long.toString(seconds));
                        }

                        @Override
                        public void onFinish() {
                            MediaPlayer mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.air);
                            mediaPlayer.start();
                        }

                    }.start();
                }
                else if(check == 0 ){
                    check =1 ;
                    btn.setText("GO!");
                    seekBar.setEnabled(true);
                    txt.setText("00:30");
                }
//               Log.i("Button Pressed", "onClick");

            }
        });


    }
}
