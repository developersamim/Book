<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.book.controller.Logout"%>
<%@page import="com.book.dao.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
//User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/customStyle.css"
	rel="stylesheet" type="text/css" />
<title>Exam Note</title>
</head>
<body>
	<div class="topbar">
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<div class="logo">
						<a href="home.jsp">exam note</a>
					</div>
				</div>
				<div class="col-md-4 col-md-offset-6">
					<ul>


						<% if(session.getAttribute("user") == null){ %>
							<li><a href="login.jsp" class="login">Log in</a></li>
							<li class="button"><a href="signup.jsp" class="signup">Sign up</a></li>
						<% } else{ User user = (User) session.getAttribute("user");%>
							<li>Hello <% out.print(user.getFirstname()); %> !</li>
							<li><a href="Logout" class="logout">LogOut</a>
						<% } %>
					</ul>								

				</div>
			</div>
		</div>
	</div>
	<!-- <header>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Exam Note</h1>
				</div>
			</div>
		</div>
	</header> -->