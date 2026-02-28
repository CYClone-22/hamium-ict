package com.example.mentos.Chat;

public class ChatMessage {
    private String text;
    private boolean isSentByMe;

    public ChatMessage(String text, boolean isSentByMe) {
        this.text = text;
        this.isSentByMe = isSentByMe;
    }

    public String getText() {
        return text;
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }
}

