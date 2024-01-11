package com.TQ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.assignment1.Que1;

public class ResultSetScrollingDemo {
	Connection con;
	Statement st;
	ResultSet rs;
	String sql;
	
	public ResultSetScrollingDemo() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		con=Que1.connection1();
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		sql="SELECT employee_id, first_name, salary, department_id FROM employees LIMIT 10";
		rs=st.executeQuery(sql);
	}
	public void showScrollingataforward() throws SQLException {
		System.out.println("_______________________________________________________");
		System.out.println("emp_id"+"\t"+"name"+"\t"+"salary"+"\t"+"dept_no");
		System.out.println("_______________________________________________________");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
		}
	}
	public void showScrollingataReverse() throws SQLException {
		System.out.println("____________________________________________________________");
		System.out.println("emp_id"+"\t"+"name"+"\t"+"salary"+"\t"+"dept_no");
		System.out.println("_______________________________________________________");
		rs.afterLast();
		while(rs.previous()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
		}
	}
	public void showsAbsolutePosition(int i) throws SQLException {
//		rs.absolute(i);
//		System.out.println("____________________________________________________________");
//		System.out.println("emp_id"+"\t"+"name"+"\t"+"salary"+"\t"+"dept_no");
//		System.out.println("_______________________________________________________");
//		System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
		
//		rs.absolute(i);
//		System.out.println("____________________________________________________________");
//		System.out.println("emp_id"+"\t"+"name"+"\t"+"salary"+"\t"+"dept_no");
//		System.out.println("_______________________________________________________");
//		System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
		rs.beforeFirst();
		rs.relative(3);
		System.out.println("____________________________________________________________");
		System.out.println("emp_id"+"\t"+"name"+"\t"+"salary"+"\t"+"dept_no");
		System.out.println("_______________________________________________________");
		System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ResultSetScrollingDemo r = new ResultSetScrollingDemo();
//		r.showScrollingataforward();
//		r.showScrollingataReverse();
//		r.showsAbsolutePosition(2);
	}

}
