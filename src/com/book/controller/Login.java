package com.book.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.DatabaseAccess;

@WebServlet("/Login")
public class Login extends HttpServlet{	
	
	private Connection conn;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Login(){
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String databaseUsername = "";
	    String databasePassword = "";
	    String databaseFirstname = "";
		PrintWriter printWriter = response.getWriter();
		
		DatabaseAccess databaseAccess = new DatabaseAccess("examnote", "root", "", 3306);

		ResultSet rs;
		try {
			String query = "SELECT * FROM user WHERE username='" + username + "' && password='" + password+ "'";
		    rs = databaseAccess.selectQuery(query);
		    
	        // Check Username and Password
		    while (rs.next()) {
		        databaseUsername = rs.getString("username");
		        databasePassword = rs.getString("password");
		        databaseFirstname = rs.getString("firstname");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

		if(username.equalsIgnoreCase(databaseUsername) && password.equalsIgnoreCase(databasePassword)){			
			printWriter.println("Hello "+ databaseFirstname+"! login successfull");
		}else{
			printWriter.println("username or password incorrect");
		}
		
	}
//	public Connection getConnection(){
//        try{
//            // Load the database driver
//            Class.forName("com.mysql.jdbc.Driver");
//        }catch(ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        
//        try{
//            // Get a connection to the database
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examnote?user=root&password=");
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return conn;
//    }
}
