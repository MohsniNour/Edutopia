/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author rayen
 */
public class Co_Studying extends RecursiveTreeObject<Co_Studying> implements Serializable {

    private int id;
    private String description;
    private File file;
    private String type;
    private String level;
    private int rating;
    private User id_student;

    public Co_Studying(String description, String type, String level) {
        this.description = description;
        this.type = type;
        this.level = level;

    }

    public Co_Studying(String description, String type, String level, User student) {
        this.description = description;
        this.type = type;
        this.level = level;
        this.id_student = student;
    }

    public Co_Studying(String description, String type, String level, int rating) {
        this.description = description;
        this.type = type;
        this.level = level;
        this.rating = rating;
    }

    public Co_Studying(String description, String type, String level, User u, int rating) {
        this.description = description;
        this.type = type;
        this.level = level;
        this.id_student = u;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getId_student() {
        return id_student;
    }

    public void setId_student(User id_student) {
        this.id_student = id_student;
    }

    public Co_Studying(String description, File file, String type, String level, User id_student) {
        this.description = description;
        this.file = file;
        this.type = type;
        this.level = level;
        this.id_student = id_student;
    }

    public Co_Studying() {
    }

    public Co_Studying(int id, String description, String type, String level) {
        this.description = description;
        this.type = type;
        this.level = level;

    }

}
