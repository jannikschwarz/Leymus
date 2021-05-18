package com.example.leymusapp.Repo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.leymusapp.Model.ImageId;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FirebaseStorageRepository {
    private final FirebaseStorage storage;
    private static FirebaseStorageRepository instance;
    private StorageReference imageRef;

    public MutableLiveData<List<ImageId>> imagesForHomeM;
    public MutableLiveData<List<Bitmap>> newsImagesToReturn;
    public MutableLiveData<List<Bitmap>> galleryToReturn;

    private List<ImageId> imagesForHome;

    private List<Bitmap> newsImages;
    private List<Bitmap> galleryImages;
    private boolean retrievedImageIsNotNull;
    private int numberNews = 1;
    private int numberGallery = 1;

    private FirebaseStorageRepository(){
        storage = FirebaseStorage.getInstance();
        imageRef = storage.getReference();
        imagesForHomeM = new MutableLiveData<>();
        newsImagesToReturn = new MutableLiveData<>();
        galleryToReturn = new MutableLiveData<>();

        newsImages = new ArrayList<>();
        galleryImages = new ArrayList<>();

        retrieveNewsImages();
        retrieveGalleryImages();
        galleryToReturn.setValue(galleryImages);
        newsImagesToReturn.setValue(newsImages);
    }

    public static synchronized FirebaseStorageRepository getInstance(){
        if(instance == null){
            instance = new FirebaseStorageRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Bitmap>> getNewsImagesToReturn() {
        return newsImagesToReturn;
    }

    public MutableLiveData<List<Bitmap>> getGalleryToReturn() {
        return galleryToReturn;
    }

    public void retrieveNewsImages(){
        StorageReference ref = storage.getReference()
                .child("newsImg")
                .child("n" + numberNews + ".jpg");
        ref.getBytes(10024*10024)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        if(bitmap == null){
                            numberNews = 1;
                        }else{
                            newsImages.add(bitmap);
                            numberNews++;
                            retrieveNewsImages();
                        }
                    }
                });
    }

    public void retrieveImage(){
        imagesForHome = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("HomeImages");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    imagesForHome.add(snapshot.getValue(ImageId.class));
                }
                imagesForHomeM.postValue(imagesForHome);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<List<ImageId>> getImagesForHomeM() {
        retrieveNewsImages();
        imagesForHomeM.setValue(imagesForHome);
        return imagesForHomeM;
    }

    public void retrieveGalleryImages(){
        StorageReference ref = storage.getReference()
                .child("gallery")
                .child("i" + numberGallery + ".jpg");
        ref.getBytes(10024*10024)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        if(bitmap == null){
                            numberGallery = 1;
                        }else{
                            galleryImages.add(bitmap);
                            numberGallery++;
                            retrieveGalleryImages();
                        }
                    }
                });
    }
}
