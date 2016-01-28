package com.book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.book.controller.Login;
import com.book.dao.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SendEmailMailgun {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private String userPassword;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public SendEmailMailgun(User user, HttpServletRequest request, HttpServletResponse response){
		this.user = user;
		this.userPassword = getDecryptedPassword(user.getPassword());
		this.request = request;
		this.response = response;
	}
	
	public ClientResponse SendSimpleMessage() {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-e60063add813967fc63901ba14fd73de"));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox2faee77caeff4a40917832fdd962ff20.mailgun.org" + "/messages");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", "Excited User <mailgun@YOUR_DOMAIN_NAME>");
		formData.add("to", user.getEmailAddress());
		formData.add("to", "admin@ExamNote");
		formData.add("subject", "Hello");
		formData.add("html", "<div style='border-color: #e0393d; border-radius: 3px; border-style: solid; border-width: 14px 4px 6px; color: #000; display: inline-block; font-weight: 700;'><a style='color:#000; font-size:14px; padding: 3px; text-decoration: none;' href='#'>exam note</a></div>"
					+ "<p>Please click on link for confirmation. <a href='" + getDomainUrl() + "?id="
					+ user.getLoginId() + "'>Click here</a></p>" 
					+ "<br/>Your username: " + user.getUsername()
					+ "<br/>Your password: "+userPassword);
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
	}
	public String getDomainUrl() {
		String uri = request.getScheme() + "://" + // "http" + "://
				request.getServerName() + // "myhost"
				":" + request.getServerPort() + // ":" + "8080"
				request.getRequestURI(); // "/Book"

		return uri;
	}
	private String getDecryptedPassword(String userPassword) {
		Login login = new Login();
		return login.getDecryptedPassword(userPassword.getBytes());
	}
}
