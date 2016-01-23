/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao.implementaion;

import com.photopartage.tp.maximfluieraru.dao.DAO;

import com.photopartage.tp.maximfluieraru.model.UserFriend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Maxim
 */
public class UserFriendDAO extends DAO<UserFriend> {

    @Override
    public boolean insert(UserFriend object) {

        boolean insert = false;
        String query = "INSERT INTO `users_friends` (`FRIEND_EMAIL`, `USER_EMAIL`) VALUES (?,?)";

        Connection connect = null;
        connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, object.getFriend());
            ps.setString(2, object.getUser());
            if (ps.executeUpdate() > 0) {
                insert = true;
            }

        } catch (SQLException ex) {

            // Logger.getLogger(UserFriendDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean delete(UserFriend object) {
        boolean delete = false;
        String userEmail = object.getUser();
        String friendEmail = object.getFriend();
        String query = "DELETE FROM `users_friends` WHERE `FRIEND_EMAIL`=? AND `USER_EMAIL`=?";

        Connection connect = null;

        PreparedStatement ps = null;
        
        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);

            ps.setString(1, friendEmail);
            ps.setString(2, userEmail);

            if (ps.executeUpdate() > 0) {
                delete = true;

            }
        } catch (SQLException ex) {
           
        } finally {
            if (connect == null) {
                return delete;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return delete;
    }

    @Override
    public boolean update(UserFriend object) {
        return false;
    }

    @Override
    public UserFriend find(String value) {
              
        return null;
    }

    public boolean find(UserFriend object) {
        boolean find = false;
        String userEmail = object.getUser();
        String friendEmail = object.getFriend();
        String query = "SELECT * FROM `users_friends` WHERE `FRIEND_EMAIL`=? AND `USER_EMAIL`=?";

        Connection connect = null;

        PreparedStatement ps = null;
        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);

            ps.setString(1, friendEmail);
            ps.setString(2, userEmail);

            if (ps.executeQuery().next()) {
                find = true;

            }
        } catch (SQLException ex) {
            // Logger.getLogger(UserFriendDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<UserFriend> findAllFriends(String user_email) {
        boolean find = false;
        ArrayList<UserFriend> list = new ArrayList<UserFriend>();
        String query = "SELECT DISTINCT `USER_EMAIL` FROM `users_friends` WHERE `USER_EMAIL` IN"
                + " (SELECT `FRIEND_EMAIL` FROM `users_friends` WHERE `USER_EMAIL` = ?)";

        Connection connect = null;

        PreparedStatement ps = null;
        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, user_email);
            UserFriend uf = null;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uf = new UserFriend(user_email, rs.getString("USER_EMAIL"));
                list.add(uf);
            }

        } catch (SQLException ex) {

        } finally {
            if (connect == null) {
                return list;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return list;
    }
    
    public ArrayList<UserFriend> findAllUnconfirmedInvitation(String user_email) {
        boolean find = false;
        ArrayList<UserFriend> list = new ArrayList<UserFriend>();
        
        String query = "SELECT `FRIEND_EMAIL` FROM `users_friends` WHERE `USER_EMAIL` = ? "
                + "AND `FRIEND_EMAIL` NOT IN (SELECT `USER_EMAIL` FROM `users_friends` WHERE `USER_EMAIL` IN "
                + "(SELECT `FRIEND_EMAIL` FROM `users_friends` WHERE `USER_EMAIL` = ?))";

        Connection connect = null;

        PreparedStatement ps = null;
        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, user_email);
            ps.setString(2, user_email);
            UserFriend uf = null;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uf = new UserFriend(user_email, rs.getString("FRIEND_EMAIL"));
                list.add(uf);
            }

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "findAllUnconfirmedInvitation =" + ex.getMessage());

        } finally {
            if (connect == null) {
                return list;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return list;
    }
    
    public ArrayList<UserFriend> findAllRecivedInvitation(String user_email) {
        boolean find = false;
        ArrayList<UserFriend> list = new ArrayList<UserFriend>();
        
        String query = "SELECT DISTINCT `USER_EMAIL` FROM `users_friends` "
                + "WHERE `USER_EMAIL` "
                + "IN (SELECT `USER_EMAIL` FROM `users_friends` WHERE `FRIEND_EMAIL` = ?)"
                + " AND `USER_EMAIL` "
                + "NOT IN (SELECT `FRIEND_EMAIL` FROM `users_friends` WHERE `USER_EMAIL` = ?)";

        Connection connect = null;

        PreparedStatement ps = null;
        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, user_email);
            ps.setString(2, user_email);
            UserFriend uf = null;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uf = new UserFriend(user_email, rs.getString("USER_EMAIL"));
                list.add(uf);
            }

        } catch (SQLException ex) {
 JOptionPane.showConfirmDialog(null, "findAllRecivedInvitation =" + ex.getMessage());

        } finally {
            if (connect == null) {
                return list;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }
        return list;
    }


}
