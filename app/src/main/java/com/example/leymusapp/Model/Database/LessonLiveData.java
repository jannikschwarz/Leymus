package com.example.leymusapp.Model.Database;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.leymusapp.Model.Lesson;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LessonLiveData extends LiveData<Lesson> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Lesson lesson = snapshot.getValue(Lesson.class);
            setValue(lesson);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public LessonLiveData(DatabaseReference ref){
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
