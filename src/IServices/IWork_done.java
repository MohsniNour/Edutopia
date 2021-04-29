/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Work_done;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IWork_done {
    
    public int getId(Work_done work_done);
    public List trierWorkDoneID();
//    public ObservableList<Work_done> getWorkDone();
    public void add(Work_done work_done);
    public void remove(int id, String path);
    public void activate(int id, String path);
    public void updateScore(int id, int score);
    public Work_done details(int id);
    
}
