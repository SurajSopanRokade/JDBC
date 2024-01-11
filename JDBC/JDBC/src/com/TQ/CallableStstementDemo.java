package com.TQ;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import com.assignment1.Que1;

public class CallableStstementDemo {
	public static Connection connection1() throws ClassNotFoundException, SQLException {
		Connection con =null;
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get the connection 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","root");
		return con;
	}
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con;
		try {
			con=connection1();
			CallableStatement cs = con.prepareCall("{Call GetCustomerLevel(?,?)}");
			cs.setInt(1, 201);
			cs.registerOutParameter(2, Types.VARCHAR);
			System.out.println(cs.execute());
			System.out.println("Customer level is "+cs.getString(2));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
