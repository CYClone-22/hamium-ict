package com.example.mentos.LearningPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.R;

public class Practice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningpage_practice);
        Switch swWiFi = findViewById(R.id.learningpage_switch);

        SharedPreferences sharedPreferences = getSharedPreferences("switch_prefs", MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("switch_state", true);

        swWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // isChecked가 true일 때 (스위치가 켜질 때)
                    Intent intent = new Intent(Practice.this, Video.class);
                    startActivity(intent);
                }
                // else 블록이 필요 없습니다.
            }
        });
    }
}
