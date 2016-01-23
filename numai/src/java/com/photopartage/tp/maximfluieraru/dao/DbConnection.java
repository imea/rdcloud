
package com.photopartage.tp.maximfluieraru.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Maxim
 */
public class DbConnection {
    private static DbConnection instance = new DbConnection();
    public static final String URL = "jdbc:mysql://localhost/photopartage";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
     
   /**
    * 
    */
    private DbConnection() {
        try {
            Class.forName(DRIVER_CLASS);
            
        } catch (ClassNotFoundException e) {
           
            e.printStackTrace();
            
        }
    }
     
    /**
     * 
     * @return 
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur: Impossible d'etablir une connexion avec la base de données");
        }
        return connection;
    }   
     
    /**
     * 
     * @return 
     */
    public static Connection getConnection() {
 
        return instance.createConnection();
        
    }
}
