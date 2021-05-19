package com.example.leymusapp.ui.news;

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
import com.example.leymusapp.Model.News;
import com.example.leymusapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements NewsAdapter.OnListItemClickListener {

    private NewsViewModel newsViewModel;
    private MutableLiveData<List<ImageId>> newsImagesM;
    private ArrayList<News> recycleNews;
    RecyclerView mNewsList;
    NewsAdapter newsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.init();
        newsImagesM = newsViewModel.getNewsImages();
        recycleNews = new ArrayList<>();
        for(int i = 0; i < newsImagesM.getValue().size(); i++){
            recycleNews.add(new News("Some Text",newsImagesM.getValue().get(i).getRef()));
        }
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsList = (RecyclerView) getView().findViewById(R.id.rv);
        mNewsList.hasFixedSize();
        mNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(recycleNews,this);
        mNewsList.setAdapter(newsAdapter);
    }

    @Override
    public void onListItemClick(int clickItemIndex) {
        int newsNumber = clickItemIndex + 1;
    }
}