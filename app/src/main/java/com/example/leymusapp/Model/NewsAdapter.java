package com.example.leymusapp.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leymusapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private ArrayList<News> mNews;
    private OnListItemClickListener mOnItemClickListener;
    public ImageView icon;
    public List<ImageId> imagesToCycle;

    NewsAdapter(ArrayList<News> news, OnListItemClickListener listener){
        mNews = news;
        mOnItemClickListener = listener;
    }

    public void setImagesToCycle(List<ImageId> imagesToCycle) {
        this.imagesToCycle = imagesToCycle;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_list_item,parent,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position){
        viewHolder.name.setText(mNews.get(position).getName());
        for(int i = 0; i < imagesToCycle.size(); i++){
            viewHolder.setImage(imagesToCycle.get(i).getRef());
        }
    }

    public int getItemCount(){
        return mNews.size();
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
