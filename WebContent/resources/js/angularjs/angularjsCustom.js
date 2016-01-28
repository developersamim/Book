// create angular app
var myApp = angular.module('myApp', []);

var rootUrl = "http://localhost:8080/Book/"
// create angular controller
myApp.controller('signupController', [ '$scope', '$http', '$location', '$window', function($scope, $http, $location, $window) {
	$scope.createUser = function(valid) {
		$scope.submitted = true;
		// Simple GET request example:
		if(valid){
			$http({
				method : 'POST',
				url : 'signupServlet',
				data : 	$scope.user
			}).then(function successCallback(response) {
				if(response.data == 'all done'){
					console.log('all done');
					//$location.path("");
					$window.location.href = rootUrl;
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
