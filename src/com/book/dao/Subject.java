package com.book.dao;

/*import java.io.IOException;*/
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/*import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

public  class Subject {
	
	private static final long serialVersionUID = 1L;
	private String subjectName="";
	private int rating;
	private String imgPath;
	
	List<Subject> subjectList;
	/*public Subject()
	{	
		
	
	}*/
	public List<Subject> subList()
	{
		
		
		
		subjectList = new ArrayList<Subject>();
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
		return subjectList;
	}
	/*@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		subjectList = new ArrayList<Subject>();
		Subject s1 = new Subject();
		s1.setSubjectName("java");
		subjectList.add(s1);
		Subject s2 = new Subject();
		s2.setSubjectName("php");
		subjectList.add(s2);
		Subject s3 = new Subject();
		s3.setSubjectName("angulr");
		subjectList.add(s3);
		
		req.setAttribute("subjectList", subjectList);
		RequestDispatcher rd =  req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
	}*/
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
	
	
	
	
}
