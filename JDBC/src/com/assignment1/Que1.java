package com.assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//1.	WAP using JDBC to select and print all country names.
public class Que1 {
	public static Connection connection1() throws ClassNotFoundException, SQLException {
		Connection con =null;
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get the connection 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testcoursedb","root","root");
		System.out.println(1);
		return con;
	}
	public static void printCountryNames() throws ClassNotFoundException, SQLException {
		Connection con = connection1();
		String sql="select country_name from countries";
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println("Country_name : "+rs.getString(1));
		}
		
	}
	public static void printEmpManagerName() throws ClassNotFoundException, SQLException {
		Connection con =connection1();
		String sql ="SELECT e1.first_name, e.first_name FROM employees e INNER JOIN employees e1 ON (e.employee_id=e1.manager_id)";
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		System.out.println(3);
		while(rs.next()) {
			System.out.print(" "+"Employee_name : " + rs.getString(1));
			System.out.println(" Manager_name : " + rs.getString(2));
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connection1();
//		printCountryNames();
		printEmpManagerName();
		
	}

}
