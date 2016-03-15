package DataAccess;

import Business.IEntity;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

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
            return entityList;

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return null;
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

    public ArrayList FetchWhere(String sql, String val){
        entityList = new ArrayList();
        try (Connection conn = Database.connect())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,val);
            ResultSet rs = stmt.executeQuery();
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
            return entityList;

        } catch (SQLException e)
        {
            ExceptionThrown(e);
        } catch (ClassNotFoundException e)
        {
            ExceptionThrown(e);
        }
        return null;
    }

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

