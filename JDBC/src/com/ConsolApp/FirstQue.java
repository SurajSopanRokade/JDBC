package com.ConsolApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FirstQue {
	Connection con;
	ResultSet rs;
	Statement st;
	PreparedStatement psmt;
	String sql;

	public Connection connection1() throws ClassNotFoundException, SQLException {
		Connection con = null;
		// load driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// get connection
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testcoursedb", "root", "root");
		return con;

	}

	public FirstQue() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		con = connection1();
		sql = "SELECT * FROM employees";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		psmt = con.prepareCall(sql);
		rs = st.executeQuery(sql);
	}

	public void showCountries() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT country_id, country_name FROM countries";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(sql);
		HashMap<String, String> map = new HashMap<>();
		while (rs.next()) {
			map.put(rs.getString(1), rs.getString(2));
		}
		System.out.println(map);
	}

	public void showRegions() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT region_id , region_name FROM regions";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(sql);
		HashMap<Integer, String> map = new HashMap<>();
		while (rs.next()) {
			map.put(rs.getInt(1), rs.getString(2));
		}
		System.out.println(map);
	}

	public void showLocations() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT location_id , city FROM locations";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(sql);
		HashMap<Integer, String> map = new HashMap<>();
		while (rs.next()) {
			map.put(rs.getInt(1), rs.getString(2));
		}
		System.out.println(map);
	}

	public void showDepartments() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT department_id , department_name FROM departments";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(sql);
		HashMap<Integer, String> map = new HashMap<>();
		while (rs.next()) {
			map.put(rs.getInt(1), rs.getString(2));
		}
		System.out.println(map);
	}

	public void showEmployees() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT employee_id , first_name FROM employees";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(sql);
		HashMap<Integer, String> map = new HashMap<>();
		while (rs.next()) {
			map.put(rs.getInt(1), rs.getString(2));
		}
		System.out.println(map);
	}

	public void showJobs() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT job_id, job_title FROM jobs";
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(sql);
		HashMap<String, String> map = new HashMap<>();
		while (rs.next()) {
			map.put(rs.getString(1), rs.getString(2));
		}
		System.out.println(map);
	}

	public void addNewRegion(double region_id, String region_name) throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "INSERT INTO regions (region_id, region_name) VALUES(?,?)";
		psmt = con.prepareCall(sql);
		psmt.setDouble(1, region_id);
		psmt.setString(2, region_name);
		int rs = psmt.executeUpdate();
		System.out.println(rs);
	}

	public void addNewCountry(String country_id, String country_name, double region_id)
			throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "INSERT INTO countries (country_id, country_name, region_id) VALUES(?,?,?)";
		psmt = con.prepareCall(sql);
		psmt.setString(1, country_id);
		psmt.setString(2, country_name);
		psmt.setDouble(3, region_id);
		int rs = psmt.executeUpdate();
		System.out.println(rs);
	}

	public void deleteRegion(int a) throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "DELETE FROM regions WHERE region_id=?";
		psmt = con.prepareCall(sql);
		psmt.setInt(1, a);
		int rs = psmt.executeUpdate();
		System.out.println(rs);
	}

	public void deleteCountry(String a) throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "DELETE FROM countries WHERE country_id=?";
		psmt = con.prepareCall(sql);
		psmt.setString(1, a);
		int rs = psmt.executeUpdate();
		System.out.println(rs);
	}

	public void showEmpWithHightestSal() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT employee_id, first_name, salary FROM employees ORDER BY salary DESC LIMIT 1";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println(
					"Emp_id : " + rs.getInt(1) + " first_name : " + rs.getString(2) + " salary : " + rs.getDouble(3));
		}
	}

	public void showEmpWithLowestSal() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT employee_id, first_name, salary FROM employees ORDER BY salary ASC LIMIT 1";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println(
					"Emp_id : " + rs.getInt(1) + " first_name : " + rs.getString(2) + " salary : " + rs.getDouble(3));
		}
	}

	public void showEmpStartfirstnameWithA() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT employee_id, first_name, salary FROM employees WHERE first_name LIKE 'A%' ";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println(
					"Emp_id : " + rs.getInt(1) + " first_name : " + rs.getString(2) + " salary : " + rs.getDouble(3));
		}
	}

	public void showEmpWhoHiredIn1987() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT employee_id, first_name, salary FROM employees WHERE year(hire_date)=1987 ";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println(
					"Emp_id : " + rs.getInt(1) + " first_name : " + rs.getString(2) + " salary : " + rs.getDouble(3));
		}
	}

	public void showDeptListWithHighEmployees() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT department_name, COUNT(employee_id) AS MAX FROM departments d LEFT JOIN employees e ON (e.department_id=d.department_id) GROUP BY department_name ORDER BY MAX DESC ";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println("department_name : " + rs.getString(1) + " count : " + rs.getInt(2));
		}
	}

	public void showTop3ManagersHighEmployees() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT e1.manager_id, COUNT(e.employee_id) AS MAX FROM employees e INNER JOIN employees e1 ON (e.employee_id=e1.employee_id) GROUP BY e1.manager_id ORDER BY MAX DESC LIMIT 3";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println("employee_id : " + rs.getInt(1) + " count : " + rs.getInt(2));
		}
	}

	public void showMiniSalForEachDept() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT e.department_id, MIN(e.salary) FROM employees e INNER JOIN employees e1 ON (e.employee_id=e1.employee_id) GROUP BY e.department_id";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println("department_id : " + rs.getInt(1) + " Minsalary : " + rs.getDouble(2));
		}
	}

	public void showMaxSalForEachDept() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT e.department_id, MAX(e.salary) FROM employees e INNER JOIN employees e1 ON (e.employee_id=e1.employee_id) GROUP BY e.department_id";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println("department_id : " + rs.getInt(1) + " Maxsalary : " + rs.getDouble(2));
		}
	}

	public void showLocationMaxDept() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "SELECT city, COUNT(d.location_id) AS MAX FROM locations l RIGHT JOIN departments d ON (l.location_id=d.location_id) GROUP BY city ORDER BY MAX DESC";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println("city : " + rs.getString(1) + " MaxDepts : " + rs.getInt(2));
		}
	}

	public void showRegionWithHighestNoOfEmp() throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = " SELECT r.region_id, COUNT(employee_id) AS maxx\r\n" + " FROM employees e\r\n"
				+ " INNER JOIN departments d ON (e.department_id=d.department_id)\r\n"
				+ " INNER JOIN locations l ON (d.location_id=l.location_id)\r\n"
				+ " INNER JOIN countries c ON (c.country_id=l.country_id)\r\n"
				+ " INNER JOIN regions r ON (r.region_id=c.region_id)\r\n" + " GROUP BY r.region_id\r\n"
				+ " ORDER BY maxx DESC LIMIT 1";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.println("region_id : " + rs.getInt(1) + " Countemp : " + rs.getInt(2));
		}
	}

	public void updateRegion(String region_name, int region_id) throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "UPDATE regions SET region_name=? WHERE region_id=?";
		psmt = con.prepareStatement(sql);
		psmt.setString(1, region_name);
		psmt.setInt(2, region_id);
		psmt.executeUpdate();
	}

	public void updateCountry(String country_id, String country_name) throws ClassNotFoundException, SQLException {
		con = connection1();
		sql = "UPDATE regions SET country_name=? WHERE country_id=?";
		psmt = con.prepareStatement(sql);
		psmt.setString(1, country_name);
		psmt.setString(2, country_id);
		psmt.executeUpdate();
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		FirstQue f1 = new FirstQue();

		char s = 'a';
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your choise");
			System.out.println("a. Show " + "b. Insert " + "c. Update " + "d. Delete " + "e. Methods ");
			String st = sc.next();
			switch (st) {

			case "a": {
				System.out.println("Enter you choise");
				System.out.println("1.	Regions\r\n" + "2.	Countries\r\n" + "3.	Locations\r\n"
						+ "4.	Departments\r\n" + "5.	Employees\r\n" + "6.	Jobs\r\n");
				int i = sc.nextInt();
				switch (i) {
				case 1:
					f1.showCountries();
					break;
				case 2:
					f1.showRegions();
					break;
				case 3:
					f1.showLocations();
					break;
				case 4:
					f1.showDepartments();
					break;
				case 5:
					f1.showEmployees();
					break;
				case 6:
					f1.showJobs();
					break;
				}
			}
				break;
			case "b": {
				System.out.println("Enter you choise" + "1. Add new region" + "2. Add new country");
				int j = sc.nextInt();
				if (j == 1) {
					System.out.println("Enter region_id ");
					int r = sc.nextInt();
					System.out.println("Enter region_name ");
					String rn = sc.next();
					f1.addNewRegion(r, rn);
				}
				if (j == 2) {
					System.out.println("Enter country_id ");
					String c_id = sc.next();
					System.out.println("Enter country_name ");
					String cn = sc.next();
					System.out.println("Enter region_id ");
					int r = sc.nextInt();
					f1.addNewCountry(c_id, cn, r);
				}
			}
				break;
			case "c": {
				System.out.println("Enter you choise" + "1. delete region" + "2. delete country");
				int k = sc.nextInt();
				if (k == 1) {
					System.out.println("Enter region_id you want to delete");
					int id = sc.nextInt();
					f1.deleteRegion(id);
				}
				if (k == 2) {
					System.out.println("Enter country_id you want to delete");
					String id = sc.next();
					f1.deleteCountry(id);
				}
			}
				break;
			case "d": {
				System.out.println("Enter you choise" + "1. update region" + "2. update country");
				int m = sc.nextInt();
				if (m == 1) {
					System.out.println("Enter region_id you want to update");
					int id = sc.nextInt();
					System.out.println("Enter region name for update");
					String uname = sc.next();
					f1.updateRegion(uname, id);
				}
				if (m == 2) {
					System.out.println("Enter country_id you want to update");
					String id = sc.next();
					System.out.println("Enter country_name for update");
					String cname = sc.next();
					f1.updateCountry(cname, id);
				}
			}
				break;
			case "e": {
				System.out.println("	Add additional special menu operations:\r\n"
						+ "1.	Show employees with highest salary\r\n" + "2.	Show employees with lowest salary\r\n"
						+ "3.	List employees who start with name 'A'\r\n"
						+ "4.	Show employees who are hired in 1987\r\n"
						+ "5.	List department which has highest no of employees\r\n"
						+ "6.	Show top 3 list of managers who has maximum no of employees under them\r\n"
						+ "7.	Show minimum salary for each department\r\n"
						+ "8.	Show maximum salary for each department\r\n"
						+ "9.	Show locations which has maximum departments\r\n"
						+ "10.	Show alternate employee names under department with highest no of employees\r\n"
						+ "11.	Show list of region with highest no of employees\r\n" + "");
				int o = sc.nextInt();
				switch (o) {
				case 1:
					f1.showEmpWithHightestSal();
					break;
				case 2:
					f1.showEmpWithLowestSal();
					break;
				case 3:
					f1.showEmpStartfirstnameWithA();
					break;
				case 4:
					f1.showEmpWhoHiredIn1987();
					break;
				case 5:
					f1.showDeptListWithHighEmployees();
					break;
				case 6:
					f1.showTop3ManagersHighEmployees();
					break;
				case 7:
					f1.showMiniSalForEachDept();
					break;
				case 8:
					f1.showMaxSalForEachDept();
					break;
				case 9:
					f1.showLocationMaxDept();
					break;
				case 10:
					break;
				case 11:
					f1.showRegionWithHighestNoOfEmp();
					break;

				}
			}
				break;
			default:
				System.out.println("Invalid entry");

			}
			System.out.println("************************************************************************");
			System.out.println("Do you want to go main press 'Y' else press 'N'");
			s = sc.next().charAt(0);
		} while (s == 'Y');
	}
}
