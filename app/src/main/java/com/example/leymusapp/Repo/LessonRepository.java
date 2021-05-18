package com.example.leymusapp.Repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.leymusapp.Model.Database.LessonDao;
import com.example.leymusapp.Model.Database.LessonDatabase;
import com.example.leymusapp.Model.Lesson;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LessonRepository {
    private static LessonRepository instance;
    private final LessonDao lessonDao;
    private final LiveData<List<Lesson>> allLessons;
    private final ExecutorService executorService;

    private LessonRepository(Application application){
        LessonDatabase database = LessonDatabase.getInstance(application);
        lessonDao = database.lessonDao();
        allLessons = lessonDao.getAllLessons();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized LessonRepository getInstance(Application application){
        if(instance == null){
            instance = new LessonRepository(application);
        }
        return instance;
    }

    public LiveData<List<Lesson>> getAllLessons(){
        return allLessons;
    }

    public void insert(Lesson lesson){
        executorService.execute(() -> lessonDao.insert(lesson));
    }

    public void deleteAllLessons(){
        executorService.execute(lessonDao::deleteAllLessons);
    }

    public void deleteLesson(Lesson lesson){
        executorService.execute(() -> lessonDao.delete(lesson));
    }

    public void updateLesson(Lesson lesson){
        executorService.execute(() -> lessonDao.update(lesson));
    }
}
