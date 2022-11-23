package com.example.medicine.model;

import java.io.Serializable;

public class Recipe implements Serializable {

    public String name;
    public String imageLink;
    public String recipeText;

    public Recipe(String name, String imageLink, String recipeText) {
        this.name = name;
        this.imageLink = imageLink;
        this.recipeText = recipeText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }
}
