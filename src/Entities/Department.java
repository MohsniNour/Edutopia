/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mrad
 */
public class Department {
    private int id=0;
    private String name;
    private int ownerId;
    private String ownername;
    private String ownerlastname;
    private String created_by;
    private Date created_date;
    private String last_updated_by;
    private Date last_update_date;
    private String archived_by;
    private Date archived_date;
    private String status;
    private List<String> specialties;
    private String spec="";
    
    

    /**
     * @return the id
     */
    public Department() {
        specialties = new ArrayList<>();
//        specialties.add("tronc commun");
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    public void setSpec(){
        specialties.forEach((s) -> {
            spec=spec+s+",";
        });
    }
    public String getSpec(){
        return spec;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }

    /**
     * @param aId the id to set
     */
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getOwnername() {
        return ownername;
    }

    public String getOwnerlastname() {
        return ownerlastname;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public void setOwnerlastname(String ownerlastname) {
        this.ownerlastname = ownerlastname;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }


    /**
     * @return the created_date
     */
    public Date getCreated_date() {
        return created_date;
    }

    /**
     * @param created_date the created_date to set
     */
    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

  

    /**
     * @return the last_update_date
     */
    public Date getLast_update_date() {
        return last_update_date;
    }

    /**
     * @param last_update_date the last_update_date to set
     */
    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    /**
     * @return the archived_by
     */
    public String getArchived_by() {
        return archived_by;
    }

    /**
     * @param archived_by the archived_by to set
     */
    public void setArchived_by(String archived_by) {
        this.archived_by = archived_by;
    }

    /**
     * @return the archived_date
     */
    public Date getArchived_date() {
        return archived_date;
    }

    /**
     * @param archived_date the archived_date to set
     */
    public void setArchived_date(Date archived_date) {
        this.archived_date = archived_date;
    }
    public void setStatus(String s){
        this.status=s;
    }
    public String getStatus(){
        return this.status;
    }
    public void addSpecialty(String s){
        specialties.add(s);
    }
    public void removeSpecialty(String s) {
        specialties.remove(s);
    }
    public String getStringSpecialties(){
        String list="";
        for (String s : specialties) {
            list=list+s+",";
        }
        return list;
    }
    public List getArrayListSpecialties() {
        return specialties;
    }
    public void setList(String s){
        String str[] = s.split(",");
        this.specialties = Arrays.asList(str);
    }
    public void setListSpec(List<String> l) {
        this.specialties=l;
    }
}
