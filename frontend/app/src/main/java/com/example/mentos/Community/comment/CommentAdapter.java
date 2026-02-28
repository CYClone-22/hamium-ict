package com.example.mentos.Community.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private ArrayList<CommentData> commentData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView commentContent, commentUsername, commentTime, commentGoodCount;
        ImageView commentProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentContent = itemView.findViewById(R.id.commentContent);
            commentUsername = itemView.findViewById(R.id.commentUsername);
            commentTime = itemView.findViewById(R.id.commentTime);
            commentGoodCount = itemView.findViewById(R.id.commentGoodCount);
            commentProfile = itemView.findViewById(R.id.commentProfile);
        }
    }

    public CommentAdapter(ArrayList<CommentData> list){commentData = list;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thread_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentData data = commentData.get(position);

        holder.commentContent.setText(data.getCommentContent());
        holder.commentUsername.setText(data.getCommentUsername());
        holder.commentTime.setText(data.getCommentTime());
        holder.commentGoodCount.setText(data.getCommentGoodCount());
        holder.commentProfile.setImageResource(data.getCommentProfile());

    }

    @Override
    public int getItemCount() {
        return (null != commentData ? commentData.size() : 0);
    }
}
