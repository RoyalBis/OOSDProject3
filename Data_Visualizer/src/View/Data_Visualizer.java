/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author 715200
 */
public class Data_Visualizer extends Application { 
    static final int WIDTH  = 615; 
    static final int HEIGHT = 650; 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AgentView.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("Agent Manager");    
        stage.show();
    }
    
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
