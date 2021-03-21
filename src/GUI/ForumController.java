/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Course;
import Entities.Forum;
import Services.CommentService;
import Services.ForumService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ForumController implements Initializable {

    @FXML
    private TextField txtSubject;
    
    @FXML
    private Button add_button;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<Forum> TableView;
    @FXML
    private TableColumn<Forum, String> idSubject;
    private TableColumn<Forum, String> idCourse;
    @FXML
    private TableColumn<Forum, String> id;
    @FXML
    private Label CourseName;
    private String id_Course;
    private String id_Forum;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showForumPage();
        showForum();
        
    } 
    
    void setId(String id_forum)
    {
        this.id_Forum=id_forum;
    }
    
    void initData(Course c) {
        CourseName.setText(c.getName());
        id_Course=c.getId();
        showForum();
    }
    
    public void showForum(){
        ForumService fs = new ForumService();
        TableView.setItems((ObservableList<Forum>) fs.getForumByIdCourse(id_Course));
        idSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
    }
    public void showForumPage(){
        ForumService fs = new ForumService();
        fs.getIdCourseByIdForum(id_Forum);
        idSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
    }

    @FXML
    private void addAction(ActionEvent event) {
        ForumService fs = new ForumService();
        Forum frm = new Forum(txtSubject.getText(),id_Course,"nour",java.sql.Date.valueOf(java.time.LocalDate.now()));
        fs.add(frm);
        showForum();
    }

    @FXML
    private void updateAction(ActionEvent event) {
        Forum frm=TableView.getSelectionModel().getSelectedItem();
        frm.setSubject(txtSubject.getText());
        frm.setId_course(id_Course);
        frm.setLast_updated_by("hamza");
        frm.setLast_updated_Date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        ForumService fs = new ForumService();
        System.out.println(frm.getId());
        fs.update(frm.getId(), frm);
        showForum();
    }

    @FXML
    private void removeButton(ActionEvent event) {
        Forum frm=TableView.getSelectionModel().getSelectedItem();
        ForumService fs = new ForumService();
        System.out.println(frm.getId());
        fs.remove(frm.getId());
        showForum();
    }
    
    @FXML
    private void showComment(ActionEvent event){
        
        Forum frm=TableView.getSelectionModel().getSelectedItem();
        System.out.println(frm.getId());
        CommentService cs = new CommentService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum_Comment.fxml"));
            stage.setScene(new Scene(loader.load()));
            Forum_CommentController controller = loader.getController();
            controller.initData(frm);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
    public Stage showComment(Forum frm) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum_Comment.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        Forum_CommentController controller = loader.getController();
        controller.initData(frm);
        stage.show();
        return stage;
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

    private void ActivateAction(ActionEvent event) {
        Forum frm=TableView.getSelectionModel().getSelectedItem();
        ForumService fs = new ForumService();
        System.out.println(frm.getId());
        fs.activate(frm.getId());
        showForum();
    }

    @FXML
    private void ArchivedListCommentAction(ActionEvent event) {
    }
    
}
