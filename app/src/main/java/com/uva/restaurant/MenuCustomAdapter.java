package com.uva.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MenuCustomAdapter extends ArrayAdapter<MenuItem>{
    private ArrayList<MenuItem> menu;
    ImageView image_url;
    // create menu adapter
    public MenuCustomAdapter(@NonNull Context context, @NonNull ArrayList<MenuItem> objects) {
        super(context, R.layout.activity_menu, objects);
        this.menu = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menuentry, parent, false);
        }

        // gather
        TextView name = convertView.findViewById(R.id.text_description);
        image_url = convertView.findViewById(R.id.text_image);
        TextView price = convertView.findViewById(R.id.text_price);

        MenuItem menuItem = menu.get(position);

        // update
        //text.setText ...
        name.setText(menuItem.getName());
        price.setText("$ " + Double.toString(menuItem.getPrice()));

        // picasso for the win!! image translation with ease
        Picasso.get().load(menuItem.getImageUrl()).resize(350,350).into(image_url);



        //return super.getView(position, convertView, parent);
        return convertView;

    }
}
