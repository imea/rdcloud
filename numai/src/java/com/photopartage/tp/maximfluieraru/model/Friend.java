/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Maxim
 */
public class Friend {

    private String friend_email;
    private ArrayList<User> friend_users;

    /**
     *
     * @param friend_email
     */
    public Friend(String friend_email) {
        this.friend_email = friend_email;
    }

    /**
     *
     * @return
     */
    public String getFriend_email() {
        return friend_email;
    }

    /**
     *
     * @param friend_email
     */
    public void setFriend_email(String friend_email) {
        this.friend_email = friend_email;
    }

    /**
     *
     * @param friend_users
     */
    public void setFriend_users(ArrayList<User> friend_users) {
        this.friend_users = friend_users;
    }

    /**
     *
     * @return
     */
    public ArrayList<User> getFriend_users() {
        return friend_users;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Friend other = (Friend) obj;
        if (!Objects.equals(this.friend_email, other.friend_email)) {
            return false;
        }
        return true;
    }

}
