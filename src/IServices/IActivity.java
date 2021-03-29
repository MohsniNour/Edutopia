/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Activity;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public interface IActivity {
   
    public int getId(Activity act);
    public List trierActivitiesID();
    public ObservableList<Activity> getActivities();
    public void add(Activity activity);
    public void remove(int id, String path);
    public void activate(int id, String path);
    public void update(int id, Activity new_activity);
    public Activity details(int id);
    public List<Activity> listAvailable();
    public List<Activity> listArchived();
    public String display(List<Activity> acts);
    
}
