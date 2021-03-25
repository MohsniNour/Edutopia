/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.Serializable;
import java.util.Date;

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
    private Date birth_date;

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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", role=" + role + ", name=" + name + ", last_name=" + last_name + ", cin=" + cin + ", email=" + email + ", phone_number=" + phone_number + ", birth_date=" + birth_date + '}';
    }

    public User(String role, String name, String last_name, int cin, String email, int phone_number, Date birth_date) {
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

    public User() {
    }

}
