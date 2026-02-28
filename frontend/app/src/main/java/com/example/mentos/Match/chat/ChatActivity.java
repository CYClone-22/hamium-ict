package com.example.mentos.Match.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.Chat.ChatListFragment;
import com.example.mentos.Chat.ChatMessage;
import com.example.mentos.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messageList = new ArrayList<>();
    private TextView textViewPartnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_chat);

        // partner name 초기화
        textViewPartnerName = findViewById(R.id.textview_chat_partner_name);

        Intent intent = getIntent();
        String partnerName = intent.getStringExtra("userName");
        if (partnerName != null) {
            textViewPartnerName.setText(partnerName); // TextView에 파트너 이름 설정
        }

        recyclerView = findViewById(R.id.matching_chat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(chatAdapter);

        ImageButton buttonSend = findViewById(R.id.btn_send);
        final EditText editTextMessage = findViewById(R.id.edit_text_message);
        ImageButton buttonGoback = findViewById(R.id.btn_goback_list); // goback_list 이미지 버튼

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    // 메시지를 리스트에 추가하고 RecyclerView 갱신
                    messageList.add(new ChatMessage(messageText, true));
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size() - 1);
                    editTextMessage.setText(""); // 메시지 입력란 비우기
                }
            }
        });

        buttonGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ChatListActivity로 돌아가기
                onBackPressed();
            }
        });
    }
}
