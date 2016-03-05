package com.example.john.showlocationinformation.Service.YahooService;

import com.example.john.showlocationinformation.data.JSON.Channel;

/**
 * Created by John on 5/3/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}