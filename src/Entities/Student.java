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

    public Student( String role, String name, String last_name, int cin, String email, int phone_number, Date birth_date) {
        super( role, name, last_name, cin, email, phone_number, birth_date);
    }

    public Student() {
    }
      
}
