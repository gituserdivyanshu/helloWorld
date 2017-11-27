package com.cg.empManage.util;

import javax.sql.DataSource;

import org.apache.derby.jdbc.ClientDataSource;
import org.apache.log4j.Logger;

import com.cg.empManage.exceptions.EmpException;

public class ProjectFactory {
	private ProjectProps props;
	private ClientDataSource ds;
	
	static Logger myLogger =  Logger.getLogger("JavaEmpCrudLayeredNew");
	
	public ProjectFactory() throws EmpException{
		props = new ProjectProps();
		
		try {
			ds = new ClientDataSource();
			ds.setDatabaseName(props.getPropertyValue("dataBaseName"));
			ds.setServerName(props.getPropertyValue("serverName"));
			ds.setPortNumber(Integer.parseInt(props.getPropertyValue("portNumber")));
		} catch (NumberFormatException e) {
			myLogger.fatal("Config file with wrong port number", e);
			throw new EmpException("Problem in execution. Contact administrator.");
		} catch (Exception e){
			myLogger.fatal("Wrong Datasource configuration.", e);
			throw new EmpException("Problem in execution. Contact administrator.");
		}
	}
	
	public DataSource getDataSource(){
		return ds;
	}
}
