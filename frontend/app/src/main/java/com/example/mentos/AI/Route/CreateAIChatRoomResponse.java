package com.example.mentos.AI.Route;

import com.google.gson.annotations.SerializedName;

public class CreateAIChatRoomResponse {

    @SerializedName("chat_room_id")
    private Integer chatRoomId;

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
