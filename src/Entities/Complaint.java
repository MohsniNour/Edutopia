/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;

/**
 *
 * @author Sabrina
 */
public class Complaint implements Serializable {
    
    public int id;
    public String object;
    public String description;
    public String status;

    public Complaint( String object, String description, String status) {
        
        this.object = object;
        this.description = description;
        this.status = status;
    }
    
    public Complaint ()
    {
    } 

    public void setId(int id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
     
}
