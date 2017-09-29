package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;

import java.util.Arrays;

public class SpringXMLConfigRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext repoContext
                = new ClassPathXmlApplicationContext("repoContext.xml");

        System.out.println(
                Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext serviceContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"serviceContext.xml"},repoContext);

        System.out.println(
                Arrays.toString(repoContext.getBeanDefinitionNames()));

        System.out.println(
                Arrays.toString(serviceContext.getBeanDefinitionNames()));


        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        System.out.println(tweetService.allTweets());
        System.out.println(tweetService.getClass());

        System.out.println(
                tweetService.newTweet() == tweetService.newTweet()
        );

        System.out.println(serviceContext.getBean(User.class));
        System.out.println(serviceContext.getBean(Tweet.class));

        serviceContext.close();
        repoContext.close();

    }
}
