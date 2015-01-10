package com.isep.project.DAO;

import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Marianne on 27/12/14.
 */
public class UserDAOImpl implements UserDAO {

    static Logger log = Logger.getLogger(UserDAOImpl.class);
    Session session ;
    TweetDAO tweetDAO;

    /* =================== Method to add a new User ===================
     * ===================      return user.id      ===================
     * */
    @Override
    public User addUser(User user) {
        BasicConfigurator.configure();
        try {
            // get the Session factory and open the session
            session = DBHelper.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            //save the user
            session.save(user);
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

    /* =================== Method to get every user ===================
     * ===================    return List<User>     ===================
     * */
    @Override
    public List<User> getUsers() {
        BasicConfigurator.configure();
        List<User> listUser = null;
        try {
            session = DBHelper.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            listUser = session.createQuery("from User").list();
            tweetDAO = new TweetDAOImpl();
            for (User user : listUser)
            {
                //user.setTweets(new HashSet<Tweet>(tweetDAO.getTweetsByUser(user.getTwitterNickname())));
                user.setTweets(null);
            }
            tx.commit();
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
        return listUser;
    }
}
