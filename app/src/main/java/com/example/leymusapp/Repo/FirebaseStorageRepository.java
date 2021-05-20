package com.example.leymusapp.Repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.leymusapp.Model.ImageId;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseStorageRepository {
    private static FirebaseStorageRepository instance;

    public MutableLiveData<List<ImageId>> imagesForHomeM;
    public MutableLiveData<List<ImageId>> imagesForNewsM;
    public MutableLiveData<List<ImageId>> imagesForGalleryM;

    private List<ImageId> imagesForHome;
    private List<ImageId> imagesForNews;
    private List<ImageId> imagesForGallery;
    private DatabaseReference databaseReference;

    private FirebaseStorageRepository(){
        imagesForHomeM = new MutableLiveData<>();
        imagesForNewsM = new MutableLiveData<>();
        imagesForGalleryM = new MutableLiveData<>();
        databaseReference = FirebaseDatabase.getInstance("https://leymus-a4763-default-rtdb.firebaseio.com").getReference();
    }

    public static synchronized FirebaseStorageRepository getInstance(){
        if(instance == null){
            instance = new FirebaseStorageRepository();
        }
        return instance;
    }

    public void retrieveNewsImages(){
        imagesForNews = new ArrayList<>();
        Query query = databaseReference.child("NewsImages");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("onDataChange inside News");
                System.out.println(snapshot.getValue(ImageId.class));
                imagesForNewsM.postValue(imagesForNews);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.toString());
            }
        });
    }

    public MutableLiveData<List<ImageId>> getImagesForNewsM() {
        retrieveNewsImages();
        imagesForNewsM.setValue(imagesForNews);
        System.out.println("Size news: " + imagesForNews.size());
        return imagesForNewsM;
    }

    public void retrieveHomeImages(){
        imagesForHome = new ArrayList<>();
        Query query = databaseReference.child("HomeImages");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("onDataChange inside Home");
                System.out.println(snapshot.getValue(ImageId.class));
                /*
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    imagesForHome.add(dataSnapshot.getValue(ImageId.class));
                }*/
                imagesForHomeM.postValue(imagesForHome);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.toString());
            }
        });
    }

    public MutableLiveData<List<ImageId>> getImagesForHomeM() {
        retrieveHomeImages();
        imagesForHomeM.setValue(imagesForHome);
        System.out.println("Size home: " + imagesForHome.size());
        return imagesForHomeM;
    }

    public void retrieveGalleryImages(){
        imagesForGallery = new ArrayList<>();
        Query query = databaseReference.child("GalleryImages");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("onDataChange inside Gallery");
                System.out.println(snapshot.getValue(ImageId.class));
                /*
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    imagesForGallery.add(dataSnapshot.getValue(ImageId.class));
                }*/
                imagesForGalleryM.postValue(imagesForHome);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.toString());
            }
        });
    }

    public MutableLiveData<List<ImageId>> getGalleryToReturn() {
        retrieveGalleryImages();
        imagesForGalleryM.setValue(imagesForGallery);
        System.out.println("Size gallery: " + imagesForGallery.size());
        return imagesForGalleryM;
    }
}
