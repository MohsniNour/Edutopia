/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Work_done;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class DetailsWork_doneController implements Initializable {
    
    private int id_Activity;
    @FXML
    private AnchorPane addWorkActivity;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private Label ActivityName;
    @FXML
    private Label worktodo;
    @FXML
    private Label workDone;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    void initData(Activity act, Work_done work) {
        ActivityName.setText(act.getName());
        worktodo.setText(act.getWork_todo());
        workDone.setText(work.getWork_file());
        id_Activity = act.getId();
        //showActivities();
    }

    @FXML
    private void HomeAction(ActionEvent event) {
    }

    @FXML
    private void DepartmentAction(ActionEvent event) {
    }

    @FXML
    private void ClassAction(ActionEvent event) {
    }

    @FXML
    private void CourseAction(ActionEvent event) {
    }

    @FXML
    private void ExamAction(ActionEvent event) {
    }

    @FXML
    private void CalendarAction(ActionEvent event) {
    }

    @FXML
    private void Co_StudyingAction(ActionEvent event) {
    }

    @FXML
    private void AccountAction(ActionEvent event) {
    }

    @FXML
    private void ClaimAction(ActionEvent event) {
    }

    @FXML
    private void DisconnectionAction(ActionEvent event) {
    }


    @FXML
    private void BackAction(ActionEvent event) {
    }

    @FXML
    private void UpdateScoreAction(ActionEvent event) {
    }
    
}
