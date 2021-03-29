/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.User;
import Services.ActivityService;
import Services.UserService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sabrina
 */
public class UserAddController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private ComboBox<String> role = new ComboBox();
    ;
    ObservableList<String> options = FXCollections.observableArrayList(
            "Admin",
            "Teacher",
            "Student"
    );
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
    private TableColumn<User, String> role_u;
    @FXML
    private TableView<User> user_t;
    @FXML
    private AnchorPane anchor_user;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.setItems(options);
//        search_btn.setDisable(true);
        showUsers();
        
    }

    public void showUsers() {
        UserService ds = new UserService();
        user_t.setItems((ObservableList<User>) ds.getUsers());
        id.setCellValueFactory(new PropertyValueFactory("id"));
        f_name.setCellValueFactory(new PropertyValueFactory("first_name"));
        l_name.setCellValueFactory(new PropertyValueFactory("last_name"));
        e_mail.setCellValueFactory(new PropertyValueFactory("email"));
        role_u.setCellValueFactory(new PropertyValueFactory("role"));

    }

    @FXML
    private void add_button_action(ActionEvent event) {
        UserService ss = new UserService();
        User u = new User(cin.getText(), f_name.getText(), name.getText(), Date.valueOf(Birthdate.getValue()), phone_number.getText(), email.getText(), role.getValue().toString());
        ss.addUser(u);
        showUsers();
    }

    @FXML
    private void edit_user(ActionEvent event) {
        User u = user_t.getSelectionModel().getSelectedItem();
        UserService ds = new UserService();
        u.setId(u.getId());
        u.setFirst_name(name.getText());
        u.setLast_name(last_name.getText());
        u.setRole(role.getValue().toString());
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
            role_u.setCellValueFactory(new PropertyValueFactory("role"));
        
    }
}
