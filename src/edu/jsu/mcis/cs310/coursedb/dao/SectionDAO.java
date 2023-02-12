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
                String sql = "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                rs = ps.executeQuery();
                
                rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
                JsonArray jArray= new JsonArray();
                
                while (rs.next()) {
                    for (int i = 1; i <= columns; i++) {
                        JsonObject jsonobject= new JsonObject();
                        jsonobject.put(rsmd.getColumnName(i), rs.getString(i));
                        jArray.add(jsonobject);
                    }   
                } 
                result=Jsoner.serialize(jArray);
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