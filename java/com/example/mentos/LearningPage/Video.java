package com.example.mentos.LearningPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.R;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningpage_video);

        Switch swWiFi = findViewById(R.id.learningpage_switch);

        // 스위치 상태를 체크하여 맞는 액티비티로 전환
        swWiFi.setChecked(false);
        swWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Video.this, Practice.class);
                    startActivity(intent);
                }
            }
        });
    }
}
