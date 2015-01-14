package com.isep.project.DAO;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Marianne on 14/01/15.
 */
public class UserMapper {
    private long id;
    private String name;
    private Timestamp tweetDate;
    private String twitterNickname;
    private Set<TweetMapper> tweets;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Timestamp tweetDate) {
        this.tweetDate = tweetDate;
    }

    public String getTwitterNickname() {
        return twitterNickname;
    }

    public void setTwitterNickname(String twitterNickname) {
        this.twitterNickname = twitterNickname;
    }

    public Set<TweetMapper> getTweets() {
        return tweets;
    }

    public void setTweets(Set<TweetMapper> tweets) {
        this.tweets = tweets;
    }


}
