package com.example.mentos.Model;

public class Task {
    private String content;
    private boolean isCompleted;

    public Task(String content, boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
