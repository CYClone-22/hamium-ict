package com.example.mentos.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.Login.LoginActivity;
import com.example.mentos.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // 서서히 나타나는 애니메이션 설정
        View contentView = findViewById(android.R.id.content);
        contentView.setAlpha(0f);
        contentView.animate().alpha(1f).setDuration(2500).setListener(null);

        // 3초 후에 LoginActivity로 전환
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // 3000ms = 3초
    }
}
