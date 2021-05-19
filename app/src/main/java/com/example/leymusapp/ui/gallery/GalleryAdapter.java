package com.example.leymusapp.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.leymusapp.Model.ImageId;
import com.example.leymusapp.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private List<ImageId> mImages;

    public GalleryAdapter(List<ImageId> images){
        mImages = images;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gallery_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        for(int i = 0; i < mImages.size(); i++){
            viewHolder.setImage(mImages.get(i).getRef());
        }
    }

    public int getItemCount() {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.gallery_iv_image);
        }

        public void setImage(String images){
            Picasso.get().load(images).into(image);
        }
    }
}
