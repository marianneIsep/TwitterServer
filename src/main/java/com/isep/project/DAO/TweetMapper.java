package com.isep.project.DAO;

import java.sql.Timestamp;

/**
 * Created by Marianne on 14/01/15.
 */
public class TweetMapper {
    private long tweetId;
    private Timestamp date;
    private String message;
    private UserMapper author;

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserMapper getAuthor() {
        return author;
    }

    public void setAuthor(UserMapper author) {
        this.author = author;
    }


}


