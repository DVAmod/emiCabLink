/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.forms;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DVAmod
 */
public class formAboutController implements Initializable {

    @FXML
    private ImageView locLogoImage;
    @FXML
    private TextArea locAboutText;
    @FXML
    private ListView<String> locModulesView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> modules = new ArrayList<String>();
        modules.add("Модуль выбора лотка с фильтрацией");
        modules.add("Модуль расчета показателей лотка по сечению кабеля");
        modules.add("Модуль выбора типового решения для несущих конструкций");
        modules.add("Модуль произвольного выбора несущих конструкций");
        modules.add("Модуль редактирование проекта");
        modules.add("Модуль выгрузки результатов проекта");
        
        locModulesView.setItems(FXCollections.observableArrayList(modules));
        
        locLogoImage.setImage(new Image(formAboutController.class.getResourceAsStream("/emidetail/img/Medal_Small.png")));
    }    

    @FXML
    private void handleClose(ActionEvent event) {
        ((Node)event.getSource()).getParent().getScene().getWindow().hide();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleClose(MouseEvent event) {
        ((Node)event.getSource()).getParent().getScene().getWindow().hide();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
