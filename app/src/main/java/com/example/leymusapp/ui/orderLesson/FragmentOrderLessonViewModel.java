package com.example.leymusapp.ui.orderLesson;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.leymusapp.Model.Lesson;
import com.example.leymusapp.Repo.LessonRepository;
import com.example.leymusapp.Repo.UserRepository;

import java.util.List;

public class FragmentOrderLessonViewModel extends AndroidViewModel {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public FragmentOrderLessonViewModel(Application app){
        super(app);
        lessonRepository = LessonRepository.getInstance(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<List<Lesson>> getAllLessons(){
        return lessonRepository.getAllLessons();
    }

    public void insertLesson(final Lesson lesson){
        lessonRepository.insert(lesson);
    }

    public void updateLesson(final Lesson lesson){
        lessonRepository.updateLesson(lesson);
    }

    public void deleteLesson(final Lesson lesson){
        lessonRepository.deleteLesson(lesson);
    }

    public void deleteAllLessons(){
        lessonRepository.deleteAllLessons();
    }
}