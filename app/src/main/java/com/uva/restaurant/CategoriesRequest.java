package com.uva.restaurant;

import android.content.Context;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<String> newList;
    private Callback callback;
    @Override
    // if error print message
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // Attempt to retrieve JSONObject
        try {
            JSONArray categories = response.getJSONArray("categories");
            newList  = new ArrayList<String>();

            // loop through categories and add each one to the new list
            for (int i = 0; i < categories.length(); i++) {
                newList.add(categories.getString(i));
            }

        }

        catch (JSONException e){
            System.out.println(e.getMessage());
        }

        callback.gotCategories(newList);
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context c) {
        this.context = c;
    }

    void getCategories(Callback activity){
        // use Volley to request new JSONObject with given URL
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;

    }
}
