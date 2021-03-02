/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DataBaseConnection {
    
    private Connection connection;
    private static DataBaseConnection database;

    public DataBaseConnection() {
         try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/edutopia?useSSL=false", "root", "");
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public static DataBaseConnection getInstance() {
        if (database == null) {
            database = new DataBaseConnection();
        }
        return database;
    }

    
    
    
    
}
