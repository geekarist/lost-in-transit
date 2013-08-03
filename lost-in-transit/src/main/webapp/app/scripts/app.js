'use strict';

angular.module('webappApp', ['ngCookies']).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/main.html',
		controller : 'MainCtrl'
	}).otherwise({
		redirectTo : '/'
	});
});

