package com.example.mentos.Model;

public class Lecture {
    private String imageUrl;
    private String name;
    private String hobbyType;
    private String difficultyLevel;
    private String videoUrl; // 비디오 URL 필드

    public Lecture(String name, String hobbyType, String difficultyLevel, String videoUrl) {
        this.name = name;
        this.hobbyType = hobbyType;
        this.difficultyLevel = difficultyLevel;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public String getHobbyType() {
        return hobbyType;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
