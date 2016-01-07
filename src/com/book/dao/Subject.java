package com.book.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.service.DatabaseAccess;

import Query.DBQuery;

/*import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

public  class Subject{
	
	
	private String subjectName="";
	private int rating;
	private String imgPath;
	private String tag;
	List<Subject> subjectList;
	
	
	
	public List<Subject> subList()
	{
		Connection conn;
		subjectList = new ArrayList<Subject>();
		/*
		Subject s1 = new Subject();
		s1.setImgPath("/resources/image/defaultIcon.jpg");
		s1.setSubjectName("java");
		subjectList.add(s1);
		Subject s2 = new Subject();
		s2.setImgPath("/resources/image/defaultIcon.jpg");
		s2.setSubjectName("php");
		subjectList.add(s2);
		Subject s3 = new Subject();
		s3.setImgPath("/resources/image/defaultIcon.jpg");
		s3.setSubjectName("angulr");
		subjectList.add(s3);
		setSubjectList(subjectList);
		
		return subjectList;*/
		
		DatabaseAccess databaseAccess = new DatabaseAccess("examnote", "root", "", 3306);
		conn = databaseAccess.getConnection();
		
		ResultSet rs;
		try {
			String query = DBQuery.strGetAllSubject;
		    rs = databaseAccess.selectQuery(conn, query);
		    
	        // Check Username and Password
		    while (rs.next()) {
		    	Subject subject = new Subject();
		    	subject.setSubjectName(rs.getString(1));
		    	subject.setTag(rs.getString(2));
		    	subject.setImgPath("/resources/image/defaultIcon.jpg");
		    	subjectList.add(subject);
		    }
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return subjectList;
		
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
	
}
