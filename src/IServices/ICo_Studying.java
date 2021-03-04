/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Co_Studying;
import java.util.List;

/**
 *
 * @author rayen
 */
public interface ICo_Studying {
    public void addCostudying(Co_Studying p);
    public void deleteCostudying(Co_Studying p);
    public void editCostudying(int id, String object, Object obj);
    public List<Co_Studying> listStudent();
}
