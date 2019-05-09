/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.api;

import com.safaricomhackathon.toyotashowroom.utilities.Configurations;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author jngetich
 */
@WebServlet(name = "ToyotaAPI", urlPatterns = {"/ToyotaAPI"})
public class ToyotaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String requestString = "";
        String outresponse = "";
        //get client IP from the request header
        String client_ip = request.getRemoteAddr();

        //get a list of allowed clients IPS from Configurations list
        String allowedclients = Configurations.config.get("ALLOWED_CLIENTS").toString();
        String[] allowed_clients = allowedclients.split(",");
        boolean allowed = Stream.of(allowed_clients).anyMatch(x -> x.equals(client_ip));
        if (allowed) {
            try {
                StringBuilder stringBuilder = new StringBuilder(256);

                BufferedReader reader = request.getReader();
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
                requestString = stringBuilder.toString();

                if (requestString.isEmpty()) {

                    outresponse = "Empty request body!";

                } else {

                    if (isJSON(requestString)) {
                        outresponse = new RequestProcessor().processrequest(requestString);

                    } else {

                        outresponse = "Request is not a JSON Object";

                    }

                }

                reader.close();

            } catch (IOException ex) {

            }
        } else {
            outresponse = "Client not allowed to do this transaction";
        }

        out.print(outresponse);

        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        out.print("GET METHOD NOT ALLOWED");
    }

    public boolean isJSON(String request) {
        try {
            JSONObject jsonObject = new JSONObject(request);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
