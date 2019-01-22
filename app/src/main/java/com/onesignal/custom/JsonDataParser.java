/*
 * Created by lenovo
 * Created on 1/22/19 1:34 PM
 * Last modified 11/13/18 8:44 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal.custom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


/**
 * This Class is used for Various JSON parse
 */
public class JsonDataParser {


    private static final Logger log = new Logger();

    /**
     * @param jsoResponse    Response Json object.
     * @param strJsonKeyName Json key for required value.
     * @return The string value of Json key in the given Json Object.
     */
    public static String getStringValueFromJSON(JSONObject jsoResponse, String strJsonKeyName) {
        String strKeyValue = null;
        try {
            strKeyValue = jsoResponse.getString(strJsonKeyName);
        } catch (JSONException r) {
            log.info("JSON Key: " + strJsonKeyName + ", Not Available in JSON Object :" + r.getLocalizedMessage());
        }
        return strKeyValue;
    }

    /**
     * @param jsoResponse    Response Json object.
     * @param strJsonKeyName Json key for required value.
     * @return The integer value of Json key in the given Json Object.
     */
    public static int getIntegerValueFromJSON(JSONObject jsoResponse, String strJsonKeyName) {
        int intKeyValue = 0;
        try {
            intKeyValue = jsoResponse.getInt(strJsonKeyName);
        } catch (JSONException r) {
            log.info("JSON Key: " + strJsonKeyName + ", Not Available in JSON Object :" + r.getLocalizedMessage());
        }
        return intKeyValue;
    }

    /**
     * @param jsoResponse    Response Json object.
     * @param strJsonKeyName Json key for required value.
     * @return The long value of Json key in the given Json Object.
     */
    public static long getLongValueFromJSON(JSONObject jsoResponse, String strJsonKeyName) {
        try {
            return jsoResponse.getLong(strJsonKeyName);
        } catch (JSONException r) {
            log.info("JSON Key: " + strJsonKeyName + ", Not Available in JSON Object :" + r.getLocalizedMessage());
            return 0;
        }
    }

    /**
     * @param jsoResponse    Response Json object.
     * @param strJsonKeyName Response Json object.
     * @return The double value of Json key in the given Json Object.
     */
    public static double getDoubleValueFromJSON(JSONObject jsoResponse, String strJsonKeyName) {
        try {
            return jsoResponse.getDouble(strJsonKeyName);
        } catch (Exception r) {
            log.info("JSON Key: " + strJsonKeyName + ", Not Available in JSON Object :" + r.getLocalizedMessage());
            return 0;
        }
    }

    /**
     * @param strJsonString string value.
     * @return true --> if given String is Valid JSON false --> if given String
     * is in-Valid JSON Data
     */
    public static boolean isValidJsonMessage(String strJsonString) {
        try {
            JSONObject jsoresponse = new JSONObject(strJsonString);
            return true;
        } catch (JSONException | NullPointerException ex) {
            log.info("Invalid JSON Data ." + ex.getLocalizedMessage());
            return false;
        }
    }


    /**
     * @param strJsonString string value.
     * @return convert and returns Json Object if the string is valid Json format or else returns null.
     */
    public static JSONObject getJsonObject(String strJsonString) {
        try {
            return new JSONObject(strJsonString);
        } catch (JSONException ex) {
            log.info("Invalid JSON Data ." + ex.getLocalizedMessage());
            return null;
        }
    }

    /**
     * @param mJsArray JSONArray
     * @param position array value position
     * @return convert and returns Json Object if the JSONArray is valid and it contains value of particular position.
     */
    public static JSONObject getJsonObject(JSONArray mJsArray, int position) {
        try {
            return mJsArray.getJSONObject(position);
        } catch (JSONException ex) {
            log.info("Invalid JSON Data ." + ex.getLocalizedMessage());
            return null;
        }
    }


    /**
     * Method used to Check the Server response code given is Success or failure. All Server
     * Json Response must contains rCode - Response Code
     *
     * @param strResponse - String response data.
     * @return true --> Request is success false --> Response is failed
     */
    public static boolean isRequestSuccess(String strResponse) {
        try {
            JSONObject jsonKnossos = new JSONObject(strResponse);
            int intResponseCode = jsonKnossos.getInt("status");
            return intResponseCode == 0;
        } catch (JSONException ex) {
            log.info("Invalid JSON Data was sent in Server Response." + ex.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Method used to Check the Server response code given is Success or failure. All Server
     * Json Response must contains rCode - Response Code
     *
     * @param jsoResponse JsonObject data.
     * @return true --> Request is success false --> Response is failed
     */
    public static boolean isRequestSuccess(JSONObject jsoResponse) {
        try {
            int intResponseCode = jsoResponse.getInt("status");
            return intResponseCode == 0;
        } catch (JSONException | NullPointerException ex) {
            log.info("Invalid JSON Data was sent in Server Response." + ex.getLocalizedMessage());
            return false;
        }
    }

    public static JSONArray getJsonArray(JSONObject jsoResponse, String strArrayKeyName) {
        JSONArray jsaValue;
        try {
            jsaValue = jsoResponse.getJSONArray(strArrayKeyName);
        } catch (JSONException r) {
            jsaValue = new JSONArray();
            log.info("JSON Array Key: " + strArrayKeyName + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaValue;
    }

    public static String loadJSONFromAsset(String filename) {
        String json;
        try {
            InputStream is = App.getAppContext().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONObject addJSONObjectToJSONObject(JSONObject jsObj, String strId, JSONObject jsValue) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject();
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = jsObj;
            log.info("JSON Array Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }

    public static JSONObject addJSONArraytoJSONObject(JSONObject jsObj, String strId, JSONArray jsValue) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject();
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = jsObj;
            log.info("JSON Array Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }

    public static JSONObject addStringToJSONObject(JSONObject jsObj, String strId, String jsValue) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject();
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = jsObj;
            log.info("JSON Array Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }

    public static JSONObject addIntToJSONObject(JSONObject jsObj, String strId, int jsValue) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject();
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = jsObj;
            log.info("JSON Array Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }

    public static JSONObject addDoubleToJSONObject(JSONObject jsObj, String strId, double jsValue) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject();
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = jsObj;
            log.info("JSON Array Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }

    public static JSONObject addFloatToJSONObject(JSONObject jsObj, String strId, float jsValue) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject();
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = jsObj;
            log.info("JSON Array Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }


    public static JSONObject convertJson2JSON(JSONObject jsObj) {
        JSONObject jsaObj;
        try {
            jsaObj = new JSONObject(jsObj.toString());
        } catch (JSONException r) {
            jsaObj = new JSONObject();
            log.info("JSONObject not valid : " + r.getMessage());
        }
        return jsaObj;
    }

    public static JSONObject addJSONObjectToJSONArray(JSONObject jsaObj, String strId, JSONObject jsValue) {
        try {
            if (jsaObj == null) {
                jsaObj = new JSONObject();
            }
            jsaObj.put(strId, jsValue);
        } catch (JSONException r) {
            jsaObj = new JSONObject();
            log.info("JSON object Key: " + jsValue + ", Not Available in JSON Object :" + r.getMessage());
        }
        return jsaObj;
    }


}
