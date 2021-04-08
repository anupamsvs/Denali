<div class="panel panel-default" ng-controller="ConversionsMainController" >
	<div class="panel panel-default">
	
	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="ConversionCountHeadingTree" href="#ConversionCountHeadingDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Conversion</a>
				<div class="right-head-btn">
			<button class="btn btn-primary btn-save function-button" ng-click="saveMultiplier(cmultiplierconversion)">
			<span>Save</span>
		</button></div>
			</h4>
		</div>
		
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
							<td>Conversion</td>
							<td>Default From Modules</td>
							<td>Client Specific</td>
							<td>Total</td>

						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in complexityList">
							<td ng-bind="item.code"></td>
							
							<td>{{isNaN(TotalEffortDefaulted[item.id])?0:TotalEffortDefaulted[item.id]}}</td>
				
							<td>{{isNaN(TotalEffortClient[item.id])?0:TotalEffortClient[item.id]}}</td>
						
							<td><b>{{totalMe(TotalEffortDefaulted[item.id]) + totalMe(TotalEffortClient[item.id])}}</b></td>
						</tr>
						<tr>
							<td><b>Total</b></td>
							<td><b>{{totalEffortDefaultedSum}}</b></td>
							<td><b>{{totalEffortClientSum}}</b></td>
							<td><b>{{totalConversionCountInScope}}</b></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="ConversionsMetaDataHeadingTree" href="#ConversionsMetaDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Pre-Defined Conversions</a>
			</h4>
		</div>
		<div id="ConversionsMetaDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="ConversionsMetaDataHeadingTree" style="overflow:scroll">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
						    
							<td class="col-md-1" style="width:100px;">Data Type</td>
							
							<td class="col-md-2" style="width:20%;">Module</td>
							<td class="col-md-1">Business Process</td>
							<td class="col-md-1" style="width:40px;">Abbr.</td>
							<td class="col-md-1" style="width:20%;">Conversion</td>
							<td class="col-md-1" style="width:80px;">Defaulted with module in Scope</td>
							<td class="col-md-1" style="width:60px;">Override Flag</td>
							<td class="col-md-1" style="width:60px;">Final Scope</td>
							<td class="col-md-1" style="width:80px;">Conversion Complexity</td>
							<td class="col-md-1" style="width:80px;">Sources</td>
							<td class="col-md-1" style="width:80px;">Volume</td>
							<td ng-repeat ="worktype in worktypeLov"  style="width:100px;">{{worktype.code}}</td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in Allitems">
						
							<td ><span >{{item.conversion_data_type_id | filterById:conversiondataTypList:'code'}}</span></td>
							<td ng-bind="item.moduleName"></td>
							<td ng-bind="item.businessProcess"></td>
							
							<td ng-bind="item.abbreviation"></td>
							<td ng-bind="item.conversion_name"></td>
							<td ng-bind="item.defaultedScope"></td>
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.scope_override_flag" ng-change="changeScope(item)">
									<option ng-repeat="x in YN" ng-selected="item.scope_override_flag==x">{{x}}</option>
							</select></td>
							<td ng-bind="item.final_scope_flag"></td>
						  
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.code for c in complexityList"
								ng-selected="item.client_complexity_id==c.id">
								
								</td>
							<td><input type="text" class="form-control" ng-model="item.conversion_source"></td>
							
							<td><input type="number" min="0.0" class="form-control" ng-model="item.conversion_volume"></td>
							<td ng-repeat ="worktype in worktypeLov"><input type="text" class="form-control" ng-readonly="true" ng-bind ="addValue[worktype.id][$index]" ng-init="masterConversionTotals[worktype.id] = totalMe(masterConversionTotals[worktype.id]) + total(listWorktypeConversioniddata[item.id+'_'+worktype.id],item.final_scope_flag)" ng-value="{{listWorktypeConversioniddata[item.id+'_'+worktype.id]}}"></td>
						    
						</tr>
						<tr><td colspan="11"><b>Total</b></td>
						    <td ng-repeat ="worktype in worktypeLov"><b>{{masterConversionTotals[worktype.id]}}</b></td></tr>
						

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
		<div id="ConversionAddClientDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="ConversionAddClientHeadingTree" style="overflow:scroll">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
							<td class="col-md-1" style="width:100px;">Data Type<span style="color: red">*</span></td>
							<td class="col-md-2" style="width:16%;">Module<span style="color: red">*</span></td>
							<td class="col-md-2" style="width:120px;">Business Process<span style="color: red">*</span></td> 
							
							<td class="col-md-1" style="width:40px;">Abbr<span style="color: red">*</span></td>
							<td class="col-md-1" style="width:60px">Conversion<span style="color: red">*</span></td>
							<td class="col-md-1" style="width:60px;">Final Scope<span style="color: red">*</span></td>
							<td class="col-md-1" style="width:85px;">Conversion Complexity<span style="color: red">*</span></td>
							<td class="col-md-1" style="width:80px;">Sources</td>
							<td class="col-md-1" style="width:60px;">Volume</td>
							<td ng-repeat ="worktype in worktypeLov" style="width:90px;">{{worktype.code}}</td>
							<td class="col-md-1" style="width:100px;">Option</td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in Userdataitems">
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.conversion_data_type_id" ng-options="c.id as c.code   for c in conversiondataTypList"
								ng-selected="item.conversion_data_type_id==c.id" ></td>
								
								<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.module_id" ng-options="module.id as module.module_name  for module in moduleDataList"
								ng-selected="item.module_id==module.id" ng-change="abbrInputModel(item,item.module_id)" ></td>
							
