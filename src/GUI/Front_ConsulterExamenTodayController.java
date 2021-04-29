package GUI;

import CalendarA.FullCalendarView;
import Entities.Exam;
import Entities.User;
import Services.Exam_Service;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Front_ConsulterExamenTodayController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    private Button btn_Exam;
    private Button btn_Passage;
    private Button btn_Course;
    @FXML
    private Pane pnl_abonnement;
    
    
    
    Exam_Service serviceexamen=new Exam_Service();
    @FXML
    private VBox vboxPassageExamen;
    @FXML
    private Text textNombreExamen;
    @FXML
    private Label UserName;
    @FXML
    private Button Exam;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Exam> exams= serviceexamen.Affichertous();
        
        int i=0;
        
        for(Exam e:exams){
            if(!serviceexamen.VerfierPassageExamen(e.getId())){
            java.util.Date dateNow = new java.util.Date();
            dateNow.setHours(0);
            dateNow.setMinutes(0);
            dateNow.setSeconds(0);
            java.util.Date dateDm1 = new java.util.Date();
            dateDm1.setHours(0);
            dateDm1.setMinutes(0);
            dateDm1.setSeconds(0);
            dateDm1.setTime(dateDm1.getTime() + 86399000);
            
                Date dateExam=new Date();
                dateExam.setYear(e.getStartdate().getYear());
                dateExam.setMonth(e.getStartdate().getMonth());
                dateExam.setDate(e.getStartdate().getDate());
                dateExam.setHours(0);
            dateExam.setMinutes(0);
            dateExam.setSeconds(0);
            
            if ((dateNow.getTime() <= dateExam.getTime()) && (dateDm1.getTime() >= dateExam.getTime())) {
               i++;
                Hyperlink hp =new Hyperlink(e.getType() +":"+ e.getSubject());
                vboxPassageExamen.getChildren().add(hp);
                
                   hp.setOnAction(new EventHandler<ActionEvent>() {
 
          @Override
          public void handle(ActionEvent event) {
              try {
          
                  Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Front_PasserExamen.fxml"));
        Parent root = loader.load();
            Front_PasserExamenController controller = loader.getController();
            controller.exam=e;
            controller.chargerExam();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                  
                  
              } catch (Exception ex) {
                  //Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
                  System.out.println(".handle()");
              }
          }
      });
            }
        }
        }   
        if(i==1){
          textNombreExamen.setText("Voici l'examen à passer aujourd'hui" );
        }
        else if(i>1){
          textNombreExamen.setText("Voici les "+i+" examens à passer aujourd'hui" );
        }else{
          textNombreExamen.setText("Pas d'examens à passer aujourd'hui" );   
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
