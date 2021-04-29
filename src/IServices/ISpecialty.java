/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Specialty;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Mrad
 */
public interface ISpecialty {
    public void addSpecialty(Specialty s);
    public int getIdSpecialty(Specialty s);
    public void remove(Specialty s);
    public void update(Specialty s);
    public Specialty getSpecialty(String s);
    public ObservableList<Specialty> getSpecialties() ;
    public ObservableList<Specialty> getHistoriqueSpecialties();
    
}
