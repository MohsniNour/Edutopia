/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import Services.ActivityService;
import java.io.File;
import static java.lang.System.in;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Test {
    
    public static void main(String[]args){
//        File f = new File("c:\\filetest.txt");
        Activity a3 = new Activity();
        a3.setName("td4");
        a3.setDeadline(java.sql.Date.valueOf(java.time.LocalDate.now()));
        //a3.setWork_todo(f);
        a3.setId_Course(5);
        a3.setStatus(a3.getStatus());
        a3.setLast_updated_by(1);
        a3.setLast_updated_Date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        ActivityService service = new ActivityService();
        //service.update("3", a3);
        service.add(a3);
        //System.out.println(service.getId(a2));
        //System.out.println(service.details("1"));
//          ActivityService service = new ActivityService();
////          System.out.println(service.display(service.listArchived()));
//          service.activate("1");

    }
}
