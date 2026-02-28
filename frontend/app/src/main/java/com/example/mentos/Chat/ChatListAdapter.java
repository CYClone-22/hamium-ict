package com.example.mentos.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatRoomViewHolder> {

    private List<ChatRoom> chatRooms;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ChatRoom chatRoom);
    }

    public ChatListAdapter(List<ChatRoom> chatRooms, OnItemClickListener onItemClickListener) {
        this.chatRooms = chatRooms;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room, parent, false);
        return new ChatRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position) {
        ChatRoom chatRoom = chatRooms.get(position);
        holder.bind(chatRoom);
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }

    class ChatRoomViewHolder extends RecyclerView.ViewHolder {

        private ImageView profileImage;
        private TextView textViewUser;
        private TextView hobbyType;
        private TextView lastChat;

        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.chat_profile);
            textViewUser = itemView.findViewById(R.id.textViewUser);
            hobbyType = itemView.findViewById(R.id.hobby_type);
            lastChat = itemView.findViewById(R.id.last_chat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(chatRooms.get(position));
                    }
                }
            });
        }

        public void bind(ChatRoom chatRoom) {
            textViewUser.setText(chatRoom.getNickname());
            hobbyType.setText(chatRoom.getHobbyType());
            lastChat.setText(chatRoom.getLastMessage());
            profileImage.setImageResource(chatRoom.getProfileImageResId());
        }
    }
}
