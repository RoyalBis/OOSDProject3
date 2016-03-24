package Model;

import Business.Customer;
import Business.IEntity;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/15/2016.
 */
public class CustomerProvider extends EntityProvider
{
    protected static IProvider provider = new CustomerProvider();

    //TABLE
    private static final String table = "Customers";
    //TABLE COLUMNS
    //identifying column
    private static final int idColumn = 0;
    //Auto Increment Column
    private static final int aiColumn = 0;
    //All columns
    private static final String[] allColumns =
            {
                    "CustomerId",
                    "CustFirstName",
                    "CustLastName",
                    "CustAddress",
                    "CustCity",
                    "CustProv",
                    "CustPostal",
                    "CustCountry",
                    "CustHomePhone",
                    "CustBusPhone",
                    "CustEmail",
                    "AgentId"
            };

    //SQL STATEMENTS
    private static String getAll =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " ORDER BY " + allColumns[0];

    private static String getById =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE " + allColumns[0] + " = ?";

    private static String insert =
            "INSERT INTO " + table + " " +
            "(" +
                    allColumns[1]  + ", " +
                    allColumns[2]  + ", " +
                    allColumns[3]  + ", " +
                    allColumns[4]  + ", " +
                    allColumns[5]  + ", " +
                    allColumns[6]  + ", " +
                    allColumns[7]  + ", " +
                    allColumns[8]  + ", " +
                    allColumns[9]  + ", " +
                    allColumns[10] + ", " +
                    allColumns[11] +
            ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static String update =
            "UPDATE " + table + " " +
            "SET " +
                       allColumns[1]  + " = ?, " +
                       allColumns[2]  + " = ?, " +
                       allColumns[3]  + " = ?, " +
                       allColumns[4]  + " = ?, " +
                       allColumns[5]  + " = ?, " +
                       allColumns[6]  + " = ?, " +
                       allColumns[7]  + " = ?, " +
                       allColumns[8]  + " = ?, " +
                       allColumns[9]  + " = ?, " +
                       allColumns[10] + " = ?, " +
                       allColumns[11] + " = ? "  +
            "WHERE " + allColumns[0]  + " = ? "  +
            "AND "   + allColumns[1]  + " = ? "  +
            "AND "   + allColumns[2]  + " = ? "  +
            "AND "   + allColumns[3]  + " = ? "  +
            "AND "   + allColumns[4]  + " = ? "  +
            "AND "   + allColumns[5]  + " = ? "  +
            "AND "   + allColumns[6]  + " = ? "  +
            "AND "   + allColumns[7]  + " = ? "  +
            "AND "   + allColumns[8]  + " = ? "  +
            "AND "   + allColumns[9]  + " = ? "  +
            "AND "   + allColumns[10] + " = ? "  +
            "AND "   + allColumns[11] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        return provider.FetchAll(sql);
    }

    public static Customer GetById(int id)
    {
        String sql = getById;
        return (Customer) provider.Fetch(sql, id);
    }

    //GET WHERE OVERLOADS///////////////////////////////////////////////////////////////////////////////
    public static ArrayList GetWhere(String col, String val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, int val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, boolean val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, java.sql.Date val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }

    public static ArrayList GetWhere(String col, BigDecimal val )
    {
        String sql = prepWhere(col);
        return provider.FetchWhere(sql, val);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String prepWhere(String col){
        int i;
        for (i = 0; i < allColumns.length; i++)
        {
            if(col.equals(allColumns[i]))
            {
                break;
            }
        }
        String sql =
                "SELECT " + String.join(", ", allColumns) + " " +
                "FROM " + table + " " +
                "WHERE " + allColumns[i] + "  = ?";
        return sql;
    }

    public static boolean Add(Customer customer)
    {
        String sql = insert;
        return provider.Insert(sql, customer);
    }

    public static boolean Modify(Customer newCustomer, Customer oldCustomer)
    {
        String sql = update;
        return provider.Update(sql, newCustomer, oldCustomer);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt(i++));
        customer.setCustFirstName(rs.getString(i++));
        customer.setCustLastName(rs.getString(i++));
        customer.setCustAddress(rs.getString(i++));
        customer.setCustCity(rs.getString(i++));
        customer.setCustProv(rs.getString(i++));
        customer.setCustPostal(rs.getString(i++));
        customer.setCustCountry(rs.getString(i++));
        customer.setCustHomePhone(rs.getString(i++));
        customer.setCustBusPhone(rs.getString(i++));
        customer.setCustEmail(rs.getString(i++));
        customer.setAgentId(rs.getInt(i++));
        return customer;
    }

    @Override
    public PreparedStatement PrepareInsert(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        Customer customer = (Customer)entity;
        stmt.setString(1, customer.getCustFirstName());
        stmt.setString(2, customer.getCustLastName());
        stmt.setString(3, customer.getCustAddress());
        stmt.setString(4, customer.getCustCity());
        stmt.setString(5, customer.getCustProv());
        stmt.setString(6, customer.getCustPostal());
        stmt.setString(7, customer.getCustCountry());
        stmt.setString(5, customer.getCustHomePhone());
        stmt.setString(6, customer.getCustBusPhone());
        stmt.setString(7, customer.getCustEmail());
        stmt.setInt(8, customer.getAgentId());
        return stmt;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        Customer newCustomer = (Customer) newEntity;
        Customer oldCustomer = (Customer)oldEntity;
        stmt.setString(1, newCustomer.getCustFirstName());
        stmt.setString(2, newCustomer.getCustLastName());
        stmt.setString(3, newCustomer.getCustAddress());
        stmt.setString(4, newCustomer.getCustCity());
        stmt.setString(5, newCustomer.getCustProv());
        stmt.setString(6, newCustomer.getCustPostal());
        stmt.setString(7, newCustomer.getCustCountry());
        stmt.setString(8, newCustomer.getCustHomePhone());
        stmt.setString(9, newCustomer.getCustBusPhone());
        stmt.setString(10, newCustomer.getCustEmail());
        stmt.setInt(11, newCustomer.getAgentId());
        stmt.setInt(12, oldCustomer.getCustomerId());
        stmt.setString(13, oldCustomer.getCustFirstName());
        stmt.setString(14, oldCustomer.getCustLastName());
        stmt.setString(15, oldCustomer.getCustAddress());
        stmt.setString(16, oldCustomer.getCustCity());
        stmt.setString(17, oldCustomer.getCustProv());
        stmt.setString(18, oldCustomer.getCustPostal());
        stmt.setString(19, oldCustomer.getCustCountry());
        stmt.setString(20, oldCustomer.getCustHomePhone());
        stmt.setString(21, oldCustomer.getCustBusPhone());
        stmt.setString(22, oldCustomer.getCustEmail());
        stmt.setInt(23, oldCustomer.getAgentId());
        return stmt;
    }
}
