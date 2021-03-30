package Services;
import Entities.Course;
import IServices.IService;
import Utils.DataBaseConnection;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseService implements IService<Course> {

    Connection c;
    ObservableList<Course> oL = FXCollections.observableArrayList();
    ArrayList<Course> la = new ArrayList();

    public CourseService() {

        c = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public void Ajouter(Course t) throws SQLException {
        try {
         
            String rq = "INSERT INTO course (name,course_file,id_Subject,created_by,Description,created_Date,archived_Date) VALUES(?,?,?,?,?,?,null)";
            PreparedStatement pre = c.prepareStatement(rq);
            pre.setString(1, t.getName());
            pre.setString(2, t.getCourse());
            pre.setInt(3, t.getId_subject());
            pre.setString(4, t.getCreated_by());
            pre.setString(5, t.getDescription());
            pre.setDate(6, new java.sql.Date(new Date().getTime()));
            pre.executeUpdate();

        } catch (SQLException excep) {
            System.err.println(excep.getMessage());
        }
    }

    @Override
    public void Supprimer(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Archiver(int id) throws SQLException {
        PreparedStatement ps;
        String query = "UPDATE `course` SET `archived_Date`=? WHERE `id`=?";
        ps = c.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, id);
        ps.execute();
    }

    @Override
    public void Modifier(Course t, int id) throws SQLException {
        try {
            String query = "UPDATE `Course` SET `name`=?,`course_file`=?,`id_Subject`=?,`Description`=? WHERE id=?";
            PreparedStatement ps = c.prepareStatement(query);
           
            ps.setString(1, t.getName());
            ps.setString(2, t.getCourse());
            ps.setInt(3, t.getId_subject());
            ps.setString(4, t.getDescription());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Updated succesfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ObservableList<Course> Affichertout() throws SQLException {
        ObservableList<Course> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `course` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"), rs.getDate("archived_Date"), rs.getString("Description")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ObservableList<Course> AfficherListCoursMatiere(int id_subject) throws SQLException {
        ObservableList<Course> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `course` where id_Subject=? ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ps.setInt(1, id_subject);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"), rs.getDate("archived_Date"), rs.getString("Description")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ObservableList<Course> serach(String cas) throws SQLException {
        ObservableList<Course> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `course` where name LIKE '%" + cas + "%' or  created_Date LIKE '%" + cas + "%' or  created_by LIKE '%" + cas + "%' or  archived_Date LIKE '%" + cas + "%' or  Description LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"), rs.getDate("archived_Date"), rs.getString("Description")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Course> AfficherCours() {
        //  FileReader reader = new FileReader(t.getCourse());
        //rs.getCharacterStream(2, reader);     
        List<Course> list = new ArrayList<>();
        String requete = "SELECT * FROM `course` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String fileName = rs.getString("name") + ".txt";
                InputStream is = rs.getBinaryStream("course_file");

                Course c = new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("id_subject"), rs.getString("created_by"), rs.getDate("created_Date"), rs.getString("Description"), is);
                list.add(c);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
