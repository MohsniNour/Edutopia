/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Student;
import Entities.Work_done;
import Services.UserService;
import java.net.URL;
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
public class WorkDoneRatedItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private Label CreatedByName;
    @FXML
    private Label Score;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CreatedByName.setWrapText(true);
        Score.setWrapText(true);
    }    
    public void setData(Work_done work) {
//        UserService ss = new UserService();
//        this.CreatedByName.setText(s.getName()+" "+s.getLast_name());
        this.Score.setText(work.getScore()+"/30");
    }

    @FXML
    private void OpenAction(ActionEvent event) {
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }
    
}
