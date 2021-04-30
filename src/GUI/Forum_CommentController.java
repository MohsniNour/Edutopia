/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentt;
import Entities.Forum;
import Entities.User;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class Forum_CommentController implements Initializable {

    @FXML
    private Label subjectForum;
    @FXML
    private TableView<Commentt> TableView;
    @FXML
    private TableColumn<Commentt, String> contentComment;
    @FXML
    private TableColumn<Commentt, Integer> likesComment;
    @FXML
    private TableColumn<Commentt, Integer> dislikesComment;
    @FXML
    private TableColumn<Commentt, String> idComment;
    
    private int id_Forum;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField txtContent;
    @FXML
    private AnchorPane dislikeButton;
    @FXML
    private Button LikeButton;
    @FXML
    private Label nbrComment;
        User current_user = LoginController.CurrentUser;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
    }   
    
    void initData(Forum frm) {
        subjectForum.setText(frm.getSubject());
        CommentService cs = new CommentService();
        id_Forum=frm.getId();
        nbrComment.setText(String.valueOf(cs.countComment(id_Forum)));
        showComment();
    }
    
    public void showComment(){
        CommentService cs = new CommentService();
        TableView.setItems((ObservableList<Commentt>) cs.getCommentsByIdForum(id_Forum));
        contentComment.setCellValueFactory(new PropertyValueFactory("content"));
        likesComment.setCellValueFactory(new PropertyValueFactory("likes"));
        dislikesComment.setCellValueFactory(new PropertyValueFactory("disLikes"));
        idComment.setCellValueFactory(new PropertyValueFactory("id"));
    }
    
    @FXML
    private void addComment(ActionEvent event) {
        
        if(txtContent.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("le contenu d'un commentaire est vide entrer un commentaire ");
            alert.showAndWait();
        }else
        {
            CommentService cs = new CommentService();
            Commentt cmt = new Commentt(txtContent.getText(),id_Forum,current_user.getId(),java.sql.Date.valueOf(java.time.LocalDate.now()));
            cs.add(cmt);
            showComment();
            nbrComment.setText(String.valueOf(cs.countComment(id_Forum)));
        }
    }

    @FXML
    private void updateComment(ActionEvent event) {
        
        if(TableView.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un commentaire ");
            alert.showAndWait();
        }else
        {
        Commentt cmt=TableView.getSelectionModel().getSelectedItem();
        cmt.setContent(txtContent.getText());
        cmt.setLast_updated_by(current_user.getId());
        cmt.setLast_updated_Date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        CommentService fs = new CommentService();
        System.out.println(cmt.getId());
        fs.update(cmt.getId(), cmt);
        showComment();
        }
    }

    @FXML
    private void removeComment(ActionEvent event) {
        
        if(TableView.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un commentaire ");
            alert.showAndWait();
        }else
        {Commentt cmt=TableView.getSelectionModel().getSelectedItem();
        CommentService cs = new CommentService();
        System.out.println(cmt.getId());
        cs.remove(cmt.getId());
        showComment();
        nbrComment.setText(String.valueOf(cs.countComment(id_Forum)));
        }
    }

    @FXML
    private void BackButton(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
            ForumController controller = loader.getController();
            controller.setId(String.valueOf(id_Forum));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void LikeButton(ActionEvent event) {
        
        if(TableView.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un commentaire ");
            alert.showAndWait();
        }else
        {Commentt cmt=TableView.getSelectionModel().getSelectedItem();
        int like=cmt.getLikes()+1;
        cmt.setContent(txtContent.getText());
        cmt.setId_forum(id_Forum);
        cmt.setLikes(cmt.getLikes());
        System.out.println(cmt.getLikes());
        CommentService fs = new CommentService();
        fs.addLike(cmt.getId(),like);
        System.out.println(cmt.getLikes());
        showComment();
        }
    }

    @FXML
    private void dislikeAction(ActionEvent event) {
        
        if(TableView.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un commentaire ");
            alert.showAndWait();
        }else
        {Commentt cmt=TableView.getSelectionModel().getSelectedItem();
        int dislike=cmt.getDisLikes()+1;
        cmt.setContent(txtContent.getText());
        cmt.setId_forum(id_Forum);
        cmt.setDisLikes(cmt.getDisLikes());
        System.out.println(cmt.getDisLikes());
        CommentService fs = new CommentService();
        fs.addDisLike(cmt.getId(),dislike);
        System.out.println(cmt.getDisLikes());
        showComment();
        }
    }

    @FXML
    private void ClickedOnTable(MouseEvent event) {
        updateButton.setDisable(false);
        deleteButton.setDisable(false);
        LikeButton.setDisable(false); 
    }
    
}
