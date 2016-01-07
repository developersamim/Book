package com.book.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.book.controller.Login;
import src.com.book.dao.User;

public class SendEmailOptusSmtp {
	
	private static final long serialVersionUID = 1L;
	
	private final String username = "developersamim@gmail.com";
	private final String password = "myvalentine143";
	private String to;
	private String from;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private User user;
	private String userPassword;

	public SendEmailOptusSmtp(String from, String to, User user, HttpServletRequest request,
			HttpServletResponse response) {
		this.from = from;
		this.to = to;
		this.request = request;
		this.response = response;
		this.user = user;
		this.userPassword = getDecryptedPassword(user.getPassword());
	}

	private String getDecryptedPassword(String userPassword) {
		Login login = new Login();
		return login.getDecryptedPassword(userPassword.getBytes());
	}

	public boolean sendEmail() {
		Properties props = new Properties();
		// for optus
		// props.put("mail.smtp.host", "mail.optusnet.com.au");

		// for tpg
		props.put("mail.smtp.host", "smtp.tpg.com.au");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, null);

		try {
			//userPassword = getDecryptedPassword(user.getPassword().getBytes());
			//System.out.println(userPassword);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Testing Subject");
			String someHtmlMessage = "<div style='border-color: #e0393d; border-radius: 3px; border-style: solid; border-width: 14px 4px 6px; color: #000; display: inline-block; font-weight: 700;'><a style='color:#000; font-size:14px; padding: 3px; text-decoration: none;' href='#'>exam note</a></div>"
					+ "<p>Please click on link for confirmation. <a href='" + getDomainUrl() + "?id="
					+ user.getLoginId() + "'>Click here</a></p>" 
					+ "<br/>Your username: " + user.getUsername()
					+ "<br/>Your password: "+userPassword;
			message.setContent(someHtmlMessage, "text/html; charset=utf-8");
			// message.setText("Dear Mail Crawler," + "\n\n No spam to my email,
			// please!");

			Transport.send(message);

			return true;

		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			return false;
			// throw new RuntimeException(e);
		}
	}

	public String getDomainUrl() {
		String uri = request.getScheme() + "://" + // "http" + "://
				request.getServerName() + // "myhost"
				":" + request.getServerPort() + // ":" + "8080"
				request.getRequestURI(); // "/Book"

		return uri;
	}

	
}
