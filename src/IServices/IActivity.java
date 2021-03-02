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
    
    public void add(Activity activity);
    public void delete(Activity activity);
    public void edit(int id, Activity new_activity);
    public void details(int id);
    public List<Activity> listAll();
    
}
