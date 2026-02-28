package com.example.mentos.Match.Recommend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.Match.Popular.PopularActivity;
import com.example.mentos.R;

import java.util.ArrayList;

public class MatchRecommendActivity extends AppCompatActivity {
    private ArrayList<profileData> arrayList;
    private ProfileAdapter profileAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_main);

        //리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView = (RecyclerView)findViewById(R.id.rv_profile);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        //예제 데이터(테스트용)
        arrayList.add(new profileData("멘토스", "여", "23", "중상급이상", R.drawable.logo_person));
        arrayList.add(new profileData("사용자", "남", "25", "상급이상", R.drawable.logo_person));
        arrayList.add(new profileData("사용자2", "남", "30", "중하급이상", R.drawable.logo_person));

        //리사이클러뷰에 profileAdapter 지정
        profileAdapter = new ProfileAdapter(arrayList);
        recyclerView.setAdapter(profileAdapter);

        //popup
        ImageButton popular;

        ImageButton.OnClickListener btnListener =new View.OnClickListener(){
            public void onClick (View v) {

                if(v.getId() == R.id.popular_hobby){
                    Intent intent = new Intent(MatchRecommendActivity.this, PopularActivity.class);
                    startActivityForResult(intent, 1);
                }
            }
        };

        popular = (ImageButton) findViewById(R.id.popular_hobby);
        popular.setOnClickListener(btnListener);
    }
}
