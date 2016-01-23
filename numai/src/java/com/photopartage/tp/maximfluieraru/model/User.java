package com.photopartage.tp.maximfluieraru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Maxim
 * @since 2015-10-16
 * @version 1.0
 */
public class User {

    private String user_email;
    private String user_password;
    private String user_first_name;
    private String user_last_name;
    private Date user_created_date;

    private ArrayList<Photo> user_photos = new ArrayList<Photo>();
    private ArrayList<Photo> user_friends_photos = new ArrayList<Photo>();
    private ArrayList<UserFriend> user_friends = new ArrayList<UserFriend>();
    private ArrayList<UserFriend> user_friends_UnconfirmedInvitation = new ArrayList<UserFriend>();
    private ArrayList<UserFriend> user_friends_RecivedInvitation = new ArrayList<UserFriend>();

    /**
     *
     * @param user_email
     * @param user_password
     */
    public User(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_photos = new ArrayList<>();
    }

    /**
     *
     * @param user_email
     * @param user_password
     * @param user_first_name
     * @param user_last_name
     * @param user_created_date
     * @param user_photos
     * @param user_likes
     */
    public User(String user_email, String user_password, String user_first_name, String user_last_name, Date user_created_date, ArrayList<Photo> user_photos) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_created_date = user_created_date;
        this.user_photos = user_photos;
    }

    /**
     *
     * @param user_email
     * @param user_name
     * @param user_password
     * @param user_first_name
     * @param user_last_name
     */
    public User(String user_email, String user_password, String user_first_name, String user_last_name) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_created_date = new Date();
        this.user_photos = new ArrayList<>();
    }

    public User(String[] data) {
        this.user_first_name = data[0];
        this.user_last_name = data[1];
        this.user_email = data[2];
        this.user_password = data[3];
        this.user_created_date = new Date();
        this.user_photos = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<Photo> getUser_photos() {
        return user_photos;
    }

    /**
     *
     * @param user_photos
     */
    public void setUser_photos(ArrayList<Photo> user_photos) {
        this.user_photos = user_photos;
    }

    /**
     * user_email get Method
     *
     * @return String
     */
    public String getUser_email() {
        return user_email;
    }

    /**
     * user_email set Method
     *
     * @param user_email String
     */
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    /**
     * user_password get Method
     *
     * @return String
     */
    public String getUser_password() {
        return user_password;
    }

    /**
     * set Method
     *
     * @param user_password String
     */
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    /**
     * user_email get Method
     *
     * @return
     */
    public String getUser_first_name() {
        return user_first_name;
    }

    /**
     * set Method
     *
     * @param user_first_name String
     */
    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    /**
     * user_email get Method
     *
     * @return
     */
    public String getUser_last_name() {
        return user_last_name;
    }

    /**
     * user_last_name set Method
     *
     * @param user_last_name String
     */
    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    /**
     * user_created_date get Method
     *
     * @return
     */
    public Date getUser_created_date() {
        return user_created_date;
    }

    /**
     * user_created_date set Method
     *
     * @param user_created_date Date
     */
    public void setUser_created_date(Date user_created_date) {
        this.user_created_date = user_created_date;
    }

    /**
     *
     * @param user_friends_photos
     */
    public void setUser_friends_photos(ArrayList<Photo> user_friends_photos) {
        this.user_friends_photos = user_friends_photos;
    }

    /**
     *
     * @return
     */
    public ArrayList<Photo> getUser_friends_photos() {
        return user_friends_photos;
    }

    /**
     *
     * @return
     */
    public ArrayList<UserFriend> getUser_friends() {
        return user_friends;
    }

    /**
     *
     * @param user_friends
     */
    public void setUser_friends(ArrayList<UserFriend> user_friends) {
        this.user_friends = user_friends;
    }

    /**
     *
     * @return
     */
    public ArrayList<UserFriend> getUser_friends_UnconfirmedInvitation() {
        return user_friends_UnconfirmedInvitation;
    }

    /**
     *
     * @return
     */
    public ArrayList<UserFriend> getUser_friends_RecivedInvitation() {
        return user_friends_RecivedInvitation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.user_email);
        hash = 83 * hash + Objects.hashCode(this.user_first_name);
        hash = 83 * hash + Objects.hashCode(this.user_last_name);
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
        final User other = (User) obj;
        if (!Objects.equals(this.user_email, other.user_email)) {
            return false;
        }
        if (!Objects.equals(this.user_first_name, other.user_first_name)) {
            return false;
        }
        if (!Objects.equals(this.user_last_name, other.user_last_name)) {
            return false;
        }
        return true;
    }

}
