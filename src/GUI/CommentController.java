/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentt;
import Services.CommentService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class CommentController implements Initializable {

    @FXML
    private TextField txtContent;
    @FXML
    private ComboBox<String> forumid=new ComboBox();
    ObservableList<String> options ;
    @FXML
    private TableView<Commentt> TableView;
    @FXML
    private TableColumn<Commentt, String> idContent;
    @FXML
    private TableColumn<Commentt, String> idForum;
    @FXML
    private TableColumn<Commentt, String> id;
    @FXML
    private TableColumn<Commentt, String> idStatus;
    @FXML
    private Button add_button;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        options= FXCollections.observableArrayList(
            "Sujet1",
            "Sujet2",
            "Sujet3"
    );
        forumid.setItems(options);
        showComment();
    }  
    public void showComment(){
        CommentService cs = new CommentService();
        TableView.setItems((ObservableList<Commentt>) cs.getComment());
        idContent.setCellValueFactory(new PropertyValueFactory("content"));
        idForum.setCellValueFactory(new PropertyValueFactory("id_forum"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        idStatus.setCellValueFactory(new PropertyValueFactory("status"));
        
    }

    @FXML
    private void addAction(ActionEvent event) {
        CommentService cs = new CommentService();
        Commentt cmt = new Commentt(txtContent.getText(),forumid.getValue().toString(),"nour",java.sql.Date.valueOf(java.time.LocalDate.now()));
        cs.add(cmt);
        showComment();
    }

    @FXML
    private void updateAction(ActionEvent event) {
        Commentt cmt=TableView.getSelectionModel().getSelectedItem();
        cmt.setContent(txtContent.getText());
        cmt.setId_forum(forumid.getValue().toString());
        cmt.setLast_updated_by("hamza");
        cmt.setLast_updated_Date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        CommentService fs = new CommentService();
        System.out.println(cmt.getId());
        fs.update(cmt.getId(), cmt);
        showComment();
    }

    @FXML
    private void removeButton(ActionEvent event) {
        Commentt cmt=TableView.getSelectionModel().getSelectedItem();
        CommentService cs = new CommentService();
        System.out.println(cmt.getId());
        cs.remove(cmt.getId());
        showComment();
    }
    
}
