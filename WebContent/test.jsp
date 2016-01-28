<jsp:include page="header.jsp"></jsp:include>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.book.dao.Subject"%>
<%List<Subject> subjectList = new ArrayList<Subject>(); %>
<%subjectList = (ArrayList<Subject>) request.getAttribute("subjectList"); %>
<form id="searchBar" class="well form-search">

	<input type="text" class="span3 search-querry" placeholder="Seach..." />
	<button class="btn">Search</button>

	<div class="addSubject">
		<img
			src="${pageContext.request.contextPath}/resources/image/addSubjectSymbol.jpg"
			alt="AddSubject" onclick="addSubjectPage()" /> <a
			href="addSubject.jsp">Add Subject</a>
	</div>
	
		
</form>
<section id="home_subjectList_section" class="subjectList">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h2 class="section__title">Course List</h2>
			</div>
		</div>
		<div class="row">



			<div class="col-md-12">

				
				 <%
	
    for(int i=0; i<subjectList.size();i++)
    	
    {
    %>
		<div class=" col-md-3 card effect__hover">
		<div class="card__front parentDiv">
						<div class="text">
						<%= subjectList.get(i).getSubjectName()%>
						</div>
						<div class="text1">
							<%-- <image alt="subjectImage" src="<%=subjectList.get(i).getSubjectImage()%>" height="50" width="30" /> --%> 
						</div>
					</div>
					<div class="card__back parentDivBack">
						<div class="text">back</div>
						<div class="text1"><a href ="">Notes:15</a></div>
					</div>
					</div>
				<%}
					
%>
				
					
				
			</div>
		</div>
		
	</div>

</section>