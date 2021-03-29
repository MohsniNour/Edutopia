package GUI;

import Alert.AlertDialog;
import CalendarA.FullCalendarView;
import Entities.BadWords;
import Entities.Exam;
import Entities.Question;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class home_QuestionController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Button btn_Exam;
    @FXML
    private Button btn_Course;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
}    

    void initData(Exam c) throws SQLException {
        exam=c;
        ExamName.setText(exam.getType());
        search();
          Modifier();
          try {
            refreche();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            tabview.setEditable(true);
            List<Integer> listInt=new ArrayList<>();
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
                            if (u.getArchived_Date() == null)
                    {
                           service.Archiver(u.getId());
                           AlertDialog.showNotification("Archivage", "Archivage avec success", AlertDialog.image_checked);
                    }
                    else{
                           AlertDialog.showNotification("Erreur  !", "Deja archiver !", AlertDialog.image_cross);
                    }  
                    } catch (SQLException ex) {
                    }
                    try {
                           refreche();
                    } catch (SQLException ex) {}
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
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_Subject.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @FXML
    private void chargerCalendarHandle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();

        FullCalendarController controller = loader.getController();
        VBox f=new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void refreche() throws SQLException {
        col_Question.setCellValueFactory(new PropertyValueFactory<>("question"));
        col_Question.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        col_prop1.setCellValueFactory(new PropertyValueFactory<>("proposition1"));
        col_prop1.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        col_prop2.setCellValueFactory(new PropertyValueFactory<>("proposition2"));
        col_prop2.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        col_prop3.setCellValueFactory(new PropertyValueFactory<>("proposition3"));
        col_prop3.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        col_prop4.setCellValueFactory(new PropertyValueFactory<>("proposition4"));
        col_prop4.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
        col_archived_date.setCellValueFactory(new PropertyValueFactory<>("archived_Date"));
        tabview.getItems().clear();
        tabview.setItems(service.AffichertoutByExam(exam.getId()));
    }
    
    public void search() {
        txt_Seach.setOnKeyReleased(e-> {
            if (txt_Seach.getText().equals("") ) {
                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {
            try {
                col_Question.setCellValueFactory(new PropertyValueFactory<>("question"));
                col_Question.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
                col_prop1.setCellValueFactory(new PropertyValueFactory<>("proposition1"));
                col_prop1.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
                col_prop2.setCellValueFactory(new PropertyValueFactory<>("proposition2"));
                col_prop2.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
                col_prop3.setCellValueFactory(new PropertyValueFactory<>("proposition3"));
                col_prop3.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
                col_prop4.setCellValueFactory(new PropertyValueFactory<>("proposition4"));
                col_prop4.setCellFactory(TextFieldTableCell.<Question> forTableColumn());
                col_archived_date.setCellValueFactory(new PropertyValueFactory<>("archived_Date"));
                tabview.getItems().clear();
                tabview.setItems(service.serach(txt_Seach.getText()));
                } catch (SQLException ex) {}
            }
        }
        );
    }
    
       public void Modifier(){

            col_Question.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition(); 
            String question = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setQuestion(question);
            try {
                service.Modifier(ac,ac.getId());
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
                service.Modifier(ac,ac.getId());
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
                service.Modifier(ac,ac.getId());
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
               service.Modifier(ac,ac.getId());
            } catch (Exception ex) {}            
        }); 
            col_prop4.setOnEditCommit((TableColumn.CellEditEvent<Question, String> event) -> {
            TablePosition<Question, String> pos = event.getTablePosition();     
            String prop4 = event.getNewValue();
            int row = pos.getRow();
            Question ac = event.getTableView().getItems().get(row);
            ac.setProposition4(prop4);
                 
            try {
                service.Modifier(ac,ac.getId());
            } catch (Exception ex) {
            }
                   
        });              
    }
       
    @FXML
    private void ajouter_Question(ActionEvent event) throws SQLException {
        BadWords.loadConfigs();

        {
            if (txt_Question.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de Quetion", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_Question.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            }
            else if (txt_prop1.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de proposition 1", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_prop1.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            }
             else if (txt_prop2.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de proposition 2", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_prop2.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);
            
            } else if (txt_prop3.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de proposition 3", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_prop3.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            } else if (txt_prop4.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de proposition 4", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_prop4.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            }
             else if (combo_bonnereponse.getSelectionModel().getSelectedItem()==null || combo_bonnereponse.getSelectionModel().getSelectedItem()<1) {
                AlertDialog.showNotification("Error !", "Veuillez choisir la bonne réponse", AlertDialog.image_cross);

            }    
            else {    
                int idExam=exam.getId();
                Question q = new Question(txt_Question.getText(), txt_prop1.getText(),  txt_prop2.getText(),  txt_prop3.getText(),  txt_prop4.getText(), idExam);
                switch(combo_bonnereponse.getSelectionModel().getSelectedItem()){
                  case 1:q.setBonnereponse(txt_prop1.getText()); break;
                  case 2:q.setBonnereponse(txt_prop2.getText()); break;
                  case 3:q.setBonnereponse(txt_prop3.getText()); break;
                  case 4:q.setBonnereponse(txt_prop4.getText()); break;
                }
            try
            {
                service.Ajouter(q);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            refreche();
            AlertDialog.showNotification("Question", "Question ajouter", AlertDialog.image_checked);
            }
        }
    }  
}