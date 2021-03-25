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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rayen
 */
public class RatingService {

    Connection cnx;

    public RatingService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    public boolean CheckUserExists(int id_rater, int id_item) {

        boolean UserExists = false;
        try {
            PreparedStatement st = cnx.prepareStatement("SELECT * FROM rating WHERE id_rater = ? AND id_item = ? ");
            st.setInt(1, id_rater);
            st.setInt(2, id_item);
            ResultSet r1 = st.executeQuery();
            if (r1.next()) {
                UserExists = true;
            }
        } catch (Exception e) {
            System.out.println("SQL Exception: " + e.toString());
        }
        return UserExists;
    }

    public int TakeUserRating(int id_rater, int id_item) {

        int rate = 0;
        try {
            PreparedStatement st = cnx.prepareStatement("select * from rating where id_rater = ? AND id_item = ? ");
            st.setInt(1, id_rater);
            st.setInt(2, id_item);
            ResultSet r1 = st.executeQuery();
            if (r1.next()) {
                rate = r1.getInt("rate");

            }
        } catch (Exception e) {
            System.out.println("SQL Exception: " + e.toString());
        }
        return rate;
    }

    public Rating getRatingById(int id) {

        Rating R = new Rating();
        try {
            PreparedStatement st = cnx.prepareStatement("select * from rating where id = " + id + "");
            ResultSet r1 = st.executeQuery();
            if (r1.next()) {
                R.setId_rater(r1.getInt("id_rater"));
                R.setId_item(r1.getInt("id_item"));
                R.setRate(r1.getInt("rate"));
            }
        } catch (Exception e) {
            System.out.println("SQL Exception: " + e.toString());
        }
        return R;
    }

    public void addRating(Rating r) throws SQLException {

        String req = "";
        if (CheckUserExists(r.getId_rater(), r.getId_item())) {
            try {
                req = "UPDATE rating SET rate = ? WHERE id_rater = ? AND id_item=?";
                PreparedStatement ste = cnx.prepareStatement(req);
                ste.setInt(1, r.getRate());
                ste.setInt(2, r.getId_rater());
                ste.setInt(3, r.getId_item());
                ste.executeUpdate();
                System.out.println("Rating updated");

            } catch (SQLException ex) {
                Logger.getLogger(RatingService.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            req = "INSERT INTO rating (id_item, id_rater, rate) Values (?,?,?)";
            try {
                PreparedStatement ste = cnx.prepareStatement(req);
                ste.setInt(1, r.getId_item());
                ste.setInt(2, r.getId_rater());
                ste.setInt(3, r.getRate());

                ste.executeUpdate();
                System.out.println("Rating ajoutée");

            } catch (SQLException ex) {
                System.out.println("Problémeeee");
                System.out.println(ex.getMessage());
            }
        }
    }

    public int getAvgRating(int id_item) throws SQLException {
        int avg = 0;
        String requete = "SELECT AVG(rate) AS nb FROM rating Where id_item = ?";
        PreparedStatement st = cnx.prepareStatement(requete);
        st.setInt(1, id_item);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            avg = rs.getInt("nb");
        }

        System.out.println(avg);
        return avg;
    }

}
