package com.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static final ConnectionFactory connectionFactory=new ConnectionFactory();
	
	private Properties props=new Properties();
	
	private ConnectionFactory() {
		try {
			props.load(new FileReader("D:\\programs\\ProjectEcommerce\\src\\main\\resources\\db.properties"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static ConnectionFactory getconnectionFactory() {
		return connectionFactory;
	}
	public Connection getConnection(){
		try {
			Class.forName(props.getProperty("driver"));
			return DriverManager.getConnection(props.getProperty("url"),
					props.getProperty("username"),
					props.getProperty("password"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
