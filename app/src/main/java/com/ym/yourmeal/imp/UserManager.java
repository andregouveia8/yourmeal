package com.ym.yourmeal.imp;

import com.ym.yourmeal.inter.iUser;
import com.ym.yourmeal.models.User;

import java.util.ArrayList;

public class UserManager implements iUser {



    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<String> keys = new ArrayList <String>();

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

    @Override
    public void setKeys(String key) {
        keys.add(key);
    }

    public ArrayList <String> getKeys(){
        return keys;
    }

}
