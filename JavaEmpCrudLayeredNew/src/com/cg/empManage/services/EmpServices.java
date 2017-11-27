package com.cg.empManage.services;

import java.util.ArrayList;

import com.cg.empManage.entities.Employee;
import com.cg.empManage.exceptions.EmpException;

public interface EmpServices {
	public ArrayList<Employee> getAllEmps() throws EmpException;
	public Employee getEmpDetails(int empNo) throws EmpException;
	public int addNewEmp(Employee emp) throws EmpException;
	public int releaseBonus(float amt) throws EmpException;
}
