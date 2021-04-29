/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import Entities.Forum;
import IServices.IForum;
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
public class ForumService implements IForum{
    
    Connection conn;
    ObservableList<Forum> oL = FXCollections.observableArrayList();

    public ForumService() {
        conn = DataBaseConnection.getInstance().getConnection();
    }
     
    @Override
    public int getId(Forum forum) {
        int id=0;
        try {
            String query="SELECT * FROM `forum` WHERE subject=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, forum.getSubject());
            ResultSet rs;
            rs = ps.executeQuery();
            while(rs.next()) {
                id=rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
    
    public int getIdCourseByIdForum(int id_forum) {
        int id=0;
        try {
            String query="SELECT id_course FROM `forum` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_forum);
            ResultSet rs;
            rs = ps.executeQuery();
            while(rs.next()) {
                id=rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public void add(Forum forum) {
        try {
            String requete = "INSERT INTO forum (subject,status,id_course,created_by,created_date) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, forum.getSubject());
            pst.setString(2, forum.getStatus());
            pst.setInt(3, forum.getId_course());
            pst.setInt(4, forum.getCreated_by());
            pst.setDate(5, (Date) forum.getCreated_date());
            pst.executeUpdate();
            System.out.println("Added succesfully");
        } catch (SQLException excep) {
            System.err.println(excep.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        try {
            String req ="UPDATE `forum` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,"nour");
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3, "Archived");
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("removed succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void activate(int id) {
        try {
            String req ="UPDATE `forum` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,"amine");
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3, "Available");
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("activated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(int id, Forum new_forum) {
        try {
            String query="UPDATE `forum` SET `subject`=?,`id_course`=?,`last_updated_by`=?,`last_updated_date`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, new_forum.getSubject());
            ps.setInt(2, new_forum.getId_course());
            ps.setInt(3, new_forum.getLast_updated_by());
            ps.setDate(4, (Date)new_forum.getLast_updated_Date());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }

    @Override
    public Forum details(int id) {
        Forum frm = new Forum();
        boolean check = false;
        try {
            String query = "select * from forum where id= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    check = true;
                    frm.setId(rs.getInt("id"));
                    frm.setSubject(rs.getString("subject"));
                    frm.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
        if (check == true) {
                return frm;
            }
            else
                return null;
    }

    @Override
    public List<Forum> listAvailable() {
        List<Forum> frms = new ArrayList();
        try {
            String query = "select subject,status from forum";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Archived".equals(rs.getString("status"))) {
                    Forum frm = new Forum();
                    frm.setSubject(rs.getString("subject"));
                    frm.setStatus(rs.getString("status"));
                    frms.add(frm);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return frms;
    }

    @Override
    public List<Forum> listArchived() {
        List<Forum> frms = new ArrayList();
        try {
            String query = "select subject,status from forum";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Available".equals(rs.getString("status"))) {
                    Forum frm = new Forum();
                    frm.setSubject(rs.getString("subject"));
                    frm.setStatus(rs.getString("status"));
                    frms.add(frm);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return frms;
    }

    @Override
    public String display(List<Forum> frms) {
        String listFooum ="";
        for (Forum frm : frms){
            listFooum+="Forum{" + "subject=" + frm.getSubject() + ", status=" + frm.getStatus()+"\n";
        }
        return listFooum;
    }

    @Override
    public ObservableList<Forum> getForum() {
        try {
            String query = "select id,subject,status,id_course from forum";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Archived".equals(rs.getString("status"))) {
                    Forum frm = new Forum();
                    frm.setId(rs.getInt("id"));
                    frm.setSubject(rs.getString("subject"));
                    frm.setStatus(rs.getString("status"));
                    frm.setId_course(rs.getInt("id_course"));
                    oL.addAll(frm);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return oL; 
    }
    
    @Override
    public ObservableList<Forum> getForumByIdCourse(int id_Course) {
        try {
            String query = "select * from forum where id_course=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_Course);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Archived".equals(rs.getString("status"))) {
                    Forum frm = new Forum();
                    frm.setId(rs.getInt("id"));
                    frm.setSubject(rs.getString("subject"));
                    frm.setStatus(rs.getString("status"));
                    frm.setId_course(rs.getInt("id_course"));
                    oL.addAll(frm);
                } 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return oL;
    }   

    @Override
    public ObservableList<Forum> getArchivedForumByIdCourse(int id_course) {
        try {
            String query = "select * from forum where id_course=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_course);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Available".equals(rs.getString("status"))) {
                    Forum frm = new Forum();
                    frm.setId(rs.getInt("id"));
                    frm.setSubject(rs.getString("subject"));
                    frm.setStatus(rs.getString("status"));
                    frm.setId_course(rs.getInt("id_course"));
                    oL.addAll(frm);
                } 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return oL;
    }
    public Forum FindForumById(int id) {

        Forum f = new Forum();
        try {
            String query = "select * from forum where id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f.setId(rs.getInt("id"));
                f.setId_course(rs.getInt("id_course"));
                f.setStatus(rs.getString("status"));
                f.setSubject(rs.getString("subject"));
                f.setCreated_by(rs.getInt("created_by"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return f;

    }
}