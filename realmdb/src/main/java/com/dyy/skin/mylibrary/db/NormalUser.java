package com.dyy.skin.mylibrary.db;

import com.dyy.skin.mylibrary.db.model.User;

/**
 * Created by 段钰莹 on 2017/8/30.
 */

public class NormalUser {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User toDBUser(){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
}
