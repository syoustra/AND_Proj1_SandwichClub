/*
 * Copyright (C) 2018 Udacity (for base material); Stephanie Youstra (for JSON parsing and layouts)
 */

package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /** Parses JSON data from strings.xml to populate the app's UI */
    /**
     * Reference used: https://www.javacodegeeks.com/2013/10/android-json-tutorial-create-and-parse-json-data.html
     */

    public static Sandwich parseSandwichJson(String json) {
/**
 *      JSON structure
 *        {name:
 *           {mainName: ,
 *           alsoKnownAs: []}
 *        placeOfOrigin: ,
 *        description: ,
 *        image: ,
 *        ingredients: [] }
 *     name, place, description, image, ingredients are all object properties
 *     alsoKnownAs and ingredients are Lists; name is another object/class
 */

        try {
            JSONObject sandwichObj = new JSONObject(json);
            JSONObject nameObj = sandwichObj.getJSONObject("name");
            String mainName = nameObj.getString("mainName");

            JSONArray alsoKnownAs = nameObj.getJSONArray("alsoKnownAs");
            List<String> akaList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                akaList.add(alsoKnownAs.getString(i));
            }

            String placeOfOrigin = sandwichObj.getString("placeOfOrigin");
            String description = sandwichObj.getString("description");
            String image = sandwichObj.getString("image");

            JSONArray ingredientsArray = sandwichObj.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }

            Sandwich sandwich = new Sandwich(mainName, akaList, placeOfOrigin, description, image, ingredientsList);
            return sandwich;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
