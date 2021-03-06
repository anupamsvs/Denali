(function() {
	// var projectManagementApp = angular.module('projectManagement',[]);
	estimatorApp.controller('reloadController', [ '$scope', function($scope) {
		$scope.reload = function() {
			$('#engagementTable').addClass('hide')
			window.location.reload();
		}
	} ]);

	estimatorApp
			.controller(
					'projectController',
					[
							'$scope',
							'$rootScope',
							'$http',
							'$filter',
							'$location',
							'$parse',
							'$timeout',
							function($scope, $rootScope, $http, $filter,
									$location, $parse, $timeout) {
								initVars = function() {
									$scope.testing = "No";
									$rootScope.fromSession = "";
									$scope.client_complexity_factor = 1;
									$scope.requestedDate = new Date();
									$('#requester').val(
											$rootScope.currentUserName);
									$('#requesterEmail').val(
											$rootScope.currentUserMail);

									$scope.goLiveSupport = 0;
								}

								$rootScope.headerDisplay = "";
								$scope.saveProject = function() {
									var plannedStartDate = $filter('date')(
											$scope.plannedStartDate,
											'MMM-dd-yyyy');
									var estimationRequestedDate = $filter(
											'date')($scope.requestedDate,
											'MMM-dd-yyyy');
									// alert(plannedStartDate);
									$scope.requestedDate = new Date().toJSON()
											.slice(0, 10);
									var params = {
										client_name : $scope.clientName,
										owner : $scope.owner,
										version : 1,
										project_no : $scope.project_no,
										engagement_name : $scope.engagementName,
										engagement_leader : $('#engagementName')
												.val(),
										engagement_leader_email : $(
												'#engagementLeaderEmail').val(),
										planned_project_start_date : plannedStartDate,
										client_sector : $scope.clientSector,
										estimation_requestor : $('#requester')
												.val(),
										estimation_requestor_email : $(
												'#requesterEmail').val(),
										estimation_requested_date : estimationRequestedDate,
										business_units_number : $scope.businessUnit,
										project_duration : $scope.duration,
										offering : $scope.offering,
										testing : $scope.testing,
										enterprise_application : "",
										client_complexity_factor : $scope.client_complexity_factor,
										post_go_live_support_duration : $scope.goLiveSupport,
										comments : $scope.comments
									// file_path:$scope.file_path
									}

									$http({
										url : 'project/saveProject',
										responseType : "json",
										method : 'GET',
										params : params,
									})
											.success(
													function(data) {
														if (data == false) {
															toastr
																	.error("Project No. exists already.");
															return;
														}
														debugger;
														$rootScope.projectDetails = data;
														$rootScope.projectDetails.planned_project_start_date = data.planned_project_start_date != undefined ? new Date(
																data.planned_project_start_date
																		.replace(
																				"/-/g",
																				"/"))
																: "";
														$rootScope.projectDetails.estimation_requested_date = new Date(
																data.estimation_requested_date
																		.replace(
																				"/-/g",
																				"/"));
														$rootScope.projectDetails.create_date = new Date(
																data.create_date
																		.replace(
																				"/-/g",
																				"/"));
														$rootScope.currentProjectId = data.id;
														$rootScope.currentGUID = data.guid;
														var offerngs = $rootScope.projectDetails.offering;
														console.log(offerngs);

														$rootScope.projectDetails.offering = [];
														var offeringlist = offerngs
																.split(",");
														for (var i = 0; i < offeringlist.length; i++) {
															console
																	.log(offeringlist[i])
															$rootScope.projectDetails.offering
																	.push(offeringlist[i]);
														}
														console
																.log($scope.projectDetails.offering);
														angular
																.element(
																		"#projectModal")
																.modal('hide');
														$scope.initialTable();

														// angular.element(document.querySelectorAll('.main-menu-items')).removeClass('main-menu-invisible');
														// angular.element(document.querySelectorAll('.nav
														// li')).removeClass('active');
														// angular.element(document.querySelector('#Details')).addClass('active');
														// angular.element("[id^=Portal]").addClass("hide");
														// angular.element("#Portal-Details").removeClass("hide");

													})
											.error(
													function(data) {

														toastr
																.error(
																		"Errors occurred while saving engagement.Please ensure Project Name entered is Unique.")
																.css("width",
																		"600px");
													});

								};
								// Copy project
								$scope.copyList = {};
								$scope.copyList.module = true;
								$scope.copyList.rice = true;
								$scope.copyList.conversion = true;
								$scope.copyList.addServices = true;
								$scope.copyList.rates = true;
								$scope.copyList.selected = false;
								$scope.copyList.projName = "";
								$scope.copyList.project_no = "";

								// Export project
								$scope.exportProject = function(proj) {
									if (!$scope.copyList.selected) {
										toastr
												.error("Please select a project to export");
										return;
									}
									$scope.copyList.projName = $scope.copyList.selected
											.split('|')[1];
									angular.element("#exportProjectModal")
											.modal('show');
								}

								$scope.exportProjectProc = function(proj) {
									var eng_id = $scope.copyList.selected
											.split('|')[0];
									var project_no = $scope.copyList.project_no;
									window.location.href = "project/summary/export/"
											+ eng_id;
									angular.element("#exportProjectModal")
											.modal('hide');
								}
								$scope.copyProject = function(proj) {
									// $scope.selectRadio;

									if (!$scope.copyList.selected) {
										toastr
												.error("Please select a project to copy");
										return;
									}
									$scope.copyList.projName = $scope.copyList.selected
											.split('|')[1];
									angular.element("#copyProject").modal(
											'show');
								};

								// Calculate Summary
								$scope.calculateSummary = function(selectRadio) {
									if (!$scope.copyList.selected) {
										toastr
												.error("Please select a project to calculate");
										return;
									}
									// alert("Are you sure?");
									projId = $scope.copyList.selected
											.split('|')[0];
									$http
											.post(
													'project/enagagement/calculate/'
															+ projId, {
														level : 3,
														engagement_id : projId
													})
											.success(
													function(data) {
														toastr
																.success("Engagement summary calculated successfully.");
													}).error(function() {

											});
								};

								$scope.copyProjectProc = function(proj) {
									var eng_id = $scope.copyList.selected
											.split('|')[0];
									var project_no = $scope.copyList.project_no;
									console.log($scope.copyList);
									// $scope.selectRadio;
									$http({
										url : 'project/copy-engagement',
										responseType : 'json',
										method : 'GET',
										params : {
											engagement_id : eng_id,
											project_no : project_no,
											enterprise_application : "",
											copyList : $scope.copyList
										}
									})
											.success(
													function(data) {
														if (data == false) {
															toastr
																	.error("Project No. already exists .");
															return;
														}
														var t = data;
														$scope.copyList = {};
														$scope.copyList.module = true;
														$scope.copyList.rice = true;
														$scope.copyList.conversion = true;
														$scope.copyList.addServices = true;
														$scope.copyList.rates = true;
														$scope.copyList.selected = false;
														$scope.copyList.projName = "";
														$scope.copyList.project_no = "";
														// window.location.reload();

														toastr
																.success("Engagement has been copied successfully.");
														// extra code
														$scope.initialTable();
														angular.element(
																"#copyProject")
																.modal('hide');

									
														$scope.copyList.selected = null;
													})
											.error(
													function() {
														// bootbox.alert("Share
														// Error!");
														toastr
																.error(
																		"Errors occurred while copying engagement. Please try again later.")
																.css("width",
																		"600px");
														angular
																.element(
																		"#releaseModal")
																.modal('hide');
													});
								};

								// Release Project
								$scope.currentReleaseID = '';
								$scope.showReleaseProjectModal = function(t) {
									$scope.currentReleaseID = t;
									angular.element("#releaseModal").modal(
											'show');
								};

								$scope.version = true;
								$scope.releseProject = function() {
									// angular.element("#releaseModal").modal('show');
									$("#zhezhao").showLoading();
									$http(
											{
												url : 'project/release-project',
												responseType : 'json',
												method : 'POST',
												params : {
													engagement_id : $scope.currentReleaseID,
													version_change : $scope.version,
													enterprise_application : ""
												}
											})
											.success(
													function(data) {
														// bootbox.alert({
														// size : 'small',
														// message : "Share
														// Successfully!"
														// });
														$("#zhezhao")
																.hideLoading();
														toastr
																.success(
																		"Engagement has been Released successfully.")
																.css("width",
																		"600px");
														angular
																.element(
																		"#releaseModal")
																.modal('hide');
														$scope.currentReleaseID = "";
														$scope.version = true;
														$scope.reload();
														// window.location.reload();
													})
											.error(
													function() {
														$("#zhezhao")
																.hideLoading();
														$scope.project_no = "";
														toastr
																.error(
																		"Errors occurred while releasing engagement. Please try again later.")
																.css("width",
																		"600px");
														angular
																.element(
																		"#releaseModal")
																.modal('hide');
													});
								};

								$scope.projectTableList = [];
								$scope.createTime;
								$rootScope.engagementEnterpriseApplicationList = [];
								$rootScope.engagementStatus = [];
								$rootScope.clientList = [];
								$rootScope.clientSector = [ 'CIPS',
										'Financial Services',
										'Forensic Services', 'GDM', 'HIA',
										'National-CS', 'TICE', 'TS-Advisory' ];
								$rootScope.enterpriseApplications = [];

								$scope.copy = false;
								$rootScope.readonly = false;

								$scope.initialTable = function(sessionId) {
									if(sessionId){
										$("#glassScreen").show();
										$rootScope.fromAdmin = true;
										$("#zhezhao").showLoading();
										
										$http.get('project/listAvailableProject/'+sessionId).success(function(data){
											$("#zhezhao").hideLoading();
											
											var projDetail = data.engagementList;
											$rootScope.currentGUID = data.currentGUID;
											$rootScope.engagementStatus = data.engagementStatusList;
											$rootScope.enterpriseApplications = data.enterpriseApplicationList;
											$rootScope.offeringList = data.offering;
											$rootScope.clientList = data.clientList;
											$rootScope.currentUserName = data.username;
											$rootScope.currentUserMail = data.email;
											$rootScope.pageTitle = "Home";
											$scope.openDetail(projDetail);
											return;
										}).error(function(error){
											$("#zhezhao").hideLoading();
											console.log(error);
										});
										
									}
									$("#zhezhao").showLoading();
									$('#engagementTable').removeClass('hide')
									$http({
										url : 'project/listAvailableProject',
										responseType : 'json',
										method : 'GET',
										params : {

										}
									})
											.success(
													function(data) {
														$("#zhezhao")
																.hideLoading();
														debugger;
														var projList = [];
														data.engagementList
																.forEach(function(
																		val) {
																	if (val.release == undefined) {
																		if (val.engagement_status == "Released") {
																			val.release = "false";
																		} else {
																			val.release = "true";
																		}
																		projList
																				.push(val);
																	} else {
																		projList
																				.push(val);
																	}
																});
														$scope.status = "";
														$scope.projectTableList = projList;
														$rootScope.currentGUID = data.currentGUID;
														$rootScope.engagementStatus = data.engagementStatusList;
														$rootScope.enterpriseApplications = data.enterpriseApplicationList;
														$rootScope.offeringList = data.offering;
														$rootScope.clientList = data.clientList;
														$rootScope.currentUserName = data.username;
														$rootScope.currentUserMail = data.email;
//														$rootScope.pageTitle = "Home";
//														console
//																.log($rootScope.clientList);
													});
								};
								$scope.status = "";
								$scope.searchProject = function() {
									$("#zhezhao").showLoading();
									$http(
											{
												url : 'project/listAvailableProject',
												responseType : 'json',
												method : 'GET',
												params : {
													client_name : $scope.searchClientName,
													engagement_name : $scope.searchEngagementName,
													engagement_status : $scope.status
												}
											})
											.success(
													function(data) {

														var projList = [];
														data.engagementList
																.forEach(function(
																		val) {
																	if (val.release == undefined) {
																		if (val.engagement_status == "Released") {
																			val.release = "false";
																		} else {
																			val.release = "true";
																		}
																		projList
																				.push(val);
																	} else {
																		projList
																				.push(val);
																	}
																});
														$scope.projectTableList = projList;
														$scope.searchClientName = "";
														$scope.searchEngagementName = "";
														$scope.status = "";
														$rootScope.enterpriseApplications = data.enterpriseApplicationList;
														$rootScope.currentGUID = data.currentGUID;
														$rootScope.engagementStatus = data.engagementStatusList;
														// $scope.projectTableList
														// = data;
														$("#zhezhao")
																.hideLoading();
													});
									$("#searchClientName").val("");
									$("#searchEngagementName").val("");
									$("#status").val("");
								}

								$scope.resetForm = function() {
									$scope.clientName = null;
									$scope.engagementName = null;
									$('#engagementName').val("");
									$('#engagementLeaderEmail').val(""),
											$scope.plannedStartDate = null;
									$scope.requestedDate = new Date().toJSON()
											.slice(0, 10);
									$scope.clientSector = null;
									$('#requester').val(""), $(
											'#requesterEmail').val(""),
											estimationRequestedDate = null;
									$scope.businessUnit = null;
									$scope.duration = null;
									$scope.goLiveSupport = null;
									$scope.comments = null;
									$scope.offering = null;
									initVars();
									$scope.listengMailNewProj = [];
									$scope.listengNameNewProj = [];
								}

								$scope.currentSharedID = 0;
								$scope.showShareProjectModal = function(t) {
									$scope.currentSharedID = t;
									angular.element("#shareModal")
											.modal('show');

									$http(
											{
												url : 'project/sharedUsers',
												responseType : 'json',
												method : 'GET',
												params : {
													engagement_id : $scope.currentSharedID
												}
											})
											.success(
													function(data) {
														if (data.sharednamelist == undefined) {
															return;
														}
														$scope.sharednamelist = data.listSharedNames;
														if ($scope.sharednamelist.length <= 0) {
															$scope.sharednamelist
																	.push("none");
														} else {
															$scope.sharednamelist = data.listSharedNames
																	.join(",");
														}

														console
																.log($scope.sharednamelist);

													}).error(
													function() {
														// bootbox.alert("Share
														// Error!");

														angular.element(
																"#shareModal")
																.modal('hide');
													});
								}
								$scope.editPermission = 'read';
								$scope.read = true;
								$scope.edit = false;
								$scope.release = false;
								$scope.name = '';

								$scope.shareProject = function() {
									$scope.read = false;
									$scope.edit = false;
									$scope.release = false;
									if ($scope.editPermission == "read") {
										$scope.read = true
									} else if ($scope.editPermission == "edit") {
										$scope.edit = true
									} else if ($scope.editPermission == "release") {
										$scope.release = true;
									}
									var params = {
										engagement_id : $scope.currentSharedID,
										guid : $scope.shareGUID,
										read : $scope.read,
										edit : $scope.edit,
										release : $scope.release,
										name : $scope.name
									};
									$http({
										url : 'project/shareProject',
										responseType : 'json',
										method : 'GET',
										params : params
									})
											.success(
													function(data) {
														if (data.listSharedNames == undefined) {
															toastr
																	.warning("Engagement is shared already.");
															angular
																	.element(
																			"#shareModal")
																	.modal(
																			'hide');
															return;
														}
														$scope.sharednamelist = data.listSharedNames;
														if ($scope.sharednamelist.length <= 0) {
															$scope.sharednamelist
																	.push("none");
														} else {
															$scope.sharednamelist = data.listSharedNames
																	.join(",");
														}

														console
																.log($scope.sharednamelist);

														// bootbox.alert({
														// size : 'small',
														// message : "Share
														// Successfully!"
														// });
														toastr
																.success(
																		"Engagement has been shared successfully.")
																.css("width",
																		"600px");
														angular.element(
																"#shareModal")
																.modal('hide');
														// window.location.reload();
													})
											.error(
													function() {
														// bootbox.alert("Share
														// Error!");
														toastr
																.error(
																		"Errors occurred while sharing engagement. Please try again later.")
																.css("width",
																		"600px");
														angular.element(
																"#shareModal")
																.modal('hide');
													});
								}

								$scope.openDetail = function(t) {
									$rootScope.listengName = [];
									$rootScope.listengMail = [];
									debugger;
									$http(
											{
												url : 'project/listEnterpriseApplicationsByEngagementId',
												responseType : 'json',
												method : 'GET',
												params : {
													id : t.id
												}
											})
											.success(
													function(data) {
														$rootScope.engagementEnterpriseApplicationList = data.engagementEnterpriseApplicationList;
													});
									// if already released disable editing

									$rootScope.projectDetails = t;
									$rootScope.projectDetails.readOnly = '';
									if ($scope.currentGUID == t.guid) {
										$rootScope.projectDetails.readOnly = '';
									} else {
										$rootScope.projectDetails.readOnly = (t.release == 'false' && t.edit == 'false') ? true
												: '';
									}
									if ($rootScope.projectDetails.engagement_status == "Released") {
										$rootScope.projectDetails.readOnly = true;
									}
									$rootScope.currentProjectId = t.id;
									if (!angular
											.isDate(t.planned_project_start_date)
											&& t.planned_project_start_date != null) {
										$rootScope.projectDetails.planned_project_start_date = new Date(
												t.planned_project_start_date
														.replace("/-/g", "/"));
									}
									if (!angular
											.isDate(t.estimation_requested_date)) {
										$rootScope.projectDetails.estimation_requested_date = new Date(
												t.estimation_requested_date
														.replace("/-/g", "/"));
									}
									if (!angular.isDate(t.create_date)) {
										$rootScope.projectDetails.create_date = new Date(
												t.create_date.replace("/-/g",
														"/"));
									}
									$rootScope.projectDetails.offering = t.offering
											.split(',').map(function(t) {
												return parseInt(t)
											});

									$http(
											{
												url : 'project/enagagement/getProgress/'
														+ $rootScope.currentProjectId,
												responseType : "json",
												method : 'GET'
											})
											.success(
													function(data) {
														$http.get("../lockcheck/"+$rootScope.currentProjectId,{}).success(function(lockData){
															var lock = lockData;
															if(lock.status === "false"){
																$("#glassScreen").show();
																$rootScope.lockMessage = "This project is locked. '"+lock.user.user+"' is working on the same project";
															}else{
																$("#glassScreen").hide();
																$rootScope.lockMessage = "";
															}
															var level = 0;
														if (data == null) {
															level = 1;
														} else {
															level = data.level;
														}
														for (var i = 1; i <= level; i++) {
															var menuEles = angular
																	.element(
																			document
																					.querySelectorAll('.main-menu-items.level-'
																							+ i))
																	.removeClass(
																			'main-menu-invisible');
															angular
																	.element(
																			document
																					.querySelectorAll('.nav li'))
																	.removeClass(
																			'active');
															angular
																	.element(
																			document
																					.querySelector('#Details'))
																	.addClass(
																			'active');
															angular
																	.element(
																			"[id^=Portal]")
																	.addClass(
																			"hide");
															angular
																	.element(
																			"#Portal-Details")
																	.removeClass(
																			"hide");
														}
														}).error(function(data) {});;
													}).error(function(data) {
												$scope.isSaveSuccess = false;
											});

									$rootScope.headerDisplay = "Project : "
											+ $rootScope.projectDetails.project_no
											+ "  |  Engagement Name : "
											+ $rootScope.projectDetails.engagement_name
											+ "  |  Version : "
											+ $rootScope.projectDetails.version;
									console.log($rootScope.headerDisplay);

								}
								$("#filePath").fileinput({
									uploadUrl : "project/upload",

									showPreview : false,

									// allowedFileExtensions : [ "zip", "bar",
									// "bpmn", "bpmn20.xml" ], ??????????????????

									elErrorContainer : "#fileError",

									browseClass : "btn btn-success",

									browseLabel : "Find",

									browseIcon : '<i ></i>',

									removeClass : "btn btn-danger",

									removeLabel : "Remove",

									removeIcon : '<i ></i>',

									showUpload : true,

									uploadClass : "btn btn-info",

									uploadIcon : '<i ></i>',

									enctype : 'multipart/form-data',

									maxFileSize : 0

								});
								$("#filePath")
										.on(
												"fileuploaded",
												function(event, data,
														previewId, index) {
													$scope.file_path = data.response;
												});
								var flag1 = true
								$(document)
										.click(
												function(e) {
													e = window.event || e;
													obj = $(e.srcElement
															|| e.target);
													if ($(obj)
															.is(
																	"#engagementNameDiv,#engagementNameDiv *")) {
													} else {
														if (flag1) {
															$(
																	"#engagementNameList")
																	.hide();
														}
													}
												});

								var engagementName = "";
								$("#engagementNameList").hide();

								$("#engagementName")
										.on(
												"input propertychange",
												function() {
													engagementName = $(this)
															.val();
													if (engagementName.length != 0) {
														setTimeout(
																function() {
																	if (engagementName
																			.indexOf(",") > 0) {
																		var arr = $(
																				"#engagementName")
																				.val()
																				.split(
																						",");
																		if (arr.length > 0) {
																			userName = arr[arr.length - 1];
																		}
																	} else {
																		userName = $(
																				"#engagementName")
																				.val();
																	}
																	$
																			.ajax({
																				url : "project/getUserInfoByName",
																				data : {
																					"userName" : userName
																				},
																				type : "json",
																				method : "post",
																				success : function(
																						result) {
																					var res = eval("("
																							+ result
																							+ ")");

																					if (res.ueList != null) {
																						var selectHtml = "";
																						var ueList = res.ueList;
																						var obj = {};
																						if (ueList.length == 0) {
																							if (engagementName.length != 0) {
																								selectHtml += "<li class='list-group-item' style='text-align:left;pointer-events: none;list-style-type:none;'>No Records Found</li>";
																							}
																						}
																						for (var i = 0; i < ueList.length; i++) {
																							if (i < 10) {
																								selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"
																										+ ueList[i].userName
																										+ "</li>";
																								obj[ueList[i].userName] = ueList[i].email;
																							} else if (i == 10) {
																								selectHtml += "<li class='list-group-item' name='more' style='text-align:center;list-style-type:none;'>>>view more</li>";
																								obj[ueList[i].userName] = ueList[i].email;
																							} else {
																								obj[ueList[i].userName] = ueList[i].email;
																							}

																						}
																						$(
																								"#engagementNameList")
																								.empty();
																						$(
																								"#engagementNameList")
																								.html(
																										selectHtml);
																						$(
																								"#engagementNameList")
																								.find(
																										'li')
																								.each(
																										function() {
																											$(
																													this)
																													.hover(
																															function() {
																																if ($(
																																		this)
																																		.attr(
																																				"name") != 'more') {
																																	$(
																																			this)
																																			.attr(
																																					"style",
																																					"color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
																																} else {
																																	$(
																																			this)
																																			.attr(
																																					"style",
																																					"color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
																																}
																															});
																											$(
																													this)
																													.mouseleave(
																															function() {
																																if ($(
																																		this)
																																		.attr(
																																				"name") != 'more') {
																																	$(
																																			this)
																																			.attr(
																																					"style",
																																					"text-align:left;list-style-type:none;")
																																} else {
																																	$(
																																			this)
																																			.attr(
																																					"style",
																																					"text-align:center;list-style-type:none;")
																																}
																															});
																											$(
																													this)
																													.on(
																															"click",
																															function() {

																																if ($(
																																		this)
																																		.attr(
																																				'name') == 'more') {
																																	flag1 = false
																																	for (var i = 10; i < ueList.length; i++) {
																																		selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"
																																				+ ueList[i].userName
																																				+ "</li>";
																																	}
																																	$(
																																			"#engagementNameList")
																																			.empty();
																																	$(
																																			"#engagementNameList")
																																			.html(
																																					selectHtml);
																																	$(
																																			"#engagementNameList")
																																			.find(
																																					'[name="more"]')
																																			.hide();
																																	setTimeout(
																																			function() {
																																				flag1 = true
																																			},
																																			1000);
																																	$(
																																			"#engagementNameList")
																																			.find(
																																					'li')
																																			.each(
																																					function() {
																																						$(
																																								this)
																																								.hover(
																																										function() {
																																											if ($(
																																													this)
																																													.attr(
																																															"name") != 'more') {
																																												$(
																																														this)
																																														.attr(
																																																"style",
																																																"color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
																																											} else {
																																												$(
																																														this)
																																														.attr(
																																																"style",
																																																"color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
																																											}
																																										});
																																						$(
																																								this)
																																								.mouseleave(
																																										function() {
																																											if ($(
																																													this)
																																													.attr(
																																															"name") != 'more') {
																																												$(
																																														this)
																																														.attr(
																																																"style",
																																																"text-align:left;list-style-type:none;")
																																											} else {
																																												$(
																																														this)
																																														.attr(
																																																"style",
																																																"text-align:center;list-style-type:none;")
																																											}
																																										});
																																						$(
																																								this)
																																								.on(
																																										"click",
																																										function() {

																																											flag1 = true
																																											var text = $(
																																													this)
																																													.text();
																																											$scope.listengNameNewProj
																																													.push(text);
																																											$scope.listengMailNewProj
																																													.push(obj[text]);

																																										});
																																					});
																																}
																																/*
																																 * isBox =
																																 * false;
																																 */
																																else {
																																	var text = $(
																																			this)
																																			.text();
																																	$scope.listengNameNewProj
																																			.push(text);
																																	$scope.listengMailNewProj
																																			.push(obj[text]);

																																}
																																$(
																																		"#engagementName")
																																		.val(
																																				$scope.listengNameNewProj
																																						.join(" , "));
																																$(
																																		"#engagementLeaderEmail")
																																		.val(
																																				$scope.listengMailNewProj
																																						.join(" , "));
																																$(
																																		this)
																																		.parent()
																																		.hide();

																															})
																										});
																					}
																				}
																			});
																	$(
																			"#engagementNameList")
																			.show();
																}, 1000);
													} else {
														$("#engagementNameList")
																.empty();
														$("#engagementNameList")
																.hide();

													}
												});

								var flag2 = true
								$(document)
										.click(
												function(e) {
													e = window.event || e;
													obj = $(e.srcElement
															|| e.target);
													if ($(obj)
															.is(
																	"#requesterDiv,#requesterDiv *")) {
													} else {
														if (flag2) {
															$("#requesterList")
																	.hide();
														}
													}
												});

								var requester = "";
								$("#requesterList").hide();

								$("#sharee-full-name")
										.on(
												"input propertychange",
												function() {
													sharee = $(this).val();
													if (sharee.length != 0) {
														$
																.ajax({
																	url : "project/getUserInfoByName",
																	data : {
																		"userName" : $(
																				this)
																				.val()
																	},
																	type : "json",
																	method : "post",
																	success : function(
																			result) {
																		var res = eval("("
																				+ result
																				+ ")");
																		if (res.ueList != null) {
																			var selectHtml = "";
																			var ueList = res.ueList;
																			var obj = {};
																			if (ueList.length == 0) {
																				if (sharee.length != 0) {
																					selectHtml += "<li class='list-group-item' style='text-align:left;pointer-events: none;list-style-type:none;'>No Records Found</li>";
																				}
																			}
																			for (var i = 0; i < ueList.length; i++) {
																				if (i < 10) {
																					selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"
																							+ ueList[i].userName
																							+ "</li>";
																					obj[ueList[i].userName] = ueList[i].email;
																					obj[ueList[i].email] = ueList[i].guid;
																				} else if (i == 10) {
																					selectHtml += "<li class='list-group-item' name='more' style='text-align:left;list-style-type:none;'>>>more</li>";
																					obj[ueList[i].userName] = ueList[i].email;
																					obj[ueList[i].email] = ueList[i].guid;
																				} else {
																					obj[ueList[i].userName] = ueList[i].email;
																					obj[ueList[i].email] = ueList[i].guid;
																				}
																			}
																			$(
																					"#shareeList")
																					.empty();
																			$(
																					"#shareeList")
																					.html(
																							selectHtml);
																			$(
																					"#shareeList")
																					.find(
																							'li')
																					.each(
																							function() {
																								$(
																										this)
																										.hover(
																												function() {
																													if ($(
																															this)
																															.attr(
																																	"name") != 'more') {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
																													} else {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
																													}
																												});
																								$(
																										this)
																										.mouseleave(
																												function() {
																													if ($(
																															this)
																															.attr(
																																	"name") != 'more') {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"text-align:left;list-style-type:none;")
																													} else {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"text-align:center;list-style-type:none;")
																													}
																												});
																								$(
																										this)
																										.on(
																												"click",
																												function() {

																													if ($(
																															this)
																															.attr(
																																	'name') == 'more') {
																														flag2 = false
																														for (var i = 10; i < ueList.length; i++) {
																															selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"
																																	+ ueList[i].userName
																																	+ "</li>";
																														}
																														$(
																																"#shareeList")
																																.empty();
																														$(
																																"#shareeList")
																																.html(
																																		selectHtml);
																														$(
																																"#shareeList")
																																.find(
																																		'[name="more"]')
																																.hide();
																														setTimeout(
																																function() {
																																	flag2 = true
																																},
																																1000);
																														$(
																																"#shareeList")
																																.find(
																																		'li')
																																.each(
																																		function() {
																																			$(
																																					this)
																																					.hover(
																																							function() {
																																								if ($(
																																										this)
																																										.attr(
																																												"name") != 'more') {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
																																								} else {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
																																								}
																																							});
																																			$(
																																					this)
																																					.mouseleave(
																																							function() {
																																								if ($(
																																										this)
																																										.attr(
																																												"name") != 'more') {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"text-align:left;list-style-type:none;")
																																								} else {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"text-align:center;list-style-type:none;")
																																								}
																																							});
																																			$(
																																					this)
																																					.on(
																																							"click",
																																							function() {
																																								flag2 = true
																																								var text = $(
																																										this)
																																										.text();
																																								var email = obj[text];
																																								var guid = obj[obj[text]]
																																										.split("CN=")[1];

																																								$(
																																										"#sharee_name")
																																										.val(
																																												obj[text]);
																																								$(
																																										"#sharee")
																																										.val(
																																												guid);
																																								$(
																																										"#sharee-full-name")
																																										.val(
																																												text);

																																								$scope.shareGUID = guid;
																																								$scope.name = email;

																																								$(
																																										this)
																																										.parent()
																																										.hide();
																																							});
																																		});
																													}

																													else {
																														var text = $(
																																this)
																																.text();
																														var email = obj[text];
																														var guid = obj[obj[text]]
																																.split("CN=")[1];

																														$(
																																"#sharee_name")
																																.val(
																																		obj[text]);
																														$(
																																"#sharee")
																																.val(
																																		guid);
																														$(
																																"#sharee-full-name")
																																.val(
																																		text);

																														$scope.shareGUID = guid;
																														$scope.name = email;

																														$(
																																this)
																																.parent()
																																.hide();
																													}

																												})
																							});
																		}
																	}
																});
														$("#shareeList").show();

													} else {
														$("#shareeList").hide();

													}
												});

								$("#requester")
										.on(
												"input propertychange",
												function() {
													requester = $(this).val();
													if (requester.length != 0) {
														$
																.ajax({
																	url : "project/getUserInfoByName",
																	data : {
																		"userName" : $(
																				"#requester")
																				.val()
																	},
																	type : "json",
																	method : "post",
																	success : function(
																			result) {
																		var res = eval("("
																				+ result
																				+ ")");
																		if (res.ueList != null) {
																			var selectHtml = "";
																			var ueList = res.ueList;
																			var obj = {};
																			if (ueList.length == 0) {
																				if (requester.length != 0) {
																					selectHtml += "<li class='list-group-item' style='text-align:left;pointer-events: none;list-style-type:none;'>No Records Found</li>";
																				}
																			}
																			for (var i = 0; i < ueList.length; i++) {
																				if (i < 10) {
																					selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"
																							+ ueList[i].userName
																							+ "</li>";
																					obj[ueList[i].userName] = ueList[i].email;
																				} else if (i == 10) {
																					selectHtml += "<li class='list-group-item' name='more' style='text-align:left;list-style-type:none;'>>>more</li>";
																					obj[ueList[i].userName] = ueList[i].email;
																				} else {
																					obj[ueList[i].userName] = ueList[i].email;
																				}
																			}
																			$(
																					"#requesterList")
																					.empty();
																			$(
																					"#requesterList")
																					.html(
																							selectHtml);
																			$(
																					"#requesterList")
																					.find(
																							'li')
																					.each(
																							function() {
																								$(
																										this)
																										.hover(
																												function() {
																													if ($(
																															this)
																															.attr(
																																	"name") != 'more') {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
																													} else {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
																													}
																												});
																								$(
																										this)
																										.mouseleave(
																												function() {
																													if ($(
																															this)
																															.attr(
																																	"name") != 'more') {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"text-align:left;list-style-type:none;")
																													} else {
																														$(
																																this)
																																.attr(
																																		"style",
																																		"text-align:center;list-style-type:none;")
																													}
																												});
																								$(
																										this)
																										.on(
																												"click",
																												function() {

																													if ($(
																															this)
																															.attr(
																																	'name') == 'more') {
																														flag2 = false
																														for (var i = 10; i < ueList.length; i++) {
																															selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"
																																	+ ueList[i].userName
																																	+ "</li>";
																														}
																														$(
																																"#requesterList")
																																.empty();
																														$(
																																"#requesterList")
																																.html(
																																		selectHtml);
																														$(
																																"#requesterList")
																																.find(
																																		'[name="more"]')
																																.hide();
																														setTimeout(
																																function() {
																																	flag2 = true
																																},
																																1000);
																														$(
																																"#requesterList")
																																.find(
																																		'li')
																																.each(
																																		function() {
																																			$(
																																					this)
																																					.hover(
																																							function() {
																																								if ($(
																																										this)
																																										.attr(
																																												"name") != 'more') {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
																																								} else {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
																																								}
																																							});
																																			$(
																																					this)
																																					.mouseleave(
																																							function() {
																																								if ($(
																																										this)
																																										.attr(
																																												"name") != 'more') {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"text-align:left;list-style-type:none;")
																																								} else {
																																									$(
																																											this)
																																											.attr(
																																													"style",
																																													"text-align:center;list-style-type:none;")
																																								}
																																							});
																																			$(
																																					this)
																																					.on(
																																							"click",
																																							function() {
																																								flag2 = true
																																								var text = $(
																																										this)
																																										.text();
																																								$(
																																										"#requester")
																																										.val(
																																												text);
																																								$(
																																										"#requesterEmail")
																																										.val(
																																												obj[text]);
																																								$(
																																										this)
																																										.parent()
																																										.hide();
																																							});
																																		});
																													}

																													else {
																														var text = $(
																																this)
																																.text();
																														$(
																																"#requester")
																																.val(
																																		text);
																														$(
																																"#requesterEmail")
																																.val(
																																		obj[text]);
																														$(
																																this)
																																.parent()
																																.hide();
																													}

																												})
																							});
																		}
																	}
																});
														$("#requesterList")
																.show();

													} else {
														$("#requesterList")
																.hide();

													}
												});
							} ]);
})();