package ua.rd.repository;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.List;

public interface TweetRepository {

    List<Tweet> allTweets();
    List<User> allUsers();

}
