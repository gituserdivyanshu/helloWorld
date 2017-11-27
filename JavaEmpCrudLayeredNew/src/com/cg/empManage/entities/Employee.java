package com.cg.empManage.entities;

import java.io.Serializable;

/* 
 * The Comparable means for Natural comparison.  
 * The empNo is natural primary key here.
 * Marker Interfaces: These are empty interfaces which do not impose 
 * 	implementation of any method.
 * 	Serializable, Cloneable, Remote
 */
public class Employee { //implements Comparable<Employee>, Serializable {
	private int empNo;
	private String empNm;
	private float empSal;
	private String email;
	
	public Employee(int empNo, String empNm, float empSal, String email) {
		super();
		this.empNo = empNo;
		this.empNm = empNm;
		this.empSal = empSal;
		this.email = email;
	}

	public Employee(int empNo, String empNm, float empSal) {
		super();
		this.empNo = empNo;
		this.empNm = empNm;
		this.empSal = empSal;
	}
	
	public Employee(String empNm, float empSal, String email) {
		super();
		this.empNm = empNm;
		this.empSal = empSal;
		this.email = email;
	}
	
	public Employee() {
		super();
	}

	
	public int getEmpNo() {
		return empNo;
	}

	public String getEmail() {
		return email;
	}

	public String getEmpNm() {
		return empNm;
	}

	public float getEmpSal() {
		return empSal;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empNm=" + empNm + ", empSal=" + empSal + ", email=" + email + "]";
	}

	/*
	@Override
	public int compareTo(Employee arg0) {
		int empNo2 = arg0.getEmpNo();
		
		return (empNo<empNo2)?+1:(empNo>empNo2)?-1:0;
	}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
}
