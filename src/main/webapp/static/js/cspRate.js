(function(){
	
	
	estimatorApp.controller("cspRateInitialController",['$scope','$rootScope','$http','$filter',function($scope,$rootScope,$http,$filter){
		$scope.initialCSPRate = function(){
			$("#zhezhao").showLoading();
			var currentProjectId = $rootScope.currentProjectId;
			$http({
				url:'rate/initialData',
				responseType: "json",
				method:'GET',
				params:{
					engagementId:currentProjectId
				}
			}).success(function(data){
				$("#zhezhao").hideLoading();
				debugger;
				$rootScope.onsiteData = data.onsiteCspRateTransactionDataList;
				$rootScope.offshoreData = data.offshoreCspRateTransactiondataList;
			}).error(function(data){
//				bootbox({
//					size : 'small',
//					message : 'Initial csp rate error!'
//				});
				toastr.error("Errors occurred while initial Csp rate. Please try again later.").css("width","600px");
			});
		}
	
	}]);
	
	estimatorApp.controller("engagementCSPRateController",['$scope','$rootScope','$http','$filter',function($scope,$rootScope,$http,$filter){
		//$scope.onsiteDataList = $rootScope.onsiteData;
		
		$scope.isSaveSuccess = true;
		$scope.saveCspRate = function(t){
			debugger;
			var onsiteDataList = t.onsiteData;
			var offshoreDataList = t.offshoreData;
			for(var i = 0; i < onsiteDataList.length; i++){
				$http({ 
					url:'rate/saveOnsiteRate',
					responseType: "json",
					method:'POST',
					params:{
						id : onsiteDataList[i].id,
						work_stream_id:onsiteDataList[i].work_stream_id,
						engagement_id:onsiteDataList[i].engagement_id,
						partner_cost:onsiteDataList[i].partner_cost,
						director_cost:onsiteDataList[i].director_cost,
						manager_cost:onsiteDataList[i].manager_cost,
						senior_associate_cost:onsiteDataList[i].senior_associate_cost,
						associate_cost:onsiteDataList[i].associate_cost,
						update_date:onsiteDataList[i].update_date,
						version:onsiteDataList[i].version
					}
				}).error(function(data){
					$scope.isSaveSuccess = false;
				});
			}
			
			for(var i = 0; i < offshoreDataList.length; i++){
				$http({
					url:'rate/saveOffshoreRate',
					responseType: "json",
					method:'POST',
					params:{
						id : offshoreDataList[i].id,
						work_stream_id:offshoreDataList[i].work_stream_id,
						engagement_id:offshoreDataList[i].engagement_id,
						offshore_landed_cost:offshoreDataList[i].offshore_landed_cost,
						manager_cost:offshoreDataList[i].manager_cost,
						staff_cost:offshoreDataList[i].staff_cost,
						update_date:offshoreDataList[i].update_date,
						version:offshoreDataList[i].version
					}
				}).error(function(data){
					$scope.isSaveSuccess = false;
				});
			}
			
			if($scope.isSaveSuccess){
				
//				bootbox.alert({
//					size : 'small',
//            		message : 'Save Success!'
//				});
				toastr.success("Csp Rate has been saved successfully.").css("width","600px");
			}else{
//				bootbox.alert({
//					size : 'small',
//            		message : 'Save Error!'				
//				});
				toastr.error(" Errors occurred while saving Csp rate. Please try again later.").css("width","600px");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}]);
})();