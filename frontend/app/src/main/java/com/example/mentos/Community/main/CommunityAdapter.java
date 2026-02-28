package com.example.mentos.Community.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.R;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private ArrayList<CommunityData> communityData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView threadTitle,threadContent,threadUsername,threadTime,threadGoodCount,threadReplyCount;
        ImageView threadProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            threadTitle = itemView.findViewById(R.id.threadTitle);
            threadContent = itemView.findViewById(R.id.threadContent);
            threadUsername = itemView.findViewById(R.id.threadUsername);
            threadTime = itemView.findViewById(R.id.threadTime);
            threadGoodCount = itemView.findViewById(R.id.threadGoodCount);
            threadReplyCount = itemView.findViewById(R.id.threadReplyCount);
            threadProfile = itemView.findViewById(R.id.threadProfile);
        }
    }

    CommunityAdapter(ArrayList<CommunityData> list){
        communityData = list;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thread_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommunityData data = communityData.get(position);

        holder.threadTitle.setText(data.getThreadTitle());
        holder.threadContent.setText(data.getThreadContent());
        holder.threadUsername.setText(data.getThreadUsername());
        holder.threadTime.setText(data.getThreadTime());
        holder.threadGoodCount.setText(data.getThreadGoodCount());
        holder.threadReplyCount.setText(data.getThreadReplyCount());
        holder.threadProfile.setImageResource(data.getThreadProfile());
    }

    @Override
    public int getItemCount() {
        return (null != communityData ? communityData.size() : 0);
    }
}
