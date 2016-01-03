package com.book.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.ContentType;

@WebServlet("/StartMyPage")
public class HomeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("attribute1", "coffee");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
		requestDispatcher.forward(request, response);
		
		//resp.sendRedirect("home.jsp");

		//RequestDispatcher requestDispatcher = req.getRequestDispatcher("firstpage.jsp");
		//requestDispatcher.forward(req, resp);
		
		//resp.sendRedirect("home.jsp");

	}
	
}
