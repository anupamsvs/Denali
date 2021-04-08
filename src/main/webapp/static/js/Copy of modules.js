(function(){
	estimatorApp.controller("modulesController",['$scope','$rootScope','$http','$filter',function($scope,$rootScope,$http,$filter){
		$rootScope.totalModulesInScope = 0;
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
			}).success(function(data){
				debugger;
				$rootScope.modules = data.transactionModuleList;
				$rootScope.totalModulesInScope = data.totalModulesInScope;
				$("#zhezhao").hideLoading();
			}).error(function(){
//				bootbox.alert({
//					size : 'small',
//					message : 'Initial modules error!'
//				});
				toastr.error("Errors occurred while initial modules. Please try again later.").css("width","600px");
			});
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
			var data = JSON.stringify(modulesList);
//			$http({
//        		url : 'modules/saveModules',
//        		responseType: "json",
//				method:'POST',
//				params:{
//					data:data
//				}
//        	});
            for(var i = 0; i < modulesList.length; i++){
            	$http({
            		url : 'modules/saveModules',
            		responseType: "json",
    				method:'POST',
    				params:{
    					id:modulesList[i].id,
    					business_process_id:modulesList[i].business_process_id,
    					engagement_id:modulesList[i].engagement_id,
    					complexity_factor:modulesList[i].complexity_factor,
    					module_name:modulesList[i].module_name,
    					module_abbreviation:modulesList[i].module_abbreviation,
    					module_duration_factor:modulesList[i].module_duration_factor,
    					moduleweightage:modulesList[i].moduleweightage,
    					ddc_supported_flag:modulesList[i].ddc_supported_flag,
    					is_in_scope_flag:modulesList[i].is_in_scope_flag,
    					number_of_requirements:modulesList[i].number_of_requirements,
    					number_of_gaps:modulesList[i].number_of_gaps,
    					version:modulesList[i].version,
    					update_date:modulesList[i].update_date,
    					version:modulesList[i].version
    				}
            	}).success(function(data){
            		/*$scope.complete();*/
            		//alert("success");
            	}).error(function(data){
            		$scope.isSaveSuccess = false;
            	});
            }
            
            if($scope.isSaveSuccess){
            	
//            	bootbox.alert({
//            		size : 'small',
//            		message : 'Save Success!'
//            	});
            	toastr.success("Modules have been saved successfully").css("width","600px");
            }else{
//            	bootbox.alert({
//            		size : 'small',
//            		message : 'Save Error!'
//            	});
            	toastr.error("Errors occurred while saving modules. Please try again later.").css("width","600px");
            }
		}
	}]);
	
	
})();