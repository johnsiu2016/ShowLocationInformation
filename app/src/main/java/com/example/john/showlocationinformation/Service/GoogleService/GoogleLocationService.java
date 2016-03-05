package com.example.john.showlocationinformation.Service.GoogleService;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by John on 5/3/2016.
 */

public class GoogleLocationService {
    private String mFileContents;

    public String getmFileContents() {
        return mFileContents;
    }

    public void downloading(String location) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                mFileContents = downloadXMLFile(params[0]);
                if (mFileContents == null) {
                    Log.d("Downloading", "Error downloading");
                }

                return mFileContents;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("Downloading", "Result was: " + s);
            }

        }.execute(location);
    }

    private String downloadXMLFile(String location) {
        StringBuilder tempBuffer = new StringBuilder();
        String link = String.format("https://maps.googleapis.com/maps/api/geocode/xml?latlng=%s&key=AIzaSyDVA8Sm56c8DBDThbBlci4NBDTK8LSYhaw&location_type=ROOFTOP&result_type=street_address", location);
        try {

            URL url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            int response = connection.getResponseCode();
            Log.d("Downloading", "The response code was " + response);
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int charRead;
            char[] inputBuffer = new char[500];
            while (true) {
                charRead = isr.read(inputBuffer);
                if (charRead <= 0) {
                    break;
                }
                tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
            }

            return tempBuffer.toString();

        } catch (IOException e) {
            Log.d("Downloading", "IO Exception reading data: " + e.getMessage());
        }

        return null;
    }
}
