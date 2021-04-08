estimatorApp.controller('projectDurationController', [
                                                      '$scope',
                                                      '$http',
                                                      '$rootScope',
                                                      function($scope, $http, $rootScope) {
                                                    	  $scope.roundNum= function(data){
                                                              if(data == undefined){
                                                           return 0;
                                                          	}
                                                          	
                                                           return  Math.round((parseFloat(data)));
                                                          	
                                                            	 }
                                                    	  
                                                    	  $scope.init = function() {
                                                    		  $("#zhezhao").showLoading();


                                                    		  var idparam = $rootScope.currentProjectId;
                                                    		  $rootScope.proj_duration = $rootScope.projectDetails.project_duration;
                                                    		  try {
                                                    			  $rootScope.post_go_live_support_duration = $rootScope.projectDetails.post_go_live_support_duration;
															} catch (e) {
																$rootScope.post_go_live_support_duration = 0;
															}
                                                    		  
                                                    		  
                                                    		  $http({	
                                                    			  url : "Project Duration/ProjectDurationMain",
                                                    			  responseType: "json",
                                                    			  method:'GET',
                                                    			  params : {
                                                    				 idparam : idparam,
                                                    				 //proj_duration :  $rootScope.proj_duration,
                                                    				 post_go_live_support_duration : $rootScope.post_go_live_support_duration,
                                                    				 planningWeeks : $rootScope.planningWeeks,
                                                    				 
                                                    				  //proj_duration : $rootScope.projectDetails.proj_duration
                                                    				  //$rootScope.post_go_live_support_duration : post_go_live_support_duration
                                                    			  }
                                                    		  }).success(function(response) {

//                                                    			  console.log("&&&&&&&"+ response);
                                                    			  $rootScope.maxModWtgAndCmplxtyFctr = response.max_Mod_Wtg_And_CmplxtyFctr;
                                                    			  $rootScope	.moduleMultiplierMap = response.module_multiplier_Map;
                                                    			  $rootScope.projDurationFactor = response.proj_Duration_Factor;
                                                    			  //$rootScope.projectImplDuration = response.project_ImplDuration;
                                                    			  $rootScope.moduleWeeks = response.module_Weeks;
                                                    			  $rootScope.riceEffortInputMap = response.rice_Effort_Input_Map;
                                                    			  $rootScope.PrototypSplitListMasterList = response.protypSplit;
                                                    			  $rootScope.prototypSplitValue =response.prototypSplitValue;
                                                    			  
                                                    			  $rootScope.post_go_live_supp_duratn =$rootScope.projectDetails.post_go_live_support_duration;
                                                    			  console.log($rootScope.projectDetails);
                                                    			  console.log($rootScope.post_go_live_supp_duratn);

                                                    			  $("#zhezhao").hideLoading();
                                                    		  }).error(function() {
                                                    			  toastr.error("Errors occurred while saving Project Duration. Please try again later.").css("width","600px");
                                                    			  
                                                    		  });
                                                    		  
                                                    		  
                                                    		  
                                                    	  }
                                                    	  $scope.saveChanges = (function(){
                                                    		  
                                                    		  var idparam = $rootScope.currentProjectId;
                                                    		  var complexity_multiplier = $scope.maxModWtgAndCmplxtyFctr.complexity_multiplier;
                                                    		   var planningWeeks = $scope.riceEffortInputMap.planningWeeks;
                                                    		  var resourcesForWeeks = $scope.riceEffortInputMap.resourcesForWeeks;
                                                    		  var resourcesForplusWeeks = $scope.riceEffortInputMap.resourcesForplusWeeks;
                                                    		  console.log(idparam+""+complexity_multiplier+""+planningWeeks+""+resourcesForWeeks+""+resourcesForplusWeeks);
                                                    		  
                                                    		  
                                                    		  $http({  
                                                    			  	  url : "Project Duration/saveChanges",
                                                        			  responseType: "json",
                                                        			  method:'GET',
                                                        			  params : {
                                                        				 idparam : idparam,
                                                        				 complexity_multiplier : complexity_multiplier,
                                                        				 planningWeeks : planningWeeks,
                                                        				 resourcesForWeeks : resourcesForWeeks,
                                                        				 resourcesForplusWeeks : resourcesForplusWeeks,
                                                        			  }
                                                    		  
                                                    		  }).success(function(){
                                                    			  toastr.success("Changes saved successfully").css("width","600px");
                                                    			  $scope.init();
                                                    		  })
                                                    		  
                                                    	  })
                                                      } ]);