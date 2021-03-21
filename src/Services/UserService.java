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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void addUser(User u) {
        try {
            String requete = "INSERT INTO user (role,name,last_name,cin,email,phone_number,birth_date) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, u.getRole());
            pst.setString(2, u.getName());
            pst.setString(3, u.getLast_name());
            pst.setInt(4, u.getCin());
            pst.setString(5, u.getEmail());
            pst.setInt(6, u.getPhone_number());
            pst.setDate(7, (Date) u.getBirth_date());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public User getUser(int id) {
        User p = new User();
        try {

            Statement pst = cnx.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * from user WHERE id=" + id + "");

            while (rs.next()) {
                int cin = rs.getInt("cin");
                String first_name = rs.getString("name");
                String last_name = rs.getString("last_name");
                String role = rs.getString("role");
                String email = rs.getString("email");
                p.setId(id);
                p.setCin(cin);
                p.setName(first_name);
                p.setLast_name(last_name);
                p.setRole(role);
                p.setEmail(email);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

}
