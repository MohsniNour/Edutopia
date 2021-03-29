package Services;

import Entities.Subjectt;
import IServices.IService;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.security.auth.Subject;

public class SubjectService implements IService{

    
    Connection conn;
    ObservableList<Subject> oL = FXCollections.observableArrayList();
    ArrayList<Subjectt> la = new ArrayList();

    public SubjectService() {
        conn = DataBaseConnection.getInstance().getConnection();

    }

    @Override
    public void Ajouter(Object t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Supprimer(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier(Object t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Subjectt> Affichertout() throws SQLException {
        try {
            String query = "select * from subject";
            PreparedStatement ps = conn.prepareStatement(query);
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


    
}
