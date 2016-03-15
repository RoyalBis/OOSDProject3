package DataAccess;

import Business.IEntity;
import Business.Package;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class PackageProvider extends EntityProvider
{
    private static final IProvider provider = new PackageProvider();

    //TABLE
    private static final String table = "Packages";
    //TABLE COLUMNS
    //identifying column
    private static final int idColumn = 0;
    //Auto Increment Column
    private static final int aiColumn = 0;
    //All columns
    private static final String[] allColumns =
            {
                    "PackageId",
                    "PkgName",
                    "PkgStartDate",
                    "PkgEndDate",
                    "PkgDesc",
                    "PkgBasePrice",
                    "PkgAgencyCommission"
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
            allColumns[1] + ", " +
            allColumns[2] + ", " +
            allColumns[3] + ", " +
            allColumns[4] + ", " +
            allColumns[5] + ", " +
            allColumns[6] + ") " +
            "Values(" + repeat("?", allColumns.length - 1) + ")";

    private static final String update =
            "UPDATE " + table + " " +
            "SET " +
            allColumns[1] + " = ?, " +
            allColumns[2] + " = ?, " +
            allColumns[3] + " = ?, " +
            allColumns[4] + " = ?, " +
            allColumns[5] + " = ?, " +
            allColumns[6] + " = ? " +
            "WHERE " + allColumns[0] + " = ? " +
            "AND "   + allColumns[1] + " = ? " +
            "AND "   + allColumns[2] + " = ? " +
            "AND "   + allColumns[3] + " = ? " +
            "AND "   + allColumns[4] + " = ? " +
            "AND "   + allColumns[5] + " = ? " +
            "AND "   + allColumns[6] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAll;
        entityList = provider.FetchAll(sql);
        return entityList;
    }

    public static Package GetById(int id)
    {
        String sql = getById;
        return (Package) provider.Fetch(sql, id);
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

    public static boolean Add(Package pkg)
    {
        String sql = insert;
        return provider.Insert(sql, pkg);
    }

    public static boolean Modify(Package newPkg, Package oldPkg)
    {
        String sql = update;
        return provider.Update(sql, newPkg, oldPkg);
    }

    @Override
    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Package pkg = new Package();
        pkg.setPackageId(rs.getInt(i++));
        pkg.setPkgName(rs.getString(i++));
        pkg.setPkgStartDate(rs.getDate(i++));
        pkg.setPkgEndDate(rs.getDate(i++));
        pkg.setPkgDesc(rs.getString(i++));
        pkg.setPkgBasePrice(rs.getBigDecimal(i++));
        pkg.setPkgAgencyCommission(rs.getBigDecimal(i++));
        return pkg;
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
