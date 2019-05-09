/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jngetich
 */
public class Utilities {

    
    //converstind resultset to hashmap
    public Map rsToMap(ResultSet rs) {
        Map<String, String> myMap = new HashMap<>();
        Map<String, Map<String, String>> myMaprES = new HashMap<>();
        try {
            if (rs == null) {
                return myMaprES;
            }
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            Integer q = 0;
            while (rs.next()) {
                for (int i = 1; i <= columns; ++i) {
                    if (rs.getString(md.getColumnName(i)) != null) {
                        myMap.put(md.getColumnName(i), rs.getString(md.getColumnName(i)));
                    }
                }
                myMaprES.put(q.toString(), myMap);
                myMap = new HashMap<String, String>();
                q++;
            }
            rs.close();
            return myMaprES;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            return new HashMap<String, String>();
        }
    }
}
