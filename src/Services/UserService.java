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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sabrina
 */
public class UserService implements IUser {

    ObservableList<User> ol = FXCollections.observableArrayList();
    List<User> list = new ArrayList<>();

    Connection cnx;

    public UserService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public void addUser(User u) {
        try {
            String requete = "INSERT INTO user (cin,first_name,last_name,birth_date,phone,email,role) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, u.getFirst_name());
            pst.setString(2, u.getLast_name());
            pst.setString(3, u.getCin());
            pst.setDate(4, (Date) u.getBirth_date());
            pst.setString(5, u.getPhone_number());
            pst.setString(6, u.getEmail());
            pst.setString(7, u.getRole());

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
                String cin = rs.getString("cin");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String role = rs.getString("role");
                String email = rs.getString("email");
                p.setId(id);
                p.setCin(cin);
                p.setFirst_name(first_name);
                p.setLast_name(last_name);
                p.setRole(role);
                p.setEmail(email);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    @Override
    public ObservableList<User> getUsers() {

        try {
            String query = "select * from user";
            Statement ps = cnx.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCin(rs.getString("cin"));
                u.setFirst_name(rs.getString("first_name"));
                u.setLast_name(rs.getString("last_name"));
                u.setBirth_date(rs.getDate("birth_date"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));

                ol.addAll(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ol;
    }

    @Override
    public void DeleteUser(User deletedUser) {
        try {
            String query = "DELETE FROM `user` WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, deletedUser.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getListUser() {
        List<User> myList = new ArrayList<>();
        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery("SELECT * from user ");
            while (rs.next()) {
                String role = rs.getString("role");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String cin = rs.getString("cin");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date birth_date = rs.getDate("birth_date");
                User u = new User(cin, first_name, first_name, birth_date, phone, email, role);
                u.setId(rs.getInt("id"));
                myList.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    @Override
    public void UpdateUser(int id, User UpdatedUser) {
        {

            try {
                String query = "UPDATE user SET cin=?,first_name=?,last_name=?,birth_date=?,phone=?,email=?,role=? WHERE id=?";
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.setString(1, UpdatedUser.getCin());
                ps.setString(2, UpdatedUser.getFirst_name());
                ps.setString(3, UpdatedUser.getLast_name());
                ps.setDate(4, (Date) UpdatedUser.getBirth_date());
                ps.setString(5, UpdatedUser.getPhone_number());
                ps.setString(6, UpdatedUser.getEmail());
                ps.setString(7, UpdatedUser.getRole());
                ps.setInt(8, id);
                ps.executeUpdate();
                System.out.println("added");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

//    @Override
//    public void UpdateUser(int id, String object, Object obj) {
//        try {
//            String requete = "UPDATE user SET ? = ? WHERE id = ?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setString(1, object);
//            pst.setObject(2, obj);
//            pst.setInt(3, id);
//            String ch = pst.toString().replaceFirst("\'", "");
//            String ch2 = ch.replaceFirst("\'", "");
//            int pos = ch2.indexOf("UPDATE");
//            String ch3 = ch2.substring(pos, ch2.length());
//            pst = cnx.prepareStatement(ch3);
//            System.out.println(pst);
//            pst.executeUpdate();
//            System.out.println("User updated");
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
    public List<User> SearchUser(String text) {
        List<User> myList = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * from user" + " WHERE cin LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE first_name LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE last_name LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE email LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE role LIKE '%" + text + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                if (rs.getInt("id") != 0) {
                    User p = getUser(rs.getInt("id"));
                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }
}
