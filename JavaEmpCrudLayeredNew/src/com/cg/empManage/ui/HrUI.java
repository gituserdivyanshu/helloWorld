package com.cg.empManage.ui;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.empManage.validators.ClientValidator;

import com.cg.empManage.entities.Employee;
import com.cg.empManage.exceptions.EmpException;
import com.cg.empManage.services.EmpServices;
import com.cg.empManage.services.EmpServicesImpl;

public class HrUI {
	static Logger myLogger =  Logger.getLogger("JavaEmpCrudLayeredNew");
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		try {
		Scanner kbdInput = new Scanner(System.in);
		EmpServices services = new EmpServicesImpl();
		
		// Create a validator
		ClientValidator validator = new ClientValidator();
		
		System.out.println("Capgemini India Pvt. Ltd.");
		System.out.println("Main Menu");
		
		System.out.println("\n\n");
		//boolean toContinue = true;
		
		repeatIt:
		while(true) {
			System.out.println("1. List all employees.");
			System.out.println("2. See details of given employee.");
			System.out.println("3. Insert new employee.");
			System.out.println("4. Release festival bonus.");
			System.out.println("9. Exit");
			
			System.out.println("Enter your choice: ");
			int ch = kbdInput.nextInt();
			myLogger.info("Main menu choice : " + ch);
			
			if ((ch<1)||(ch>4)){
				if (ch != 9) {
					System.out.println("Invalid choice.  Enter again...");
					continue;
				}
			}
			
			
				switch(ch){
				case 1 : // List all employees.
					System.out.println("List all employees."); 
					
					ArrayList<Employee> empList = services.getAllEmps();
					System.out.println(empList);
					
					break;
				case 2 : // See details of given employee.
					System.out.println("View employee details."); 
					System.out.print("Enter employee number: ");
					int empNo1 = kbdInput.nextInt();
					Employee emp1 = services.getEmpDetails(empNo1);
					
					System.out.println(emp1);
					
					break;
				case 3 : // Insert new employee.
					System.out.println("Insert new employee."); 
					
					/*// Validate Employee Number and ask again for invalid input.
					String strEmpNo = null;
					while (true) {
						System.out.print("Enter employee number: ");
						strEmpNo = kbdInput.next();
						boolean isEmpNoValid = validator.validateEmpNo(strEmpNo);
						if (!isEmpNoValid) {
							System.out.println("Wrong employee number.  Pls enter again.");
							continue;
						} else {
							break;
						}
					}
					int empNo = Integer.parseInt(strEmpNo);
							*/
					// Validate Employee Name and ask again for invalid input.
					String empNm=null;
					while (true) {
						System.out.print("Enter name: ");
						empNm = kbdInput.next();
						boolean isEmpNameValid = validator.validateName(empNm);
						if (!isEmpNameValid) {
							System.out.println("Wrong employee name.  Pls enter again.");
							continue;
						} else {
							break;
						}
					}
					
					String strEmpSal;
					while (true) {
						// Validate Employee Salary and ask again for invalid input.
						System.out.print("Enter salary: ");
						strEmpSal = kbdInput.next();
						
						boolean isEmpSalValid = validator.validateSalary(strEmpSal);
						if (!isEmpSalValid) {
							System.out.println("Wrong employee salary.  Pls enter again.");
							continue;
						} else {
							break;
						}
					}
					
					float empSal = Float.parseFloat(strEmpSal);
					
					
					// Validate Employee email Id and ask again for invalid input.
					String empEmail=null;
					while (true) {
						System.out.print("Enter Email: ");
						empEmail = kbdInput.next();
						boolean isEmpEmailValid = validator.validateEmailId(empEmail);
						if (!isEmpEmailValid) {
							System.out.println("Wrong employee emaiId.  Pls enter again.");
							continue;
						} else {
							break;
						}
					}
					
					Employee emp = new Employee(empNm, empSal, empEmail);
					
					int recInserted = services.addNewEmp(emp);
					
					if (recInserted>=1){
						System.out.println("Employee inserted successfully.");
					} else {
						System.out.println("Employee insertion failed.");
					}
					
					break;
				case 4:  // Distribute festival bonus to all employees.
					System.out.println("Festival bonus.");
					
					System.out.print("Enter bonus per employee to distribute: ");
					float amt = kbdInput.nextFloat();
					int countBonusDistributed = services.releaseBonus(amt);
					
					System.out.println("Total bonus distributed " + countBonusDistributed * amt);
					break;
					
				case 9 : // Exit.
					System.out.println("In case 9.");
					
					break repeatIt;
				}
			}// End of while
		}catch (EmpException e) {
				e.printStackTrace();
			}  
	}	// End of main
}
