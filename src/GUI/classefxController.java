/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.classe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class classefxController implements Initializable {


    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfclasse_size;
    @FXML
    private TextField tfcreated_by;
    @FXML
    private TextField tfcreated_date;
     @FXML
    private TextField tfid;
    @FXML
    private TextField tflast_update_by;
     @FXML
    private TextField tfid2;
    @FXML
    private TextField tflast_update_date;
    @FXML
    private TextField tfarchived_by;
    @FXML
    private TextField tfarchived_date;
    @FXML
    private TableView<classe> tvclasse;
    @FXML
    private TableColumn<classe, Integer> collid;
    @FXML
    private TableColumn<classe, String> collnom;
    @FXML
    private TableColumn<classe, Integer> collclasse_size;
    @FXML
    private TableColumn<classe, String> collcreated_by;
    @FXML
    private TableColumn<classe, Date> collcreated_date;
    @FXML
    private TableColumn<classe, String> colllast_update_by;
    @FXML
    private TableColumn<classe, Date> colllast_update_date;
    @FXML
    private TableColumn<classe, String> collarchived_by;
    @FXML
    private TableColumn<classe, Date> collarchived_date;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showclasse();
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
         if(event.getSource() == btnajouter)
            insertRecord();
         else if (event.getSource() == btnupdate){
            updateRecord();
         }
         else if(event.getSource() == btndelete){
            deleteButton();
        }
    }
         
    public Connection getConnection()
    {
        Connection conn;
                try{conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/edutopia", "root","");
                return conn;}
                catch(Exception ex ){
                System.out.println("error" + ex.getMessage());
                
                        }
        return null;

                        
                       
   }
    
    public ObservableList<classe> getclasseList() 
    {
    ObservableList<classe> classeList = FXCollections.observableArrayList();
            Connection conn;
        conn = getConnection();
            String query = "SELECT * FROM classe";
            Statement st;
            ResultSet rs;
            try{st = conn.createStatement();
            rs = st.executeQuery(query);
            classe classe;
            while(rs.next())
            {
                classe = new classe(rs.getInt("id"),rs.getString("nom"),rs.getInt("classe_size"),rs.getString("created_by"),rs.getDate("created_date"),rs.getString("last_update_by"),rs.getDate("Last_update_date"),rs.getString("archived_by"),rs.getDate("archived_date"));
            classeList.add(classe);
            }
            
            }
            catch(Exception ex)
            {ex.printStackTrace();}
            return classeList;
    
    }
    public void showclasse()
    {ObservableList<classe> list =getclasseList();
     collid.setCellValueFactory(new PropertyValueFactory<classe , Integer>("id"));
     collnom.setCellValueFactory(new PropertyValueFactory<classe, String>("name"));
    collclasse_size.setCellValueFactory(new PropertyValueFactory<classe , Integer>("classe_size"));
    collcreated_by.setCellValueFactory(new PropertyValueFactory<classe, String>("created_by"));
    collcreated_date.setCellValueFactory(new PropertyValueFactory<classe , Date>("created_date"));
    colllast_update_by.setCellValueFactory(new PropertyValueFactory<classe, String>("last_update_by"));
    colllast_update_date.setCellValueFactory(new PropertyValueFactory<classe , Date>("last_update_date"));
    collarchived_by.setCellValueFactory(new PropertyValueFactory<classe, String>("archived_by"));
    collarchived_date.setCellValueFactory(new PropertyValueFactory<classe , Date>("archived_date"));
   tvclasse.setItems(list);
            }
      private void insertRecord(){
        String query;
       // Random random = new Random();
       // IntStream ints = random.ints(1,1,100);
 
        query = "INSERT INTO classe VALUES ('9','" + tfnom.getText() + "','" + tfclasse_size.getText() + "','" + tfcreated_by.getText() + "','"
                + tfcreated_date.getText() + "','" + tflast_update_by.getText() + "','" + tflast_update_date.getText() + "','" + tfarchived_by.getText() + "','" + tfarchived_date.getText() + "')";
        executeQuery(query);
        showclasse();
    }
 private void updateRecord(){
        String query = "UPDATE  classe SET nom  = '" + tfnom.getText() + "' WHERE id = " + tfid2.getText() + "";
        executeQuery(query);
        showclasse();
    }
   private void deleteButton(){
        String query = "DELETE FROM classe WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showclasse();
    }
    private void executeQuery(String query) {
    
                
          Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
        }
        
    }
    
     
    
}
