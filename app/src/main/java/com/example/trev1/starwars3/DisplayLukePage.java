package com.example.trev1.starwars3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayLukePage extends AppCompatActivity {
    public static TextView tvName;
    public static TextView tvHeight;
    public static TextView tvWeight;
    public static TextView tvGender;
    public static RadioButton rbGender;
    public static TextView tvHomeWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_luke_page);

        tvName = findViewById(R.id.lukeName);
        tvHeight = findViewById(R.id.lukeHeight);
        tvWeight = findViewById(R.id.lukeWeight);
        rbGender = findViewById(R.id.rbMale);
        tvHomeWorld = findViewById(R.id.lukeHomeWorld);
    }
}
