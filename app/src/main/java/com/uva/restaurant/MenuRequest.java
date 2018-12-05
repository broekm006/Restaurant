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
    private ArrayList<MenuItem> newList;
    private Callback callback;
    private String categoryValue;
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // attempt to get JSONObject
        try {
            JSONArray array = response.getJSONArray("items");
            newList  = new ArrayList<MenuItem>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject specific = array.getJSONObject(i);

                // if the name of the category list == the JSONObject category. Capture the fields and save them in the newList
                if (categoryValue.equals(specific.get("category"))){
                    String category = specific.getString("category");
                    String name = specific.getString("name");
                    String description = specific.getString("description");
                    double price = specific.getDouble("price");
                    String imageUrl = specific.getString("image_url");
                    newList.add(new MenuItem(name, description, imageUrl, price, category));
                }
            }

        }

        // in case of error
        catch (JSONException e){
            System.out.println(e.getMessage());
        }

        callback.gotMenu(newList);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    public MenuRequest(Context c) {
        this.context = c;
    }

    void getMenus(Callback activity, String category){
        // use Volley to request new JSONObject with given URL
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
        categoryValue = category;
    }
}

