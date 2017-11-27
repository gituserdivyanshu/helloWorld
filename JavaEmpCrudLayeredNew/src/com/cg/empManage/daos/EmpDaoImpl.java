package com.cg.empManage.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.cg.empManage.entities.Employee;
import com.cg.empManage.exceptions.EmpException;
import com.cg.empManage.util.ProjectFactory;

/*
 * This class gets DataSource from ProjectFactory.
 * Each method gets Connection from data source and ensures connection gets closed within a method.
 * Ensure none of the methods to let SQL Exception to come out.  
 * 		Only custom exceptions like EmpException to come out.
 */
public class EmpDaoImpl implements EmpDao {
	private DataSource ds;
	static Logger myLogger =  Logger.getLogger("JavaEmpCrudLayeredNew");
	
	public EmpDaoImpl() throws EmpException {
		ProjectFactory factory = new ProjectFactory();
		ds = factory.getDataSource();
	}
	
	@Override
	public ArrayList<Employee> getAllEmps() throws EmpException{
		ArrayList<Employee> empList = new ArrayList<>();
		try (
				Connection connect = ds.getConnection();
				Statement stmt = connect.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT empno, ename, sal FROM app.emp");
			){
			
			// 5. Traverse result set
			while(rs.next()){
				int empNo = rs.getInt(1);  // 6. Convert data to Java format
				String empNm = rs.getString(2);
				float empSal = rs.getFloat(3);
				Employee emp = new Employee(empNo, empNm, empSal);
				empList.add(emp);
			}
			myLogger.info("Number of records procured: " + empList.size());
		} catch (SQLException e) {
			myLogger.error("Problem in getAllEmps()", e);
			throw new EmpException("Something wrong in execution. Contact admin.");
		} 
		return empList;
	}

	/*@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}*/

	/*@Override
	public void close() throws Exception {
		if (connect != null)
			connect.close();
	}*/

	@Override
	public Employee getEmpDetails(int empNo) throws EmpException{
		Employee emp = null;
		ResultSet rs = null;
		try (	
				Connection connect = ds.getConnection();
				PreparedStatement stmt = connect.prepareStatement("SELECT ename, sal FROM app.emp WHERE empno=?");
			){
			stmt.setInt(1, empNo);
			rs = stmt.executeQuery();
			
			// 5. Traverse result set
			if(rs.next()){
				String empNm = rs.getString("ename");
				float empSal = rs.getFloat("sal");
				emp = new Employee(empNo, empNm, empSal);
			}
			myLogger.info("Emp details procured: " + emp);
		} catch (SQLException e) {
			myLogger.error("Problem in getEmpDetails()", e);
			throw new EmpException("Something is wrong in execution");
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				throw new EmpException("Something is wrong in closing in getEmpDetails()", e);
			}
		}
		
		return emp;
	}

	
	@Override
	public int addNewEmp(Employee emp) throws EmpException {
		int countRecordsAffected = 0;
		try (
				Connection connect = ds.getConnection();
				PreparedStatement stmt = connect.prepareStatement("INSERT INTO app.emp (empNo, ename, sal) VALUES(next value for app.seq_emp_empNo1, ?, ?)");
			){
			//stmt.setInt(1, emp.getEmpNo());
			stmt.setString(1, emp.getEmpNm());
			stmt.setFloat(2, emp.getEmpSal());
			
			countRecordsAffected = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new EmpException("Something is wrong in addNewEmp()", e);
		} 
		
		return countRecordsAffected;
	} 

	@Override
	public int releaseBonus(float amt) throws EmpException {
		int countRecordsAffected = 0;
		try (	Connection connect = ds.getConnection();
				PreparedStatement stmt = connect.prepareStatement("UPDATE emp SET sal = sal + ?");
			){
			stmt.setFloat(1, amt);
			
			countRecordsAffected = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new EmpException("Something is wrong in releaseBonus()", e);
		} 
		
		return countRecordsAffected;
	}

	/*
	@Override
	public int removeEmp(int empNo) {
		int countRecordsAffected = 0;
		try (
				PreparedStatement stmt = connect.prepareStatement("DELETE FROM emp WHERE empno=?");
			){
			stmt.setInt(1, empNo);
			
			countRecordsAffected = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return countRecordsAffected;
	}
	*/
}
