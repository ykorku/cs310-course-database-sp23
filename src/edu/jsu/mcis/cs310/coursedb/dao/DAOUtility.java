package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                ArrayList<String> columnNames = new ArrayList<>();

                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(rsmd.getColumnName(i));
                }

                while (rs.next()) {
                    JsonObject record = new JsonObject();
                    for (String columnName : columnNames) {
                        record.put(columnName, rs.getString(columnName));
                    }
                    records.add(record);
                }
                
                
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
