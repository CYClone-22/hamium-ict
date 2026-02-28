package com.example.mentos.AI.Route;

public class StartChatRequest {
    private Integer chat_room_id;

    public StartChatRequest(int chatRoomId) {
        this.chat_room_id = chatRoomId;
    }

    public Integer getChatRoomId() {
        return chat_room_id;
    }

    public void setChatRoomId(Integer chat_room_id) {
        this.chat_room_id = chat_room_id;
    }
}
