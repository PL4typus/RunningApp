package com.insacvl.palant.palant_td3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.Scanner;

public class printLogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_logs);
        final ListView list = findViewById(R.id.listView1);
        printlogs(list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mapsIntent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapsIntent);
            }
        });
    }

    protected void printlogs(ListView list) {
        Scanner scan;
        final File file = new File(this.getFilesDir(), MainActivity.filename);
        file.getAbsoluteFile().setReadable(true);
        ArrayAdapter<String> tableau = new ArrayAdapter<>(list.getContext(),
                R.layout.montexte);
        Log.d("2SU", "Start reading");
        if (file.getAbsoluteFile().exists()) {
            try {
                scan = new Scanner(file.getAbsoluteFile());
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    Log.d("2SU", line);
                    tableau.add(line);
                }
                scan.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            list.setAdapter(tableau);

        }
    }
}
