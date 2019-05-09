/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safaricomhackathon.toyotashowroom.dbconn;

import com.safaricomhackathon.toyotashowroom.utilities.Configurations;
import java.sql.Connection;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author jngetich
 */
public class DbConnection {

    public Connection conn = null;
    javax.sql.DataSource ds;
    public Statement stmt = null;
    public Context ctx = null;

    public DbConnection() {

        this.conn = getDbConn();
    }

    public Connection getDbConn() {
        Connection connection = null;

        try {
            String dsName = Configurations.config.get("datasource").toString();
            ctx = new InitialContext();
            ds = (javax.sql.DataSource) ctx.lookup(dsName);

            connection = ds.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
