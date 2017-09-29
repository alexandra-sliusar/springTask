package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
       /* tweets = Arrays.asList(
                new Tweet(1L, "First Mesg", null),
                new Tweet(2L, "Second Mesg", null)
               );
        System.out.println("In mem post constr");*/
    }

    @Override
    public List<Tweet> allTweets() {
        return tweets;
    }

    @Override
    public List<User> allUsers() {
        return users;
    }
}
