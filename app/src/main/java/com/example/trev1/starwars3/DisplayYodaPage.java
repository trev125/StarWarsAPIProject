package com.example.trev1.starwars3;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class DisplayYodaPage extends Activity {

    public static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoda_display_page);

        tv = findViewById(R.id.yodaBio);

        fetchData process = new fetchData();
        process = (fetchData) process.execute(20);

        String test = process.dataParsed;
        DisplayYodaPage.tv.setText(test);
    }

}

