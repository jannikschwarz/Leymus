package com.example.leymus.Repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.leymus.Models.Kursus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseObjectRepository {

    private static FirebaseObjectRepository instance;
    private FirebaseDatabase database;
    private DatabaseReference kursusDatabaseRef;
    private Kursus temp;

    private FirebaseObjectRepository(Application application){
        init();
    }

    public Kursus getTemp() {
        return temp;
    }

    public static synchronized FirebaseObjectRepository getInstance(Application application){
        if(instance == null){
            instance = new FirebaseObjectRepository(application);
        }
        return instance;
    }

    public void init(){
        database = FirebaseDatabase.getInstance();
        kursusDatabaseRef = database.getReference();
        kursusDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void showData(DataSnapshot snapshot) {
        for(DataSnapshot ds : snapshot.getChildren()){
            temp.setID(ds.child("Kurser").getValue(Kursus.class).getID());
            temp.setAntal(ds.child("Kurser").getValue(Kursus.class).getAntal());
            temp.setDato(ds.child("Kurser").getValue(Kursus.class).getDato());
            temp.setEgnetForBørn(ds.child("Kurser").getValue(Kursus.class).getEgnetForBørn());
            temp.setLevel(ds.child("Kurser").getValue(Kursus.class).getLevel());
            temp.setPris(ds.child("Kurser").getValue(Kursus.class).getPris());
            temp.setTema(ds.child("Kurser").getValue(Kursus.class).getTema());
            temp.setTid(ds.child("Kurser").getValue(Kursus.class).getTid());
        }
    }
}
