estimatorApp.controller('SummaryEffortController', ['$timeout','$rootScope','$scope','$http', function($timeout,$rootScope,$scope, $http){

		
		$scope.initSummaryEfforts = function() {
			$("#zhezhao").showLoading();
			var currentProjectId = $rootScope.currentProjectId;
			
			$http
					.get(
							"SummaryEfforts/findSummaryEffortsData?engId="
									+ currentProjectId)
					.success(
							function(response) {
								$rootScope.workstreamLst =response.SummaryEffortForAddServices.workstream;
								$rootScope.resForAddServices = response.SummaryEffortForAddServices;
								$rootScope.totalAdditionalEfforts =$rootScope.resForAddServices.Total;
								
								$rootScope.resForProjectEfforts = response.SummaryProjectsEfforts;
								$rootScope.totalProjectEfforts =$rootScope.resForProjectEfforts.TotalProjectSumEffort;
								
								$rootScope.totalResForSumaaryEfforts = response.TotalSummaryEfforts;
								$rootScope.totalSummaryEfforts =$rootScope.totalResForSumaaryEfforts.TotalSummaryEfforts;
								
								$rootScope.protypeSplitList =$rootScope.totalSummaryEfforts.protypeSplitList;
								$rootScope.projectDurationPrototypSplit =$rootScope.totalSummaryEfforts.projectDurationPrototypSplit.mapProtypSplit;
								$rootScope.sumProtypsplit =$rootScope.totalSummaryEfforts.projectDurationPrototypSplit.sumProtypSplit;
						
								console.log($rootScope.projectDurationPrototypSplit);
								$("#summary-effort li:first-child").addClass('active');
								$("#zhezhao").hideLoading();		
							});

			

		}
		
		$scope.getDta =function(stream,array){
			//var data =$rootScope.array;
		
			return array[stream];
		}
		
		
		$rootScope.roundMe =function(data){
			if(data == undefined){
				return 0;
			}
			
//			if((data.indexOf(',') > -1)){
//				return Math.round(parseFloat((data.replace(",",""))));
//				}else{
//					
//					return  Math.round((parseFloat(data)));
//				}
			return Math.round(data);
			
		}
		
		
		$rootScope.roundMeToDec =function(data,num){
			if(data == undefined){
				return 0;
			}
//			if((data.indexOf(',') > -1)){
//				return parseFloat(data.replace(",","")).toFixed(num);
//				}else{
//					console.log(parseFloat(data).toFixed(num));
//					
//				}
			
			return parseFloat(data).toFixed(num);
		}
		
		$scope.getSumValue= function(stream,field,array){
			//var data =$rootScope.array;
			
			var stramDta= array[stream];
			for(var	i=0;i<stramDta.length;i++){
				
				if(field in stramDta[i]){
					
					return stramDta[i][field];
				}
				
			}
			
			
		}

}])