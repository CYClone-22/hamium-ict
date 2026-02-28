package com.example.mentos.AI.Route;

public class GoalRequest {
    private int chat_room_id;
    private int challenge_number;
    private int level;

    public GoalRequest(int chat_room_id, int challenge_number, int level) {
        this.chat_room_id = chat_room_id;
        this.challenge_number = challenge_number;
        this.level = level;
    }

    // Getters and Setters (자동 생성 가능)
    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public int getChallenge_number() {
        return challenge_number;
    }

    public void setChallenge_number(int challenge_number) {
        this.challenge_number = challenge_number;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
