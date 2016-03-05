package com.example.john.showlocationinformation.Service.GoogleService;


import com.example.john.showlocationinformation.data.XML.Application;

import java.util.ArrayList;

/**
 * Created by John on 5/3/2016.
 */

public interface LocationServiceCallback {
    void serviceSuccess(ArrayList<Application> application);
    void serviceFailure(Exception exception);
}
