package com.example.leymusapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lesson_table")
public class Lesson {
    private String name;
    @PrimaryKey(autoGenerate = true)
    private int iconId;
    private String type;
    private String email;


    public Lesson(String name, int iconId, String type, String email){
        this.name = name;
        this.iconId = iconId;
        this.type = type;
        this.email = email;
    }

    public int getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }
}
