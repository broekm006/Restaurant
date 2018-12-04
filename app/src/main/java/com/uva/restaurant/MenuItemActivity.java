package com.uva.restaurant;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        Intent intent = getIntent();

        TextView name = findViewById(R.id.detail_title);
        ImageView img = findViewById(R.id.detail_img);
        TextView description = findViewById(R.id.detail_description);
        TextView price = findViewById(R.id.detail_price);

        name.setText(intent.getStringExtra("name"));
        Picasso.get().load(intent.getStringExtra("img")).resize(650,650).into(img);
        description.setText(intent.getStringExtra("description"));
        price.setText("$ " + (intent.getStringExtra("price")));
    }
}
