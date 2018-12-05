package com.uva.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
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

        // gather the correct fields / views
        TextView name = convertView.findViewById(R.id.text_description);
        image_url = convertView.findViewById(R.id.text_image);
        TextView price = convertView.findViewById(R.id.text_price);

        MenuItem menuItem = menu.get(position);

        // update the views with the correct menu value
        name.setText(menuItem.getName());
        price.setText("$ " + Double.toString(menuItem.getPrice()));

        Picasso.get().load(menuItem.getImageUrl())
                .resize(350, 350)
                //.placeholder(R.drawable.pika)
                .error(R.drawable.pika)
                .into(image_url);

        return convertView;
    }
}
