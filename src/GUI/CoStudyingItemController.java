/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Co_Studying;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    @FXML
    private AnchorPane id_clicked;
    @FXML
    private Label id_co;
    @FXML
    private Label rating;
    @FXML
    private ImageView fruitImg;
    @FXML
    private VBox vbox_id;

    static int id_item;
    private Co_Studying co;

    private static final DropShadow highlight = new DropShadow(15, Color.DARKRED);
    DropShadow shadow = new DropShadow();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        description.setWrapText(true);
        type.setWrapText(true);
    }

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

    @FXML
    private void off_hover(MouseEvent event) {
       id_clicked.setEffect(null);
    }

    @FXML
    private void on_hover(MouseEvent event) {
        id_clicked.setEffect(highlight);
        id_clicked.setCursor(Cursor.HAND);
    }

}
