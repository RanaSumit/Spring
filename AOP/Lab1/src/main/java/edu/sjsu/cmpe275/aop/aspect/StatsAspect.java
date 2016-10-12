package edu.sjsu.cmpe275.aop.aspect;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.After;  

/**
 * @author Sumit Rana
 *
 */

@Aspect
@Order(0)
public class StatsAspect {

	
     @After("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))")
     public void afterTweetAdvice(JoinPoint jp) {
     	
     	int currentTweetLength = jp.getArgs()[1].toString().length();
     	System.out.println("Current Tweet Length: "+currentTweetLength);
     	// get length of longest tweet, but should not be greater than 140 characters.
     	if(currentTweetLength > edu.sjsu.cmpe275.aop.TweetStatsImpl.longestTweetLength && currentTweetLength <= 140) {
     		edu.sjsu.cmpe275.aop.TweetStatsImpl.longestTweetLength = currentTweetLength;

     	}
     	String user = jp.getArgs()[0].toString();
     	String tweet = jp.getArgs()[1].toString();

     			TreeMap<String, Integer> userTweetLengthMap = edu.sjsu.cmpe275.aop.TweetStatsImpl.userTweetLengthMap;
     			// if tweet length is greater than 140 then it does not count
     			if(tweet.length() <= 140) {
     				if(userTweetLengthMap.containsKey(user)) {
     					userTweetLengthMap.put(user,userTweetLengthMap.get(user) + tweet.length());
     				}else{
     					userTweetLengthMap.put(user,tweet.length());	
     				}
     				
     			}
     	
     }

     @Before("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.follow(..))")
     public void beforeFollowAdvice(JoinPoint jp) { 
     	
     			String follower = jp.getArgs()[0].toString();
     			String followee = jp.getArgs()[1].toString();
     			TreeMap<String, Set<String>> followerMap = edu.sjsu.cmpe275.aop.TweetStatsImpl.followerMap;
     			Set<String> followerSet;
     			if(followerMap.containsKey(follower)) {
     				followerSet = followerMap.get(follower);

     			}else {
     				followerSet = new HashSet<String>();
     			}
     			followerSet.add(followee);
     			followerMap.put(follower, followerSet);    	
     	
     }
}