/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

/**
 *
 * @author lenovo
 */
public class etudiant {
    private String nom;
    private String prenom;
    private String classe;
    private Spinner etat ;
    private Integer nbp ;

    public etudiant(String nom, String prenom, String classe,Integer nbp) {
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.etat = new Spinner();
        this.nbp= nbp;
    }

   public  etudiant() {
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Spinner getEtat() {
        return etat;
    }

    public Integer getNbp() {
        return nbp;
    }

    public void setNbp(Integer nbp) {
        this.nbp = nbp;
    }

    public void setEtat(Spinner etat) {
        this.etat = etat;
    }
    
    
}

