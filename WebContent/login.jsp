<script>// <![CDATA[
    (function() {
        var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
        po.src = 'https://apis.google.com/js/client:plusone.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    })();
// ]]>
</script>
<jsp:include page="header.jsp"></jsp:include>
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

