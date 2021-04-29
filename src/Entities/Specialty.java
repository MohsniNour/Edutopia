/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.*;

/**
 *
 * @author Mrad
 */
public class Specialty {
     private int id;
    private int idTeacher;
    private String specialty;
    private String name;
    private String lastname;
    private List<String> niveaux;
    private String niveau;
    private int created_by;
    private java.sql.Date created_date;
    private int last_updated_by;
    private java.sql.Date last_update_date;
    private int archived_by;
    private java.sql.Date archived_date;
    private String status;
    
    public Specialty(){
        niveaux = new ArrayList<>();
    }
    
    public void addNiveau(String s){
        niveaux.add(s);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }
    
    public void removeNiveau(String s){
        niveaux.remove(s);
    }
    public String getStringNiveaux(){
        String list="";
        
        for (String s : niveaux) {
            list=list+","+s;
        }
        return list;
    }
    public String getNiveau(){
        return this.niveau;
    }
    public List getArrayNiveaux() {
        return niveaux;
    }
    public void setListNiveaux(String s){
        String str[] = s.split(",");
        this.niveaux = Arrays.asList(str);
        this.niveau=s;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setId_resp(int id_resp) {
        this.idTeacher = id_resp;
    }

    public void setSpecialty(String s) {
        this.specialty = s;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public void setCreated_date(java.sql.Date created_date) {
        this.created_date = created_date;
    }

    public void setLast_updated_by(int last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setLast_update_date(java.sql.Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setArchived_by(int archived_by) {
        this.archived_by = archived_by;
    }

    public void setArchived_date(java.sql.Date archived_date) {
        this.archived_date = archived_date;
    }

    public int getId() {
        return id;
    }

    public int getId_resp() {
        return idTeacher;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getCreated_by() {
        return created_by;
    }

    public java.sql.Date getCreated_date() {
        return created_date;
    }

    public int getLast_updated_by() {
        return last_updated_by;
    }

    public java.sql.Date getLast_update_date() {
        return last_update_date;
    }

    public int getArchived_by() {
        return archived_by;
    }

    public java.sql.Date getArchived_date() {
        return archived_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Specialty{" + "id=" + id + ", idTeacher=" + idTeacher + ", specialty=" + specialty + ", niveaux=" + niveaux + ", niveau=" + niveau + ", created_by=" + created_by + ", created_date=" + created_date + ", last_updated_by=" + last_updated_by + ", last_update_date=" + last_update_date + ", archived_by=" + archived_by + ", archived_date=" + archived_date + ", status=" + status + '}';
    }

    
    
    
    
}
