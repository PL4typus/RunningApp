package com.insacvl.palant.palant_td3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    static final String filename = "gps_logs.log";
    File file;
    Button buStart = null;
    Button buStop = null;
    Button buClear = null;
    Button buLogs = null;
    static int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 64;
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
    }
}
