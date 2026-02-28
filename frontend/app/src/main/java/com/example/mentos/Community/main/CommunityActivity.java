package com.example.mentos.Community.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mentos.Community.comment.CommentAdapter;
import com.example.mentos.Community.comment.CommentData;
import com.example.mentos.R;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {
    private ArrayList<CommunityData> mainList;
    private ArrayList<CommentData> commentlist;
    private CommunityAdapter communityAdapter;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_main);

        //게시물 리사이클러뷰
        recyclerView = (RecyclerView) findViewById(R.id.rv_thread);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainList = new ArrayList<>();

        //예제 데이터(테스트용)
        mainList.add(new CommunityData("제목", "내용","이름", "08/01 23:59", "0", "0", R.drawable.logo_person));

        communityAdapter = new CommunityAdapter(mainList);
        recyclerView.setAdapter(communityAdapter);

        //댓글 리사이클러뷰
        recyclerView = (RecyclerView) findViewById(R.id.rv_comment);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        commentlist = new ArrayList<>();

        //예제 데이터(테스트용)
        commentlist.add(new CommentData("내용", R.drawable.logo_person, "이름", "08/01 23:59", "0"));
        commentlist.add(new CommentData("내용", R.drawable.logo_person, "이름", "08/01 23:59", "0"));
        commentlist.add(new CommentData("내용", R.drawable.logo_person, "이름", "08/01 23:59", "0"));

        commentAdapter = new CommentAdapter(commentlist);
        recyclerView.setAdapter(commentAdapter);

        //댓글작성
        Button btn = findViewById(R.id.commentBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
