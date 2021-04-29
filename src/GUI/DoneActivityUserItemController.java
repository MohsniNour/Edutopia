/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Work_done;
import Services.ActivityService;
import Services.Work_doneService;
import Utils.Config;
import Utils.Helpers;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class DoneActivityUserItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private Label Deadline;
    @FXML
    private Label ActivityName;
    @FXML
    private Label score;
    private Activity act;
    static int id_Activity;
    private Path to;
    private Path from;
    private Path fromUpdated;
    private Path removePath;
    File file = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Deadline.setWrapText(true);
        ActivityName.setWrapText(true);
    }

    public void setData(Activity act) {
        this.act = act;
        id_Activity = act.getId();
        String date = act.getDeadline().toString();
        Deadline.setText(date);
        ActivityName.setText(act.getName());
        Work_doneService ws = new Work_doneService();
        Work_done work = new Work_done();
        work = ws.FindWorkByIdActivity(id_Activity);
        score.setText(work.getScore() + "/30");
    }

    @FXML
    private void OpenActivityAction(ActionEvent event) {
        if (id_Activity != 0) {
            ActivityService as = new ActivityService();
            Activity act = new Activity();
            act = as.FindActivityById(id_Activity);
            String fileName = act.getWork_todo();
            Helpers.openWebpage(Config.BASE_URL2 + fileName);
        }
    }

    @FXML
    private void OpenWorkAction(ActionEvent event) {
        if (id_Activity != 0) {
            Work_doneService ws = new Work_doneService();
            Work_done work = new Work_done();
            System.out.println(id_Activity);
            work = ws.FindWorkByIdActivity(id_Activity);
            System.out.println(work);
            String fileName = work.getWork_file();
            Helpers.openWebpage(Config.BASE_URL2 + fileName);
        }
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }

}
