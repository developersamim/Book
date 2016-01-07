<%@ page import="java.util.*"%>
<% int currentYear = Calendar.getInstance().get(Calendar.YEAR); %>

<jsp:include page="header.jsp"></jsp:include>
<section id="firstsection">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 signup">
				<form method="POST" action="Signup">
					<div class="labelinput">
						<div class="col-md-3 label">
							<label>Firstname</label>
						</div>
						<div class="col-md-9 input">
							<input type="text" name="firstname">
						</div>
					</div>
					<div class="labelinput">
						<div class="col-md-3 label">
							<label>Lastname</label>
						</div>
						<div class="col-md-9 input">
							<input type="text" name="lastname">
						</div>
					</div>
					<div class="labelinput">
						<div class="col-md-3 label">
							<label>Email</label>
						</div>
						<div class="col-md-9 input">
							<input type="text" name="emailAddress">
						</div>
					</div>
					<div class="labelinput">
						<div class="col-md-3 label">
							<label>Username</label>
						</div>
						<div class="col-md-9 input">
							<input type="text" name="username">
						</div>
					</div>
					<div class="labelinput">
						<div class="col-md-3 label">
							<label>Password</label>
						</div>
						<div class="col-md-9 input">
							<input type="password" name="password">
						</div>
					</div>
					<div class="labelinput">
						<div class="col-md-3 label">
							<label>BirthDay</label>
						</div>
						<div class="col-md-9 input birthday">
							<select name="day" class="birthday">
								<option value="">Day</option>
								<% for(int i = 1; i <= 31; i++){ %>
								<option value="<%= i %>"><%= i %></option>
								<% } %>
							</select> <select name="month" class="birthday">
								<option value="">Month</option>
								<option value="1">Jan</option>
								<option value="2">Feb</option>
								<option value="3">Mar</option>
								<option value="4">Apr</option>
								<option value="5">May</option>
								<option value="6">Jun</option>
								<option value="7">Jul</option>
								<option value="8">Aug</option>
								<option value="9">Sep</option>
								<option value="10">Oct</option>
								<option value="11">Nov</option>
								<option value="12">Dec</option>
							</select> <select name="year" class="birthday last">
								<option value="">Year</option>
								<% for(int j = currentYear; j >= 1905; j--){ %>
								<option value="<%= j %>"><%= j %></option>
								<% } %>
							</select>
						</div>
					</div>
					<div class="labelinput sex">
						<div class="col-md-3 label">
							<label>Sex</label>
						</div>
						<div class="col-md-9 input sex">
							<input type="radio" name="sex" value="F" id="female"
								class="normal" /> <label class="sex first" for="female">Female</label>
							<input type="radio" name="sex" value="M" id="male" class="normal" />
							<label class="sex" for="male">Male</label>
						</div>
					</div>
					<hr class="line" />
					<div class="labelinput">
						<div class="col-md-12 submitButton">
							<input class="submit" type="submit" value="Sign up" />
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
</section>
</body>
</html>