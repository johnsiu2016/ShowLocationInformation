package com.example.john.showlocationinformation.data.JSON;

import org.json.JSONObject;

/**
 * Created by John on 5/3/2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
