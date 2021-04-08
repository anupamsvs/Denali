<div class="panel panel-default" ng-controller="OtherRICEMainController">
	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherRICECountOfTypeHeadingTree" href="#OtherRICECountOfTypeDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Count Of Rie/Pass</a>
			<div class="right-head-btn">
			<button class="btn btn-primary btn-save function-button" ng-click="savecomplexity_multiplier(cmultiplier)">
			<span>Save/Refresh</span>
		</button></div>
		</h4>
	</div>
	<div id="OtherRICECountOfTypeDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICECountOfTypeHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td>RIE</td>
						<td ng-repeat="item in rieLov">{{item.code}}</td>
						<td>Total</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in complexityLov">
						<td>{{item.code}}</td>
						<td ng-repeat="rie in rieLov" ng-init="rieTotalsRow[rie.id] = totalMe(rieTotalsRow[rie.id])+totalMe(Allcount_rice_record[rie.id+'_'+item.id]);rieTotalsCol[item.id] = totalMe(rieTotalsCol[item.id])+totalMe(Allcount_rice_record[rie.id+'_'+item.id]);">{{Allcount_rice_record[rie.id+'_'+item.id]}}</Label></td>
						<td ><b>{{rieTotalsCol[item.id]}}</b></td>
					</tr>
					<tr>
					<td><b>Total</b></td>
					<td ng-repeat="item in rieLov"><b>{{rieTotalsRow[item.id]}}</b></td>				
					<td><b>{{AllRieTotal}}</b></td>
					</tr>
				    
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherRICEHeadingTree" href="#OtherRICEDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Other RIE </a>
		</h4>
	</div>
	<div id="OtherRICEDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICEHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td style="width:100px;">RIE Type<span style="color: red">*</span></td>
<!-- 						<td style="width:120px;">PaaS Apps Type</td> -->
						<td style="width:20%;">Module<span style="color: red">*</span></td>
						<td style="width:25%;">RIE Name<span style="color: red">*</span></td>
						<td style="width:90px;">Complexity<span style="color: red">*</span></td>
						<td>Source System</td>
						<td>Target System</td>
						<td ng-repeat ="worktype in worktypeLov">{{worktype.code}}</td>
						<td style="width:100px;">Action</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in items">
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.rice_type_id" ng-options="c.id as c.code   for c in rieLov" ng-selected="item.rice_type_id==c.id"
							ng-change="setArray()"></td>
						
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.module_id" ng-options="c.id as c.module_name for c in moduleNameLov" ng-selected="item.module_id==c.id"></td>
						
						<td><input type="text" class="form-control" ng-model="item.rice_name"></td>
						
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.code   for c in complexityLov"
							ng-selected="item.client_complexity_id==c.id" ng-change="setArray()"></td>
						
						<td><input type="text" class="form-control" ng-model="item.rice_source_system"></td>
						<td><input type="text" class="form-control" ng-model="item.rice_target_system"></td>
						<td ng-repeat ="worktype in worktypeLov"><input type="text" ng-init="otherTotals[worktype.id] = totalMe(otherTotals[worktype.id]) + totalMe(listWorktypeRiceiddata[item.id+'_'+worktype.id])" class="form-control" ng-readonly="true" ng-value="{{listWorktypeRiceiddata[item.id+'_'+worktype.id]}}"></td>
						
						<td><button class="btn btn-primary function-button" ng-click="addtable()">
								<span><i class="glyphicon glyphicon-plus"></i></span>
							</button>
							<button class="btn btn-primary function-button" ng-click="dec($index)">
								<span><i class="glyphicon glyphicon-minus"></i></span>
							</button></td>
					</tr>
					
					<tr><td colspan="6"><b>Total</b></td>
					<td ng-repeat="worktype in worktypeLov"><label>{{otherTotals[worktype.id]}}</label><td></tr>
					
				</tbody>
			</table>
<!-- 			<div class="panel-heading panel-save"> -->
<!-- 			<button class="btn btn-primary btn-save function-button" ng-click="save()"> -->
<!-- 			<span>save</span> -->
<!-- 		</button> -->
<!-- 	</div> -->
		</div>
	</div>

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherTree" href="#OtherList">
		<h4 class="panel-title dashboard_box_title">
			<a>Rie Complexity Multiplier </a>
		</h4>
	</div>
	<div id="OtherList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICEHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td ng-repeat ="workstream in WorkstreamLov |orderBy : workstream.description">{{workstream.code}}</td>
<!-- 					    <td>Action</td> -->
						
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td ng-repeat ="workstream in WorkstreamLov ">
						<input type="number" min="1.0" class="form-control"  ng-model="cmultiplier[workstream.id]" ng-value="{{complexity_multiplier[workstream.id]}}">
						</td>
