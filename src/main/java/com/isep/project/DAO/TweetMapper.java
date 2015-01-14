package com.isep.project.DAO;

import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


