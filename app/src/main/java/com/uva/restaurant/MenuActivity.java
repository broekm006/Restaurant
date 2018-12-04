package com.uva.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String category = intent.getStringExtra("categories");
        MenuRequest x = new MenuRequest(this);
        x.getMenus(this, category);
    }

    public void gotMenu(ArrayList<MenuItem> menu) {
        Toast.makeText(this, "ello", Toast.LENGTH_LONG).show();

        // change arrayadapter to custom view > custom adapter
        MenuCustomAdapter adapter = new MenuCustomAdapter(this, menu);
        ListView listView = (ListView) findViewById(R.id.menu_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
        System.out.println("sad" + adapter.getCount());
    }

    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MenuActivity.this, CategoriesActivity.class);
            //intent.putExtra("categories", adapterView.getItemAtPosition(i).toString());
            startActivity(intent);
        }
    }
}