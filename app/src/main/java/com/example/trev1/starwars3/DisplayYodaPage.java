package com.example.trev1.starwars3;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayYodaPage extends AppCompatActivity {
    public static TextView tvName;
    public static TextView tvHeight;
    public static TextView tvWeight;
    public static TextView tvGender;
    public static RadioButton rbGender;
    public static TextView tvHomeWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoda_display_page);

        tvName = findViewById(R.id.yodaName);
        tvHeight = findViewById(R.id.yodaHeight);
        tvWeight = findViewById(R.id.yodaWeight);
        rbGender = findViewById(R.id.rbMale);
        tvHomeWorld = findViewById(R.id.yodaHomeWorld);
    }

}

