package com.example.mentos.Match;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.mentos.Match.Recommend.RecommendData;
import com.example.mentos.Match.chat.ChatActivity;
import com.example.mentos.R;

import java.util.ArrayList;

public class CombineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_BASIC = 0;
    private static final int VIEW_TYPE_RECOMMEND = 1;

    private ArrayList<profileData> basicList;
    private ArrayList<RecommendData> recommendList;
    private Button emptyStateButtonRecommend, emptyStateButtonBasic;
    private boolean showBasicUsers = false; // 기본적으로 일반 유저를 표시하지 않음

    public CombineAdapter(ArrayList<profileData> basicList, ArrayList<RecommendData> recommendList, Button emptyStateButtonRecommend, Button emptyStateButtonBasic) {
        this.basicList = basicList;
        this.recommendList = recommendList;
        this.emptyStateButtonRecommend = emptyStateButtonRecommend;
        this.emptyStateButtonBasic = emptyStateButtonBasic;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < recommendList.size()) {
            return VIEW_TYPE_RECOMMEND;
        } else {
            return VIEW_TYPE_BASIC;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_RECOMMEND) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_profile_recommend, parent, false);
            return new RecommendViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_profile_basic, parent, false);
            return new BasicViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_RECOMMEND) {
            RecommendData data = recommendList.get(position);
            ((RecommendViewHolder) holder).bind(data);
        } else {
            profileData data = basicList.get(position - recommendList.size());
            ((BasicViewHolder) holder).bind(data);
        }
    }

    @Override
    public int getItemCount() {
        if (showBasicUsers) {
            return recommendList.size() + basicList.size();
        } else {
            return recommendList.size();
        }
    }

    public class BasicViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_userImage, profile_favorite, profile_blockButton, profile_connectButton;
        TextView profile_level, profile_name, profile_age, profile_slash, profile_gender;

        public BasicViewHolder(View itemView) {
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
                profile_favorite.setImageResource(R.drawable.heart);
            } else {
                profile_favorite.setImageResource(R.drawable.empty_heart);
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
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            });
        }
    }

    public class RecommendViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_userImage, profile_favorite, profile_blockButton, profile_connectButton;
        TextView profile_level, profile_name, profile_age, profile_slash, profile_gender;

        public RecommendViewHolder(View itemView) {
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

        public void bind(RecommendData data) {
            profile_userImage.setImageResource(data.getUserImage());
            profile_name.setText(data.getName());
            profile_age.setText(data.getAge());
            profile_level.setText(data.getLevel());
            profile_gender.setText(data.getGender());

            if (data.isFavorite()) {
                profile_favorite.setImageResource(R.drawable.heart);
            } else {
                profile_favorite.setImageResource(R.drawable.empty_heart);
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
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            });
        }
    }

    private void openChatFragment(Fragment fragment, View itemView, String userName, int profileImage) {
        // itemView에서 Context를 가져옴
        Context context = itemView.getContext();

        // Context가 FragmentActivity의 인스턴스인지 확인
        if (context instanceof FragmentActivity) {
            // 데이터를 담을 Bundle 생성
            Bundle args = new Bundle();
            args.putString("userName", userName); // String 데이터 전달
            args.putInt("profileImage", profileImage); // int 데이터 전달

            // Fragment에 Bundle을 설정
            fragment.setArguments(args);

            // FragmentManager를 통해 FragmentTransaction 시작
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // Fragment를 fragment_container에 교체하고 백스택에 추가
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null); // 뒤로 가기 시 이전 Fragment로 돌아갈 수 있도록 함
            transaction.commit();
        } else {
            // 예외 처리: Context가 FragmentActivity가 아닌 경우
            throw new IllegalStateException("Context is not an instance of FragmentActivity");
        }
    }


    private void showAlertDialog(Context context, int position) {
        int recommendListSize = recommendList.size();

        new AlertDialog.Builder(context)
                .setTitle("차단 확인")
                .setMessage("정말로 차단하시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> {
                    if (position < recommendListSize) {
                        // 추천 리스트에서 항목 제거
                        recommendList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        // 일반 리스트에서 항목 제거
                        int adjustedPosition = position - recommendListSize;
                        basicList.remove(adjustedPosition);
                        notifyItemRemoved(adjustedPosition + recommendListSize);
                    }

                    // 추천 유저가 모두 제거되었을 때
                    if (recommendList.isEmpty() && !showBasicUsers) {
                        emptyStateButtonRecommend.setVisibility(View.VISIBLE);
                        emptyStateButtonRecommend.setOnClickListener(v -> {
                            // "일반 유저 추천 받기" 버튼 클릭 시
                            showBasicUsersAlert(v.getContext());
                        });
                    }

                    // 일반 유저가 모두 제거되었을 때
                    if (basicList.isEmpty() && showBasicUsers) {
                        emptyStateButtonBasic.setVisibility(View.VISIBLE);
                        emptyStateButtonBasic.setOnClickListener(v -> {
                            showEmptyAlert(v.getContext());
                        });
                    }
                })
                .setNegativeButton("아니오", null)
                .show();
    }

    private void showBasicUsersAlert(Context context) {
        new AlertDialog.Builder(context)
                .setMessage("더 이상 추천할 유저가 없습니다. 일반 유저를 추천받으시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> {
                    showBasicUsers = true; // 일반 유저를 표시하도록 설정
                    emptyStateButtonRecommend.setVisibility(View.GONE); // 버튼 숨김
                    notifyDataSetChanged(); // RecyclerView 갱신
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


    // 추천 데이터가 비었는지 확인
    private boolean recommendListEmpty() {
        return recommendList.isEmpty();
    }

    // 일반 데이터가 비었는지 확인
    private boolean basicListEmpty() {
        return basicList.isEmpty();
    }
}
