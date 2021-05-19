package com.example.leymusapp.ui.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leymusapp.Model.News;
import com.example.leymusapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private OnListItemClickListener mOnItemClickListener;
    public ArrayList<News> recycleNews;

    public NewsAdapter(ArrayList<News> recycleNews, OnListItemClickListener listener){
        this.recycleNews = recycleNews;
        mOnItemClickListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_list_item,parent,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        for(int i = 0; i < recycleNews.size(); i++){
            viewHolder.setImage(recycleNews.get(i).getImage());
        }
    }

    public int getItemCount(){
        return recycleNews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView icon;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(this::onClick);
        }

        public void setImage(String images){
            Picasso.get().load(images).into(icon);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener{
        void onListItemClick(int clickItemIndex);
    }
}
