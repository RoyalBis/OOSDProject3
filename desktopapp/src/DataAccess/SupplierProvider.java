package DataAccess;

import Business.IEntity;
import Business.Product;
import Business.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class SupplierProvider extends EntityProvider
{
    private static final IProvider provider = new SupplierProvider();

    //TABLE
    private static final String table = "Suppliers";
    //TABLE COLUMNS
    //identifying column
    private static final int idColumn = 0;
    //Auto Increment Column
    private static final int aiColumn = 0;
    //All columns
    private static final String[] allColumns =
            {
                    "SupplierId",
                    "SupName"
            };

    //SQL STATEMENTS
    private static final String getAll =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " ORDER BY " + allColumns[0];

    private static final String getById =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE " + allColumns[0] + " = ?";

    private static final String getWhere =
            "SELECT " + String.join(", ", allColumns) + " " +
            "FROM " + table + " " +
            "WHERE ? = ?";

    private static final String insert =
            "INSERT INTO " + table + " " +
            "(" +
            allColumns[1] + ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static final String update =
            "UPDATE " + table + " " +
            "SET " +
            allColumns[1] + " = ? " +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        entityList = provider.FetchAll(sql);
        return entityList;
    }

    public static Supplier GetById(int id)
    {
        String sql = getById;
        return (Supplier) provider.Fetch(sql, id);
    }

    public static ArrayList GetWhere(String col, String val )
    {
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
        return provider.FetchWhere(sql, val);
    }

    public static boolean Add(Supplier supplier)
    {
        String sql = insert;
        return provider.Insert(sql, supplier);
    }

    public static boolean Modify(Supplier newSupplier, Supplier oldSupplier)
    {
        String sql = update;
        return provider.Update(sql, newSupplier, oldSupplier);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(rs.getInt(1));
        supplier.setSupName(rs.getString(2));
        return supplier;
    }

    @Override
    public PreparedStatement PrepareInsert(PreparedStatement stmt, IEntity entity)
    {
        return null;
    }

    @Override
    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        return null;
    }
}
