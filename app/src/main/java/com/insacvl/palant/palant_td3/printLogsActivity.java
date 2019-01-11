package com.insacvl.palant.palant_td3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.Scanner;

public class printLogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_logs);
        printlogs();
    }

    protected void printlogs() {
        Scanner scan;
        final File file = new File(this.getFilesDir(), MainActivity.filename);
        file.getAbsoluteFile().setReadable(true);
        ListView list = findViewById(R.id.listView1);
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
