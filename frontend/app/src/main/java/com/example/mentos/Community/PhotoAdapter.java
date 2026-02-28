package com.example.mentos.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.R;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private ArrayList<PhotoData> photoData = null;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, deleteBtn;
        ImageView imageInsert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            imageInsert = itemView.findViewById(R.id.image_insert);
        }
    }

    PhotoAdapter(ArrayList<PhotoData> list) {
            photoData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoData data = photoData.get(position);

        holder.image.setImageResource(data.getImage());
    }

    @Override
    public int getItemCount() {
        return (null != photoData ? photoData.size() : 0);
    }
}
