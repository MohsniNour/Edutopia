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
class Comment {

    String id;
    String content;
    String id_post;
    String created_by;
    Date ceated_date;
    String last_updated_by;
    Date last_updated_Date;
    String archived_by;
    Date archived_Date;

    public Comment(String content, String id_post, String created_by, Date ceated_date) {
        this.content = content;
        this.id_post = id_post;
        this.created_by = created_by;
        this.ceated_date = ceated_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
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
        return "Comment{" + "content=" + content + ", id_post=" + id_post + ", created_by=" + created_by + ", ceated_date=" + ceated_date + ", last_updated_by=" + last_updated_by + ", last_updated_Date=" + last_updated_Date + ", archived_by=" + archived_by + ", archived_Date=" + archived_Date + '}';
    }
    
    

}
