<jsp:include page="header.jsp"></jsp:include>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.book.dao.Subject" %>
<link href="${pageContext.request.contextPath}/resources/css/customStyle.css" rel="stylesheet" type="text/css"/>

<form id="searchBar" class ="well form-search">

	<input type="text" class="span3 search-querry" placeholder="Seach..."/>
	<button class ="btn">Search</button>

	<div class ="addSubject">
	<img src ="${pageContext.request.contextPath}/resources/image/addSubjectSymbol.jpg" alt="AddSubject" onclick="addSubject()"/>
	<a href="">Add Subject</a>
	</div>
</form>
<div >

<%List<Subject> subjectList = new ArrayList<Subject>(); %>
<%subjectList = (ArrayList<Subject>) request.getAttribute("subjectList"); %>
<table class ="table"><%
	
    for(int i=0; i<subjectList.size();i++)
    {%>
    
        <tr>
        	<td class="subject-space"><image src="${pageContext.request.contextPath}<%=subjectList.get(i).getImgPath()%>"/>
            <a href=""><%= subjectList.get(i).getSubjectName()%></a>
            </td>
	         	<td class="subject-column-space">Note 
	            Rating
	            </<td>        
        </tr>
       
<%}
%>
	
 </table>
 
 <div class ="hidden" id="addSubject">
	<br/>
		<form id= "addNewSubject" >
		<table class ="hidden" cols="2">
			<tr>
				<td>
					<label for="subName">Label for Subject</label>
				</td>
				<td>	
					<input  name="subName"type ="text" id="subName" placeholder="e.g Java"/>
					
				</td>
			</tr>
			
			</table>
		</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>