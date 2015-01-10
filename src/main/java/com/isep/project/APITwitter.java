package com.isep.project;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;

/**
 * Created by Marianne on 09/01/15.
 */
public class APITwitter {

    private static Logger log = Logger.getLogger(APITwitter.class);
    private final static String CONSUMER_KEY = "L6ZGosflD657OA7tVKErCWHBI";
    private final static String CONSUMER_KEY_SECRET = "N7SLrFctf084ngiI2deXk7H9Psn07b6ixbt8dcJqPUEfBwyIKS";
    private final static String ACCESS_TOKEN = "2529838722-yQHVEcjr46nSXIYZ5udlJgXlpS1FCIQkjcqJTK8";
    private final static String ACCESS_TOKEN_SECRET = "C2gu5c7syMQswLb5UbwB0LLUMGkc8vYiucb0JRneSEWk2";

    public void getBlabla(){
        BasicConfigurator.configure();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(CONSUMER_KEY);
        cb.setOAuthConsumerSecret(CONSUMER_KEY_SECRET);
        cb.setOAuthAccessToken(ACCESS_TOKEN);
        cb.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        Query query = new Query("#peace");
        int numberOfTweets = 512;
        long lastID = Long.MAX_VALUE;
        log.info("presentation des tweets");
        ArrayList<Status> tweets = new ArrayList<Status>();
        log.info("taille de la liste : " + tweets.size());
        while (tweets.size () < numberOfTweets) {
            log.info("ca plante pas");
            if (numberOfTweets - tweets.size() > 100) {
                log.info("ca plante toujours pas");
                query.setCount(100);
            } else {
                log.info("ca plante pas dans le sinon");
                query.setCount(numberOfTweets - tweets.size());
            }
            try {
                log.info("Va pour la requete");
                QueryResult result = twitter.search(query);
                log.info("C'est la merde maintenant");
                tweets.addAll(result.getTweets());
                System.out.println("Gathered " + tweets.size() + " tweets");
                for (Status t: tweets)
                    if(t.getId() < lastID) lastID = t.getId();

            }

            catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            query.setMaxId(lastID-1);
        }

        for (int i=0; i<tweets.size(); i++)
        {
            Status t = tweets.get(i);

            GeoLocation loc = t.getGeoLocation();

            if(loc != null)
            {
                tweets.get(i++);
                String user = t.getUser().getScreenName();
                String msg = t.getText();

                System.out.println("user : " + user + " msg : " + msg);


            }
        }
    }
}
