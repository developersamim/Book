package com.book.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {
	
	private Connection connection;
	private String databaseName;
	private String username;
	private String password;
	private int port;
	private Statement stmt;
	
	public DatabaseAccess(String databaseName, String username, String password, int port){
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
		this.port = port;
		
		stmt = null;
	}
	
	public Connection getConnection(){
		// Load the database Driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		// Make a connection to the database
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+databaseName+"?user="+username+"&password="+password);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return connection;		
	}
	
	public ResultSet selectQuery (String query) throws SQLException{
		ResultSet rs = null;
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if (stmt != null) { 
				stmt.close(); 
			}
		}
		
		return rs;
		
	}
	
	

}
