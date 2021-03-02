package Iservices;
import entities.Department;
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
public interface IDepartment {
    public void create(Department dep);
    public int getIdDep(Department dep);
    public void remove(Department dep);
    public void update(Department dep);
    public Department getDepartment(int id);
    public List<Department> getDepartments();
    
}
