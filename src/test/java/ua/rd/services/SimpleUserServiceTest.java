package ua.rd.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import ua.rd.RepositoryConfig;
import ua.rd.ServiceConfig;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.services.impl.SimpleTweetService;
import ua.rd.services.impl.SimpleUserService;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextHierarchy({
        @ContextConfiguration(classes = RepositoryConfig.class),
        @ContextConfiguration(classes = ServiceConfig.class)
})
public class SimpleUserServiceTest {

    @Test
    public void testNewUserAddsUsersInRepo() {
        List<User> users = new ArrayList<>();

        TweetRepository tweetRepository = mock(TweetRepository.class);
        UserService service = new SimpleUserService(tweetRepository);

        when(tweetRepository.allUsers()).thenReturn(users);

        service.createNewUser("aaa");
        service.createNewUser("bbb");


        assertEquals(2, users.size());
    }

    @Test
    public void testRetweetIsAddedToUserWall() {
        List<User> users = new ArrayList<>();
        User user1 = new User("abc");
        User user2 = new User("dbc");
        users.add(user1);
        users.add(user2);
        Tweet tweet = new Tweet("test", user1);

        TweetRepository tweetRepository = mock(TweetRepository.class);
        UserService service = new SimpleUserService(tweetRepository);

        service.retweet(user2, "retweet", tweet);
        boolean ifAdded = false;
        for (Tweet t : service.wall(user2)) {
            if (t.getTxt().equals("retweet"))
                ifAdded = true;
        }
        assertTrue(ifAdded);
    }

    @Test
    public void checkUserWallTwitter() {

        TweetRepository tweetRepository = mock(TweetRepository.class);
        UserService userService = new SimpleUserService(tweetRepository);
        TweetService tweetService = new SimpleTweetService(tweetRepository);
        User user = new User("someone");
        Tweet tweet1 = tweetService.newTweet("txt1", user);
        Tweet tweet2 = tweetService.newTweet("txt2", user);
        Tweet tweet3 = tweetService.newTweet("txt3", user);
        Tweet tweet4 = tweetService.newTweet("txt4", user);
        List<Tweet> tweets = new ArrayList<>();
        when(tweetRepository.allTweets()).thenReturn(tweets);

        assertEquals(4, userService.wall(user).size());
    }


}
