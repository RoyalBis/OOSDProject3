package Controller;
 
import Business.Agent; 
import Business.Customer;

import Model.AgentProvider;
import Model.CustomerProvider;

import View.Master;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;

/**======================================|
 * @author Mark C. Willms                |
 * --------------------------------------|
 * Controller Status:        COMPLETE    |
 * -> update:                COMPLETE    |
 * -> insert:                COMPLETE    |
 * -> customer reassignment: COMPLETE    |
 * -> validation:            INCOMPLETE  |
 *    -> setActive/InActive: COMPLETE    |
 *    -> newAgt form fill:   INCOMPLETE  |
 * --------------------------------------|
 * Code Optimization required, currently |
 * too many DB calls than I would prefer |
 *                                       |
 * The Addition of more global objects   |
 * opposed to index would significantly  |
 * improve performance.                  |
 *                                       |
 * - Mark                                |
 *=====================================*/

public class AgentController implements Initializable {
    // ------------------- FXML --------------------- \\
    @FXML private TableView<Agent> TableView;
    @FXML private TableColumn<Agent, String> ID;
    @FXML private TableColumn<Agent, String> First;
    @FXML private TableColumn<Agent, String> Initial;
    @FXML private TableColumn<Agent, String> Last;
    @FXML private TableColumn<Agent, String> Phone;
    @FXML private TableColumn<Agent, String> Email;
    @FXML private TableColumn<Agent, String> Position;
    @FXML private TableColumn<Agent, String> Agency;
    
    @FXML private TextField agtId;
    @FXML private TextField agtFirst;
    @FXML private TextField agtInitial;
    @FXML private TextField agtLast;
    @FXML private TextField agtPhone;
    @FXML private TextField agtEmail;
    @FXML private TextField agtPosition;
    @FXML private TextField agtAgency;
    
    @FXML private TextField newAgtId;
    @FXML private TextField newAgtFirst;
    @FXML private TextField newAgtInitial;
    @FXML private TextField newAgtLast;
    @FXML private TextField newAgtPhone;
    @FXML private TextField newAgtEmail;
    @FXML private TextField newAgtPosition;
    @FXML private TextField newAgtAgency;
    
    @FXML private TableView<Customer> custTable; 
    @FXML private TableColumn<Customer, String> custID; 
    @FXML private TableColumn<Customer, String> custName; 
    @FXML private TableColumn<Customer, String> custAgt; 
    
    @FXML TextField cust; 
    @FXML TextField agt; 
    @FXML TextField newAgt; 
    
    @FXML private ComboBox Combo; 
    @FXML private CheckBox activeBox; 
    
    // --------------- Array Lists ---------------- \\
    List<Integer> arr = new ArrayList<>(); 
    List<Agent> agents = new ArrayList<>(); 
    List<Customer> customers = new ArrayList<>(); 
    
    // --------------- Objects -------------------- \\
    Customer customer; 
    private Master main;
    int Index; 
    
    /*================================================||
    |    Function List:                                |
    |__________________________________________________|
    |    handleAgentUpdate                             |
    |    handleComboBox                                | 
    |    handleCreateAgent                             |
    |    handleCustReAssignment                        |
    |    handleCustomerSelection                       |
    |    handleTableSelection                          |
    |--------------------------------------------------|
    |    setMaster                                     |
    |    setText                                       |
    |--------------------------------------------------|
    |    loadCustomerTable                             |
    |    updateTable                                   |
    /================================================*/
    
