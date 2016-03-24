/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AgentController;
import Controller.MenuController;
import Controller.ModalController;
import Controller.PackageViewController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author 715200
 */
public class Master extends Application { 
    private Stage stage;
    private Stage dialog; 
    private BorderPane root;
    
    public Stage getPrimaryStage() {
        return stage;
    }
    
    public Stage getDialog() {
        return dialog;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setTitle("Agent Manager");  
        
        FXMLLoader loader = new FXMLLoader(Master.class.getResource("Root.fxml"));
        root = (BorderPane) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MenuController controller = loader.getController();
        controller.setMaster(this);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        
        stage.setScene(scene);
          
        stage.show();
        
        agentView(); 
    }
    
    public void packageView() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(Master.class.getResource("PackageView.fxml"));
            AnchorPane packagePage = (AnchorPane) loader.load();
            root.setCenter(packagePage);
            // Give the controller access to the main app
            PackageViewController controller = loader.getController();
            controller.setMaster(this);
            
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    
    public void agentView() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(Master.class.getResource("AgentView.fxml"));
            AnchorPane agentPage = (AnchorPane) loader.load();
            root.setCenter(agentPage);
            // Give the controller access to the main app
            AgentController controller = loader.getController();
            controller.setMaster(this);
            
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    
    public void errorModal(String message) { 
        try {
            // Load the fxml file and set into the center of the main layout
            dialog = new Stage(); 
            FXMLLoader loader = new FXMLLoader(Master.class.getResource("ErrorModal.fxml"));
            AnchorPane errorModal = (AnchorPane) loader.load();
            Scene scene = new Scene(errorModal); 
            setUserAgentStylesheet(STYLESHEET_CASPIAN);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setResizable(false);

            dialog.setScene(scene);
            // Give the controller access to the main app
            ModalController controller = loader.getController();
            controller.setMaster(this);
            controller.setMessage(message);
            
           

            dialog.show();
        
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
