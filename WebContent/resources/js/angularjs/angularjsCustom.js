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

