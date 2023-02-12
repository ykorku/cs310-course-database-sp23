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
    String url = "jdbc:mysql://localhost/jsu_sp23_v1";
    String username="cs310_p2_user";
    String password="P2!user";
    
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
                
                // INSERT YOUR CODE HERE
                String query = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
            
                int records = ps.executeUpdate();
                
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
                
                // INSERT YOUR CODE HERE
                String query = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
            
                result = ps.execute();
                
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
                String query = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
            
                result = ps.execute();
                
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
        
        
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                    JsonArray results = new JsonArray();
                    String query = "SELECT * FROM registration ORDER BY crn";
                    ps = conn.prepareStatement(query);
                    rs = ps.getResultSet();

                    while(rs.next()) {
                        JsonObject jsonobject= new JsonObject();
                        
                        int id = rs.getInt("studentid");
                        int tid = rs.getInt("termid");
                        String lastname = rs.getString("lastname");
                        jsonobject.put("studentid",tid);
                        jsonobject.put("termid",tid);
                        jsonobject.put("lastname",lastname);
                        results.add(jsonobject);
                        
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
