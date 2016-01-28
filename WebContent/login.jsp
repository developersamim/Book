<script>// <![CDATA[
    (function() {
        var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
        po.src = 'https://apis.google.com/js/client:plusone.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    })();
// ]]>
</script>
<jsp:include page="header.jsp"></jsp:include>
<<<<<<< HEAD
<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Sign in to continue to
					Bootsnipp</h1>
				<div class="account-wall">
					<img class="profile-img"
						src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
						alt="">
					<form class="form-signin" action="Login" method="post">
						<input type="text" class="form-control" name="username"
							placeholder="Email" required autofocus> <input
							type="password" class="form-control" name="password"
							placeholder="Password" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
						<label class="checkbox pull-left"> <input type="checkbox"
							value="remember-me"> Remember me
						</label> <a href="#" class="pull-right need-help">Need help? </a><span
							class="clearfix"></span>
					</form>
				</div>
				<a href="signup.html" class="text-center new-account">Create an
					account </a>
			</div>
		</div>
	</div>
	<footer> </footer>
</section>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
=======
<section id="firstsection">
	<div class="container">
		<div class="row">
		
			<div class="col-md-6 col-md-offset-3 signup"
				ng-controller="signinController">
				
				<form name="signinForm" ng-submit="checkUser(signinForm.$valid)" novalidate>
					
					<p class="col-md-9 col-md-offset-3 formErrorMessage" ng-show="formErrorMessage != null">Invalid Username or Password</p>
					<div class="labelinput" ng-controller="googleSigninController">
						<div class="col-md-9 col-md-offset-3">
							<div id="googleButton" class="googleButton">
								<span id="googleSignIn">
								    <span id="signInButton">Continue with Google</span>
								</span>
							</div>
						</div>
					</div>
					<div class="labelinput" ng-class="{ 'has-error' : signinForm.username.$invalid && signinSubmitted }">
						<div class="col-md-3 label">
							<label>Username</label>
						</div>
						<div class="col-md-9 input">
							<input type="text" name="username" ng-model="user.username" ng-class="{'inputTextRequired' : signinForm.username.$invalid && signinSubmitted}"
								placeholder="Email" required autofocus>
							<div ng-messages="signupForm.firstname.$error">
								<p
									ng-show="signinForm.username.$error.required && signinSubmitted"
									class="help-block">Your user name is required.</p>

							</div>
						</div>
					</div>
					<div class="labelinput" ng-class="{ 'has-error' : signinForm.password.$invalid && signinSubmitted }">
						<div class="col-md-3 label">
							<label>Password</label>
						</div>
						<div class="col-md-9 input">
							<input type="password" name="password" ng-model="user.password" ng-class="{'inputTextRequired' : signinForm.password.$invalid && signinSubmitted}"
								placeholder="Password" required>
							<div ng-messages="signupForm.password.$error">
								<p
									ng-show="signinForm.password.$error.required && signinSubmitted"
									class="help-block">Your password is required.</p>

							</div>
						</div>
					</div>

					<hr class="line" />
					<div class="labelinput">
						<div class="col-md-12 submitButton">
							<button class="submit" type="submit" />
							Sign in
							</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>

</section>

<jsp:include page="footer.jsp"></jsp:include>
<<<<<<< HEAD
>>>>>>> refs/remotes/developersamim/master
=======

>>>>>>> refs/remotes/developersamim/master
