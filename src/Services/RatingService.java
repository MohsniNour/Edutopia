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

}
