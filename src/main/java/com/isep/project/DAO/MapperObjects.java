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

    //Convert the tweets : allows to not use the entity class
    public Set<TweetMapper> mapTweets (Set<Tweet> tweets) {
        Set<TweetMapper> tweetMappers = new HashSet<TweetMapper>();
        if (tweets.size() != 0) {
            for (Tweet tweet : tweets) {
                TweetMapper tweetMapper = new TweetMapper();
                tweetMapper.setTweetId(tweet.getTweetId());
                tweetMapper.setDate(tweet.getDate());
                tweetMapper.setMessage(tweet.getMessage());
                tweetMappers.add(tweetMapper);
            }
        }
        return tweetMappers;
    }

    //Convert the users
    public List<UserMapper> mapUsers (List<User> users) {
        List<UserMapper> userMappers = new ArrayList<UserMapper>();
        for (User user : users)
        {
            UserMapper userMapper = new UserMapper();
            userMapper.setId(user.getId());
            userMapper.setName(user.getName());
            userMapper.setTwitterNickname(user.getTwitterNickname());
            userMapper.setTweetDate(user.getTweetDate());
            userMapper.setTweets(mapTweets(user.getTweets()));
            userMappers.add(userMapper);
        }

        return userMappers;
    }

    //Convert the tweets and not get the tweets of the user
    //Allows to not have infinity
    public List<TweetMapper>  SetTweetsForUser (List<Tweet> tweets){
        List<TweetMapper> tweetMappers = new ArrayList<TweetMapper>();
            for (Tweet tweet : tweets) {

                //Tweet
                TweetMapper tweetMapper = new TweetMapper();
                tweetMapper.setTweetId(tweet.getTweetId());
                tweetMapper.setDate(tweet.getDate());
                tweetMapper.setMessage(tweet.getMessage());

                //UserMapper
                UserMapper userMapper = new UserMapper();
                userMapper.setId(tweet.getAuthor().getId());
                userMapper.setName(tweet.getAuthor().getName());
                userMapper.setTwitterNickname(tweet.getAuthor().getTwitterNickname());
                userMapper.setTweetDate(tweet.getAuthor().getTweetDate());
                tweetMappers.add(tweetMapper);
            }
        return tweetMappers;
    }
}
