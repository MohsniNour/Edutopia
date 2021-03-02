/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Student;
import java.util.List;

/**
 *
 * @author rayen
 */
public interface IStudent {
    
    public void addStudent(Student p);
    public void deleteStudent(Student p);
    public void editStudent(int id, String object, Object obj);
    public List<Student> listStudent();
    
}
