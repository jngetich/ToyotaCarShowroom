/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.dbconn;

import com.safaricomhackathon.toyotashowroom.utilities.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author jngetich
 */
public class DbFunctions {

    private DbConnection dbConn = new DbConnection();

    private void getDBconn() {
        this.dbConn = new DbConnection();
    }

    //INSETING CAR DETAILS TO THE DATABASE
    public boolean saveCar(JSONObject json) {
        boolean excecute = false;
        try {
            String sqlString = "INSERT INTO TBCARDETAILS (Carmake,Color,RegistrationNumber,YearOfManufacturing,DateCreated)\n"
                    + "VALUES(?,?,?,?,?)";
            Map<Integer, Object> params = new HashMap<>();
            PreparedStatement preparedStatement = dbConn.conn.prepareStatement(sqlString);
            preparedStatement.setString(1, json.getString("CarMake"));
            preparedStatement.setString(2, json.getString("Color"));
            preparedStatement.setString(3, json.getString("RegistrationNumber"));
            preparedStatement.setString(4, json.getString("YearOfManufuctring"));
            preparedStatement.setString(5, json.getString("DateCreated"));
            excecute = preparedStatement.execute();
            excecute = true;
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return excecute;

    }

    //delete car record
    public boolean deleteCar(JSONObject json) {
        boolean excecute = false;
        try {
            String sqlString = "update TBCARDETAILS set Active='0' where ID=?";
            PreparedStatement preparedStatement = dbConn.conn.prepareStatement(sqlString);
            preparedStatement.setString(1, json.getString("car_id"));
            excecute = preparedStatement.execute();
            excecute = true;
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return excecute;

    }

    //update car record
    public boolean updateCar(JSONObject json) {
        boolean excecute = false;
        String sqlString = "";
        PreparedStatement preparedStatement = null;
        try {

            if (json.has("color") && json.has("active")) {
                sqlString = "update TBCARDETAILS set Color=?,Active=?  where ID=?";
                preparedStatement = dbConn.conn.prepareStatement(sqlString);
                preparedStatement.setString(1, json.getString("color"));
                preparedStatement.setString(2, json.getString("active"));
                preparedStatement.setString(3, json.getString("car_id"));
            } else if (json.has("color") && (!json.has("active"))) {
                sqlString = "update TBCARDETAILS set Color=?  where ID=?";
                preparedStatement = dbConn.conn.prepareStatement(sqlString);
                preparedStatement.setString(1, json.getString("color"));
                preparedStatement.setString(2, json.getString("car_id"));

            } else if ((!json.has("color")) && json.has("active")) {
                sqlString = "update TBCARDETAILS set Active=?  where ID=?";
                preparedStatement = dbConn.conn.prepareStatement(sqlString);
                preparedStatement.setString(1, json.getString("active"));
                preparedStatement.setString(2, json.getString("car_id"));

            }

            excecute = preparedStatement.execute();
            excecute = true;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return excecute;

    }

    //view blue cars record
    public Map viewblueCarDetails(JSONObject json) {
        boolean excecute = false;
        Map<String, String> resultMap = new HashMap<>();
        ResultSet rs = null;

        try {
            String sqlString = "select * from TBCARDETAILS WHERE Active='1' AND Color=blue";
            PreparedStatement preparedStatement = dbConn.conn.prepareStatement(sqlString);
            preparedStatement.setString(1, json.getString("car_id"));
            rs = preparedStatement.executeQuery();
            resultMap = new Utilities().rsToMap(rs);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return resultMap;

    }

}
