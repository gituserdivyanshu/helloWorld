package com.cg.empManage.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.empManage.entities.Employee;
import com.cg.empManage.exceptions.EmpException;
import com.cg.empManage.services.EmpServices;
import com.cg.empManage.services.EmpServicesImpl;



public class TestEmpServices {
	private static EmpServices services;
	
	@BeforeClass
	public static void init() throws EmpException{
		services = new EmpServicesImpl();
	}
	
	@Test
	public void testEmpDetails(){
		try {
			Employee empExpt = new Employee(7499, "Allen", 40000);
			Employee empActual = services.getEmpDetails(7499);
			
			if (empExpt.equals(empActual)){
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		} catch (EmpException e) {
			e.printStackTrace();
		}
	}
}
