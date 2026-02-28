package com.example.mentos.Home;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentos.AI.AIMainChatActivity;
import com.example.mentos.AI.AIChatListFragment;
import com.example.mentos.Chat.ChatListFragment;
import com.example.mentos.Match.MatchFragment;
import com.example.mentos.R;
import com.example.mentos.LearningPage.LearningPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Nav_main extends AppCompatActivity {

    private FloatingActionButton fab;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_bottom_nav);

        fab = findViewById(R.id.main_floating_btn);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        fragmentManager = getSupportFragmentManager();

        // 기본 프래그먼트 설정
        if (savedInstanceState == null) {
            showFragment(new HomeFragment());
        }

        // 바텀 네비게이션 초기화
        initBottomNav();

        // FAB 클릭 리스너 설정
        fab.setOnClickListener(v -> {
            // FAB 클릭 시 nav_aitutor 항목을 클릭한 것처럼 동작
            bottomNavigationView.setSelectedItemId(R.id.nav_aitutor);
            Intent intent = new Intent(Nav_main.this, AIMainChatActivity.class);
            startActivity(intent);

            // 화면 전환 애니메이션 설정 (아래에서 위로 올라오는 애니메이션)
            overridePendingTransition(R.anim.slide_in_up, R.anim.stay);

        });
    }

    private void initBottomNav() {
        // 바텀네비게이션 음영 삭제
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                showFragment(new HomeFragment());

            } else if (itemId == R.id.nav_learning_page) {
                showFragment(new LearningPageFragment());

            } else if (itemId == R.id.nav_aitutor) {
                showFragment(new AIChatListFragment());
                Intent intent = new Intent(Nav_main.this, AIMainChatActivity.class);
                startActivity(intent);

            } else if (itemId == R.id.nav_matching) {
                showFragment(new MatchFragment());

            } else if (itemId == R.id.nav_chat) {
                showFragment(new ChatListFragment());
            }
            return true;
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            // 바텀네비 재클릭시 화면 재생성 방지
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Add fragment to back stack
        transaction.commit();
    }
}
