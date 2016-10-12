package edu.sjsu.cmpe275.aop;

import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.TreeMap;

/**
 * @author Sumit Rana
 *
 */

public class TweetStatsImpl implements TweetStats {


	public static int longestTweetLength = 0;
	public static TreeMap<String, Set<String>> followerMap = new TreeMap<String, Set<String>>();
	public static TreeMap<String, Integer> userTweetLengthMap = new TreeMap<String, Integer>();
	public static int retryCounter = 2;

	/**
    * reset all the three measurements.
    */
   public void resetStats() {
   		longestTweetLength = 0;
   		followerMap.clear();
   		userTweetLengthMap.clear();
   }
   
   /**
    * @return the length of longest message successfully tweeted since the beginning or last reset.
    * If no messages were successfully tweeted, return 0.
    */
   public int getLengthOfLongestTweet() {   		
   		return longestTweetLength;
   }

    /**
    * @return the user who has attempted to follow the biggest number of different users since
    * the beginning or last reset. If there is a tie, return the 1st of such users based on
    * alphabetical order. Even if the follow action did not succeed, it still counts toward the stats.
    * If no users attempted to follow anybody, return null.  
    */
   public String getMostActiveFollower() {
   		int maxFollowedUser = 0;
   		String user = null;

   		if(followerMap.size() > 0) {
   			for(Map.Entry<String, Set<String>> entry : followerMap.entrySet()) {

   				String currentUser = entry.getKey();
   				int currentSize = entry.getValue().size();
   				if(currentSize > maxFollowedUser) {
   					user = currentUser;
   					maxFollowedUser = currentSize;
   				}
   			}
   			return user;
   		}
   		return null;
   }

    /**
    * The most productive user is determined by the total length of all the messages successfully tweeted since the beginning
    * or last reset. If there is a tie, return the 1st of such users based on alphabetical order. If no users successfully tweeted, return null.
    * @return the most productive user.
    */
   public String getMostProductiveUser() {
   		int mostProductiveUser = 0;
   		String user = null;

   		if(userTweetLengthMap.size() > 0) {
   			for(Map.Entry<String, Integer> entry : userTweetLengthMap.entrySet()) {

   				String currentUser = entry.getKey();
   				int currentSize = entry.getValue();
   				if(currentSize > mostProductiveUser) {
   					user = currentUser;
   					mostProductiveUser = currentSize;
   				}  				

   			}
   			return user;
   		}
   		return null;
   }
}