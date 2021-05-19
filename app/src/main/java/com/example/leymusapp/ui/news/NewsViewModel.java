package com.example.leymusapp.ui.news;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leymusapp.Model.ImageId;
import com.example.leymusapp.Repo.FirebaseStorageRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private FirebaseStorageRepository firebaseStorageRepository;
    public MutableLiveData<List<ImageId>> newsImages;

    public NewsViewModel() {
        firebaseStorageRepository = FirebaseStorageRepository.getInstance();
        newsImages = firebaseStorageRepository.getImagesForNewsM();

    }

    public void init(){
        if(newsImages != null){
            return;
        }else{
            newsImages = FirebaseStorageRepository.getInstance().getImagesForNewsM();
        }
    }

    public MutableLiveData<List<ImageId>> getNewsImages() {
        return newsImages;
    }
}