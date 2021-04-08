<div class="panel panel-default" ng-controller="additionalCtrl">

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="AdditionalServiceHeadingTree" href="#AdditionalServiceDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Additional Services</a>
			<div class="right-head-btn">
			<button class="btn btn-primary btn-save function-button" ng-click="save()">
								<span>save</span>
			  </button></div>
		</h4>
	</div>
	<div id="AdditionalServiceDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="AdditionalServiceHeadingTree" style="overflow:scroll">
		<div class="page_box_content accordion-inner">
		
	
			<table class="table table-bordered table-sm table-striped table-hover no-footer " id="casetable">
				<thead>
					<tr>
						<td class="col-md-1" style="width:100px;">WorkStream<span style="color: red">*</span></td>
						<td class="col-md-2" style="width:12%;">Service Owner<span style="color: red">*</span></td>
						<td class="col-md-1" style="width:20%;">Service Activity<span style="color: red">*</span></td>
						<td class="col-md-1" style="width:20%;">Level<span style="color: red">*</span></td>
						
					
						<td style="width:120px;" ng-repeat ="prototyp in prototypes">{{prototyp.code}}(Hr)</td>
						<td class="col-md-1" style="width:15%;">CSP Rate</td>
					
<!-- 						<td class="col-md-1" style="width:60px;">Post Go-Live Support</td> -->
						<td class="col-md-1" style="width:70px;">Total Effort (Hr)</td>
						
						<td class="col-md-1" style="width:120px;">Action</td>
					</tr>
				</thead>
				<tbody>

					<tr ng-repeat="(itemIndex, item) in additionalitems">
						<td><select required="true" ng-init="x[$index].work_stream_id=item.work_stream_id" class="form-control ng-pristine ng-valid ng-touched" ng-model="x[$index].work_stream_id"  ng-options="c.id as c.code   for c in workstreams"
							></select></td>
						<td><select  required="true" ng-init="x[$index].service_owner_id=item.service_owner_id" class="form-control ng-pristine ng-valid ng-touched" ng-model="x[$index].service_owner_id"  ng-options="c.id as c.code   for c in serviceowners"
							></select></td>
						<td><input  required="true" type="text" ng-init="x[$index].activity=item.activity" class="form-control" ng-model="x[$index].activity" ng-value="item.activity"></td>
						
						<td><select  required="true" ng-init="x[$index].level_id=item.level_id" class="form-control ng-pristine ng-valid ng-touched" ng-model="x[$index].level_id" ng-options="c.id as c.code   for c in levels"
							></select></td>
						
						<input type="hidden" ng-init="x[$index].item_id = item.id;" />
						<td ng-repeat ="prototyp in prototypes"><input type="number" min ="0.0" class="form-control" ng-init="x[itemIndex].protos[prototyp.id]=item.protos[prototyp.id]" ng-model="x[itemIndex].protos[prototyp.id]" ng-value="item.protos[prototyp.id]"/></td>
						<td><input type="number"  min ="0.0" class="form-control" ng-init="x[$index].rate=item.rate" ng-model="x[$index].rate" ng-value="item.rate"></td>

						<td>{{item.tatalEffort}}</td>
						<td><button class="btn btn-primary function-button" ng-click="addrow()">
								<span><i class="glyphicon glyphicon-plus"></i></span>
							</button>
							<button class="btn btn-primary function-button" ng-click="dec(itemIndex)">
								<span><i class="glyphicon glyphicon-minus"></i></span>
							</button></td>
					</tr>

				</tbody>
			</table>
			
			</div>

			<hr>
			<div class="row">
			  <div style="text-align: right;border-right:0" class="col-md-10">
			  <Strong>Total Effort  :   {{TotalEffort}}</Strong></div>
			  
			  <div class="col-md-2">
<!-- 			  <button class="btn btn-primary btn-save function-button" ng-click="save()"> -->
<!-- 								<span>save</span> -->
<!-- 			  </button> -->
			  
			  </div>
			
		</div>
	</div>
</div>

