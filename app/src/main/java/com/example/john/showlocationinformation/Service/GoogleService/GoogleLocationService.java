package com.example.john.showlocationinformation.Service.GoogleService;

import android.os.AsyncTask;

import com.example.john.showlocationinformation.data.XML.ParseApplication;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by John on 5/3/2016.
 */

public class GoogleLocationService {
    private LocationServiceCallback callback;
    private String mFileContents;
    private Exception error;

    public GoogleLocationService(LocationServiceCallback callback) {
        this.callback = callback;
    }

    public void downloading(String location) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                mFileContents = downloadXMLFile(params[0]);

                return mFileContents;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }

                ParseApplication parseApplication = new ParseApplication(mFileContents);
                parseApplication.porcess();

                callback.serviceSuccess(parseApplication.getApplication());

            }

        }.execute(location);
    }

    private String downloadXMLFile(String location) {
        StringBuilder tempBuffer = new StringBuilder();
        String link = String.format("https://maps.googleapis.com/maps/api/geocode/xml?latlng=%s&key=AIzaSyDVA8Sm56c8DBDThbBlci4NBDTK8LSYhaw&location_type=ROOFTOP&result_type=street_address", location);
        try {

            URL url = new URL(link);
            URLConnection connection = url.openConnection();
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

        } catch (Exception e) {
            error = e;
        }

        return null;
    }

    public class LocationPositionException extends Exception {
        public LocationPositionException(String detailMessage) {
            super(detailMessage);
        }
    }
}
