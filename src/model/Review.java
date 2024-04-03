package model;

public class Review {
    private int rating;
    private String comment;
    private int userAge;

    public Review(int rating, String comment, int userAge) {
        this.rating = rating;
        this.comment = comment;
        this.userAge = userAge;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public int getUserAge() {
        return userAge;
    }

}
