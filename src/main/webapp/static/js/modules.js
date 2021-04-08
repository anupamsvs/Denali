(function(){
	estimatorApp.controller("modulesController",['$timeout','$scope','$rootScope','$http','$filter',function($timeout,$scope,$rootScope,$http,$filter){
		$scope.totalModulesInScope = 0;

		$rootScope.module_totals = {};
		
		$scope.reduceMe = function(arr,key){
			return arr.reduce(function(map, obj) {
			    map[obj[key]] = obj;
			    return map;
			}, {});
			
		}
		  //Initiate Grid
		$scope.gridModule = {
	        enableSorting: true,
	        paginationPageSizes: [10, 25, 50, 75],
	        paginationPageSize: 10,
	        enableFiltering: true,
	        rowHeight:45,
	        columnDefs: [],
	    };
		$scope.gridModule.onRegisterApi = function (gridModuleApi) {
	        $rootScope.gridModuleApi = gridModuleApi;
	        gridModuleApi.edit.on.afterCellEdit($rootScope, function (rowEntity, colDef, newValue, oldValue) {
	            //No need to save if not changed
	        	if (newValue != oldValue) {
	        		$scope.saveModules([rowEntity]);
	            }
	        });
	    };

	    $scope.gridModule.data = [];
	    
	    
	    
	    $scope.initModules = function(){
			$("#zhezhao").showLoading();
			var currentProjectId = $rootScope.currentProjectId;
			$http({
				url : 'modules/listEngagementModules',
				responseType: "json",
				method:'GET',
				params:{
					engagementId:currentProjectId
				}
			}).success(function(rootData){
				debugger;
				
				$rootScope.totalModulesInScope = rootData.totalModulesInScope;
				/* $http({
						url : 'modules/listActivities',
						responseType: "json",
						method:'GET',
						params:{}
					}).success(function(data){
						debugger;
						//console.log(data);
//						var activities = data.activityList;
						var mydataList = [
						 { name: 'Cloud Offering',pinnedLeft:true,enableCellEdit:false, field: 'cloudOffering',width:"130"},
	                     { name: 'Business Process',pinnedLeft:true,enableCellEdit:false, field: 'businessProcessName',width:"130"},
	                     { name: 'Module', field: 'module_name',pinnedLeft:true,enableCellEdit:false,width:"130"},
	                     { name: 'Abbreviation', field: 'module_abbreviation',pinnedLeft:true,enableCellEdit:false,width:"80"},
	                     { name: 'In Scope', cellClass: 'editable',pinnedLeft:true,field: 'is_in_scope_flag',width:"100",enableFiltering: false,enableCellEdit  :true,cellEditableCondition:true,editableCellTemplate: 'ui-grid/dropdownEditor',editDropdownIdLabel: 'id',editDropdownValueLabel: 'code', editDropdownOptionsArray:  [{id:"Yes",code:"Yes"},{id:"No",code:"No"}]},
	                     { name: 'Complexity Factor',cellClass: 'editable', field: 'complexity_factor',type:"number",width:"100",enableFiltering: false},
	                     { name: '# Of Requirement', cellClass: 'editable',field: 'number_of_requirements',type:"number" ,width:"100",enableFiltering: false},
	                     { name: '# Of Gaps',cellClass: 'editable', field: 'number_of_gaps',type:"number",width:"100",enableFiltering: false },
	                     { name: 'Duration Factor', field: 'module_duration_factor',enableCellEdit:false,width:"60",enableFiltering: false},
	                     { name: 'Weightage', field: 'moduleweightage',enableCellEdit:false,width:"80",enableFiltering: false},
	                     { name: 'DDC Supported', field: 'ddc_supported_flag',enableCellEdit:false,width:"80",enableFiltering: false,},
	                     { name: 'Job Aids', field: 'job_aid',width:"60",enableCellEdit:false,enableFiltering: false,enableFiltering: false },
	                     { name: 'Workshop', field: 'workshop',type:"number",width:"80",enableFiltering: false ,enableCellEdit:false},
	                            
	                     ];
//						for(var i=0;i<activities.length;i++){
//							myActivity = {"name":activities[i].activity,"field":"activity"+activities[i].id,headerCellTemplate:"<div class='custom-grid-header'>" +
//										  "<div class='main-header'>"+activities[i].activity+"</div>" +
//										  "<span class='sub-header'>Onsite Effort</span>" +
//										  "<span class='sub-header'>DDC Effort</span></div>" +
//										  "</div>",width:"15%","extra":activities[i],
//										  enableSorting: false,
//										  enableCellEdit:false,
//										  index:i,
//										  //cellTemplate: '<div ng-bind-html="COL_FIELD | trusted"></div>',
//									  cellFilter:'calculateHours:row.entity:this'
//										 };
//							mydataList.push(myActivity);
//						}
////						console.log($rootScope.gridModule.columnDefs);
						
					
//						$rootScope.gridModuleApi.core.refresh();
					}).error(function(){
						toastr.error("Errors occurred while initial modules. Please try again later.");
					});
					*/
				$rootScope.moduleList = rootData.transactionModuleList;
				$rootScope.activity_values = $scope.reduceMe(rootData.act_values,'id');
				$rootScope.activities = rootData.activities;
				$rootScope.module_totals = {};
				$("#zhezhao").hideLoading();
//				$(window).trigger('resize');
				$timeout(function(){$("#modulesTable").tableHeadFixer({'left' : 5,'head':true})});
			}).error(function(){
				toastr.error("Errors occurred while initial modules. Please try again later.");
			});
		};
		
		$scope.isSaveSuccess = true;
		
//		$scope.changed_index = [];
		$scope.saveModules = function(t){
			var changed_index = [];
			$('#modulesTable .ng-dirty').each(function(){
				changed_index.push(parseInt($(this).parent().parent().find("input[type='hidden']").val()));
			});
			console.log(changed_index);
//			return;
			debugger;
			var modulesList = t;
            for(var i = 0; i < changed_index.length; i++){
            	var param = $rootScope.moduleList[changed_index[i]];
            	param.update_date = new Date().toJSON().slice(0, 10);
            	$http({
            		url : 'modules/saveModules',
            		responseType: "json",
    				method:'POST',
    				params:	param
    				
            	}).success(function(data){
            		/*$scope.complete();*/
            		//alert("success");
            		toastr.success("Modules have been saved successfully");
            		$rootScope.module_totals = {};
            	}).error(function(data){
            		$scope.isSaveSuccess = false;
            	});
            }
            
            if($scope.isSaveSuccess){
            	
//            	bootbox.alert({
//            		size : 'small',
//            		message : 'Save Success!'
//            	});
            	$scope.initModules();
            	$rootScope.moduleList = null;
            	toastr.success("Modules have been saved successfully").css("width","600px");
            	
            }else{
//            	bootbox.alert({
//            		size : 'small',
//            		message : 'Save Error!'
//            	});
            	toastr.error("Errors occurred while saving modules. Please try again later.").css("width","600px");
            }
		};
	}]);
	
	estimatorApp.controller("engagementModulesController",['$scope','$rootScope','$http','$filter',function($scope,$rootScope,$http,$filter){
		$scope.YN = [ "Yes", "No" ];
		//$scope.engagementModules = $rootScope.modules;
		//$scope.totalModulesInScope = 0;
		$scope.calculateInScopeTotal = function(t){
			debugger;
			$rootScope.totalModulesInScope = 0;
			var moduleList = t.modules;
			for(var i = 0; i < moduleList.length; i++){
				if(moduleList[i].is_in_scope_flag == 'Yes'){
					$rootScope.totalModulesInScope++;
				}
			}
		};
		
		$scope.isSaveSuccess = true;
		$scope.saveModules = function(t){
			
			debugger;
			var modulesList = t;
            for(var i = 0; i < modulesList.length; i++){
            	modulesList[i].update_date = new Date().toJSON().slice(0, 10);
            	$http({
            		url : 'modules/saveModules',
            		responseType: "json",
    				method:'POST',
    				params:	modulesList[i]
    				
            	}).success(function(data){
            		/*$scope.complete();*/
            		//alert("success");
            		if(i == (modulesList.length -1)){
            		$scope.initModules();
            		}
            		toastr.success("Modules have been saved successfully");
            	}).error(function(data){
            		$scope.isSaveSuccess = false;
            	});
            }
            
            if($scope.isSaveSuccess){
            	
//            	bootbox.alert({
//            		size : 'small',
//            		message : 'Save Success!'
//            	});
            	$rootScope.module_totals = {};
            	toastr.success("Modules have been saved successfully").css("width","600px");
            }else{
//            	bootbox.alert({
//            		size : 'small',
//            		message : 'Save Error!'
//            	});
            	toastr.error("Errors occurred while saving modules. Please try again later.").css("width","600px");
            }
		}
	}]).filter('calculateHours',function($sce){
		 return function (input, context,key,column) {
			 var onSite = "";
//			 ddc = context.activity_ddc.split(',')[column];
			 onSite = context[key].split(',')[column];
			 if(onSite==undefined || onSite == ""){
				 return $sce.trustAsHtml("--");
			 }
			 return $sce.trustAsHtml(onSite);
		 }
	}).filter('YesNo', function(){
        return function(text){
            return text ? "Yes" : "No";
          }
        }).filter('trusted', function ($sce) {
        	  return function (value) {
        		    return $sce.trustAsHtml(value);
        		  }
        		});
	
	
})();