package com.ym.yourmeal.imp;

import com.ym.yourmeal.inter.IUser;
import com.ym.yourmeal.models.User;

import java.security.PublicKey;
import java.util.ArrayList;

public class UserManager implements IUser {



    public ArrayList<User> users = new ArrayList<User>();

    static UserManager userManager = null;

    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    @Override
    public void setUsers(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers(){
        return users;
    }
}
