/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author lenovo
 */
public class seance {

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

 
   
    private int duree;
    private Date date;
    private String url;
    private String matiere;
    private int id;
    private classe classe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public seance(int duree, Date date, String url, String matiere,int id) {
        this.duree = duree;
        this.date = date;
        this.url = url;
        this.matiere = matiere;
        this.id=id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
  
  
 
}
