/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 *
 * @author Maxim
 */
public class Comment {

    private int comment_id;
    private Timestamp comment_created_date;
    private String comment_text;

    private String comment_user_email;
    private int comment_photo_id;

    public Comment() {
    }

    /**
     *
     * @param comment_text
     * @param comment_user_email
     * @param comment_photo_id
     */
    public Comment(String comment_text, String comment_user_email, int comment_photo_id) {
        this.comment_text = comment_text;
        this.comment_user_email = comment_user_email;
        this.comment_photo_id = comment_photo_id;
        this.comment_created_date = new Timestamp(new Date().getTime());
    }

    /**
     *
     * @param comment_id
     * @param comment_created_date
     * @param comment_text
     * @param comment_user
     * @param comment_photo
     */
    public Comment(int comment_id, Timestamp comment_created_date, String comment_text, String comment_user_email, int comment_photo_id) {
        this.comment_id = comment_id;
        this.comment_created_date = comment_created_date;
        this.comment_text = comment_text;
        this.comment_user_email = comment_user_email;
        this.comment_photo_id = comment_photo_id;
    }

    /**
     *
     * @return
     */
    public int getComment_id() {
        return comment_id;
    }

    /**
     *
     *
     * @param comment_id
     */
    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    /**
     *
     * @return
     */
    public Timestamp getComment_created_date() {
        return comment_created_date;
    }

    /**
     *
     * @param comment_created_date
     */
    public void setComment_created_date(Timestamp comment_created_date) {
        this.comment_created_date = comment_created_date;
    }

    /**
     *
     * @return
     */
    public String getComment_text() {
        return comment_text;
    }

    /**
     *
     * @param comment_text
     */
    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    /**
     *
     * @return
     */
    public String getComment_user_email() {
        return this.comment_user_email;
    }

    /**
     *
     * @param comment_user
     */
    public void setComment_user_email(String comment_user_email) {
        this.comment_user_email = comment_user_email;
    }

    /**
     *
     * @return
     */
    public int getComment_photo_id() {
        return comment_photo_id;
    }

    /**
     *
     * @param comment_photo
     */
    public void setComment_photo_id(int comment_photo_id) {
        this.comment_photo_id = comment_photo_id;
    }

}