<!-- 							<td ng-bind="item.business_process_id"></td> -->
							<td><input  type="text" class="form-control" name="biz" id="biz" value="{{item.module_id | filterById:moduleDataList:'businessprocess':item.businessprocess}}"  readonly="readonly"></td>
						    
							
							
							
<!-- 							<td ng-bind="item.abbreviation"></td> -->
								<td><input  type="text" class="form-control" name="biz" id="biz" value="{{item.module_id | filterById:moduleDataList:'module_abbreviation':item.module_abbreviation}}" readonly="readonly"></td>
							
							<td><input type="text" class="form-control" ng-model="item.conversion_name"></td>
							
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.final_scope_flag" >
									<option ng-repeat="x in YN" ng-selected="item.final_scope_flag==x">{{x}}</option>
							</select></td>
							
							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.client_complexity_id" ng-options="c.id as c.code   for c in complexityList"
								ng-selected="item.client_complexity_id==c.id" ></td>
								
							<td><input type="text" class="form-control" ng-model="item.conversion_source"></td>
							<td><input type="number" min="0.0" class="form-control" ng-model="item.conversion_volume"></td>
<!-- 							<td ng-repeat ="worktype in worktypeLov"><input type="text" class="form-control" ng-init="otherTotals[worktype.id] = totalMe(otherTotals[worktype.id]) + total(listWorktypeConversioniddata[item.id+'_'+worktype.id],item.final_scope_flag)" ng-readonly="true" ng-bind ="addValue[worktype.id][$index]"  ng-value="{{listWorktypeConversioniddata[item.id+'_'+worktype.id]}}"></td> -->
		
<!-- 		                    <td ng-repeat ="worktype in worktypeLov"><input type="text" class="form-control" ng-init="totalConversionScopeCount(otherTotals[worktype.id],listWorktypeConversioniddata[item.id+'_'+worktype.id],item.final_scope_flag)" ng-readonly="true" ng-bind ="addValue[worktype.id][$index]" ng-init =""  ng-value="{{listWorktypeConversioniddata[item.id+'_'+worktype.id]}}"></td> -->
						    <td ng-repeat ="worktype in worktypeLov"><input type="text" class="form-control" ng-readonly="true" ng-bind ="addValue[worktype.id][$index]" ng-init="otherTotals[worktype.id] = totalMe(otherTotals[worktype.id]) + total(listWorktypeConversioniddata[item.id+'_'+worktype.id],item.final_scope_flag)" ng-value="{{listWorktypeConversioniddata[item.id+'_'+worktype.id]}}"></td>
						    				    
							<td><button class="btn btn-primary function-button" ng-click="add()">
									<span><i class="glyphicon glyphicon-plus"></i></span>
								</button>
								<button class="btn btn-primary function-button" ng-click="dec($index)">
									<span><i class="glyphicon glyphicon-minus"></i></span>
								</button></td>
						</tr>
						
						<tr><td colspan="9"><b>Total</b></td>
						    <td ng-repeat ="worktype in worktypeLov"><b>{{otherTotals[worktype.id]}}</b></td><td></td></tr>
					</tbody>
				</table>

			</div>
		</div>

	</div>
