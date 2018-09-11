package com.example.trev1.starwars3;

import android.app.Activity;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class fetchData extends AsyncTask<Integer,Void,Void> {
    String data = "";
    public String dataParsed = "";
    public String nameParsed = "";
    String heightParsed = "";
    String weightParsed = "";
    int heightCM = 0;
    int weightKG = 0;
    String hairColorParsed = "";
    String genderParsed = "";
    String homeWorldParsed = "";
    String speciesParsed = "";

    @Override
    protected Void doInBackground(Integer... integers) {
        try {
            int number = integers[0];
            URL url = new URL("https://swapi.co/api/people/" + number + "/?format=json");//https://api.myjson.com/bins/ap3ok
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);
            nameParsed = "" + JO.get("name");
            heightParsed = "" + JO.get("height");
            weightParsed = "" + JO.get("mass");
            heightCM = Integer.parseInt(heightParsed);
            heightParsed = "" + convertHeight(heightCM);
            weightKG = Integer.parseInt(weightParsed);
            weightParsed = "" + convertWeight(weightKG);
            genderParsed = "" + JO.get("gender");
            genderParsed = genderParsed.substring(0, 1).toUpperCase() + genderParsed.substring(1).toLowerCase();
            //dataParsed =  "Name: " + nameParsed + "\n" + "Height: " + heightParsed + "\n" + "Weight: " + weightParsed+ "\n" + "Gender: " + genderParsed;
            homeWorldParsed = "" + JO.get("homeworld");
            homeWorldParsed = homeworldParse(homeWorldParsed);
//            speciesParsed = "" + JO.get("species");
//            JSONArray jsonarray = new JSONArray(speciesParsed);
//            for (int i = 0; i < jsonarray.length(); i++) {
//                JSONObject jsonobject = jsonarray.getJSONObject(i);
//                String name = jsonobject.getString("name");
//                String url2 = jsonobject.getString("url");
//            }
//            speciesParsed = homeworldParse(speciesParsed);



        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String convertHeight(int height){
        DecimalFormat df = new DecimalFormat("#.#");
        String heightString = "";
        int cm = height;
        double feet = cm/30.48;
        double inches = (cm/2.54) - ((int)feet * 12);
        String formatInches = df.format(inches);
        heightString = "" + (int)feet + " feet and " + formatInches + " inches";
        return heightString;
    }

    protected String convertWeight(int weight){
        DecimalFormat df = new DecimalFormat("#.#");
        double conversion = 2.20462;
        double weightLB = weight * conversion;
        String weightFormatted = df.format(weightLB) + " pounds";
        return weightFormatted;
    }

    protected String homeworldParse (String homeworld) {
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
        }catch (MalformedURLException e){
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (JSONException e) {
        e.printStackTrace();
        }
    return homeworldName;
    }

//    protected String speciesParse (String species) {
//        String speciesURL = species;
//        String speciesName = "";
//        try {
//            URL url = new URL(speciesURL + "?format=json");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            String data = "";
//            while (line != null) {
//                line = bufferedReader.readLine();
//                data = data + line;
//            }
//            JSONObject JO = new JSONObject(data);
//            speciesName = "" + JO.get("name");
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return speciesName;
//    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        String name = this.nameParsed;
        String height = this.heightParsed;
        String weight = this.weightParsed;
        String homeWorld = this.homeWorldParsed;
        String species = this.speciesParsed;

        int [] imageResources = new int [] {R.drawable.tatooine, R.drawable.tatooinemos, R.drawable.dago, R.drawable.tatoo, R.drawable.stew, R.drawable.naboo, R.drawable.alderaan,
                                            R.drawable.corellia, R.drawable.kash};

        DisplayPerson.tvName.setText(name);
        DisplayPerson.tvHeight.setText(height);
        DisplayPerson.tvWeight.setText(weight);
        DisplayPerson.tvHomeWorld.setText(homeWorld);
        DisplayPerson.tvNameTitle.setText(name);
        DisplayPerson.tvSpecies.setText(species);

        if(name.equals("Luke Skywalker")){
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[0]);
        }
        else if (name.equals("C-3PO")){
            DisplayPerson.rbGenderNa.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[1]);
        }
        else if(name.equals("Yoda")) {
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[2]);
        }
        else if(name.equals("Anakin Skywalker")){
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[3]);
        }
        else if (name.equals("Obi-Wan Kenobi")){
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[4]);
        }
        else if(name.equals("Palpatine")) {
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[5]);
        }
        else if(name.equals("Leia Organa")){
            DisplayPerson.rbGenderFemale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[6]);
        }
        else if (name.equals("Han Solo")){
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[7]);
        }
        else if(name.equals("Chewbacca")) {
            DisplayPerson.rbGenderMale.toggle();
            DisplayPerson.ivTopImage.setImageResource(imageResources[8]);
        }
    }
}
