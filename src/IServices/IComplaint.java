/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Complaint;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Sabrina
 */
public interface IComplaint {
    
    public void AddComplaint (Complaint c);
    public void UpdateComplaint (int id,Complaint updatedComplaint);
    public void DeleteComplaint (Complaint deletedComplaint);
    public int GetIdComplaint (Complaint c);
    public Complaint getComplaint(int id);
    public ObservableList<Complaint> getComplaints();
    public List<Complaint> getListComplaint();
    
}
