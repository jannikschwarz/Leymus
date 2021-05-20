package com.example.leymusapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.leymusapp.Model.ImageId;
import com.example.leymusapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private MutableLiveData<List<ImageId>> homeImagesM;
    ImageView homeImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.init();
        homeImagesM = homeViewModel.getImgToReturn();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        homeImage = (ImageView) getView().findViewById(R.id.cycling_image_view);
        MyThread myThread = new MyThread();
        myThread.run();*/
    }

    public void setImage(String image){
        Picasso.get().load(image).into(homeImage);
    }

    private class MyThread extends Thread{
        public void run(){
            int currentImage = 0;
            int listSize;
            while(true){
                if(homeImagesM.getValue() != null){
                    listSize = homeImagesM.getValue().size();
                    if(currentImage > listSize){
                        currentImage = 0;
                    }
                    if(listSize != 0){
                        System.out.println("Trying to add image");
                        setImage(homeImagesM.getValue().get(currentImage).getRef());
                        currentImage++;
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}