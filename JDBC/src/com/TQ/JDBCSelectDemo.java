package com.TQ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSelectDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con = MyConnection.getMySQL();
			String sql="Select*from departments";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
//			while(rs.next()) {
//				System.out.println("Department_id "+rs.getInt(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getInt(3));
//				System.out.println(rs.getInt(4));
//				System.out.println("*****************************");
//			}
//			sql= "insert into departments (department_id, department_name, manager_id, location_id) VALUES(245,'Hr',107, 1700)";
//			int cnt= st.executeUpdate(sql);
//			System.out.println(cnt);
			
//			sql= "update departments set department_id=244 where department_id=245";
//			int cnt= st.executeUpdate(sql);
//			System.out.println(cnt);
			
//			sql= "delete from departments where department_id=244";
//			int cnt =st.executeUpdate(sql);
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
