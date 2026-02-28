package com.example.mentos.Community.comment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private ArrayList<CommentData> arrayList;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_main);

        //리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView = (RecyclerView) findViewById(R.id.rv_comment);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        //예제 데이터(테스트용)
        arrayList.add(new CommentData("내용", R.drawable.logo_person, "이름", "08/01 23:59", "0"));
        arrayList.add(new CommentData("내용", R.drawable.logo_person, "이름", "08/01 23:59", "0"));
        arrayList.add(new CommentData("내용", R.drawable.logo_person, "이름", "08/01 23:59", "0"));

        commentAdapter = new CommentAdapter(arrayList);
        recyclerView.setAdapter(commentAdapter);
    }
}
