package com.example.trev1.starwars3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayPerson extends AppCompatActivity {
    /*
    *  This is where I am building the DisplayPerson page, which will be used for displaying all of
    *  the data I got from the API. I was originally making separate pages for each person (stupid,
    *  I know) then I realized I could use one page and just change the information dynamically. The
    *  only thing I had an issue with changing dynamically was the Title of the activity. For whatever
    *  reason whenever I tried to set that it would only set for the previous person I visited.
    *      Ex: I visit Luke Skywalker page and the title is set as null. I then go back and visit the
    *      Yoda page, the title will be "Luke Skywalker". Then I go back and click on Leia, the title
    *      will be "Yoda".
    *  So I tried fixing that for a bit, but after a few hours of googling and asking others, I settled
    *  on setting it as null and just loading my own title above the image. Not ideal :(
    *
    *  Back to the page, I create all of the TextViews, RadioButtons and ImageViews here so I can
    *  access them in the Async tasks. I then set them equal to all of the IDs that I have on the
    *  activity page so that I can actually load in the data. That's all that's on this page.
    *
    *  CREDIT FOR THE ICONS: https://www.behance.net/creativeflip
    *
    */
    public static TextView tvName;
    public static TextView tvHeight;
    public static TextView tvWeight;
    public static TextView tvNameTitle;
    public static RadioButton rbGenderMale;
    public static RadioButton rbGenderFemale;
    public static RadioButton rbGenderNa;
    public static TextView tvHomeWorld;
    public static TextView tvHairColor;
    public static ImageView ivTopImage;
    public static TextView tvEyeColor;
    public static TextView tvBirthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person);

        //setTitle(stringDisplayName);

        String stringDisplayName = "";
        this.setTitle(stringDisplayName);

        tvName = findViewById(R.id.name);
        tvHeight = findViewById(R.id.height);
        tvWeight = findViewById(R.id.weight);
        rbGenderMale = findViewById(R.id.rbMale);
        rbGenderFemale = findViewById(R.id.rbFemale);
        rbGenderNa = findViewById(R.id.rbNA);
        tvHomeWorld = findViewById(R.id.homeWorld);
        ivTopImage = findViewById(R.id.topImage);
        tvNameTitle = findViewById(R.id.bigNameTitle);
        tvHairColor = findViewById(R.id.hairColor);
        tvEyeColor = findViewById(R.id.eyeColor);
        tvBirthYear = findViewById(R.id.birthYear);

    }

}
