package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SectionDAO {
    
    // INSERT YOUR CODE HERE
    String QUERY_FIND = "SELECT * FROM section WHERE subjectid = ? AND num = ? AND termid = ? ORDER BY crn";
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) throws SQLException {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                 ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                
                boolean hasresults = ps.execute();
                
                if (hasresults) {

                    rs = ps.getResultSet();

                    JsonArray sections = new JsonArray();

                    while (rs.next()) {
                        JsonObject section = new JsonObject();
                        section.put("termid", rs.getInt("termid"));
                        section.put("subjectid", rs.getString("subjectid"));
                        section.put("num", rs.getString("num"));
                        section.put("title", rs.getString("title"));
                        sections.add(section);
                    }

                    result = sections.toString();
                }
                
            }
      
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}