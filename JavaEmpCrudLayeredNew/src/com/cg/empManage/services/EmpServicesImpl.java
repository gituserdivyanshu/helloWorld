package com.cg.empManage.services;

import java.util.ArrayList;

import com.cg.empManage.daos.EmpDao;
import com.cg.empManage.daos.EmpDaoImpl;
import com.cg.empManage.entities.Employee;
import com.cg.empManage.exceptions.EmpException;

public class EmpServicesImpl implements EmpServices{
	private EmpDao dao;
	
	public EmpServicesImpl() throws EmpException{
		dao = new EmpDaoImpl();
	}
	
	@Override
	public ArrayList<Employee> getAllEmps() throws EmpException {
		return dao.getAllEmps();
	}

	@Override
	public Employee getEmpDetails(int empNo) throws EmpException {
		return dao.getEmpDetails(empNo);
	}

	@Override
	public int releaseBonus(float amt) throws EmpException {
		return dao.releaseBonus(amt);
	}

	@Override
	public int addNewEmp(Employee emp) throws EmpException {
		return dao.addNewEmp(emp);
	}
}
