/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao.implementaion;

import com.photopartage.tp.maximfluieraru.dao.DAO;
import com.photopartage.tp.maximfluieraru.model.Friend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxim
 */
public class FriendDAO extends DAO<Friend> {

    @Override
    public boolean insert(Friend object) {
        boolean insert = false;
        String queryFriend = "INSERT INTO `friends`(`FRIEND_EMAIL`) VALUES (?)";

        Connection connect = null;
        PreparedStatement ps = null;

        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(queryFriend);

            ps.setString(1, object.getFriend_email());

            if (ps.executeUpdate() > 0) {
                insert = true;
            }

        } catch (SQLException ex) {

            //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect == null) {
                return false;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return insert;
    }

    @Override
    public boolean delete(Friend object) {
        boolean delete = false;

        String query = "DELETE FROM `friends` WHERE `FRIEND_EMAIL`=?";
        Connection connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();

        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(query);

            ps.setString(1, object.getFriend_email());

            if (ps.executeUpdate() > 0) {
                delete = true;
            }

        } catch (SQLException ex) {

            delete = false;

        } finally {

            if (connect == null) {
                return false;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }

        return delete;
    }

    @Override
    public boolean update(Friend object) {
        return false;
    }

    @Override
    public Friend find(String value) {

        return null;
    }

    public boolean find(Friend object) {
        boolean find = false;
        String query = "SELECT * FROM `friends` WHERE `FRIEND_EMAIL` =?";
        Connection connect = null;

        PreparedStatement ps = null;

        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);

            ps.setString(1, object.getFriend_email());

            if (ps.executeQuery().next()) {
                find = true;
            }

        } catch (SQLException ex) {
            find = false;
            //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect == null) {
                return false;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }

        return find;
    }
}
