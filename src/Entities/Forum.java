/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Forum {
    
    String id;
    String subject;
    List<Commentt> comment;
    String status;
    int id_course;
    String created_by;
    Date created_date;
    String last_updated_by;
    Date last_updated_Date;
    String archived_by;
    Date archived_Date;

    public Forum() {
        this.status = "Available" ;
    }

    public Forum(String subject, int id_course, String created_by, Date created_date) {
        this.subject = subject;
        this.id_course = id_course;
        this.created_by = created_by;
        this.created_date = created_date;
        this.status = "Available" ;
    }
    

    public Forum(String subject, int id_course, List<Commentt> comment, String created_by, Date created_date, String last_updated_by, Date last_updated_Date, String archived_by, Date archived_Date) {
        this.subject = subject;
        this.id_course = id_course;
        this.comment = comment;
        this.created_by = created_by;
        this.created_date = created_date;
        this.last_updated_by = last_updated_by;
        this.last_updated_Date = last_updated_Date;
        this.archived_by = archived_by;
        this.archived_Date = archived_Date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    public List<Commentt> getComment() {
        return comment;
    }

    public void setComment(List<Commentt> comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public Date getLast_updated_Date() {
        return last_updated_Date;
    }

    public void setLast_updated_Date(Date last_updated_Date) {
        this.last_updated_Date = last_updated_Date;
    }

    public String getArchived_by() {
        return archived_by;
    }

    public void setArchived_by(String archived_by) {
        this.archived_by = archived_by;
    }

    public Date getArchived_Date() {
        return archived_Date;
    }

    public void setArchived_Date(Date archived_Date) {
        this.archived_Date = archived_Date;
    }

    @Override
    public String toString() {
        return "Forum{" + "subject=" + subject + ", id_course=" + id_course + ", comment=" + comment + ", created_by=" + created_by + ", created_date=" + created_date + ", last_updated_by=" + last_updated_by + ", last_updated_Date=" + last_updated_Date + ", archived_by=" + archived_by + ", archived_Date=" + archived_Date + '}'+"\n";
    }
    
    
}
