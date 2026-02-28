package com.example.mentos.Chat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
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

public class ChatListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatListAdapter adapter;
    private List<ChatRoom> chatRoomList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.h_fragment_chatlist, container, false);

        recyclerView = view.findViewById(R.id.chatListRecyclerView); // RecyclerView의 ID

        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),1));

        // Initialize chatRoomList and adapter
        chatRoomList = new ArrayList<>();
        // Add sample data with profile image and hobby type
        chatRoomList.add(new ChatRoom("room1", "user1", "백종원", "냄비를 태우면 어떡해유 으이구", System.currentTimeMillis(), R.drawable.img_cook, "요리"));
        chatRoomList.add(new ChatRoom("room2", "user2", "신예찬", "바이올린 활이 끊어졌어요,,,", System.currentTimeMillis(), R.drawable.img_violin, "바이올린"));
        //채팅리스트에 받아온 데이터 추가하기
        if (getArguments() != null) {
            String userName = getArguments().getString("userName");
            int profileImage = getArguments().getInt("profileImage");
            
            chatRoomList.add(new ChatRoom("room3", "user3", userName, "대화를 시작해보세요", System.currentTimeMillis(), profileImage, "바이올린"));
        }

        adapter = new ChatListAdapter(chatRoomList, new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ChatRoom chatRoom) {
                openChatActivity(chatRoom);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Find the ImageButtons
        ImageButton menuButton = view.findViewById(R.id.menuButton);
        ImageButton searchButton = view.findViewById(R.id.searchButton);

        // Desired color (replace with your desired color resource or hardcoded color)
        int color = getResources().getColor(R.color.button_color);

        // Apply color filter to change the color of the icons
        menuButton.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        searchButton.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));

        return view;
    }

    private void openChatActivity(ChatRoom chatRoom) {
        Intent intent = new Intent(getActivity(), com.example.mentos.Match.chat.ChatActivity.class);
        intent.putExtra("userName", chatRoom.getNickname());
        intent.putExtra("profileImage", chatRoom.getProfileImageResId());
        startActivity(intent);
    }
}
