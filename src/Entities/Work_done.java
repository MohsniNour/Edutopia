/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Work_done {
    
    int id;
    String work_file;
    String status;
    int score;
    int id_activity;
    String uploaded_by;
    Date uploaded_date;
    String last_updated_by;
    Date last_updated_Date;
    String archived_by;
    Date archived_Date;

    public Work_done() {
        this.status="Work_missing";
    }

    public Work_done(String work_file, String status, int id_activity, String uploaded_by, Date uploaded_date) {
        this.work_file = work_file;
        this.status = status;
        this.id_activity = id_activity;
        this.uploaded_by = uploaded_by;
        this.uploaded_date = uploaded_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWork_file() {
        return work_file;
    }

    public void setWork_file(String work_file) {
        this.work_file = work_file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId_activity() {
        return id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }


    public String getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(String uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public Date getUploaded_date() {
        return uploaded_date;
    }

    public void setUploaded_date(Date uploaded_date) {
        this.uploaded_date = uploaded_date;
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
        return "Work_done{" + "work_file=" + work_file + ", status=" + status + ", score=" + score + ", id_activity=" + id_activity + ", uploaded_by=" + uploaded_by + ", uploaded_date=" + uploaded_date + '}';
    }
    
    
    
    
}
