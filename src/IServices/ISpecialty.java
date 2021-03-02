package Iservices;

import entities.Specialty;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mrad
 */
public interface ISpecialty {
    
    public void addSpecialty(Specialty s);
    public int getIdSpecialty(Specialty s);
    public void remove(Specialty s);
    public void update(Specialty s);
    public Specialty getSpecialty(int id);
    public List<Specialty> getSpecialties();
    
}
