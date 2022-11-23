package com.example.medicine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicine.R;
import com.example.medicine.model.Recipe;

import java.io.IOException;
import java.io.InputStream;

public class RecipeActivity extends AppCompatActivity {

    ImageView recipeImage;
    TextView recipeText;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        backButton = findViewById(R.id.backButton);
        recipeText = findViewById(R.id.recipeText);
        recipeImage = findViewById(R.id.recipeImage);

        View.OnClickListener backButtonListener = v -> finish();
        backButton.setOnClickListener(backButtonListener);

        Bundle args = getIntent().getExtras();
        Recipe recipe = (Recipe) args.getSerializable("recipe");

        recipeText.setText(recipe.getRecipeText());

        try(InputStream inputStream = getApplicationContext().getAssets().open(recipe.getImageLink())){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            recipeImage.setImageDrawable(drawable);
            recipeImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}