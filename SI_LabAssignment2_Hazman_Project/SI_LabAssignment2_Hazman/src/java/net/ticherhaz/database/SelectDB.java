/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ticherhaz.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hazman
 */
public class SelectDB {

    static {
        //STEP 1 : Registering The Driver Class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable To Load The Driver class");
        }
    }

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Database Credentials
            String URL = " jdbc:mysql://localhost:3306/labclass?useTimezone=true&serverTimezone=UTC ";

            String username = "root";

            String password = "";

            //STEP 2 : Creating The Connection Object
            con = DriverManager.getConnection(URL, username, password);

            //STEP 3 : Creating The Statement Object
            stmt = con.createStatement();

            //Constructing The SQL Query
            String sql = "SELECT * FROM EMPLOYEE";

            //Step 4 : Executing The Query
            //We are using executeQuery() method as we are executing SELECT statement
            rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println("Number of column : " + rsmd.getColumnCount());
            //Processing the ResultSet object

            while (rs.next()) {
                System.out.println("ID :" + rs.getInt(1)); //rs.getInt("ID")

                System.out.println("First Name : " + rs.getString(2)); //rs.getString("FIRST_NAME")

                System.out.println("Last Name :" + rs.getString(3)); //rs.getString("LAST_NAME")

                System.out.println("Designation :" + rs.getString(4)); //rs.getString("DESIGNATION")

                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //STEP 5 : Closing The DB Resources

            //Closing the ResultSet object
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Closing the Statement object
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Closing the Connection object
            try {
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
