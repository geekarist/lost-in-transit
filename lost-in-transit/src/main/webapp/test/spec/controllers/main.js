'use strict';

describe('Controller: MainCtrl', function() {

	// load the controller's module
	beforeEach(module('webappApp'));

	var MainCtrl, scope;

	// Initialize the controller and a mock scope
	beforeEach(inject(function($controller, $rootScope) {
	    $httpBackend = $injector.get('$httpBackend');
		scope = $rootScope.$new();
		MainCtrl = $controller('MainCtrl', {
			$scope : scope
		});
	}));

	it('should attach a list of awesomeThings to the scope', function() {
		expect(scope.awesomeThings.length).toBe(3);
	});
	
	it('should compute score', function() {
		// GIVEN
		scope.address = '9 rue de la croix faubin, 75011 Paris';
		// WHEN
		scope.computeScore();
		// THEN
		expect(scope.score).toBe(42);
	});
});
