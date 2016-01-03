<%@ page import="java.util.*" %>
<% int currentYear = Calendar.getInstance().get(Calendar.YEAR); %>

<jsp:include page="header.jsp"></jsp:include>
	<section id="firstsection">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 signup" ng-controller="signupController">
					<form name="signupForm" ng-submit="createUser(signupForm.$valid)" novalidate>
						<div class="labelinput" ng-class="{ 'has-error' : signupForm.firstname.$invalid && submitted }">
							<div class="col-md-3 label">
								<label>Firstname</label>
							</div>
							<div class="col-md-9 input">
								<input type="text" name="firstname" ng-class="{'inputTextRequired' : signupForm.firstname.$invalid && submitted}" ng-maxlength="30" ng-minlength="2" ng-model="user.firstname" required>
								<div ng-messages="signupForm.firstname.$error">
									<p ng-show="signupForm.firstname.$error.required && submitted" class="help-block">Your first name is required.</p>
									<p ng-show="signupForm.firstname.$error.maxlength && submitted" class="help-block">Your first name must be less than 30 characters.</p>
									<p ng-show="signupForm.firstname.$error.minlength && submitted" class="help-block">Your first name must be greater than 2 characters.</p>
								</div>
								
							</div>
						</div>
						<div class="labelinput" ng-class="{ 'has-error' : signupForm.lastname.$invalid && submitted }">
							<div class="col-md-3 label">
								<label>Lastname</label>
							</div>
							<div class="col-md-9 input">
								<input type="text" name="lastname" ng-model="user.lastname" ng-maxlength="30" ng-minlength="2" ng-class="{'inputTextRequired' : signupForm.lastname.$invalid && submitted}" required>
								<div ng-messages="signupForm.lastname.$error">
									<p ng-show="signupForm.lastname.$error.required && submitted" class="help-block">Your last name is required.</p>
									<p ng-show="signupForm.lastname.$error.maxlength && submitted" class="help-block">Your last name must be less than 30 characters.</p>
									<p ng-show="signupForm.lastname.$error.minlength && submitted" class="help-block">Your last name must be greater than 2 characters.</p>
								</div>
								
							</div>
						</div>
						<div class="labelinput" ng-class="{ 'has-error' : signupForm.emailAddress.$invalid && submitted }">
							<div class="col-md-3 label">
								<label>Email</label>
							</div>
							<div class="col-md-9 input">
								<input type="email" name="emailAddress" ng-model="user.emailAddress" ng-class="{'inputTextRequired' : signupForm.emailAddress.$invalid && submitted}" required>
								<div ng-messages="signupForm.emailAddress.$error" role="alert">
									<p ng-show="signupForm.emailAddress.$error.required && submitted" class="help-block">Your email address is required.</p>
									<p ng-show="signupForm.emailAddress.$error.email && submitted" class="help-block">Not valid email address.</p>
								</div>
								
								
							</div>
						</div>
						<div class="labelinput" ng-class="{ 'has-error' : signupForm.password.$invalid && submitted }">
							<div class="col-md-3 label">
								<label>Password</label>
							</div>
							<div class="col-md-9 input" role="alert">
								<input type="password" name="password" ng-model="user.password" ng-class="{'inputTextRequired' : signupForm.password.$invalid && submitted}" required ng-maxlength="56" ng-minlength="5">
								<div ng-messages="signupForm.password.$error">
									<p ng-show="signupForm.password.$error.required && submitted" class="help-block">Your password is required.</p>
									<p ng-show="signupForm.password.$error.maxlength && submitted" class="help-block">Your password must be less than 56 characters.</p>
									<p ng-show="signupForm.password.$error.minlength && submitted" class="help-block">Your password must be greater than 5 characters.</p>
								</div>
								
							</div>
						</div>
					
						<hr class="line"/>
						<div class="labelinput">
							<div class="col-md-12 submitButton">
								<button class="submit" type="submit"/>Sign up</button>
							</div>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</section>
<jsp:include page="footer.jsp"></jsp:include>