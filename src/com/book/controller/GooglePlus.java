package com.book.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GooglePlus extends HttpServlet {
	
	public static final String CLIENT_ID = "642272481305-jltf25s5gpbgse56j99f8i9m0lb0ruvs.apps.googleusercontent.com";
	public static final String CLIENT_SECRET = "bqvgqPBqHF93320-3Sb-KPch";
	
	public GooglePlus(){
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
