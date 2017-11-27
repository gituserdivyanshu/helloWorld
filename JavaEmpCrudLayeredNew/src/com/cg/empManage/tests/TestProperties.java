package com.cg.empManage.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.empManage.exceptions.EmpException;
import com.cg.empManage.util.ProjectProps;

public class TestProperties {
	private static ProjectProps props;
	
	@BeforeClass
	public static void projInitialize() throws EmpException{
		props = new ProjectProps();
		System.out.println("In project Initialization()");
	}
	
	@AfterClass
	public static void projDispose() throws EmpException{
		props = new ProjectProps();
		System.out.println("In project Dispose()");
	}
	
	@Before  // This method gets executed before each test.
	public void testInitialize() throws EmpException{
		System.out.println("In initialization()");
	}
	
	@After  // This method gets executed before each test.
	public void testDispose() throws EmpException{
		
		System.out.println("In dispose()");
	}
	
	@Test
	public void testPropsForValue(){
		try {
			String expectedValue = "1527";
			String actualValue   = props.getPropertyValue("portNumber");
			
			Assert.assertTrue(expectedValue.equals(actualValue));
			System.out.println("In test1");
		} catch (EmpException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserName(){
		try {
			String expectedValue = "";
			String actualValue   = props.getPropertyValue("userName");
			
			Assert.assertTrue(expectedValue.equals(actualValue));
			System.out.println("In test2");
		} catch (EmpException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=EmpException.class)  // Test to check for exception.
	public void testWrongPropertyName() throws EmpException{
		String actualValue   = props.getPropertyValue("wrongProperty");
		System.out.println("In test3");
	}
}
