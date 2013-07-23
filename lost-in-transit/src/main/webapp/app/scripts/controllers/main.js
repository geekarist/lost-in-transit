'use strict';

angular.module('webappApp').controller('MainCtrl', function($scope, $http) {

	$scope.awesomeThings = [ 'HTML5 Boilerplate', 'AngularJS', 'Karma' ];

	$scope.computeScore = function() {
		$http.get('../webresources/myresource/' + $scope.address).success(function(data) {
			$scope.score = data;
		});
	};

});
