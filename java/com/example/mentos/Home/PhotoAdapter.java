package com.example.mentos.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Integer> photoList; // 사진 리소스 ID 목록을 담을 리스트

    public PhotoAdapter(List<Integer> photoList) {
        this.photoList = photoList;
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        int photoResId = photoList.get(position);
        holder.photoImageView.setImageResource(photoResId);
    }
    // 뷰 홀더와 데이터 연결 작업
    // 예: holder.imageView.setImageResource(imageList.get(position));

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;
        // 뷰 홀더 내부 구현
        // 예: ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }
        // 뷰 홀더 내부 초기화
        // 예: imageView = itemView.findViewById(R.id.imageView);



    }
}


