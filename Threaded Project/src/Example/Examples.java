package Example;

import Model.CustomerProvider;
import Model.ProductSupplierProvider;
import Model.ProductProvider;
import Model.PkgProdSupProvider;
import Model.SupplierProvider;
import Model.EntityProvider;
import Model.PackageProvider;
import Model.AgentProvider;
import Business.ProductSupplier;
import Business.Customer;
import Business.PackageProductSupplier;
import Business.Product;
import Business.Package;
import Business.Agent; 

import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Examples
{
    public static void example(String[] args) throws ClassNotFoundException
    {
        //GET ALL FUNCTIONS
        //Call <Entity>Provider.GetAll()
        ArrayList<Agent> agentList = AgentProvider.GetAll();
        for(Agent agent:agentList)
        {
            System.out.println(agent);
        }

        ArrayList<Package> pkgList = PackageProvider.GetAll();
        for(Package pkg:pkgList)
        {
            System.out.println(pkg);
        }

        ArrayList<Product> productList = ProductProvider.GetAll();
        for(Product product:productList)
        {
            System.out.println(product);
        }

        ArrayList<Business.Supplier> supplierList = SupplierProvider.GetAll();
        for(Business.Supplier supplier:supplierList)
        {
            System.out.println(supplier);
        }

        ArrayList<Customer> customerList = CustomerProvider.GetAll();
        for(Customer customer:customerList)
        {
            System.out.println(customer);
        }

        ArrayList<ProductSupplier> productsupplierList = ProductSupplierProvider.GetAll();
        for(ProductSupplier productsupplier:productsupplierList)
        {
            System.out.println(productsupplier);
        }

        ArrayList<PackageProductSupplier> ppsList = PkgProdSupProvider.GetAll();
        for(PackageProductSupplier pps:ppsList)
        {
            System.out.println(pps);
        }

        //GET BY ID FUNCTIONS
        //Call <Entity>Provider.GetById(int)
        Agent agent = AgentProvider.GetById(1);
        System.out.println(agent);

        Package pkg = PackageProvider.GetById(2);
        System.out.println(pkg);

        Product product = ProductProvider.GetById(1);
        System.out.println(product);

        Business.Supplier supplier = SupplierProvider.GetById(13596);
        System.out.println(supplier);

        Customer customer = CustomerProvider.GetById(104);
        System.out.println(customer);

        ProductSupplier productSupplier = ProductSupplierProvider.GetById(1);
        System.out.println(productSupplier);

        //CANNOT GET PACKAGE PRODUCT SUPPLIERS BY ID (COMPOUND ID)
//        PackageProductSupplier productSupplier = PkgProdSupProvider.GetById(1);
//        System.out.println(productSupplier);

        //INSERT FUNCTION
        //Call <Entity>Provider.Add(<Entity>)
        System.out.println(AgentProvider.Add(agent));

        //UPDATE FUNCTION
        //CALL <Entity>Provider.Modify( <Entity> <-New, <Entity> <-Old )
        Agent newAgent = new Agent(17,"A","B","C","D","E","F",0,false); //this will be my new entity
        System.out.println(AgentProvider.Modify(newAgent,AgentProvider.GetById(17))); //THIS IS THE UPDATE CALL IN A PRINT

        //GET WHERE COLUMN = VALUE
        ArrayList<Agent> janets = AgentProvider.GetWhere("AgtFirstName", "Janet");
        for(Agent janet:janets)
        {
            System.out.println(janet);
        }

        //RETURNING AN ERROR MESSAGE
        Agent agt = AgentProvider.GetById(500);
        if (agt == null)
        {
            //EX. All Providers hold the same Message value
            System.out.println(EntityProvider.Message);
            System.out.println(AgentProvider.Message);
            System.out.println(ProductProvider.Message);
        }

        //ADDING PRODUCT SUPPLIERS TO THE PACKAGE
        //There is probably a way better way to do this
        Package myPkg = PackageProvider.GetById(1);
        ArrayList<PackageProductSupplier> myPkgProdSup = PkgProdSupProvider.GetWhere("PackageId",myPkg.getPackageId());
        myPkg.setProductSuppliers(myPkgProdSup);
        System.out.println(myPkg);


    }
}
