package services;

import entities.Specialty;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Utils.DataBaseConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mrad
 */
public class SpecialtyService {
  
    Connection cnx;
    public SpecialtyService(){
        cnx = DataBaseConnection.getInstance().getConnection();
    }
    
    public void addSpecialty(Specialty s) {
    try {
        String requete = "INSERT INTO `specialties`(`id_resp`, `specialty`, `niveau`, `created_by`, `created_date`) VALUES (?,?,?,?,?)";

        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, s.getId_resp());
        pst.setString(2, s.getSpecialty());
        pst.setString(3, s.getStringNiveaux());
        pst.setString(4, s.getCreated_by());
        pst.setDate(5, s.getCreated_date());
        pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public int getIdSpecialty(Specialty s) throws SQLException {
        String query="SELECT * FROM `specialties` WHERE id_resp=?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setInt(1, s.getId_resp());
        int id=0;
        ResultSet rs;
        rs = ps.executeQuery();
        while(rs.next()) {
            id=rs.getInt("id");
        }
            return id;
    }
    
    public void remove(Specialty s) throws SQLException {
        String query="UPDATE `specialties` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(query); 
        ps.setString(1, s.getArchived_by());
        ps.setDate(2, s.getArchived_date());
        ps.setString(3, "archived");
        ps.setInt(4, s.getId());
        ps.executeUpdate(); 
        System.out.println("removed succesfully"); }
    
    public void update(Specialty s) throws SQLException {
        String query="UPDATE `specialties` SET `id_resp`=?,`specialty`=?,`niveau`=?,`update_by`=?,`update_date`=? WHERE id=?";
        PreparedStatement pr = cnx.prepareStatement(query);
        pr.setInt(1, s.getId_resp());
        pr.setString(2, s.getSpecialty());
        pr.setString(3, s.getStringNiveaux());
        pr.setString(4, s.getLast_updated_by());
        pr.setDate(5, s.getLast_update_date());
        pr.setInt(6, s.getId());
        pr.executeUpdate();
        System.out.println("dep updated succesfully");
    }
    
    public Specialty getSpecialty(int id) 
        throws SQLException 
    { 
        String query = "select * from specialties where id= ?"; 
        PreparedStatement ps = cnx.prepareStatement(query); 
  
        ps.setInt(1, id); 
        Specialty s = new Specialty(); 
        ResultSet rs = ps.executeQuery(); 
        boolean check = false; 
  
        while (rs.next()) { 
            if(!"archived".equals(rs.getString("status"))) {
            check = true;
            
            s.setId_resp(rs.getInt("id_resp"));
            s.setSpecialty(rs.getString("specialty")); 
            s.setListNiveaux(rs.getString("niveau"));
            s.setCreated_by(rs.getString("created_by"));
            s.setCreated_date(rs.getDate("created_date"));
            s.setLast_updated_by(rs.getString("update_by"));
            s.setLast_update_date(rs.getDate("update_date"));
            }
        } 
  
        if (check == true) { 
            return s; 
        } 
        else
            return null; 
    }
    public List<Specialty> getSpecialties() 
        throws SQLException 
    { 
        String query = "select * from specialties"; 
        PreparedStatement ps = cnx.prepareStatement(query); 
        ResultSet rs = ps.executeQuery(); 
        
        List<Specialty> ld = new ArrayList(); 
  
        while (rs.next()) { 
            if(!"archived".equals(rs.getString("status"))) {
            Specialty s = new Specialty(); 
            s.setId_resp(rs.getInt("id_resp"));
            s.setSpecialty(rs.getString("specialty")); 
            s.setListNiveaux(rs.getString("niveau"));
            s.setCreated_by(rs.getString("created_by"));
            s.setCreated_date(rs.getDate("created_date"));
            s.setLast_updated_by(rs.getString("update_by"));
            s.setLast_update_date(rs.getDate("update_date"));
            ld.add(s);}
        } 
        return ld; 
    } 
}
