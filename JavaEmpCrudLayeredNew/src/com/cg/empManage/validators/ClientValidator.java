package com.cg.empManage.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator {
	// Validations for EmpNo.
	// 1. Min and Max. 4 characters.
	// 2. Characters must be all digits.(0 to 9).
	public boolean validateEmpNo(String strEmpNo){
		Pattern pattern = Pattern.compile("[0-9]{4}");
		Matcher match = pattern.matcher(strEmpNo);
		
		return match.matches();
	}
	
	// Validation of name
	// 1. All characters must be alphabets.
	// 2. First character must always be capital.
	// 3. Minimum 2 characters.
	public boolean validateName(String name){
		Pattern pattern = Pattern.compile("[A-Z][a-z]{1,29}");
		Matcher match = pattern.matcher(name);
		
		return match.matches();
	}
	
	// Validation of Salary
	// 1. All must be numerics with min and max size 5, 6
	public boolean validateSalary(String strSalary){
		Pattern pattern = Pattern.compile("[1-9][0-9]{4,5}");
		Matcher match = pattern.matcher(strSalary);
		
		return match.matches();
	}
	
	// Validation of email Id
	// 1. Email is to be divided in three parts.
	//		Ist part: Allowed characters A-Z, a-z, 0-9, _, .
	//		IInd part: @a-z
	// 		IIIrd part: ., com|org|in|gov|co.in
	public boolean validateEmailId(String strEmailId){
		Pattern pattern = Pattern.compile("[A-Za-z0-9_.]+@[a-z]+[.](com|org|in|gov|co.in)");
		Matcher match = pattern.matcher(strEmailId);
		
		return match.matches();
	}
	
	public static void main(String[] args) {
		ClientValidator validate = new ClientValidator();
		/*
		// Test Employee Number
		System.out.println("1: " + validate.validateEmpNo("1234"));
		System.out.println("2: " + validate.validateEmpNo("0001"));
		System.out.println("3: " + validate.validateEmpNo("001"));
		System.out.println("2: " + validate.validateEmpNo("o001"));
		System.out.println("2: " + validate.validateEmpNo("00012"));
		
		System.out.println("*************************");
		// Test Employee Name
		System.out.println("1: " + validate.validateName("Aaaaa"));  // true
		System.out.println("2: " + validate.validateName("aaaaa"));  // false
		System.out.println("3: " + validate.validateName("A"));      // false
		System.out.println("4: " + validate.validateName("Aa"));     // true
		System.out.println("5: " + validate.validateName("ABaaa"));  // false
		*/
		System.out.println("*************************");
		System.out.println("1: " + validate.validateEmailId("aa@bb.com"));  // true
		System.out.println("2: " + validate.validateEmailId("Aa@bb.com"));	// true
		System.out.println("3: " + validate.validateEmailId("@bb.com"));	// false
		System.out.println("4: " + validate.validateEmailId("Aa999@bb.com")); // true
		System.out.println("5: " + validate.validateEmailId("Aa bb.com"));	// false
		System.out.println("6: " + validate.validateEmailId("Aa@bb com"));	// false
		System.out.println("7: " + validate.validateEmailId("Aa@bb.cam"));	// false
		System.out.println("8: " + validate.validateEmailId("Aa@@bb.com"));	// false
		System.out.println("9: " + validate.validateEmailId("Aa@bb..com")); // false
		System.out.println("10: " + validate.validateEmailId("Aa@bb.co.in")); // true
	}
}
