package com.example.mentos.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mentos.AI.AIMainChatActivity;
import com.example.mentos.Community.PostActivity;
import com.example.mentos.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager2;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private PopularAdapter popularAdapter;
    private ReviewAdapter reviewAdapter;
    private ArrayList<PopularData> popularDataList;
    private ArrayList<ReviewData> reviewDataList;
    private Handler handler;
    private Runnable runnable;
    private final int SLIDE_DELAY = 2000; // 슬라이드 간격 (4초)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.h_fragment_home, container, false);

        viewPager2 = view.findViewById(R.id.main_banner_viewpager);
        recyclerView = view.findViewById(R.id.recycler_view_popular_hobbies);
        recyclerView2 = view.findViewById(R.id.recycler_view_popular_user);

        //Handler 초기화
        handler = new Handler(Looper.getMainLooper());

        // 이미지 배열 정의
        int[] images = {
                R.drawable.banner03,
                R.drawable.banner02,
                R.drawable.banner01
        };

        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
        viewPager2.setAdapter(adapter);

        // RecyclerView 설정
        popularDataList = new ArrayList<>();
        popularDataList.add(new PopularData("1", "자전거 타기", "상승"));
        popularDataList.add(new PopularData("2", "독서", "하락"));
        popularDataList.add(new PopularData("3", "등산", "유지"));
        popularDataList.add(new PopularData("4", "피아노", "상승"));
        popularDataList.add(new PopularData("5", "코바늘", "하락"));
        // 더 많은 데이터 추가...

        popularAdapter = new PopularAdapter(popularDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(popularAdapter);

        //순위 RecyclerView 주기적 업데이트 시작
        startPeriodicUpdate();

        //recyclerview2 설정
        reviewDataList = new ArrayList<>();
        reviewDataList.add(new ReviewData("김○○","★★★★★", "솔직히 처음에는 반신반의 했습니다 ...더보기"));
        reviewDataList.add(new ReviewData("이○○","★★★★★", "진짜 도움됩니다. 이제는 여기 정착 ...더보기"));
        reviewDataList.add(new ReviewData("박○○","★★★★★", "왠만한 학원보다도 훨씬 나은것 같 ... 더보기"));

        reviewAdapter = new ReviewAdapter((reviewDataList));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(reviewAdapter);

        // button_notifications 클릭 리스너 설정
        ImageButton buttonNotifications = view.findViewById(R.id.button_notifications);
        buttonNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AIMainChatActivity.class);
            startActivity(intent);
        });

        // button_community 클릭 리스너 설정
        ImageButton buttonCommunity = view.findViewById(R.id.community_button);
        buttonCommunity.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PostActivity.class);
            startActivity(intent);
        });

        // 자동 슬라이드 기능 추가
        startAutoSlide();

        return view;
    }

    //순위 RecyclerView 주기적 업데이트 메소드
    private void startPeriodicUpdate() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //리스트 순서 또는 상태 변경
                updateRankingList();
                //어댑테에 변경사항 전달
                popularAdapter.updateRanking(popularDataList);
                //5초후 재실행
                handler.postDelayed(this,5000);
            }
        }, 5000);
    }

    private void updateRankingList() {
        Random random = new Random();
        Map<Integer, PopularData> rankMap = new HashMap<>();

        // 상태에 따른 순위 변화
        for (PopularData data : popularDataList) {
            int currentRank = Integer.parseInt(data.getRank());
            int newRank = currentRank;

            // 무작위로 "상승", "하락", "유지" 상태를 결정
            int randomValue = random.nextInt(3);
            switch (randomValue) {
                case 0: // 상승
                    if (newRank > 1) newRank--;
                    data.setChangeRanking("상승");
                    break;
                case 1: // 하락
                    if (newRank < popularDataList.size()) newRank++;
                    data.setChangeRanking("하락");
                    break;
                case 2: // 유지
                default:
                    data.setChangeRanking("유지");
                    break;
            }

            // 새로운 순위가 중복되면 조정
            while (rankMap.containsKey(newRank)) {
                newRank++;
            }

            data.setRank(String.valueOf(newRank));
            rankMap.put(newRank, data);
        }

        // 리스트를 새로운 순위에 따라 정렬
        Collections.sort(popularDataList, new Comparator<PopularData>() {
            @Override
            public int compare(PopularData d1, PopularData d2) {
                int rank1 = Integer.parseInt(d1.getRank());
                int rank2 = Integer.parseInt(d2.getRank());
                return Integer.compare(rank1, rank2);
            }
        });

        // 중복 없이 최종 순위를 재설정
        for (int i = 0; i < popularDataList.size(); i++) {
            PopularData data = popularDataList.get(i);
            data.setRank(String.valueOf(i + 1));  // 1부터 시작하는 순위 설정
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Fragment가 파괴될 때 Handler와 Runnable을 제거합니다.
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void startAutoSlide() {
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                // 현재 페이지의 인덱스를 가져옵니다.
                int currentItem = viewPager2.getCurrentItem();
                int totalItems = viewPager2.getAdapter().getItemCount();

                // 다음 페이지로 이동합니다.
                if (currentItem < totalItems - 1) {
                    viewPager2.setCurrentItem(currentItem + 1);
                } else {
                    viewPager2.setCurrentItem(0); // 마지막 페이지에서 처음으로 돌아갑니다.
                }

                // 4초 후에 이 Runnable을 다시 실행합니다.
                handler.postDelayed(this, SLIDE_DELAY);
            }
        };

        // 자동 슬라이드를 시작합니다.
        handler.postDelayed(runnable, SLIDE_DELAY);
    }
}
