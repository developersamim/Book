package com.book.controller;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Properties;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> origin/master

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import com.oracle.jrockit.jfr.ContentType;
=======
import com.book.dao.Subject;


public class HomeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		System.out.println("this is StartFirst Class");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
		requestDispatcher.forward(request, response);
		
		response.sendRedirect("home.jsp");


	}
	
}
