/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Course;
import IServices.ICourse;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class CourseService implements ICourse{
    
    Connection conn;
    ObservableList<Course> oL = FXCollections.observableArrayList();
    ArrayList<Course> la = new ArrayList();

    public CourseService() {
        conn = DataBaseConnection.getInstance().getConnection();;
    }

    
    @Override
    public ArrayList<Course> getCoursesList() {
        try {
            String query = "select * from course";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                la.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return la;
    }
    
}
