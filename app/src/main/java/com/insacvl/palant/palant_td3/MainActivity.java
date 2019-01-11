package com.insacvl.palant.palant_td3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    static final String filename = "gps_logs.log";
    File file;
    static int delay;
    Button buStart = null;
    Button buStop = null;
    Button buClear = null;
    Button buLogs = null;
    SeekBar sBar = null;

    static int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 64;
    TextView delayView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(this.getFilesDir(), filename);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        }

        buStart = findViewById(R.id.buStart);
        buStop = findViewById(R.id.buStop);
        buClear = findViewById(R.id.buClear);
        buLogs = findViewById(R.id.buLogs);
        sBar = findViewById(R.id.seekBar);
        delayView = findViewById(R.id.delayView);
        delay = sBar.getProgress() * 1000;
        String delayString = "Measurement interval = " + delay / 1000 + "s";
        delayView.setText(delayString);

        buStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, LocationService.class);
                startService(serviceIntent);
            }
        });

        buStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, LocationService.class);
                stopService(serviceIntent);
            }
        });

        buClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file.delete();
                Toast.makeText(getApplicationContext(), "File Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        buLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, printLogsActivity.class);
                startActivity(intent);
            }
        });

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                delay = progress * 1000;
                Log.i("2SU", "delay " + delay);
                Intent serviceIntent = new Intent(MainActivity.this, LocationService.class);
                stopService(serviceIntent);
                String delayString = "Measurement interval = " + progress + "s";

                delayView.setText(delayString);

                startService(serviceIntent);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
