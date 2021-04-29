/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Commentt;
import Entities.Forum;
import static GUI.ActivityItemController.id_Activity;
import Services.ActivityService;
import Services.CommentService;
import Services.ForumService;
import Services.Work_doneService;
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
import javafx.scene.control.Button;
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
public class CommentItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private TextField Comment;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button LikeButton;
    @FXML
    private Label dislikes;
    @FXML
    private Label likes;
    Commentt cmt;
    int id_Comment;
    int id_Forum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        likes.setWrapText(true);
        dislikes.setWrapText(true);
        // TODO
    }

    public void setData(Commentt cmt) {
        this.cmt = cmt;
        id_Comment = cmt.getId();
        id_Forum = cmt.getId_forum();
        Comment.setText(cmt.getContent());
        likes.setText(String.valueOf(cmt.getLikes()));
        dislikes.setText(String.valueOf(cmt.getDisLikes()));

    }

    @FXML
    private void updateComment(ActionEvent event) {
        if (Comment.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire est vide entrez un commentaire ");
            alert.showAndWait();
        } else {
            CommentService cs = new CommentService();
            Commentt cmt = new Commentt();
            cmt.setContent(Comment.getText());
            cmt.setLast_updated_by(1);
            cmt.setLast_updated_Date(java.sql.Date.valueOf(java.time.LocalDate.now()));
            cs.update(id_Comment, cmt);
            ForumService fs = new ForumService();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Note ajoutée avec succès");
            Node node = (Node) event.getSource();
            Stage oldStage = (Stage) node.getScene().getWindow();
            oldStage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommentForum.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                ListCommentForumController controller = loader.getController();
                Forum f = fs.FindForumById(id_Forum);
                controller.initData(f);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }

        }
    }

    @FXML
    private void removeComment(ActionEvent event) {

        CommentService cs = new CommentService();
        cs.remove(cmt.getId());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommentForum.fxml"));
            stage.setScene(new Scene(loader.load()));
            ListCommentForumController controller = loader.getController();
            Forum f = new Forum();
            ForumService fsr = new ForumService();
            System.out.println(id_Forum);
            f = fsr.FindForumById(id_Forum);
            controller.initData(f);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void LikeButton(ActionEvent event) {

        CommentService cs = new CommentService();
        int like = cmt.getLikes() + 1;
        cs.addLike(id_Comment, like);
        ForumService fs = new ForumService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Note ajoutée avec succès");
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommentForum.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ListCommentForumController controller = loader.getController();
            Forum f = fs.FindForumById(id_Forum);
            controller.initData(f);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void dislikeAction(ActionEvent event) {
        CommentService cs = new CommentService();
        int dislike = cmt.getDisLikes() + 1;
        cs.addDisLike(id_Comment, dislike);
        ForumService fs = new ForumService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Note ajoutée avec succès");
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommentForum.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ListCommentForumController controller = loader.getController();
            Forum f = fs.FindForumById(id_Forum);
            controller.initData(f);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }

}