    // On Form Creation 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        updateTable();        
    }
    
    // Create Link to Master 
    public void setMaster(Master main) {
        this.main = main;     
    }

    // On Save Updates Press
    @FXML protected void handleAgentUpdate(ActionEvent event) { 
        Agent currentAgent = agents.get(Index);
        Agent newAgent = new Agent(
            100, // Should not matter as its auto-incremented
            agtFirst.getText(), 
            agtInitial.getText(),
            agtLast.getText(),
            agtPhone.getText(),
            agtEmail.getText(),
            agtPosition.getText(),
            Integer.parseInt(agtAgency.getText()), 
            activeBox.isSelected() // Set The Agent to Active Automatically 
        );
        AgentProvider.Modify(newAgent, currentAgent);
        updateTable();
    }
    
    // Insert Function 
    @FXML protected void handleCreateAgent(ActionEvent event) { 
        Agent agent = new Agent(
            100, // Should not matter as its auto-incremented
            newAgtFirst.getText(), 
            newAgtInitial.getText(),
            newAgtLast.getText(),
            newAgtPhone.getText(),
            newAgtEmail.getText(),
            newAgtPosition.getText(),
            Integer.parseInt(newAgtAgency.getText()), 
            true // Set The Agent to Active Automatically 
        ); 
        System.out.println("Adding Agent");
        AgentProvider.Add(agent); 
        updateTable(); 
    }
    
    // On ComboBox Select
    @FXML protected void handleComboBox(ActionEvent event) {
        int i = Combo.getSelectionModel().getSelectedIndex();
        if(i < 0 ) return;        
        setText(i); 
    }
    
    // Selecting the Agent Table
    @FXML protected void handleTableSelection(MouseEvent t) {
        int i = TableView.getSelectionModel().getSelectedIndex();
        if(i < 0 ) return;         
        setText(i); 
    }
    
    // Selecting The Customer Table 
    @FXML protected void handleCustomerSelection(MouseEvent t) {
        int i = custTable.getSelectionModel().getSelectedIndex();
        if(i < 0 ) return; 
        customer = customers.get(i); 
        cust.setText(customer.getCustFirstName() + " " + customer.getCustLastName());
        agt.setText(customer.getAgentId() + "");
        agt.setEditable(false);        
    }
    
    // Submit Button; Re-Assigning Customers to new Agents 
    @FXML protected void handleCustReassignment(ActionEvent event) throws CloneNotSupportedException { 
        // If No Customer Selected Then Exit The Function 
        if(customer == null) 
            return; 
        // Add one to normalize the index (0->1) 
        int i = Combo.getSelectionModel().getSelectedIndex(); 
        // Check To See if New Agent is Active
        if(agents.get(i).isActive() == false) { 
            main.errorModal("You may only assign Customers to Active Agents");
            return; 
        }
        // Clone The Current Customer 
        Customer newCustomer = customer.clone();  
        // Set New Value & Modify Customer 
        newCustomer.setAgentId(i+1);
        CustomerProvider.Modify(newCustomer, customer); 
        loadCustomerTable(); 
        customer = null; 
    }
    
    // Make Sure that No Customers are bound to inactive Agents
    @FXML protected void activeValidation(MouseEvent t) {
        if(customers.size() > 0) { 
           main.errorModal("Please re-assign all current customers before marking"
                + " an Agent inactive");
           activeBox.setSelected(true);
        }
    }
    
    // Re-Load The Customer Table
    private void loadCustomerTable() { 
        customers.clear(); 
        customers = CustomerProvider.GetWhere("AgentId", Index+1); 
        
        // Bind Customer Table
        custID.setCellValueFactory(   new PropertyValueFactory("CustomerId"));
        custName.setCellValueFactory( new PropertyValueFactory("CustFirstName"));
        custAgt.setCellValueFactory(  new PropertyValueFactory("AgentId"));
        
        // Load Corresponding Customers 
        custTable.getItems().setAll(customers); 
    }
    
    // Set The Text To A Certain Index 
    public void setText(int i) { 
        // Fill The TextView On Selected Index
        agtId.setText(         agents.get(i).getAgentId() + "");
        agtFirst.setText(      agents.get(i).getAgtFirstName());
        agtInitial.setText(    agents.get(i).getAgtMiddleInitial());
        agtLast.setText(       agents.get(i).getAgtLastName());
        agtPhone.setText(      agents.get(i).getAgtBusPhone()); 
        agtEmail.setText(      agents.get(i).getAgtEmail()); 
        agtPosition.setText(   agents.get(i).getAgtPosition()); 
        agtAgency.setText(     agents.get(i).getAgencyId() + ""); 
        activeBox.setSelected( agents.get(i).isActive());
        
        // Set Global Index and Load The Appropriate Customers 
        Index = i; 
        loadCustomerTable(); 
    }
    
    // Update The Agent Table
    private void updateTable() { 
        // Re-Build The Agent Object Array 
        agents.clear(); 
        agents = AgentProvider.GetAll(); 
        // Update The Combo Box
        Combo.getItems().clear(); 
        arr.clear(); 
        for(int i = 0; i < agents.size(); i++) {
            arr.add(agents.get(i).getAgentId());
        }
        Combo.getItems().setAll(arr); 
        
        // Re-Bind The Table
        ID.setCellValueFactory(       new PropertyValueFactory("AgentId"));
        First.setCellValueFactory(    new PropertyValueFactory("AgtFirstName"));
        Initial.setCellValueFactory(  new PropertyValueFactory("AgtMiddleInitial"));
        Last.setCellValueFactory(     new PropertyValueFactory("AgtLastName"));
        Phone.setCellValueFactory(    new PropertyValueFactory("AgtBusPhone"));
        Email.setCellValueFactory(    new PropertyValueFactory("AgtEmail"));
        Position.setCellValueFactory( new PropertyValueFactory("AgtPosition"));
        Agency.setCellValueFactory(   new PropertyValueFactory("AgencyId"));
        
        // Re-Load The Table Data
        TableView.getItems().setAll(agents);
    }
       
}
