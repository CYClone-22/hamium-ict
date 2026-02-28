package com.example.mentos.Home;

public class ReviewData {
    String name;
    String grade;
    String content;

    public ReviewData(String name, String grade, String content) {
        this.name = name;
        this.grade = grade;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
