package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.Key;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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

@WebServlet("/Signup")
public class Signup extends HttpServlet{
	
	String firstname, lastname, emailAddress, username, password, dateOfBirthString, createdUser, lastLogin, ipAddress;
	char status, sex;
	String inputDay, inputMonth, inputYear;
	User user;
	
	Connection conn;
		
	public Signup(){
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean signupSuccess = doProcess(request, response);	
		if(signupSuccess){
			user = getUserObject();
			setSession(user, request);
		}
	}
	
	private boolean doProcess(HttpServletRequest request, HttpServletResponse response){
		firstname = request.getParameter("firstname");
		lastname= request.getParameter("lastname");
		emailAddress = request.getParameter("emailAddress");
		sex = request.getParameter("sex").charAt(0);
		username = request.getParameter("username");
		password = request.getParameter("password");
		inputDay = request.getParameter("day");
		inputMonth = request.getParameter("month");
		inputYear = request.getParameter("year");
		
		
		
		dateOfBirthString = inputYear+"/"+inputMonth+"/"+inputDay;
				
		createdUser = getCurrentDate();
		lastLogin = getCurrentDate();
		status = 'A';
		ipAddress = getIPAddress();
		
		password = getEncryptedPassword(password);
		
		DatabaseAccess databaseAccess = new DatabaseAccess("examnote", "root", "", 3306);
		conn = databaseAccess.getConnection();
		String query = "insert into user(username, password, firstname, lastname, dateOfBirth, sex, emailAddress, createdUser, lastLogin, ipAddress, status) values('"+username+"', '"+ password+"', '"+firstname+"', '"+lastname+"', '"+dateOfBirthString+"', '"+sex+"', '"+emailAddress+"', '"+createdUser+"', '"+lastLogin+"', '"+ipAddress+"', '"+status+"')";
		boolean insertSuccess = databaseAccess.insertQuery(conn, query);
		
		if(insertSuccess){
			return true;
		}
		return false;	
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
	
	private String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateObject = new Date();
		return sdf.format(dateObject);
	}
	
	private String getIPAddress(){
		InetAddress IP = null;
		try{
			IP = InetAddress.getLocalHost();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return IP.getHostAddress();
	}
	private User getUserObject(){
		
		Date dateOfBirth = null;
		Date createdUserDate = null;
		Date lastLoginDate = null;
		String dateInString = inputDay+"/"+inputMonth+"/"+inputYear;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			dateOfBirth = formatter.parse(dateInString);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try{
			createdUserDate = formatter.parse(createdUser);
			lastLoginDate = formatter.parse(lastLogin);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setDateOfBirth(dateOfBirth);
		user.setCreatedUser(createdUserDate);
		user.setLastLogin(lastLoginDate);
		user.setStatus(status);
		user.setSex(sex);
		user.setEmailAddress(emailAddress);
		user.setIpAddress(ipAddress);
		return user;
	}
	private void setSession(User user, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}
}