<!-- 						<td><button class="btn btn-primary btn-save function-button" ng-click="savecomplexity_multiplier(cmultiplier)"> -->
<!-- 								<span>Save Complexity Multiplier</span></td> -->
						
					</tr>
				</tbody>
			</table>
			
		</div>
	</div>
	
	<div class="panel-heading panel-save">
			<button class="btn btn-primary btn-save function-button" ng-click="savecomplexity_multiplier(cmultiplier)">
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
								$scope.cmultiplier ={};
								
							  
								$scope.totalMe = function(newadd){
									if(newadd == undefined){
										return 0;
									}
									return parseInt(newadd);
								}
								$scope.addMe = function(newadd,oldadd){
									newadd = newadd==undefined?0:newadd;
									oldadd = oldadd==undefined?0:oldadd;
									sum = parseInt(newadd)+parseInt(oldadd);
									return sum;
								}
								
								$rootScope.initRice = function() {
									$scope.otherTotals = {};
									$scope.rieTotals = {};
									$scope.rieTotalsRow = {};
									$scope.rieTotalsCol = {};
									$rootScope.AllRieTotal=0;
									$("#zhezhao").showLoading();
									
									// $rootScope.currentProjectId +"-"+$rootScope.projectDetails.version
									var idparam = $rootScope.currentProjectId;
									
									console.log(idparam);
										$http({	
													url : "RiceTransactionData/loadAllRice",
													responseType: "json",
													method:'GET',
													params : {
														idparam : idparam
													}
												}).success(function(response) {
													
													
													
													$rootScope.complexityLov=response.complexityLov;
													$rootScope.rieLov=response.rieLov;
													$rootScope.moduleNameLov=response.moduleNameLov;
													$rootScope.worktypeLov=response.worktypeLov;
													$rootScope.WorkstreamLov =response.WorkstreamLov;
													$rootScope.listWorktypeRiceiddata =response.listWorktypeRiceiddata;
													$rootScope.complexity_multiplier =response.complexity_multiplier;
													$rootScope.Allcount_rice_record=response.Allcount_rice_record;
													$rootScope.AllRieTotal=response.AllRieTotal;
													
													$rootScope.items = response.riceDta;
													if ($rootScope.items == "")
															{
														$rootScope.addtable();
													} 
													$rootScope
													.setArray();
													$("#zhezhao").hideLoading();
												
													
										}).error(function() {
											toastr.error("Errors occurred while saving  information. Please try again later.").css("width","600px");
										});
								}

										$rootScope.addtable = function() {
											$rootScope.items
													.push({
														id : -1,
														rice_type_id : 0,
														//worktypeEfforts:0,
														module_id : 0,
														client_complexity_id : 0,
														engagement_id : $rootScope.currentProjectId,
														rice_name : "",
														rice_source_system : "",
														rice_target_system : "",
														

													});
										};
										
										$rootScope.save = function() {
											console.log("save");
											var checkData=$rootScope.items;
											var flag=true;
											for(var i=0;i<checkData.length;i++){
												if(checkData[i].rice_type_id==0&&
														   checkData[i].module_id==0&&checkData[i].client_complexity_id==0&&
														   checkData[i].rice_name==""&&checkData[i].rice_source_system==""&&checkData[i].rice_target_system==""){
													
													continue;
														}else if(checkData[i].rice_type_id!=0&&
																   checkData[i].module_id!=0&&checkData[i].client_complexity_id!=0&&
																   checkData[i].rice_name!=""){
															continue;
														}else{
															flag=false;
														}
											}
											if(flag==false){
												toastr.error("Please fill the mandatory fields in Other RICE module to save the data.").css("width","600px");
												return;
											}
											
											if(flag == true && $rootScope.worktypeLov.length!=0){
												console.log("true");	
											
											
											var data = JSON.stringify($rootScope.items);
											console.log(data);
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
														$rootScope.initRice();
														
											}).error(function() {
												toastr.error("Errors occurred while saving Other RICE information. Please try again later.").css("width","600px");
											});
											}

										};
										
										$rootScope.dec = function(index) {
											$rootScope.items.splice(index, 1);
											$rootScope.setArray();
											if ($rootScope.items.length == 0) {
												$rootScope.addtable();
											}
										}
										
										$scope.savecomplexity_multiplier =function(data){
											
										    
											var id = $rootScope.currentProjectId;
											$http.post("RiceTransactionData/saveComplexityMultiplier",
														 {
															data : data,
															id :{engId:id}
															
														}).success(function(msg) {
														
														
															//toastr.success("Complexity multiplier data saved successfully.").css("width","600px");
															$rootScope.save();
														
														
											}).error(function() {
												toastr.error("Errors occurred while saving data. Please try again later.").css("width","600px");
											});
											
										}
										$rootScope.setArray =function(){
											
										}
										
										
										
								
								
					}]);
</script>