package com.example.mentos.Community;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.Community.main.CommunityActivity;
import com.example.mentos.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<PostData> postD = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView post_title, post_content, post_time;
        LinearLayout background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            post_title = itemView.findViewById(R.id.postTitle);
            post_content = itemView.findViewById(R.id.postContent);
            post_time = itemView.findViewById(R.id.postTime);
            background = itemView.findViewById(R.id.background);
        }
    }

    PostAdapter(ArrayList<PostData> list) {postD = list;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostData data = postD.get(position);

        holder.post_title.setText(data.getTitle());
        holder.post_content.setText(data.getContent());
        holder.post_time.setText(data.getTime());

        //게시물 들어가기
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommunityActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != postD ? postD.size() : 0);
    }

}
