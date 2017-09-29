package ua.rd.services;

import ua.rd.domain.Comment;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.List;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
public interface UserService {
    User createNewUser(String name);

    void like(Tweet tweet);

    void like(Comment comment);

    Tweet retweet(User retweeter, String message, Tweet oldTweet);

    Comment reply(User replier, String message, Tweet oldTweet);

    List<Tweet> wall(User author);

}