<!-- 	<div class="panel-heading panel-save"> -->
<!-- 		<button class="btn btn-primary btn-save function-button" ng-click="saveConversion()"> -->
<!-- 			<span>save</span> -->
<!-- 		</button> -->
<!-- 	</div> -->
	
	
	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="OtherTree" href="#OtherList">
		<h4 class="panel-title dashboard_box_title">
			<a>Client Complexity Multiplier</a>
		</h4>
	</div>
	<div id="OtherList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="OtherRICEHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
				<thead>
					<tr>
						<td ng-repeat ="workstream in worksteamList |orderBy : workstream.description">{{workstream.code}}</td>
<!-- 						<td>Action</td> -->
					
						
						
					</tr>
				</thead>
				<tbody>
					<tr>
<!-- 						<td ng-repeat ="workstream in worksteamList"> -->
<!-- 						<input type="number" min="1.0" class="form-control" ng-model="complexity_multiplier" ng-value="{{complexity_multiplier[workstream.id]}}" ng-model-options="{debounce:1000}" ng-change="saveMultiplier(workstream.id+'_'+complexity_multiplier+'_conversion')"> -->
<!-- 						</td> -->
                        <td ng-repeat ="workstream in worksteamList ">
						<input type="number" min="1.0" class="form-control"  ng-model="cmultiplierconversion[workstream.id]" ng-value="{{complexity_multiplier[workstream.id]}}">
						</td>
