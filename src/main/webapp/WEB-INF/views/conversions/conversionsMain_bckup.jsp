<div class="panel panel-default" ng-controller="conversionsController">
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="ConversionCountHeadingTree" href="#ConversionCountHeadingDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Conversion Count </a>
			</h4>
		</div>
		<div id="ConversionCountHeadingDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="ConversionCountHeadingTree">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
							<td>RICE</td>
							<td>Default From Modules</td>
							<td>Client Specific</td>
							<td>Total</td>

						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in complexitys">
							<td ng-bind="item.description"></td>
							<td>{{totalArray[$index][0]}}</td>
							<td>{{totalArray[$index][1]}}</td>
							<td>{{totalArray[$index][2]}}</td>
						</tr>
						<tr>
							<td>Total</td>
							<td>{{totalArray[3][0]}}</td>
							<td>{{totalArray[3][1]}}</td>
							<td>{{totalArray[3][2]}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="ConversionsMetaDataHeadingTree" href="#ConversionsMetaDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Master Data </a>
			</h4>
		</div>
		<div id="ConversionsMetaDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="ConversionsMetaDataHeadingTree">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
							<td class="col-md-1" style="width:100px;">Data Type</td>
							<td class="col-md-1">Business Process</td>
							<td class="col-md-2" style="width:20%;">Module</td>
							<td class="col-md-1" style="width:40px;">Abbr.</td>
							<td class="col-md-1" style="width:20%;">Conversion</td>
							<td class="col-md-1" style="width:80px;">Defaulted with module in Scope</td>
							<td class="col-md-1" style="width:60px;">Override Flag</td>
							<td class="col-md-1" style="width:60px;">Final Scope</td>
							<td class="col-md-1" style="width:80px;">Client Complexity</td>
							<td class="col-md-1" style="width:80px;">Sources</td>
							<td class="col-md-1" style="width:80px;">Volume</td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in Masteritems">
							<td ng-bind="item.dataType"></td>
							<td ng-bind="item.businessProcess"></td>
							<td ng-bind="item.moduleName"></td>
							<td ng-bind="item.abbreviation"></td>
							<td ng-bind="item.conversion_name"></td>
							<td ng-bind="item.defaultedScope"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.scope_override_flag" ng-change="changeScope(item)">
									<option ng-repeat="x in YN" ng-selected="item.scope_override_flag==x">{{x}}</option>
							</select></td>
							<td ng-bind="item.final_scope_flag"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.description   for c in complexitys"
								ng-selected="item.client_complexity_id==c.id" ng-change="setConArray()"></td>
							<td><input type="text" class="form-control" ng-model="item.conversion_source"></td>
							<td><input type="number" min="0.0" class="form-control" ng-model="item.conversion_volume"></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="conversionsTransactionHeadingTree" href="#conversionsTransactionsDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Transaction Data </a>
			</h4>
		</div>
		<div id="conversionsTransactionsDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="conversionsTransactionHeadingTree">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
							<td class="col-md-1" style="width:100px;">Data Type</td>
							<td class="col-md-1">Business Process</td>
							<td class="col-md-2" style="width:20%;">Module</td>
							<td class="col-md-1" style="width:40px;">Abbr.</td>
							<td class="col-md-1" style="width:20%;">Conversion</td>
							<td class="col-md-1" style="width:80px;">Defaulted with module in Scope</td>
							<td class="col-md-1" style="width:60px;">Override Flag</td>
							<td class="col-md-1" style="width:60px;">Final Scope</td>
							<td class="col-md-1" style="width:80px;">Client Complexity</td>
							<td class="col-md-1" style="width:80px;">Sources</td>
							<td class="col-md-1" style="width:80px;">Volume</td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in Transactionitems">
							<td ng-bind="item.dataType"></td>
							<td ng-bind="item.businessProcess"></td>
							<td ng-bind="item.moduleName"></td>
							<td ng-bind="item.abbreviation"></td>
							<td ng-bind="item.conversion_name"></td>
							<td ng-bind="item.defaultedScope"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.scope_override_flag" ng-change="changeScope(item)">
									<option ng-repeat="x in YN" ng-selected="item.scope_override_flag==x">{{x}}</option>
							</select></td>
							<td ng-bind="item.final_scope_flag"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.description   for c in complexitys"
								ng-selected="item.client_complexity_id==c.id" ng-change="setConArray()"></td>
							<td><input type="text" class="form-control" ng-model="item.conversion_source"></td>
							<td><input type="number" min="0.0" class="form-control" ng-model="item.conversion_volume"></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="ConversionAddClientHeadingTree" href="#ConversionAddClientDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Add Additional Client Conversions</a>
			</h4>
		</div>
		<div id="ConversionAddClientDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="ConversionAddClientHeadingTree">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
							<td class="col-md-1" style="width:100px;">Data Type</td>
							<td class="col-md-2" style="width:120px;">Business Process</td> 
							<td class="col-md-2" style="width:16%;">Module</td>
							<td class="col-md-1" style="width:40px;">Abbr.</td>
							<td class="col-md-1" style="width:20%;">Conversion</td>
							<td class="col-md-1" style="width:60px;">Final Scope</td>
							<td class="col-md-1" style="width:85px;">Client Complexity</td>
							<td class="col-md-1" style="width:80px;">Sources</td>
							<td class="col-md-1" style="width:60px;">Volume</td>
							<td class="col-md-1" style="width:100px;">Option</td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in Userdataitems">
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.conversion_data_type_id" ng-options="c.id as c.description   for c in datatypes"
								ng-selected="item.conversion_data_type_id==c.id"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.business_process_id" ng-options="c.id as c.description   for c in businessProcess"
								ng-selected="item.business_process_id==c.id" ng-change="changeModel(item.business_process_id,$index)"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.module_id" ng-options="c.id as c.module_name   for c in modulemetas[$index]"
								ng-selected="item.module_id==c.id" ng-change="changeAbbreviation(item,$index)"></td>
							<td ng-bind="item.abbreviation"></td>
							<td><input type="text" class="form-control" ng-model="item.conversion_name"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.final_scope_flag">
									<option ng-repeat="x in YN" ng-selected="item.final_scope_flag==x">{{x}}</option>
							</select></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.description   for c in complexitys"
								ng-selected="item.client_complexity_id==c.id" ng-change="setConArray()"></td>
							<td><input type="text" class="form-control" ng-model="item.conversion_source"></td>
							<td><input type="number" min="0.0" class="form-control" ng-model="item.conversion_volume"></td>
							<td><button class="btn btn-primary function-button" ng-click="add()">
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
					'ConversionsMainController',
					[
							'$scope',
							'$http',
							'$rootScope',
							
							function($scope, $http, $rootScope) {
								
								$scope.init = function() {
									$("#zhezhao").showLoading();
									var currentProjectId = $rootScope.currentProjectId;
									$rootScope.modulemetas = [];
									$http
											.get(
													"RiceTransactionData/findcomplexity")
											.success(
													function(response) {
														$rootScope.complexitys = response;
														$http
																.get(
																		"Conversions/findconversions?id="
																				+ currentProjectId
																				+ "&type=Master Data&userflag=No")
																.success(
																		function(
																				response) {
																			$rootScope.Masteritems = response;
																			$http
																					.get(
																							"Conversions/findconversions?id="
																									+ currentProjectId
																									+ "&type=Transaction Data&userflag=No")
																					.success(
																							function(
																									response) {
																								$rootScope.Transactionitems = response;
																								$http
																										.get(
																												"Conversions/findallconversiondatatype")
																										.success(
																												function(
																														response) {
																													$rootScope.datatypes = response;
																													$http
																															.get(
																																	"Conversions/findallbusinessprocess")
																															.success(
																																	function(
																																			response) {
																																		$rootScope.businessProcess = response;
																																		$http
																																				.get(
																																						"Conversions/findconversionsforusercreate?id="
																																								+ currentProjectId)
																																				.success(
																																						function(
																																								response) {
																																							$rootScope.Userdataitems = response;
																																							if ($rootScope.Userdataitems == ""
																																									|| $rootScope.Userdataitems == undefined) {
																																								$rootScope
																																										.add();
																																							} else {
																																								$rootScope
																																										.getModule(
																																												0,
																																												$rootScope.Userdataitems.length);
																																							}
																																							$rootScope
																																									.setConArray();
																																							$("#zhezhao").hideLoading();
																																						});
																																	});
																												});
																							});
																		});
													});

									$rootScope.getModule = function(index, i) {
										if (index >= i) {
											return;
										}
										$http
												.get(
														"Conversions/findmodulemetadatabybusinessid?businessid="
																+ $rootScope.Userdataitems[index].business_process_id)
												.success(
														function(response) {
															$rootScope.modulemetas
																	.push(response);
															index++;
															$rootScope
																	.getModule(
																			index,
																			i);
														});
									};

									$rootScope.add = function() {
										$rootScope.Userdataitems.push({
											id : -1,
											conversion_data_type_id : 0,
											business_process_id : 0,
											module_id : 0,
											client_complexity_id : 0,
											engagement_id : currentProjectId,
											conversion_name : "",
											scope_override_flag : "",
											final_scope_flag : "",
											conversion_source : "",
											conversion_volume : "",
											is_user_create_flag : "Yes"
										});

									};

									$rootScope.setConArray = function() {
										$rootScope.totalArray = [];
										for (var i = 0; i < $rootScope.complexitys.length + 1; i++) {
											var a = [ 0, 0, 0 ];
											$rootScope.totalArray[i] = a;
										}
										var node1 = $rootScope.Masteritems;
										var node2 = $rootScope.Transactionitems;
										var node3 = $rootScope.Userdataitems;
										if (node1.length > 0) {
											for (var i = 0; i < node1.length; i++) {
												var complexity = node1[i].client_complexity_id;
												var flag = node1[i].final_scope_flag;
												for (var k = 0; k < $rootScope.complexitys.length; k++) {
													if (complexity == $rootScope.complexitys[k].id
															&& flag == 'Yes') {
														$rootScope.totalArray[k][0] += 1;
														$rootScope.totalArray[$rootScope.totalArray.length - 1][0] += 1;
														$rootScope.totalArray[k][2] += 1;
														$rootScope.totalArray[$rootScope.totalArray.length - 1][2] += 1;
													}
												}
											}
										}

										if (node2.length > 0) {
											for (var i = 0; i < node2.length; i++) {
												var complexity = node2[i].client_complexity_id;
												var flag = node2[i].final_scope_flag;
												for (var k = 0; k < $rootScope.complexitys.length; k++) {
													if (complexity == $rootScope.complexitys[k].id
															&& flag == 'Yes') {
														$rootScope.totalArray[k][0] += 1;
														$rootScope.totalArray[$rootScope.totalArray.length - 1][0] += 1;
														$rootScope.totalArray[k][2] += 1;
														$rootScope.totalArray[$rootScope.totalArray.length - 1][2] += 1;
													}
												}
											}
										}
										if (node3.length > 0) {
											for (var i = 0; i < node3.length; i++) {
												var complexity = node3[i].client_complexity_id;
												var flag = node3[i].final_scope_flag;
												for (var k = 0; k < $rootScope.complexitys.length; k++) {
													if (complexity == $rootScope.complexitys[k].id
															&& flag == 'Yes') {
														$rootScope.totalArray[k][1] += 1;
														$rootScope.totalArray[$rootScope.totalArray.length - 1][1] += 1;
														$rootScope.totalArray[k][2] += 1;
														$rootScope.totalArray[$rootScope.totalArray.length - 1][2] += 1;
													}
												}
											}
										}
									};

								}

							} ]);

	estimatorApp
			.controller(
					'conversionsController',
					[
							'$scope',
							'$http',
							'$rootScope',
							function($scope, $http, $rootScope) {

								$scope.YN = [ "Yes", "No" ];
								$scope.dec = function(index) {
									$rootScope.Userdataitems.splice(index, 1);
									$rootScope.modulemetas.splice(index, 1);
									$rootScope.setConArray();
									if ($rootScope.Userdataitems.length == 0) {
										$rootScope.add();
									}
								}
								$scope.changeScope = function(item) {
									item.final_scope_flag = item.scope_override_flag;
									$rootScope.setConArray();
								}
								$scope.changeModel = function(businessid, index) {
									$http
											.get(
													"Conversions/findmodulemetadatabybusinessid?businessid="
															+ businessid)
											.success(
													function(response) {
														$rootScope.modulemetas[index] = response;
													});
								}
								$scope.changeAbbreviation = function(item,
										index) {
									var moudels = $rootScope.modulemetas[index];
									for (var i = 0; i < moudels.length; i++) {
										if (moudels[i].id == item.module_id) {
											item.abbreviation = moudels[i].module_abbreviation;
										}
									}
								}
								
								$scope.save = function() {
									var checkData=$rootScope.Userdataitems;
									var flag=true;
									for(var i=0;i<checkData.length;i++){
										if(checkData[i].conversion_data_type_id==0&&checkData[i].business_process_id==0&&
												   checkData[i].module_id==0&&checkData[i].client_complexity_id==0&&
												   checkData[i].conversion_name==""&&checkData[i].final_scope_flag==""
												   &&checkData[i].conversion_source==""&&checkData[i].conversion_volume==""){		
											continue;
												}else if(checkData[i].conversion_data_type_id!=0&&checkData[i].business_process_id!=0&&
														   checkData[i].module_id!=0&&checkData[i].client_complexity_id!=0&&
														   checkData[i].conversion_name!=""&&checkData[i].final_scope_flag!=""){
													continue;
												}else{
													flag=false;
												}
									}
									if(flag==false){
										toastr.error("Data need to be filled in Add Additional Client Conversions module.").css("width","600px");
										return;
									}
									var data = JSON
											.stringify($rootScope.Masteritems
													.concat(
															$rootScope.Transactionitems)
													.concat(
															$rootScope.Userdataitems));
									$http(
											{
												method : "POST",
												url : "Conversions/update",
												params : {
													data : data
												},
												headers : {
													'Content-Type' : 'application/x-www-form-urlencoded'
												}
											}).success(function(msg) {
												if(msg=="\"success\""){
													toastr.success("Conversions information has been saved successfully.").css("width","600px");
												}else{
													toastr.error("Errors occurred while saving Conversions information. Please try again later.").css("width","600px");
												};

									}).error(function() {
										toastr.error("Errors occurred while saving Conversions information. Please try again later.").css("width","600px");
									});

								};
							} ]);
</script>