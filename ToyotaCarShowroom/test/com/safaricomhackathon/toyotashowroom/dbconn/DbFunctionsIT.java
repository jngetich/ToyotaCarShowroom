/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.dbconn;

import java.util.Map;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.invoke.empty.Empty;

/**
 *
 * @author jngetich
 */
public class DbFunctionsIT {

    public DbFunctionsIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveCar method, of class DbFunctions.
     */
    @Test
    public void testSaveCar() {
        System.out.println("Car registration");
        String request = "{\n"
                + "	\"service\": \"register\",\n"
                + "	\"CarMake\": \"0712626174\",\n"
                + "	\"RegistrationNumber\": \"0712626174\",\n"
                + "	\"Type\": \"0712626174\",\n"
                + "	\"Color\": \"red\",\n"
                + "	\"YearOfManufuctring\":\"sssss\"\n"
                + "}";
        JSONObject json = new JSONObject(request);
        DbFunctions instance = new DbFunctions();
        boolean expResult = true;
        boolean result = instance.saveCar(json);
        assertEquals(expResult, result);
        fail("This printing out means the this test failed");
    }

    /**
     * Test of deleteCar method, of class DbFunctions.
     */
    @Test
    public void testDeleteCar() {
        System.out.println("deleting Car");
        String request = "{\n"
                + "	\"service\": \"updateCarInfo\",\n"
                + "	\"car_id\": \"2\",\n"
                + "	\"active\": \"0\"\n"
                + "}";
        JSONObject json = new JSONObject(request);
        DbFunctions instance = new DbFunctions();
        boolean expResult = true;
        boolean result = instance.deleteCar(json);
        assertEquals(expResult, result);
        fail("This printing out means the this test failed");
    }

    /**
     * Test of updateCar method, of class DbFunctions.
     */
    @Test
    public void testUpdateCar() {
        System.out.println("updateCar");
        String request = "{\n"
                + "	\"service\": \"updateCarInfo\",\n"
                + "	\"car_id\": \"2\",\n"
                + "	\"color\": \"0712626174\",\n"
                + "	\"active\": \"0712626174\"\n"
                + "}";
        JSONObject json = new JSONObject(request);
        DbFunctions instance = new DbFunctions();
        boolean expResult = true;
        boolean result = instance.updateCar(json);
        assertEquals(expResult, result);
        fail("This printing out means the this test failed");
    }

    /**
     * Test of viewblueCarDetails method, of class DbFunctions.
     */
    @Test
    public void testViewblueCarDetails() {
        System.out.println("view blue CarDetails");
        JSONObject json = null;
        DbFunctions instance = new DbFunctions();
        Map result = instance.viewblueCarDetails(json);
        if (result.equals(null)) {
            System.out.println("method failed to fetch from DB"); 
        }

    }

}
