package GUI;
import CalendarA.FullCalendarView;
import Entities.Course;
import Services.CourseService;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Front_ConsulterCoursController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_Exam;
    @FXML
    private Button btn_Aganda;
    @FXML
    private Button btn_Course;
    @FXML
    private Pane pnl_abonnement;
    
    
    
    @FXML
    private FlowPane flowpaneCours;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CourseService servicecours=new CourseService();
       List<Course> courses= servicecours.AfficherCours();
       
       for(Course c :courses){
           VBox vb=new VBox();
           
        //images 
        ImageView img=new ImageView();
        
         Image image = new Image("images/Cours.jpg");
         img.setFitHeight(120);
         img.setFitWidth(120);

        img.setImage(image);
        img.setCache(true);
        
        
        Label titre=new Label(c.getName());
           Label description=new Label(c.getDescription());
           titre.setAlignment(Pos.CENTER);
           description.setAlignment(Pos.CENTER);
        
        
        vb.getChildren().add(img);
        vb.getChildren().add(titre);
        vb.getChildren().add(description);
        vb.getChildren().add(c.getDownload());
        
        
           flowpaneCours.getChildren().add(vb);
       }
       
       
    }    

    @FXML
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
        if (event.getSource() == btn_Course) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ConsulterCours.fxml")));
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
    
    
    
  
    
}