<script>
	estimatorApp
			.controller(
					'AdditionalServiceMainController',
					[
							'$scope',
							'$http',
							'$rootScope',
							function($scope, $http, $rootScope) {
								
								
							
								
								$rootScope.initAddEffort = function() {
									$("#zhezhao").showLoading();
									var currentProjectId = $rootScope.currentProjectId;
									$rootScope.count = 0;
									
									$http
											.get(
													"Additional/findadditionalserviceInitData?id="
															+ currentProjectId)
											.success(
													function(response) {
														$rootScope.workstreams = response.workstreamLov;
														$rootScope.serviceowners = response.serviceOwnerLov;
														$rootScope.levels = response.LevelLov;
														$rootScope.prototypes = response.PrototypeLov;
														
														$rootScope.additionalitems = response.additionalServiceTotalDta;
														$rootScope.TotalEffort =response.TotalEffort;
														
														if ($rootScope.additionalitems == ""
																|| $rootScope.additionalitems == undefined) {
															$rootScope.addrow();
														} else {
// 													
														}
														
														$("#zhezhao").hideLoading();		
													});

									$rootScope.addrow = function() {
										$rootScope.additionalitems
												.push({
													id : -1,
													engagement_id : currentProjectId,
													work_stream_id : 0,
													service_owner_id : 0,
													level_id :0,
													activity : "",

												});

									};

								}

							} ]);

	estimatorApp
			.controller(
					'additionalCtrl',
					[
							'$scope',
							'$http',
							'$rootScope',
							function($scope, $http, $rootScope) {
								$scope.extra={};
								$scope.x = {};
								$scope.statusRice =false;
								$scope.dec = function(index) {
									$rootScope.additionalitems.splice(index, 1);
									delete $scope.x[index];
									
									if ($rootScope.additionalitems.length == 0) {
										//$('#saveServicesModal').modal('show');
// 										if($scope.statusRice ==true ){
// 											$scope.save();
// 										}
										
										
										$rootScope.addrow();
									}
								}

								$scope.save = function() {
									
									var checkData=$scope.x;
									var flag=true;
// 									$('#Portal-AdditionalService table td .ng-dirty').parent('tr').find("td > *").filter('[required]:visible').each(function(){
// 										if($(this).val() != ""){
// 											console.log($(this)	);
// 											flag=false;
// 											return;
// 										}
// 									});
		
									$('#Portal-AdditionalService .ng-dirty').parent().parent().find(".form-control").filter('[required]:visible').each(function(){
										var val = 	$(this).val();
										if(!flag || val == ""){
												flag = false;
												return true; 
											}
										});
								
									for(var i=0;i<checkData.length;i++){
										if(checkData[i].work_stream_id==0&&
												   checkData[i].service_owner_id==0&&checkData[i].level_id==0&&
												   checkData[i].activity==""){		
											continue;
												}else if(checkData[i].work_stream_id!=0&&
														   checkData[i].service_owner_id!=0&&checkData[i].level_id!=0&&
														   checkData[i].activity!=""){
													continue;
												}else{
													flag=false;
												}
									}
									
									if(flag==false){
										console.log(flag);
										toastr.error("Please fill the mandatory fields to save the data.").css("width","600px");
										return;
									}
									
								
									
									console.log($scope.x);
									var data =$scope.x;
									
									var currentProjectId = $rootScope.currentProjectId;
									$http(
											{
												method : "POST",
												url : "Additional/save",
												params : {
													data :data,
													id :currentProjectId
													
												},
												headers : {
													'Content-Type' : 'application/json'
												}
											}).success(function(msg) {
												if(msg=="\"success\""){
													toastr.success("AdditionalService information has been saved successfully.").css("width","600px");
												}else{
													toastr.error("Errors occurred while saving AdditionalService information. Please try again later.").css("width","600px");
												};
												$rootScope.initAddEffort();
									}).error(function() {
										toastr.error("Errors occurred while saving AdditionalService information. Please try again later.").css("width","600px");
									});

								};
								
								
								
// 								$scope.dec = function(index) {
// 									console.log("into");
// 									$rootScope.additionalitems.splice(index, 1);
									
// 									if ($rootScope.additionalitems.length == 0) {
// 										$rootScope.addrow();
// 									}
// 									console.log($rootScope.additionalitems.length);
// 								}
								
								$scope.calculate = function() {
									$rootScope.count = 0;
									var node = $rootScope.additionalitems;
									for (var i = 0; i < node.length; i++) {
										var assessment = node[i].prototype_effort_p1_assessment;
										var config = node[i].prototype_effort_p2_design_config;
										var validate = node[i].prototype_effort_p3_refine_validate;
										var deploy = node[i].prototype_effort_p4_test_deploy;
										var support = node[i].prototype_effort_post_go_live_support;
										$rootScope.count += assessment + config
												+ validate + deploy + support;
									}
								};

							} ]);
</script>