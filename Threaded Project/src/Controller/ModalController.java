/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Master;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author 715200
 */
public class ModalController implements Initializable {
    private Master main;
    @FXML Label Message; 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    // Create Link to Master 
    public void setMaster(Master main) {
        this.main = main;     
    }
    
    // Set Error Message Text 
    public void setMessage(String message) { 
        Message.setText(message); 
    }
    
    @FXML protected void Terminate(ActionEvent event) throws IOException{ 
        main.getDialog().close(); 
    }
    
}
