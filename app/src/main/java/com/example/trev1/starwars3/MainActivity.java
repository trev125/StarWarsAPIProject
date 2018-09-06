package com.example.trev1.starwars3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    boolean textChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textViewID);

    }

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
}
