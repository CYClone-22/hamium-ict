package com.example.mentos.LearningPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.Model.Lecture;
import com.example.mentos.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.LectureViewHolder> {

    private Context context;
    private List<Lecture> lectureList;

    public LectureAdapter(Context context, List<Lecture> lectureList) {
        this.context = context;
        this.lectureList = lectureList;
    }

    @NonNull
    @Override
    public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lecture, parent, false);
        return new LectureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureViewHolder holder, int position) {
        Lecture lecture = lectureList.get(position);
        holder.lectureName.setText(lecture.getName());
        holder.hobbyType.setText(lecture.getHobbyType());
        holder.difficultyLevel.setText(lecture.getDifficultyLevel());

        // WebView를 설정하여 YouTube 동영상 재생
        if (lecture.getVideoUrl() != null && !lecture.getVideoUrl().isEmpty()) {
            holder.lecture_video.setVisibility(View.VISIBLE);
            String videoUrl = lecture.getVideoUrl();
            String embeddedVideoUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                    + extractYouTubeVideoId(videoUrl) + "\" frameborder=\"0\" allowfullscreen></iframe>";

            holder.lecture_video.getSettings().setJavaScriptEnabled(true);
            holder.lecture_video.setWebViewClient(new WebViewClient());

            // WebChromeClient 설정
            holder.lecture_video.setWebChromeClient(new WebChromeClient() {
                private View customView;
                private WebChromeClient.CustomViewCallback customViewCallback;
                private int originalSystemUiVisibility;

                @Override
                public void onShowCustomView(View view, CustomViewCallback callback) {
                    // 전체 화면 전환 시 호출
                    if (customView != null) {
                        callback.onCustomViewHidden();
                        return;
                    }

                    // 원래의 UI 설정 저장
                    customView = view;
                    originalSystemUiVisibility = holder.itemView.getSystemUiVisibility();

                    // FrameLayout에 전체 화면 뷰 추가
                    FrameLayout parent = (FrameLayout) holder.itemView.getRootView();
                    parent.addView(customView, new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    ));

                    // 전체 화면 콜백 및 시스템 UI 숨기기
                    holder.itemView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    customViewCallback = callback;
                }

                @Override
                public void onHideCustomView() {
                    // 전체 화면 종료 시 호출
                    if (customView == null) {
                        return;
                    }

                    // 전체 화면 뷰 제거 및 원래 UI 복원
                    FrameLayout parent = (FrameLayout) holder.itemView.getRootView();
                    parent.removeView(customView);
                    customView = null;
                    holder.itemView.setSystemUiVisibility(originalSystemUiVisibility);
                    customViewCallback.onCustomViewHidden();
                }
            });

            holder.lecture_video.loadData(embeddedVideoUrl, "text/html", "utf-8");
        } else {
            holder.lecture_video.setVisibility(View.GONE); // 비디오가 없는 경우 WebView 숨기기
        }
    }

    private String extractYouTubeVideoId(String videoUrl) {
        String videoId = "";
        String[] parts = videoUrl.split("v=");
        if (parts.length > 1) {
            videoId = parts[1].split("&")[0];
        } else if (videoUrl.contains("youtu.be/")) {
            videoId = videoUrl.substring(videoUrl.lastIndexOf("/") + 1);
        }
        return videoId;
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }

    public static class LectureViewHolder extends RecyclerView.ViewHolder {
        TextView lectureName;
        TextView hobbyType;
        TextView difficultyLevel;
        WebView lecture_video;

        public LectureViewHolder(@NonNull View itemView) {
            super(itemView);
            lecture_video = itemView.findViewById(R.id.lecture_video);
            lectureName = itemView.findViewById(R.id.lecture_name);
            hobbyType = itemView.findViewById(R.id.hobby_type);
            difficultyLevel = itemView.findViewById(R.id.difficulty_level);
        }
    }
}
