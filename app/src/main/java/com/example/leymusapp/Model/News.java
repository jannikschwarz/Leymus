package com.example.leymusapp.Model;

import android.graphics.Bitmap;

public class News {
    private String name;
    private String text;
    private Bitmap image;

    public News(String name, String text, Bitmap image){
        this.text = text;
        this.image = image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
