/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Activity;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IActivity {
   
    public String getId(Activity act); 
    public void add(Activity activity);
    public void delete(String id);
    public void update(String id, Activity new_activity);
    public Activity details(String id);
    public List<Activity> listAll();
    
}
