/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Student;
import Entities.Teacher;
import Entities.User;
import Entities.classe;
import GUI.LoginController;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sabrina
 */
public class UserService implements IUser {

    User currentUser = LoginController.CurrentUser;
    ObservableList<User> ol = FXCollections.observableArrayList();
    List<User> list = new ArrayList<>();
    Connection cnx;

    public UserService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    public Boolean Authentifier(String u, String p) throws SQLException {
        String req = "SELECT * FROM `user` WHERE email =\'" + u + "\' and password=\'" + p + "\'";
        User user = new User();

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {

                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setBirth_date(rs.getDate("birth_date"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public User AssignCurrentUser(String u, String p) throws SQLException {
        User user = new User();
        String req = "SELECT * FROM `user` WHERE email =\'" + u + "\' and password=\'" + p + "\'";
        System.out.println(req);

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if (rs != null) {
                while (rs.next()) {

                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setRole(rs.getString("role"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setCin(rs.getInt("cin"));
                    user.setPhone_number(rs.getInt("phone_number"));
                    user.setBirth_date(rs.getDate("birth_date"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Student AssignCurrentStudent(String u, String p) {
        Student std = new Student();
        ClasseService cs = new ClasseService();
        String req = "SELECT * FROM `user` WHERE email =\'" + u + "\' and password=\'" + p + "\'";
        System.out.println(req);

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {

                    std.setId(rs.getInt("id"));
                    std.setName(rs.getString("name"));
                    std.setLast_name(rs.getString("last_name"));
                    std.setRole(rs.getString("role"));
                    std.setCin(rs.getInt("cin"));
                    std.setPhone_number(rs.getInt("phone_number"));
                    std.setBirth_date(rs.getDate("birth_date"));
                    std.setEmail(rs.getString("email"));
                    std.setPassword(rs.getString("password"));
                    classe c = cs.getClasseId(rs.getString("classe"));
                    System.out.println(c.getId());
                    std.setClasse(c);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return std;
    }

    public Teacher AssignCurrentTeahcer(String u, String p) {
        Teacher std = new Teacher();

        String req = "SELECT * FROM `user` WHERE email =\'" + u + "\' and password=\'" + p + "\'";

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {

                    std.setId(rs.getInt("id"));
                    std.setName(rs.getString("name"));
                    std.setLastname(rs.getString("last_name"));
                    std.setRole(rs.getString("role"));
                    std.setCin(rs.getInt("cin"));
                    std.setPhone_number(rs.getInt("phone_number"));
                    std.setBirth_date(rs.getDate("birth_date"));
                    std.setEmail(rs.getString("email"));
                    std.setPassword(rs.getString("password"));
                    std.setDepId(rs.getInt("depId"));
                    std.setStatus(rs.getString("subjects"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return std;
    }

    public void addUser(User u) {
        try {
            String requete = "INSERT INTO user (cin,name,last_name,birth_date,phone_number,email,password,role,created_by) VALUES(?,?,?,?,?,?,?,'Admin',?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, u.getCin());
            pst.setString(2, u.getName());
            pst.setString(3, u.getLast_name());
            pst.setDate(4, (Date) u.getBirth_date());
            pst.setInt(5, u.getPhone_number());
            pst.setString(6, u.getEmail());
            pst.setString(7, u.getPassword());
            pst.setInt(8, currentUser.getId());

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
                String password = rs.getString("password");
                String email = rs.getString("email");
                p.setId(id);
                p.setCin(cin);
                p.setName(first_name);
                p.setLast_name(last_name);
                p.setPassword(password);
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
                u.setCin(rs.getInt("cin"));
                u.setName(rs.getString("name"));
                u.setLast_name(rs.getString("last_name"));
                u.setBirth_date(rs.getDate("birth_date"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
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
            ResultSet rs = pst.executeQuery("SELECT * from user where role = 'Admin' ");
            while (rs.next()) {
                String password = rs.getString("password");
                String first_name = rs.getString("name");
                String last_name = rs.getString("last_name");
                int cin = rs.getInt("cin");
                String email = rs.getString("email");
                int phone = rs.getInt("phone_number");
                Date birth_date = rs.getDate("birth_date");
                User u = new User(cin, first_name, last_name, birth_date, phone, email, password, "Admin", currentUser.getId());
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
                String query = "UPDATE user SET cin=?,name=?,last_name=?,birth_date=?,phone_number=?,email=?,password=? WHERE id=?";
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.setInt(1, UpdatedUser.getCin());
                ps.setString(2, UpdatedUser.getName());
                ps.setString(3, UpdatedUser.getLast_name());
                ps.setDate(4, (Date) UpdatedUser.getBirth_date());
                ps.setInt(5, UpdatedUser.getPhone_number());
                ps.setString(6, UpdatedUser.getEmail());
                ps.setString(7, UpdatedUser.getPassword());
                ps.setInt(8, id);
                ps.executeUpdate();
                System.out.println("added");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public List<User> SearchUser(String text) {
        List<User> myList = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * from user" + " WHERE cin LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE name LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE last_name LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE email LIKE '%" + text + "%'";
            sql += " UNION SELECT * from user" + " WHERE password LIKE '%" + text + "%'";
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
