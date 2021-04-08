estimatorApp.controller('staffController',['$rootScope','$scope','$http',function($rootScope, $scope, $http) {
	
	$scope.ready = false;
	$scope.staffData = {};
//	$scope.staffData.ddc = {};
//	$scope.staffData.workstream = {};
//	
//	$scope.staffData.ddc.effortData = [];
//	$scope.staffData.workstream.effortData = [];
//	
//	$scope.staffData.extra = {};
//	$scope.staffData.extra.levels = [];
//	$scope.staffData.extra.workstream = [];

		// Init on page load
		$scope.reduceMe = function(arr,key){
			return arr.reduce(function(map, obj) {
			    map[obj[key]] = obj;
			    return map;
			}, {});
			
		}
		$scope.controlInside = {};
		$rootScope.loadStaffDrivers = function() {
			// get levels
			$scope.ready = false;
				var url = "../user/staff/efforts/"+$rootScope.currentProjectId;
				$http.get(url)
					  .success(
								function(data) {

									if (data.efforts != undefined) {
										
										/*$scope.staffData.ddc.effortData = data.efforts.ddc;
										$scope.staffData.workstream.effortData = data.efforts.workstream;
										$scope.staffData.extra.levels = data.extras_onsite.levels;
										$scope.staffData.extra.workstream = data.extras.workstream;*/
										var transformedData = data;
										
										transformedData.efforts.ddc = $scope.reduceMe(data.efforts.ddc,'id_couple');
										transformedData.efforts.workstream = $scope.reduceMe(data.efforts.workstream,'id_couple');
										
										transformedData.team.ddc = $scope.reduceMe(data.team.ddc,'id_couple');
										transformedData.team.workstream = $scope.reduceMe(data.team.workstream,'id_couple');
										
										transformedData.team_rates.rsr_onsite = $scope.reduceMe(data.team_rates.rsr_onsite,'id_couple');
										transformedData.team_rates.csp_onsite = $scope.reduceMe(data.team_rates.csp_onsite,'id_couple');
										transformedData.team_rates.csp_offsite = $scope.reduceMe(data.team_rates.csp_offsite,'id_couple');
										transformedData.team_rates.rsr_offsite = $scope.reduceMe(data.team_rates.rsr_offsite,'id_couple');
										transformedData.currentProjectId = $rootScope.currentProjectId;
//										console.log(transformedData);
										$scope.staffData = transformedData;
										$scope.ready  = true;
									}
								})
						.error(
								function(data) {
									$rootScope.errorLoadingBusinessProcess = true;
								});
			
			$(window).trigger('resize');
			setTimeout(
					function() {
						$(".shadow-tabs li:first-child")
								.addClass('active')
						$(".shadow-tab-container .tab-pane")
								.removeClass('active')
						$(
								".shadow-tab-container .tab-pane:first-child")
								.addClass('active')
					}, 100);
		}
		//Uses below directive to generate tables
}]).directive('staffDriver', function($timeout,$http) {
	return {
		restrict : 'E',
		scope : {
			data : '=data',
			type : '@',
			control: '='
		},
		transclude : true,
		link : function(scope, element, attrs) {
			scope.scopeeffort = {};
			scope.scopeTeam = {};
			scope.blendedRates = {};
			scope.teamValue = {};
			scope.effortValue = {};
			scope.internalControl = scope.control || {};
			scope.internalControl.callInit = function(){
				scope.initMe();
			} 
			//SaveDat
			scope.saveEfforts = function(dt) {
//				$('#body').showLoading();
				var paramData = {
						"team":scope.teamValue,
						"effort":scope.effortValue,
						"blended_rates":scope.blendedRates,
				}
		
				$http.post('../user/staff/saveAll/'+dt.currentProjectId, paramData)
						.success(function(data){
							toastr.success("Saved successfully.");
//							$('#body').hideLoading();
							})
						.error(function(data){console.log("err");console.log(data)
						toastr.error("Save failed, Please try again.");
//						$('#body').hideLoading();
						});
			};
			
			scope.cal_sum = function(row,id){
				var onsitesum = 0;
				var offsitesum = 0;
				
				//Calculate row onsite
				$("."+row+" ."+scope.type+"_onsite_row").each(function(){
					var currVal =  parseInt($(this).val());
					currVal = isNaN(currVal)?0:currVal;
					scope.teamValue[$(this).attr("data-teamid")] = currVal;
					onsitesum = onsitesum + currVal;
				});
				
				//Calculate row offsite
				$("."+row+" ."+scope.type+"_offsite_row").each(function(){
					var currVal =  parseInt($(this).val());
					currVal = isNaN(currVal)?0:currVal;
					
					scope.teamValue[$(this).attr("data-teamid")] = currVal;
					
					offsitesum = offsitesum + currVal;
				});
				
				
				$("."+row).attr('onsite-sum',onsitesum);
				$("."+row).attr('offsite-sum',offsitesum);
				
				var onsiteCspSum = 0;
				var onsiteRsrSum = 0;
				$("."+row+" ."+scope.type+"_onsite_row").each(function(){
					var curEle = $(this);
					
					//get current value
					var currVal =  parseInt(curEle.val());
					currVal = isNaN(currVal)?0:currVal;
					
					//get effort
					var workId  = curEle.attr('id').split("_")[2];
					var levelId  = curEle.attr('id').split("_")[3];
					
					var effort =  parseInt($("#"+scope.type+"_onsite_"+workId).val());
					//get effort
					effort = isNaN(effort)?1:effort;
					scope.effortValue[scope.type+'_onsite_'+workId]  = isNaN(effort)?null:effort;
					
					var per_sum = parseFloat(currVal/onsitesum*effort).toFixed(2);
					var myId  = curEle.attr('id');
					$("#percentage_"+myId).text(isNaN(per_sum)?0:per_sum+"%");
					var idWithouttype = myId.substr(myId.indexOf('_')+1);
					var cspRate = per_sum*scope.data.team_rates.csp_onsite[idWithouttype].rate;
					var rsrRate = per_sum*scope.data.team_rates.rsr_onsite[idWithouttype].rate;
					
					onsiteCspSum = onsiteCspSum + cspRate;
					onsiteRsrSum = onsiteRsrSum + rsrRate;
					
					$("#csp_"+myId).text(scope.data.team_rates.csp_onsite[idWithouttype].rate);
					$("#rsr_"+myId).text(scope.data.team_rates.rsr_onsite[idWithouttype].rate);
					$("#margin_"+myId).text(scope.data.team_rates.csp_onsite[idWithouttype].rate - scope.data.team_rates.rsr_onsite[idWithouttype].rate);
				});
//				scope.cspSum[row] = onsiteCspSum;
//				scope.rsrSum[row] = onsiteRsrSum;
				
				//Calculate offsite
				var offsiteCspSum = 0;
				var offsiteRsrSum = 0;
				$("."+row+" ."+scope.type+"_offsite_row").each(function(){
					var curEle = $(this);
					
					//get current value
					var currVal =  parseInt(curEle.val());
					currVal = isNaN(currVal)?0:currVal;
					
					//get effort
					var workId  = curEle.attr('id').split("_")[2];
					var levelId  = curEle.attr('id').split("_")[3];
					var effort =  parseInt($("#"+scope.type+"_offsite_"+workId).val());
					effort = isNaN(effort)?1:effort;
					
					scope.effortValue[scope.type+'_offsite_'+workId]  = effort;
					
					var offsoreVal = parseInt($("#"+scope.type+"_offshorelanded_"+workId).val());
					
					scope.effortValue[scope.type+'_offshore_'+workId]  = offsoreVal;
					
					var offsiteVal = parseInt($("#"+scope.type+"_offsite_"+workId).val());
					var myId  = curEle.attr('id');
					
					// Check if offshore landed
					if(levelId == 55){
						var per_sum = parseFloat(offsoreVal*offsiteVal*0.01).toFixed(2);
					}
					else{
						//calculate offsite formula (1-offshore%)
						var per_sum = parseFloat((((100-offsoreVal)*offsiteVal*currVal)/100)/offsitesum).toFixed(2);
					}
					$("#percentage_"+myId).text(isNaN(per_sum)?0:per_sum+"%");
					var idWithouttype = myId.substr(myId.indexOf('_')+1);
					
					//Get rates for the cell
					 var cspRate = per_sum*scope.data.team_rates.csp_offsite[idWithouttype].rate;
					 var rsrRate = per_sum*scope.data.team_rates.rsr_offsite[idWithouttype].rate;
					 
					 offsiteCspSum = offsiteCspSum + cspRate;
					 offsiteRsrSum = offsiteRsrSum + rsrRate;
					$("#csp_"+myId).text(scope.data.team_rates.csp_offsite[idWithouttype].rate);
					$("#rsr_"+myId).text(scope.data.team_rates.rsr_offsite[idWithouttype].rate);
					$("#margin_"+myId).text(scope.data.team_rates.csp_offsite[idWithouttype].rate - scope.data.team_rates.rsr_offsite[idWithouttype].rate);
				});
				var totalCsp = parseFloat((onsiteCspSum + offsiteCspSum)/100).toFixed(2);
				var totalRsr = parseFloat((onsiteRsrSum + offsiteRsrSum)/100).toFixed(2);
				
				var totalMargin = parseFloat((totalCsp-totalRsr)).toFixed(2);
				
				scope.blendedRates[row.split('-')[2]+"_csp_"+scope.type] = isNaN(totalCsp)?0:totalCsp;
				scope.blendedRates[row.split('-')[2]+"_rsr_"+scope.type] = isNaN(totalRsr)?0:totalRsr;
				
				$("#"+scope.type+"_blended_csp_"+row.split('-')[2]).text(isNaN(totalCsp)?0:totalCsp);
				$("#"+scope.type+"_blended_rsr_"+row.split('-')[2]).text(isNaN(totalRsr)?0:totalRsr);
				
				$("#"+scope.type+"_blended_margin_"+row.split('-')[2]).text(isNaN(totalMargin)?0:totalMargin);
			}
			scope.initMe = function(){
				$("."+scope.type+"-input-row").each(function(){
					scope.cal_sum($(this).attr('data-calclass'),"");
				});
			}
			$timeout(function(){
			scope.initMe();
			});
			scope.calculate_percentage = function(extra) {
				return extra;
			};
		},
		templateUrl : '../static/html/stafftemplate.html'
	};
});