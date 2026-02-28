package com.example.mentos.Chat;

public class ChatRoom {
    private String roomId;
    private String userId;
    private String nickname;
    private String lastMessage;
    private long lastMessageTime;
    private int profileImageResId; // 프로필 이미지 리소스 ID
    private String hobbyType; // 취미 종류

    public ChatRoom(String roomId, String userId, String nickname, String lastMessage, long lastMessageTime, int profileImageResId, String hobbyType) {
        this.roomId = roomId;
        this.userId = userId;
        this.nickname = nickname;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.profileImageResId = profileImageResId;
        this.hobbyType = hobbyType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(long lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public int getProfileImageResId() {
        return profileImageResId;
    }

    public void setProfileImageResId(int profileImageResId) {
        this.profileImageResId = profileImageResId;
    }

    public String getHobbyType() {
        return hobbyType;
    }

    public void setHobbyType(String hobbyType) {
        this.hobbyType = hobbyType;
    }
}
