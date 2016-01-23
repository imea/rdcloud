/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao.implementaion;

import com.photopartage.tp.maximfluieraru.dao.DAO;
import com.photopartage.tp.maximfluieraru.dao.HelperContext;
import com.photopartage.tp.maximfluieraru.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

/**
 *
 * @author Maxim
 */
public class UserDAO extends DAO<User> {

    @Override
    public boolean insert(User user) {
        boolean insert = false;
        Connection connect = null;
        connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
        PreparedStatement ps = null;
        try {

            String query = "INSERT INTO `users`(`USER_EMAIL`, `USER_PASSWORD`, `USER_FIRST_NAME`, `USER_LAST_NAME`, `USER_CREATED_DATE`) VALUES (?,?,?,?,?)";
            ps = connect.prepareStatement(query);
            ps.setString(1, user.getUser_email());
            ps.setString(2, user.getUser_password());
            ps.setString(3, user.getUser_first_name());
            ps.setString(4, user.getUser_last_name());
            ps.setTimestamp(5, HelperContext.toMySqlTimestamp(user.getUser_created_date()));

            if (ps.executeUpdate()>0){
                insert = true;
            }
            
        } catch (SQLException ex) {
            insert = true;
            //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connect == null) {
                insert = false;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return insert;
    }

    @Override
    public boolean delete(User object) {
        return false;
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public User find(String id) {

        String id_password[] = id.split(":");

        User user = new User(id_password[0], id_password[1]);
        Connection connect = null;

        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            Statement statement = connect.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user_email='" + user.getUser_email() + "' AND user_password='" + user.getUser_password() + "'");

            if (rs.first()) {

                user.setUser_first_name(rs.getString("user_first_name"));
                user.setUser_last_name(rs.getString("user_last_name"));
                try {
                    user.setUser_created_date(HelperContext.toUserDate(rs.getString("user_created_date")));

                } catch (ParseException ex) {

                    //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                user.setUser_password("");
            }

        } catch (SQLException e) {

        } finally {
            if (connect == null) {
                return user;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }

        return user;

    }

    public User find(User user) {

        Connection connect = null;

        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            Statement statement = connect.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user_email='" + user.getUser_email() + "' AND user_password='" + user.getUser_password() + "'");

            if (rs.first()) {

                user.setUser_first_name(rs.getString("user_first_name"));
                user.setUser_last_name(rs.getString("user_last_name"));

                try {
                    user.setUser_created_date(HelperContext.toUserDate(rs.getString("user_created_date")));

                } catch (ParseException ex) {
                    user = null;
                    //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                user = null;
            }

        } catch (SQLException e) {

        } finally {
            if (connect == null) {
                return user;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }

        return user;

    }

}
