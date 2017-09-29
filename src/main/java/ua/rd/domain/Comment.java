package ua.rd.domain;

import java.util.Date;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
public class Comment {
    private String txt;
    private User user;
    private int likeCount;
    private Date date;

    public Comment(String txt, User user, int likeCount, Date date) {
        this.txt = txt;
        this.user = user;
        this.likeCount = likeCount;
        this.date = date;
    }

    public Comment(String txt, User user, Date date) {
        this.txt = txt;
        this.user = user;
        this.date = date;
        likeCount = 0;
    }

    public Comment(String txt, User user) {
        this.txt = txt;
        this.user = user;
        likeCount = 0;
        date = new Date();
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment: " + txt +
                "\nauthor: " + user.getName() +
                "\nlikes: " + likeCount +
                "\ndate: " + date;
    }
}
