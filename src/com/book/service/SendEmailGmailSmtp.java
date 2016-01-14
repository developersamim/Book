package com.book.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.controller.Login;
import com.book.dao.User;

public class SendEmailGmailSmtp {
	
	private static final long serialVersionUID = 1L;
	
	private final String username = "";
    private final String password = "";
    private String to;
	private String from;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private User user;
	private String userPassword;
    
    public SendEmailGmailSmtp(String from, String to, User user, HttpServletRequest request,
			HttpServletResponse response){
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

	public boolean sendEmail(){		

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                + "\n\n No spam to my email, please!");

            Transport.send(message);

            return true;

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            return false;
        	//throw new RuntimeException(e);
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
