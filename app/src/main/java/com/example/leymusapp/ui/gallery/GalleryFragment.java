package com.example.leymusapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leymusapp.Model.ImageId;
import com.example.leymusapp.R;

import java.util.List;

public class GalleryFragment extends Fragment{

    private GalleryViewModel galleryViewModel;
    private MutableLiveData<List<ImageId>> galleryImagesM;
    RecyclerView mGalleryList;
    GalleryAdapter mGalleryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryViewModel.init();
        galleryImagesM = galleryViewModel.getGalleryImages();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGalleryList = (RecyclerView) getView().findViewById(R.id.galleryRV);
        mGalleryList.hasFixedSize();
        mGalleryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mGalleryAdapter = new GalleryAdapter(galleryImagesM.getValue());
        mGalleryList.setAdapter(mGalleryAdapter);
    }
}