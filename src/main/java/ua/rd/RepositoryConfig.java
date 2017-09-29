package ua.rd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.rd.repository.InMemTweetRepository;
import ua.rd.repository.TweetRepository;

@Configuration
public class RepositoryConfig {

    @Bean(initMethod = "init")
    public TweetRepository tweetRepository() {
        return new InMemTweetRepository();
    }

}
