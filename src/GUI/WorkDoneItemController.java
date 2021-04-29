/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Student;
import Entities.Work_done;
import Services.ActivityService;
import Services.StudentService;
import Services.UserService;
import Services.Work_doneService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class WorkDoneItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private TextField ScoreValue;
    int Score=0;
    int id_Activity;
    int id_workDone;
    Date workDate;
    @FXML
    private Label CreatedByName;
    int test;
    public static Work_done work;
    public static Student s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CreatedByName.setWrapText(true);
    }

    public void setData(Work_done work) {
        this.work=work;
        UserService ss = new UserService();
//        s = (Student) ss.getUser(work.getUploaded_by());
//        System.out.println(s);
//        String ff = s.getName()+" "+s.getLast_name();
//        this.CreatedByName.setText(ff);
        this.id_workDone = work.getId();
        this.id_Activity = work.getId_activity();
        this.workDate = work.getUploaded_date();
    }

    @FXML
    private void OpenAction(ActionEvent event) {
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }

    @FXML
    private void UpdateScoreAction(ActionEvent event) {
        if (ScoreValue == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("La note est vide entrez une note ");
            alert.showAndWait();
        } else {
            ActivityService as = new ActivityService();
            Activity act = new Activity();
            act = as.FindActivityById(id_Activity);
            test=workDate.compareTo(act.getDeadline());
            //(workDate.before(act.getDeadline()))&&(workDate.equals(act.getDeadline()))
            if (test<=0) {
                System.out.println(act);
                Work_doneService ws = new Work_doneService();
                System.out.println(ScoreValue.getText());
                Score = Integer.parseInt(ScoreValue.getText()) + 10;
                ws.updateScore(id_Activity, Score);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Note ajoutée avec succès");
                Node node = (Node) event.getSource();
                Stage oldStage = (Stage) node.getScene().getWindow();
                oldStage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("List_User_ActivityDone.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    List_User_ActivityDoneController controller = loader.getController();
                    controller.initData(act);
                    stage.show();
                } catch (IOException e) {
                    System.err.println(String.format("Error: %s", e.getMessage()));
                }
            } else {
                Work_doneService ws = new Work_doneService();
                Score = Integer.parseInt(ScoreValue.getText());
                //System.out.println(Score);
                ws.updateScore(id_Activity, Score);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Note ajoutée avec succès");
                Node node = (Node) event.getSource();
                Stage oldStage = (Stage) node.getScene().getWindow();
                oldStage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("List_User_ActivityDone.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    List_User_ActivityDoneController controller = loader.getController();
                    controller.initData(act);
                    stage.show();
                } catch (IOException e) {
                    System.err.println(String.format("Error: %s", e.getMessage()));
                }
            }
        }
    }

}
