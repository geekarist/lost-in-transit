'use strict';

angular.module('webappApp').controller('MainCtrl', function($scope, $http) {

	$scope.awesomeThings = [ 'HTML5 Boilerplate', 'AngularJS', 'Karma' ];

	if ($scope.scores == undefined) {
		$scope.scores = [];
	}

	$scope.computeScore = function() {
		var places = JSON.stringify({
			from : $scope.from,
			to1 : $scope.to1,
			to2 : $scope.to2,
			to3 : $scope.to3,
			to4 : $scope.to4
		});

		var url = '../webresources/myresource';
		$http.post(url, places).success(function(score) {
			$scope.scores.push({
				home : $scope.home,
				work : $scope.work,
				score : score
			});
		});
	};

});
