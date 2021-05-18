package com.example.leymusapp.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.leymusapp.Model.Lesson;

import java.util.List;

@Dao
public interface LessonDao {

    @Insert
    void insert(Lesson lesson);

    @Update
    void update(Lesson lesson);

    @Delete
    void delete(Lesson lesson);

    @Query("DELETE FROM lesson_table")
    void deleteAllLessons();

    @Query("SELECT * FROM lesson_table ORDER BY name DESC")
    LiveData<List<Lesson>> getAllLessons();
}
