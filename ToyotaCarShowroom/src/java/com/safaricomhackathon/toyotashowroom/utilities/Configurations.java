/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author jngetich
 */
public class Configurations {

    private static Properties props;
    public static HashMap config = getConfig();

   

    public static HashMap getConfig() {
        HashMap configuration = new HashMap();
        InputStream inputStream = null;


        String propsFile = "D:\\ToyotaCarShowroom\\Configurations\\Configurations.properties";

        try {
            inputStream = new FileInputStream(propsFile);

            props = new Properties();

            props.load(inputStream);

            Set<String> propertyNames = props.stringPropertyNames();

            for (String Property : propertyNames) {
                configuration.put(Property.trim(), props.getProperty(Property).trim());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return configuration;
    }

}
