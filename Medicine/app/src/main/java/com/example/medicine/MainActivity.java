package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.medicine.activity.RecipeActivity;
import com.example.medicine.model.Recipe;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button callButton;
    ListView recipeList;
    DBHelper dbHelper;

    ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callButton = findViewById(R.id.callButton);
        recipeList = findViewById(R.id.recipeList);

        View.OnClickListener callButtonListener = v -> {
            Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "103"));
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(myIntent);
        };

        callButton.setOnClickListener(callButtonListener);

        dbHelper = new DBHelper(getApplicationContext());

        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbHelper.openDataBase();

        Cursor cursor = dbHelper.getData();
        int ROW_NUMBER = cursor.getCount();
        ArrayList<String> names = new ArrayList<>();

        cursor.moveToFirst();

        for (int i = 0; i<ROW_NUMBER; i++){

            String name = cursor.getString(1);
            names.add(name);

            Recipe recipe = new Recipe(name, "pics/" + cursor.getString(2), cursor.getString(3));
            this.recipes.add(recipe);

            cursor.moveToNext();

        }

        cursor.close();
        dbHelper.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, names);

        recipeList.setAdapter(adapter);
        recipeList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", this.recipes.get(i));
        startActivity(intent);

    }
}