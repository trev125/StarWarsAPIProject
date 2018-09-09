package com.example.trev1.starwars3;

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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        String name = this.nameParsed;
        String height = this.heightParsed;
        String weight = this.weightParsed;
        String gender = this.genderParsed;
        String homeWorld = this.homeWorldParsed;

        if(name.equals("Luke Skywalker")){
            DisplayLukePage.tvName.setText(name);
            DisplayLukePage.tvHeight.setText(height);
            DisplayLukePage.tvWeight.setText(weight);
            if(gender.equals("Male")){
                DisplayLukePage.rbGender.toggle();
            }
            DisplayLukePage.tvHomeWorld.setText(homeWorld);
        }
        else if (name.equals("C-3PO")){
            DisplayC3POPage.tvName.setText(name);
            DisplayC3POPage.tvHeight.setText(height);
            DisplayC3POPage.tvWeight.setText(weight);
            if(gender.equals("N/a")){
                DisplayC3POPage.rbGender.toggle();
            }
            DisplayC3POPage.tvHomeWorld.setText(homeWorld);
        }
        else if(name.equals("Yoda")) {
            DisplayYodaPage.tvName.setText(name);
            DisplayYodaPage.tvHeight.setText(height);
            DisplayYodaPage.tvWeight.setText(weight);
            if(gender.equals("Male")){
                DisplayYodaPage.rbGender.toggle();
            }
            DisplayYodaPage.tvHomeWorld.setText(homeWorld);
        }
        //MainActivity.tv.setText(this.dataParsed);
    }
}
