package ua.rd.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet {

    //  private Long tweetId;
    private String txt;
    private User user;
    private int likeCount;
    private int retweetCount;
    private List<Comment> replies;
    private Date date;

    public Tweet() {
        txt = "";
        user = new User("user");
        likeCount = 0;
        retweetCount = 0;
        replies = new ArrayList<>();
        date = new Date();
    }

    public Tweet(String txt, User user) {
        this.txt = txt;
        this.user = user;
        likeCount = 0;
        retweetCount = 0;
        replies = new ArrayList<>();
        date = new Date();
    }

    public Tweet(String txt, User user, Date date) {
        this.txt = txt;
        this.user = user;
        likeCount = 0;
        retweetCount = 0;
        replies = new ArrayList<>();
        this.date = date;
    }

    public Tweet(Tweet tweet) {
        this.txt = tweet.getTxt();
        this.user = tweet.getUser();
        this.likeCount = tweet.getLikeCount();
        this.retweetCount = tweet.getRetweetCount();
        this.replies = tweet.getReplies();
        this.date = tweet.getDate();
    }

   /* public Long getTweetId() {
        return tweetId;
    }


    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }*/

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Tweet: " + // tweetId +
                "\t" + txt +
                "\nauthor: " + user.getName() +
                "\nlikes: " + likeCount +
                "\nretweets: " + retweetCount +
                "\ndate: " + date;
    }
}
