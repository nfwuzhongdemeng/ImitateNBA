package com.dyy.skin.mylibrary.db.model;

import com.dyy.skin.mylibrary.db.NormalUser;

import io.realm.RealmObject;

/**
 * Created by 段钰莹 on 2017/8/30.
 */

public class User extends RealmObject {
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

    public NormalUser toNormalUser(){
        NormalUser user = new NormalUser();
        user.setId(id);
        user.setName(name);
        return user;
    }

}
