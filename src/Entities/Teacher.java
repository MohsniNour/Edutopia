/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.*;
import java.util.List;

/**
 *
 * @author Mrad
 */
public class Teacher {
    private int id=0;
    private String role = "teacher";
    private String name;
    private String lastname;
    private long cin;
    private String email;
    private int phone_number;
    private java.sql.Date birth_date;
    private Department dep;
    private String deps;
    private int depId;
    private String subject;
//    private List<Subject> subjects;
    private String status;
    private String created_by;
    private java.sql.Date created_date;
    private String last_updated_by;
    private java.sql.Date last_update_date;
    private String archived_by;
    private java.sql.Date archived_date;
    private int nbMatieres;

    public Teacher(){
//        subjects = new ArrayList<>();
    }

    public String getSubject(){
//        for (Subject s : subjects) {
//            subject+=s.getName()+", ";
//        }
//        return subject;
            return "test";
    }    

    public void setDeps(String deps) {
        this.deps = deps;
    }
    public int getNbMatiers(){
        String stri = subject.substring(1, subject.length() - 1);
        String str[] = stri.split(",");
        return str.length;
    }
    public String getDeps() {
        return deps;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getDepId() {
        return depId;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", role=" + role + ", name=" + name + ", lastname=" + lastname + ", cin=" + cin + ", email=" + email + ", phone_number=" + phone_number + ", birth_date=" + birth_date + ", dep=" + dep + ", deps=" + deps + ", depId=" + depId + ", subject=" + subject + ", subjects=" + /*subjects + */", status=" + status + ", created_by=" + created_by + ", created_date=" + created_date + ", last_updated_by=" + last_updated_by + ", last_update_date=" + last_update_date + ", archived_by=" + archived_by + ", archived_date=" + archived_date + '}';
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getCin() {
        return cin;
    }

    public void setCin(long cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(java.sql.Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public java.sql.Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(java.sql.Date created_date) {
        this.created_date = created_date;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public java.sql.Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(java.sql.Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public String getArchived_by() {
        return archived_by;
    }

    public void setArchived_by(String archived_by) {
        this.archived_by = archived_by;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public java.sql.Date getArchived_date() {
        return archived_date;
    }

    public void setArchived_date(java.sql.Date archived_date) {
        this.archived_date = archived_date;
    }

    public void addSubject(Object s){
//        subjects.add(s);
    }
    public void removeSubjects(Object s) {
//        subjects.remove(s);
    }
    public String getStringSubjects(){
        String list="";
//        for (Subject s : subjects) {
//            list=list+","+s.getName();
//        }
        return list;
    }
//    public List getArrayListSubjects() {
//        return subjects;
//    }
    public void setDepartment(Department d){
        this.dep=d;
    }
    public Department getDepartment(){
        return dep;
    }
    public void setStatus(String s){
        this.status=s;
    }
    public String getStatus(){
        return status;
    }
}
