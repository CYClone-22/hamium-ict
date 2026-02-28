package com.example.mentos.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private ArrayList<PopularData> pData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView rank, hobby, changeRanking;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.rank = itemView.findViewById(R.id.rank);
            this.hobby = itemView.findViewById(R.id.hobby);
            this.changeRanking = itemView.findViewById(R.id.changeRanking);
        }
    }

    public PopularAdapter(ArrayList<PopularData> list) {
        pData = list;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        PopularData data = pData.get(position);

        holder.rank.setText(data.getRank());
        holder.hobby.setText(data.getHobby());
        holder.changeRanking.setText(data.getChangeRanking());
    }

    @Override
    public int getItemCount() {
        return (null != pData ? pData.size() : 0);
    }

    public void updateRanking(ArrayList<PopularData> popularList){
        this.pData = popularList;
        notifyDataSetChanged();
    }
}
