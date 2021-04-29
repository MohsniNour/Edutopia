/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentt;
import IServices.IComment;
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
public class CommentService implements IComment{
    
    Connection conn;
    ObservableList<Commentt> oL = FXCollections.observableArrayList();

    public CommentService() {
        conn = DataBaseConnection.getInstance().getConnection();
    }
    
    @Override
    public String getId(Commentt comment) {
        String id="";
        try {
            String query="SELECT * FROM `comment` WHERE content=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, comment.getContent());
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                id=rs.getString("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public ObservableList<Commentt> getComment() {
        try {
            String query = "select id,content,id_forum,status from comment";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Archived".equals(rs.getString("status"))) {
                    Commentt cmt= new Commentt();
                    cmt.setId(rs.getInt("id"));
                    cmt.setContent(rs.getString("content"));
                    cmt.setId_forum(rs.getInt("id_forum"));
                    cmt.setLikes(rs.getInt("likes"));
                    cmt.setDisLikes(rs.getInt("dislike"));
                    cmt.setStatus(rs.getString("status"));
                    oL.addAll(cmt);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return oL;
    }
    

    @Override
    public void add(Commentt comment) {
        try {
            String requete = "INSERT INTO comment (content,id_forum,status,created_by,created_date) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, comment.getContent());
            pst.setInt(2, comment.getId_forum());
            pst.setString(3, comment.getStatus());
            pst.setInt(4, comment.getCreated_by());
            pst.setDate(5, (Date) comment.getCreated_date());
            pst.executeUpdate();
            System.out.println("Added succesfully");
        } catch (SQLException excep) {
            System.err.println(excep.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        try {
            String req ="UPDATE `comment` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
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
            String req ="UPDATE `comment` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
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
    public void update(int id, Commentt new_comment) {
        try {
            String query="UPDATE `comment` SET `content`=?,`last_updated_by`=?,`last_updated_date`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, new_comment.getContent());
            ps.setInt(2, new_comment.getLast_updated_by());
            ps.setDate(3, (Date)new_comment.getLast_updated_Date());
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Commentt details(int id) {
        Commentt cmt = new Commentt();
        boolean check = false;
        try {
            String query = "select * from comment where id= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    check = true;
                    cmt.setId(rs.getInt("id"));
                    cmt.setContent(rs.getString("content"));
                    cmt.setStatus(rs.getString("status"));
                    cmt.setLikes(rs.getInt("likes"));
                    cmt.setDisLikes(rs.getInt("dislike"));
            }
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
        if (check == true) {
                return cmt;
            }
            else
                return null;
    }

    @Override
    public List<Commentt> listAvailable() {
        List<Commentt> cmts = new ArrayList();
        try {
            String query = "select content,status,likes from comment";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Archived".equals(rs.getString("status"))) {
                    Commentt cmt = new Commentt();
                    cmt.setContent(rs.getString("content"));
                    cmt.setStatus(rs.getString("status"));
                    cmt.setId_forum(rs.getInt("id_forum"));
                    cmt.setLikes(rs.getInt("likes"));
                    cmt.setDisLikes(rs.getInt("dislike"));
                    cmts.add(cmt);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cmts;
    }

    @Override
    public List<Commentt> listArchived() {
        List<Commentt> cmts = new ArrayList();
        try {
            String query = "select content,status from comment";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Available".equals(rs.getString("status"))) {
                    Commentt cmt = new Commentt();
                    cmt.setContent(rs.getString("content"));
                    cmt.setStatus(rs.getString("status"));
                    cmt.setId_forum(rs.getInt("id_forum"));
                    cmt.setLikes(rs.getInt("likes"));
                    cmt.setDisLikes(rs.getInt("dislike"));
                    cmts.add(cmt);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cmts;
    }

    @Override
    public String display(List<Commentt> cmts) {
        String listComment ="";
        for (Commentt cmt : cmts){
            listComment+="Comment{" + "content=" + cmt.getContent() + ", status=" + cmt.getStatus()+"\n";
        }
        return listComment;
    }

    @Override
    public ObservableList<Commentt> getCommentsByIdForum(int id_forum) {
        try {
            String query = "select * from comment where id_forum=? order by likes desc";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_forum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"Archived".equals(rs.getString("status"))) {
                    Commentt cmt = new Commentt();
                    cmt.setId(rs.getInt("id"));
                    cmt.setContent(rs.getString("content"));
                    cmt.setStatus(rs.getString("status"));
                    cmt.setLikes(rs.getInt("likes"));
                    cmt.setDisLikes(rs.getInt("dislike"));
                    cmt.setId_forum(rs.getInt("id_forum"));
                    oL.addAll(cmt);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return oL;
        
    }

    @Override
    public void addLike(int id, int like) {
        
        try {
            String query="UPDATE `comment` SET `likes`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, like);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("liked succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addDisLike(int id, int disLike) {
        
        try {
            String query="UPDATE `comment` SET `dislike`=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, disLike);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("disliked succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int countComment(int id_forum) {
        int count=0;
        try {
            String query = "select count(*) from comment where id_forum=? and status=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_forum); 
            ps.setString(2,"Available" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count=rs.getInt(1);        
            }  
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    
}
