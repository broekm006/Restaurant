package com.uva.restaurant;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<MenuItem> dict;
    private Callback callback;
    private String test;
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray array = response.getJSONArray("items");
            dict  = new ArrayList<MenuItem>();
            for (int i = 0; i < array.length(); i++) {
                //dict.add(array.getString(i));
                //String category = response.getString("category");
                JSONObject specific = array.getJSONObject(i);
                if (test.equals(specific.get("category"))){
                    String category = specific.getString("category");
                    String name = specific.getString("name");
                    String description = specific.getString("description");
                    double price = specific.getDouble("price");
                    String imageUrl = specific.getString("image_url");
                    dict.add(new MenuItem(name, description, imageUrl, price, category));
                }
            }

        }

        catch (JSONException e){
            System.out.println(e.getMessage());
        }

        callback.gotMenu(dict);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    public MenuRequest(Context c) {
        this.context = c;
    }

    void getMenus(Callback activity, String category){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
        test = category;
    }
}

