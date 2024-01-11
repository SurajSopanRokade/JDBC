package com.TQ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.assignment1.Que1;

public class DemoResultSetMetadata {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		{
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;

			try
			{
				
				con = Que1.connection1();
				System.out.println("Connected...");
				
				st = con.createStatement();

				String sql = "select * from employees limit 10";
				rs = st.executeQuery(sql);
			
				System.out.println("-------------ResultMetaData=-----------------");
				
				ResultSetMetaData rsmd = rs.getMetaData();
				
				System.out.println("\nNo. of columns : " + rsmd.getColumnCount());
				while(rs.next())
				{	
				
					for(int i=1; i<=rsmd.getColumnCount(); i++)
					{
						System.out.print("\nColumns name : " + rsmd.getColumnName(i)+" SQL Data type : " + rsmd.getColumnTypeName(i));
						System.out.println();
						System.out.println(rs.getString(i));
						System.out.println("____________________________");
					}
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}

}
