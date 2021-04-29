/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Student;
import Entities.classe;
import Entities.seance;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author rayen
 */
public interface IClasse {

    public classe getClasseId(String classe);

    public void showclasse();

    public void showseance();

    public void showetudiant();

    public ObservableList<classe> getclasseList();

    public ObservableList<seance> getseanceList();

    public ObservableList<Student> getetudiantList();

    public ObservableList<classe> search(String input);

    public ObservableList<Student> search1(String input);

    public void searchkey1(KeyEvent event);

    public void searchkey(KeyEvent event);

    public void insertRecord();

    public void insertseance();

    public void updateRecord();

    public void updateseance();

    public void deleteButton();

    public void deleteseance();

}
