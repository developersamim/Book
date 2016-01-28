// create angular app
var myApp = angular.module('myApp', ['angular-growl', 'ngAnimate', 'ngCookies']);

myApp.config(['growlProvider', function (growlProvider) {
	  growlProvider.globalTimeToLive(11000);
	}]);

var notification = true;

var rootUrl = "http://localhost:8080/Book/"
// create angular controller
myApp.controller('signupController', [ '$scope', '$http', '$location', '$window', '$rootScope', 'growl', '$cookieStore', function($scope, $http, $location, $window, $rootScope, growl, $cookieStore) {
	//alert('hasmi');
	var signupSuccess = $cookieStore.get('signup');
	var loginSuccess = $cookieStore.get('login');
    //console.log("My Favourite Outside: "+signupSuccess);
    var config = {};
    if(signupSuccess == 'success'){
    	growl.success("<b>You have successfully register.</b> We have send you a confirmation link in your mail", config);
    	$cookieStore.remove('signup');
    }
    if(loginSuccess == 'success'){
    	growl.success("<b>You have successfully logged in.</b>", config);
    	$cookieStore.remove('login');
    }
	
	$scope.createUser = function(valid) {
		$scope.submitted = true;

		
		
		var parent = $rootScope;
		
		
		
		// Simple GET request example:
		if(valid){
			$http({
				method : 'POST',
				url : 'signupServlet',
				data : 	$scope.user
			}).then(function successCallback(response) {
				if(response.data == 'all done'){
					// Put cookie
				    $cookieStore.put('signup','success');
				    //var favouriteCookie = $cookieStore.get('myFavorite');
				    //console.log("My Favourite: "+favouriteCookie);
					//console.log('all done');
					//$location.path("");
					$window.location.href = rootUrl;
					growl.success("<b>You have successfully register.</b> We have send you a confirmation link in your mail", config);
				}
				// this callback will be called asynchronously
				// when the response is available
			}, function errorCallback(response) {
				console.log('call failed');
				// called asynchronously if an error occurs
				// or server returns response with an error status.
			});
		}
		
	}
} ]);

myApp.controller('signinController', ['$scope', '$http', '$window', '$location', 'growl', '$cookieStore', function($scope, $http, $window, $location, growl, $cookieStore){
	
	var config = {};
	
	$scope.checkUser = function(valid){
		$scope.signinSubmitted = true;
		$scope.formErrorMessage = null;
		if(valid){
			$http({
				method : 'POST',
				url : 'loginServlet',
				data : $scope.user
			}).then(function successCallback(response){
				console.log(response.data);
				if(response.data == 'all done'){
					$cookieStore.put('login', 'success');
					$window.location.href = rootUrl;
					growl.success("<b>You have successfully logged in.</b>", config);
				}
				else if(response.data == 'wrong'){
					$scope.formErrorMessage = true;
				}
			}, function errorCallback(response){
				console.log('call failed');
			});
		}
	}
}]);

myApp.controller('googleSigninController', ['$scope', function($scope){
	// This flag we use to show or hide the button in our HTML.
    $scope.signedIn = false;
 
    // Here we do the authentication processing and error handling.
    // Note that authResult is a JSON object.
    $scope.processAuth = function(authResult) {
        // Do a check if authentication has been successful.
        if(authResult['access_token']) {
            // Successful sign in.
            $scope.signedIn = true;
            //     ...
            // Do some work [1].
            //     ...
        } else if(authResult['error']) {
            // Error while signing in.
            $scope.signedIn = false;
 
            // Report error.
        }
    };
 
    // When callback is received, we need to process authentication.
    $scope.signInCallback = function(authResult) {
        $scope.$apply(function() {
            $scope.processAuth(authResult);
        });
    };
 
    // Render the sign in button.
    $scope.renderSignInButton = function() {
        gapi.signin.render('signInButton',
            {
                'callback': $scope.signInCallback, // Function handling the callback.
                'clientid': '642272481305-eqeslfjmal3du4hr9hvlh7sj4cd7op2j.apps.googleusercontent.com', // CLIENT_ID from developer console which has been explained earlier.
                'requestvisibleactions': 'http://schemas.google.com/AddActivity', // Visible actions, scope and cookie policy wont be described now,
                                                                                  // as their explanation is available in Google+ API Documentation.
                'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email',
                'cookiepolicy': 'single_host_origin'
            }
        );
    }
 
    // Start function in this example only renders the sign in button.
    $scope.start = function() {
        $scope.renderSignInButton();
    };
 
    // Call start function on load.
    $scope.start();
    
// // Process user info.
// // userInfo is a JSON object.
// $scope.processUserInfo = function(userInfo) {
//     // You can check user info for domain.
//     if(userInfo['domain'] == 'http://localhost:8080/Book') {
//         // Hello colleague!
//    	 alert("hello");
//     }
//  
//     // Or use his email address to send e-mails to his primary e-mail address.
//     sendEMail(userInfo['emails'][0]['value']);
// }
}]);

