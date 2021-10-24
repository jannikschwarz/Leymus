package com.example.leymus;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.leymus.Repository.FirebaseFileRepository;

import java.io.File;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final FirebaseFileRepository fileRepository;

    public MainActivityViewModel(Application app){
        super(app);
        fileRepository = FirebaseFileRepository.GetInstance(app);
    }

    public LiveData<List<File>> getMainPageTextFiles(){
        return fileRepository.getMainPageTextFiles();
    }
}
