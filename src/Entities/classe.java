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
    private int created_by;
    private Date created_date;
    private int last_update_by;
    private Date last_update_date;
    private int archived_by;
    private Date archived_date;

    public classe(int id, String name, int classe_size, int created_by, Date created_date, int last_update_by, Date last_update_date, int archived_by, Date archived_date) {
        this.id = id;
        this.name = name;
        this.classe_size = classe_size;
        this.created_by = created_by;
        this.created_date = created_date;
        this.last_update_by = last_update_by;
        this.last_update_date = last_update_date;
        this.archived_by = archived_by;
        this.archived_date = archived_date;
    }

    public classe() {
    }

    public classe(String name) {
        this.name = name;
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

    public int getCreated_by() {
        return created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public int getLast_update_by() {
        return last_update_by;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public int getArchived_by() {
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

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setLast_update_by(int last_update_by) {
        this.last_update_by = last_update_by;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setArchived_by(int archived_by) {
        this.archived_by = archived_by;
    }

    public void setArchived_date(Date archived_date) {
        this.archived_date = archived_date;
    }

    @Override
    public String toString() {
        return "classe" + "id=" + id + ", name=" + name + ", classe_size=" + classe_size + ", created_by=" + created_by + ", created_date=" + created_date + ", last_update_by=" + last_update_by + ", last_update_date=" + last_update_date + ", archived_by=" + archived_by + ", archived_date=" + archived_date + '}';
    }

}
