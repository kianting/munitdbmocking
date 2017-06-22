package org.kianting.mulesoft.inmemorydb.derby;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import org.springframework.beans.factory.InitializingBean;

public class DBInitialization  implements InitializingBean {
	
  public void afterPropertiesSet() throws Exception {
		
		String dbURL = "jdbc:derby:DemoDb";
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
			Statement stmt = conn.createStatement();
			//InputStream is = this.getClass().getResourceAsStream("DemoDB.sql");
			System.out.println("Loading SQL Querry :" + this.getClass().getResource("DemoDB.sql").getPath());
			FileReader fr = new FileReader(this.getClass().getResource("DemoDB.sql").getPath());
	        Scanner scanner = new Scanner(fr);
	        while (scanner.hasNextLine()) {
	        	stmt.executeUpdate(scanner.nextLine());
	        }
	        stmt.executeUpdate(scanner.nextLine());
	        scanner.close();
	        //is.close();
		} 
		catch (java.sql.SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		}
	}
}
