package com.example.trev1.starwars3;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

@SuppressWarnings("StringConcatenationInLoop")
public class fetchData extends AsyncTask<Integer,Void,Void> {
    /* I set these variables here so I can access them in my onPostExecute function so I don't have
    *  to worry about passing the variables down there.
    */
    private String data = "";
    private String nameParsed = "";
    private String heightParsed = "";
    private String weightParsed = "";
    private String hairColorParsed = "";
    private String genderParsed = "";
    private String homeWorldParsed = "";
    private String eyeColorParsed = "";
    private String birthYearParsed = "";

    @Override
    protected Void doInBackground(Integer... integers) {
        /*
        *  A lot happens in this function, so I'll try to break it all down so it makes sense. I'll
        *  also try to label all of the sections below with what they are doing.
        *       1. I need to find out what person I am parsing the data for, so I passed in a number
        *          when I called this Async function, and I take that and concatenate it directly into
        *          the URL.
        *
        *       2. Then I get all the data from the actual JSON object. I was lucky with this API
        *          because none of the data I wanted was in an Array. (The HomePlanet was in it's
        *          own JSON object, but we wll get to that later.)
        *
        *       3. Next, I loaded it into a JSON object, and started to set the data to the local
        *          variables that I created earlier. I accessed them using the Keys that are in the
        *          JSON objects.
        *
        *          3a. Things got a little funky sometimes. I ended up having to convert some of the
        *              data that I got to some more readable stuff. I adapted a lot of the conversion
        *              functions (convertHeight and convertWeight) from different forums online.
        *              Basically, I just took CM and KG and converted them to Feet and Inches or Pounds.
        *
        *          3b. Similarly, there were some strings that I got back from the JSON object that
        *              were not capitalized and that bothered me, so I fixed that.
        *
        *          3c. This was a doozie. Kinda. The HomePlanet information was actually a URL to
        *              another API call. I ended up parsing that in its own function to just get the
        *              name of it. It wasn't too bad. I probably could have changed up the original
        *              parser I used in step 3, but I would have to change a lot of the ways I was
        *              handling data, so I decided to write another function.
        *
        *       4. Onto the onPostExecute function! Okay so here I create an array of the images I
        *          want to show on the specific pages of the people. Then I access the different
        *          elements of the DisplayPerson activity and set the information of the person. The
        *          only thing that I had to be careful with here was the Gender and the Top Image.
        *          Because those can be different per person, I did the switch statements.
        *
        *  That's it! That's all of the project, hopefully explained well enough.
        */
        try {
            int number = integers[0];//Step 1
            URL url = new URL("https://swapi.co/api/people/" + number + "/?format=json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//Step 2
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);//Step 3
            nameParsed = "" + JO.get("name");
            heightParsed = "" + JO.get("height");
            weightParsed = "" + JO.get("mass");
            int heightCM = Integer.parseInt(heightParsed);
            heightParsed = "" + convertHeight(heightCM);//Step 3a
            int weightKG = Integer.parseInt(weightParsed);
            weightParsed = "" + convertWeight(weightKG);//Step3a
            genderParsed = "" + JO.get("gender");
            genderParsed = genderParsed.substring(0, 1).toUpperCase() + genderParsed.substring(1).toLowerCase();//Step 3b
            homeWorldParsed = "" + JO.get("homeworld");
            homeWorldParsed = homeworldParse(homeWorldParsed);//Step 3c
            homeWorldParsed = homeWorldParsed.substring(0, 1).toUpperCase() + homeWorldParsed.substring(1).toLowerCase();//Step 3b
            hairColorParsed = "" + JO.get("hair_color");
            hairColorParsed = hairColorParsed.substring(0, 1).toUpperCase() + hairColorParsed.substring(1).toLowerCase();//Step 3b
            eyeColorParsed = "" + JO.get("eye_color");
            eyeColorParsed = eyeColorParsed.substring(0, 1).toUpperCase() + eyeColorParsed.substring(1).toLowerCase();//Step 3b
            birthYearParsed = "" + JO.get("birth_year");

        }catch (JSONException | IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String convertHeight(int height){
        //Like I said above, this is just to change the height from CM to feet and inches
        DecimalFormat df = new DecimalFormat("#.#");
        String heightString;
        double cmConst = 30.48;
        double inConst = 2.54;
        int ftConst = 12;

        //int cm = height;
        double feet = height/cmConst;
        double inches = (height/inConst) - ((int)feet * ftConst);
        String formatInches = df.format(inches);
        heightString = "" + (int)feet + " feet and " + formatInches + " inches";
        return heightString;
    }

    private String convertWeight(int weight){
        //Like I said above, this is just to convert from KG to Pounds.
        DecimalFormat df = new DecimalFormat("#.#");
        double conversion = 2.20462;

        double weightLB = weight * conversion;
        String weightFormatted = df.format(weightLB) + " pounds";
        return weightFormatted;
    }

    private String homeworldParse(String homeworld) {
        /*
        * This is the second parse function to get the Home World name for the person. It is
        * essentially the same function that I use earlier to get the personal information.
        */
        String homeworldURL = homeworld;
        String homeworldName = "";
        try {
            URL url = new URL(homeworldURL + "?format=json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String data = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JO = new JSONObject(data);
            homeworldName = "" + JO.get("name");
        } catch (IOException e) {
        e.printStackTrace();
        } catch (JSONException e) {
        e.printStackTrace();
        }
    return homeworldName;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        /*
        *  Like I said above, this is how I communicate with the data I have being built on a different
        *  thread that is constructing the actual UI. I take all the data I have gotten from the API
        *  and I am setting it to the different TextViews I have set up to receive them. Gender is
        *  handled a bit differently because its a radio button. Same with images because I wanted
        *  them to be different depending on who you were viewing. The Image is generally the Home
        *  Planet of the person. The exception is Yoda because his Home Planet is "Unknown" so I did
        *  a picture of Dagobah.
        */
        super.onPostExecute(aVoid);

        int [] imageResources = new int [] {R.drawable.tatooine, R.drawable.tatooinemos, R.drawable.dago, R.drawable.tatoo, R.drawable.stew, R.drawable.naboo, R.drawable.alderaan, R.drawable.corellia, R.drawable.kash};

        DisplayPerson.tvName.setText(this.nameParsed);
        DisplayPerson.tvHeight.setText(this.heightParsed);
        DisplayPerson.tvWeight.setText(this.weightParsed);
        DisplayPerson.tvHomeWorld.setText(this.homeWorldParsed);
        DisplayPerson.tvNameTitle.setText(this.nameParsed);
        DisplayPerson.tvHairColor.setText(this.hairColorParsed);
        DisplayPerson.tvEyeColor.setText(this.eyeColorParsed);
        DisplayPerson.tvBirthYear.setText(this.birthYearParsed);

        switch (this.genderParsed) {
            case "Male":
                DisplayPerson.rbGenderMale.toggle();
                break;
            case "Female":
                DisplayPerson.rbGenderFemale.toggle();
                break;
            default:
                DisplayPerson.rbGenderNa.toggle();
                break;
        }

        switch (this.nameParsed) {
            case "Luke Skywalker":
                DisplayPerson.ivTopImage.setImageResource(imageResources[0]);
                break;
            case "C-3PO":
                DisplayPerson.ivTopImage.setImageResource(imageResources[1]);
                break;
            case "Yoda":
                DisplayPerson.ivTopImage.setImageResource(imageResources[2]);
                break;
            case "Anakin Skywalker":
                DisplayPerson.ivTopImage.setImageResource(imageResources[3]);
                break;
            case "Obi-Wan Kenobi":
                DisplayPerson.ivTopImage.setImageResource(imageResources[4]);
                break;
            case "Palpatine":
                DisplayPerson.ivTopImage.setImageResource(imageResources[5]);
                break;
            case "Leia Organa":
                DisplayPerson.ivTopImage.setImageResource(imageResources[6]);
                break;
            case "Han Solo":
                DisplayPerson.ivTopImage.setImageResource(imageResources[7]);
                break;
            case "Chewbacca":
                DisplayPerson.ivTopImage.setImageResource(imageResources[8]);
                break;
        }
    }
}
