package com.book.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.Key;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.book.dao.User;
import com.book.service.DatabaseAccess;
import com.book.service.SendEmailGmailSmtp;
import com.book.service.SendEmailMailgun;
import com.book.service.SendEmailOptusSmtp;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Signup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	String firstname, lastname, emailAddress, password, createdUser, lastLogin, ipAddress;
	char status;
	User user;

	Connection conn;
	DatabaseAccess databaseAccess;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		signupConfirmation(request, response);
	}

	private void signupConfirmation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int loginId = Integer.parseInt(request.getParameter("id"));
		String query = "update user set status='C' where id=" + loginId;
		boolean updateSuccess = databaseAccess.updateQuery(conn, query);
		if (updateSuccess) {
			setSession(user, request);
			response.sendRedirect("home.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("code is here");

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

		int signupSuccess = doProcess(request, response, jsonObjectUser);
		if (signupSuccess != 0) {

			user = getUserObject(signupSuccess);
			setSession(user, request);
			
			// send email from mailgun
			//SendEmailMailgun sendEmailMailgun = new SendEmailMailgun(user, request, response);
			//sendEmailMailgun.SendSimpleMessage();

			// send email to the new user for confirmation
			//SendEmailGmailSmtp sendEmailOptusSmtp = new SendEmailGmailSmtp("developersamim@gmail.com", "hasmi2006@gmail.com", user, request, response);
			//sendEmailOptusSmtp.sendEmail();

			// send email to the new user for confirmation gmail only
			// SendEmailGmailSmtp sendEmailGmailSmtp = new
			// SendEmailGmailSmtp("developersamim@gmail.com",
			// "hasmi2006@gmail.com", user, request, response);
			// sendEmailGmailSmtp.sendEmail();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.write("all done");
			out.flush();
			out.close();
			// RequestDispatcher requestDispatcher =
			// request.getRequestDispatcher("home.jsp");
			// requestDispatcher.forward(request, response);
			// return;
		}
	}

	private int doProcess(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObjectUser) {
		// firstname = request.getParameter("firstname");
		// lastname= request.getParameter("lastname");
		// emailAddress = request.getParameter("emailAddress");
		// password = request.getParameter("password");
		firstname = (String) jsonObjectUser.get("firstname");
		lastname = (String) jsonObjectUser.get("lastname");
		emailAddress = (String) jsonObjectUser.get("emailAddress");
		password = (String) jsonObjectUser.get("password");

		createdUser = getCurrentDate();
		lastLogin = getCurrentDate();
		status = 'N';
		ipAddress = getIPAddress();

		password = getEncryptedPassword(password);

		databaseAccess = new DatabaseAccess("examnote", "root", "", 3306);
		conn = databaseAccess.getConnection();
		String query = "insert into user(username, password, firstname, lastname, emailAddress, createdUser, lastLogin, ipAddress, status) values('"
				+ emailAddress + "', '" + password + "', '" + firstname + "', '" + lastname + "', '" + emailAddress
				+ "', '" + createdUser + "', '" + lastLogin + "', '" + ipAddress + "', '" + status + "')";
		int insertSuccess = databaseAccess.insertQueryReturnId(conn, query);
		return insertSuccess;
	}

	private String getEncryptedPassword(String textToEncrypt) { // 128 bit key

		String toReturn = null;
		byte[] encryptedText = null;
		String key = "Bar12345Bar12345"; // 128 bit key
		// Create key and cipher
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encryptedText = cipher.doFinal(textToEncrypt.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		toReturn = new String(encryptedText);
		System.out.println(toReturn);
		return toReturn;
	}

	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateObject = new Date();
		return sdf.format(dateObject);
	}

	private String getIPAddress() {
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return IP.getHostAddress();
	}

	private User getUserObject(int loginId) {

		Date createdUserDate = null;
		Date lastLoginDate = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			createdUserDate = formatter.parse(createdUser);
			lastLoginDate = formatter.parse(lastLogin);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		User user = new User();
		user.setLoginId(loginId);
		user.setUsername(emailAddress);
		user.setPassword(password);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setCreatedUser(createdUserDate);
		user.setLastLogin(lastLoginDate);
		user.setStatus(status);
		user.setEmailAddress(emailAddress);
		user.setIpAddress(ipAddress);
		return user;
	}

	private void setSession(User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}	

}
