package com.isep.project.DAO;


import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Marianne on 17/12/14.
 */
public interface TweetDAO {

    public Tweet addTweet(Tweet tweet);
    public List<Tweet> getTweetsByUser (String userNickname);
}
