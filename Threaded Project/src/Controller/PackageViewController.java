/*
 * Package Controller: Mark Willms 
 */
package Controller;
 
import Business.Package;
import Business.PackageProductSupplier;
import Business.ProductSupplier; 
import Business.Product; 
import Business.Supplier; 

import Model.PackageProvider;
import Model.PkgProdSupProvider;
import Model.ProductSupplierProvider;
import View.Master;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    
    @FXML private TableView SideTable; 
    @FXML private TableColumn<Product, String> allProduct;
    @FXML private TableColumn<Supplier, String> allSupplier;
    
    @FXML Button Save; 
    
    @FXML private TextField PkgId;
    @FXML private TextField PkgName;
    @FXML private DatePicker PkgStart;
    @FXML private DatePicker PkgEnd;
    @FXML private TextField PkgPrice;
    @FXML private TextField PkgCommission;
    @FXML private TextArea PkgDesc;
    
    private Master main; 
    
    List<Package> packages            = new ArrayList<>();
    List<ProductSupplier> allProSup   = new ArrayList<>(); 
    List<ProductSupplier> productSupp = new ArrayList<>(); 
    List<Product> product             = new ArrayList<>(); 
    List<Supplier> supplier           = new ArrayList<>(); 
    
    // Initialize The Controller (on-load of corresponding FXML Scene) 
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // Load The Globals
        packages  = PackageProvider.GetAll(); 
        allProSup = ProductSupplierProvider.GetAll(); 
        
        for (Package pkg : packages) {
            pkg.setProductSuppliers(PkgProdSupProvider.GetWhere("PackageId", pkg.getPackageId()));
        }
        
        // Place Data in Tables 
        loadSideViewTable(); 
        updateTable();       
    }    
    
    // Set Reference to Master Controller
    public void setMaster(Master main) {
        this.main = main;     
    }
    
    // Event Handler on the ProductSupplier Side View Click
    public void handleSideViewClick(MouseEvent t) { 
        int i = SideTable.getSelectionModel().getSelectedIndex();
        ProSupTable.getItems().add(allProSup.get(i)); 
    }
    
    // Fill Text Fields Based on Selected Index in Package Table
    public void handleTableClick(MouseEvent t) {  
        // Fill The "Update Tab" TextView Form 
        int i = TableView.getSelectionModel().getSelectedIndex();
        PkgId.setText(          packages.get(i).getPackageId()+ "");
        PkgName.setText(        packages.get(i).getPkgName() + "");
        PkgStart.setPromptText( packages.get(i).getPkgStartDate().toString());
        PkgEnd.setPromptText(   packages.get(i).getPkgEndDate().toString());
        PkgDesc.setText(        packages.get(i).getPkgDesc()+ "");
        PkgPrice.setText(       packages.get(i).getPkgBasePrice() + "");
        PkgCommission.setText(  packages.get(i).getPkgAgencyCommission() + "");
        
        // Build Product Supplier Table
        ProSupTable.getItems().clear();
        ArrayList<ProductSupplier> prodsupp;
        ArrayList<PackageProductSupplier> tmpArr = packages.get(i).getProductSuppliers();
        
        Product.setCellValueFactory(  new PropertyValueFactory("Product"));
        Supplier.setCellValueFactory( new PropertyValueFactory("Supplier"));
        for(int ii = 0; ii < tmpArr.size(); ii++) { 
            prodsupp = ProductSupplierProvider.GetWhere("ProductSupplierId", tmpArr.get(ii).getProductsupplier().getProductSupplierId()); 
            System.out.println(prodsupp); 
            ProSupTable.getItems().addAll(prodsupp);
        }
     }
    
    // Update The Package Table 
    private void updateTable() { 
        ID.setCellValueFactory(          new PropertyValueFactory("PackageId"));
        Name.setCellValueFactory(        new PropertyValueFactory("PkgName"));
        Start.setCellValueFactory(       new PropertyValueFactory("PkgStartDate"));
        End.setCellValueFactory(         new PropertyValueFactory("PkgEndDate"));
        Description.setCellValueFactory( new PropertyValueFactory("PkgDesc"));
        BasePrice.setCellValueFactory(   new PropertyValueFactory("PkgBasePrice"));
        Commission.setCellValueFactory(  new PropertyValueFactory("PkgAgencyCommission"));
        
        TableView.getItems().setAll(packages);
    }
    
    // Update The SideView Table (full product supplier table)  
    private void loadSideViewTable() { 
        allProduct.setCellValueFactory(  new PropertyValueFactory("Product"));
        allSupplier.setCellValueFactory( new PropertyValueFactory("Supplier"));
        
        SideTable.getItems().setAll(allProSup);
    }
}
