package com.example.mentos.AI;


public class AIChatRoom {
    private Integer chatRoomId;
    private Integer userId;
    private String nickname;

    private String lastMessageTime;
    private String hobbyType; // 취미 종류

    public AIChatRoom(Integer chatRoomId, Integer userId, String nickname, String lastMessageTime, String hobbyType) {
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.nickname = nickname;
        this.lastMessageTime = lastMessageTime;
        this.hobbyType = hobbyType;
    }

    public Integer getchatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }


    public String getHobbyType() {
        return hobbyType;
    }

    public void setHobbyType(String hobbyType) {
        this.hobbyType = hobbyType;
    }
}
