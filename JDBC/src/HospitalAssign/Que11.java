package HospitalAssign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Que11 {
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	Statement st;
	
	public static Connection connection1() throws ClassNotFoundException, SQLException {
		//1. Create Connection with hospital database.
		Connection con =null;
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get the connection 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
		return con;
	}
	public void listPatientDetailsAgeAbove50() throws ClassNotFoundException, SQLException {
		//2. List All Patient details with age above 50
		con=connection1();
		sql="SELECT pid, pname FROM patient WHERE age>?";
		psmt=con.prepareCall(sql);
		psmt.setInt(1,60);
		ResultSet rs =psmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
		}
	}
	
	public void inserRecordInHospital() throws ClassNotFoundException, SQLException {
		//3. Insert new record in Patient table using PreparedStatement object.
		con=connection1();
		st=con.createStatement();
		sql="INSERT INTO patient(pid, pname, age, weight, email, admissiondate) VALUES (16,'Ram', 29,68,'ram@example.com','2024-8-4')";
		int r=st.executeUpdate(sql);
		System.out.println(r);
	}
	
	public void updateEmail() throws ClassNotFoundException, SQLException {
		//4. Update email address of patient whose name is ‘K.V.Naik’. Use 
		//Statement object to do the same.
		con=connection1();
		st=con.createStatement();
		sql="UPDATE patient "
				+ " SET email = 'ishaa@example.com' "
				+ "  WHERE pname ='ishawar' ";
		int r= st.executeUpdate(sql);
		System.out.println(r);
		
	}
	
	public void deleteRecordForSpecific_pid() throws SQLException, ClassNotFoundException {
		//5. Delete all records from patient table for given patient id. Use 
		//PreparedStatement for the same
		con=connection1();
		sql="DELETE FROM patient WHERE pid=?";
		psmt=con.prepareCall(sql);
		psmt.setInt(1, 16);
		int r=psmt.executeUpdate();
		System.out.println(r);
	}
	public void retriveAndShowForward() throws ClassNotFoundException, SQLException {
		//7. Retrive all records from Patient table in Result Set and display them in 
		//reverse and forward order
		con=connection1();
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		sql="SELECT * FROM patient";
		rs=st.executeQuery(sql);
		System.out.println("pid"+"\t\t"+"pname"+"\t\t"+"page"+"\t\t"+"pweight"+"\t\t"+"email"+"\t\t"+"Adate"+"\t\t");
		System.out.println("____________________________________________________________________________________________");
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getFloat(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getDate(6)+"\t\t");
		}
		System.out.println("pid"+"\t\t"+"pname"+"\t\t"+"page"+"\t\t"+"pweight"+"\t\t"+"email"+"\t\t"+"Adate"+"\t\t");
		System.out.println("____________________________________________________________________________________________");
		rs.afterLast();
		while(rs.previous()) {
			System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getFloat(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getDate(6)+"\t\t");
		}
	}
	
	public void deleteRecord(int i) throws SQLException {
		//8. Delete record no 5 from resultset.
		rs.beforeFirst();
		rs.absolute(i);
		rs.deleteRow();
	}
	
	public void insertNewRecord() throws ClassNotFoundException, SQLException {
		//9. Insert new record in ResultSet so that it would be added in databade table also.
		con=connection1();
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		sql="SELECT * FROM patient";
		rs=st.executeQuery(sql);
		rs.moveToInsertRow();
		rs.updateInt(1, 16);
		rs.updateString(2, "Ram");
		rs.updateInt(3, 35);
		rs.updateFloat(4, 70);
		rs.updateString(5, "ram@example.com");
		rs.updateDate(6, null);
		rs.insertRow();
		
	}
	
	public void updateRecord(int i) throws SQLException, ClassNotFoundException {
		//10.Update weight to 55.50 kg for record from ResultSet where pid is 1101.
		con=connection1();
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		sql="SELECT * FROM patient";
		rs=st.executeQuery(sql);
		rs.absolute(i);
		rs.updateInt(4, 80);
		rs.updateRow();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Que11 q1 = new Que11();
//		q1.listPatientDetailsAgeAbove50();
//		q1.inserRecordInHospital();
//		q1.updateEmail();
//		q1.deleteRecordForSpecific_pid();
//		q1.retriveAndShowForward();
//		q1.insertNewRecord();
//		q1.updateRecord(16);
	}

}
