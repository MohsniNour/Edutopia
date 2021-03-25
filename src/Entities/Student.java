/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author rayen
 */
public class Student extends User  {
    
    private int id;
    private classe classe;

    public Student(int id, classe classe) {
        this.id = id;
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public classe getClasse() {
        return classe;
    }

    public void setClasse(classe classe) {
        this.classe = classe;
    }

    public Student( String role, String name, String last_name, int cin, String email, int phone_number, Date birth_date, classe classe) {
        super( role, name, last_name, cin, email, phone_number, birth_date);
        this.classe = classe;
    }

    public Student(int id, String role, String name, String last_name, int cin, String email, int phone_number) {
        super(role, name, last_name, cin, email, phone_number);
        this.id = id;

    }
    
   

    public Student() {
    }
      
}
