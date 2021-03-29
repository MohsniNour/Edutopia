/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Complaint;
import Services.ComplaintService;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sabrina
 */
public class ComplaintAddController implements Initializable {

    @FXML
    private TextField object;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> status = new ComboBox();
    ObservableList<String> options = FXCollections.observableArrayList(
            "Urgent",
            "Normal",
            "Low"
    );
    @FXML
    private Button send;
    @FXML
    private AnchorPane add_btn;

    @FXML
    private Button delete;
    @FXML
    private TableView<Complaint> table_v;
    @FXML
    private TableColumn<Complaint, String> descri;
    @FXML
    private TableColumn<Complaint, String> object_c;
    @FXML
    private TableColumn<Complaint, String> status_c;
    @FXML
    private TableColumn<Complaint, Integer> id;
    @FXML
    private Label countComplaint;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComplaintService cs = new ComplaintService();
        status.setItems(options);
        showComplaints();
        System.out.println(cs.countComplaints());
        countComplaint.setText(Integer.toString(cs.countComplaints()));

    }

    public void showComplaints() {
        ComplaintService ds = new ComplaintService();
        table_v.setItems((ObservableList<Complaint>) ds.getComplaints());
        object_c.setCellValueFactory(new PropertyValueFactory("object"));
        status_c.setCellValueFactory(new PropertyValueFactory("status"));
        descri.setCellValueFactory(new PropertyValueFactory("description"));
        id.setCellValueFactory(new PropertyValueFactory("id"));

    }

    @FXML
    private void btn_click(ActionEvent event) throws ParseException {
        ComplaintService ss = new ComplaintService();
        Complaint c = new Complaint(object.getText(), description.getText(), status.getValue().toString());
        ss.AddComplaint(c);
        showComplaints();
    }

    @FXML
    private void delete_btn(ActionEvent event) {
        Complaint c = table_v.getSelectionModel().getSelectedItem();
        ComplaintService ds = new ComplaintService();
        c.setId(c.getId());
        System.out.println(c.getId());
        ds.DeleteComplaint(c);
        showComplaints();

    }

    @FXML
    private void modify_btn(ActionEvent event) {
        Complaint c = table_v.getSelectionModel().getSelectedItem();
        ComplaintService ds = new ComplaintService();
        c.setId(c.getId());
        c.setObject(object.getText());
        c.setDescription(description.getText());
        c.setStatus(status.getValue().toString());
        ds.UpdateComplaint(c.getId(), c);
        showComplaints();
    }

}
