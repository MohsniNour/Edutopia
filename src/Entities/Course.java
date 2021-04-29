package Entities;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;

public class Course {

    private int id;
    private String name;
    private String course;
    private int id_subject;
    private int created_by;
    private Date created_Date;
    private Date archived_Date;
    private String Description;

    public Course(int id, String name, int id_subject, int created_by, Date created_Date, String Description, String is) {
        this.id = id;
        this.name = name;
        this.id_subject = id_subject;
        this.created_by = created_by;
        this.created_Date = created_Date;
        this.Description = Description;
        this.course = is;
    }

    public Course(int id, String name, int id_subject, int created_by, Date created_Date, Date archived_Date, String Description) {
        this.id = id;
        this.name = name;
        this.id_subject = id_subject;
        this.created_by = created_by;
        this.created_Date = created_Date;
        this.archived_Date = archived_Date;
        this.Description = Description;

    }

    public Course(String name, String course, int id_subject, int created_by, Date created_Date, String Description) {
        this.name = name;
        this.course = course;
        this.id_subject = id_subject;
        this.created_by = created_by;
        this.created_Date = created_Date;
        this.Description = Description;
    }

    public Course(String name, int id_subject, int created_by, Date created_Date, Date archived_Date, String Description) {
        this.name = name;
        this.id_subject = id_subject;
        this.created_by = created_by;
        this.created_Date = created_Date;
        this.archived_Date = archived_Date;
        this.Description = Description;
    }

    public Course(String name, String course, int id_subject, int created_by, Date created_Date, Date archived_Date, String Description) {
        this.name = name;
        this.course = course;
        this.id_subject = id_subject;
        this.created_by = created_by;
        this.created_Date = created_Date;
        this.archived_Date = archived_Date;
        this.Description = Description;
    }

    public Course(String name, String course, int id_subject, String Description) {
        this.name = name;
        this.course = course;
        this.id_subject = id_subject;
        this.Description = Description;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }

    public Date getArchived_Date() {
        return archived_Date;
    }

    public void setArchived_Date(Date archived_Date) {
        this.archived_Date = archived_Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }



}
