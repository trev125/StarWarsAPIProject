package com.example.trev1.starwars3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView tv;
    boolean textChanged = false;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textViewID);

//        click = (Button) findViewById(R.id.changeTextButtonID);
//
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View view){
//                fetchData process = new fetchData();
//                process.execute(2);
//            }
//        });

    }

    public void changeTextLuke(View v){
        fetchData process = new fetchData();
        process.execute(1);

    }

    public void changeTextC3(View v){
        fetchData process = new fetchData();
        process.execute(2);

    }





}
