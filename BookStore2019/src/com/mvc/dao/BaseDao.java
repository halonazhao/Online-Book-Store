package com.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mvc.util.DbInfo;


public class BaseDao {
	public Connection connection;
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		try {
			this.openConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.connection;
	}
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	//jdbc:oracle:thin:@DESKTOP-VNKL3S2:1521:orcl
	public void openConnection() throws ClassNotFoundException,SQLException{
		try {
			if(connection == null || connection.isClosed()) {
				DbInfo dbinfo = DbInfo.getInstance();
				Class.forName(dbinfo.getDriver());
				connection = DriverManager.getConnection(dbinfo.getURL(), dbinfo.getUser(), dbinfo.getPassword());
				//System.out.println("User " + dbinfo.getUser() + " has been successfully connected");
				System.out.println("Connection is good!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.connection.setAutoCommit(false);
		
	}
	
	public void commit() throws Exception{
		try {
			this.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollback() throws Exception{
		if(this.connection != null) {
			this.connection.rollback();
		}
	}

}

