/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Course;
import Services.CourseService;
import Services.ForumService;
import Utils.Config;
import Utils.Helpers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CourseItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private Label CourseName;
    @FXML
    private Label description;
    int id_Course;
    int id_Subject;
    File file = null;
    public static Course c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CourseName.setWrapText(true);
        description.setWrapText(true);
    }

    public void setData(Course c) {
        this.c = c;
        id_Course = c.getId();
        id_Subject=c.getId_subject();
        CourseName.setText(c.getName());
        description.setText(c.getDescription());

    }

    @FXML
    private void OpenAction(ActionEvent event) {
        if (id_Course != 0) {
            CourseService as = new CourseService();
            Course c = new Course();
            c = as.getCourseById(id_Course);
            String fileName = c.getCourse();
            System.out.println(fileName);
            Helpers.openWebpage(Config.BASE_URL2 + fileName);
        }
    }

    @FXML
    private void AvailableActivityListAction(ActionEvent event) {

        CourseService cs = new CourseService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_User_Activity.fxml"));
            stage.setScene(new Scene(loader.load()));
            List_User_ActivityController controller = loader.getController();
            controller.initData(c);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
 
    }

    @FXML
    private void DoneActivityListAction(ActionEvent event) {
        CourseService cs = new CourseService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_User_ActivityDone.fxml"));
            stage.setScene(new Scene(loader.load()));
            List_User_ActivityDoneController controller = loader.getController();
            controller.initData(c);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void AvailableForumListAction(ActionEvent event) {
        CourseService cs = new CourseService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvailableForumUser.fxml"));
            stage.setScene(new Scene(loader.load()));
            AvailableForumUserController controller = loader.getController();
            controller.initData(c);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
        
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }

}
