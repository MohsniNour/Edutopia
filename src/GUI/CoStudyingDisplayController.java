/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.Co_Studying;
import Entities.User;
import Services.Co_StudyingService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CoStudyingDisplayController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    Co_StudyingService ps;
    @FXML
    private Button retun;
    @FXML
    private StackPane StackPane;
    @FXML
    private StackPane StackPane1;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    private Label mainmenu_txt;
    @FXML
    private ImageView mainmenu_icon;
    
    User current_user = LoginController.CurrentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ps = new Co_StudyingService();

        // type table view
        JFXTreeTableColumn<Co_Studying, String> type = new JFXTreeTableColumn<>("Type");
        type.setPrefWidth(150);
        type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Co_Studying, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Co_Studying, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getType());
            }
        });

        //making new editable type text field
        type.setCellFactory((TreeTableColumn<Co_Studying, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable type text field
        type.setOnEditCommit((CellEditEvent<Co_Studying, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setType(t.getNewValue());
            ps.editCostudying(id, "type", newValue);
        });

        //description table view
        JFXTreeTableColumn<Co_Studying, String> description = new JFXTreeTableColumn<>("Description");
        description.setPrefWidth(150);
        description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Co_Studying, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Co_Studying, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDescription());
            }
        });
        //making new editabledescription text field
        description.setCellFactory((TreeTableColumn<Co_Studying, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editabledescription text field
        description.setOnEditCommit((CellEditEvent<Co_Studying, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setDescription(t.getNewValue());
            ps.editCostudying(id, "description", newValue);
        });

        // niveau table view
        JFXTreeTableColumn<Co_Studying, String> niveau = new JFXTreeTableColumn<>("Niveau");
        niveau.setPrefWidth(150);
        niveau.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Co_Studying, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Co_Studying, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getLevel());
            }
        });//making new editable niveau text field
        niveau.setCellFactory((TreeTableColumn<Co_Studying, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable niveau text field
        niveau.setOnEditCommit((CellEditEvent<Co_Studying, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setLevel(t.getNewValue());
            ps.editCostudying(id, "niveau", newValue);
        });

        // filename table view
        JFXTreeTableColumn<Co_Studying, String> filename = new JFXTreeTableColumn<>("Nom de Fichier");
        filename.setPrefWidth(150);
        filename.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Co_Studying, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Co_Studying, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getFile());
            }
        });//making new editable niveau text field
        niveau.setCellFactory((TreeTableColumn<Co_Studying, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable niveau text field
        filename.setOnEditCommit((CellEditEvent<Co_Studying, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setLevel(t.getNewValue());
            ps.editCostudying(id, "file", newValue);
        });

        List<Co_Studying> myLst;
        myLst = ps.getListCo_studying();
        ObservableList<Co_Studying> cos = FXCollections.observableArrayList();

        myLst.forEach(p -> cos.add(p));
        JFXTreeTableView<Co_Studying> treeview = new JFXTreeTableView<>();
        final TreeItem<Co_Studying> root = new RecursiveTreeItem<Co_Studying>(cos, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(description, type, niveau, filename);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);

        //declarer la button supprimer
        JFXButton DltBtn = new JFXButton("Supprimer");
        DltBtn.setLayoutY(410D);
        DltBtn.setTextFill(Paint.valueOf("#FFFFFF"));
        DltBtn.setStyle("-fx-background-color: #0367b9");
        DltBtn.setRipplerFill(Paint.valueOf("#FFFFFF"));
        DltBtn.setButtonType(JFXButton.ButtonType.RAISED);
        DltBtn.setOnAction(new EventHandler<ActionEvent>() {

            //eventHandler de la button supprimer
            @Override
            public void handle(ActionEvent event) {
                Dialog confirmation = new Dialog();
                GridPane grid2 = new GridPane();
                Label l1 = new Label("Supprimer Contenu?");
                grid2.add(l1, 2, 2);
                confirmation.setTitle("Confirmation de suppression!");
                confirmation.getDialogPane().setContent(grid2);
                ButtonType Confi = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                ButtonType Ann = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
                confirmation.getDialogPane().getButtonTypes().add(Confi);
                confirmation.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                confirmation.setResultConverter(new Callback<ButtonType, Co_Studying>() {
                    @Override
                    public Co_Studying call(ButtonType param) {
                        if (param == Confi) {
                            Co_Studying p = treeview.getSelectionModel().getSelectedItem().getValue();
                            ps.deleteCostudying((Co_Studying) p);
                            Button cancelButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.CANCEL);
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
        StackPane.getChildren().addAll(DltBtn);
        StackPane1.getChildren().addAll(treeview);

    }

    @FXML
    private void on_return_button(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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
    private void UserAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAddPicker.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void DepartmentAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDepartment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClassAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminClasse.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CourseAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CalendarAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();
        FullCalendarController controller = loader.getController();
        VBox f = new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void AccountAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAdminModify.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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
        Label l1 = new Label("Décnnecter ?");
        grid2.add(l1, 2, 2);
        confirmation.setTitle("Confirmation");
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
                        Logger.getLogger(Add_Student_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Co_StudyingAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void mm_hover(MouseEvent event) {
        mainmenu_icon.setCursor(Cursor.HAND);
    }

    @FXML
    private void main_menu_clicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}
