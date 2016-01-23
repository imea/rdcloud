/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao.implementaion;

import com.photopartage.tp.maximfluieraru.dao.DAO;
import com.photopartage.tp.maximfluieraru.dao.DbConnection;
import com.photopartage.tp.maximfluieraru.model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maxim
 */
public class CommentDAO extends DAO<Comment> {

    @Override
    public boolean insert(Comment object) {
        boolean insert = false;
        String query = "INSERT INTO `comments`(`USER_EMAIL`, `PHOTO_ID`, `COMMENT_CREATED_DATE`, `COMMENT_TEXT`) VALUES (?,?,?,?)";

        Connection connection = DbConnection.getConnection();

        PreparedStatement ps = null;

        try {

            ps = connection.prepareStatement(query);
            int i = 1;
            ps.setString(i++, object.getComment_user_email());
            ps.setInt(i++, object.getComment_photo_id());
            ps.setTimestamp(i++, object.getComment_created_date());
            ps.setString(i++, object.getComment_text());

            if (ps.executeUpdate() > 0) {
                insert = true;
            }

        } catch (SQLException e) {

            // Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return insert;
    }

    @Override
    public boolean delete(Comment object) {
        Connection connection = DbConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
                
        String query = "DELETE FROM `comments` WHERE `PHOTO_ID`=?";
                   
        boolean delete = false;

        Connection connect = DbConnection.getConnection();

        try {
            ps = connect.prepareStatement(query);
            ps.setInt(1, object.getComment_photo_id());
            int res = ps.executeUpdate();
            ps.close();
            if (res > 0) {
                delete = true;
            }
        } catch (SQLException ex) {

        } finally {
            if (connect == null) {
                delete = false;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return delete;
       

    }

    @Override
    public boolean update(Comment object) {
        return false;

    }

    @Override
    public Comment find(String value) {
        return null;

    }

    public ArrayList<Comment> findAllbyPhoto(int photo_id) {
        String query = "SELECT `USER_EMAIL`, `PHOTO_ID`, `COMMENT_CREATED_DATE`, `COMMENT_TEXT`, `COMMENT_ID` FROM `comments` WHERE `PHOTO_ID` = ?";
        ArrayList<Comment> list = new ArrayList<Comment>();
        Connection connection = DbConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Comment comment = new Comment();
        try {

            ps = connection.prepareStatement(query);

            ps.setInt(1, photo_id);

            rs = ps.executeQuery();

            while (rs.next()) {

                comment.setComment_user_email(rs.getString("USER_EMAIL"));
                comment.setComment_photo_id(rs.getInt("PHOTO_ID"));
                comment.setComment_created_date(rs.getTimestamp("COMMENT_CREATED_DATE"));
                comment.setComment_text(rs.getString("COMMENT_TEXT"));
                comment.setComment_id(rs.getInt("COMMENT_ID"));

                list.add(comment);
                comment = new Comment();

            }

        } catch (SQLException e) {

            // Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }

        }
        return list;

    }
}
