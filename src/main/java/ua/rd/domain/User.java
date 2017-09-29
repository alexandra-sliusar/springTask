package ua.rd.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    //  private Long userId;
    private String name;
    private List<Tweet> tweets;

    public User(String name) {
        this.name = name;
        tweets = new ArrayList<>();
    }

 /*   public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "User: " + //userId +
                "\t" + name +
                "\nWall:\n" + tweets;
    }
}
