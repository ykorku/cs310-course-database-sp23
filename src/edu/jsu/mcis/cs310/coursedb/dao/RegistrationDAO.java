package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
    
    // INSERT YOUR CODE HERE
    private final Connection connection = null;
    private static final String QUERY_CREATE = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
    private static final String QUERY_DELETE = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
    private static final String QUERY_DELETE2 = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
    private static final String QUERY_LIST = "SELECT * FROM registration ORDER BY crn";
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
                    result = true;
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

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_DELETE);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    result = true;

                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(QUERY_DELETE2);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        JsonArray registrations = new JsonArray();
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                String query = "SELECT studentid, termid, crn FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                rs = ps.executeQuery();
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    JsonObject registration = new JsonObject();
                    registration.put("studentid", rs.getInt("studentid"));
                    registration.put("termid", rs.getInt("termid"));
                    registration.put("crn", rs.getInt("crn"));
                    registrations.add(registration);
                }
                
                
                result = registrations.toString();    
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
