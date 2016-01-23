/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao.implementaion;

import com.photopartage.tp.maximfluieraru.dao.DAO;
import com.photopartage.tp.maximfluieraru.model.Page;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxim
 */
public class PageDAO extends DAO<Page> {

    @Override
    public boolean insert(Page object) {
        return false;
    }

    @Override
    public boolean delete(Page object) {
       
        return false;
    }

    @Override
    public boolean update(Page object) {
        return false;
    }

    @Override
    public Page find(String value) {
        Page page = new Page();
        Connection connect = null;
        PreparedStatement ps = null;
        String query = "SELECT `ID`, `SECTION`, `TITLE` FROM `pages` WHERE `ID`= ?";
        ResultSet rs = null;
        try {
            connect = com.photopartage.tp.maximfluieraru.dao.DbConnection.getConnection();

            ps = connect.prepareStatement(query);

            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {

                page.setTitle(rs.getString("TITLE"));
                page.setSection(rs.getString("SECTION"));
                page.setPage_id(rs.getString("ID"));

            } else {
                page.setPage_id("-1");
                page.setTitle("ERROR 404");
                page.setSection("error_404");
            }

        } catch (SQLException e) {
            page.setPage_id("-1");
            page.setTitle("ERROR 404");
            page.setSection("error_404");

        } finally {
            if (connect == null) {
                return page;
            }
            try {
                connect.close();
            } catch (Exception e) {
            }
        }

        return page;
    }

}
