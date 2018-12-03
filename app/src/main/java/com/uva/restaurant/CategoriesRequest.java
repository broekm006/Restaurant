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
    private ArrayList<String> dict;
    private Callback callings;
    @Override
    public void onErrorResponse(VolleyError error) {
        callings.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray categories = response.getJSONArray("categories");
            dict  = new ArrayList<String>();

            for (int i = 0; i < categories.length(); i++) {
                dict.add(categories.getString(i));
            }

        }

        catch (JSONException e){
            System.out.println(e.getMessage());
        }

        callings.gotCategories(dict);
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context c) {
        this.context = c;
    }

    void getCategories(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

        callings = activity;

    }
}
