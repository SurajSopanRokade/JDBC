package com.TQ;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class FunctionCall {
	public static Connection connection1() throws ClassNotFoundException, SQLException {
		Connection con =null;
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get the connection 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","root");
		return con;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con=connection1();
		CallableStatement cs = con.prepareCall("{?=call get_Num(?)}");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, 1102);
		cs.execute();
		System.out.println(cs.getInt(1));
	}

}
