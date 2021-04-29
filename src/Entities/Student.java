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
public class Student extends User {

    private classe classe;
    private int nbabsece;
    private String mail_parent;
    private String classe_name;

    public Student(int id, String name, String last_name, String classe_name, int nbabsece, String mail_parent) {
        super(id, name, last_name);
        this.classe_name = classe_name;
        this.nbabsece = nbabsece;
        this.mail_parent = mail_parent;
        
    }

    public String getClasse_name() {
        return classe_name;
    }

    public void setClasse_name(String classe_name) {
        this.classe_name = classe_name;
    }

    public Student(int id, String name, String last_name, int nbabsece, String mail_parent, String classe_name) {
        super(id, name, last_name);
        this.nbabsece = nbabsece;
        this.mail_parent = mail_parent;
        this.classe_name = classe_name;
    }

    public Student(classe classe) {

        this.classe = classe;
    }

    public Student(int id, String name, String last_name, int nbabsece, String mail_parent, classe classe) {
        super(id, name, last_name);
        this.nbabsece = nbabsece;
        this.mail_parent = mail_parent;
        this.classe = classe;

    }

    public int getNbabsece() {
        return nbabsece;
    }

    public void setNbabsece(int nbabsece) {
        this.nbabsece = nbabsece;
    }

    public String getMail_parent() {
        return mail_parent;
    }

    public void setMail_parent(String mail_parent) {
        this.mail_parent = mail_parent;
    }

    public classe getClasse() {
        return classe;
    }

    public void setClasse(classe classe) {
        this.classe = classe;
    }

    public Student(String role, String name, String last_name, int cin, String email, int phone_number, java.sql.Date birth_date, classe classe) {
        super(role, name, last_name, cin, email, phone_number, birth_date);
        this.classe = classe;
    }

    public Student(int id, String role, String name, String last_name, int cin, String email, int phone_number, String password) {
        super(id, role, name, last_name, cin, email, phone_number, password);
    }

    public Student(int id, String role, String name, String last_name, int cin, String email, int phone_number, java.sql.Date birth_date, String password) {
        super(id, role, name, last_name, cin, email, phone_number, birth_date, password);
    }

    public Student(classe classe, int id, String role, String name, String last_name, int cin, String email, int phone_number, java.sql.Date birth_date, String password) {
        super(id, role, name, last_name, cin, email, phone_number, birth_date, password);
        this.classe = classe;
    }

    public Student() {
    }

}
