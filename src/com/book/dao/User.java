package com.book.dao;
import java.util.Date;

public class User {
	
	private int loginId;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private Date dateOfBirth;
	private char status;
	private Date createdUser;
	private Date lastLogin;
	private String emailAddress;
	private String ipAddress;
	private char sex;
	
	public User(){
		
	}
	
	public void setLoginId(int loginId){
		this.loginId = loginId;
	}
	public int getLoginId(){
		return loginId;
	}
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	public String getFirstname(){
		return firstname;
	}
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfBirth(){
		return dateOfBirth;
	}
	public void setStatus(char status){
		this.status = status;
	}
	public char getStatus(){
		return status;
	}
	public void setCreatedUser(Date createdUser){
		this.createdUser = createdUser;
	}
	public Date getCreatedUser(){
		return createdUser;
	}
	public void setLastLogin(Date lastLogin){
		this.lastLogin = lastLogin;
	}
	public Date getLastLogin(){
		return lastLogin;
	}
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress(){
		return emailAddress;
	}
	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}
	public String getIpAddress(){
		return ipAddress;
	}
	
	public void setSex(char sex){
		this.sex = sex;
	}
	public char getSex(){
		return sex;
	}

}
