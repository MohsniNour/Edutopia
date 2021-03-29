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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class List_Work_doneController implements Initializable {

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
    private TableView<Work_done> TableView;
    @FXML
    private TableColumn<Work_done, String> work_done;
    @FXML
    private TableColumn<Work_done, String> StudentName;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label ActivityName;
    private int id_Activity;
    private Path to;
    private Path from;
//    private Path fromUpdated;
//    private Path removePath;
    File file = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    void initData(Activity act) {
        ActivityName.setText(act.getName());
        UserName.setText(act.getName());
        id_Activity = act.getId();
        showActivities();
    }

    public void showActivities() {
        Work_doneService as = new Work_doneService();
        ArrayList<Work_done> al = as.getWorkDoneListByIdActivity(id_Activity);
        ObservableList<Work_done> oL = FXCollections.observableArrayList(al);
        work_done.setCellValueFactory(new PropertyValueFactory("work_file"));
        StudentName.setCellValueFactory(new PropertyValueFactory("uploaded_by"));
        TableView.setItems(oL);
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
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Course_User.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Liste des cours");
            List_CourseController controller = loader.getController();
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
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
    private void SearchAction(ActionEvent event) {
    }

    @FXML
    private void DetailsAction(ActionEvent event) {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un travail rendu ");
            alert.showAndWait();
        } else {
            Activity act = new Activity();
            ActivityService as=new ActivityService();
            act=as.FindActivityById(id_Activity);
            Work_done work = TableView.getSelectionModel().getSelectedItem();
            System.out.println(work.getId());
            Work_doneService ws = new Work_doneService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWork_done.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Details travail rendu");
                DetailsWork_doneController controller = loader.getController();
                controller.initData(act,work);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }


    @FXML
    private void RefreshAction(ActionEvent event) {
    }

    @FXML
    private void BackAction(ActionEvent event) {
        Activity act=new Activity();
        ActivityService as= new ActivityService();
        act=as.FindActivityById(id_Activity);
        System.out.println(act.getId());
        Work_doneService cs = new Work_doneService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity.fxml"));
            stage.setScene(new Scene(loader.load()));
            List_ActivityController controller = loader.getController();
            //controller.initData(act);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
    
}
