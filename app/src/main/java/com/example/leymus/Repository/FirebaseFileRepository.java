package com.example.leymus.Repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.leymus.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class FirebaseFileRepository {
    private static FirebaseFileRepository instance;
    private FirebaseStorage storage;
    private StorageReference textFileStorage;
    private MutableLiveData<List<File>> mainPageTextFiles;

    private FirebaseFileRepository(Application app){
        mainPageTextFiles = new MutableLiveData<>();
        storage = FirebaseStorage.getInstance();
        textFileStorage = storage.getReference();
        init();
    }

    public static synchronized FirebaseFileRepository GetInstance(Application app){
        if(instance == null){
            instance = new FirebaseFileRepository(app);
        }
        return instance;
    }

    private void init(){
        mainPageTextFiles.setValue(retrieveMainPageText());
        startCounter();
    }

    private List<File> retrieveMainPageText() {
        List<File> mainPageFiles = new ArrayList<>();
        System.out.println("Retrieving files");
        try {
            File localFile = File.createTempFile("SeasonHeader","txt");
            StorageReference mainPageText = textFileStorage.child("MainPage Text/SeasonHeader.txt");
            mainPageText.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    System.out.println("Retrieved file + " + localFile.getName());
                }
            });
            mainPageFiles.add(localFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return mainPageFiles;
    }

    private void startCounter() {
        Thread counter = new Thread();
    }

    public LiveData<List<File>> getMainPageTextFiles(){
        return mainPageTextFiles;
    }
}
