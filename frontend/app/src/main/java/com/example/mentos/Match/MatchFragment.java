package com.example.mentos.Match;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.mentos.Match.Basic.ProfileAdapter;
import com.example.mentos.Match.Basic.profileData;
import com.example.mentos.Match.Recommend.RecommendAdapter;
import com.example.mentos.Match.Recommend.RecommendData;
import com.example.mentos.Match.Survey.SurveyGuideActivity;
import com.example.mentos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchFragment extends Fragment {

    private ArrayList<profileData> basicList;
    private ArrayList<RecommendData> recommendList;
    private RecyclerView recyclerViewBasic;
    private RecyclerView recyclerViewRecommend;

    private Button recommendButton, basicButton;

    public String role;
    public int guestId;
    private Response.Listener<JSONObject> responseListener;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "MentosPrefs";
    private static final String KEY_GUEST_ID = "guest_id";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.h_fragment_matching, container, false);

        recyclerViewBasic = view.findViewById(R.id.recyclerView_matching_basic);
        recyclerViewRecommend = view.findViewById(R.id.recyclerView_matching_recommend); // 수정된 부분

        // 데이터 설정
        basicList = new ArrayList<>();
        basicList.add(new profileData("김지수", "여", "23", "중상급이상", R.drawable.profile_temp));
        basicList.add(new profileData("신예찬", "남", "32", "상급이상", R.drawable.img_violin));
        basicList.add(new profileData("이민석", "남", "30", "중하급이상", R.drawable.logo_person));

        recommendList = new ArrayList<>();
        recommendList.add(new RecommendData("김지수", "여", "23", "중상급이상", R.drawable.profile_temp));
        recommendList.add(new RecommendData("신예찬", "남", "32", "상급이상", R.drawable.img_violin));
        recommendList.add(new RecommendData("이민석", "남", "30", "중하급이상", R.drawable.logo_person));

//        recommendButton = view.findViewById(R.id.empty_state_button_recommend);
//        basicButton = view.findViewById(R.id.empty_state_button_basic);

        // ProfileAdapter 설정
        ProfileAdapter profileAdapter = new ProfileAdapter(basicList, basicButton, true, this);
        recyclerViewBasic.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBasic.setAdapter(profileAdapter);

        // RecommendAdapter 설정
        RecommendAdapter recommendAdapter = new RecommendAdapter(recommendList, recommendButton, false, this);
        recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRecommend.setAdapter(recommendAdapter);

        // FloatingActionButton 설정
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SurveyGuideActivity.class);
                startActivity(intent);
            }
        });

        //데이터 받아오기
        if (getArguments() != null) {
            role = getArguments().getString("role");
            guestId = getArguments().getInt("guest_id", -1);
            processMatchRequest();
        }

        return view;
    }

    public void processMatchRequest() {
        // 이 부분에서 mentorId와 menteeId를 설정합니다
        int mentee_id = 0, mentor_id = 0;
        if (role.equals("멘토")) {
            mentor_id = guestId;
            mentee_id = 23;
        } else if (role.equals("멘티")) {
            mentee_id = guestId;
            mentor_id = 45;
        }

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        try {
            MatchRequest matchRequest = new MatchRequest(mentee_id, mentor_id, responseListener);
            queue.add(matchRequest);
        } catch (JSONException e){
            e.printStackTrace();
            Log.e("MatchActivity", "Failed to create MatchRequest: " + e.getMessage());
        }

        Log.e("mentorID", String.valueOf(mentor_id));
        Log.e("menterrId", String.valueOf(mentee_id));
    }
}
