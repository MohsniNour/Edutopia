/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import IServices.IActivity;
import Utils.DataBaseConnection;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ActivityService implements IActivity{
    
    Connection conn;

    public ActivityService() {
        conn = DataBaseConnection.getInstance().getConnection();
    }
    
    @Override
    public String getId(Activity act) {
        String id="";
        try {
            String query="SELECT * FROM `activity` WHERE name=? and id_Course=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, act.getName());
            ps.setString(2, act.getId_Course());
            ResultSet rs;
            rs = ps.executeQuery();
            while(rs.next()) {
                id=rs.getString("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
    
    @Override
    public void add(Activity activity) {
         try {
            String requete = "INSERT INTO activity (name,deadline,work_todo,id_course,status,created_by,ceated_date) VALUES(?,?,?,?,?,?,?)";
            FileReader reader = new FileReader(activity.getWork_todo());
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, activity.getName());
            pst.setDate(2, (Date) activity.getDeadline());
            pst.setCharacterStream(3, reader);
            pst.setString(4, activity.getId_Course());
            pst.setString(5, activity.getStatus());
            pst.setString(6, activity.getCreated_by());
            pst.setDate(7, (Date) activity.getCreated_date());
            pst.executeUpdate();
            System.out.println("Added succesfully");
        } catch (SQLException excep) {
            System.err.println(excep.getMessage());
        } catch (FileNotFoundException excep) {
            excep.printStackTrace();
        }

    }

    @Override
    public void delete(String id) {
        try {
            String req ="UPDATE `activity` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,"nour");
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3, "Archived");
            ps.setString(4, id);
            ps.executeUpdate();
            System.out.println("removed succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(String id, Activity new_activity) {
        try {
            String query="UPDATE `activity` SET `name`=?,`deadline`=?,`work_todo`=?,`last_updated_by`=?,`last_updated_date`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            FileReader reader = new FileReader(new_activity.getWork_todo());
            ps.setString(1, new_activity.getName());
            ps.setDate(2, (Date) new_activity.getDeadline());
            ps.setCharacterStream(3, reader);
            ps.setString(4, new_activity.getLast_updated_by());
            ps.setDate(5, (Date)new_activity.getLast_updated_Date());
            ps.setString(6, id);
            ps.executeUpdate();
            System.out.println("Updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ActivityService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public Activity details(String id) {
        Activity act = new Activity();
        boolean check = false;
        try {
            String query = "select * from activity where id= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    check = true;
                    act.setId(rs.getString("id"));
                    act.setName(rs.getString("name"));
                    act.setDeadline(rs.getDate("deadline"));
                    act.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
        if (check == true) {
                return act;
            }
            else
                return null;
    }

    @Override
    public List<Activity> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
