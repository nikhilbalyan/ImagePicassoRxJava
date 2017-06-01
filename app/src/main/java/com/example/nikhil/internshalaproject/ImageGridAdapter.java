package com.example.nikhil.internshalaproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ImageViewHolder> {

    private Context context;

    private List<String> imageList;


    // Constructor
    public ImageGridAdapter(Context context) {

        this.imageList = new ArrayList<>();
        this.context=context;

    }


    @Override
    public ImageGridAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.simple_image, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageGridAdapter.ImageViewHolder holder, int position) {

        Picasso picasso=Picasso.with(context);
        picasso.load(imageList.get(position))
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void add(String str) {
        imageList.add(str);
    }

    public void addAll(List<String> imageList) {
        for (String str : imageList) {
            add(str);
        }
        notifyDataSetChanged();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView imageView;

        public ImageViewHolder(View view) {
            super(view);
            imageView=(ImageView)view.findViewById(R.id.imageView);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            SharedActivity.showFullscreenImage(context, imageList.get(getAdapterPosition()));
        }
    }
}
