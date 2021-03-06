package com.uva.restaurant;

import android.content.Intent;
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

        // gather detail view information
        TextView name = findViewById(R.id.detail_title);
        ImageView img = findViewById(R.id.detail_img);
        TextView description = findViewById(R.id.detail_description);
        TextView price = findViewById(R.id.detail_price);

        // update detail screen with correct details
        name.setText(intent.getStringExtra("name"));
        Picasso.get().load(intent.getStringExtra("img"))
                .resize(650,650)
                //.placeholder(R.drawable.pika)
                .error(R.drawable.pika)
                .into(img);
        description.setText(intent.getStringExtra("description"));
        price.setText("$ " + (intent.getStringExtra("price")));

    }
}
