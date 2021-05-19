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
}
