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
   
    public String getId(Activity act);
    public List trierActivitiesID();
    public ObservableList<Activity> getActivities();
    public void add(Activity activity);
    public void remove(String id, String path);
    public void activate(String id, String path);
    public void update(String id, Activity new_activity);
    public Activity details(String id);
    public List<Activity> listAvailable();
    public List<Activity> listArchived();
    public String display(List<Activity> acts);
    
}
