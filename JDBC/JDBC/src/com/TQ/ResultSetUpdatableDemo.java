package com.TQ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.assignment1.Que1;

public class ResultSetUpdatableDemo {
	Connection con;
	Statement st;
	ResultSet rs;
	String sql;
	
	public ResultSetUpdatableDemo() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		con=Que1.connection1();
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		sql="select employee_id,first_name,last_name,salary,department_id from employees limit 10";
		rs=st.executeQuery(sql);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
