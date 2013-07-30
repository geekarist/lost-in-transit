'use strict';

angular.module('webappApp').controller('MainCtrl', function($scope, $http) {

	$scope.computeScore = function() {
		var places = JSON.stringify({
			from : $scope.from,
			to1 : $scope.to1,
			to2 : $scope.to2,
			to3 : $scope.to3,
			to4 : $scope.to4
		});

		var url = '../webresources/myresource';
		$http.post(url, places).success(function(result) {
			$scope.times = {
					to1 : result.to1,
					to2 : result.to2,
					to3 : result.to3,
					to4 : result.to4,
			};
		});
	};

});
