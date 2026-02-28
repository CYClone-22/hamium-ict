package com.example.mentos.AI.Route;

public class ChatGPTRequest {
    private int chat_room_id; // 채팅방 ID
    private String message; // 사용자 메시지

    public ChatGPTRequest(int chatRoomId, String message) {
        this.chat_room_id = chatRoomId;
        this.message = message;
    }

    // Getters and Setters
    public int getChatRoomId() {
        return chat_room_id;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chat_room_id = chatRoomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

