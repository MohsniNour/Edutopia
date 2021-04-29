/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Department;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Mrad
 */
public interface IDepartment {
    public void create(Department dep);
    public int getIdDep(Department dep);
    public void remove(Department dep);
    public void update(Department dep);
    public Department getDepartment(String str);
    public ObservableList<Department> getDepartments();
    public List<Department> getListDepartment();
    public ObservableList<Department> getHistoriqueDepartment();
    public int nbrDepart();
    public ObservableList<Department> nomDepart();
    public Department orderDesc();
}
