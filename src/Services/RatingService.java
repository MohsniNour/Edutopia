/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Rating;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;

/**
 *
 * @author rayen
 */
public class RatingService {
    
    Connection cnx;

    public RatingService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    public void addRating(Rating r) throws SQLException {
        String requete = "insert into rating(id_item, rate) values(?,?)";
        PreparedStatement st = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, r.getId_item());
        st.setInt(2, r.getRate());
        //st.setInt(3, r.getId_rater());
        st.executeUpdate();
        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            r.setId(generatedKeys.getInt(1));
        }
        System.out.println("Rating added");
    }

    public void editRating(Rating r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteRating(Rating r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getAvgRating(int id_item) throws SQLException {
        int avg = 0;
        String requete = "SELECT AVG(rate) AS nb FROM rating Where id_item = ?";
        PreparedStatement st = cnx.prepareStatement(requete);
        st.setInt(1, id_item);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            avg = rs.getInt("nb");
        }

        System.out.println(avg);
        return avg;
    }

}
