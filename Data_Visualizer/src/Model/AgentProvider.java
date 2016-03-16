package Model;

import Business.Agent;
import Business.IEntity;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 723403 on 3/9/2016.
 */
public class AgentProvider extends EntityProvider
{
    protected static IProvider provider = new AgentProvider();

    //TABLE
    private static final String table = "Agents";
    //TABLE COLUMNS
    //identifying column
    private static final int idColumn = 0;
    //Auto Increment Column
    private static final int aiColumn = 0;
    //All columns
    private static final String[] allColumns =
                {
                    "AgentId",
                    "AgtFirstName",
                    "AgtMiddleInitial",
                    "AgtLastName",
                    "AgtBusPhone",
                    "AgtEmail",
                    "AgtPosition",
                    "AgencyId",
                    "Active"
                };

    //SQL STATEMENTS
    private static String getAllAgents =
                    "SELECT " + String.join(", ", allColumns) + " " +
                    "FROM " + table + " ORDER BY " + allColumns[0];
    private static String getAgentById =
                    "SELECT " + String.join(", ", allColumns) + " " +
                    "FROM " + table + " " +
                    "WHERE " + allColumns[0] + " = ?";
    private static String insertAgent =
                    "INSERT INTO " + table + " " +
                    "(" +
                            allColumns[1] + ", " +
                            allColumns[2] + ", " +
                            allColumns[3] + ", " +
                            allColumns[4] + ", " +
                            allColumns[5] + ", " +
                            allColumns[6] + ", " +
                            allColumns[7] + ", " +
                            allColumns[8] +
                    ") " +
                    "Values(" + repeat("?", allColumns.length - 1) + ")";
    private static String updateAgent =
                    "UPDATE " + table + " " +
                    "SET " +
                    allColumns[1] + " = ?, " +
                    allColumns[2] + " = ?, " +
                    allColumns[3] + " = ?, " +
                    allColumns[4] + " = ?, " +
                    allColumns[5] + " = ?, " +
                    allColumns[6] + " = ?, " +
                    allColumns[7] + " = ?, " +
                    allColumns[8] + " = ? " +
                    "WHERE " + allColumns[0] + " = ? " +
                    "AND "   + allColumns[1] + " = ? " +
                    "AND "   + allColumns[2] + " = ? " +
                    "AND "   + allColumns[3] + " = ? " +
                    "AND "   + allColumns[4] + " = ? " +
                    "AND "   + allColumns[5] + " = ? " +
                    "AND "   + allColumns[6] + " = ? " +
                    "AND "   + allColumns[7] + " = ? " +
                    "AND "   + allColumns[8] + " = ?";

    public static ArrayList GetAll()
    {
        String sql = getAllAgents;
        return provider.FetchAll(sql);
    }

    public static Agent GetById(int id)
    {
        String sql = getAgentById;
        return (Agent) provider.Fetch(sql, id);
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

    public static boolean Add(Agent agent)
    {
        String sql = insertAgent;
        return provider.Insert(sql, agent);
    }

    public static boolean Modify(Agent newAgent, Agent oldAgent)
    {
        String sql = updateAgent;
        return provider.Update(sql, newAgent, oldAgent);
    }

    public IEntity Construct(ResultSet rs) throws SQLException
    {
        int i = 1;
        Agent agent = new Agent();
        agent.setAgentId(rs.getInt(i++));
        agent.setAgtFirstName(rs.getString(i++));
        agent.setAgtMiddleInitial(rs.getString(i++));
        agent.setAgtLastName(rs.getString(i++));
        agent.setAgtBusPhone(rs.getString(i++));
        agent.setAgtEmail(rs.getString(i++));
        agent.setAgtPosition(rs.getString(i++));
        agent.setAgencyId(rs.getInt(i++));
        agent.setActive(rs.getBoolean(i++));
        return agent;
    }

    @Override
    public PreparedStatement PrepareInsert(PreparedStatement stmt, IEntity entity) throws SQLException
    {
        Agent agent = (Agent)entity;
        stmt.setString(1, agent.getAgtFirstName());
        stmt.setString(2, agent.getAgtMiddleInitial());
        stmt.setString(3, agent.getAgtLastName());
        stmt.setString(4, agent.getAgtBusPhone());
        stmt.setString(5, agent.getAgtEmail());
        stmt.setString(6, agent.getAgtPosition());
        stmt.setInt(7, agent.getAgencyId());
        stmt.setBoolean(8, agent.isActive());
        return stmt;
    }

    public PreparedStatement PrepareUpdate(PreparedStatement stmt, IEntity newEntity, IEntity oldEntity) throws SQLException
    {
        Agent newAgent = (Agent)newEntity;
        Agent oldAgent = (Agent)oldEntity;
        stmt.setString(1, newAgent.getAgtFirstName());
        stmt.setString(2, newAgent.getAgtMiddleInitial());
        stmt.setString(3, newAgent.getAgtLastName());
        stmt.setString(4, newAgent.getAgtBusPhone());
        stmt.setString(5, newAgent.getAgtEmail());
        stmt.setString(6, newAgent.getAgtPosition());
        stmt.setInt(7, newAgent.getAgencyId());
        stmt.setBoolean(8, newAgent.isActive());
        stmt.setInt(9, oldAgent.getAgentId());
        stmt.setString(10, oldAgent.getAgtFirstName());
        stmt.setString(11, oldAgent.getAgtMiddleInitial());
        stmt.setString(12, oldAgent.getAgtLastName());
        stmt.setString(13, oldAgent.getAgtBusPhone());
        stmt.setString(14, oldAgent.getAgtEmail());
        stmt.setString(15, oldAgent.getAgtPosition());
        stmt.setInt(16, oldAgent.getAgencyId());
        stmt.setBoolean(17, oldAgent.isActive());
        return stmt;
    }
}
