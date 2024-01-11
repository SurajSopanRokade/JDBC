package com.assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Que2 {
	public static Connection connection2() throws ClassNotFoundException, SQLException {
		//1.	WAP using JDBC to delete employees if their salaries are equal to 0.
		Connection con = null;
		// load dirver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get connection
		con =DriverManager.getConnection("jdbc:mysql://localhost:3306/testcoursedb","root","root");
		return con;
	}
	public static void deleteEmployee() throws ClassNotFoundException, SQLException {
		Connection con = Que2.connection2();
		String sql = "DELETE FROM employees WHERE salary=?";
		PreparedStatement psmt =con.prepareCall(sql);
		psmt.setInt(1, 0);
		int rs=psmt.executeUpdate();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		deleteEmployee();
	}

}
