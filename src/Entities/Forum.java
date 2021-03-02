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
    String id_course;
    List<Comment> comment;
    String created_by;
    Date ceated_date;
    String last_updated_by;
    Date last_updated_Date;
    String archived_by;
    Date archived_Date;

    public Forum(String subject, String id_course, List<Comment> comment, String created_by, Date ceated_date, String last_updated_by, Date last_updated_Date, String archived_by, Date archived_Date) {
        this.subject = subject;
        this.id_course = id_course;
        this.comment = comment;
        this.created_by = created_by;
        this.ceated_date = ceated_date;
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

    public String getId_course() {
        return id_course;
    }

    public void setId_course(String id_course) {
        this.id_course = id_course;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCeated_date() {
        return ceated_date;
    }

    public void setCeated_date(Date ceated_date) {
        this.ceated_date = ceated_date;
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
        return "Forum{" + "subject=" + subject + ", id_course=" + id_course + ", comment=" + comment + ", created_by=" + created_by + ", ceated_date=" + ceated_date + ", last_updated_by=" + last_updated_by + ", last_updated_Date=" + last_updated_Date + ", archived_by=" + archived_by + ", archived_Date=" + archived_Date + '}';
    }
    
    
}
