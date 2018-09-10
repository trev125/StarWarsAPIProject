package com.example.trev1.starwars3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendPageLuke(View v){
        fetchData process = new fetchData();
        process.execute(1);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageC3(View v){
        fetchData process = new fetchData();
        process.execute(2);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageYoda(View v){
        fetchData process = new fetchData();
        process.execute(20);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);
    }

    public void sendPageDarth(View v){
        fetchData process = new fetchData();
        process.execute(11);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageObi(View v){
        fetchData process = new fetchData();
        process.execute(10);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageSid(View v){
        fetchData process = new fetchData();
        process.execute(21);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageLeia(View v){
        fetchData process = new fetchData();
        process.execute(5);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageHan(View v){
        fetchData process = new fetchData();
        process.execute(14);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }

    public void sendPageChewie(View v){
        fetchData process = new fetchData();
        process.execute(13);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);

    }





}
