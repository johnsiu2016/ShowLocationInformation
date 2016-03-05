package com.example.john.showlocationinformation.data.XML;

/**
 * Created by John on 4/3/2016.
 */
public class Application {
    private String formattedAddress;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String format_address) {
        this.formattedAddress = format_address;
    }

    @Override
    public String toString() {
        return "Application{" +
                "formattedAddress='" + formattedAddress + '\'' +
                '}';
    }
}
