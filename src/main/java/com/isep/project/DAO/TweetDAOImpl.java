package com.isep.project.DAO;

import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Marianne on 27/12/14.
 */
public class TweetDAOImpl implements TweetDAO {

    static Logger log = Logger.getLogger(TweetDAOImpl.class);
    Session session;

    @Override
    public Tweet addTweet(Tweet tweet) {
        BasicConfigurator.configure();
        try {
            session = DBHelper.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(tweet);
            tx.commit();
            log.info(tweet.getTweetId());
        } catch(Exception e){
            log.warn(e.getMessage());
        }
        finally {
            if(session != null & session.isOpen())
            {
                session.close();
            }
        }
        return tweet;
    }

    @Override
    public List<Tweet> getTweetsByUser(String userNickname){
        BasicConfigurator.configure();
        List<Tweet> listTweetsByUser = null;
        try {
            session = DBHelper.getSessionFactory().openSession();
            listTweetsByUser = session.createCriteria(Tweet.class).
                    createAlias("author", "author").
                    add(Restrictions.like("author.twitterNickname", userNickname)).
                    addOrder(Order.desc("date")).list();
            log.info(listTweetsByUser.size());
        } catch (Exception e){
            log.error(e.getMessage());
        }
        finally {
            if(session != null & session.isOpen())
            {
                session.close();
            }
        }
        for(Tweet tweet : listTweetsByUser){
            tweet.setAuthor(null);
        }
        return listTweetsByUser;
    }
}
