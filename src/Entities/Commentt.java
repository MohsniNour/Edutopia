/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Commentt {
    int id;
    String content;
    int id_forum;
    String status;
    int likes;
    int disLikes;
    int created_by;
    Date created_date;
    int last_updated_by;
    Date last_updated_Date;
    int archived_by;
    Date archived_Date;

    public Commentt() {
        this.status="Available";
    }
    
    

    public Commentt(String content, int id_forum, int created_by, Date created_date) {
        this.content = content;
        this.id_forum = id_forum;
        this.created_by = created_by;
        this.created_date = created_date;
        this.status="Available";
        this.likes=0;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_forum() {
        return id_forum;
    }

    public void setId_forum(int id_forum) {
        this.id_forum = id_forum;
    }
  

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int like) {
        this.likes = like;
    }

    public int getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(int disLikes) {
        this.disLikes = disLikes;
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

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
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

    @Override
    public String toString() {
        return "Commentt{" + "content=" + content + ", status=" + status + ", likes=" + likes + ", disLikes=" + disLikes + '}';
    }

    

    
    
}
