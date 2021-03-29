/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import Entities.Work_done;
import IServices.IWork_done;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class Work_doneService implements IWork_done {

    Connection conn;
    ObservableList<Work_done> oL = FXCollections.observableArrayList();
    ArrayList<Work_done> la = new ArrayList();

    public Work_doneService() {
        conn = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public int getId(Work_done work_done) {
        int id = 0;
        try {
            String query = "SELECT * FROM `work_done` WHERE work_file=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, work_done.getWork_file());
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

    @Override
    public void add(Work_done work_done) {
        try {
            String requete = "INSERT INTO work_done (work_file,status,id_activity,uploaded_by,uploaded_date) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, work_done.getWork_file());
            pst.setString(2, work_done.getStatus());
            pst.setInt(3, work_done.getId_activity());
            pst.setString(4, work_done.getUploaded_by());
            pst.setDate(5, work_done.getUploaded_date());
            pst.executeUpdate();
            System.out.println("Added succesfully");
        } catch (SQLException excep) {
            System.err.println(excep.getMessage());
        }
    }

    @Override
    public void remove(int id, String path) {
        try {
            String req = "UPDATE `work_done` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, "sabrine");
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3, "Archived");
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("archived succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void activate(int id, String path) {
        try {
            String req = "UPDATE `work_done` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, "sabrine");
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3, "available");
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("archived succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Work_done details(int id) {
        Work_done work_done = new Work_done();
        boolean check = false;
        try {
            String query = "select * from work_done where id= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
                work_done.setId(rs.getInt("id"));
                work_done.setWork_file(rs.getString("work_file"));
                work_done.setScore(rs.getInt("score"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (check == true) {
            return work_done;
        } else {
            return null;
        }
    }

    @Override
    public void updateScore(int id, int score) {
        try {
            String query = "UPDATE `work_done` SET `score`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, score);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Updated score succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Work_done> getWorkDoneListByIdActivity(int id_activity) {
        try {
            String query = "select * from work_done where id_activity=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_activity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Work_done work = new Work_done();
                work.setId(rs.getInt("id"));
                work.setWork_file(rs.getString("work_file"));
                work.setStatus(rs.getString("status"));
                work.setId_activity(rs.getInt("id_activity"));
                work.setScore(rs.getInt("score"));
                la.add(work);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return la;
    }

    @Override
    public List trierWorkDoneID() {
       ArrayList<Work_done> listWorks = new ArrayList<>();
        try {

            String query = "select * from work_done ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Work_done work = new Work_done();
                work.setId(rs.getInt("id"));
                work.setWork_file(rs.getString("work_file"));
                work.setStatus(rs.getString("status"));
                work.setId_activity(rs.getInt("id_activity"));
                work.setScore(rs.getInt("score"));
                listWorks.add(work);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listWorks;
    }
    

}
