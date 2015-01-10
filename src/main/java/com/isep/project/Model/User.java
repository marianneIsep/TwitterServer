package com.isep.project.Model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Marianne on 28/12/14.
 */



@Entity
public class User {
    private int id;
    private String name;
    private Timestamp tweetDate;
    private String twitterNickname;
    private Set<Tweet> tweets;


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tweetDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Timestamp tweetDate) {
        this.tweetDate = tweetDate;
    }

    @Basic
    @Column(name = "twitterNickname", nullable = true, insertable = true, updatable = true, length = 255)
    public String getTwitterNickname() {
        return twitterNickname;
    }

    public void setTwitterNickname(String twitterNickname) {
        this.twitterNickname = twitterNickname;
    }

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "author")
    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (tweetDate != null ? !tweetDate.equals(user.tweetDate) : user.tweetDate != null) return false;
        if (twitterNickname != null ? !twitterNickname.equals(user.twitterNickname) : user.twitterNickname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tweetDate != null ? tweetDate.hashCode() : 0);
        result = 31 * result + (twitterNickname != null ? twitterNickname.hashCode() : 0);
        return result;
    }
}
