package com.insacvl.palant.palant_td3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class printLogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_logs);
        File file = new File(this.getFilesDir(), MainActivity.filename);
        FileInputStream fis = null;
        byte[] b = null;
        try {
            fis = new FileInputStream(file);
            b = new byte[((int) file.length())];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("2SU", "File not found");
        }
        try {
            fis.read(b);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("2SU", "Read error");
        }
        String text = b.toString();
        ListView list = findViewById(R.id.listView1);
        ArrayAdapter<String> tableau = new ArrayAdapter<>(list.getContext(),
                R.layout.montexte);
        Log.i("2SU", text);
        /*
        for (int i=0; i<(int)file.length(); i++) {
            tableau.add(lines[i]);
        }
        list.setAdapter(tableau);
        */
    }
}
