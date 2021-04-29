package GUI;

import CalendarA.FullCalendarView;
import Entities.Exam;
import Entities.Question;
import Entities.User;
import Services.Exam_Service;
import Services.Question_Service;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Front_PasserExamenController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    private Button btn_Exam;
    private Button btn_Passage; 
    private Button btn_Course;
    @FXML
    private Pane pnl_abonnement;
    
    Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
    
    private int idUser=2;
    
    Exam_Service serviceexamen=new Exam_Service();
    @FXML
    private ScrollPane scrollpaneExamen;
    
    public Exam exam;
    @FXML
    private Label labeltype;
    @FXML
    private Label labelsujet;
    @FXML
    private Label labeldateexamen;
    @FXML
    private Label labelEnsegnant;
    @FXML
    private Label labelnotefinal;
    
    Question_Service qs=new Question_Service();
    @FXML
    private Button btnTerminer;
    private int id_Subject;
    @FXML
    private Label UserName;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
//    void initData(Subject c) throws SQLException {
//        id_Subject = c.getId();
//        labelsujet.setText(c.getId_Subject());
//
//    }

    public void chargerHeader(){
        labeltype.setText(exam.getType());
        labeldateexamen.setText(exam.getStartdate().toString());
        labelEnsegnant.setText(String.valueOf(exam.getCreated_by()));
        labelsujet.setText(exam.getSubject());
    }
     VBox paneQuestion=new VBox();
     int index=0;
    public void chargerExam(){
        chargerHeader();
        scrollpaneExamen.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
      
       
       List<Question>questions= qs.AfficherQuestionByexam(exam);
      
      
       for(Question q: questions){
           VBox paneQuestiondetail=new VBox();
           index++;
           Label questionTitre=new Label("question "+index+": "+q.getQuestion());
           
           ToggleGroup group = new ToggleGroup();
           RadioButton buttonProp1 = new RadioButton(q.getProposition1());
           buttonProp1.setToggleGroup(group);
           
            RadioButton buttonProp2 = new RadioButton(q.getProposition2());
           buttonProp2.setToggleGroup(group);
           
           RadioButton buttonProp3 = new RadioButton(q.getProposition3());
           buttonProp3.setToggleGroup(group);
           
           RadioButton buttonProp4 = new RadioButton(q.getProposition4());
           buttonProp4.setToggleGroup(group);
           
           paneQuestiondetail.getChildren().addAll(questionTitre,buttonProp1,buttonProp2,buttonProp3,buttonProp4);
           
           paneQuestion.getChildren().add(paneQuestiondetail);
           paneQuestion.setSpacing(20);
           scrollpaneExamen.setContent(paneQuestion);
           
          
           
           group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
        public void changed(ObservableValue<? extends Toggle> ob,  
                                                Toggle o, Toggle n) 
        { 

            RadioButton rb = (RadioButton)group.getSelectedToggle(); 

            if (rb != null) { 
                String s = rb.getText(); 
                if(s.equals(q.getBonnereponse())){
                    if(map.containsKey(q.getId())){
                        map.remove(q.getId());
                        map.put(q.getId(), true);
                    }else{
                         map.put(q.getId(), true);
                    }

                }else{
                  if(map.containsKey(q.getId())){
                        map.remove(q.getId());
                        map.put(q.getId(), false);
                    }else{
                         map.put(q.getId(), false);
                    }
                }

            }


        } 
    }); 


   }


}
    
    private void handleClicks(ActionEvent event) throws IOException {
          if (event.getSource() == btn_Exam) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ChargerExamen.fxml")));
            stage.setScene(scene);
            stage.show();

        }
          if (event.getSource() == btn_Passage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ConsulterExamenToday.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Course) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLSubjectForStudent.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
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

    @FXML
    private void TerminerHandle(ActionEvent event) {
        if(index!=map.keySet().size()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez terminer l'examen", ButtonType.CLOSE);
                alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir valider? ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                int reponsecorrecte=0;
                for(Boolean b: map.values()){
                    if(b){
                       reponsecorrecte++; 
                    }
                }
                double notefinal=((float) reponsecorrecte*20/index);
               
        labelnotefinal.setText(fmt(round(notefinal,2))+"/20");     
        List<Question>questions= qs.AfficherQuestionByexam(exam);
        VBox vbb=new VBox();
        Label lb=new Label("Bonne réponse: ");
        vbb.getChildren().add(lb);
        for(Question q: questions){
           Text question=new Text(q.getQuestion()+" : "+q.getBonnereponse());
           vbb.getChildren().add(question);
       }
        btnTerminer.setVisible(false);
        paneQuestion.getChildren().add(vbb);
        serviceexamen.AjouterNoteExamen((float) notefinal, idUser, exam.getId());
            }
        }
    }
    
    
    public  double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}   
    public  String fmt(double d)
{
    if(d == (long) d)
        return String.format("%d",(long)d);
    else
        return String.format("%s",d);
}

    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CourseAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForStudent.fxml"));
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

    private void Co_StudyingAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying_Front.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void AccountAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateAccountStudent.fxml"));
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
        Label l1 = new Label("Décnnecter?");
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
    private void ClassAction(ActionEvent event) {
    }

    @FXML
    private void ExamAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Front_ChargerExamen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
   
    
}
