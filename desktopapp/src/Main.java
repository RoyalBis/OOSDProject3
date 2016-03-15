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

        Agent agent = AgentProvider.GetById(1);
        System.out.println(agent);

        Package pkg = PackageProvider.GetById(2);
        System.out.println(pkg);

        Product product = ProductProvider.GetById(1);
        System.out.println(product);

        Business.Supplier supplier = SupplierProvider.GetById(13596);
        System.out.println(supplier);

        System.out.println(AgentProvider.Add(agent));

        Agent newAgent = new Agent(17,"A","B","C","D","E","F",0,false);
        System.out.println(AgentProvider.Modify(newAgent,AgentProvider.GetById(17)));
        System.out.println(EntityProvider.Message);

        ArrayList<Agent> janets = AgentProvider.GetWhere("AgtFirstName", "Janet");
        for(Agent janet:janets)
        {
            System.out.println(janet);
        }


        System.out.println(AgentProvider.GetById(500));
        System.out.println(EntityProvider.Message);
    }
}
