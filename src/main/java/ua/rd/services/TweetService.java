package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;

public interface TweetService {
    int MAXIMUM_TWEET_LENGTH = 50;

    Iterable<Tweet> allTweets();

    TweetRepository getRepository();

    Tweet newTweet(String message, User user);

    Tweet newTweet();

    int countLike(Tweet tweet);

    int countRetweet(Tweet tweet);

    int textLength(Tweet tweet);

    String showAuthor(Tweet tweet);
}
