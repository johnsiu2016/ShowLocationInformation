package com.example.john.showlocationinformation.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.john.showlocationinformation.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by John on 5/3/2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallBack callBack;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallBack callBack) {
        this.callBack = callBack;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String location) {
        this.location = location;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", params[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection urlConnection = url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return reader.toString();

                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && error != null) {
                    callBack.servcieFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if (count == 0) {
                        callBack.servcieFailure(new LocationWeatherException("No weather information found for " + getLocation()));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("result").optJSONObject("channel"));

                    callBack.serviceSuccess(channel);
                } catch (JSONException e) {
                    callBack.servcieFailure(e);
                }
            }
        }.execute(location);
    }
    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
