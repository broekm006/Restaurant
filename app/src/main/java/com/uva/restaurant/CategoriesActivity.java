package com.uva.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView listView = (ListView) findViewById(R.id.cato_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ListViewClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("categories", adapterView.getItemAtPosition(i).toString());
            startActivity(intent);
        }
    }
}

