/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import IServices.ISubject;
import Utils.DataBaseConnection;
import GUI.classefxController;
import Entities.Subjectt;
import Entities.classe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mrad
 */
public class SubjectService implements ISubject {

    Connection con;
    ObservableList<Subjectt> os = FXCollections.observableArrayList();

    public SubjectService() {
        con = DataBaseConnection.getInstance().getConnection();

    }

    @Override
    public int getIdSubject(Subjectt s) {
        int id = 0;
        try {
            String query = "SELECT * FROM `subject` WHERE id_Subject= ? and id_class=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, s.getId_Subject());
            ps.setInt(2, s.getId_class());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public ObservableList<Subjectt> getSubjects() {
        try {
            Statement ps = con.createStatement();
            String query = "select * from subject";
            ResultSet rs = ps.executeQuery(query);
            classefxController cs = new classefxController();
            classe c = new classe();
            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    Subjectt s = new Subjectt();
                    s.setId_Subject(rs.getString("id_Subject"));
                    s.setId(rs.getInt("id"));
                    c = cs.getClasseParID(rs.getInt("id_class"));
                    s.setNomClasse(c.getName());

                    s.setCourses(rs.getString("courses"));
                    s.setId_class(rs.getInt("id_class"));
                    s.setId_teacher(rs.getInt("id_teacher"));
                    os.addAll(s);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }

    public int nbSubject() {
        int nb = 0;
        try {
            Statement ps = con.createStatement();
            String query = "select count(*) from subject";
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                nb = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }

    @Override
    public void remove(Subjectt s) {
        try {
            String query = "DELETE FROM `subject` WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
           
            
            ps.setInt(1, s.getId());
            ps.executeUpdate();
            System.out.println("removed succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Subjectt s) {
        try {
            String query = "UPDATE `subject` SET `id_Subject`=?, id_class=?, id_teacher=? , `update_by`=?,`update_date`=? WHERE id=?";
            PreparedStatement pr = con.prepareStatement(query);
            System.out.println(s.getId());
            pr.setString(1, s.getId_Subject());
            pr.setInt(4, s.getLast_updated_by());
            pr.setDate(5, s.getLast_update_date());
            pr.setInt(2, s.getId_class());
            pr.setInt(3, s.getId_teacher());
            System.out.println(s.getId_class() + " " + s.getId_teacher());
            pr.setInt(6, s.getId());
            pr.executeUpdate();
            System.out.println("dep updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Subjectt getSubject(String str, int id_classe) {
        Subjectt s = new Subjectt();
        boolean check = false;
        try {
            String query = "select * from subject where id_Subject=? and id_classe=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, str);
            ps.setInt(2, id_classe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    check = true;
                    s.setId(rs.getInt("id"));
                    s.setId_Subject(rs.getString("id_Subject"));
                    s.setCourses(rs.getString("courses"));
                    s.setId_class(rs.getInt("id_class"));
                    s.setId_teacher(rs.getInt("id_teacher"));
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
    public void addSubject(Subjectt s) {
        try {
            String requete = "INSERT INTO `subject`(`id`,`id_Subject`,`courses`,`id_class`,`id_teacher`,`created_by`, `created_date`) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, s.getId());
            pst.setString(2, s.getId_Subject());
            pst.setString(3, s.getCourses());
            pst.setInt(4, s.getId_class());
            pst.setInt(5, s.getId_teacher());
            pst.setInt(6, s.getCreated_by());
            pst.setDate(7, s.getCreated_date());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<Subjectt> recherche(String st) {
        try {
            Statement ps = con.createStatement();
            String query = "select * from subject where id_Subject like '%" + st + "%'";
            ResultSet rs = ps.executeQuery(query);
            Subjectt s = new Subjectt();
            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    s.setId(rs.getInt("id"));
                    s.setId_Subject(rs.getString("id_Subject"));
                    s.setCourses(rs.getString("courses"));
                    s.setId_class(rs.getInt("id_class"));
                    s.setId_teacher(rs.getInt("id_teacher"));
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

    public ArrayList<Subjectt> Affichertout() throws SQLException {
        ArrayList<Subjectt> la = null;
        try {
            String query = "select * from subject";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subjectt c = new Subjectt();
                c.setId(rs.getInt("id"));
                c.setId_Subject(rs.getString("id_Subject"));
                la.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return la;
    }

    public Subjectt getSubjectParI(int id) {
        Subjectt s = new Subjectt();
        boolean check = false;
        try {
            String query = "select * from subject where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!"archived".equals(rs.getString("status"))) {
                    check = true;
                    s.setId(rs.getInt("id"));
                    s.setId_Subject(rs.getString("id_Subject"));
                    s.setCourses(rs.getString("courses"));
                    s.setId_class(rs.getInt("id_class"));
                    s.setId_teacher(rs.getInt("id_teacher"));
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
}
