/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Mrad
 */
public class Subjectt {

    private int id;
    private String id_Subject;
    private String courses;
    private int id_class;
    private String nomClasse;
    private int id_teacher;
    private String nomTeacher;
    private int created_by;
    private java.sql.Date created_date;
    private int last_updated_by;
    private java.sql.Date last_update_date;
    private int archived_by;
    private java.sql.Date archived_date;

    public String getNomClasse() {
        return nomClasse;
    }

    public String getNomTeacher() {
        return nomTeacher;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public void setNomTeacher(String nomTeacher) {
        this.nomTeacher = nomTeacher;
    }
    
    public int getId() {
        return id;
    }

    public String getCourses() {
        return courses;
    }

    public int getId_class() {
        return id_class;
    }
    
    public int getId_teacher() {
        return id_teacher;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_Subject() {
        return id_Subject;
    }
   
    public void setId_Subject(String name) {
        this.id_Subject = name;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public java.sql.Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(java.sql.Date created_date) {
        this.created_date = created_date;
    }

    public int getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(int last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public java.sql.Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(java.sql.Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getArchived_by() {
        return archived_by;
    }

    public void setArchived_by(int archived_by) {
        this.archived_by = archived_by;
    }

    public Date getArchived_date() {
        return archived_date;
    }

    public void setArchived_date(Date archived_date) {
        this.archived_date = archived_date;
    }
   
    
}
