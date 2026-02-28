package com.example.mentos.Community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.R;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    private ArrayList<PostData> arrayList;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button postingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_main);

        //메인 액티비티
        recyclerView = (RecyclerView)findViewById(R.id.post_recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        
        arrayList.add(new PostData("ㅈㄱㄴ \n안 부풀어요", "이거 왜 안 부풀까요?", "5분전"));
        arrayList.add(new PostData("첼로가 없음요", "첼로 빌려주실 분?", "7분전"));
        arrayList.add(new PostData("흑흑 \nAI는 내 맘도 몰라주고...", "AI가 날 바보로 알아", "23분전"));


        arrayList.add(new PostData("존재할까...?", "AI가 자기보다 똑똑한 사람이 좋대", "38분전"));
        arrayList.add(new PostData("abcdefgh", "abc", "54분전"));
        arrayList.add(new PostData("뇽뇽", "뇽", "1시간전"));
        arrayList.add(new PostData("진짜덥다", "덥다", "1시간전"));
        arrayList.add(new PostData("덥다", "너무", "3시간전"));
        arrayList.add(new PostData("먕먕", "먕", "5시간전"));
        arrayList.add(new PostData("먕먕", "먕", "6시간전"));
        arrayList.add(new PostData("먕먕", "먕", "1일전"));
        arrayList.add(new PostData("먕먕", "먕", "1일전"));

        postAdapter = new PostAdapter(arrayList);
        recyclerView.setAdapter(postAdapter);

        //글쓰기
        postingButton = findViewById(R.id.postingButton);
        postingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, WritingBoard.class);
                startActivity(intent);
            }
        });
    }
}
