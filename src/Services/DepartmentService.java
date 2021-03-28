/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Department;
import IServices.IDepartment;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
/**
 *
 * @author Mrad
 */
public class DepartmentService implements IDepartment {
    Connection con;
    
    ObservableList<Department> os = FXCollections.observableArrayList();
    ObservableList<String> sos = FXCollections.observableArrayList();
    ObservableList<TableColumn<Department, ?>> columns;
    List<Department> li = new ArrayList<>();
    public DepartmentService() {
            con = DataBaseConnection.getInstance().getConnection();
    }
    
    @Override
    public void create(Department dep) {
        try {
            String query="INSERT INTO `department`(`name`,`ownerId`, `ownername`, `ownerlastname`, `created_by` , `created_date` , `specialties`) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, dep.getName());
            statement.setInt(2, dep.getOwnerId());
            statement.setString(3, dep.getOwnername());
            statement.setString(4, dep.getOwnerlastname());
            statement.setString(5, dep.getCreated_by());
            statement.setDate(6, dep.getCreated_date());
            statement.setString(7, dep.getStringSpecialties());
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
            String query="SELECT * FROM `department` WHERE name=? and ownername=? and ownerlastname=? and ownerId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dep.getName());
            ps.setString(2, dep.getOwnername());
            ps.setString(3, dep.getOwnerlastname());
            ps.setInt(4, dep.getOwnerId());
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
            PreparedStatement ps = con.prepareStatement(query);
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
        System.out.println("ena win");
        try {
            String query="UPDATE `department` SET `name`=?,`ownerId`=?,`ownername`=?,`ownerlastname`=?,`last_updated_by`=?,`last_updated_date`=?, `specialties`=? WHERE id=?";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, dep.getName());
            pr.setInt(2, dep.getOwnerId());
            pr.setString(3, dep.getOwnername());
            pr.setString(4, dep.getOwnerlastname());
            pr.setString(5, dep.getLast_updated_by());
            pr.setDate(6, dep.getLast_update_date());
            pr.setString(7, dep.getStringSpecialties());
            pr.setInt(8, dep.getId());
            pr.executeUpdate();           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Department getDepartment(String str) { 
        Department d = new Department();
        boolean check = false;
        try {
            String query = "select * from department where name=? or ownername=? or ownerlastname=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, str);
            ps.setString(2, str);
            ps.setString(3, str);
//            ps.setInt(4, Integer.parseInt(str));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    check = true;
                    d.setId(rs.getInt("id"));
                    d.setOwnername(rs.getString("ownername"));
                    d.setOwnerlastname(rs.getString("ownerlastname"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                    System.out.println(d.toString());
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
    public ObservableList<Department> getDepartments() { 
        try {
            String query = "select * from department";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    Department d = new Department();
                    d.setId(rs.getInt("id"));
                    d.setOwnerId(rs.getInt("ownerId"));
                    d.setOwnername(rs.getString("ownername"));
                    d.setOwnerlastname(rs.getString("ownerlastname"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                    d.setSpec();
                    os.addAll(d);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }
    
    @Override
    public List<Department> getListDepartment() {
        try {
            String query = "select * from department";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    Department d = new Department();
                    d.setId(rs.getInt("id"));
                    d.setOwnerId(rs.getInt("ownerId"));
                    d.setOwnername(rs.getString("ownername"));
                    d.setOwnerlastname(rs.getString("ownerlastname"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                    d.setSpec();
                    li.add(d);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return li;
    }

    @Override
    public ObservableList<Department> getHistoriqueDepartment() {
        try {
            String query = "select * from department";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("id"));
                d.setOwnername(rs.getString("ownername"));
                d.setOwnerlastname(rs.getString("ownerlastname"));
                d.setName(rs.getString("name"));
                d.setCreated_by(rs.getString("created_by"));
                d.setCreated_date(rs.getDate("created_date"));
                d.setLast_update_date(rs.getDate("last_updated_date"));
                d.setLast_updated_by(rs.getString("last_updated_by"));
                d.setArchived_by(rs.getString("archived_by"));
                d.setArchived_date(rs.getDate("archived_date"));
                d.setList(rs.getString("specialties"));
                d.setSpec();
                os.addAll(d);
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }

    @Override
    public int nbrDepart() {
        int nb=0;
        try {
            String query="SELECT COUNT(*) FROM `department` WHERE status is null or status!='archived'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                nb=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nb;
    }

    @Override
    public ObservableList<Department> nomDepart() {
        Department d = new Department();
        try {
            String query="SELECT id,name FROM `department` WHERE status is null or status!='archived'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                d.setName(rs.getString("name"));
                d.setId(rs.getInt("id"));
                os.addAll(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }

    @Override
    public Department orderDesc() {
        Department d = new Department();
        try {
            String query="SELECT * FROM `department` WHERE status is null or status!='archived' ORDER BY specialties DESC";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setOwnername(rs.getString("ownername"));
                d.setOwnerlastname(rs.getString("ownerlastname"));
                d.setName(rs.getString("name"));
                d.setCreated_by(rs.getString("created_by"));
                d.setCreated_date(rs.getDate("created_date"));
                d.setLast_update_date(rs.getDate("last_updated_date"));
                d.setLast_updated_by(rs.getString("last_updated_by"));
                d.setArchived_by(rs.getString("archived_by"));
                d.setArchived_date(rs.getDate("archived_date"));
                d.setList(rs.getString("specialties"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return d;
    }
    
    public Department getDepParId(int i) {
        Department d = new Department();
        boolean check = false;
        try {
            String query = "select * from department where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, i);
//            ps.setInt(4, Integer.parseInt(str));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    check = true;
                    d.setId(rs.getInt("id"));
                    d.setOwnername(rs.getString("ownername"));
                    d.setOwnerlastname(rs.getString("ownerlastname"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                    System.out.println(d.toString());
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
    public ObservableList<Department> recherche(String st){
        try {
            Statement ps = con.createStatement();
            String query = "select * from department where name like '%"+st+"%' or ownerlastname like '%"+st+"%' or ownername like '%"+st+"%'";
            ResultSet rs = ps.executeQuery(query);
           
            while (rs.next()) {
                if(!"archived".equals(rs.getString("status"))) {
                    Department d = new Department();
                    d.setId(rs.getInt("id"));
                    d.setOwnername(rs.getString("ownername"));
                    d.setOwnerlastname(rs.getString("ownerlastname"));
                    d.setName(rs.getString("name"));
                    d.setCreated_by(rs.getString("created_by"));
                    d.setCreated_date(rs.getDate("created_date"));
                    d.setLast_update_date(rs.getDate("last_updated_date"));
                    d.setLast_updated_by(rs.getString("last_updated_by"));
                    d.setArchived_by(rs.getString("archived_by"));
                    d.setArchived_date(rs.getDate("archived_date"));
                    d.setList(rs.getString("specialties"));
                    d.setSpec();
                    os.addAll(d);
                }
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }
}
