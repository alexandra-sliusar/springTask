package ua.rd.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.domain.Comment;
import ua.rd.domain.Retweet;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.services.UserService;

import java.util.List;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
@Service("userService")
public class SimpleUserService implements UserService {
    TweetRepository tweetRepository;

    @Autowired
    public SimpleUserService(TweetRepository tweetRepository) {

        this.tweetRepository = tweetRepository;
    }

    @Override
    public User createNewUser(String name) {
        User user = new User(name);
        tweetRepository.allUsers().add(user);
        return user;
    }

    @Override
    public void like(Tweet tweet) {
        tweet.setLikeCount(tweet.getLikeCount() + 1);
    }

    @Override
    public void like(Comment comment) {
        comment.setLikeCount(comment.getLikeCount() + 1);
    }

    @Override
    public Tweet retweet(User retweeter, String message, Tweet oldTweet) {
        Retweet retweet = new Retweet(message, retweeter, oldTweet);
        oldTweet.setRetweetCount(oldTweet.getRetweetCount() + 1);
        retweeter.getTweets().add(retweet);
        return retweet;
    }

    @Override
    public Comment reply(User replier, String message, Tweet oldTweet) {
        Comment comment = new Comment(message, replier);
        oldTweet.getReplies().add(comment);
        return comment;
    }

    @Override
    public List<Tweet> wall(User author) {
        return author.getTweets();
    }


}
