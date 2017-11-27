package com.cg.empManage.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cg.empManage.exceptions.EmpException;

/*
 * This class will supply different property values of the project.
 * 	Open a property file. (Done)
 * 	Read all properties. (Done)
 * 	Close property file. (Done with try-resources)
 * 	Give out a specific property value. (Done)
 * 	Exception handling: FileNotFoundException, IOException (Done with exception chaining.)
 *  For wrong property name: throwing custom exception. (Done)
 * 	Logger handling. (To do)
 */
public class ProjectProps {
	private Properties props;
	static Logger myLogger =  Logger.getLogger("JavaEmpCrudLayeredNew");
	
	public ProjectProps() throws EmpException{
		props = new Properties();
		try(
				InputStream is = new FileInputStream("Project.properties");
		) {
			props.load(is);
		} catch (FileNotFoundException e) {
			myLogger.fatal("Config file missing", e);
			throw new EmpException("Problem in execution. Contact administrator.");
		} catch (IOException e) {
			myLogger.fatal("Config file corrupt", e);
			throw new EmpException("Problem in execution. Contact administrator.");
		}
	}
	
	public String getPropertyValue(String propertyName) throws EmpException{
		if (!props.containsKey(propertyName)){
			throw new EmpException("Property name is wrong.");
		}
		
		return props.getProperty(propertyName);  // Returning actual property value.
	}
}
