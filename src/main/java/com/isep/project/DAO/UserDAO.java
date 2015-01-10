package com.isep.project.DAO;


import com.isep.project.Model.User;

import java.util.List;

/**
 * Created by Marianne on 16/12/14.
 */
public interface UserDAO {
    public User addUser(User user);
    public List<User> getUsers();

}
