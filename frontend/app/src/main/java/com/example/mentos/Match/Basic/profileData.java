package com.example.mentos.Match.Basic;

public class profileData {
    private String name;
    private String gender;
    private String age;
    private String level;
    private int userImage;
    private boolean favorite;

    public profileData(String name, String gender, String age, String level, int userImage) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.level = level;
        this.userImage = userImage;
        this.favorite = false;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }
}
