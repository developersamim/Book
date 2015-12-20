package com.book.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.User;
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
		
		boolean authenticateBoolean = authenticate(request, response);	
		if(authenticateBoolean){
			HttpSession session = request.getSession();
			User user = new User();
			user.setUsername(request.getParameter("username"));
			session.setAttribute("username", user.getUsername());
			response.sendRedirect("home.jsp");
		}else{
			PrintWriter printWriter = response.getWriter();
			printWriter.println("username or password incorrect");
			printWriter.close();
		}
		
	}
	
	private boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String databaseUsername = "";
	    String databasePassword = "";
	    String databaseFirstname = "";
		
		
		DatabaseAccess databaseAccess = new DatabaseAccess("examnote", "root", "", 3306);
		conn = databaseAccess.getConnection();
		
		ResultSet rs;
		try {
			String query = "SELECT * FROM user WHERE username='" + username +"'";
		    rs = databaseAccess.selectQuery(conn, query);
		    
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
		
		String decryptedPassword = getDecryptedPassword(databasePassword.getBytes());
	    

		if(username.equalsIgnoreCase(databaseUsername) && password.equalsIgnoreCase(decryptedPassword)){						
			return true;
		}else{
			
			return false;
		}

	}
	
	private String getDecryptedPassword(byte[] encryptedText){
		
		String decryptedText = null;
		String key = "Bar12345Bar12345"; // 128 bit key
		
		try{
			// Create key and cipher
			Cipher cipher = Cipher.getInstance("AES");
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			
			// decrypt the text
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decryptedText = new String(cipher.doFinal(encryptedText));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return decryptedText;
	}
}
