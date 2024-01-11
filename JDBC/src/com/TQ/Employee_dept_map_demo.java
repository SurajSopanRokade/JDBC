package com.TQ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.assignment1.Que1;

public class Employee_dept_map_demo {
	Connection con;
	ResultSet rsemp, rsdept;
	Statement stemp, stdept;
	HashMap<String, ArrayList<String>> map = new HashMap<>();
	
	public Employee_dept_map_demo() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		con=Que1.connection1();
		stemp = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rsemp = stemp.executeQuery("select * from employees");
		
		stdept = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rsdept = stdept.executeQuery("select * from departments");
	}
	public void createMap() throws SQLException {
		String dname=null;
		int dept=0;

		while(rsdept.next()) {
			dept=rsdept.getInt(1);
			ArrayList<String> list = new ArrayList<>();
			rsemp.beforeFirst();
			while(rsemp.next()) {
				if(rsemp.getInt(11)==dept) {
					dname=rsdept.getString(2);
					list.add(rsemp.getString(2));
				}
			}
			map.put(dname, list);
		}
	}
	public void show() {
		Set<Entry<String,ArrayList<String>>>set=map.entrySet();
		Iterator <Entry<String,ArrayList<String>>> itr =set.iterator();
		while(itr.hasNext()) {
			Entry<String,ArrayList<String>> ent=itr.next();
			if(ent.getValue().size()>0) {
			System.out.println(ent.getKey());
			System.out.println(ent.getValue());
			}
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Employee_dept_map_demo e = new Employee_dept_map_demo();
		e.createMap();
		e.show();
	}

}
