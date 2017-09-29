package ua.rd.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.services.TweetService;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
@Service("tweetService")
public class SimpleTweetService implements TweetService {

    private TweetRepository tweetRepository;


    @Autowired
    public SimpleTweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweetRepository.allTweets();
    }

    @Override
    public TweetRepository getRepository() {
        return tweetRepository;
    }

    @Override
    public Tweet newTweet(String message, User user) {
        if (message.length() > MAXIMUM_TWEET_LENGTH)
            message = message.substring(0, MAXIMUM_TWEET_LENGTH);
        Tweet tweet = new Tweet(message, user);
        tweetRepository.allTweets().add(tweet);
        user.getTweets().add(tweet);
        return tweet;
    }

    @Override
    public Tweet newTweet() {
        Tweet tweet = new Tweet("", new User("Unknown user"));
        tweetRepository.allTweets().add(tweet);
        return tweet;
    }


    @Override
    public int countLike(Tweet tweet) {
        return tweet.getLikeCount();
    }

    @Override
    public int countRetweet(Tweet tweet) {
        return tweet.getRetweetCount();
    }

    @Override
    public int textLength(Tweet tweet) {
        return tweet.getTxt().length();
    }

    @Override
    public String showAuthor(Tweet tweet) {
        return tweet.getUser().getName();
    }
}
