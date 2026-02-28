package com.example.mentos.Match.Survey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.Home.HomeFragment;
import com.example.mentos.R;

public class SurveyGuideActivity extends AppCompatActivity {

    private Button backButton, confirmButton;
    private TextView surveyGuideTextView;
    private LinearLayout surveyGuideButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_surveyguide);

        surveyGuideTextView = findViewById(R.id.SurveyGuideTextView);
        surveyGuideButtons = findViewById(R.id.SurveyGuideButtons);
        backButton = findViewById(R.id.backButton);
        confirmButton = findViewById(R.id.confirmButton);

        // 메시지 텍스트뷰에 페이드 인 애니메이션 적용
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        surveyGuideTextView.startAnimation(fadeInAnimation);

        // 2초 뒤에 버튼 레이아웃에 슬라이드 애니메이션 적용
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation slideUpAnimation = AnimationUtils.loadAnimation(SurveyGuideActivity.this, R.anim.slide_up);
                surveyGuideButtons.setVisibility(View.VISIBLE);
                surveyGuideButtons.startAnimation(slideUpAnimation);
            }
        }, 500); // 2초 지연

        // 확인 버튼 클릭 시 설문조사 화면으로 이동
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 설문조사 Activity로 이동
                Intent intent = new Intent(SurveyGuideActivity.this, SurveyGuideActivity2.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼 클릭 시 HomeActivity로 이동
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurveyGuideActivity.this, HomeFragment.class);
                startActivity(intent);
            }
        });


    }
}
