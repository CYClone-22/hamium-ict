package com.example.mentos.AI.Route;

public class GenerateDescriptionRequest {
    private Integer chat_room_id;
    private String lastMessage;

    public GenerateDescriptionRequest(int chatRoomId, String lastMessage) {
        this.chat_room_id = chatRoomId;
        this.lastMessage = lastMessage;
    }

    public int getChatRoomId() {
        return chat_room_id;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}