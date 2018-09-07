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
    String heightParsed = "";
    String weightParsed = "";
    int heightCM = 0;
    int weightKG = 0;
    String nameParsed = "";
    String hairColor = "";
    String gender = "";
    String homeWorld = "";

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
            nameParsed = "Name: " + JO.get("name");
            heightParsed = "" + JO.get("height");
            weightParsed = "" + JO.get("mass");
            heightCM = Integer.parseInt(heightParsed);
            heightParsed = "Height: " + convertHeight(heightCM);
            weightKG = Integer.parseInt(weightParsed);
            weightParsed = "Weight: " + convertWeight(weightKG);
            gender = "Gender: " + JO.get("gender");
            dataParsed = nameParsed + "\n" + heightParsed + "\n" + weightParsed+ "\n" + gender;



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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //MainActivity.tv.setText(this.dataParsed);
    }
}
