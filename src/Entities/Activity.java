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
public class Activity {
    
    String id;
    String name;
    Date deadline;
    File work_todo;
    File work_done;
    String id_course;
    List<Comment> comment;
    String status;
    String created_by;
    Date created_date;
    String last_updated_by;
    Date last_updated_Date;
    String archived_by;
    Date archived_Date;
    String Uploated_by;
    Date Uploated_date;

    public Activity() {
    }
    

    public Activity(String name, Date deadline, File work_todo, String id_course, String created_by) {
        this.name = name;
        this.deadline = deadline;
        this.work_todo = work_todo;
        this.id_course = id_course;
        this.created_by = created_by;
        this.status = "active";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public File getWork_todo() {
        return work_todo;
    }

    public void setWork_todo(File work_todo) {
        this.work_todo = work_todo;
    }

    public File getWork_done() {
        return work_done;
    }

    public void setWork_done(File work_done) {
        this.work_done = work_done;
    }

    public String getId_Course() {
        return id_course;
    }

    public void setId_Course(String id_Course) {
        this.id_course = id_Course;
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

    public void setCreated_date(Date ceated_date) {
        this.created_date = ceated_date;
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

    public String getId_course() {
        return id_course;
    }

    public void setId_course(String id_course) {
        this.id_course = id_course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUploated_by() {
        return Uploated_by;
    }

    public void setUploated_by(String Uploated_by) {
        this.Uploated_by = Uploated_by;
    }

    public Date getUploated_date() {
        return Uploated_date;
    }

    public void setUploated_date(Date Uploated_date) {
        this.Uploated_date = Uploated_date;
    }
    
    @Override
    public String toString() {
        return "Activity{" + "name=" + name + ", deadline=" + deadline + ", work_todo=" + work_todo + ", work_done=" + work_done + ", id_course=" + id_course + ", created_by=" + created_by + ", ceated_date=" + created_date + ", last_updated_by=" + last_updated_by + ", last_updated_Date=" + last_updated_Date + ", archived_by=" + archived_by + ", archived_Date=" + archived_Date + '}';
    }

    
}
