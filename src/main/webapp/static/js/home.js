(function() {
	estimatorApp = angular.module('estimatorApp', [ 'ngTouch',
			'pageslide-directive', 'ui.grid', 'ui.grid.edit',
			'ui.grid.validate', 'ui.grid.pagination', 'ui.grid.pinning',
			'ngIdle' ]);
	estimatorApp.run(function($rootScope) {
		$rootScope.currentProjectId = 0;
		$rootScope.currentGUID = '';
		$rootScope.checked = false;
		$rootScope.toggle = function() {
			$rootScope.checked = !$rootScope.checked
		}
		$('#main-nav  li').click(function(e) {
			$rootScope.checked = false;
		});
		$rootScope.closeWindow = function close_window() {
			if (confirm("Close Window?")) {
				close();
			}
		}
		$rootScope.projectDetails;

	});

	estimatorApp.directive('numericOnly', function() {
		return {
			restrict : 'A',
			link : function(scope, element, attrs) {

				element.on('keypress', function(event) {

					if (!isIntegerChar())
						event.preventDefault();

					function isIntegerChar() {
						return /[0-9]|./.test(String.fromCharCode(event.which))
					}

				})

			}
		};
	});

	estimatorApp
			.controller('EventsCtrl',
					function($scope, $rootScope, $window, Idle) {
						$scope.events = [];

						$scope.$on('IdleStart', function() {
							// the user appears to have gone idle
						});

						$scope.$on('IdleTimeout', function() {
							// the user has timed out (meaning idleDuration +
							// timeout has passed without any activity)
							// this is where you'd log them
						});

					})
			.config(function(IdleProvider, KeepaliveProvider) {
				// configure Idle settings
				IdleProvider.idle(60); // in 1 min
				IdleProvider.timeout(600); // in 10 min
				// KeepaliveProvider.interval(2); // in seconds
			})
			.run(
					function($rootScope, Idle, $window) {
						// start watching when the app runs. also starts the
						// Keepalive service by default.
						Idle.watch();
						$rootScope
								.$on(
										'IdleStart',
										function() { /*
														 * Display modal warning
														 * or sth
														 */
											toastr
													.warning("The page will expired in 60 sec.Your page will redirected to login page");
										});
						$rootScope.$on('IdleTimeout', function($window) {
							/* Logout user */

							window.location.href = "/Denali/logout";
							// window.location =

						});
					});

})();

$(document)
		.ready(
				function() {
					var isChanged = false;
					$("#caselist").find("input").change(function() {
						isChanged = true;
					});
					$("#cspRateList").find("input").change(function() {
						isChanged = true;
					});
					$(
							"#ConversionsMetaDataList,#conversionsTransactionsDataList,#ConversionAddClientDataList")
							.change(function() {
								isChanged = true;
							});
					$("#AdditionalServiceDataList").change(function() {
						isChanged = true;
					});
					$("#ModuleList").change(function() {
						isChanged = true;
					});
					$("#OtherRICEDataList,#OtherList").change(function() {
						isChanged = true;
					});
					$("#pwcResponsibilityList,#pwcResponsibilityUnderList")
							.change(function() {
								isChanged = true;
							});

					$("#cspRateList").change(function() {
						isChanged = true;
					});

					$(".col-sm-8").find("button").click(function() {
						isChanged = false;
					});
					$(".col-md-12").find("table").find("tbody").find("tr")
							.find("td").find("input").click(function() {
								isChanged = false;
							});

					$(".col-md-12").find("div").find("div").find("button")
							.click(function() {
								isChanged = false;
							});

					$("#main-nav")
							.find("a")
							.click(
									function(e) {
										if (isChanged) {
											var msg = "Not saved!Leave this page without saving?";

											if (confirm(msg) == true) {
												isChanged = false;
												return true;
											} else {
												return false;
											}
										}
									});
				});