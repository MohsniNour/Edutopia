package entities;


import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mrad
 */
public class Specialty {
    private int id;
    private int id_resp;
    private String specialty;
    private List<String> niveaux;
    private String created_by;
    private java.sql.Date created_date;
    private String last_updated_by;
    private java.sql.Date last_update_date;
    private String archived_by;
    private java.sql.Date archived_date;
    private String status;
    
    public Specialty(){
        niveaux = new ArrayList<>();
    }
    
    public void addNiveau(String s){
        niveaux.add(s);
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
    public List getArrayNiveaux() {
        return niveaux;
    }
    public void setListNiveaux(String s){
        String str[] = s.split(",");
        this.niveaux = Arrays.asList(str);
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setId_resp(int id_resp) {
        this.id_resp = id_resp;
    }

    public void setSpecialty(String s) {
        this.specialty = s;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCreated_date(java.sql.Date created_date) {
        this.created_date = created_date;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setLast_update_date(java.sql.Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setArchived_by(String archived_by) {
        this.archived_by = archived_by;
    }

    public void setArchived_date(java.sql.Date archived_date) {
        this.archived_date = archived_date;
    }

    public int getId() {
        return id;
    }

    public int getId_resp() {
        return id_resp;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getCreated_by() {
        return created_by;
    }

    public java.sql.Date getCreated_date() {
        return created_date;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public java.sql.Date getLast_update_date() {
        return last_update_date;
    }

    public String getArchived_by() {
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
    
    
    @Override
    public String toString() {
        return "Specialties{" + "id=" + id + ", id_resp=" + id_resp + ", specialty=" + specialty + ", niveaux=" + getStringNiveaux() + ", created_by=" + created_by + ", created_date=" + created_date + ", last_updated_by=" + last_updated_by + ", last_update_date=" + last_update_date + ", archived_by=" + archived_by + ", archived_date=" + archived_date + ", status=" + status + '}';
    }

    
}
