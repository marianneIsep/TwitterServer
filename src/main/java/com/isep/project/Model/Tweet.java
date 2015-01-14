package com.isep.project.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Marianne on 12/01/15.
 */

@Entity
public class Tweet {
    private long tweetId;
    private Timestamp date;
    private String message;
    private User author;

    @Id
    @Column(name = "tweetId", nullable = false, insertable = true, updatable = true)
    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "message", nullable = true, insertable = true, updatable = true, length = 140)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false, insertable = true, updatable = true)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User authorId) {
        this.author = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (tweetId != tweet.tweetId) return false;
        if (date != null ? !date.equals(tweet.date) : tweet.date != null) return false;
        if (message != null ? !message.equals(tweet.message) : tweet.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tweetId ^ (tweetId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
