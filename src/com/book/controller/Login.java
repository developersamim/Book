package com.book.controller;
import java.io.BufferedReader;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.book.dao.User;
import com.book.service.DatabaseAccess;


public class Login extends HttpServlet{	
	
	private Connection conn;
	String databaseUsername = "";
    String databasePassword = "";
    String databaseFirstname = "";
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
		System.out.println("this is Login get method");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObjectUser = null;
		try {
			jsonObjectUser = (JSONObject) jsonParser.parse(sb.toString());
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		boolean authenticateBoolean = doAuthenticate(request, response, jsonObjectUser);	
		System.out.println("AuthenticateBoolean: "+ authenticateBoolean);
		if(authenticateBoolean){
			HttpSession session = request.getSession();
			User user = new User();
			user.setUsername(databaseUsername);
			user.setFirstname(databaseFirstname);
			session.setAttribute("user", user);
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.write("all done");
			out.flush();
			out.close();
		}else{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.write("wrong");
			out.flush();
			out.close();
		}
		
	}
	
	private boolean doAuthenticate(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObjectUser) throws IOException{
		
		
		String username = (String) jsonObjectUser.get("username");
		String password = (String) jsonObjectUser.get("password");
		
		
		
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
	
	public String getDecryptedPassword(byte[] encryptedText){
		
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
