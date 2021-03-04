/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import IServices.ICo_Studying;
import java.io.File;

/**
 *
 * @author rayen
 */
public class Co_Studying {

    private int id;
    private String description;
    private File file;
    private String type;
    private String level;
    private int id_student;

    public Co_Studying(String description, String type, String level) {
        this.description = description;
        this.type = type;
        this.level = level;
 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public Co_Studying(String description, File file, String type, String level, int id_student) {
        this.description = description;
        this.file = file;
        this.type = type;
        this.level = level;
        this.id_student = id_student;
    }

    public Co_Studying() {
    }

}
