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

/**
 *
 * @author 715200
 */
public class MenuController implements Initializable {
    private Master main;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    @FXML protected void Terminate(ActionEvent event) throws IOException{ 
        System.exit(0);
    }
    
    @FXML protected void openAgentView(ActionEvent event) throws IOException{ 
        main.agentView();
    }
    
    @FXML protected void openPackageView(ActionEvent event) throws IOException{ 
        main.packageView();
    }
    
    public void setMaster(Master main) {
        this.main = main;     
    }
    
}