<!-- 						<td><button class="btn btn-primary btn-save function-button" ng-click="saveMultiplier(cmultiplierconversion)"> -->
<!-- 								<span>Save Complexity Multiplier</span></td> -->
						
					</tr>
				</tbody>
			</table>
			
		</div>
	</div>
	
	<div class="panel-heading panel-save">
		<button class="btn btn-primary btn-save function-button" ng-click="saveMultiplier(cmultiplierconversion)">
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
								
								$rootScope.YN = [ "Yes", "No" ];
							    $rootScope.addValue ={};
							    $scope.cmultiplierconversion ={};
							    
							    
							    $scope.saveMultiplier =function(data){
									
									var id = $rootScope.currentProjectId;
									$http.post("Conversions/saveComplexityMultiplier",
											 {
												data : data,
												id :{engId:id}
												
											}).success(function(msg) {
												    $rootScope.saveConversion();
													//toastr.success(" Complexity multiplier data saved successfully.").css("width","600px");
													//$rootScope.initRice();
												
												
									}).error(function() {
										toastr.error("Errors occurred while saving data. Please try again later.").css("width","600px");
									});
									
								}
							    
							    
								$scope.totalMe = function(newadd){
									if(newadd == undefined){
										return 0;
									}
									return parseInt(newadd);
								}
								


                               $scope.total = function(eachitem,flag){
                            	   
                            	   console.log(eachitem +"+++"+flag)
                            	   if(flag == 'Yes'){
                            		   return parseInt(eachitem);
                            	   }else{
                            		   return 0;
                            	   }
                            	   
                               }
								
								
								$scope.dec = function(index) {
									$rootScope.Userdataitems.splice(index, 1);
									
									
									if ($rootScope.Userdataitems.length == 0) {
										$rootScope.add();
									}
								}
								
								$rootScope.initConversion = function() {
									
									$scope.otherTotals = {};
									$scope.totalClientConversion =0;
									$scope.totalDefaultConversion =0;
									$scope.masterConversionTotals = {};
									$scope.conversionTotalsRow = {};
									$scope.conversionTotalsCol = {};
									
									$("#zhezhao").showLoading();
									
									
									var idparam = $rootScope.currentProjectId;
									
									console.log(idparam);
										$http({	
													url : "Conversions/loadAllConversion",
													responseType: "json",
													method:'GET',
													params : {
														idparam : idparam
													}
												}).success(function(response) {
													
													console.log("success");
													
													$rootScope.conversiondataTypList=response.conversiondataTypList;
													$rootScope.moduleDataList=response.moduleDataList;
													$rootScope.complexityList=response.complexityList;
													$rootScope.worktypeLov=response.worktypeLov;
													$rootScope.Userdataitems = response.conversiontrasactionDta;
													$rootScope.Allitems=response.conversionAllAdminList;
													$rootScope.listWorktypeConversioniddata =response.listWorktypeConversioniddata;
													$rootScope.len=$rootScope.complexityList.length;
													$rootScope.TotalEffortDefaulted = response.TotalEffortDefaulted;
													$rootScope.TotalEffortClient=response.TotalEffortClient;
													$scope.totalEffortClientSum =$scope.getSum($rootScope.TotalEffortClient);
													$scope.totalEffortDefaultedSum =$scope.getSum($rootScope.TotalEffortDefaulted);
													$scope.complexity_multiplier=response.Complexity_multiplier;
													$scope.worksteamList =response.worksteamList;
													$scope.totalConversionCountInScope=response.totalConversionCountInScope;
													
													
													if ($rootScope.Userdataitems == "")
															{
														$rootScope.add();
													}
													
												
													$("#zhezhao").hideLoading();
												
													
										}).error(function() {
											toastr.error("Errors occurred while loading. Please try again later.").css("width","600px");
										});
										
								}
								
								$rootScope.getId =function(complexity){
									
									for(var i = 0; i < $rootScope.complexityList.length; i++){
										
										if(complexity == $rootScope.complexityList[i].code){
											return $rootScope.complexityList[i].id;	
										}
									}
									
								}
								
								$scope.getSum = function(ArrayToSum) {
									
									var sum =0;
									for(var i in ArrayToSum){
										
										sum =sum + parseInt(ArrayToSum[i]);
										
									}
									return sum;
				
								};

							

								
								
								$rootScope.saveConversion = function() {
									var checkData=$rootScope.Userdataitems;
									
									var flag=true;
									for(var i=0;i<checkData.length;i++){
										if(checkData[i].conversion_data_type_id==0&&
												   checkData[i].module_id==0&&checkData[i].client_complexity_id==0&&
												   checkData[i].conversion_name==""&&checkData[i].final_scope_flag==""
												   &&checkData[i].conversion_source==""&&checkData[i].conversion_volume==""){		
											continue;
												}else if(checkData[i].conversion_data_type_id!=0&&
														   checkData[i].module_id!=0&&checkData[i].client_complexity_id!=0&&
														   checkData[i].conversion_name!=""&&checkData[i].final_scope_flag!=""){
													continue;
												}else{
													flag=false;
												}
									}
									if(flag==false){
										toastr.error("Please fill the mandatory fields in Add Additional Client Conversions module to save the data.").css("width","600px");
										return;
									}
									var data = JSON
											.stringify($rootScope.Userdataitems.concat($rootScope.Allitems));
									console.log(data);
												
									$http(
											{
												method : "POST",
												url : "Conversions/update",
												params : {
													data : data,
													id : $rootScope.currentProjectId
												},
												headers : {
													'Content-Type' : 'application/x-www-form-urlencoded'
												}
											}).success(function(msg) {
												if(msg=="\"success\""){
													toastr.success("Conversions information has been saved successfully.").css("width","600px");
												}
												$rootScope.initConversion();

									}).error(function() {
										toastr.error("Duplicate conversion already exists.").css("width","600px");
									});

								};
								
								
								
								$rootScope.abbrInputModel = function(item,abbreviation){
									console.log(abbreviation);
									
									if (abbreviation !== undefined){
										if(typeof(abbr) == "object"){
											abbreviation = abbreviation.id;
										}
										for(var i=0; i<$rootScope.moduleDataList.length; i++){
											if ($rootScope.moduleDataList[i].id == abbreviation){
												
												
												item.abbreviation = $rootScope.moduleDataList[i].module_abbreviation;
												//$rootScope.id   =   $rootScope.moduleDataList[i].id;
											    item.businessprocess  =   $rootScope.moduleDataList[i].businessprocess;
												item.final_scope_flag  =   $rootScope.moduleDataList[i].is_in_scope_flag;
												
												
												//$rootScope.singleConversion.businessprocess = $rootScope.businessprocess;
												return;
											}				
										}
									}
								}
								
								$rootScope.add = function() {
									$rootScope.Userdataitems.push({
										id : -1,
										conversion_data_type_id : 0,
										business_process_id : 0,
										module_id : 0,
										client_complexity_id : 0,
										engagement_id : $rootScope.currentProjectId,
										conversion_name : "",
										scope_override_flag : "",
										final_scope_flag : "",
										conversion_source : "",
										conversion_volume : "",
										is_user_create_flag : "Yes"
									});

								};
								$rootScope.changeScope = function(item) {
									console.log(item.client_complexity_id);
									item.final_scope_flag = item.scope_override_flag;
									
									
								}
								
								
															} ]).filter('filterById', function() { 
																 return function( id,arrayObjs,retKey,item) { 
																	 try{
																	 for(var i = 0;i<=arrayObjs.length;i++){
																		 
																		 if(parseInt(id) == arrayObjs[i].id){ 
																			 item = arrayObjs[i][retKey];
																			 return arrayObjs[i][retKey];
																		 }
																		 
																	 }
																	 }catch(e){}
																	
																 };
															 });
</script>