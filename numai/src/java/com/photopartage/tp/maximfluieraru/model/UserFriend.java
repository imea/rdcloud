/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.model;

import java.util.Objects;

/**
 *
 * @author Maxim
 */
public class UserFriend {
    
    private String user;
    private String friend;

    public UserFriend() {
    }

    
    public UserFriend(String user, String friend) {
        this.user = user;
        this.friend = friend;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.user);
        hash = 61 * hash + Objects.hashCode(this.friend);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserFriend other = (UserFriend) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.friend, other.friend)) {
            return false;
        }
        return true;
    }
    
    
    
}
