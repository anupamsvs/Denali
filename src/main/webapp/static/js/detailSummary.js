(function(){
	estimatorApp.controller("detailSummaryController",['$timeout','$scope','$rootScope','$http','$filter',function($timeout,$scope,$rootScope,$http,$filter){
		$rootScope.allSummary = {};
		$scope.data = {};
		$rootScope.summaryTotal = {};
		$scope.twoDecimal  = function(twoDecimal){
			return parseFloat(twoDecimal).toFixed(2);
		}
		

		$scope.saveOverride = function(){
			$("#zhezhao").showLoading();		
			console.log($scope.data);
			$http.post( 'summary/detailed/'+$rootScope.currentProjectId+'/save',$scope.data).success(function(data){
        		$rootScope.allSummary = data;
        		$("#zhezhao").hideLoading();
        		toastr.success("Updated successfully.");
        	}).error(function(data){
        		if(status == 403){ 	location.href = "../login" }
        		$("#zhezhao").hideLoading();	
        		toastr.error("Failed to save, Please try again later.");
        		$scope.isSaveSuccess = false;
        	});
		}
		
		$scope.marginMe = function(a,b){
			return Math.round(a)-Math.round(b);
		}
		
		$scope.initSummary  = function(){
			$rootScope.allSummary = {};
			$scope.data = {};
			$rootScope.summaryTotal = {};
			$rootScope.detailEffortOnsite = {};
			$rootScope.detailEffortDdc = {};
			
			$rootScope.detailEffortByColumnOnsite = {};
			$rootScope.detailEffortByColumnDdc = {};
			
			$rootScope.totalEffortByColumnOnsite = {};
			$rootScope.totalEffortByColumnDdc = {};
			
			$rootScope.totalHrEffortByColumnOnsite = {};
			$rootScope.totalHrEffortByColumnDdc = {};
			$("#zhezhao").showLoading();		
			//Get Data here
			$http({
        		url : 'summary/detailed/'+$rootScope.currentProjectId,	
        		responseType: "json",
				method:'GET'
        	}).success(function(data){
        		$("#zhezhao").hideLoading();		
        		$rootScope.allSummary = data;
        		$timeout(function(){$("#detail-summary").tableHeadFixer()});
        	}).error(function(data){
        		if(status == 403){ 	location.href = "../login" }
        		$("#zhezhao").hideLoading();		
        		toastr.error("Failed to load, Please try again later.");
        		$scope.isSaveSuccess = false;
        	});
		}
		
	}]);
	
	estimatorApp.controller("detailEffortSummaryController",['$scope','$rootScope','$http','$filter',function($scope,$rootScope,$http,$filter){
		function getByValue(arr, value) {

			  for (var i=0, iLen=arr.length; i<iLen; i++) {

			    if (arr[i].idkey == value) return arr[i];
			  }
			}
		$rootScope.trackMe  = function(id,type,workstream){
			value = getByValue($rootScope.allEffortSummary.list[workstream+""],id);
			if(value){
			return parseFloat(value[type]).toFixed();
			}else{
				return 0.00;
			}
		}
		$rootScope.detailEffortOnsite = {};
		$rootScope.detailEffortDdc = {};
		
		$rootScope.detailEffortByColumnOnsite = {};
		$rootScope.detailEffortByColumnDdc = {};
		
		$rootScope.totalEffortByColumnOnsite = {};
		$rootScope.totalEffortByColumnDdc = {};
		
		$rootScope.totalHrEffortByColumnOnsite = {};
		$rootScope.totalHrEffortByColumnDdc = {};
		
		
		$rootScope.sumMe = function(a,b,c){
			var x = isNaN(parseFloat(a))?0:a;
			var y = isNaN(parseFloat(b))?0:b;
			var z = parseFloat(x)+parseFloat(y);
			console.log(x+"-"+y+"-"+"-",z);
			return z.toFixed(2);
		}
		$scope.initSummary  = function(){
			$rootScope.detailEffortOnsite = {};
			$rootScope.detailEffortDdc = {};
			
			$rootScope.detailEffortByColumnOnsite = {};
			$rootScope.detailEffortByColumnDdc = {};
			
			$rootScope.totalEffortByColumnOnsite = {};
			$rootScope.totalEffortByColumnDdc = {};
			
			$rootScope.totalHrEffortByColumnOnsite = {};
			$rootScope.totalHrEffortByColumnDdc = {};
			$("#zhezhao").showLoading();		
			//Get Data here
			$http({
        		url : 'summary/detailed/effort/'+$rootScope.currentProjectId,	
        		responseType: "json",
				method:'GET'
        	}).success(function(data){
        		$("#zhezhao").hideLoading();		
        		$rootScope.allEffortSummary = data;
        	}).error(function(data){
        		if(status == 403){ 	location.href = "../login" }
        		$("#zhezhao").hideLoading();		
        		toastr.error("Failed to load, Please try again later.");
        		$scope.isSaveSuccess = false;
        	});
		}
		
	}]);
//Close iffy
})();