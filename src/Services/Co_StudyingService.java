/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Co_Studying;
import IServices.ICo_Studying;
import Utils.DataBaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import GUI.CoStudyingAddController;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rayen
 */
public class Co_StudyingService implements ICo_Studying{
    
    Connection cnx;

    public Co_StudyingService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }
    
    @Override
    public void addCostudying(Co_Studying p) {
        try {
            String requete = "INSERT INTO co_studying (description,file,type,niveau) VALUES(?,?,?,?)";
            String s = CoStudyingAddController.s;
            InputStream is = new FileInputStream(new File(s));
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, p.getDescription());
            pst.setBlob(2, is);
            pst.setString(3, p.getType());
            pst.setString(4, p.getLevel());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Co_StudyingService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteCostudying(Co_Studying p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editCostudying(int id, String object, Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Co_Studying> listStudent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
