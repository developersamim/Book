package com.book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.Subject;

@WebServlet("/StartMyPage")
public class HomeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*System.out.println("this is StartFirst Class");
		req.setAttribute("attribute1", "coffee");*/
		
		Subject subject = new Subject();
		List<Subject>subjectList = new ArrayList<Subject>();
		subjectList= subject.subList();
		
		req.setAttribute("subjectList", subjectList);
		RequestDispatcher rd =  req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
		//RequestDispatcher requestDispatcher = req.getRequestDispatcher("firstpage.jsp");
		//requestDispatcher.forward(req, resp);
		
		//resp.sendRedirect("home.jsp");

		System.out.println("this is StartFirst Class");
		req.setAttribute("attribute1", "coffee");

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
		requestDispatcher.forward(req, resp);
		
		//resp.sendRedirect("home.jsp");

		//RequestDispatcher requestDispatcher = req.getRequestDispatcher("firstpage.jsp");
		//requestDispatcher.forward(req, resp);
		
<<<<<<< HEAD
		resp.sendRedirect("home.jsp");
=======
		//resp.sendRedirect("home.jsp");
>>>>>>> refs/remotes/developersamim/master

	}
	
}
