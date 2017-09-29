package ua.rd.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import ua.rd.RepositoryConfig;
import ua.rd.ServiceConfig;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextHierarchy({
        @ContextConfiguration(classes = RepositoryConfig.class),
        @ContextConfiguration(classes = ServiceConfig.class)
})
public class SimpleTweetServiceTest {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;


    @Test
    public void newTweetIsAddedCorrectly() {
        User user = new User("aaa");
        tweetService.newTweet("", user);
        assertEquals(1, user.getTweets().size());
    }

    @Test
    public void tweetsCountIsCorrect() {
        User user = new User("aaa");
        tweetService.newTweet("", user);
        tweetService.newTweet("", user);
        User otherUser = new User("bbb");
        tweetService.newTweet("", otherUser);

        int itemCounter = 0;
        for (Tweet t : tweetService.allTweets()) {
            itemCounter++;
        }
        assertEquals(3, itemCounter);
    }


    @Test
    public void newTweetWithoutParamsIsNotNull() throws Exception {
        tweetService.newTweet();
        assertNotNull(tweetService.newTweet());
    }

    @Test
    public void newTweetLikesCountIsZero() {
        Tweet tweet = new Tweet("", new User("avb"));
        assertEquals(0, tweetService.countLike(tweet));
    }

    @Test
    public void newTweetRetweetCountIsZero() {
        Tweet tweet = new Tweet("", new User("avb"));
        assertEquals(0, tweetService.countRetweet(tweet));
    }

    @Test
    public void tweetWithBigMessageIsCutOffToMaximumLength() {

        tweetService.newTweet("121hiffseihfihsfihiehf" +
                "feshisehihihigsheh" +
                "segihgeishgisehgisges", new User("avb"));
        for (Tweet t : tweetService.allTweets()) {
            assertEquals(tweetService.MAXIMUM_TWEET_LENGTH, t.getTxt().length());
        }
    }

    @Test
    public void testCountOfLikesCountsCorrectly() {
        Tweet tweet = new Tweet("abc", new User("abc"));
        userService.like(tweet);
        userService.like(tweet);
        userService.like(tweet);
        userService.like(tweet);
        assertEquals(4, tweet.getLikeCount());

    }

    @Test
    public void testCountOfRetweetsCountsCorrectly() {
        Tweet tweet = new Tweet("abc", new User("abc"));
        User user = new User("aa");
        userService.retweet(user, "", tweet);
        userService.retweet(user, "", tweet);
        userService.retweet(user, "", tweet);
        userService.retweet(user, "", tweet);
        assertEquals(4, tweet.getRetweetCount());
    }

    @Test
    public void testCountOfRepliesCountsCorrectly() {
        Tweet tweet = new Tweet("abc", new User("abc"));
        User user = new User("aa");
        userService.reply(user, "", tweet);
        userService.reply(user, "", tweet);
        userService.reply(user, "", tweet);
        userService.reply(user, "", tweet);
        assertEquals(4, tweet.getReplies().size());
    }

    @Test
    public void testShowAuthorWorksCorrectly() {
        User user = userService.createNewUser("aa");
        Tweet tweet = tweetService.newTweet("abc", user);
        assertEquals(user.getName(), tweetService.showAuthor(tweet));
    }

}