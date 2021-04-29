
package Entities;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Cours {

private int id ;
private String name;
private File course ;
private int seen ;
private int id_Subject;
private String created_by;
private Date ceated_date;
private int last_updated_by;
private Date last_updated_Date;
private int archived_by;
private Date archived_Date;

    public Cours() {
    }

    public Cours(String name, File course, int id_Subject, String created_by, Date ceated_date) {
        this.name = name;
        this.course = course;
        this.id_Subject = id_Subject;
        this.created_by = created_by;
        this.ceated_date = ceated_date;
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

    public File getCourse() {
        return course;
    }

    public void setCourse(File course) {
        this.course = course;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCeated_date() {
        return ceated_date;
    }

    public void setCeated_date(Date ceated_date) {
        this.ceated_date = ceated_date;
    }

    public int getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(int last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public Date getLast_updated_Date() {
        return last_updated_Date;
    }

    public void setLast_updated_Date(Date last_updated_Date) {
        this.last_updated_Date = last_updated_Date;
    }

    public int getArchived_by() {
        return archived_by;
    }

    public void setArchived_by(int archived_by) {
        this.archived_by = archived_by;
    }

    public Date getArchived_Date() {
        return archived_Date;
    }

    public void setArchived_Date(Date archived_Date) {
        this.archived_Date = archived_Date;
    }



    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(int id_Subject) {
        this.id_Subject = id_Subject;
    }

    @Override
    public String toString() {
        return "Cours{" + "name=" + name + ", course=" + course + ", seen=" + seen + ", id_Subject=" + id_Subject + ", created_by=" + created_by + ", ceated_date=" + ceated_date + ", last_updated_by=" + last_updated_by + ", last_updated_Date=" + last_updated_Date + ", archived_by=" + archived_by + ", archived_Date=" + archived_Date + '}';
    }





  
    
}
