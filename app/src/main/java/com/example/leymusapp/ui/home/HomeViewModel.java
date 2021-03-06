package com.example.leymusapp.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leymusapp.Model.ImageId;
import com.example.leymusapp.Repo.FirebaseStorageRepository;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private FirebaseStorageRepository firebaseStorageRepository;
    public MutableLiveData<List<ImageId>> imgsToReturnM;

    public HomeViewModel() {
        firebaseStorageRepository = FirebaseStorageRepository.getInstance();
        imgsToReturnM = firebaseStorageRepository.getImagesForHomeM();
    }

    public void init(){
        if(imgsToReturnM != null){
            return;
        }else{
            imgsToReturnM = FirebaseStorageRepository.getInstance().getImagesForHomeM();
        }
        System.out.println("Inside init" + imgsToReturnM.getValue().size());
    }

    public MutableLiveData<List<ImageId>> getImgToReturn() {
        return imgsToReturnM;
    }
}