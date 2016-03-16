package Controller;

import Business.Agent; 
import Model.AgentProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Mark C. Willms 
 */
public class AgentController implements Initializable {
    @FXML private TableView<Agent> TableView;
    @FXML private TableColumn<Agent, String> ID;
    @FXML private TableColumn<Agent, String> First;
    @FXML private TableColumn<Agent, String> Initial;
    @FXML private TableColumn<Agent, String> Last;
    @FXML private TableColumn<Agent, String> Phone;
    @FXML private TableColumn<Agent, String> Email;
    @FXML private TableColumn<Agent, String> Position;
    @FXML private TableColumn<Agent, String> Agency;
    
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private TextField text4;
    @FXML private TextField text5;
    @FXML private TextField text6;
    @FXML private TextField text7;
    @FXML private TextField text8;
    
    @FXML private ComboBox Combo; 
    @FXML private Button ChangeButton;
    @FXML private AnchorPane AnchorPane; 
    
    List<Integer> arr = new ArrayList<>(); 
    List<Agent> agents = new ArrayList<>(); 
    
    // On Form Creation 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableText(); 
        agents = AgentProvider.GetAll();  
        for(int i = 0; i < agents.size(); i++)
            arr.add(agents.get(i).getAgentId()); 
        
        updateTable(); 
        Combo.getItems().setAll(arr); 
    }
    
    protected void disableText() { 
        text1.setEditable(false);
        text2.setEditable(false); 
        text3.setEditable(false);
        text4.setEditable(false);
        text5.setEditable(false);
        text6.setEditable(false); 
        text7.setEditable(false);
        text8.setEditable(false);
    }
    // On Edit Press 
    @FXML protected void handleEditButton(ActionEvent event) { 
        text1.setEditable(true); 
        text2.setEditable(true); 
        text3.setEditable(true); 
        text4.setEditable(true); 
        text5.setEditable(true); 
        text6.setEditable(true); 
        text7.setEditable(true); 
        text8.setEditable(true);  
    }
    
    @FXML protected void Terminate(ActionEvent event) throws IOException{ 
        System.exit(0);
    }
    
    // Switch Scenes 
    @FXML private void handleSceneChange(ActionEvent event) throws IOException{ 
        Parent PackageView; 
        PackageView = FXMLLoader.load(getClass().getResource("/View/PackageView.fxml"));

        Scene newScene; 
        newScene = new Scene(PackageView, 615, 650);

        Stage mainWindow;
        mainWindow = (Stage) Combo.getScene().getWindow();
        mainWindow.setTitle("Package Editor");
        mainWindow.setScene(newScene); 
    }
    
    // On Save Press
    @FXML protected void handleSaveClick(ActionEvent event) { 
        agents.clear();
        agents = AgentProvider.GetAll();
        updateTable();
    }
    
    // On Build Press 
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        int i = Combo.getSelectionModel().getSelectedIndex();
        if(i < 0 )
            return; 
        disableText();         
        text1.setText(agents.get(i).getAgentId() + "");
        text2.setText(agents.get(i).getAgtFirstName());
        text3.setText(agents.get(i).getAgtMiddleInitial());
        text4.setText(agents.get(i).getAgtLastName());
        text5.setText(agents.get(i).getAgtBusPhone()); 
        text6.setText(agents.get(i).getAgtEmail()); 
        text7.setText(agents.get(i).getAgtPosition()); 
        text8.setText(agents.get(i).getAgencyId() + ""); 

    }
    
    private void updateTable() { 
        ID.setCellValueFactory(new PropertyValueFactory("AgentId"));
        First.setCellValueFactory(new PropertyValueFactory("AgtFirstName"));
        Initial.setCellValueFactory(new PropertyValueFactory("AgtMiddleInitial"));
        Last.setCellValueFactory(new PropertyValueFactory("AgtLastName"));
        Phone.setCellValueFactory(new PropertyValueFactory("AgtBusPhone"));
        Email.setCellValueFactory(new PropertyValueFactory("AgtEmail"));
        Position.setCellValueFactory(new PropertyValueFactory("AgtPosition"));
        Agency.setCellValueFactory(new PropertyValueFactory("AgencyId"));
        
        TableView.getItems().setAll(agents);
    }
       
}
