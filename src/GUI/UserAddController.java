/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Mail;
import Entities.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Sabrina
 */
public class UserAddController implements Initializable {
    
    User currentUser = LoginController.CurrentUser;

    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private Button add_button;
    @FXML
    private TextField phone_number;
    @FXML
    private TableColumn<String, Integer> id;
    @FXML
    private TableColumn<User, String> f_name;
    @FXML
    private TableColumn<User, String> l_name;
    @FXML
    private TableColumn<User, String> e_mail;

    @FXML
    private TableView<User> user_t;
    @FXML
    private Button edit;
    @FXML
    private Button delete_user;
    @FXML
    private DatePicker Birthdate;
    @FXML
    private TextField search_txt;
    @FXML
    private Button search_btn;
    @FXML
    private TextField password;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
  


    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsers();
        String[] possible = {"esprit.tn","yahoo.fr","gmail.com" } ;
        TextFields.bindAutoCompletion(search_txt, possible);
        
     
//        search_btn.setDisable(true);
        
        
    }

    public void showUsers() {
        UserService ds = new UserService();
        user_t.setItems((ObservableList<User>) ds.getUsers());
        id.setCellValueFactory(new PropertyValueFactory("id"));
        f_name.setCellValueFactory(new PropertyValueFactory("first_name"));
        l_name.setCellValueFactory(new PropertyValueFactory("last_name"));
        e_mail.setCellValueFactory(new PropertyValueFactory("email"));
        
       

    }

    @FXML
    private void add_button_action(ActionEvent event) {
        UserService ss = new UserService();
        User u = new User(cin.getText(), f_name.getText(), name.getText(), Date.valueOf(Birthdate.getValue()), phone_number.getText(), email.getText(),password.getText(), "Admin", currentUser.getId());
        ss.addUser(u);
        showUsers();
        try {
            
            Mail.sendMail(email.getText(), "Edutopia admin's password", "Welcome to Edutopia "+name.getText()+" this your password,save it somewhere safe :        "+password.getText());
        } catch (Exception ex) {
            Logger.getLogger(UserAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void edit_user(ActionEvent event) {
        User u = user_t.getSelectionModel().getSelectedItem();
        UserService ds = new UserService();
        u.setId(u.getId());
        u.setFirst_name(name.getText());
        u.setLast_name(last_name.getText());
        u.setPassword(password.getText());
        System.out.println(u.getId());
        ds.UpdateUser(u.getId(), u);
        showUsers();
    }

    @FXML
    private void delete_user(ActionEvent event) {
        User u = user_t.getSelectionModel().getSelectedItem();
        UserService ds = new UserService();
        u.setId(u.getId());
        ds.DeleteUser(u);
        showUsers();

    }

    @FXML
    private void on_search_clicked(ActionEvent event) throws SQLException {
       
            List<User> user_list = new ArrayList();
            UserService us = new UserService();
            user_list = us.SearchUser(search_txt.getText());
            ObservableList<User> users = FXCollections.observableArrayList(user_list);
            user_t.setItems(users);
            id.setCellValueFactory(new PropertyValueFactory("id"));
            f_name.setCellValueFactory(new PropertyValueFactory("first_name"));
            l_name.setCellValueFactory(new PropertyValueFactory("last_name"));
            e_mail.setCellValueFactory(new PropertyValueFactory("email"));
            
        
    }

    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    

    @FXML
    private void DepartmentAction(ActionEvent event) {
    }

    @FXML
    private void ClassAction(ActionEvent event) {
    }

    @FXML
    private void CourseAction(ActionEvent event) {
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
    private void ClaimAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ComplaintAdd.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
    @FXML
    private void DisconnectionAction(ActionEvent event) {
        Dialog confirmation = new Dialog();
        GridPane grid2 = new GridPane();
        Label l1 = new Label("Décnnecter?");
        grid2.add(l1, 2, 2);
        confirmation.setTitle("Confirmation de déconnection");
        confirmation.getDialogPane().setContent(grid2);
        ButtonType Confi = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType Ann = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
        confirmation.getDialogPane().getButtonTypes().add(Confi);
        confirmation.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        confirmation.setResultConverter(new Callback<ButtonType, User>() {
            @Override
            public User call(ButtonType param) {
                if (param == Confi) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(UserAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                }

                return null;
            }
        });
        confirmation.showAndWait();
    }

    @FXML
    private void UsersAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAdd.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    }

