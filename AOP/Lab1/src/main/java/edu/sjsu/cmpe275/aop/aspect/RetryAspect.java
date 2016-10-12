package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around; 

/**
 * @author Sumit Rana
 *
 */
@Aspect
@Order(1)
public class RetryAspect {

     @Around("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.tweet(..))")
     public void aroundTweet(ProceedingJoinPoint jp) throws Throwable{ 
     	boolean retry = true;
     	Object result = null;
     	while(retry) {
     		try{
     			result = jp.proceed();
     			retry = false;
     		}catch(IOException ex) {
     			System.out.println("Network Failure!");
     			if(edu.sjsu.cmpe275.aop.TweetStatsImpl.retryCounter > 0) {
     				edu.sjsu.cmpe275.aop.TweetStatsImpl.retryCounter--;
     			}else {
     				retry = false;
     				edu.sjsu.cmpe275.aop.TweetStatsImpl.retryCounter = 2;
     			}
     		}catch(IllegalArgumentException illegalException) {
     			System.out.println("Tweet Length cannot be more than 140 characters.");
     			retry = false;
     		}catch(Exception ex) {
     			retry = false;
     		}     		
     	}
     }

     @Around("execution(public void edu.sjsu.cmpe275.aop.TweetServiceImpl.follow(..))")
     public void aroundFollow(ProceedingJoinPoint jp) throws Throwable{ 
     	boolean retry = true;
     	Object result = null;
     	while(retry) {
     		try{
     			result = jp.proceed();
     			retry = false;
     		}catch(IOException ex) {
     			System.out.println("Network Failure!");
     			if(edu.sjsu.cmpe275.aop.TweetStatsImpl.retryCounter > 0) {
     				edu.sjsu.cmpe275.aop.TweetStatsImpl.retryCounter--;
     			}else {
     				retry = false;
     				edu.sjsu.cmpe275.aop.TweetStatsImpl.retryCounter = 2;
     			}
     		}catch(Exception ex) {
     			retry = false;
     		}
     		
     	}

     }
}
