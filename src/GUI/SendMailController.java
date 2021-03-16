/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SendMailController implements Initializable {

    @FXML
    private TextField toadr;
    @FXML
    private TextField obj;
    @FXML
    private TextArea core;
    @FXML
    private Button retun;
    @FXML
    private Button send_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void on_return_button(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("display_Student_FXML.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void on_send_button(ActionEvent event) {
        try {
            System.out.println(toadr.getText()+" "+  obj.getText()+" "+ core.getText());
            Mail.sendMail(toadr.getText(), obj.getText(), core.getText());
        } catch (Exception ex) {
            Logger.getLogger(SendMailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
