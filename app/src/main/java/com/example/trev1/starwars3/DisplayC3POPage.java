package com.example.trev1.starwars3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayC3POPage extends AppCompatActivity {
    public static TextView tvName;
    public static TextView tvHeight;
    public static TextView tvWeight;
    public static TextView tvGender;
    public static RadioButton rbGender;
    public static TextView tvHomeWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_c3_popage);

        tvName = findViewById(R.id.c3Name);
        tvHeight = findViewById(R.id.c3Height);
        tvWeight = findViewById(R.id.c3Weight);
        rbGender = findViewById(R.id.rbNA);
        tvHomeWorld = findViewById(R.id.c3HomeWorld);
    }


}
