/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Course;
import Services.ActivityService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class List_Archived_ActivityController implements Initializable {

    @FXML
    private TableView<Activity> TableView;
    @FXML
    private TableColumn<Activity, String> idName;
    @FXML
    private TableColumn<Activity, Date> idDeadline;
    @FXML
    private Label CourseName;
    private String id_Course;
    
    private Path to;
    private Path from;
    private Path fromUpdated;
    private Path removePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showActivities();
    }

    void initData(Course c) {
        CourseName.setText(c.getName());
        id_Course = c.getId();
        showActivities();
    }

    public void showActivities() {
        ActivityService as = new ActivityService();
        ArrayList<Activity> al = as.getArchivedActivitiesListByIdCourse(id_Course);
        System.out.println(id_Course);
        for (Activity a : al) {
            System.out.println(a.toString());
        }
        ObservableList<Activity> oL = FXCollections.observableArrayList(al);
        idName.setCellValueFactory(new PropertyValueFactory("name"));
        idDeadline.setCellValueFactory(new PropertyValueFactory("deadline"));
        TableView.setItems(oL);
    }

    @FXML
    private void backAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Course.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void ActivateAction(ActionEvent event) throws IOException {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez une activité ");
            alert.showAndWait();
        } else {
            Activity act = TableView.getSelectionModel().getSelectedItem();
            ActivityService as = new ActivityService();
            System.out.println(act.getId());
            from = Paths.get(act.getWork_todo());
            File f = new File(from.toString());
            removePath = Paths.get("src/Files/" + f.getName());
            Files.copy(from, removePath);
            Files.delete(from);
            as.activate(act.getId(), removePath.toString());
            showActivities();
        }
    }
}
