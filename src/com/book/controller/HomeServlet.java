
package com.book.controller;


import java.util.Properties;
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


public class HomeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*start code*/
		
		Subject subject = new Subject();
		List<Subject>subjectList = new ArrayList<Subject>();
		subjectList= subject.subList();
		req.setAttribute("subjectList", subjectList);
		RequestDispatcher rd =  req.getRequestDispatcher("test.jsp");
		rd.forward(req, resp);
		/*end code*/
				
		
	}		

	
}




