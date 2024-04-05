
package model;

public class Review {
    static protected int idCounter = 0;
    protected  int id;
    private int rating;
    private String comment;
    private int userAge;

    public Review(int rating, String comment, int userAge) {
        ++idCounter;
        this.id = idCounter;
        this.rating = rating;
        this.comment = comment;
        this.userAge = userAge;
    }

    public int getId() {
        return id;
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
