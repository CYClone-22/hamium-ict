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

import com.example.mentos.R;

public class SurveyGuideActivity2 extends AppCompatActivity {

    private Button mentoButton, menteeButton;
    private TextView surveyGuideTextView2;
    private LinearLayout surveyGuideButtons2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_surveyguide2);

        surveyGuideTextView2 = findViewById(R.id.SurveyGuideTextView2);
        surveyGuideButtons2 = findViewById(R.id.SurveyGuideButtons2);
        mentoButton = findViewById(R.id.mentoButton);
        menteeButton = findViewById(R.id.menteeButton);

        // 메시지 텍스트뷰에 페이드 인 애니메이션 적용
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        surveyGuideTextView2.startAnimation(fadeInAnimation);

        // 2초 뒤에 버튼 레이아웃에 슬라이드 애니메이션 적용
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation slideUpAnimation = AnimationUtils.loadAnimation(SurveyGuideActivity2.this, R.anim.slide_up);
                surveyGuideButtons2.setVisibility(View.VISIBLE);
                surveyGuideButtons2.startAnimation(slideUpAnimation);
            }
        }, 300); // 2초 지연

        // 멘티 버튼 클릭 시 멘티-설문조사 화면으로 이동
        menteeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 설문조사 Activity로 이동
                Intent intent = new Intent(SurveyGuideActivity2.this, Survey_Mentee_Activity.class);
                startActivity(intent);
            }
        });

        // 멘토 버튼 클릭 시 멘토-설문조사 화면으로 이동
        mentoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurveyGuideActivity2.this, Survey_Mentor_Activity.class);
                startActivity(intent);
            }
        });


    }
}
