package com.example.mentos.Chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatListAdapter adapter;
    private List<ChatRoom> chatRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_fragment_chatlist);

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.chatListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        // 가상의 채팅방 목록 생성 (실제 데이터는 서버에서 가져와야 함)
//        chatRooms = createChatRooms();

        // Adapter 설정
        adapter = new ChatListAdapter(chatRooms, new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ChatRoom chatRoom) {

            }
        });
        recyclerView.setAdapter(adapter);

    }

//    // 가상의 채팅방 목록 생성 (임시 데이터)
//    private List<ChatRoom> createChatRooms() {
//        List<ChatRoom> rooms = new ArrayList<>();
//
//        // 각 채팅방에 대한 정보를 명시적으로 지정하여 ChatRoom 객체 생성
//        rooms.add(new ChatRoom("1", "userId1", "User1", "Hello!", System.currentTimeMillis()));
//        rooms.add(new ChatRoom("2", "userId2", "User2", "Hi!", System.currentTimeMillis()));
//        rooms.add(new ChatRoom("3", "userId3", "User3", "Hey!", System.currentTimeMillis()));
//
//        return rooms;
//    }

}
