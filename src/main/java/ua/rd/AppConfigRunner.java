package ua.rd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.Arrays;

@Configuration
public class AppConfigRunner {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfigRunner.class);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        BeanDefinition beanDefinition =
                context.getBeanFactory().getBeanDefinition("tweet");
        System.out.println(beanDefinition);
        Tweet tweet = (Tweet) context.getBean("tweet");
        System.out.println(tweet);

        User user = (User) context.getBean("user");
        System.out.println(user);

        AppConfigRunner runner = context.getBean(AppConfigRunner.class);

        System.out.println(context.getBean(AppConfigRunner.class).getClass());
    }

    @Bean
    @Lazy
    @Scope("singleton")
    public Tweet tweet() {
        System.out.println("Called");
        return new Tweet();
    }

    @Bean()
    @Lazy
    public User user(Tweet tweet) {
        User user = new User("Andrii");
        return user;
    }
}
