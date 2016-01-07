<jsp:include page="header.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/resources/css/customStyle.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
	<section id="firstsection">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 signup">
					<form method="POST" action="Signup">
						<div class="labelinput">
							<div class="col-md-3 label">
								<label>Subject</label>
							</div>
							<div class="col-md-9 input">
								<input type="text" name="subjectName">
							</div>
						</div>
												
						<div class="labelinput">
							<div class="col-md-3 label">
								<label>Select an image</label>
							</div>
							<div class="col-md-9 input">
								<input class="selectImage" type="file" name="subImage" title=" ">
							</div>
						</div>
						<div class="labelinput">
							<div class="col-md-3 label">
								<label>Add a Tag</label>
							</div>
							<div class="col-md-9 input">
								<input type="text" name="subjectTag">
							</div>
						</div>
						
								
						<hr class="line"/>
						<div class="labelinput">
							<div class="col-md-12 submitButton">
								<input class="submit" type="submit" value="Add"/>
							</div>
						</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
						
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>