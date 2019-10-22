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

    public DBConnectionManager() {
    }

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

    public static double getInterestRateDB(final String bankName) throws ClassNotFoundException, SQLException {
        double value = 0;
        Class.forName(JDBC_DRIVER);
        connectionObject = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        Connection connection = DBConnectionManager.setDBConnection();
        Statement st;
        ResultSet rs;
        st = connection.createStatement();
        rs = st.executeQuery("SELECT interestRate FROM bank WHERE bankName='" + bankName + "'");

        while (rs.next()) {
            
            int numColumns = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                // Column numbers start at 1.
                // Also there are many methods on the result set to return
                //  the column as a particular type. Refer to the Sun documentation
                //  for the list of valid conversions.
                value = rs.getDouble(i);
            }
        }
        return value;
    }
}
