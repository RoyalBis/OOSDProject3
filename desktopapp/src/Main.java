import Business.Agent;
import Business.Package;
import Business.Product;
import DataAccess.*;

import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Main
{
    public static void main(String[] args) throws ClassNotFoundException
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
    }
}
