package ua.rd.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;

import java.util.Arrays;

public class TweetBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("tweet");
        bd.setScope("singleton");
        MutablePropertyValues propertyValues = bd.getPropertyValues();
        PropertyValue user = propertyValues.getPropertyValue("user");
        Object usv = user.getValue();
        usv = new User(user.getValue().toString());

    }
}
