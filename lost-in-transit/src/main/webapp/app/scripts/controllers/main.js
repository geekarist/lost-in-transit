'use strict';

angular.module('webappApp').controller('MainCtrl', function($scope, $http) {

	$scope.awesomeThings = [ 'HTML5 Boilerplate', 'AngularJS', 'Karma' ];

	if ($scope.scores == undefined) {
		$scope.scores = [];
	}

	$scope.computeScore = function() {
		$http.get('../webresources/myresource/' + $scope.home + '/' + $scope.work).success(function(score) {
			$scope.scores.push({
				home : $scope.home,
				work : $scope.work,
				score : score
			});
		});
	};

});
