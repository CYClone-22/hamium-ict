package com.example.mentos.Community.comment;

public class CommentData {
    String commentContent;
    int commentProfile;
    String commentUsername;
    String commentTime;
    String commentGoodCount;

    public CommentData(String commentContent, int commentProfile, String commentUsername, String commentTime, String commentGoodCount) {
        this.commentContent = commentContent;
        this.commentProfile = commentProfile;
        this.commentUsername = commentUsername;
        this.commentTime = commentTime;
        this.commentGoodCount = commentGoodCount;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentProfile() {
        return commentProfile;
    }

    public void setCommentProfile(int commentProfile) {
        this.commentProfile = commentProfile;
    }

    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentGoodCount() {
        return commentGoodCount;
    }

    public void setCommentGoodCount(String commentGoodCount) {
        this.commentGoodCount = commentGoodCount;
    }
}
