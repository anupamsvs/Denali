estimatorApp.controller('cspRatesSetupController', ['$rootScope','$http', function($rootScope, $http){
	
	
	var cspDataOnsite =[];
	var cspDataOffsite =[];
	var rspDataOnsite =[];
	var rspDataOffsite =[];
	
	$rootScope.create_dt= "";
	$rootScope.ratesLoaded=false;
	$rootScope.status=false;
	var editableCellTemplate = '<div><form name="inputForm"><input type="number" pattern="[0-9]+"  min="0" step="1" ng-class="\'colt\' + col.uid" ui-grid-editor ng-model="MODEL_COL_FIELD"></form></div>' 
	    
$rootScope.loadRates= function(version){
	 cspDataOnsite.length = 0;
	 cspDataOffsite.length = 0;
	 rspDataOnsite.length = 0;
	 rspDataOffsite.length = 0;
	 var idparam = $rootScope.currentProjectId +"-"+$rootScope.projectDetails.version;
$('#zhezhao').showLoading();


				$http({
						url : "../admin/loadOnSite",
								responseType: "json",
								method:'GET',
								params : {
									version : version,
									idparam :idparam
								}
								
							}).success(function(data){
	$('#zhezhao').hideLoading();
	//$rootScope.globalExceptionHandler(data.CODE);
	
	var dataonsit = Object.keys(data.allOnSite[0]);
	var dataoffsite =Object.keys(data.allOffSite[0]);
	var dataonsitRsp =Object.keys(data.onsiteRsp[0]);
	var dataoffsiteRsp =Object.keys(data.offsiteRsp[0]);
	//var version_date_data =data.versionList;

for(var i=0 ;i<dataonsit.length;i++){
		

		if(dataonsit[i] == "id"){
			continue;
		}
		if(dataonsit[i] == "Workstream"){
			cspDataOnsite.unshift({
    			name: dataonsit[i],
    			field: dataonsit[i],
    			enableCellEdit: false,
    			editableCellTemplate:editableCellTemplate,
    			 type: 'number',
    			minWidth: 80,
    			enablePagination:false,enablePaginationControls:false
    	    });
		}else{
			cspDataOnsite.push({
    			name: dataonsit[i],
    			field: dataonsit[i],
    			editableCellTemplate:editableCellTemplate,
    			minWidth: 80,
    			 type: 'number',
    			enablePagination:false,enablePaginationControls:false
    	    });
		}
		
	}
	for(var i=0 ;i<dataoffsite.length;i++){
			

		if(dataoffsite[i] == "id"){
			continue;
		}
		if(dataoffsite[i] == "Workstream"){
			continue;
		}
			
		cspDataOffsite.push({
			name: dataoffsite[i],
			field: dataoffsite[i],
			editableCellTemplate:editableCellTemplate,
			minWidth: 80,
			 type: 'number',
			enablePagination:false,enablePaginationControls:false
	    });
//    		if(dataoffsite[i] == "Workstream"){
//    			cspDataOffsite.unshift({
//    				name: dataoffsite[i],
//    				field: dataoffsite[i],
//    				minWidth: 150,
//    				enableCellEdit: false,
//        	    });
//    		}else{
//    			
//    		}
			
		}
	

	 for(var i=0 ;i<dataonsitRsp.length;i++){
		  

 		if(dataonsitRsp[i] == "id"){
 			continue;
 		}
 		
	    	if(dataonsitRsp[i] == "Workstream"){
	    		rspDataOnsite.unshift({
	    			name: dataonsitRsp[i],
	    			field: dataonsitRsp[i],
					enableCellEdit: false,
					minWidth: 80,
					 type: 'number',
					editableCellTemplate:editableCellTemplate,
					enablePagination:false,enablePaginationControls:false
	    	    });
			}else{
				rspDataOnsite.push({
					name: dataonsitRsp[i],
	    			field: dataonsitRsp[i],
	    			editableCellTemplate:editableCellTemplate,
	    			minWidth: 60,
	    			 type: 'number',
	    			enablePagination:false,enablePaginationControls:false
	    	    });
			}
	    	}
	    	for(var i=0 ;i<dataoffsiteRsp.length;i++){
	    		
	    		
	    		if(dataoffsiteRsp[i] == "id"){
	    			continue;
	    		}
	    		
	    		
	    		if(dataoffsiteRsp[i] == "Workstream"){
	    			continue;
//	    			
//	    			rspDataOffsite.unshift({
//	        			name: dataoffsiteRsp[i],
//	    				field: dataoffsiteRsp[i],
//	    				enableCellEdit: false,
//	    				minWidth: 150
//	        	    });
	    		}
	    			rspDataOffsite.push({
	    				name: dataoffsiteRsp[i],
	    				field: dataoffsiteRsp[i],
	        			editableCellTemplate:editableCellTemplate,
	        			minWidth: 60,
	        			enablePagination:false,enablePaginationControls:false
	        	    });
	    		}
	    			
	    		
	
	

	$rootScope.gridAdminOnsiteRatesOptions.data = data.allOnSite;
	$rootScope.gridAdminOffsiteRatesOptions.data = data.allOffSite;
	$rootScope.gridAdminOnsiteRatesOptionsRSP.data = data.onsiteRsp;
	$rootScope.gridAdminOffsiteRatesOptionsRSP.data = data.offsiteRsp;
	$rootScope.versionlist = data.versionlist; 
	if(data.create_dt =="null" ||data.create_dt =='')
	{
		$rootScope.create_dt ="none"}
	else{
		
		$rootScope.create_dt=new Date(data.create_dt.split("**")[0]);
		$rootScope.version_rates=data.create_dt.split("**")[1];
		
	}

//	toastr.success("Data loaded successfully.");
	$(window).trigger('resize');
	$rootScope.ratesLoaded = true;
	
	if(version !=""){
		toastr.success("Version "+version+" data successfully loaded.").css("width","600px");
	}
}).error(function(data){
	toastr.error("Errors occurred while loading onsite rate information. Please try again later.").css("width","600px");
});
}

var updateDataOffsiteCspRate ={};
var updateDataOnsiteCspRate ={};
var updateDataOffsiteRspRate ={};
var updateDataOnsiteRspRate ={};



	
	  $rootScope.gridAdminOffsiteRatesOptions = {
		        enableSorting: true,
		        enableCellEditOnFocus: true,
		        paginationPageSizes: [10, 25, 50, 75],
		        paginationPageSize: 10,
		        rowEditWaitInterval: 4,
		        enableFiltering: false,
		        rowHeight:45,
		        minRowsToShow:8,
		        columnDefs: cspDataOffsite,
		    };
	  
	  $rootScope.gridAdminOnsiteRatesOptions = {
		        enableSorting: true,
		        enableCellEditOnFocus: true,
		        paginationPageSizes: [10, 25, 50, 75],
		        paginationPageSize: 10,
		        rowEditWaitInterval: 4,
		        enableFiltering: false,
		        rowHeight:45,
		        minRowsToShow:8,
		        columnDefs: cspDataOnsite,
		    };
	  $rootScope.gridAdminOffsiteRatesOptionsRSP = {
		        enableSorting: true,
		        enableCellEditOnFocus: true,
		        paginationPageSizes: [10, 25, 50, 75],
		        paginationPageSize: 10,
		        rowEditWaitInterval: 4,
		        enableFiltering: false,
		        rowHeight:45,
		        minRowsToShow:8,
		        columnDefs: rspDataOffsite,
		    };
	  
	  $rootScope.gridAdminOnsiteRatesOptionsRSP = {
		        enableSorting: true,
		        enableCellEditOnFocus: true,
		        paginationPageSizes: [10, 25, 50, 75],
		        paginationPageSize: 10,
		        rowEditWaitInterval: 4,
		        enableFiltering: false,
		        rowHeight:45,
		        minRowsToShow:8,
		        columnDefs: rspDataOnsite,
		    };

		$rootScope.gridAdminOnsiteRatesOptions.data = [];
	    $rootScope.gridAdminOffsiteRatesOptions.data = [];
	    $rootScope.gridAdminOnsiteRatesOptionsRSP.data = [];
	    $rootScope.gridAdminOffsiteRatesOptionsRSP.data = [];
	    
	    
	    $rootScope.gridAdminOffsiteRatesOptions.onRegisterApi = function (gridApi) {
	        $rootScope.gridApi = gridApi;
	        gridApi.edit.on.afterCellEdit($rootScope, function (rowEntity, colDef, newValue, oldValue) {
	        	var id =rowEntity.id;
	        	updateDataOffsiteCspRate[id] =rowEntity;
	        	//console.log(updateDataOffsiteCspRate);
	        });
	    };

	    $rootScope.gridAdminOnsiteRatesOptions.onRegisterApi = function (gridApi) {
	        $rootScope.gridApi = gridApi;
	        gridApi.edit.on.afterCellEdit($rootScope, function (rowEntity, colDef, newValue, oldValue) {
	        	var id =rowEntity.id;
	        	updateDataOnsiteCspRate[id] =rowEntity;
	        	//console.log(updateDataOnsiteCspRate);
	        	
	        });
	    };   
	    
	    $rootScope.gridAdminOffsiteRatesOptionsRSP.onRegisterApi = function (gridApi) {
	        $rootScope.gridApi = gridApi;
	        gridApi.edit.on.afterCellEdit($rootScope, function (rowEntity, colDef, newValue, oldValue) {
	        	var id =rowEntity.id;
	        	updateDataOffsiteRspRate[id] =rowEntity;
	        	//console.log(updateDataOffsiteRspRate);
	        });
	    };

	    $rootScope.gridAdminOnsiteRatesOptionsRSP.onRegisterApi = function (gridApi) {
	        $rootScope.gridApi = gridApi;
	        gridApi.edit.on.afterCellEdit($rootScope, function (rowEntity, colDef, newValue, oldValue) {
	        	var id =rowEntity.id;
	        	updateDataOnsiteRspRate[id] =rowEntity;
	        	//console.log(updateDataOnsiteRspRate);
	        	
	        });
	    };
	$rootScope.saveRate = function(){
		var savetype ="";
		if($rootScope.status ==false){
			savetype="save";
		}else if($rootScope.status ==true){
			savetype="version";
		}
		console.log(savetype +"++"+$rootScope.status);
		var idparam = $rootScope.currentProjectId +"-"+$rootScope.projectDetails.version;
		
		console.log(updateDataOnsiteCspRate);
		console.log(updateDataOffsiteCspRate);
		console.log(updateDataOffsiteRspRate);
		console.log(updateDataOnsiteRspRate);
		console.log(savetype);
			
			$http({
	            url: '../admin/saveRate',
	            responseType: 'json',
	            method: 'POST',
	            params: {
	                onsiteDataCSP:updateDataOnsiteCspRate ,
	                offsitedataCsp:updateDataOffsiteCspRate ,
	                offsitedataRsp:updateDataOffsiteRspRate,
	                onsiteDataRSP:updateDataOnsiteRspRate,
	                idparam :idparam,
	                savetype:savetype
	            }
	        }).success(function (data) {
	            debugger;
	            $("#zhezhao").hideLoading();
	            if(savetype =='save'){
	            toastr.success("Saved successfully");
	            $rootScope.loadRates("");
	            }else if(savetype =='version'){
	            toastr.success("New version created successfully");
	            $rootScope.loadRates("");
	            }

	            
	        }).error(function (data,status) {
	            toastr.error("Something went wrong, Please try again!");
	            $("#zhezhao").hideLoading();
	        });
			
		
		
	};
	
	
	
	
	
	
	$rootScope.refreshRate =function(){
		$('#zhezhao').showLoading();
		$http({
	        url: '../admin/refreshAdminRates',
	        responseType: 'json',
	        method: 'GET',
	        params: {
	          
	        }
	    }).success(function (data) {
	        debugger;
	        console.log(data);
	        $("#zhezhao").hideLoading();
	        var dataonsit = Object.keys(data.allOnSite[0]);
	    	var dataoffsite =Object.keys(data.allOffSite[0]);
	    	var dataonsitRsp =Object.keys(data.onsiteRsp[0]);
	    	var dataoffsiteRsp =Object.keys(data.offsiteRsp[0]);
	    	
	    	for(var i=0 ;i<dataonsit.length;i++){
	    		if(dataonsit[i] == "id"){
	    			continue;
	    		}
	    		
	    		if(dataonsit[i] == "Workstream"){
	    			cspDataOnsite.unshift({
	        			name: dataonsit[i],
	        			field: dataonsit[i],
	        			minWidth: 150,
	    				enableCellEdit: false
	        	    });
	    		}else{
	    			cspDataOnsite.push({
	        			name: dataonsit[i],
	        			field: dataonsit[i],
	        			minWidth: 150,
	        			editableCellTemplate:editableCellTemplate,
	        	    });
	    		}
	    		
	    	}
	    	for(var i=0 ;i<dataoffsite.length;i++){

	    		if(dataoffsite[i] == "id"){
	    			continue;
	    		}
	    			
	    		if(dataoffsite[i] == "Workstream"){
	    			continue;
	    		}
	    			
	    		cspDataOffsite.push({
					name: dataoffsite[i],
					field: dataoffsite[i],
					editableCellTemplate:editableCellTemplate,
					minWidth: 150
			    });
//	        		if(dataoffsite[i] == "Workstream"){
//	        			cspDataOffsite.unshift({
//	        				name: dataoffsite[i],
//	        				field: dataoffsite[i],
//	        				minWidth: 150,
//	        				enableCellEdit: false
//	            	    });
//	        		}else{
//	        			
//	        		}
	    			
	    		}
	    	
	    	
	    	
	    	
	    for(var i=0 ;i<dataonsitRsp.length;i++){
	    	

			if(dataonsitRsp[i] == "id"){
				continue;
			}
	    		
	    	if(dataonsitRsp[i] == "Workstream"){
	    		rspDataOnsite.unshift({
	    			name: dataonsitRsp[i],
	    			field: dataonsitRsp[i],
					enableCellEdit: false,
					minWidth: 150
	    	    });
			}else{
				rspDataOnsite.push({
					name: dataonsitRsp[i],
	    			field: dataonsitRsp[i],
	    			editableCellTemplate:editableCellTemplate,
	    			minWidth: 150
	    	    });
			}
	    	}
	    	for(var i=0 ;i<dataoffsiteRsp.length;i++){

	    		if(dataoffsiteRsp[i] == "id"){
	    			continue;
	    		}
	    		if(dataoffsiteRsp[i] == "Workstream"){
	    			continue;
	    		}
	    		
	    		rspDataOffsite.push({
					name: dataoffsiteRsp[i],
					field: dataoffsiteRsp[i],
					minWidth: 150,
	    			editableCellTemplate:editableCellTemplate,
	    	    });
	    		
//	    		if(dataoffsiteRsp[i] == "Workstream"){
//	    			
//	    			
//	    		}else{
//	    			rspDataOffsite.push({
//	    				name: dataoffsiteRsp[i],
//	    				field: dataoffsiteRsp[i],
//	    				minWidth: 150,
//	        			editableCellTemplate:editableCellTemplate,
//	        	    });
//	    		}
	    			
	    		}
	    	
//	    	console.log(cspDataOffsite);
//	    	console.log(cspDataOnsite);
//	    	console.log(rspDataOnsite);
//	    	console.log(rspDataOffsite);
	    	$rootScope.gridAdminOnsiteRatesOptions.data = data.allOnSite;
	    	$rootScope.gridAdminOffsiteRatesOptions.data = data.allOffSite;
	    	$rootScope.gridAdminOnsiteRatesOptionsRSP.data = data.onsiteRsp;
	    	$rootScope.gridAdminOffsiteRatesOptionsRSP.data = data.offsiteRsp;
	    	
	        toastr.success("Latest rates fetched from admin.Please save the data to use the rates");
	        
	        
	        
	    }).error(function (data) {
	        toastr.error("Something went wrong, Please try again!");
	        $("#zhezhao").hideLoading();
	    });
		
	}

	
}]);