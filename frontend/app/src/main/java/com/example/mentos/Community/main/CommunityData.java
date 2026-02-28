package com.example.mentos.Community.main;

public class CommunityData {
    String threadTitle, threadContent, threadUsername, threadTime, threadGoodCount, threadReplyCount;
    int threadProfile;

    public CommunityData(String threadTitle, String threadContent, String threadUsername, String threadTime, String threadGoodCount, String threadReplyCount, int threadProfile) {
        this.threadTitle = threadTitle;
        this.threadContent = threadContent;
        this.threadUsername = threadUsername;
        this.threadTime = threadTime;
        this.threadGoodCount = threadGoodCount;
        this.threadReplyCount = threadReplyCount;
        this.threadProfile = threadProfile;
    }

    public String getThreadTitle() {
        return threadTitle;
    }

    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    public String getThreadUsername() {
        return threadUsername;
    }

    public void setThreadUsername(String threadUsername) {
        this.threadUsername = threadUsername;
    }

    public String getThreadTime() {
        return threadTime;
    }

    public void setThreadTime(String threadTime) {
        this.threadTime = threadTime;
    }

    public String getThreadGoodCount() {
        return threadGoodCount;
    }

    public void setThreadGoodCount(String threadGoodCount) {
        this.threadGoodCount = threadGoodCount;
    }

    public String getThreadReplyCount() {
        return threadReplyCount;
    }

    public void setThreadReplyCount(String threadReplyCount) {
        this.threadReplyCount = threadReplyCount;
    }

    public int getThreadProfile() {
        return threadProfile;
    }

    public void setThreadProfile(int threadProfile) {
        this.threadProfile = threadProfile;
    }
}
