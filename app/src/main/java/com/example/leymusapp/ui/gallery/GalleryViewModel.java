package com.example.leymusapp.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leymusapp.Model.ImageId;
import com.example.leymusapp.Repo.FirebaseStorageRepository;

import java.util.List;

public class GalleryViewModel extends ViewModel {

    private FirebaseStorageRepository firebaseStorageRepository;
    public MutableLiveData<List<ImageId>> galleryImages;

    public GalleryViewModel() {
        firebaseStorageRepository = FirebaseStorageRepository.getInstance();
        galleryImages = firebaseStorageRepository.getGalleryToReturn();
    }

    public void init(){
        if(galleryImages != null){
            return;
        }else{
            galleryImages = FirebaseStorageRepository.getInstance().getGalleryToReturn();
        }
    }

    public MutableLiveData<List<ImageId>> getGalleryImages() {
        return galleryImages;
    }
}