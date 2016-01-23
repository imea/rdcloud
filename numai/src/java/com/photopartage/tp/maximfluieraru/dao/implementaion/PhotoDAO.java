/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao.implementaion;

import com.photopartage.tp.maximfluieraru.dao.DAO;
import com.photopartage.tp.maximfluieraru.dao.DbConnection;
import com.photopartage.tp.maximfluieraru.model.Friend;
import com.photopartage.tp.maximfluieraru.model.Photo;
import com.photopartage.tp.maximfluieraru.model.User;
import com.photopartage.tp.maximfluieraru.model.UserFriend;
import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxim
 */
public class PhotoDAO extends DAO<Photo> {

    @Override
    public boolean insert(Photo photo) {

        Connection connection = DbConnection.getConnection();
        String query = "INSERT INTO `photos`(`USER_EMAIL`, `IMAGE_PATH`, `IMAGE_SIZE`, `DATE_UPLOADED`, `TAG`, `BLOB`) VALUES (?,?,?,?,?,?)";
        boolean insert = false;

        PreparedStatement ps = null;

        try {

            ps = connection.prepareStatement(query);
            int i = 1;
            ps.setString(i++, photo.getUser_email());
            ps.setString(i++, photo.getImage_path());
            ps.setLong(i++, photo.getImage_size());
            ps.setTimestamp(i++, photo.getDate_uploaded());
            ps.setString(i++, photo.getTag());

            BufferedImage bi = photo.getBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ImageIO.write(bi, photo.getImage_path().substring(photo.getImage_path().length() - 3), baos);
            } catch (IOException ex) {
                // Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            InputStream is = new ByteArrayInputStream(baos.toByteArray());

            ps.setBinaryStream(i++, is, photo.getImage_size());

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
    public boolean delete(Photo object) {

        boolean delete = false;
        String query = "DELETE FROM `photos` WHERE `PHOTO_ID`=?";
        Connection connect = DbConnection.getConnection();

        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, object.getPhoto_id());
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
    public boolean update(Photo object) {

        String query = "UPDATE `photos` SET `TAG`=? WHERE `PHOTO_ID` = ?";

        boolean update = false;
        Connection connect = null;

        PreparedStatement ps = null;

        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();
            ps = connect.prepareStatement(query);

            int i = 1;
            ps.setString(i++, object.getTag());
            ps.setInt(i++, object.getPhoto_id());

            int res = ps.executeUpdate();
            ps.close();

            if (res == 1) {
                update = true;
            }
        } catch (SQLException e) {
        } finally {
            if (connect == null) {
                update = false;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }

        return update;
    }

    @Override
    public Photo find(String id) {
        return null;
    }

    public ArrayList<Photo> findAllPublic() {
        ArrayList<Photo> list = new ArrayList<Photo>();
        Connection connection = DbConnection.getConnection();
        String query = "SELECT `PHOTO_ID`, `USER_EMAIL`, `IMAGE_PATH`, `IMAGE_SIZE`, `TAG`, `BLOB` FROM `photos` WHERE `TAG`='public' ORDER BY RAND()";

        try {
            ResultSet rs = connection.createStatement().executeQuery(query);
            Photo photo = new Photo();

            while (rs.next()) {

                photo.setPhoto_id(rs.getInt("PHOTO_ID"));
                photo.setUser_email(rs.getString("USER_EMAIL"));
                photo.setImage_path(rs.getString("IMAGE_PATH"));
                photo.setImage_size(rs.getLong("IMAGE_SIZE"));
                photo.setTag(rs.getString("TAG"));

                BufferedImage buffImg = null;

                try {
                    buffImg = ImageIO.read(rs.getBinaryStream("BLOB"));

                    buffImg.flush();

                } catch (IOException ex) {

                    //Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                photo.setBufferedImage(buffImg);

                list.add(photo);

                photo = new Photo();
            }
        } catch (SQLException ex) {
            return null;
        }finally {
            if (connection == null) {
                 return null;
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }

        return list;
    }

    public ArrayList<Photo> findAllByUser(User user) {
        ArrayList<Photo> list = new ArrayList<Photo>();
        Connection connection = DbConnection.getConnection();

        String query = "SELECT `PHOTO_ID`, `USER_EMAIL`, `IMAGE_PATH`, `IMAGE_SIZE`, `TAG`, `BLOB` FROM `photos` WHERE `USER_EMAIL`= ?";

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            Photo photo = new Photo();

            ps = connection.prepareStatement(query);

            ps.setString(1, user.getUser_email());

            rs = ps.executeQuery();

            while (rs.next()) {

                photo.setPhoto_id(rs.getInt("PHOTO_ID"));
                photo.setUser_email(rs.getString("USER_EMAIL"));
                photo.setImage_path(rs.getString("IMAGE_PATH"));
                photo.setImage_size(rs.getLong("IMAGE_SIZE"));
                photo.setTag(rs.getString("TAG"));
                BufferedImage buffImg = null;
                try {
                    buffImg = ImageIO.read(rs.getBinaryStream("BLOB"));
                } catch (IOException ex) {

                    //Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

                photo.setBufferedImage(buffImg);

                list.add(photo);

                photo = new Photo();
            }
        } catch (SQLException ex) {

            return null;
        }finally {
            if (connection == null) {
                 return null;
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }

        return list;

    }

    public ArrayList<Photo> findAllProtectedByUser(User user) {
        ArrayList<Photo> list = new ArrayList<Photo>();
        Connection connection = DbConnection.getConnection();

        String query = "SELECT `PHOTO_ID`, `USER_EMAIL`, `IMAGE_PATH`, `IMAGE_SIZE`, `TAG`, `BLOB` FROM `photos` WHERE `USER_EMAIL`= ? AND TAG='protected'";

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            Photo photo = new Photo();
            ps = connection.prepareStatement(query);

            ArrayList<UserFriend> listUf = user.getUser_friends();

            for (UserFriend uf : listUf) {
                
                ps.setString(1, uf.getFriend());

                rs = ps.executeQuery();

                while (rs.next()) {

                    photo.setPhoto_id(rs.getInt("PHOTO_ID"));
                    photo.setUser_email(rs.getString("USER_EMAIL"));
                    photo.setImage_path(rs.getString("IMAGE_PATH"));
                    photo.setImage_size(rs.getLong("IMAGE_SIZE"));
                    photo.setTag(rs.getString("TAG"));
                    BufferedImage buffImg = null;
                    try {
                        buffImg = ImageIO.read(rs.getBinaryStream("BLOB"));
                    } catch (IOException ex) {
                       //Logger.getLogger(PhotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    photo.setBufferedImage(buffImg);
                    
                    list.add(photo);      
                    photo = new Photo();
                }

            }
        } catch (Exception ex) {
           
        } finally {

            if (connection == null) {
                 return null;
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

}
