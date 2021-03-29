/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Complaint;
import IServices.IComplaint;
import Utils.DataBaseConnection;
import java.sql.Connection;
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
public class ComplaintService implements IComplaint {

    ObservableList<Complaint> ol = FXCollections.observableArrayList();
    List<Complaint> list = new ArrayList<>();

    Connection cnx;

    public ComplaintService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public void AddComplaint(Complaint c) {

        try {
            String requete = "INSERT INTO complaint (object,description,status) VALUES(?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getObject());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getStatus());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//@Override
//        public void UpdateComplaint(Complaint updatedComplaint) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//
    @Override
    public void DeleteComplaint(Complaint deletedComplaint) {

        try {
            String query = "DELETE FROM `complaint` WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, deletedComplaint.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Complaint getComplaint(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Complaint> getComplaints() {

        try {
            String query = "select * from complaint";
            Statement ps = cnx.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setObject(rs.getString("object"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));

                ol.addAll(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ol;
    }

    @Override
    public List<Complaint> getListComplaint() {
        List<Complaint> myList = new ArrayList<>();
        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery("SELECT * from complaint ");
            while (rs.next()) {
                String object = rs.getString("object");
                String description = rs.getString("description");
                String status = rs.getString("status");

                Complaint c = new Complaint(object, description, status);
                c.setId(rs.getInt("id"));
                myList.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    @Override
    public void UpdateComplaint(int id, Complaint UpdatedComplaint) {
        {
            {

                try {
                    String query = "UPDATE complaint SET object=?,description=?,status=? WHERE id=?";
                    PreparedStatement ps = cnx.prepareStatement(query);
                    ps.setString(1, UpdatedComplaint.getObject());
                    ps.setString(2, UpdatedComplaint.getDescription());
                    ps.setString(3, UpdatedComplaint.getStatus());
                    ps.setInt(4, id);
                    ps.executeUpdate();
                    System.out.println("updated");

                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public int GetIdComplaint(Complaint c
    ) {
        int id = 10;
        try {
            String query = "SELECT * FROM complaint WHERE and object=? and description=? and status=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, c.getObject());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getStatus());
            ps.setInt(4, c.getId());
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public int countComplaints() {
        int count = 4;
        try {
            String query = "Select COUNT (*) as nb FROM complaint ";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("nb");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
}
