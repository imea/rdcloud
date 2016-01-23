/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.model;


import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


/**
 * @author Maxim
 * @since 2015-10-17
 * @version 1.0
 */
public class Photo {

    private int photo_id;
    private String user_email;
    private String image_path;
    private long image_size;
    private Timestamp date_uploaded;
    private String tag = "private";

    
    
    private BufferedImage bufferedImage = null;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    private ArrayList<Comment> photo_comments = new ArrayList<Comment>();;

    
    /**
     * 
     */
    public Photo() {
    }

  /**
   * 
   * @param photo_id
   * @param image_path
   * @param image_size
   * @param caption
   * @param date_uploaded
   * @param user_email
   * @param tag 
   */
    public Photo(int photo_id, String image_path, long image_size, Timestamp date_uploaded, String user_email, String tag) {
        this.photo_id = photo_id;
        this.image_path = image_path;
        this.image_size = image_size;
        this.date_uploaded = date_uploaded;
        this.user_email = user_email;
        this.tag = tag;
    }

    /**
     * 
     * @param photo_id
     * @param image_path
     * @param image_size
     * @param caption
     * @param user_email 
     */
    public Photo(String image_path, long image_size, String user_email) {
        this.image_path = image_path;
        this.image_size = image_size;
        this.date_uploaded = new Timestamp(new Date().getTime());
        this.user_email = user_email;
    }

    /**
     *
     * @return user_email
     */
    public String getUser_email() {
        return user_email;
    }

    /**
     *
     * @param user_email User
     */
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    
    /**
     *
     * @return String
     */
    public int getPhoto_id() {
        return photo_id;
    }

    /**
     *
     * @param photo_id String
     */
    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    /**
     *
     * @return String
     */
    public String getImage_path() {
        return image_path;
    }

    /**
     *
     * @param image_path String
     */
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    /**
     *
     * @return String
     */
    public long getImage_size() {
        return image_size;
    }

    public void setImage_size(long image_size) {
        this.image_size = image_size;
    }

    public Timestamp getDate_uploaded() {
        return date_uploaded;
    }

    public void setDate_uploaded(Timestamp date_uploaded) {
        this.date_uploaded = date_uploaded;
    }

    /**
     *
     * @return
     */
    public ArrayList<Comment> getPhoto_comments() {
        return photo_comments;
    }

    /**
     *
     * @param photo_comments
     */
    public void setPhoto_comments(ArrayList<Comment> photo_comments) {
        this.photo_comments = photo_comments;
    }

    /**
     *
     * @return
     */
    public String getTag() {
        return tag;
    }

    /**
     *
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Photo other = (Photo) obj;
        if (this.photo_id != other.photo_id) {
            return false;
        }
       
        return true;
    }


   

}
