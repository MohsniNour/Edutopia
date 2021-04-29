/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Course;
import Entities.Forum;
import Services.ForumService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class List_Archived_ForumController implements Initializable {

    @FXML
    private Label CourseName;
    @FXML
    private TableView<Forum> TableView;
    @FXML
    private TableColumn<Forum, String> idSubject;
    @FXML
    private TableColumn<Forum, Integer> id;
    @FXML
    private Button backButton;
    @FXML
    private Button Activate_button;
    private int id_Course;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showArchivedForum();
    } 
    
    void initData(Course c) {
        CourseName.setText(c.getName());
        id_Course=c.getId();
        showArchivedForum();
    }

    public void showArchivedForum(){
        ForumService fs = new ForumService();
       // TableView.setItems((ObservableList<Forum>) fs.getForumByIdCourse(id_Course));
        idSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
    }
    @FXML
    private void BackAction(ActionEvent event) {
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
    private void activateAction(ActionEvent event) {
    }
    
}
