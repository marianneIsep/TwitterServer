package com.isep.project.DAO;

import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Marianne on 14/01/15.
 */
public class MapperObjects {

    public Set<TweetMapper> mapTweets (Set<Tweet> tweets, UserMapper userMapper ) {
        Set<TweetMapper> tweetMappers = new HashSet<TweetMapper>();
        if (tweets.size() != 0) {
            for (Tweet tweet : tweets) {
                TweetMapper tweetMapper = new TweetMapper();
                tweetMapper.setTweetId(tweet.getTweetId());
                tweetMapper.setDate(tweet.getDate());
                tweetMapper.setMessage(tweet.getMessage());
                tweetMapper.setAuthor(userMapper);
                tweetMappers.add(tweetMapper);
            }
        }
        return tweetMappers;
    }

    public List<UserMapper> mapUsers (List<User> users) {
        List<UserMapper> userMappers = new ArrayList<UserMapper>();
        for (User user : users)
        {
            UserMapper userMapper = new UserMapper();
            userMapper.setId(user.getId());
            userMapper.setName(user.getName());
            userMapper.setTwitterNickname(user.getTwitterNickname());
            userMapper.setTweetDate(user.getTweetDate());
            userMapper.setTweets(mapTweets(user.getTweets(), userMapper));
            userMappers.add(userMapper);
        }

        return userMappers;
    }
}