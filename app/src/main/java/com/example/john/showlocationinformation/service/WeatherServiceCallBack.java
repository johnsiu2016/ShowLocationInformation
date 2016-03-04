package com.example.john.showlocationinformation.service;

import com.example.john.showlocationinformation.data.Channel;

/**
 * Created by John on 5/3/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}