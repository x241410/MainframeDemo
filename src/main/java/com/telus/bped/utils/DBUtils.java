package com.telus.bped.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;

import com.test.utils.DatesUtils;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;
import com.test.utils.SystemProperties;


import com.telus.api.test.utils.APIJava;
import com.test.reporting.Reporting;
import com.test.ui.actions.WebDriverSteps;

public class DBUtils {


	static String connectionString = null;
	public static Connection Conn = null;
	static ResultSet Resultset = null;

	

	public Connection dbConnect(String host, String port, String serviceName, String username, String password) throws SQLException {

		Reporting.logReporter(Status.INFO, "Oracle JDBC Driver Successfully Registered! Let's make connection now");
        String connectionString = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + serviceName;

        try {
            // Returns the Class object associated with the class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException exception) {
            System.out.println("Oracle Driver Class Not found Exception: " + exception.toString());
        }
        // Set connection timeout. Make sure you set this correctly as per your need
        DriverManager.setLoginTimeout(5);

        try {
			// Attempts to establish a connection
			Conn = DriverManager.getConnection(connectionString, username, password);
			System.out.println("Oracle JDBC Driver Successfully Registered! Let's make connection now");
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return Conn;
	}

	public void dbDisConnect() throws SQLException {

		try {
			if (!Conn.isClosed()) {
				Resultset = null;
				Conn.close();
				Conn = null;
			}
		} catch (SQLException e) {
			Reporting.logReporter(Status.INFO, "Exception during DB Disconnect");

		}
		Reporting.logReporter(Status.INFO, "DB Disconnected successfully");
	}
	

	public void callDBDisconnect() throws SQLException {

		if (!Conn.isClosed()) {
			Conn.close();
		}
	
	}

}
