package com.insacvl.palant.palant_td3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buStart = null;
    Button buStop = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buStart = findViewById(R.id.buStart);
        buStop = findViewById(R.id.buStop);

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
    }
}
