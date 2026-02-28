package com.example.mentos.Match.Popular;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.Home.PopularAdapter;
import com.example.mentos.Home.PopularData;
import com.example.mentos.R;

import java.util.ArrayList;

public class PopularActivity extends AppCompatActivity {
    private ArrayList<PopularData> popularData;
    private PopularAdapter popularAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_hobbies);

        //리사이클러뷰에 객체지정
        recyclerView = (RecyclerView)findViewById(R.id.rv_popular);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        popularData = new ArrayList<>();

        // 순위용 예제 데이터
        popularData.add(new PopularData("1", "축구", "+3"));
        popularData.add(new PopularData("2", "뜨개질", "+11"));
        popularData.add(new PopularData("3", "작곡", "+2"));
        popularData.add(new PopularData("4", "춤", "NEW"));
        popularData.add(new PopularData("5", "야구", "+4"));
        popularData.add(new PopularData("6", "농구", "NEW"));
        popularData.add(new PopularData("7", "피아노", "-5"));
        popularData.add(new PopularData("8", "영상편집", "+6"));
        popularData.add(new PopularData("9", "일러스트", "NEW"));
        popularData.add(new PopularData("10", "바느질", "-3"));

        //리사이클러뷰에 adapter 지정
        popularAdapter = new PopularAdapter(popularData);
        recyclerView.setAdapter(popularAdapter);

    }

    public void quit(View v){
        finish();
    }
}
