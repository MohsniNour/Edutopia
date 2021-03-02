/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import IServices.IUser;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Sabrina
 */
public class UserService implements IUser {

    Connection cnx;

    public UserService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public void addUser(User p) {
        try {
            String requete = "INSERT INTO user (role,name,last_name,cin,email,phone_number,birth_date) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getRole());
            pst.setString(2, p.getName());
            pst.setString(3, p.getLast_name());
            pst.setInt(4, p.getCin());
            pst.setString(5, p.getEmail());
            pst.setInt(6, p.getPhone_number());
            pst.setDate(7, (Date) p.getBirth_date());
            pst.executeUpdate();

           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
