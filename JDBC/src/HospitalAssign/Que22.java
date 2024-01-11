package HospitalAssign;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Que22 {
	Connection con;
	ResultSet rs;
	PreparedStatement psmt;
	Statement st;
	String sql;
	public Que22() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		con=connection1();
		sql="SELECT * FROM patient";
		psmt=con.prepareCall(sql);
		st=con.createStatement();
		rs=st.executeQuery(sql);
	}
	public Connection connection1() throws ClassNotFoundException, SQLException {
		//1. Create Connection with hospital database.
		Connection con=null;
		// load driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// get connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
		return con;	
	}
	public void fetchPatientNames() throws SQLException {
		//2. Fetch Patient names admitted after 5th May 2020 and age above 50 and store it in ArrayList.
		sql="SELECT pname FROM patient WHERE admissiondate>'2020-5-5' AND age>50";
		st=con.createStatement();
		rs=st.executeQuery(sql);
		ArrayList<String> list = new ArrayList<>();
		while(rs.next()) {
			list.add(rs.getString(1));
		}
		System.out.println(list);
	}
	
	public void createHashMap() throws ClassNotFoundException, SQLException {
		//3. Create HashMap with patient id as key and patient Name as value and display it.
		sql="SELECT pid, pname FROM patient";
		con=connection1();
		st=con.createStatement();
		rs=st.executeQuery(sql);
		HashMap<Integer, String> map = new HashMap<>();
		while(rs.next()) {
			map.put(rs.getInt(1), rs.getString(2));
		}
		System.out.println(map);
	}
	
	public void createHashMapDoc() throws ClassNotFoundException, SQLException {
		//4. Create a HashMap with docid as key and list of patients treated by that doctor as value. Show all entries in HashMap.
		sql="SELECT * FROM patient";
		con=connection1();
		Statement pst=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet prs=pst.executeQuery(sql);
		
		sql="SELECT * FROM doctor2";
		Statement dst=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet drs=dst.executeQuery(sql);
		int doc_id;
		
		HashMap<Integer, ArrayList<Patient>> map = new HashMap<>();
		while(drs.next()) {
			doc_id=drs.getInt(1);
			ArrayList<Patient> list = new ArrayList<>();
			prs.beforeFirst();
			while(prs.next()) {
				if (doc_id==prs.getInt(7)) {
					Patient p = new Patient(prs.getInt(1),prs.getInt(3), prs.getFloat(4),prs.getString(2), prs.getString(5));
					list.add(p);
				}
			}
			map.put(doc_id, list);
		}
		Set<Entry<Integer, ArrayList<Patient>>> set = map.entrySet();
		Iterator<Entry<Integer, ArrayList<Patient>>> itr = set.iterator();
		while(itr.hasNext()) {
			Entry<Integer, ArrayList<Patient>>ent=itr.next();
			System.out.println("Doctor_id : "+ent.getKey());
			ArrayList<Patient> list= ent.getValue();
			Iterator<Patient> itr1 = list.iterator();
			while(itr1.hasNext()) {
				System.out.println("Patients : "+itr1.next());
			}
			System.out.println("_________________________________________________________________________________________________________");
		}
		
	}
	

	public void createSet() throws ClassNotFoundException, SQLException {
		//5. Create a Set of patient ids and sort it in descending order of patient ids.
		TreeSet<Integer> set = new TreeSet<>();
		sql="SELECT pid FROM patient";
		con=connection1();
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs=st.executeQuery(sql);
		rs.afterLast();
		while(rs.previous()) 
		{  
			set.add(rs.getInt(1));
		}
		Iterator<Integer>itr=set.descendingIterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
	}
	
	public void patientInfo() throws ClassNotFoundException, SQLException {
		//6. Create Stored Procedure in database which takes pid as input parameter 
		//and returns patient age and name as output parameters. Call this 
		//procedure through java application
		con=connection1();
		CallableStatement cs = con.prepareCall("{Call PatientInfo(?, ?, ?)}");
		cs.setInt(1, 2);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.registerOutParameter(3, Types.VARCHAR);
		System.out.println(cs.execute());
		System.out.println("patient Age : "+cs.getInt(2));
		System.out.println("Patient name : "+cs.getString(3));	
	}
	
	public void docSpeciality() throws ClassNotFoundException, SQLException {
		//7. Create a Stored Procedure which takes Doctor’s name as input 
		//parameter and returns his speciality as output parameter. Call this stored 
		//procedure in java Application.
		con=connection1();
		CallableStatement cs = con.prepareCall("{Call doctorSpeciality(?, ?)}");
		cs.setString(1, "Dr. Smith");
		cs.registerOutParameter(2, Types.VARCHAR);
		System.out.println(cs.execute());
		System.out.println("doctor speciality : "+cs.getString(2));
	}
	public void givePatientDetails() throws ClassNotFoundException, SQLException {
//		8. Create Stored Procedure which takes doctor id as input parameter and 
//		returns patient name, patient id and his age as result Set. Call This Stored 
//		Procedure in Java Application
		con=connection1();
		CallableStatement cs = con.prepareCall("{call get_Patient(?)}");
		cs.setInt(1, 1);
		ResultSet rs =cs.executeQuery();
		while(rs.next()) {
			System.out.println("pid : "+rs.getInt(1)+" pname : "+rs.getString(2)+" age : "+rs.getInt(3));
		}
	}
	public void returnCountPatient() throws ClassNotFoundException, SQLException {
		//9. Create Function to return count of patients admitted on given date and 
		//call this function in java Application.
		con=connection1();
		CallableStatement cs = con.prepareCall("{? = Call GetCount(?)}");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setDate(2,Date.valueOf("2023-06-10"));
		System.out.println(cs.execute());
		System.out.println(cs.getInt(1));
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Que22 q1 = new Que22();
//		q1.fetchPatientNames();
//		q1.createHashMap();
//		q1.createSet();
//		q1.createHashMapDoc();
//		q1.patientInfo();
//		q1.docSpeciality();
//		q1.returnCountPatient();
//		q1.givePatientDetails();
	}

}
