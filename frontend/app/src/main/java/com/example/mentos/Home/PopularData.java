package com.example.mentos.Home;

public class PopularData {
    String rank;
    String hobby;
    String changeRanking;

    public PopularData(String rank, String hobby, String changeRanking) {
        this.rank = rank;
        this.hobby = hobby;
        this.changeRanking = changeRanking;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getChangeRanking() {
        return changeRanking;
    }

    public void setChangeRanking(String changeRanking) {
        this.changeRanking = changeRanking;
    }
}
