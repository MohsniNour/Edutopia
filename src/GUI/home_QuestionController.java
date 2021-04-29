package GUI;

import Alert.AlertDialog;
import CalendarA.FullCalendarView;
import Entities.BadWords;
import Entities.Exam;
import Entities.Question;
import Entities.User;
import Services.Exam_Service;
import Services.Question_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class home_QuestionController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    private Button btn_Exam;
    private Button btn_Course;
    private Button btnBack;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<Question> tabview;
    @FXML
    private TableColumn<Question, String> col_Question;
    @FXML
    private TableColumn<Question, String> col_prop1;
    @FXML
    private TableColumn<Question, String> col_prop2;
    @FXML
    private TableColumn<Question, String> col_prop3;
    @FXML
    private TableColumn<Question, String> col_prop4;
    @FXML
    private TableColumn<Question, String> col_reponse;
    @FXML
    private TableColumn<Question, Date> col_archived_date;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField txt_Question;
    @FXML
    private TextField txt_prop1;
    @FXML
    private TextField txt_prop2;
    @FXML
    private TextField txt_prop3;
    @FXML
    private TextField txt_prop4;

    private Question_Service service = new Question_Service();
    private TableColumn<Question, String> col_btnArchiver;
    private Exam_Service service_exam = new Exam_Service();
    @FXML
    private ComboBox<Integer> combo_bonnereponse;
    private Exam exam;
    @FXML
    private Label ExamName;
    @FXML
    private Label UserName;
    @FXML
    private Button CourseAction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    void initData(Exam c) throws SQLException {
        exam = c;
        ExamName.setText(exam.getType());
        search();
        Modifier();
        try {
            refreche();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tabview.setEditable(true);
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1);
        listInt.add(2);
        listInt.add(3);
        listInt.add(4);
        ObservableList<Integer> listeNumero = FXCollections.observableArrayList(listInt);
        combo_bonnereponse.setItems(listeNumero);

        col_btnArchiver = new TableColumn("Archiver");
        javafx.util.Callback<TableColumn<Question, String>, TableCell<Question, String>> cellFactory = new Callback<TableColumn<Question, String>, TableCell<Question, String>>() {

            public TableCell call(final TableColumn<Question, String> param) {
                final TableCell<Question, String> cell = new TableCell<Question, String>() {
                    final Button btn = new Button("Archiver");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Question u = getTableView().getItems().get(getIndex());
                                try {
                                    if (u.getArchived_Date() == null) {
                                        service.Archiver(u.getId());
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Archivage ");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Archivage avec success ");
                                        alert.showAndWait();
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Erreur ! ");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Deja archiver");
                                        alert.showAndWait();
                                    }
                                } catch (SQLException ex) {
                                }
                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnArchiver.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnArchiver);
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_Exam) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Exam.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Course) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLSubjectForTeacher.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    private void chargerCalendarHandle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();

        FullCalendarController controller = loader.getController();
        VBox f = new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void refreche() throws SQLException {
        col_Question.setCellValueFactory(new PropertyValueFactory<>("question"));
        col_Question.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
        col_prop1.setCellValueFactory(new PropertyValueFactory<>("proposition1"));
        col_prop1.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
        col_prop2.setCellValueFactory(new PropertyValueFactory<>("proposition2"));
        col_prop2.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
        col_prop3.setCellValueFactory(new PropertyValueFactory<>("proposition3"));
        col_prop3.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
        col_prop4.setCellValueFactory(new PropertyValueFactory<>("proposition4"));
        col_prop4.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
        col_reponse.setCellValueFactory(new PropertyValueFactory<>("bonnereponse"));
        col_reponse.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
        col_archived_date.setCellValueFactory(new PropertyValueFactory<>("archived_Date"));
        tabview.getItems().clear();
        tabview.setItems(service.AffichertoutByExam(exam.getId()));
    }

    public void search() {
        txt_Seach.setOnKeyReleased(e -> {
            if (txt_Seach.getText().equals("")) {
                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {
                try {
                    col_Question.setCellValueFactory(new PropertyValueFactory<>("question"));
                    col_Question.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
                    col_prop1.setCellValueFactory(new PropertyValueFactory<>("proposition1"));
                    col_prop1.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
                    col_prop2.setCellValueFactory(new PropertyValueFactory<>("proposition2"));
                    col_prop2.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
                    col_prop3.setCellValueFactory(new PropertyValueFactory<>("proposition3"));
                    col_prop3.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
                    col_prop4.setCellValueFactory(new PropertyValueFactory<>("proposition4"));
                    col_prop4.setCellFactory(TextFieldTableCell.<Question>forTableColumn());
                    col_archived_date.setCellValueFactory(new PropertyValueFactory<>("archived_Date"));
                    tabview.getItems().clear();
                    tabview.setItems(service.serach(txt_Seach.getText()));
                } catch (SQLException ex) {
                }
            }
        }
        );
    }

    public void Modifier() {

        col_Question.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();
            String question = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setQuestion(question);
            try {
                service.Modifier(ac, ac.getId());
            } catch (Exception ex) {
            }
        });
        col_prop1.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();
            String prop1 = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setProposition1(prop1);

            try {
                service.Modifier(ac, ac.getId());
            } catch (Exception ex) {
            }

        });
        col_prop2.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();
            String prop2 = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setProposition2(prop2);

            try {
                service.Modifier(ac, ac.getId());
            } catch (Exception ex) {
            }

        });
        col_prop3.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();
            String prop3 = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setProposition3(prop3);

            try {
                service.Modifier(ac, ac.getId());
            } catch (Exception ex) {
            }
        });
        col_prop4.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();
            String prop4 = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setProposition4(prop4);

            try {
                service.Modifier(ac, ac.getId());
            } catch (Exception ex) {
            }
        });
        col_reponse.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();
            String type = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.getBonnereponse();
            try {
                service.Modifier(ac, ac.getId());
            } catch (Exception ex) {
            }
        });
    }

    @FXML
    private void ajouter_Question(ActionEvent event) throws SQLException {
        BadWords.loadConfigs();

        {
            if (txt_Question.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(txt_Question.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else if (txt_prop1.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(txt_prop1.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else if (txt_prop2.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(txt_prop2.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else if (txt_prop3.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(txt_prop3.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else if (txt_prop4.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(txt_prop4.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else if (combo_bonnereponse.getSelectionModel().getSelectedItem() == null || combo_bonnereponse.getSelectionModel().getSelectedItem() < 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir la bonne réponse");
                alert.showAndWait();
            } else {
                int idExam = exam.getId();
                Question q = new Question(txt_Question.getText(), txt_prop1.getText(), txt_prop2.getText(), txt_prop3.getText(), txt_prop4.getText(), idExam);
                switch (combo_bonnereponse.getSelectionModel().getSelectedItem()) {
                    case 1:
                        q.setBonnereponse(txt_prop1.getText());
                        break;
                    case 2:
                        q.setBonnereponse(txt_prop2.getText());
                        break;
                    case 3:
                        q.setBonnereponse(txt_prop3.getText());
                        break;
                    case 4:
                        q.setBonnereponse(txt_prop4.getText());
                        break;
                }
                try {
                    service.Ajouter(q);
                    refreche();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Question");
                    alert.setHeaderText(null);
                    alert.setContentText("Question ajouter");
                    alert.showAndWait();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    private void backAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Exam.fxml")));
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClassAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EnseignantClasse.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void CourseAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForTeacher.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTeacherModify.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClaimAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ComplaintFront.fxml"));
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
    private void backAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home_Exam.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
