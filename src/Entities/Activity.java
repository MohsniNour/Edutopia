/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Activity {
    
    int id;
    String name;
    Date deadline;
    String work_todo;
    List<Work_done> work_done;
    int id_course;
    List<Commentt> comment;
    String status;
    int created_by;
    Date created_date;
    int last_updated_by;
    Date last_updated_Date;
    int archived_by;
    Date archived_Date;

    public Activity() {
        this.status = "Available";
    }
    public Activity(String name, int id_course, int created_by) {
        this.name = name;
        this.id_course = id_course;
        this.created_by = created_by;
        this.status = "active";
    }

    public Activity(String name, Date deadline, String work_todo, int id_course, int created_by, Date created_date) {
        this.name = name;
        this.deadline = deadline;
        this.work_todo = work_todo;
        this.id_course = id_course;
        this.created_by = created_by;
        this.created_date = created_date;
        this.status = "Available";
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getWork_todo() {
        return work_todo;
    }

    public void setWork_todo(String work_todo) {
        this.work_todo = work_todo;
    }

    public List<Work_done> getWork_done() {
        return work_done;
    }

    public void setWork_done(List<Work_done> work_done) {
        this.work_done = work_done;
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

    

    public int getId_Course() {
        return id_course;
    }

    public void setId_Course(int id_Course) {
        this.id_course = id_Course;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date ceated_date) {
        this.created_date = ceated_date;
    }

    public int getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(int last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public Date getLast_updated_Date() {
        return last_updated_Date;
    }

    public void setLast_updated_Date(Date last_updated_Date) {
        this.last_updated_Date = last_updated_Date;
    }

    public int getArchived_by() {
        return archived_by;
    }

    public void setArchived_by(int archived_by) {
        this.archived_by = archived_by;
    }

    public Date getArchived_Date() {
        return archived_Date;
    }

    public void setArchived_Date(Date archived_Date) {
        this.archived_Date = archived_Date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Activity{" + "name=" + name + ", deadline=" + deadline + ", work_todo=" + work_todo + ", work_done=" + work_done + ", id_course=" + id_course + ", comment=" + comment + ", status=" + status + ", created_by=" + created_by + ", created_date=" + created_date + ", last_updated_by=" + last_updated_by + ", last_updated_Date=" + last_updated_Date + ", archived_by=" + archived_by + ", archived_Date=" + archived_Date +'}'+"\n";
    }

    
}

