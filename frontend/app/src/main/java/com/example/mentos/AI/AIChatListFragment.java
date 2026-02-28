package com.example.mentos.AI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.ArrayList;
import java.util.List;

public class AIChatListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AIChatListAdapter adapter;
    private List<AIChatRoom> AIchatRoomList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.h_fragment_aitutor, container, false);

        recyclerView = view.findViewById(R.id.AIchatListRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        AIchatRoomList = new ArrayList<>();
        AIchatRoomList.add(new AIChatRoom(11, 45, "피아노AI", "35분전", "피아노"));

        // Pass the context to the adapter constructor
        adapter = new AIChatListAdapter(getContext(), AIchatRoomList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // plus_btn 클릭 시 AIActivity로 이동하는 코드 추가
        ImageButton plusButton = view.findViewById(R.id.plus_btn);
        plusButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AIMainChatActivity.class);
            startActivity(intent);
            // 화면 전환 애니메이션 추가
            getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
        });

        return view;
    }
}
