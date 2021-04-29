/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.ISpecialty;
import Utils.DataBaseConnection;
import Entities.Specialty;
import java.sql.Connection;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mrad
 */
public class SpecialtyService implements ISpecialty {

    Connection con;
    ObservableList<Specialty> os = FXCollections.observableArrayList();

    public SpecialtyService() {
        con = DataBaseConnection.getInstance().getConnection();

    }

    public void addSpecialty(Specialty s) {
        try {
            String requete = "INSERT INTO `specialties`(`idTeacher`,`name`,`lastname`, `specialty`, `niveau`, `created_by`, `created_date`) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, s.getId_resp());
            pst.setString(2, s.getName());
            pst.setString(3, s.getLastname());
            pst.setString(4, s.getSpecialty());
            pst.setString(5, s.getNiveau());
            pst.setInt(6, s.getCreated_by());
            pst.setDate(7, s.getCreated_date());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getIdSpecialty(Specialty s) {
        int id = 0;
        try {
            String query = "SELECT id FROM `specialties` WHERE idTeacher=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, s.getId_resp());
//            System.out.println(s.getId_resp());
//            ps.setString(2, s.getSpecialty());
//            System.out.println(s.getSpecialty());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public void remove(Specialty s) {
        try {
            String query = "DELETE FROM `specialties` WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, s.getArchived_by());
            ps.setDate(2, s.getArchived_date());
            ps.setString(3, "archived");
            ps.setInt(4, s.getId());
            ps.executeUpdate();
            System.out.println("removed succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Specialty s) {
        try {
            String query = "UPDATE `specialties` SET `idTeacher`=?,`name`=?,`lastname`=?,`specialty`=?,`niveau`=?,`update_by`=?,`update_date`=? WHERE id=?";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, s.getId_resp());
//            System.out.println(s.getId_resp());
            pr.setString(2, s.getName());
//            System.out.println(s.getName());
            pr.setString(3, s.getLastname());
//            System.out.println( s.getLastname());
            pr.setString(4, s.getSpecialty());
//            System.out.println(s.getSpecialty());
            pr.setString(5, s.getNiveau());
//            System.out.println(s.getNiveau());
            pr.setInt(6, s.getLast_updated_by());
//            System.out.println(s.getLast_updated_by());
            pr.setDate(7, s.getLast_update_date());
//            System.out.println(s.getLast_update_date());
            pr.setInt(8, s.getId());
            pr.executeUpdate();
            System.out.println("dep updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Specialty getSpecialty(String str) {
        Specialty s = new Specialty();
        boolean check = false;
        try {
            String query = "select * from specialties where name=? or lastname=? or specialty=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, str);
            ps.setString(2, str);
            ps.setString(3, str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    check = true;
                    s.setId_resp(rs.getInt("idTeacher"));
                    s.setLastname(rs.getString("lastname"));
                    s.setName(rs.getString("name"));
                    s.setSpecialty(rs.getString("specialty"));
                    s.setListNiveaux(rs.getString("niveau"));
                    s.setCreated_by(rs.getInt("created_by"));
                    s.setCreated_date(rs.getDate("created_date"));
                    s.setLast_updated_by(rs.getInt("update_by"));
                    s.setLast_update_date(rs.getDate("update_date"));
                    s.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (check == true) {
            return s;
        } else {
            return null;
        }
    }

    @Override
    public ObservableList<Specialty> getSpecialties() {

        try {
            Statement ps = con.createStatement();
            String query = "select * from specialties";
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    Specialty s = new Specialty();
                    s.setId_resp(rs.getInt("idTeacher"));
                    s.setName(rs.getString("name"));
                    s.setLastname(rs.getString("lastname"));
                    s.setSpecialty(rs.getString("specialty"));
                    s.setListNiveaux(rs.getString("niveau"));
                    s.setCreated_by(rs.getInt("created_by"));
                    s.setCreated_date(rs.getDate("created_date"));
                    s.setLast_updated_by(rs.getInt("update_by"));
                    s.setLast_update_date(rs.getDate("update_date"));
                    os.addAll(s);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }

    public ObservableList<Specialty> getHistoriqueSpecialties() {
        try {
            Statement ps = con.createStatement();
            String query = "select * from specialties";
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Specialty s = new Specialty();
                s.setId_resp(rs.getInt("idTeacher"));
                s.setSpecialty(rs.getString("specialty"));
                s.setListNiveaux(rs.getString("niveau"));
                s.setCreated_by(rs.getInt("created_by"));
                s.setCreated_date(rs.getDate("created_date"));
//                System.out.println(s.getCreated_date());
                s.setLast_updated_by(rs.getInt("update_by"));
                s.setLast_update_date(rs.getDate("update_date"));
                System.out.println(s.getLast_update_date());
                s.setArchived_by(rs.getInt("archived_by"));
                s.setArchived_date(rs.getDate("archived_date"));
                os.addAll(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }

    public ObservableList<Specialty> recherche(String st) {
        try {
            Statement ps = con.createStatement();
            String query = "select * from specialties where name like '%" + st + "%' or lastname like '%" + st + "%' or specialty like '%" + st + "%'";
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    Specialty s = new Specialty();
                    s.setId_resp(rs.getInt("idTeacher"));
                    s.setName(rs.getString("name"));
                    s.setLastname(rs.getString("lastname"));
                    s.setSpecialty(rs.getString("specialty"));
                    s.setId(rs.getInt("id"));
                    s.setListNiveaux(rs.getString("niveau"));
                    s.setCreated_by(rs.getInt("created_by"));
                    s.setCreated_date(rs.getDate("created_date"));
                    s.setLast_updated_by(rs.getInt("update_by"));
                    s.setLast_update_date(rs.getDate("update_date"));
                    os.addAll(s);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }

    public Specialty getSpecialty(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
