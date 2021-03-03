/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Student;
import Entities.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import Services.StudentService;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class Display_Student_FXMLController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    StudentService ps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ps = new StudentService();

        // role table view
        JFXTreeTableColumn<User, String> role = new JFXTreeTableColumn<>("role");
        role.setPrefWidth(150);
        role.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getRole());
            }
        });

        //making new editable role text field
        role.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable role text field
        role.setOnEditCommit((CellEditEvent<User, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setRole(t.getNewValue());
            ps.editStudent(id, "role", newValue);
        });

        // name table view
        JFXTreeTableColumn<User, String> name = new JFXTreeTableColumn<>("name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getName());
            }
        });
        //making new editable name text field
        name.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable name text field
        name.setOnEditCommit((CellEditEvent<User, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setName(t.getNewValue());
            ps.editStudent(id, "name", newValue);
        });

        // last_name table view
        JFXTreeTableColumn<User, String> last_name = new JFXTreeTableColumn<>("last_name");
        last_name.setPrefWidth(150);
        last_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getLast_name());
            }
        });
        //making new editable last_name text field
        last_name.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable last_name text field
        last_name.setOnEditCommit((CellEditEvent<User, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setLast_name(t.getNewValue());
            ps.editStudent(id, "last_name", newValue);
        });

        // cin table view
        JFXTreeTableColumn<User, String> cin = new JFXTreeTableColumn<>("cin");
        cin.setPrefWidth(150);
        cin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getCin()));
            }
        });
        //making new editable cin text field
        cin.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable cin text field
        cin.setOnEditCommit((CellEditEvent<User, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setCin(Integer.parseInt(t.getNewValue()));
            ps.editStudent(id, "cin", newValue);
        });

        // email table view
        JFXTreeTableColumn<User, String> email = new JFXTreeTableColumn<>("email");
        email.setPrefWidth(150);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getEmail());
            }
        });//making new editable email text field
        email.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable email text field
        email.setOnEditCommit((CellEditEvent<User, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setEmail(t.getNewValue());
            ps.editStudent(id, "email", newValue);
        });

        // phone_number table view
        JFXTreeTableColumn<User, String> phone_number = new JFXTreeTableColumn<>("phone_number");
        phone_number.setPrefWidth(150);
        phone_number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getPhone_number()));
            }
        });
        //making new editable phone num text field
        phone_number.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable phone num text field
        phone_number.setOnEditCommit((CellEditEvent<User, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPhone_number(Integer.parseInt(t.getNewValue()));
            ps.editStudent(id, "phone_number", newValue);
        });

        //  birth_date table view
        JFXTreeTableColumn<User, String> birth_date = new JFXTreeTableColumn<>("birth_date");
        birth_date.setPrefWidth(150);
        birth_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue().getBirth_date()));
            }
        });

        //making new editable brith date text field
        birth_date.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable birth date text field
        birth_date.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
            @Override
            public void handle(CellEditEvent<User, String> t) {
                try {
                    int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
                    String newValue = t.getNewValue();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                    java.util.Date date = sdf.parse(t.getNewValue().toString());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    t.getTreeTableView()
                            .getTreeItem(t.getTreeTablePosition()
                                    .getRow())
                            .getValue().setBirth_date(sqlDate);
                    ps.editStudent(id, "birth_date", newValue);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        List<Student> myLst;
        myLst = ps.listStudent();
        ObservableList<User> students = FXCollections.observableArrayList();

        myLst.forEach(p -> students.add(p));
        JFXTreeTableView<User> treeview = new JFXTreeTableView<>();
        final TreeItem<User> root = new RecursiveTreeItem<User>(students, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(name, last_name, role, cin, email, phone_number, birth_date);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);

        //declarer la button supprimer
        JFXButton DltBtn = new JFXButton("Remove");
        DltBtn.setLayoutY(410D);
        DltBtn.setOnAction(new EventHandler<ActionEvent>() {

            //eventHandler de la button supprimer
            @Override
            public void handle(ActionEvent event) {
                Dialog confirmation = new Dialog();
                GridPane grid2 = new GridPane();
                Label l1 = new Label("Delete Student?");
                grid2.add(l1, 2, 2);
                confirmation.setTitle("Confirmation de suppression!");
                confirmation.getDialogPane().setContent(grid2);
                ButtonType Confi = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType Ann  = new ButtonType("No", ButtonBar.ButtonData.OK_DONE);
                confirmation.getDialogPane().getButtonTypes().add(Confi);
                confirmation.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                confirmation.setResultConverter(new Callback<ButtonType, User>() {
                    @Override
                    public User call(ButtonType param) {
                        if (param == Confi) {
                            User p = treeview.getSelectionModel().getSelectedItem().getValue();
                            ps.deleteStudent((Student) p);
                            Button cancelButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.CLOSE);
                            cancelButton.fire();
                            initialize(url, rb);
                        }

                        return null;
                    }
                });
                confirmation.showAndWait();
            }
        });

        //affichage dans AnchorPane en passant la résultat de tableview et la button de suppression
        AnchorPane.getChildren().addAll(treeview, DltBtn);

    }

}
