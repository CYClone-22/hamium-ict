package com.example.mentos.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<ReviewData> rData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView name, grade, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.review_name);
            this.grade = itemView.findViewById(R.id.review_grade);
            this.content = itemView.findViewById(R.id.review_content);
        }
    }

    public ReviewAdapter(ArrayList<ReviewData> list){
        rData = list;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_popular, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        ReviewData data = rData.get(position);

        holder.name.setText(data.getName());
        holder.grade.setText(data.getGrade());
        holder.content.setText(data.getContent());
    }

    @Override
    public int getItemCount() {
        return (null != rData ? rData.size() : 0);
    }
}
