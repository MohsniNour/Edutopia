package IServices;

import Entities.Teacher;
import java.util.List;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mrad
 */
public interface ITeacher {
    public void addTeacher(Teacher t);
    public int getIdTeacher(Teacher t);
    public void remove(Teacher t);
    public void update(Teacher t);
    public Teacher getTeacher(String str);
    public List<Teacher> getTeachers();
    public ObservableList<Teacher> getTeachersObs();
    public Boolean exist(int id);
    public ObservableList<Teacher> getHistoriqueTeachers();
    public int nbEnseignantselonDepart(int j);
    
}
