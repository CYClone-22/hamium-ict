package com.example.mentos.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMessage;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.text_view_message);
        }

        void bind(ChatMessage message) {
            textViewMessage.setText(message.getText());

            // 메시지를 오른쪽 정렬 또는 왼쪽 정렬로 표시
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textViewMessage.getLayoutParams();
            if (message.isSentByMe()) {
                // 내가 보낸 메시지 스타일
                textViewMessage.setBackgroundResource(R.drawable.rounded_background_sent);
                textViewMessage.setTextColor(itemView.getContext().getResources().getColor(android.R.color.black));
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
                params.addRule(RelativeLayout.ALIGN_PARENT_START, 0); // 시작 정렬 제거
            } else {
                // 상대방이 보낸 메시지 스타일
                textViewMessage.setBackgroundResource(R.drawable.rounded_background_received);
                textViewMessage.setTextColor(itemView.getContext().getResources().getColor(android.R.color.white));
                params.addRule(RelativeLayout.ALIGN_PARENT_START);
                params.addRule(RelativeLayout.ALIGN_PARENT_END, 0); // 끝 정렬 제거
            }
            textViewMessage.setLayoutParams(params);
        }
    }

}

