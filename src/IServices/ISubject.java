/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Subjectt;
import javafx.collections.ObservableList;

/**
 *
 * @author Mrad
 */
public interface ISubject {
    public void addSubject(Subjectt s);
    public int getIdSubject(Subjectt s);
    public void remove(Subjectt s);
    public void update(Subjectt s);
    public Subjectt getSubject(String s,int id_classe);
    public ObservableList<Subjectt> getSubjects() ;
}
