package com.example.mentos.AI;

public class AI_ChatMsg {
    public static final String ROLE_USER = "user";
    public static final String ROLE_ASSISTANT = "assistant";
    public static final String ROLE_LOADING = "loading"; // 추가된 부분
    public static final String ROLE_LEVEL_TEST = "level_test"; // 추가된 부분

    private String role;
    private String content;
    private boolean showLevelTestLayout; // 레이아웃 표시 여부

    // Constructor
    public AI_ChatMsg(String role, String content) {
        this.role = role;
        this.content = content;

    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Setter for role
    public void setRole(String role) {
        this.role = role;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }


}

