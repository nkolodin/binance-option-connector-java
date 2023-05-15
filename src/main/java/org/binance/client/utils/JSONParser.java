package org.binance.client.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private JSONParser() {
    }

    public static String getJSONStringValue(String json, String key) {
        try {
            JSONObject obj = new JSONObject(json);
            return obj.getString(key);
        } catch (JSONException var3) {
            throw new JSONException(String.format("[JSONParser] Failed to get \"%s\"  from JSON object", key));
        }
    }

    public static int getJSONIntValue(String json, String key) {
        try {
            JSONObject obj = new JSONObject(json);
            return obj.getInt(key);
        } catch (JSONException var3) {
            throw new JSONException(String.format("[JSONParser] Failed to get \"%s\" from JSON object", key));
        }
    }

    public static String getJSONArray(ArrayList<?> symbols, String key) {
        try {
            JSONArray arr = new JSONArray(symbols);
            return arr.toString();
        } catch (JSONException var3) {
            throw new JSONException(String.format("[JSONParser] Failed to convert \"%s\" to JSON array", key));
        }
    }
}
