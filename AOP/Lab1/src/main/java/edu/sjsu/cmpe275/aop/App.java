package edu.sjsu.cmpe275.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        TweetService tweeter = (TweetService) ctx.getBean("tweetService");
        TweetStats stats = (TweetStats) ctx.getBean("tweetStats");

        try {
            tweeter.tweet("alex", "first tweet");            
            tweeter.follow("john","alex");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Most productive user: " + stats.getMostProductiveUser());
        System.out.println("Most active follower: " + stats.getMostActiveFollower());
        System.out.println("Length of the longest tweet: " + stats.getLengthOfLongestTweet());
        ctx.close();
    }
}
