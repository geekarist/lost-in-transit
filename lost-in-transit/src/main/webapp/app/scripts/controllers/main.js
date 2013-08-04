'use strict';

angular.module('webappApp').controller('MainCtrl', function($scope, $http, $cookieStore) {
	
	$scope.restoreState = function() {
		var state = $cookieStore.get('state') || {};
		$scope.from = state.from;
		$scope.to1 = state.to1;
		$scope.to2 = state.to2;
		$scope.to3 = state.to3;
		$scope.to4 = state.to4;
	};

	$scope.computeScore = function() {
		$scope.waiting = true;
		
		var places = {
			from : $scope.from,
			to1 : $scope.to1,
			to2 : $scope.to2,
			to3 : $scope.to3,
			to4 : $scope.to4
		};

		$cookieStore.put('state', places);

		var url = '../webresources/myresource';
		$http.post(url, JSON.stringify(places)).success(function(result) {
			$scope.times = {
				to1 : result.to1,
				to2 : result.to2,
				to3 : result.to3,
				to4 : result.to4,
			};
			$scope.waiting = false;
		}).error(function() {
			$scope.waiting = false;
		});
	};

});
