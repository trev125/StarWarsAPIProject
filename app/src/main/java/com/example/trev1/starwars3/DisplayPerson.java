package com.example.trev1.starwars3;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayPerson extends AppCompatActivity {
    public static TextView tvName;
    public static TextView tvHeight;
    public static TextView tvWeight;
    public static RadioButton rbGenderMale;
    public static RadioButton rbGenderFemale;
    public static RadioButton rbGenderNa;
    public static TextView tvHomeWorld;
    public static ImageView ivTopImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person);

        tvName = findViewById(R.id.name);
        tvHeight = findViewById(R.id.height);
        tvWeight = findViewById(R.id.weight);
        rbGenderMale = findViewById(R.id.rbMale);
        rbGenderFemale = findViewById(R.id.rbFemale);
        rbGenderNa = findViewById(R.id.rbNA);
        tvHomeWorld = findViewById(R.id.homeWorld);
        ivTopImage = findViewById(R.id.topImage);
    }

}