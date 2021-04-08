<div class="panel panel-default" ng-controller="responsibilityCtrl">
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle"  role="tab" id="pwcResponsibilityHeadingTree" href="#pwcResponsibilityList">
			<h4 class="panel-title dashboard_box_title">
				<a>PwC Responsibility </a>
					 <div class="right-head-btn">
	    <b>Please Select to save the Form: <b>
	    <select id="checkpwcResponsibility" ng-model="checkpwcResponsibility" name ="checkpwcResponsibility">
	                       <option>--- Select Pwc Responsibility Y/N ---</option>
	                      <option value="No" selected>No</option>
					      <option value="Yes">Yes</option>
					      
		 </select>
		
		<button class="btn btn-primary btn-save function-button" ng-click="save(checkpwcResponsibility)">
			<span >save</span>
		</button>
		</div>
			</h4>
		</div>
		<div id="pwcResponsibilityList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="pwcResponsibilityHeadingTree">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
						    <td class="col-md-2"><input type="checkbox" ng-model="selectedAllOthers" ng-click="checkAll()" /></td>
							<td class="col-md-2">Work Stream</td>
							<td class="col-md-3">ProtoType</td>
							<td class="col-md-5">Activity</td>
							<td class="col-md-5">Bu Factor</td>
							
							<td class="col-md-2" style="width:120px;">PwC Responsibility</td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="itemoth in itemsother">
						    <td><input type="checkbox" ng-model="itemoth.selected"/></td>
							<td >{{itemoth.workstream | filterById:workstreamlst:'code'}}</td>
							<td >{{itemoth.prototype | filterById:prototypelst:'code'}}</td>
							<td ng-bind="itemoth.activity"></td>
							<td ng-bind="itemoth.bu_dependency"></td>
							<td ng-bind="itemoth.is_pwc_responsibility_flag"></td>
<!-- 							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="itemoth.is_pwc_responsibility_flag"> -->
<!-- 									<option ng-repeat="x in YN" ng-selected="itemoth.is_pwc_responsibility_flag==x">{{x}}</option> -->
<!-- 							</select></td> -->
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div id="caselist" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
			<div class="page_box_content">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<tr>
						<td style="text-align: left"><strong>Will PwC use a dedicated PMO Team? Otherwise, the PMO Activities will be performed by the Functional Team:</strong></td>
						<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="pmo_flag" ng-change="changedata(pmo_flag)">
								<option ng-repeat="x in YN" ng-selected="pmo_flag==x">{{x}}</option>
						</select></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="pwcResponsibilityUnderHeadingTree" href="#pwcResponsibilityUnderList">
			<h4 class="panel-title dashboard_box_title">
				<a>PwC Responsibility </a>
			</h4>
		</div>
		<div id="pwcResponsibilityUnderList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="pwcResponsibilityUnderHeadingTree">
			<div class="page_box_content accordion-inner">
				<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
					<thead>
						<tr>
						    
						    <td class="col-md-2"><input type="checkbox" ng-model="selectedAllpmo" ng-click="checkAll()" /></td>
							<td class="col-md-2">Work Stream</td>
							<td class="col-md-3">ProtoType</td>
							<td class="col-md-5">Activity</td>
							<td class="col-md-5">BU Factor</td>
							<td class="col-md-2" style="width:120px;">PwC Responsibility</td>
							
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in itemspmo">
						    <td><input type="checkbox" ng-model="item.selected" required/></td>
							<td >{{item.workstream | filterById:workstreamlst:'code'}}</td>
							<td >{{item.prototype | filterById:prototypelst:'code'}}</td>
							<td ng-bind="item.activity"></td>
							<td ng-bind="item.bu_dependency"></td>
							<td ng-bind="item.is_pwc_responsibility_flag"></td>
<!-- 							<td><select class="form-control ng-pristine ng-valid ng-touched" ng-model="item.is_pwc_responsibility_flag"> -->
<!-- 									<option ng-repeat="x in YN" ng-selected="item.is_pwc_responsibility_flag==x">{{x}}</option> -->
<!-- 							</select></td> -->
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
	
	<div class="panel-heading panel-save">
	 <div>
	    <b>Please Select to save the Form: <b>
	    <select id="checkpwcResponsibility" ng-model="checkpwcResponsibility" name ="checkpwcResponsibility">
	                       <option>--- Select Pwc Responsibility Y/N ---</option>
	                      <option value="No" selected>No</option>
					      <option value="Yes">Yes</option>
					      
		 </select>
		
		<button class="btn btn-primary btn-save function-button" ng-click="save(checkpwcResponsibility)">
			<span >save</span>
		</button>
		</div>
	</div>
	
