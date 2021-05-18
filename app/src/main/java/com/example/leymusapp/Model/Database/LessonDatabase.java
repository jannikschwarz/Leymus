package com.example.leymusapp.Model.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.leymusapp.Model.Lesson;

@Database(entities = {Lesson.class}, version = 1)
public abstract class LessonDatabase extends RoomDatabase {
    private static LessonDatabase instance;

    public abstract LessonDao lessonDao();

    public static synchronized LessonDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,
                    LessonDatabase.class, "lesson_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
