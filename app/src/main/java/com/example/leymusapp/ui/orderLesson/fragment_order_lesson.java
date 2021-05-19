package com.example.leymusapp.ui.orderLesson;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leymusapp.FrontActivity;
import com.example.leymusapp.R;

public class fragment_order_lesson extends Fragment {

    private FragmentOrderLessonViewModel mViewModel;
    private MutableLiveData<String> mLessonName;
    private MutableLiveData<String> mLessonType;
    EditText lessonName;
    EditText lessonType;
    Button addLesson;

    public static fragment_order_lesson newInstance() {
        return new fragment_order_lesson();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_lesson_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentOrderLessonViewModel.class);
        mLessonName = mViewModel.getmLessonText();
        mLessonType = mViewModel.getmLessonType();
        lessonName = (EditText) getView().findViewById(R.id.lessonNameEditText);
        lessonType = (EditText) getView().findViewById(R.id.lessonTypeEditText);
        addLesson = (Button) getView().findViewById(R.id.addLesson);
        addLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLesson();
            }
        });
    }

    public void addLesson(){
        if(lessonType.getText().toString().isEmpty() || lessonName.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Name or type is empty", Toast.LENGTH_SHORT).show();
        }else{
            mViewModel.createNewLesson(lessonType.getText().toString(),lessonName.getText().toString());
            Toast.makeText(getActivity(), "Lesson " + lessonName.getText() + " created", Toast.LENGTH_SHORT).show();
            goOrderedLessons();
            lessonName.setText(mLessonName.getValue());
            lessonType.setText(mLessonType.getValue());
        }
    }

    private void goOrderedLessons() {
        startActivity(new Intent(getContext(), FrontActivity.class));
    }
}