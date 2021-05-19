package com.example.leymusapp.Model;

import android.graphics.Bitmap;

public class News {
    private String text;
    private String image;

    public News(String text, String image){
        this.text = text;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
