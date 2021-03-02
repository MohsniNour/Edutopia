/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import IServices.IActivity;
import Utils.DataBaseConnection;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
    public void add(Activity activity) {
         try {
            String requete = "INSERT INTO activity (name,deadline,work_todo,id_course,created_by,ceated_date) VALUES(?,?,?,?,?,?)";

            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, activity.getName());
            pst.setDate(2, (Date) activity.getDeadline());
            pst.setString(6, activity.getId_Course());
            pst.setDate(7, (Date) activity.getCeated_date());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Activity activity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(int id, Activity new_activity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void details(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
