/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Business.Package;
import Business.ProductSupplier; 
import Business.Product; 
import Business.Supplier; 
import Model.PackageProvider;
import Model.ProductSupplierProvider;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author 715200
 */
public class PackageViewController implements Initializable {
    
    @FXML private TableView<Package> TableView;
    @FXML private TableColumn<Package, String> ID;
    @FXML private TableColumn<Package, String> Name;
    @FXML private TableColumn<Package, String> Start;
    @FXML private TableColumn<Package, String> End;
    @FXML private TableColumn<Package, String> Description;
    @FXML private TableColumn<Package, String> BasePrice;
    @FXML private TableColumn<Package, String> Commission;
    
    @FXML private TableView ProSupTable; 
    @FXML private TableColumn<Product, String> Product;
    @FXML private TableColumn<Supplier, String> Supplier;
    
    @FXML Button Save; 
    List<Package> packages = new ArrayList<>();
    List<ProductSupplier> productSupp = new ArrayList<>(); 
    List<Product> product = new ArrayList<>(); 
    List<Supplier> supplier = new ArrayList<>(); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        packages = PackageProvider.GetAll(); 
        updateTable(); 
    }    
    
    private void updateTable() { 
        ID.setCellValueFactory(new PropertyValueFactory("PackageId"));
        Name.setCellValueFactory(new PropertyValueFactory("PkgName"));
        Start.setCellValueFactory(new PropertyValueFactory("PkgStartDate"));
        End.setCellValueFactory(new PropertyValueFactory("PkgEndDate"));
        Description.setCellValueFactory(new PropertyValueFactory("PkgDesc"));
        BasePrice.setCellValueFactory(new PropertyValueFactory("PkgBasePrice"));
        Commission.setCellValueFactory(new PropertyValueFactory("PkgAgencyCommission"));
        
        TableView.getItems().setAll(packages);
    }
    
    @FXML protected void Terminate(ActionEvent event) throws IOException{ 
        System.exit(0);
    }
    
    @FXML private void ShowProductSupplier(ActionEvent event) throws IOException{ 
        // Display Fly-Out
        Stage mainWindow;
        mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        mainWindow.setWidth(818);
        
        // Build Array 
        productSupp = ProductSupplierProvider.GetAll(); 
         
        for(int i = 0; i < productSupp.size(); i++) { 
            
            product.add(productSupp.get(i).getProduct());
            //Product.setCellValueFactory(new PropertyValueFactory(product.get(i).getProdName()));
            supplier.add(productSupp.get(i).getSupplier());
        }

        Product.setCellValueFactory(new PropertyValueFactory("Product"));
        Product.setCellValueFactory(cellData -> cellData.getValue().getProdName());
        
        Supplier.setCellValueFactory(new PropertyValueFactory("Supplier"));
        
        //ProSupTable.getItems().setAll(productSupp);
    }
    
    @FXML private void MinimizeView(ActionEvent event) throws IOException{ 
        Stage mainWindow;
        mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        mainWindow.setWidth(615);
    }
    
    @FXML private void handleSceneChange(ActionEvent event) throws IOException{ 
        Parent PackageView; 
        PackageView = FXMLLoader.load(getClass().getResource("/View/AgentView.fxml"));

        Scene newScene; 
        newScene = new Scene(PackageView, 615, 650);

        Stage mainWindow;
        mainWindow = (Stage) Save.getScene().getWindow();
        mainWindow.setWidth(615);
        mainWindow.setHeight(650);
        mainWindow.setTitle("Agent Manager");
        mainWindow.setScene(newScene); 
    }
}
