package com.example.leymusapp.ui.orderedLessons;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leymusapp.Model.Lesson;
import com.example.leymusapp.Repo.FirebaseStorageRepository;
import com.example.leymusapp.Repo.LessonRepository;
import com.example.leymusapp.ui.orderLesson.FragmentOrderLessonViewModel;

import java.util.List;

public class FragmentOrderedLessonsViewModel extends AndroidViewModel {
    private final LessonRepository lessonRepository;
    private LiveData<List<Lesson>> allLessons;

    public FragmentOrderedLessonsViewModel(Application app){
        super(app);
        lessonRepository = LessonRepository.getInstance(app);
        allLessons = lessonRepository.getAllLessons();
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