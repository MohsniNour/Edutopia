/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Sabrina
 */
public class User extends RecursiveTreeObject<User> implements Serializable {

    private int id;
    private String role;
    private String name;
    private String last_name;
    private int cin;
    private String email;
    private int phone_number;
    private java.sql.Date birth_date;
    private String password;
    private int CreatedBy;

    public int getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public java.sql.Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(java.sql.Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role=" + role + ", name=" + name + ", last_name=" + last_name + ", cin=" + cin + ", email=" + email + ", phone_number=" + phone_number + ", birth_date=" + birth_date + ", password=" + password + '}';
    }

    public User(int id, String role, String name, String last_name, int cin, String email, int phone_number, java.sql.Date birth_date, String password) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.cin = cin;
        this.email = email;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.password = password;
    }

    public User(String role, String name, String last_name, int cin, String email, int phone_number, java.sql.Date birth_date, String password) {
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.cin = cin;
        this.email = email;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.password = password;
    }

    public User(String role, String name, String last_name, int cin, String email, int phone_number, java.sql.Date birth_date) {
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.cin = cin;
        this.email = email;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
    }

    public User(String role, String name, String last_name, int cin, String email, int phone_number) {
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.cin = cin;
        this.email = email;
        this.phone_number = phone_number;

    }

    public User(int id, String role, String name, String last_name, int cin, String email, int phone_number, String password) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.cin = cin;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    User(int id, String name, String last_name) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
    }

    public User(int id, String role, String name, String last_name) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.last_name = last_name;
    }

    public User(int id, int cin, String first_name, String last_name, Date birth_date, int phone_number, String email, String password, String role, int CreatedBy) {
        this.id = id;
        this.cin = cin;
        this.name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.role = role;
        this.CreatedBy = CreatedBy;
    }
    
        public User(int cin, String first_name, String last_name, Date birth_date, int phone_number, String email, String password, String role, int CreatedBy) {
        this.cin = cin;
        this.name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.role = role;
        this.CreatedBy = CreatedBy;
    }

   
    public User(int id, int cin, String first_name, String last_name, Date birth_date, int phone_number, String email, String password, String role) {
        this.id = id;
        this.cin = cin;
        this.name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public User() {
    }

}
