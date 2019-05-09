/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.api;

import com.safaricomhackathon.toyotashowroom.dbconn.DbFunctions;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author jngetich
 */
public class RequestProcessor {

    public String processrequest(String request) {
        String response = "";
        JSONObject jsonResponse = new JSONObject();
        DbFunctions dbf = new DbFunctions();

        ValidateRequest validation = new ValidateRequest();

        JSONObject jsonRequest = new JSONObject(request);
        String service = jsonRequest.getString("service");
        String date = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());

        switch (service) {

            case "register":
                if (validation.validateReg(jsonRequest)) {
                    if (validation.validateColors(jsonRequest.getString("Color"))) {
                        jsonRequest.put("DateCreated", date);

                        boolean save = dbf.saveCar(jsonRequest);
                        if (save) {
                            jsonResponse.put("status", 1);
                            jsonResponse.put("message", "Car saved successfully!");
                        } else {
                            jsonResponse.put("status", 0);
                            jsonResponse.put("message", "Failed to save new car");
                        }

                    } else {
                        jsonResponse.put("status", 0);
                        jsonResponse.put("message", "Invalid color!");
                    }
                } else {
                    jsonResponse.put("status", 0);
                    jsonResponse.put("message", "Invalid request!");

                }
                break;
            case "delete":
                if (jsonRequest.has("car_id")) {
                    if (dbf.deleteCar(jsonRequest)) {
                        jsonResponse.put("status", 0);
                        jsonResponse.put("message", "Invalid request!");
                    } else {
                        jsonResponse.put("status", 0);
                        jsonResponse.put("message", "request failed!");
                    }

                } else {
                    jsonResponse.put("status", 0);
                    jsonResponse.put("message", "Invalid request!");
                }

                break;
            case "viewbluecars":

                if (jsonRequest.has("car_id")) {
                    Map<String, String> resultMap = new HashMap<>();

                    resultMap = dbf.viewblueCarDetails(jsonRequest);
                    JSONObject cars = new JSONObject(resultMap);

                    jsonResponse.put("status", 0);
                    jsonResponse.put("message", cars.toString());

                } else {
                    jsonResponse.put("status", 0);
                    jsonResponse.put("message", "Invalid request!");
                }

                break;

            case "updateCarInfo":
                if (jsonRequest.has("car_id")) {
                    if (jsonRequest.has("color") && validation.validateColors(jsonRequest.getString("color"))) {

                        if (dbf.updateCar(jsonRequest)) {
                            jsonResponse.put("status", 0);
                            jsonResponse.put("message", "Values updated successfully!");
                        } else {
                            jsonResponse.put("status", 0);
                            jsonResponse.put("message", "Invalid request!");

                        }
                    } else {
                        jsonResponse.put("status", 0);
                        jsonResponse.put("message", "Invalid car color !");
                    }

                } else {
                    jsonResponse.put("status", 0);
                    jsonResponse.put("message", "Invalid request!");
                }
                break;
        }
        return jsonResponse.toString();
    }

}
