package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {



    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject sandwhich = new JSONObject(json);
            JSONObject name= sandwhich.getJSONObject("name");
            String mainName= name.getString("mainName");

            JSONArray alsoKnownAs= name.getJSONArray("alsoKnownAs");
            ArrayList<String> knownList=new ArrayList<>();

            if(alsoKnownAs.length()!=0) {

                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    knownList.add(alsoKnownAs.getString(i));
                }
            }

            String placeOfOrigin=sandwhich.getString("placeOfOrigin");
            String description=sandwhich.getString("description");
            String image=sandwhich.getString("image");

            JSONArray ingredients= sandwhich.getJSONArray("ingredients");
            ArrayList<String>ingList=new ArrayList<>();

            if(ingredients.length()!=0) {

                for (int i = 0; i < ingredients.length(); i++) {
                    ingList.add(ingredients.getString(i));
                }

            }
            return new Sandwich(mainName,knownList,placeOfOrigin,description,image,ingList);

        } catch (JSONException e) {

            }
        return null;

    }
}
