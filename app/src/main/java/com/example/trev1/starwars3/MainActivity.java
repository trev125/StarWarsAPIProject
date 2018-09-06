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

        click = (Button) findViewById(R.id.changeTextButtonID);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                fetchData process = new fetchData();
                process.execute();
            }
        });

    }
/*
    public void changeText(View v){

        if(!textChanged){
            tv.setText("I changed the text!");
            textChanged = true;
        }
        else {
            tv.setText("The text was changed back!");
            textChanged = false;
        }

    }
*/




}
