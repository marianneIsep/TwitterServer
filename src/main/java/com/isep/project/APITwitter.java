package com.isep.project;
import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;
import org.apache.log4j.Logger;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marianne on 09/01/15.
 */
public class APITwitter {

    private static final Logger log = Logger.getLogger(APITwitter.class);
    private final static String CONSUMER_KEY = "L6ZGosflD657OA7tVKErCWHBI";
    private final static String CONSUMER_KEY_SECRET = "N7SLrFctf084ngiI2deXk7H9Psn07b6ixbt8dcJqPUEfBwyIKS";
    private final static String ACCESS_TOKEN = "2529838722-yQHVEcjr46nSXIYZ5udlJgXlpS1FCIQkjcqJTK8";
    private final static String ACCESS_TOKEN_SECRET = "C2gu5c7syMQswLb5UbwB0LLUMGkc8vYiucb0JRneSEWk2";

    private static ConfigurationBuilder configurationBuilder;
    private static int numberOfTweets;

    public APITwitter(int numberOfTweets){
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(CONSUMER_KEY);
        configurationBuilder.setOAuthConsumerSecret(CONSUMER_KEY_SECRET);
        configurationBuilder.setOAuthAccessToken(ACCESS_TOKEN);
        configurationBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        this.numberOfTweets = numberOfTweets;
    }

    public List<Status> getTweet(){
        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
        Query query = new Query("@google");
        List<Status> listTweetsFromTwitter = new ArrayList<Status>();
        while (listTweetsFromTwitter.size() < numberOfTweets){
            try
            {
                query.setCount(numberOfTweets);
                QueryResult queryResult = twitter.search(query);
                listTweetsFromTwitter.addAll(queryResult.getTweets());
            }
            catch(TwitterException te)
            {
                log.error(te.getMessage());
            }
        }

        return listTweetsFromTwitter;
    }

    public User getUserFromStatus(Status status){
        User user = new User();
        user.setName(status.getUser().getName());
        user.setTwitterNickname(status.getUser().getScreenName());
        user.setTweetDate(new Timestamp(status.getUser().getCreatedAt().getTime()));
        user.setId(status.getUser().getId());
        return user;
    }

    public Tweet getTweetFromStatus(Status status){
        Tweet tweet = new Tweet();
        tweet.setMessage(status.getText());
        tweet.setDate(new Timestamp(status.getCreatedAt().getTime()));
        tweet.setTweetId(status.getId());
        return tweet;
    }
}
