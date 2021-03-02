package services;

import Iservices.IDepartment;
import entities.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DepartmentService implements IDepartment {
    Connection cnx;
    public DepartmentService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }
    
    @Override
    public void create(Department dep) {
        try {
            String query="INSERT INTO `department`(`name`, `owner_id`, `admin_number`, `created_by` , `created_date` , `specialties`) VALUES ( ? , ? , ? , ? , ? , ? )";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, dep.getName());
            statement.setInt(2, dep.getOwner_id());
            statement.setInt(3, dep.getAdmin_number());
            statement.setString(4, dep.getCreated_by());
            statement.setDate(5, dep.getCreated_date());
            statement.setString(6, dep.getStringSpecialties());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public int getIdDep(Department dep) {
        int id=0;
        try {
            String query="SELECT * FROM `department` WHERE name=? and owner_id=? and admin_number=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, dep.getName());
            ps.setInt(2, dep.getOwner_id());
            ps.setInt(3, dep.getAdmin_number());
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
    public void remove(Department dep) {
        try {
            String query="UPDATE `department` SET `archived_by`=?,`archived_date`=?,`status`=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, dep.getArchived_by());
            ps.setDate(2, dep.getArchived_date());
            ps.setString(3, "archived");
            ps.setInt(4, dep.getId());
            ps.executeUpdate();
            System.out.println("removed succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void update(Department dep) {
        try {
            String query="UPDATE `department` SET `name`=?,`owner_id`=?,`admin_number`=?,`last_updated_by`=?,`last_updated_date`=?, `specialties`=? WHERE id=?";
            PreparedStatement pr = cnx.prepareStatement(query);
            pr.setString(1, dep.getName());
            pr.setInt(2, dep.getOwner_id());
            pr.setInt(3, dep.getAdmin_number());
            pr.setString(4, dep.getLast_updated_by());
            pr.setDate(5, dep.getLast_update_date());
            pr.setString(6, dep.getStringSpecialties());
            pr.setInt(7, dep.getId());
            pr.executeUpdate();
            System.out.println("dep updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Department getDepartment(int id) { 
        Department d = new Department();
        boolean check = false;
        try {
            String query = "select * from department where id= ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    check = true;
                    d.setId(rs.getInt("id"));
                    d.setOwner_id(rs.getInt("owner_id"));
                    d.setAdmin_number(rs.getInt("admin_number"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                }
            }
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
        if (check == true) {
                return d;
            }
            else
                return null;
    } 
    
    @Override
    public List<Department> getDepartments() { 
        List<Department> ld = new ArrayList();
        try {
            String query = "select * from department";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    Department d = new Department();
                    d.setId(rs.getInt("id"));
                    d.setOwner_id(rs.getInt("owner_id"));
                    d.setAdmin_number(rs.getInt("admin_number"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                    ld.add(d);}
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ld;
    } 
}
