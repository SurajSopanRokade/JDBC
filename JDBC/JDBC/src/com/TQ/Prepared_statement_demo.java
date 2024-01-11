package com.TQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prepared_statement_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con=MyConnection2PrepareStat.getMySQLConnection();
			String sql="select employee_id,salary from employees where salary> ?";
			PreparedStatement psmt =con.prepareCall(sql);
			psmt.setDouble(1,30000);
			ResultSet rs =psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getDouble(2));
				System.out.println("*********************************");
			}
			
			psmt.clearParameters();
			psmt.setDouble(1, 50000);
			System.out.println("_________________________");
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getDouble(2));
				System.out.println("********************************");
			}
			
			// insert into department
			String sql1 = "insert into test_employee(employee_id, first_name, salary) VALUES(?,?,?)";
			PreparedStatement pt =con.prepareCall(sql1);
			
			
			pt.setDouble(1,7);
			pt.setString(2,"Ishver");
			pt.setDouble(3,2000);
			
			int rss =pt.executeUpdate();
			
		}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		}
	}

}
