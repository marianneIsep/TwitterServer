package com.isep.project;

import com.isep.project.DAO.*;
import com.isep.project.Model.Tweet;
import com.isep.project.Model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Marianne on 16/12/14.
 */


@Path("/services")
public class RESTServices {

    DBHelper dbHelper = new DBHelper();
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
        Tweet tweet = new Tweet();

        APITwitter apiTwitter = new APITwitter(10);
        List<Status> listStatus = apiTwitter.getTweet();
        for(Status status : listStatus) {
            user = apiTwitter.getUserFromStatus(status);
            tweet = apiTwitter.getTweetFromStatus(status, user);

            // check param values are not null
            if (!user.equals(null) && !tweet.equals(null)) {
                user = dbHelper.saveUser(user);
                tweet = dbHelper.saveTweet(tweet);
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
        List<User> listUsers = dbHelper.getUsers();
        return listUsers;
    }

    /*
    * Method that get all tweets from the database
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTweets/{param}")
    public List<Tweet> getTweets(@PathParam("param") long userId) {
        BasicConfigurator.configure();
        List<Tweet> listTweets = dbHelper.getTweetsByUser(userId);
        log.info("param : " + userId);
        return listTweets;
    }



}
