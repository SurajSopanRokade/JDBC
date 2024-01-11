package com.TQ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection2PrepareStat {
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		Connection con=null;
		// load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testcoursedb", "root", "root");
		return con;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
