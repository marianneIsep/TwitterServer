package com.isep.project;

import com.isep.project.DAO.TweetDAO;
import com.isep.project.DAO.TweetDAOImpl;
import com.isep.project.DAO.UserDAO;
import com.isep.project.DAO.UserDAOImpl;
import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Marianne on 16/12/14.
 */


@Path("/services")
public class RESTServices {
    UserDAO userDAO;
    TweetDAO tweetDAO;

    Logger log = Logger.getLogger(RESTServices.class);
    /*
    * Method that update the Database :
    * insert a user (if he doesn't exist) and add the new Tweet
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addTweet")
    public String updateData() throws JSONException {
        User user = new User();
        user.setName("Marianne");
        user.setTwitterNickname("MarianneKo");

        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        tweet.setMessage("twitter c'est cool !");
        userDAO = new UserDAOImpl();
        tweetDAO = new TweetDAOImpl();
        // check param values are not null
        if (!user.equals(null) && !tweet.equals(null)) {

            List<User> allUsers = userDAO.getUsers();
            boolean userExists = false;
            for (User oneUser : allUsers) {
                // check if the User doesn't exist
                if (oneUser.getTwitterNickname().equals(user.getTwitterNickname())) {
                    userExists = true;
                    user = oneUser;
                }
            }

            // add the user if he doesn't exist
            if (!userExists)
            {
                user = userDAO.addUser(user);
            }

            if (!tweet.equals(null)) {
                tweet.setAuthor(user);
                tweet = tweetDAO.addTweet(tweet);
            }

        }


        JSONObject jsonObject = new JSONObject();
        if (tweet.getTweetId() != 0 && user.getId() !=0)
            jsonObject.put("statut", "OK");
        else
            jsonObject.put("statut", "NOK");
        return jsonObject.toString();
    }

    /*
    * Method that get all users from the database
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUsers")
    public List<User> getUsers(){
        userDAO = new UserDAOImpl();
        List<User> listUsers = userDAO.getUsers();
        return listUsers;
    }

    /*
    * Method that get all tweets from the database
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTweets/{param}")
    public List<Tweet> getTweets(@PathParam("param") String Nickname) {
        BasicConfigurator.configure();
        tweetDAO = new TweetDAOImpl();
        List<Tweet> listTweets = tweetDAO.getTweetsByUser(Nickname);
        log.info("param : " + Nickname);
        return listTweets;
    }

    @GET
    @Path("/")
    public void testTweets() throws TwitterException {

        APITwitter api = new APITwitter();
        api.getBlabla();
    }
}
