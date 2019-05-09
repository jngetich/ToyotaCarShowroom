/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.utilities;

import java.util.stream.Stream;

/**
 *
 * @author jngetich
 */
public class Tester {

    public static void main(String[] args) {

        String allowedclients = Configurations.config.get("ALLOWED_CLIENTS").toString();
        String[] allowed_clients = allowedclients.split(",");
        boolean allowed = Stream.of(allowed_clients).anyMatch(x -> x.equals("ss"));
        System.out.println(allowed);

    }
}
