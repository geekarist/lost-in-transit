'use strict';

angular.module('webappApp').controller('MainCtrl', function($scope, $http) {

	$scope.awesomeThings = [ 'HTML5 Boilerplate', 'AngularJS', 'Karma' ];

	if ($scope.scores == undefined) {
		$scope.scores = [];
	}

	$scope.computeScore = function() {
		$http.get('../webresources/myresource/' + $scope.address).success(function(data) {
			$scope.scores.push({
				address : $scope.address,
				score : data
			});
		});
	};

});
