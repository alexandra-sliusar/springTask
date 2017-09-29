package ua.rd.web.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.rd.RepositoryConfig;
import ua.rd.ServiceConfig;
import ua.rd.services.TweetService;

@Configuration
//@Import({RepositoryConfig.class, ServiceConfig.class})
public class WebInfConfig {

    @Bean
    public HandlerMapping handlerMapping() {
        return new BeanNameURLHandlerMapping();
    }

    @Bean
    public MyController hello() {
        return new HelloContoller();
    }

    @Bean
    public MyController tweets(TweetService tweetService) {
        return new TweetController(tweetService);
    }


}
