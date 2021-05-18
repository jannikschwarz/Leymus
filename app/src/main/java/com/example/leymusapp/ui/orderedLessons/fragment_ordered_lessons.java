package com.example.leymusapp.ui.orderedLessons;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leymusapp.Model.Lesson;
import com.example.leymusapp.R;

import java.util.List;

public class fragment_ordered_lessons extends Fragment {

    private FragmentOrderedLessonsViewModel mViewModel;
    TextView orderedTextView;

    public static fragment_ordered_lessons newInstance() {
        return new fragment_ordered_lessons();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ordered_lessons_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentOrderedLessonsViewModel.class);
        orderedTextView = (TextView) getView().findViewById(R.id.ordered_lessons_textView);
        mViewModel.getAllLessons().observe(getViewLifecycleOwner(),lessons -> {
            if(!lessons.isEmpty()){
                orderedTextView.setText("");
                for(Lesson n : lessons){
                    orderedTextView.append(n.getName() + n.getType() + "\n");
                }
            }else{
                orderedTextView.setText("Empty");
            }
        });
    }
}