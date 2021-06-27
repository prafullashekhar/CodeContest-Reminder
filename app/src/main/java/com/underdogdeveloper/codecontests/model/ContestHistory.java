package com.underdogdeveloper.codecontests.model;

public class ContestHistory {
    String contestName;
    int rank,ratingChange,newRating,oldRating;

    public ContestHistory() {
    }

    public ContestHistory(String contestName, int rank, int newRating, int oldRating) {
        this.contestName = contestName;
        this.rank = rank;
        this.ratingChange = newRating - oldRating;
        this.newRating = newRating;
        this.oldRating = oldRating;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRatingChange() {
        return ratingChange;
    }

    public void setRatingChange(int ratingChange) {
        this.ratingChange = ratingChange;
    }

    public int getNewRating() {
        return newRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }

    public int getOldRating() {
        return oldRating;
    }

    public void setOldRating(int oldRating) {
        this.oldRating = oldRating;
    }
}
