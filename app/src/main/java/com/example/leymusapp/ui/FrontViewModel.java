package com.example.leymusapp.ui;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.leymusapp.Model.Lesson;
import com.example.leymusapp.Repo.LessonRepository;
import com.example.leymusapp.Repo.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class FrontViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public FrontViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        lessonRepository = LessonRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut(){
        userRepository.signOut();
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

    public void createNewLesson(String name, String type) {
        List<Lesson> allLessons = getAllLessons().getValue();
        Lesson toAdd = new Lesson(name,allLessons.size(),type,userRepository.getCurrentUser().getValue().getEmail());
        boolean exists = false;
        for(int i = 0; i<allLessons.size(); i++){
            if(allLessons.get(i).equals(toAdd)){
                updateLesson(toAdd);
                exists = true;
                break;
            }
        }
        if(exists){
            insertLesson(toAdd);
        }
    }
}
