<%@ page language="java" contentType="text/html; charset=UTF-8"
<<<<<<< HEAD
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
=======
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%-- <%@ page import="java.io.File;" %> --%>
    <%@ page language="java" import="java.util.*,java.lang.*" %> 
>>>>>>> refs/remotes/developersamim/master
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script>
function openDialog()
{
document.getElementById("").click();
}
</script> -->
</head>
<<<<<<< HEAD
<body>
	Something to show:
	<h2>name</h2>
	<%String name=(String)request.getAttribute("files");%>
	<h1><%= name %></h1>
=======
<body> 

<h2>Notes</h2>
<%List files = (List)request.getAttribute("files");%>
<ol>
    <c:forEach items="${files}" var="file">
 	<c:set var="splittext" value="${fn:split(file,'/')}" />	
    <c:set var="lastString" value="${splittext[fn:length(splittext)-1]}" />
     <!--  <input type="file" id="file1" style="display:none"> -->
     <li> <a href="http://orderbook.comlu.com/book/${lastString }" ><c:out value="${lastString }"></c:out> </a></li>
      
      <br />
    </c:forEach> 
    </ol>
>>>>>>> refs/remotes/developersamim/master

</body>
</html>