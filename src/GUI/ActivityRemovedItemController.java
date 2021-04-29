/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import static GUI.ActivityItemController.id_Activity;
import Services.ActivityService;
import Utils.Config;
import Utils.Helpers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ActivityRemovedItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private Label Deadline;
    @FXML
    private Label ActivityName;
    private Activity act;
    static int id_Activity;
    File file = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Deadline.setWrapText(true);
        ActivityName.setWrapText(true);
    }    

    public void setData(Activity act) {
        this.act = act;
        id_Activity=act.getId();
        String date = act.getDeadline().toString();
        Deadline.setText(date);
        ActivityName.setText(act.getName());

    }


    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }

    @FXML
    private void ActivateAction(ActionEvent event) throws IOException {
        ActivityService as = new ActivityService();
        as.activate(act.getId());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity.fxml"));
            stage.setScene(new Scene(loader.load()));
            List_ActivityController controller = loader.getController();
            controller.initData(act);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void OpenAction(ActionEvent event) {
        if (id_Activity != 0) {
            ActivityService as = new ActivityService();
            Activity act = new Activity();
            act = as.FindActivityById(id_Activity);
            String fileName = act.getWork_todo();
            Helpers.openWebpage(Config.BASE_URL2 + fileName);
        }
    }
    
}
