package com.book.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public boolean updateQuery(Connection conn, String query){
		int rowAffected = 0;
		boolean updateSuccess;
		try{
			stmt = conn.createStatement();
			rowAffected = stmt.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		updateSuccess = (rowAffected != 0) ? true : false;
		return updateSuccess;
	}
	
	public int insertQuery(Connection conn, String query){
		int insertSuccess = 0;
		try{
			stmt = conn.createStatement();
			
			insertSuccess = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			//ResultSet rs = stmt.getGeneratedKeys();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return insertSuccess;
	}
	
	
	public ResultSet selectQuery (Connection conn, String query) throws SQLException{
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (!rs.isBeforeFirst() ) {    
				 System.out.println("No data"); 
				} 
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if (stmt != null) { 
				//stmt.close(); 
			}
		}
		
		return rs;
		
	}

	public int insertQueryReturnId(Connection conn, String query){
		int last_inserted_id = 0;
		try{
			
			PreparedStatement prest;
            prest = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prest.executeUpdate();
            ResultSet rs = prest.getGeneratedKeys();
            if(rs.next())
            {
                last_inserted_id = rs.getInt(1);
            }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return last_inserted_id;
	}

}
