package com.example.mentos.AI.Route;

import com.google.gson.annotations.SerializedName;

public class CreateAIChatRoomRequest {

    @SerializedName("guest_id")
    private int guestId; // int 타입으로 변경

    @SerializedName("is_ai_chatbot")
    private boolean isAiChatbot;

    public CreateAIChatRoomRequest(int guestId, boolean isAiChatbot) {
        this.guestId = guestId;
        this.isAiChatbot = isAiChatbot;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public boolean isAiChatbot() {
        return isAiChatbot;
    }

    public void setAiChatbot(boolean isAiChatbot) {
        this.isAiChatbot = isAiChatbot;
    }
}
