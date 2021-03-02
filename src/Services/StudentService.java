/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IStudent;
import Entities.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.DataBaseConnection;

/**
 *
 * @author rayen
 */
public class StudentService implements IStudent {

    Connection cnx;

    public StudentService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public void addStudent(Student p) {
        try {
            String requete = "INSERT INTO user (role,name,last_name,cin,email,phone_number,birth_date) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getRole());
            pst.setString(2, p.getName());
            pst.setString(3, p.getLast_name());
            pst.setInt(4, p.getCin());
            pst.setString(5, p.getEmail());
            pst.setInt(6, p.getPhone_number());
            pst.setDate(7, (Date) p.getBirth_date());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void deleteStudent(Student p) {
        try {
            String requete = "DELETE FROM user WHERE id=?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Etudiant supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void editStudent(int id, String object, Object obj) {
        try {
            String requete = "UPDATE user SET ? = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, object);
            pst.setObject(2, obj);
            pst.setInt(3, id);
            String ch = pst.toString().replaceFirst("\'", "");
            String ch2 = ch.replaceFirst("\'", "");
            int pos = ch2.indexOf("UPDATE");
            String ch3 = ch2.substring(pos, ch2.length());
            pst = cnx.prepareStatement(ch3);
            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("Etudiant modifié avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Student> listStudent() {

        List<Student> myList = new ArrayList<>();
        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery("SELECT * from user");
            while (rs.next()) {
                String role = rs.getString("role");
                String name = rs.getString("name");
                String last_name = rs.getString("last_name");
                int cin = rs.getInt("cin");
                String email = rs.getString("email");
                int phone_number = rs.getInt("phone_number");
                Date birth_date = rs.getDate("birth_date");
                Student p = new Student(role, name, last_name, cin, email, phone_number, birth_date);
                p.setId(rs.getInt("id"));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    public void addUser(Student p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
