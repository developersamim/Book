package com.book.dao;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.swing.ImageIcon;

import com.book.service.DatabaseAccess;

import Query.DBQuery;

public  class Subject{
	
	
	private String subjectName="";
	private int rating;
	private String imgPath;
	private ImageIcon subjectImage;
	private String tag;
	List<Subject> subjectList;
	private static final int BUFFER_SIZE = 4096;
	
	
	
	public List<Subject> subList() throws IOException
	{
		Connection conn;
		subjectList = new ArrayList<Subject>();
		
		 String filePath = "E:/Photos/";
		
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
		    	subject.setTag(rs.getString(3));
		    	Blob blob = rs.getBlob(2); 	
		    	InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream(filePath+rs.getString(1)+".jpg");
                 int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                
		    	/*ImageIcon imageIcon = new ImageIcon(
		    			 blob.getBytes(1, (int)blob.length()));*/
 
                
                System.out.println("File saved");
		    		    	
		  // 	subject.setImgPath("/resources/image/defaultIcon.jpg");
		   	/*subject.setSubjectImage(imageIcon);
		    	subjectList.add(subject);*/
		    }
		    rs.close(); 
		    
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

	public ImageIcon getSubjectImage() {
		return subjectImage;
	}

	public void setSubjectImage(ImageIcon subjectImage) {
		this.subjectImage = subjectImage;
	}

	
	
	
	
	
	
}
