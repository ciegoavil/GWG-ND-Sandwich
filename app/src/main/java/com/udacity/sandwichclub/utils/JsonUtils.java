package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonMainObj = new JSONObject(json);
            JSONObject jsonNameObj = jsonMainObj.getJSONObject("name");

            sandwich = new Sandwich();
            sandwich.setMainName(jsonNameObj.getString("mainName"));
            List<String> listAlsoKnown = new ArrayList<String>();
            JSONArray arrayAlsoKnown = jsonNameObj.getJSONArray("alsoKnownAs");
            for(int i=0; i<arrayAlsoKnown.length();i++){
                listAlsoKnown.add(arrayAlsoKnown.getString(i));
            }
            sandwich.setAlsoKnownAs(listAlsoKnown);

            sandwich.setDescription(jsonMainObj.getString("description"));
            sandwich.setImage(jsonMainObj.getString("image"));
            sandwich.setPlaceOfOrigin(jsonMainObj.getString("placeOfOrigin"));

            List<String> listIngridients = new ArrayList<String>();
            JSONArray arrayIngridients = jsonMainObj.getJSONArray("ingredients");
            for(int i=0; i<arrayIngridients.length();i++){
                listIngridients.add(arrayIngridients.getString(i));
            }
            sandwich.setIngredients(listIngridients);

        }
        catch(JSONException jex){
            Log.e("JsonParse", "Error parsing the json string");
            return null;
        }
        catch (Exception ex){
            Log.e("GeneralError", "There was an error");
            return null;
        }
        return sandwich;
    }

}
