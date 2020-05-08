package com.example.workingwithbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
        BluetoothAdapter BA;
        Button boff ;
        Button dis ;
        ListView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(BA.isEnabled()){
            Toast.makeText(getApplicationContext(), "Bluetooth is enable", Toast.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if(BA.isEnabled()){
                Toast.makeText(getApplicationContext(), "Bluetooth is enable", Toast.LENGTH_LONG).show();
            }
        }
        boff = findViewById(R.id.boff);
        boff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BA.disable();
                if(BA.isEnabled()){
                    Toast.makeText(getApplicationContext(), "Bluetooth isn't turned off", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bluetooth is disabled", Toast.LENGTH_LONG).show();
                }
            }
        });
        dis = findViewById(R.id.dis);
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivity(i);

            }
        });
        Set<BluetoothDevice> paireddevices = BA.getBondedDevices();
        final ArrayList<String> arr = new ArrayList<String>();
        for(BluetoothDevice bluetoothDevice : paireddevices){
            arr.add(bluetoothDevice.getName());
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);

        list =(ListView)findViewById(R.id.list);
        list.setAdapter(ad);







    }
}
