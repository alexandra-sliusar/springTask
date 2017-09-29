package ua.rd.runner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.RepositoryConfig;
import ua.rd.ServiceConfig;
import ua.rd.domain.Comment;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.services.TweetService;
import ua.rd.services.UserService;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
public class JavaConfigRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext repoContext =
                new AnnotationConfigApplicationContext(RepositoryConfig.class);

        AnnotationConfigApplicationContext serviceContext =
                new AnnotationConfigApplicationContext();
        serviceContext.setParent(repoContext);
        serviceContext.register(ServiceConfig.class);
        serviceContext.refresh();

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        TweetRepository tweetRepository = (TweetRepository) serviceContext.getBean("tweetRepository");
        UserService userService = (UserService) serviceContext.getBean("userService");


        User user1 = userService.createNewUser("Anne");
        User user2 = userService.createNewUser("Sherlock M. Holmes");
        User user3 = userService.createNewUser("Caitlin");
        User user4 = userService.createNewUser("John Doe");
        User user5 = userService.createNewUser("userHohoho");

        Tweet tweet1 = tweetService.newTweet("Some text", user1);
        userService.like(tweet1);
        userService.like(tweet1);
        Comment comment1 = userService.reply(user2, "Funny", tweet1);
        Comment comment2 = userService.reply(user4, "Agree", tweet1);
        userService.like(comment1);
        userService.like(comment2);
        userService.like(comment2);

        Tweet tweet2 = userService.retweet(user3, "See that", tweet1);
        userService.retweet(user5, "Wow", tweet2);

        System.out.println(user1.getName() + ": \n" + userService.wall(user1)+ "\n");
        System.out.println(user2.getName() + ": \n" +userService.wall(user2)+ "\n");
        System.out.println(user3.getName() + ": \n" +userService.wall(user3)+ "\n");
        System.out.println(user4.getName() + ": \n" +userService.wall(user4)+ "\n");
        System.out.println(user5.getName() + ": \n" +userService.wall(user5)+ "\n");

    }
}

