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
    private String classe;

    public Student(int id, String classe) {
        this.id = id;
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Student( String role, String name, String last_name, int cin, String email, int phone_number, Date birth_date, String classe) {
        super( role, name, last_name, cin, email, phone_number, birth_date);
        this.classe = classe;
    }

    public Student(String role, String name, String last_name, int cin, String email, int phone_number, java.util.Date birth_date) {
        super(role, name, last_name, cin, email, phone_number, birth_date);
    }

    public Student() {
    }
      
}
