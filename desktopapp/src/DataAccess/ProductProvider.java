package DataAccess;

import Business.IEntity;
import Business.Package;
import Business.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class ProductProvider extends EntityProvider
{
    public static IProvider provider = new ProductProvider();

    //TABLE
    private static final String table = "Products";
    //TABLE COLUMNS
    //identifying column
    private static final int idColumn = 0;
    //Auto Increment Column
    private static final int aiColumn = 0;
    //All columns
    private static final String[] allColumns =
            {
                    "ProductId",
                    "ProdName"
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

    public static Product GetById(int id)
    {
        String sql = getById;
        return (Product) provider.Fetch(sql, id);
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

    public static boolean Add(Product product)
    {
        String sql = insert;
        return provider.Insert(sql, product);
    }

    public static boolean Modify(Product newProduct, Product oldProduct)
    {
        String sql = update;
        return provider.Update(sql, newProduct, oldProduct);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        Product product = new Product();
        product.setProductId(rs.getInt(1));
        product.setProdName(rs.getString(2));
        return product;
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
