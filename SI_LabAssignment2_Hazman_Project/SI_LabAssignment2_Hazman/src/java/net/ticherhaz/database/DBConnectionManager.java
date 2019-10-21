/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ticherhaz.database;

import java.sql.*;

/**
 *
 * @author Hazman
 */
public class DBConnectionManager {

    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "nbuser";
    static final String DB_URL = "jdbc:mysql://localhost:3306/si_labassignment2_hazman?zeroDateTimeBehavior=convertToNull";

    private static Connection connectionObject;

    public static Connection setDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connectionObject = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return connectionObject;
    }
}
