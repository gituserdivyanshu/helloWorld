package com.cg.empManage.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cg.empManage.entities.Employee;
import com.cg.empManage.exceptions.EmpException;

public interface EmpDao { // AutoCloseable{
	public ArrayList<Employee> getAllEmps() throws EmpException;
	public Employee getEmpDetails(int empNo) throws EmpException;
	public int addNewEmp(Employee emp) throws EmpException;
	public int releaseBonus(float amt)throws EmpException;
	/*public int removeEmp(int empNo);*/
}
