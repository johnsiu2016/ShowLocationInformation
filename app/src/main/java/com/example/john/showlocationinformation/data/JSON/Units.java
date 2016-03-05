package com.example.john.showlocationinformation.data.JSON;

import com.example.john.showlocationinformation.data.JSON.JSONPopulator;

import org.json.JSONObject;

/**
 * Created by John on 5/3/2016.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
