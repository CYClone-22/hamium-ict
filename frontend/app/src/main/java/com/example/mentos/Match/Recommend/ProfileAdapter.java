package com.example.mentos.Match.Recommend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{

    private ArrayList<profileData> mData = null;

    //뷰홀더
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_base,profile_userImage,profile_favorite,  profile_connectButton;
        ImageButton profile_cancelButton;
        TextView profile_level, profile_name, profile_age, profile_slash, profile_gender;

        public ViewHolder(View itemView) {
            super(itemView);

            profile_base = itemView.findViewById(R.id.profile_base);
            profile_userImage = itemView.findViewById(R.id.profile_userImage);
            profile_cancelButton= itemView.findViewById(R.id.profile_cancelButton);
            profile_connectButton = itemView.findViewById(R.id.profile_connectButton);
            profile_level = itemView.findViewById(R.id.profile_level);
            profile_name = itemView.findViewById(R.id.profile_name);
            profile_age = itemView.findViewById(R.id.profile_age);
            profile_slash = itemView.findViewById(R.id.profile_slash);
            profile_gender = itemView.findViewById(R.id.profile_gender);
            profile_favorite = itemView.findViewById(R.id.profile_favorite);
        }
    }

    //생성자에서 데이터 객체 전달받음
    ProfileAdapter(ArrayList<profileData> list){
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_recommend, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        profileData data = mData.get(position);

        holder.profile_userImage.setImageResource(data.getUserImage());
        holder.profile_name.setText(data.getName());
        holder.profile_age.setText(data.getAge());
        holder.profile_level.setText(data.getLevel());
        holder.profile_gender.setText(data.getGender());

        holder.profile_cancelButton.setOnClickListener(v -> {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        });

//        holder.profile_connectButton.setOnClickListener(v -> {
//            openChatActivity(holder.itemView.getContext());
//        });

        //하트 상태 설정
        if(data.isFavorite()){
            holder.profile_favorite.setImageResource(R.drawable.heart);
        }
        else {
            holder.profile_favorite.setImageResource(R.drawable.empty_heart);
        }

        //하트 클릭 리스너
        holder.profile_favorite.setOnClickListener(v -> {
            data.setFavorite(!data.isFavorite());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return (null != mData ? mData.size() : 0);
    }
}
