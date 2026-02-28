package com.example.mentos.Match.Basic;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.Chat.ChatListFragment;
import com.example.mentos.Match.Basic.profileData;
import com.example.mentos.Match.MatchFragment;
import com.example.mentos.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private ArrayList<profileData> profileList;
    private Button emptyStateButton;
    private boolean showBasicUsers;
    private Fragment fragment;

    public ProfileAdapter(ArrayList<profileData> profileList, Button emptyStateButton, boolean showBasicUsers, Fragment fragment) {
        this.profileList = profileList;
        this.emptyStateButton = emptyStateButton;
        this.showBasicUsers = showBasicUsers;
        this.fragment = fragment;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_profile_basic, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        profileData data = profileList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_userImage, profile_favorite, profile_blockButton, profile_connectButton;
        TextView profile_level, profile_name, profile_age, profile_slash, profile_gender;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            profile_userImage = itemView.findViewById(R.id.profile_userImage);
            profile_blockButton = itemView.findViewById(R.id.profile_blockButton);
            profile_connectButton = itemView.findViewById(R.id.profile_connectButton);
            profile_level = itemView.findViewById(R.id.profile_level);
            profile_name = itemView.findViewById(R.id.profile_name);
            profile_age = itemView.findViewById(R.id.profile_age);
            profile_slash = itemView.findViewById(R.id.profile_slash);
            profile_gender = itemView.findViewById(R.id.profile_gender);
            profile_favorite = itemView.findViewById(R.id.profile_favorite);
        }

        public void bind(profileData data) {
            profile_userImage.setImageResource(data.getUserImage());
            profile_name.setText(data.getName());
            profile_age.setText(data.getAge());
            profile_level.setText(data.getLevel());
            profile_gender.setText(data.getGender());

            if (data.isFavorite()) {
                profile_favorite.setImageResource(R.drawable.lovep);
            } else {
                profile_favorite.setImageResource(R.drawable.loveg);
            }

            profile_favorite.setOnClickListener(v -> {
                data.setFavorite(!data.isFavorite());
                notifyItemChanged(getAdapterPosition());
            });

            profile_blockButton.setOnClickListener(v -> showAlertDialog(itemView.getContext(), getAdapterPosition()));

            profile_connectButton.setOnClickListener(v -> {
                new AlertDialog.Builder(itemView.getContext())
                        .setTitle("연결 확인")
                        .setMessage(data.getName() + "와 연결하시겠습니까?")
                        .setPositiveButton("예", (dialog, which) -> {
                            Context context = itemView.getContext();
                            ChatListFragment chatListFragment = new ChatListFragment();
                            openChatFragment(chatListFragment, itemView, data.getName(), data.getUserImage());

                            //라우팅하기
                            if (fragment instanceof MatchFragment) {
                                MatchFragment matchFragment = (MatchFragment) fragment;
                                // 연결할 멘토와 멘티의 ID를 지정합니다
                                int mentorId = 45; // 실제 ID로 변경
                                int menteeId = 23; // 실제 ID로 변경
                                matchFragment. processMatchRequest();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            });
        }
    }

    private void openChatFragment(Fragment fragment, View itemView, String userName, int profileImage) {
        Context context = itemView.getContext();
        if (context instanceof FragmentActivity) {
            Bundle args = new Bundle();
            args.putString("userName", userName);
            args.putInt("profileImage", profileImage);
            fragment.setArguments(args);

            FragmentActivity fragmentActivity = (FragmentActivity) context;
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            throw new IllegalStateException("Context is not an instance of FragmentActivity");
        }
    }

    private void showAlertDialog(Context context, int position) {
        new AlertDialog.Builder(context)
                .setTitle("차단 확인")
                .setMessage("정말로 차단하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> {
                    profileList.remove(position);
                    notifyItemRemoved(position);

                    if (profileList.isEmpty() && showBasicUsers) {
                        emptyStateButton.setVisibility(View.VISIBLE);
                        emptyStateButton.setOnClickListener(v -> {
                            showEmptyAlert(v.getContext());
                        });
                    }
                })
                .setNegativeButton("아니오", null)
                .show();
    }

    private void showEmptyAlert(Context context) {
        new AlertDialog.Builder(context)
                .setMessage("더 이상 유저가 존재하지 않습니다.")
                .setPositiveButton("확인", null)
                .show();
    }
}
