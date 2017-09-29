package ua.rd.domain;

import java.util.Date;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
public class Retweet extends Tweet {
    private Tweet sourceTweet;

    public Retweet(User user, Tweet sourceTweet) {
        super("", user);
        this.sourceTweet = sourceTweet;
    }

    public Retweet(String txt, User user, Tweet sourceTweet) {
        super(txt, user);
        this.sourceTweet = sourceTweet;
    }

    public Retweet(String txt, User user, Date date, Tweet sourceTweet) {
        super(txt, user, date);
        this.sourceTweet = sourceTweet;
    }

    public Retweet(Tweet tweet, Tweet sourceTweet) {
        super(tweet);
        this.sourceTweet = sourceTweet;
    }

    public Tweet getSourceTweet() {
        return sourceTweet;
    }

    public void setSourceTweet(Tweet sourceTweet) {
        this.sourceTweet = sourceTweet;
    }

    @Override
    public String toString() {
        return "Retweeted: " + sourceTweet.getTxt()
                + "\n source author: " + sourceTweet.getUser().getName()
                + "\nRetweet:\n" + new Tweet(this);
    }
}
