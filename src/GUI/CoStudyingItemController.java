/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Co_Studying;
import Entities.Rating;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CoStudyingItemController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label level;
    @FXML
    private Label description;
//    @FXML
//    private Label student;
    @FXML
    private AnchorPane id_clicked;
    
    static int id_item;
    @FXML
    private Label id_co;
    @FXML
    private Label rating;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        description.setWrapText(true);
        type.setWrapText(true);
    }

    private Co_Studying co;

    public void setData(Co_Studying co) {
        this.co = co;
        id_co.setText(Integer.toString(co.getId()));
        type.setText(co.getType());
        description.setText(co.getDescription());
        level.setText(co.getLevel());
        rating.setText(Integer.toString(co.getRating()));
//      student.setText(co.getId_student().getName());
        
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
      id_item = Integer.parseInt(id_co.getText());
      System.out.println(id_item);
    }

}
