package com.TQ;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class ClassConnectionAnotherMethod {
	public Connection NewConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection con = null;
		Driver d =(Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Properties p = new Properties();
		p.put("user", "root");
		p.put("passward", "root");
		con=d.connect("jdbc:mysql://localhost:3306/classicmodels", p);
		return con;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
