package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.sql.Connection;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.DatabaseAccess;

@WebServlet("/Signup")
public class Signup extends HttpServlet{
	
	String username;
	String password;
	Connection conn;
		
	public Signup(){
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);		
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response){
		username = request.getParameter("username");
		password = request.getParameter("password");
		password = getEncryptedPassword(password);
		
		DatabaseAccess databaseAccess = new DatabaseAccess("examnote", "root", "", 3306);
		conn = databaseAccess.getConnection();
		String query = "insert into user(username, password) values('"+username+"', '"+ password+"')";
		boolean insertSuccess = databaseAccess.insertQuery(conn, query);
		
		try{
			PrintWriter out = response.getWriter();
			if(insertSuccess){
				out.println("<h1>insert success</h1>");
			}else{
				out.println("<h1>Some error occured</h1>");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	private String getEncryptedPassword(String textToEncrypt){ // 128 bit key
		
		String toReturn = null;
		byte[] encryptedText = null;
		String key = "Bar12345Bar12345"; // 128 bit key
        // Create key and cipher
        try{
        	Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encryptedText = cipher.doFinal(textToEncrypt.getBytes());
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        toReturn = new String(encryptedText);
        System.out.println(toReturn);
        return toReturn;
	}
}
