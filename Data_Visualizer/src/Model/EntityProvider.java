package Model;

import Business.IEntity;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by 723403 on 3/9/2016.
 */
public abstract class EntityProvider implements IProvider
{
    protected static ArrayList entityList;
    public static String Message;

    public void ExceptionThrown(Exception e)
    {
        System.out.println(e.getMessage());
        Message = e.getMessage();
        e.printStackTrace();
    }

    public ArrayList FetchAll(String sql)
    {
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            BulkQuery(rs);

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return entityList;
    }

    public IEntity Fetch(String sql, int id)
    {
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                try
                {
                    return this.Construct(rs);
                }
                catch (Exception e)
                {
                    ExceptionThrown(e);
                }
            }
            else
            {
                Message = "No Result with id = " + id;
            }

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return null;
    }

    //FETCH WHERE///////////////////////////////////////////////////////////////////////////////////////
    public ArrayList FetchWhere(String sql, String val){
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,val);
            ResultSet rs = stmt.executeQuery();
            BulkQuery(rs);

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return entityList;
    }

    public ArrayList FetchWhere(String sql, int val){
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,val);
            ResultSet rs = stmt.executeQuery();
            BulkQuery(rs);

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return entityList;
    }

    public ArrayList FetchWhere(String sql, boolean val){
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,val);
            ResultSet rs = stmt.executeQuery();
            BulkQuery(rs);

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return entityList;
    }

    public ArrayList FetchWhere(String sql, Date val){
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1,val);
            ResultSet rs = stmt.executeQuery();
            BulkQuery(rs);

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return entityList;
    }

    public ArrayList FetchWhere(String sql, BigDecimal val){
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBigDecimal(1,val);
            ResultSet rs = stmt.executeQuery();
            BulkQuery(rs);

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return entityList;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////


    public boolean Insert(String sql, IEntity entity)
    {
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt = PrepareInsert(stmt, entity);
            int rs = stmt.executeUpdate();
            if (rs == 1)
            {
                conn.commit();
                return true;
            }
            else
            {
                Message = "Insert Failed";
            }

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return false;
    }

    public boolean Update(String sql, IEntity newEntity, IEntity oldEntity){

        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt = PrepareUpdate(stmt, newEntity, oldEntity);
            int rs = stmt.executeUpdate();
            if (rs == 1)
            {
                conn.commit();
                return true;
            }
            else
            {
                Message = "Insert Failed";
            }

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return false;
    }

    private void BulkQuery(ResultSet rs) throws SQLException
    {
        if (rs.next())
        {
            do
            {
                try
                {
                    entityList.add(this.Construct(rs));
                } catch (Exception e)
                {
                    ExceptionThrown(e);
                }
            } while (rs.next());
        }
        else
        {
            Message = "No matching results";
        }
        return;
    }

    protected static String repeat(String in, int count){
        String out = "";
        for (int i = 0; i < count; i++)
        {
            if(i != 0 ) {out += ", ";}
            out += in;
        }
        return out;
    }
}

