package com.example.myapp.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.myapp.hr.model.Emp;

public class EmpDao {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
 
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr"; // 데이터베이스 사용자 아이디
	String password = "hr"; // 데이터베이스 사용자 비밀번호
 
	public int getEmpCount() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT COUNT(*) FROM employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) { }
		}
		return 100;
	}

	public int getEmpCount(int deptid) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT COUNT(*) FROM employees WHERE department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, deptid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) { }
		}
		return 0;
	}
	
	public List<Emp> getEmpList() {
		Connection con = null;
		List<Emp> empList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				empList.add(emp);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) { }
		}
		return empList;
	}

	public int deleteJobHistory(int empid) {
		Connection con = null;
		int rowCount = 0;
		try {
			con = DriverManager.getConnection(url, username, password);
			String sql = "DELETE FROM job_history WHERE employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empid);
			rowCount = stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) { }
		}
		return rowCount;
	}
}