</div>
<script>
	estimatorApp.controller('PwcResponsibilityMainController', [
			'$scope',
			'$http',
			'$rootScope',
			function($scope, $http, $rootScope) {
				$rootScope.init = function() {
					$("#zhezhao").showLoading();
					
					var buss_unit = $rootScope.projectDetails.business_units_number;
					var idparam = $rootScope.currentProjectId +"-"+$rootScope.projectDetails.version+"-"+buss_unit;
					
					$http({	
						url : "PwcResponsibility/findListFromMasterAdmin",
						responseType: "json",
						method:'GET',
						params : {
							
						}
					}).success(function(response) {
						
						$rootScope.workstreamlst = response.workstreamList;
						$rootScope.prototypelst = response.prototypeList;
						
						console.log($rootScope.workstreamlst);
						console.log($rootScope.prototypelst);
						
			}).error(function() {
				toastr.error("Errors occurred while retrieving data. Please try again later.").css("width","600px");
			});
					
					
					
						$http({	
									url : "PwcResponsibility/findpwcresponsibilityforother",
									responseType: "json",
									method:'GET',
									params : {
										idparam : idparam
									}
								}).success(function(response) {
									
									$rootScope.itemsother = response;
									
						}).error(function() {
							toastr.error("Errors occurred while saving PwC Responsibility information. Please try again later.").css("width","600px");
						});
					  
						$http({
								url : "PwcResponsibility/findpwcresponsibilityforpmo",
								responseType: "json",
								method:'GET',
								params : {
									idparam : idparam
								}
								
							}).success(function(response) {
								$rootScope.itemspmo = response;
								console.log("loaded");
								$("#zhezhao").hideLoading();
								
					}).error(function() {
						toastr.error("Errors occurred while saving PwC Responsibility information. Please try again later.").css("width","600px");
					});
					
				}
			} ]);
	estimatorApp
			.controller(
					'responsibilityCtrl',
					[
'$scope',
'$http',
'$timeout',
'$rootScope',
function($scope, $http,$timeout, $rootScope) {
	
	
	$scope.selectedAllpmo = false;
	$scope.selectedAllOthers = false;
	$scope.YN = [ "Yes", "No" ];
	
	$scope.checkAll = function(){
		if(!$scope.selectedAllpmo){
			$scope.selectedAllpmo ==true;
		}else{
			$scope.selectedAllpmo ==false;
		}
		
		if(!$scope.selectedAllOthers){
			$scope.selectedAllOthers ==true;
		}else{
			$scope.selectedAllOthers ==false;
		}
		
		angular.forEach($scope.itemspmo,function(items){
			items.selected = $scope.selectedAllpmo;
		});
		angular.forEach($scope.itemsother,function(itemoth){
			itemoth.selected = $scope.selectedAllOthers;
		})
		
	}

	
	$scope.save=function(checkstatusY_N){
		 var checkedPwcResponsibilityObj=[];
		 //var uncheckedPwcResponsibility=[];
		 
		 angular.forEach($scope.itemspmo,function(selected){
            	
        	 if(!selected.selected){
        		 //uncheckedPwcResponsibility.push(selected);
                }else{
                	checkedPwcResponsibilityObj.push(selected);
                }
			
		});
		angular.forEach($scope.itemsother,function(selected){
			 if(!selected.selected){
                }else{
                	checkedPwcResponsibilityObj.push(selected);
                }
		});
		
		console.log(checkedPwcResponsibilityObj);
        
		  var idparam = $rootScope.currentProjectId +"-"+$rootScope.projectDetails.version+"-"+$rootScope.projectDetails.business_units_number; 
          
       	   var data = JSON
					.stringify(checkedPwcResponsibilityObj);
       	   console.log(data);
       	
       	   
       		   console.log("m in")
			$http(
					{
						method : "POST",
						url : "PwcResponsibility/update",
						params : {
							data : data,
							checkstatusY_N:checkstatusY_N,
							idparam:idparam
						},
						headers : {
							'Content-Type' : 'application/json'
						}
					}).success(function(response) {
						  	
							toastr.success(response.msg);
							$scope.selectedAllpmo = false;
							$scope.selectedAllOthers = false;
							//$scope.checkpwcResponsibility ="";
							$rootScope.init();
							$http.post(
								'project/enagagement/saveProgress/'+$rootScope.currentProjectId,
								{
									level:2,
									engagement_id:$rootScope.currentProjectId
								}
							).success(function(data){
							var menuEles = angular.element(document.querySelectorAll('.main-menu-items.level-'+2)).removeClass('main-menu-invisible');
							//angular.element("[id^=Portal]").addClass("hide");
							$timeout(function(){$("#CSPRate").trigger("click");$("body").animate({ scrollTop: "0px" }); });
							
							}).error(function(){
								
							});
												
			}).error(function() {
				toastr.error("Errors occurred while saving PwC Responsibility information. Please try again later.").css("width","600px");
			});
       	   
	}    
			
		
		
           
	
// 		$scope.save = function() {
// 			var data = JSON
// 					.stringify($rootScope.itemsother
// 							.concat($rootScope.itemspmo));
// 			$http(
// 					{
// 						method : "POST",
// 						url : "PwcResponsibility/update?id="+ currentProjectId,
// 						params : {
// 							data : data
// 						},
// 						headers : {
// 							'Content-Type' : 'application/x-www-form-urlencoded'
// 						}
// 					}).success(function(msg) {
// 						if(msg=="\"success\""){
// 							toastr.success("PwC Responsibility information has been saved successfully.").css("width","600px");
// 						}else{
// 							toastr.error("Errors occurred while saving PwC Responsibility information. Please try again later.").css("width","600px");
// 						};
// 			}).error(function() {
// 				toastr.error("Errors occurred while saving PwC Responsibility information. Please try again later.").css("width","600px");
// 			});
// 		};

	$scope.changedata = function(value) {
		if ($rootScope.itemspmo.length > 0) {
			for (var i = 0; i < $rootScope.itemspmo.length; i++) {
				console.log($rootScope.itemspmo[i].workstream);
				if ($rootScope.itemspmo[i].workstream == 6 || $rootScope.itemspmo[i].workstream == 7) {
					$rootScope.itemspmo[i].is_pwc_responsibility_flag = value;
				}
			}
		}
	}

} ]).filter('filterById', function() { 
	return function(id,currObj,retKey){
    	
    	try{
    		var arrayObjs = currObj;
    		for(var i = 0;i<=arrayObjs.length;i++){

    		if(parseInt(id) == arrayObjs[i].id){
    		return arrayObjs[i][retKey];
    		}

    		}
    		}catch(e){}   		
    }
});;
</script>