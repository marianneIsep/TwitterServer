package com.isep.project.DAO;

import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Marianne on 16/12/14.
 */
public class DBHelper {
    private static SessionFactory sessionFactory;
    Session session;
    private static Logger log = Logger.getLogger(DBHelper.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }


    /*
    =============================================
    ================= Save data =================
    =============================================
    * */
    public Tweet saveTweet(Tweet tweet) {
        BasicConfigurator.configure();
        try {
            session = DBHelper.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(tweet);
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

    public User saveUser(User user) {
        BasicConfigurator.configure();
        try {
            // get the Session factory and open the session
            session = DBHelper.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            //save the user
            session.saveOrUpdate(user);
            //allows to insert into the database
            tx.commit();
            log.info(user.getId());
        } catch(Exception e){
            // Create log if there is a problem with the transaction
            log.warn(e.getMessage());
        }
        finally {
            // even if there is a problem, the session is closed at the end
            if(session != null & session.isOpen())
            {
                session.close();
            }
        }
        return user;
    }


    /*
    =============================================
    ========= get tweets for one user ===========
    =============================================
    * */


    public List<Tweet> getTweetsByUser(long userId){
        BasicConfigurator.configure();
        List<Tweet> listTweetsByUser = null;
        try {
            session = DBHelper.getSessionFactory().openSession();
            listTweetsByUser = session.createCriteria(Tweet.class).
                    createAlias("author", "author").
                    add(Restrictions.like("author.id", userId)).
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
        return listTweetsByUser;
    }

    /*
    =============================================
    ========= get users ===========
    =============================================
    * */

     public List<User> getUsers() {
        BasicConfigurator.configure();
        List<User> listUser = null;
        try {
            session = DBHelper.getSessionFactory().openSession();
            listUser = (List<User>)session.createQuery("from User").list();
            log.info(listUser.size());
        } catch(Exception e){
            log.warn(e.getMessage());
        }
        finally {
            if(session != null & session.isOpen())
            {
                session.close();
            }
        }
        return listUser ;
    }




}
