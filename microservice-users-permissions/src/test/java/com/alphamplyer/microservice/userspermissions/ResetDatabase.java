package com.alphamplyer.microservice.userspermissions;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ResetDatabase {

    public static void reset(String path) throws ClassNotFoundException, SQLException {

        // Create MySql Connection
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/alphamplyer-microservice-orders-test", "admin", "admin");

        try {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(con);

            // Give the input file to Reader
            Reader reader = new BufferedReader(
                new FileReader(path));

            // Exctute script
            sr.runScript(reader);

            con.close();
        } catch (Exception e) {
            System.err.println("Failed to Execute" + path
                + " The error is " + e.getMessage());
        }
    }
}