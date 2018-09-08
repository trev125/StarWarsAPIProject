package com.example.trev1.starwars3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textViewID);
    }

    public void sendPageYoda(View v){
        Intent startNewActivity = new Intent(this, DisplayYodaPage.class);
        startActivity(startNewActivity);
        fetchData process = new fetchData();
        process.execute(20);
    }

    public void changeTextLuke(View v){
        Intent startNewActivity = new Intent(this, DisplayLukePage.class);
        startActivity(startNewActivity);
        fetchData process = new fetchData();
        process.execute(1);

    }

    public void changeTextC3(View v){
        fetchData process = new fetchData();
        process.execute(2);

    }





}
