package com.example.leymusapp.ui.news;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leymusapp.Repo.FirebaseStorageRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private FirebaseStorageRepository firebaseStorageRepository;
    public MutableLiveData<List<Bitmap>> newsImages;

    public NewsViewModel() {
        firebaseStorageRepository = FirebaseStorageRepository.getInstance();
        newsImages = firebaseStorageRepository.getNewsImagesToReturn();
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<Bitmap>> getNewsImages() {
        return newsImages;
    }
}