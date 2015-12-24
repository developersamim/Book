package com.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StartMyPage")
public class HomeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("this is StartFirst Class");
		req.setAttribute("attribute1", "coffee");
<<<<<<< HEAD
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
		requestDispatcher.forward(req, resp);
		
		//resp.sendRedirect("home.jsp");
=======
		//RequestDispatcher requestDispatcher = req.getRequestDispatcher("firstpage.jsp");
		//requestDispatcher.forward(req, resp);
		
		resp.sendRedirect("home.jsp");
>>>>>>> origin/master
	}
	
}
