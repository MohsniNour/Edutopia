package Entities;

import java.sql.Date;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class classe {

    private int id;
    private String name;
    private int classe_size;
    private String created_by;
    private Date created_date;
    private String last_update_by;
    private Date last_update_date;
    private String archived_by;
    private Date archived_date;

    public classe(int id, String name, int classe_size, String created_by, Date created_date, String last_update_by, Date last_update_date, String archived_by, Date archived_date) {
        this.name = name;
        this.id = id;
        this.classe_size = classe_size;
        this.created_by = created_by;
        this.created_date = created_date;
        this.last_update_by = last_update_by;
        this.last_update_date = last_update_date;
        this.archived_by = archived_by;
        this.archived_date = archived_date;
    }

    public classe(int id_classe) {
        this.id = id_classe;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClasse_size() {
        return classe_size;
    }

    public String getCreated_by() {
        return created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public String getLast_update_by() {
        return last_update_by;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public String getArchived_by() {
        return archived_by;
    }

    public Date getArchived_date() {
        return archived_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClasse_size(int classe_size) {
        this.classe_size = classe_size;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setArchived_by(String archived_by) {
        this.archived_by = archived_by;
    }

    public void setArchived_date(Date archived_date) {
        this.archived_date = archived_date;
    }

    public classe() {
    }

    @Override
    public String toString() {
        return "classe" + "id=" + id + ", name=" + name + ", classe_size=" + classe_size + ", created_by=" + created_by + ", created_date=" + created_date + ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + ", archived_by=" + archived_by + ", archived_date=" + archived_date + '}';
    }

}
