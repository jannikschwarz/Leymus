package com.example.leymusapp.ui.orderLesson;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.leymusapp.Model.Lesson;
import com.example.leymusapp.Repo.LessonRepository;
import com.example.leymusapp.Repo.UserRepository;

import java.util.List;

public class FragmentOrderLessonViewModel extends AndroidViewModel {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public MutableLiveData<String> mLessonText;
    public MutableLiveData<String> mLessonType;

    public FragmentOrderLessonViewModel(Application app){
        super(app);
        lessonRepository = LessonRepository.getInstance(app);
        userRepository = UserRepository.getInstance(app);
        mLessonText = new MutableLiveData<>();
        mLessonType = new MutableLiveData<>();
    }

    public void createNewLesson(String type, String name) {
        List<Lesson> allLessons = getAllLessons().getValue();
        Lesson toAdd;
        if(allLessons == null){
            toAdd = new Lesson(name,0,type,userRepository.getCurrentUser().getValue().getEmail());
            insertLesson(toAdd);
            mLessonType.setValue("");
            mLessonText.setValue("");
        }else{
            System.out.println("Lessons in total: " + allLessons.size());
            toAdd = new Lesson(name,allLessons.size(),type,userRepository.getCurrentUser().getValue().getEmail());
            boolean exists = false;
            for(int i = 0; i<allLessons.size(); i++){
                if(allLessons.get(i).equals(toAdd)){
                    updateLesson(toAdd);
                    exists = true;
                    break;
                }
            }
            if(exists){
                insertLesson(toAdd);
            }
        }
    }

    public MutableLiveData<String> getmLessonText() {
        return mLessonText;
    }

    public MutableLiveData<String> getmLessonType() {
        return mLessonType;
    }

    public LiveData<List<Lesson>> getAllLessons(){
        return lessonRepository.getAllLessons();
    }

    public void insertLesson(final Lesson lesson){
        lessonRepository.insert(lesson);
    }

    public void updateLesson(final Lesson lesson){
        lessonRepository.updateLesson(lesson);
    }

    public void deleteLesson(final Lesson lesson){
        lessonRepository.deleteLesson(lesson);
    }

    public void deleteAllLessons(){
        lessonRepository.deleteAllLessons();
    }
}