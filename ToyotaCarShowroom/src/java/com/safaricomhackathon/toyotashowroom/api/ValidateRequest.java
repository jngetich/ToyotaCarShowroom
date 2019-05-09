/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.api;

import com.safaricomhackathon.toyotashowroom.utilities.Configurations;
import java.util.stream.Stream;
import org.json.JSONObject;

/**
 *
 * @author jngetich
 */
public class ValidateRequest {

    //validating registration details
    public static boolean validateReg(JSONObject request) {
        boolean val = false;

        if (request.has("CarMake") && request.has("RegistrationNumber")
                && request.has("YearOfManufuctring")
                && request.has("Type") && request.has("Color")) {
            val = true;

        }

        return val;
    }

    //validating colors in the request to match whats in the config file
    public static boolean validateColors(String color) {
        boolean val = false;
        String configcolors = Configurations.config.get("COLORS").toString();
        String[] procodes = configcolors.split(",");
        val = Stream.of(procodes).anyMatch(x -> x.equals(color));

        return val;
    }

}
