package Entities;

import java.sql.Date;

public class Exam {
        private int id;
        private String type;
        private Date start_date;
        private Date finish_date;
        private int id_subject;
        private String created_by ;   
        private Date  created_Date;
        private Date  archived_Date;
        private java.util.Date startdate;
        private String subject;

    public Exam(int id, String type, java.util.Date startdate, int id_subject) {
        this.id = id;
        this.type = type;
        this.startdate = startdate;
        this.id_subject = id_subject;
    }

    public java.util.Date getStartdate() {
        return startdate;
    }

    public void setStartdate(java.util.Date startdate) {
        this.startdate = startdate;
    }

    public Exam(String type, Date start_date, Date finish_date, int id_subject, Date created_Date, Date archived_Date) {
        this.type = type;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.id_subject = id_subject;
        this.created_Date = created_Date;
        this.archived_Date = archived_Date;
    }

    public Exam(int id, String type, Date start_date, Date finish_date, int id_subject, String created_by, Date created_Date, Date archived_Date) {
        this.id = id;
        this.type = type;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.id_subject = id_subject;
        this.created_by = created_by;
        this.created_Date = created_Date;
        this.archived_Date = archived_Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }


    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return  "Type=" + type + ",  Date de fin=" + finish_date + ", Sujet=" + id_subject + '}';
    }
     
      public Exam(int id, String type, java.util.Date startdate, String subjectString) {
        this.id = id;
        this.type = type;
        this.startdate = startdate;
        this.subject = subjectString;
    }
    
}
