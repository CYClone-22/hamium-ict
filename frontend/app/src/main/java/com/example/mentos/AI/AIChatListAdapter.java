package com.example.mentos.AI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

import java.util.List;

public class AIChatListAdapter extends RecyclerView.Adapter<AIChatListAdapter.AIChatRoomViewHolder> {

    private List<AIChatRoom> AIchatRooms;
    private Context context;

    // Constructor with Context and List<AIChatRoom>
    public AIChatListAdapter(Context context, List<AIChatRoom> AIchatRooms) {
        this.context = context;
        this.AIchatRooms = AIchatRooms;
    }

    @NonNull
    @Override
    public AIChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ai_chat_room, parent, false);
        return new AIChatRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AIChatRoomViewHolder holder, int position) {
        AIChatRoom AIchatRoom = AIchatRooms.get(position);
        holder.bind(AIchatRoom);

    }

    @Override
    public int getItemCount() {
        return AIchatRooms.size();
    }

    static class AIChatRoomViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewUser;
        private TextView hobbyType;
        private TextView lastChatTime;

        public AIChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUser = itemView.findViewById(R.id.textViewUser);
            hobbyType = itemView.findViewById(R.id.hobby_type);
            lastChatTime = itemView.findViewById(R.id.last_chat_time);
        }

        public void bind(AIChatRoom AIchatRoom) {
            textViewUser.setText(AIchatRoom.getNickname());
            hobbyType.setText(AIchatRoom.getHobbyType());
            lastChatTime.setText(AIchatRoom.getLastMessageTime());
        }
    }
}
