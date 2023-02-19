package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
            
            //if there are results add the data for each row to a json object, then add those json objects to the jsonarray
             if (rs != null) {
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                
                while (rs.next()) {
                    JsonObject record = new JsonObject();
                    for (int i = 1; i <= columnCount; i++) {
                        record.put(metadata.getColumnName(i), rs.getObject(i));
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