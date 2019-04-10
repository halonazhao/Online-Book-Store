package com.mvc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class DbInfo {
	private ConfigInfo config;
	private static DbInfo dbInfo;
	
	private DbInfo() {
		config = new ConfigInfo();
		getProperties();
	}
	
	public static DbInfo getInstance() {
		if(dbInfo == null) {
			dbInfo = new DbInfo();
		}
		return dbInfo;
	}
	public String getDriver() {
		String driver = config.getDriver();
		return driver;
	}
	public String getPassword() {
		String pwd = config.getPassword();
		return pwd;
	}
	public String getUser() {
		String user = config.getUser();
		return user;
	}
	public String getURL() {
		String url = config.getURL();
		return url;
	}
	private void getProperties() {
		FileInputStream fil = null;
		//DbInfo.class.getResource("/").toURI().getPath()+"db.properties";
		String path;
		try {
			path = DbInfo.class.getResource("/").toURI().getPath()+"db.properties";
			File file = new File(path);
			Properties properties = new Properties();
			fil = new FileInputStream(file);
			properties.load(fil);
			config.setDriver(properties.getProperty("dbdriver"));
			config.setPassword(properties.getProperty("pwd"));
			config.setURL(properties.getProperty("dbURL"));
			config.setUser(properties.getProperty("uname"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally{
			if(fil != null) {
				try {
					fil.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	
	
	
//From here and below are private information	
	
	private class ConfigInfo{
		private String driver;
		private String URL;
		private String user;
		private String password;
		public String getDriver() {
			return driver;
		}
		public void setDriver(String driver) {
			this.driver = driver;
		}
		public String getURL() {
			return URL;
		}
		public void setURL(String uRL) {
			URL = uRL;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}


}
