/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Co_Studying;
import Entities.User;
import IServices.ICo_Studying;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import GUI.CoStudyingAddController;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rayen
 */
public class Co_StudyingService implements ICo_Studying {

    ObservableList<Co_Studying> obv = FXCollections.observableArrayList();
    List<Co_Studying> list = new ArrayList<>();
    Connection cnx;

    public Co_StudyingService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public void addCostudying(Co_Studying p) {
        try {
            String requete = "INSERT INTO co_studying (description,file,type,niveau) VALUES(?,?,?,?)";
            String s = CoStudyingAddController.s;
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getDescription());
            pst.setString(2, p.getFile());
            pst.setString(3, p.getType());
            pst.setString(4, p.getLevel());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void deleteCostudying(Co_Studying p) {
        try {
            String query = "DELETE FROM co_studying WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            System.out.println("removed succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void editCostudying(int id, String object, Object obj) {
        try {
            String requete = "UPDATE co_studying SET ? = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, object);
            pst.setObject(2, obj);
            pst.setInt(3, id);
            String ch = pst.toString().replaceFirst("\'", "");
            String ch2 = ch.replaceFirst("\'", "");
            int pos = ch2.indexOf("UPDATE");
            String ch3 = ch2.substring(pos, ch2.length());
            pst = cnx.prepareStatement(ch3);
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("Co-Studying modifié avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Co_Studying> getCostudyings() {
        try {
            String query = "select * from co_studying";
            Statement ps = cnx.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Co_Studying d = new Co_Studying();
                User u = new User();
                UserService us = new UserService();
                u.setId(rs.getInt("id_student"));
                User u1 = us.getUser(u.getId());
                u.setId(rs.getInt("id"));
                d.setId_student(u1);
                d.setType(rs.getString("type"));
                d.setDescription(rs.getString("description"));
                /*d.setFile((File) rs.getBlob("file"));*/
                d.setLevel(rs.getString("niveau"));
                d.setRating(rs.getInt("rating"));

                obv.addAll(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return obv;
    }

    @Override
    public List<Co_Studying> getListCo_studying() {
        List<Co_Studying> myList = new ArrayList<>();
        try {
           
            Statement pst = cnx.createStatement();
            ResultSet rs;
            rs = pst.executeQuery("SELECT *FROM co_studying");
            while (rs.next()) {
                String type = rs.getString("type");
                String description = rs.getString("description");
                String level = rs.getString("niveau");
                int rating = rs.getInt("rating");
                String file = rs.getString("file");
                User u = new User();
                UserService us = new UserService();
                u.setId(rs.getInt("id_student"));
                User u1 = us.getUser(u.getId());

                u.setId(rs.getInt("id"));
                Co_Studying p = new Co_Studying(description, type, file, level, u1, rating);
                p.setId(rs.getInt("id"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    @Override
    public Co_Studying FindCo_Studying(int id) {

        Co_Studying p = new Co_Studying();
        try {

            Statement pst = cnx.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * FROM co_studying WHERE id=" + id + "");

            while (rs.next()) {

                User u = new User();
                UserService us = new UserService();
                u.setId(rs.getInt("id_student"));
                User u1 = us.getUser(u.getId());

                String type = rs.getString("type");
                String description = rs.getString("description");
                String level = rs.getString("niveau");
                String file = rs.getString("file");
                int rating = rs.getInt("rating");
                p.setId_student(u1);
                p.setId(id);
                p.setType(type);
                p.setDescription(description);
                p.setLevel(level);
                p.setRating(rating);
                p.setFile(file);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;

    }

    public List<Co_Studying> consulterFiltered(String toCompare) {

        List<Co_Studying> myList = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * from co_studying" + " WHERE type LIKE '%" + toCompare + "%'";
            sql += " UNION SELECT * from co_studying" + " WHERE niveau LIKE '%" + toCompare + "%'";
            sql += " UNION SELECT * from co_studying" + " WHERE description LIKE '%" + toCompare + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                if (rs.getInt("id") != 0) {
                    Co_Studying p = FindCo_Studying(rs.getInt("id"));
                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

    public List<Co_Studying> consulterOrdered() {

        List<Co_Studying> myList = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * from co_studying order by rating desc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("id") != 0) {
                    Co_Studying p = FindCo_Studying(rs.getInt("id"));
                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;

    }

}
