package com.example.john.showlocationinformation;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by John on 4/3/2016.
 */
public class ParseApplication {
    private String xmlData;
    private ArrayList<Application> application;

    public ParseApplication(String xmlData) {
        this.xmlData = xmlData;
        application = new ArrayList<Application>();
    }

    public ArrayList<Application> getApplication() {
        return application;
    }

    boolean porcess() {
        boolean status = true;
        Application currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("formatted_address")) {

                        }

                        if (tagName.equalsIgnoreCase("result")) {
                            inEntry = true;
                            currentRecord = new Application();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if (tagName.equalsIgnoreCase("formatted_address")) {
                                currentRecord.setFormattedAddress(textValue);
                            } else if (tagName.equalsIgnoreCase("result")) {
                                application.add(currentRecord);
                                inEntry = false;
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return true;
    }
}
