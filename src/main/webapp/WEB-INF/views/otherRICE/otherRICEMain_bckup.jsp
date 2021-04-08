<div class="panel panel-default" ng-controller="otherRiceCtrl">
	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherRICECountOfPaaSHeadingTree" href="#OtherRICECountOfPaaSDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Count of PaaS Type </a>
		</h4>
	</div>
	<div id="OtherRICECountOfPaaSDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICECountOfPaaSHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td style="width:350px">RICE</td>
						<td ng-repeat="item in paasapptypes">{{item.description}}</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in complexitys">
						<td>{{item.description}}</td>
						<td ng-repeat="i in paasapptypes">{{passtotalArray[$parent.$index][$index]}}</td>
					</tr>

					<tr>
						<td>Total</td>
						<td ng-repeat="i in paasapptypes">{{passtotalArray[passtotalArray.length-1][$index]}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherRICECountOfTypeHeadingTree" href="#OtherRICECountOfTypeDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Count of RICE Type </a>
		</h4>
	</div>
	<div id="OtherRICECountOfTypeDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICECountOfTypeHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td>RICE</td>
						<td ng-repeat="item in ricetypes">{{item.description}}</td>
						<td>Total</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in complexitys">
						<td>{{item.description}}</td>
						<td ng-repeat="i in ricetypes">{{ricetotalArray[$parent.$index][$index]}}</td>
						<td>{{ricetotalArray[$index][ricetypes.length]}}</td>
					</tr>

					<tr>
						<td>Total</td>
						<td ng-repeat="i in ricetypes">{{ricetotalArray[ricetotalArray.length-1][$index]}}</td>
						<td>{{ricetotalArray[ricetotalArray.length-1][ricetypes.length]}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherRICEHeadingTree" href="#OtherRICEDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Other RICE </a>
		</h4>
	</div>
	<div id="OtherRICEDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICEHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td style="width:100px;">RICE Type</td>
<!-- 						<td style="width:120px;">PaaS Apps Type</td> -->
						<td style="width:20%;">Module</td>
						<td style="width:25%;">RICE Name</td>
						<td style="width:90px;">Complexity</td>
						<td>Source System</td>
						<td>Target System</td>
						<td style="width:100px;">Action</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in items">
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.rice_type_id" ng-options="c.id as c.description   for c in ricetypes" ng-selected="item.rice_type_id==c.id"
							ng-change="setArray()"></td>
<!-- 						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.paas_app_type_id" ng-options="c.id as c.description   for c in paasapptypes" -->
<!-- 							ng-selected="item.paas_app_type_id==c.id" ng-change="setArray()"></td> -->
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.module_id" ng-options="c.id as c.module_name   for c in moduledatas" ng-selected="item.module_id==c.id"></td>
						<td><input type="text" class="form-control" ng-model="item.rice_name"></td>
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.description   for c in complexitys"
							ng-selected="item.client_complexity_id==c.id" ng-change="setArray()"></td>
						<td><input type="text" class="form-control" ng-model="item.rice_source_system"></td>
						<td><input type="text" class="form-control" ng-model="item.rice_target_system"></td>
						<td><button class="btn btn-primary function-button" ng-click="addtable()">
								<span><i class="glyphicon glyphicon-plus"></i></span>
							</button>
							<button class="btn btn-primary function-button" ng-click="dec($index)">
								<span><i class="glyphicon glyphicon-minus"></i></span>
							</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherTree" href="#OtherList">
		<h4 class="panel-title dashboard_box_title">
			<a>Client Complexity Multiplier </a>
		</h4>
	</div>
	<div id="OtherList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICEHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td>Technical</td>
						<td>Functional</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="number" min="0.0" class="form-control" ng-model="technical_complexity_multiplier" ng-change="setTechnical(technical_complexity_multiplier)"></td>
						<td><input type="number" min="0.0" class="form-control" ng-model="functional_complexity_multiplier" ng-change="setFunctional(functional_complexity_multiplier)"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="panel-heading panel-save">
		<button class="btn btn-primary btn-save function-button" ng-click="save()">
			<span>save</span>
		</button>
	</div>
</div>


<script>
	estimatorApp
			.controller(
					'OtherRICEMainController',
					[
							'$scope',
							'$http',
							'$rootScope',
							
							function($scope, $http, $rootScope) {
								
								
								$scope.init = function() {
									$("#zhezhao").showLoading();
									var currentProjectId = $rootScope.currentProjectId;
									$http
											.get(
													"RiceTransactionData/findricetype")
											.success(
													function(response) {
														$rootScope.ricetypes = response;
														$http
																.get(
																		"RiceTransactionData/findpaasapptype")
																.success(
																		function(
																				response) {
																			$rootScope.paasapptypes = response;
																			$http
																					.get(
																							"RiceTransactionData/findcomplexity")
																					.success(
																							function(
																									response) {
																								$rootScope.complexitys = response;
																								$http
																										.get(
																												"RiceTransactionData/findmoduledata?id="
																														+ currentProjectId)
																										.success(
																												function(
																														response) {
																													$rootScope.moduledatas = response;
																													$http
																															.get(
																																	"RiceTransactionData/findricetransactiondata?id="
																																			+ currentProjectId)
																															.success(
																																	function(
																																			response) {
																																		$rootScope.items = response;
																																		if ($rootScope.items == ""
																																				|| $rootScope.items == undefined) {
																																			$rootScope.technical_complexity_multiplier = 1.0;
																																			$rootScope.functional_complexity_multiplier = 1.0;
																																			$rootScope
																																					.addtable();
																																		} else {
																																			$rootScope.technical_complexity_multiplier = $rootScope.items[0].technical_complexity_multiplier;
																																			$rootScope.functional_complexity_multiplier = $rootScope.items[0].functional_complexity_multiplier;
																																		}
																																		$rootScope
																																				.setArray();
																																		$("#zhezhao").hideLoading();
																																	});
																												});
																							});
																		});
													});

									$rootScope.addtable = function() {
										$rootScope.items
												.push({
													id : -1,
													rice_type_id : 0,
													paas_app_type_id : 0,
													module_id : 0,
													client_complexity_id : 0,
													engagement_id : currentProjectId,
													rice_name : "",
													rice_source_system : "",
													rice_target_system : "",
													technical_complexity_multiplier : $rootScope.technical_complexity_multiplier,
													functional_complexity_multiplier : $rootScope.functional_complexity_multiplier

												});
									};

									$rootScope.setArray = function() {
										$rootScope.ricetotalArray = [];
										$rootScope.passtotalArray = [];
										for (var i = 0; i < $rootScope.complexitys.length + 1; i++) {
	//										var a = [];
											var b = [];
// 											for (j = 0; j < $rootScope.ricetypes.length + 1; j++) {
// 												a[j] = 0;
// 											}
// 											$rootScope.ricetotalArray[i] = a;
											for (j = 0; j < $rootScope.paasapptypes.length; j++) {
												b[j] = 0;
											}
											$rootScope.passtotalArray[i] = b;
										}
										var node = $rootScope.items;

										for (var i = 0; i < node.length; i++) {
											var rice_type = node[i].rice_type_id;
											var complexity = node[i].client_complexity_id;
											var paas_app_type = node[i].paas_app_type_id;

// 											for (var j = 0; j < $rootScope.ricetypes.length; j++) {
// 												if (rice_type == $rootScope.ricetypes[j].id) {
// 													for (var k = 0; k < $rootScope.complexitys.length; k++) {
// 														if (complexity == $rootScope.complexitys[k].id) {
// 															$rootScope.ricetotalArray[k][j] += 1;
// 															$rootScope.ricetotalArray[$rootScope.ricetotalArray.length - 1][j] += 1;
// 															$rootScope.ricetotalArray[k][$rootScope.ricetypes.length] += 1;
// 															$rootScope.ricetotalArray[$rootScope.ricetotalArray.length - 1][$rootScope.ricetypes.length] += 1;
// 														}
// 													}

// 												}
// 											}
											for (var j = 0; j < $rootScope.paasapptypes.length; j++) {
												if (paas_app_type == $rootScope.paasapptypes[j].id) {
													for (var k = 0; k < $rootScope.complexitys.length; k++) {
														if (complexity == $rootScope.complexitys[k].id) {
															$rootScope.passtotalArray[k][j] += 1;
															$rootScope.passtotalArray[$rootScope.passtotalArray.length - 1][j] += 1;
														}
													}
												}
											}
										}

									};
								}
							} ]);
	estimatorApp
			.controller(
					'otherRiceCtrl',
					function($scope, $http, $rootScope) {

						$scope.setTechnical = function(value) {
							if ($rootScope.items != undefined
									&& $rootScope.items != "") {
								for (var i = 0; i < $rootScope.items.length; i++) {
									$rootScope.items[i].technical_complexity_multiplier = value;
								}
							}
						}
						$scope.setFunctional = function(value) {
							if ($rootScope.items != undefined
									&& $rootScope.items != "") {
								for (var i = 0; i < $rootScope.items.length; i++) {
									$rootScope.items[i].functional_complexity_multiplier = value;
								}
							}
						}

						$scope.dec = function(index) {
							$rootScope.items.splice(index, 1);
							$rootScope.setArray();
							if ($rootScope.items.length == 0) {
								$rootScope.addtable();
							}
						}
					
						
						$scope.save = function() {
							
							var checkData=$rootScope.items;
							var flag=true;
							for(var i=0;i<checkData.length;i++){
								if(checkData[i].rice_type_id==0&&checkData[i].paas_app_type_id==0&&
										   checkData[i].module_id==0&&checkData[i].client_complexity_id==0&&
										   checkData[i].rice_name==""&&checkData[i].rice_source_system==""&&checkData[i].rice_target_system==""){		
									continue;
										}else if(checkData[i].rice_type_id!=0&&checkData[i].paas_app_type_id!=0&&
												   checkData[i].module_id!=0&&checkData[i].client_complexity_id!=0&&
												   checkData[i].rice_name!=""){
											continue;
										}else{
											flag=false;
										}
							}
							if(flag==false){
								toastr.error("Data need to be filled in Other RICE module.").css("width","600px");
								return;
							}
							
							var data = JSON.stringify($rootScope.items);
							$http(
									{
										method : "POST",
										url : "RiceTransactionData/save",
										params : {
											data : data
										},
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(msg) {
										if(msg=="\"success\""){
											toastr.success("Other RICE information has been saved successfully.").css("width","600px");
										}else{
											toastr.error("Errors occurred while saving Other RICE information. Please try again later.").css("width","600px");
										};
							}).error(function() {
								toastr.error("Errors occurred while saving Other RICE information. Please try again later.").css("width","600px");
							});

						};

					});
</script>

