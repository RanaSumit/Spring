package edu.sjsu.cmpe275.aop;

import java.io.IOException;

public class TweetServiceImpl implements TweetService {

	

	/**
    * @throws IllegalArgumentException if the message is more than 140 characters as measured by string length.
    * @throws IOException if there is a network failure
    */

	private int i=0;
    private int j=0;

    public void tweet(String user, String message) throws IllegalArgumentException, IOException {
      //java.util.Date date= new java.util.Date();
      //System.out.println("Twitter Service : Req:("+(i+1)+")" + new java.sql.Timestamp(date.getTime()));
      if(i == 0){
        i++;
        throw new IOException();
      }
      if(i == 1){
        i++;
        throw new IOException();
      }
      if(i == 2){
        i++;
        throw new IOException();
      }
      if(i == 3){
        i++;
        throw new IOException();
      }
      
      if(message.length() > 140){
        i++;
        throw new IllegalArgumentException();
      }
      
      //System.out.println("Twitter Service : Req:("+(i+1)+") Tweet Sucessful!!");
      i++;
      
    }

    public void follow(String follower, String followee) throws IOException {
      if(j == 0){
          j++;
          throw new IOException();
        }
        if(j == 1){
          j++;
          throw new IOException();
        }
        if(j == 2){
          j++;
          throw new IOException();
        }
        if(j == 3){
          j++;
          throw new IOException();
        }
      //System.out.println(follower + " followed " + followee);
      j++;
    }
}