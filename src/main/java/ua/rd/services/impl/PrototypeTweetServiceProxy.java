package ua.rd.services.impl;

import ua.rd.ioc.Context;
import ua.rd.services.TweetService;

import java.lang.reflect.Proxy;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */

public class PrototypeTweetServiceProxy {
    private TweetService realTweetService;
    private Context context;

    public PrototypeTweetServiceProxy(TweetService realTweetService, Context context) {
        this.realTweetService = realTweetService;
        this.context = context;
    }

    public TweetService createProxy() {
        return (TweetService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{TweetService.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("newTweet")) {
                        return context.getBean("tweet");
                    } else {
                        return method.invoke(realTweetService, args);
                    }
                }
        );
    }
}