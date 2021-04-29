package Services;

import Entities.Exam;
import Entities.Question;
import IServices.IService;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Question_Service implements IService<Question> {

    private Connection c = DataBaseConnection.getInstance().getConnection();

    @Override
    public void Ajouter(Question t) throws SQLException {
        try {
            String requete = "INSERT INTO question( question, proposition1, proposition2, proposition3, proposition4, archived_Date, id_Exam,bonnereponse) VALUES (?,?,?,?,?,null,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);

            pst.setString(1, t.getQuestion());
            pst.setString(2, t.getProposition1());
            pst.setString(3, t.getProposition2());
            pst.setString(4, t.getProposition3());
            pst.setString(5, t.getProposition4());
            pst.setInt(6, t.getId_Exam());
            pst.setString(7, t.getBonnereponse());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier(Question t, int id) throws SQLException {

        PreparedStatement ps;
        String query = "UPDATE question SET question=?,proposition1=?, proposition2=?, proposition3 =?, proposition4 =? , id_Exam =? , bonnereponse =? WHERE  id =?";
        ps = c.prepareStatement(query);
        ps.setString(1, t.getQuestion());
        ps.setString(2, t.getProposition1());
        ps.setString(3, t.getProposition2());
        ps.setString(4, t.getProposition3());
        ps.setString(5, t.getProposition4());
        ps.setInt(6, t.getId_Exam());
        ps.setString(7, t.getBonnereponse());
        ps.setInt(8, id);
        ps.execute();
    }

    public void Archiver(int id) throws SQLException {

        PreparedStatement ps;
        String query = "UPDATE question SET archived_Date=? WHERE id=?";
        ps = c.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, id);
        ps.execute();
    }

    @Override
    public ObservableList<Question> Affichertout() throws SQLException {
        ObservableList<Question> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM question ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Question(rs.getInt("id"), rs.getString("question"), rs.getString("proposition1"), rs.getString("proposition2"), rs.getString("proposition3"), rs.getString("proposition4"), rs.getDate("archived_Date"), rs.getInt("id_Exam"), rs.getString("bonnereponse")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<Question> AffichertoutByExam(int idexam) throws SQLException {
        ObservableList<Question> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM question where id_exam= " + idexam;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Question(rs.getInt("id"), rs.getString("question"), rs.getString("proposition1"), rs.getString("proposition2"), rs.getString("proposition3"), rs.getString("proposition4"), rs.getDate("archived_Date"), rs.getInt("id_Exam"), rs.getString("bonnereponse")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public List<Question> AfficherQuestionByexam(Exam exam) {
        ObservableList<Question> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM question where id_Exam= " + exam.getId();
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question(rs.getInt("id"), rs.getString("question"), rs.getString("proposition1"), rs.getString("proposition2"), rs.getString("proposition3"), rs.getString("proposition4"), rs.getString("bonnereponse"), exam);
                list.add(q);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<Question> serach(String cas) throws SQLException {
        ObservableList<Question> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM question where question LIKE '%" + cas + "%'or proposition1 LIKE '%" + cas + "%' or  proposition2 LIKE '%" + cas + "%' or  proposition3 LIKE '%" + cas + "%' or  proposition4 LIKE '%" + cas + "%' or  archived_Date LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Question(rs.getInt("id"), rs.getString("question"), rs.getString("proposition1"), rs.getString("proposition2"), rs.getString("proposition3"), rs.getString("proposition4"), rs.getDate("archived_Date"), rs.getInt("id_Exam"), rs.getString("bonnereponse")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
