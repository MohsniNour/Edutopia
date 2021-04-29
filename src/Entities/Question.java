package Entities;

import java.sql.Date;

public class Question {
       private int id;
       private String question;
       private String proposition1;
       private String proposition2;
       private String proposition3;
       private String proposition4;
       private Date archived_Date;
       private String bonnereponse;
       private Exam exam;
       private int id_Exam;

    public Question(String question, String proposition1, String proposition2, String proposition3, String proposition4, Date archived_Date, String bonnereponse, Exam exam) {
        this.question = question;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.archived_Date = archived_Date;
        this.bonnereponse = bonnereponse;
        this.exam = exam;
    }
     
       
       
    public Question(String question, String proposition1, String proposition2, String proposition3, String proposition4, Date archived_Date, int id_Exam) {
        this.question = question;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.archived_Date = archived_Date;
        this.id_Exam = id_Exam;
    }
  public Question(String question, String proposition1, String proposition2, String proposition3, String proposition4,  int id_Exam) {
        this.question = question;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.id_Exam = id_Exam;
    }
    public Question(int id, String question, String proposition1, String proposition2, String proposition3, String proposition4, Date archived_Date, int id_Exam , String bonnereponse) {
        this.id = id;
        this.question = question;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.archived_Date = archived_Date;
        this.id_Exam = id_Exam;
        this.bonnereponse=  bonnereponse;
    }

    public Question(int id, String question, String proposition1, String proposition2, String proposition3, String proposition4, String bonnereponse, Exam exam) {
        this.id = id;
        this.question = question;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.bonnereponse = bonnereponse;
        this.exam = exam;
    }
    
    

    public String getBonnereponse() {
        return bonnereponse;
    }

    public void setBonnereponse(String bonnereponse) {
        this.bonnereponse = bonnereponse;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getProposition1() {
        return proposition1;
    }

    public void setProposition1(String proposition1) {
        this.proposition1 = proposition1;
    }

    public String getProposition2() {
        return proposition2;
    }

    public void setProposition2(String proposition2) {
        this.proposition2 = proposition2;
    }

    public String getProposition3() {
        return proposition3;
    }

    public void setProposition3(String proposition3) {
        this.proposition3 = proposition3;
    }

    public String getProposition4() {
        return proposition4;
    }

    public void setProposition4(String proposition4) {
        this.proposition4 = proposition4;
    }

    public Date getArchived_Date() {
        return archived_Date;
    }

    public void setArchived_Date(Date archived_Date) {
        this.archived_Date = archived_Date;
    }

    public int getId_Exam() {
        return id_Exam;
    }

    public void setId_Exam(int id_Exam) {
        this.id_Exam = id_Exam;
    }
            
}