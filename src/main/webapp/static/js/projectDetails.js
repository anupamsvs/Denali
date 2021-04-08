(function(){
	estimatorApp.controller("projectDetailController",['$scope','$rootScope','$timeout','$http','$filter','$location','$parse',function($scope,$rootScope,$timeout,$http,$filter,$location,$parse){
		  $scope.selection = [];
		  $scope.toggleflag = false;
		  $rootScope.pageTitle= "Home";
		// enterprise_application
		  /*$scope.enterprise_application = ['Oracle ERP Cloud', 'Oracle HCM Cloud'];*/

		  // Selected enterprise_application
		 /* $scope.selection = $rootScope.engagementEnterpriseApplicationList;*/
		  		  // Toggle selection for a given enterprise_application by name
		  $scope.readOnly = '';
	
		  $scope.toggleSelection = function toggleSelection(application_name,engagementEnterpriseApplicationList) {
			if(!$scope.toggleflag){
				$scope.selection = engagementEnterpriseApplicationList;
			}
		    $scope.toggleflag = true;
		    var idx = $scope.selection.indexOf(application_name);
		    // Is currently selected
		    if (idx > -1) {
		    	$scope.selection.splice(idx, 1);
		    }

		    // Is newly selected
		    else {
		    	$scope.selection.push(application_name);
		    }
		  };
		
			//Calculate Summary 
			$rootScope.calculateSummaryAll = function(){
//				alert("Are you sure?");
				projId = $rootScope.currentProjectId;
				$http.post(
						'project/enagagement/calculate/'+projId,
						{
							level:3,
							engagement_id:projId
						}
					).success(function(data){
						toastr.success("Engagement summary calculated successfully.");
					}).error(function(){
						
					});
			};
			
		$scope.saveChanges = function(t,engagementEnterpriseApplicationList){
			console.log(t);
			debugger;
			if($scope.toggleflag){
				engagementEnterpriseApplicationList = $scope.selection;
			}
			var plannedStartDate = $filter('date')(t.planned_project_start_date,'MMM-dd-yyyy');
			var estimationRequestedDate = $filter('date')(t.estimation_requested_date,'MMM-dd-yyyy');
			var createDate = $filter('date')(t.create_date,'MMM-dd-yyyy');
			$http({
				url : 'project/saveProject',
				responseType: "json",
				method:'GET',
				params:{
					id : t.id,
					guid : t.guid,
					version : t.version,
					client_name:t.client_name,
					owner: t.owner,
					engagement_name:t.engagement_name,
					engagement_leader:$('#engagementName2').val(),
					engagement_leader_email:$('#engagementLeaderEmail2').val(),
					planned_project_start_date:plannedStartDate,
					client_sector:t.client_sector,
					estimation_requestor:$('#requester2').val(),
					estimation_requestor_email:$('#requesterEmail2').val(),
					estimation_requested_date:estimationRequestedDate,
					business_units_number:t.business_units_number,
					project_duration:t.project_duration,
					post_go_live_support_duration:t.post_go_live_support_duration,
					enterprise_application:engagementEnterpriseApplicationList.join(),
					create_date : createDate,
					engagement_status:t.engagement_status,
					comments:t.comments,
					file_path:$scope.file_path2,
					offering:t.offering,
					testing:t.testing
				}
			}).success(function(data){
//					
//				    bootbox.alert({
//				    	size : 'small',
//						message : "Update Success!"
//				    });
				toastr.success("Engagement has been saved successfully.").css("text-align","center");
				$timeout(function(){$("#PwcResponsibility").trigger("click");$("body").animate({ scrollTop: "0px" }); });
			}).error(function(){
//					bootbox.alert({
//				    	size : 'small',
//						message : "Update Failed!"
//				    });
				toastr.error("Errors occurred while saving engagement. Please try again later.").css("width","600px");
			});
		};
		
		var flag1 = true
		$(document).click(function(e){ 
	        e = window.event || e; 
	        obj = $(e.srcElement || e.target);
	          if ($(obj).is("#engagementNameDiv2,#engagementNameDiv2 *")) { 
	        } else {
	        	if(flag1){
	        	$("#engagementNameList2").hide();
	        	}
	        } 
		});
		
		var engagementName = "";
		$("#engagementNameList2").hide();
		
		$("#engagementName2").on("input propertychange",function(){
			engagementName = $(this).val();
			if(engagementName.length != 0){
				setTimeout(function () {
                                   if(engagementName.indexOf(",")>0){
						
						var arr = $("#engagementName2").val().split(",");
						if(arr.length>0){
							userName = arr[arr.length -1];
						}
						}
						else{
							userName =$("#engagementName2").val();
						}
				$.ajax({
					url: "project/getUserInfoByName",
					data : {
						"userName" : userName
					},
					type : "json",
					method : "post",
					success : function(result){
						var res = eval("("+result+")");
						
						if(res.ueList != null){
							var selectHtml = "";
							var ueList = res.ueList;
							var obj= {};
							if(ueList.length == 0){
								if(engagementName.length != 0){
									selectHtml += "<li class='list-group-item' style='text-align:left;pointer-events: none;list-style-type:none;'>No Records Found</li>";
								}
							}
							for(var i=0;i<ueList.length;i++){
								 if(i<10){
										selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"+ueList[i].userName+"</li>";
										obj[ueList[i].userName] = ueList[i].email;
									}
									else if(i==10){
										selectHtml += "<li class='list-group-item' name='more' style='text-align:center;list-style-type:none;'>>>view more</li>";
										obj[ueList[i].userName] = ueList[i].email;
									}
									else{
										obj[ueList[i].userName] = ueList[i].email;
									}
								
							}
							$("#engagementNameList2").empty();
							$("#engagementNameList2").html(selectHtml);
							$("#engagementNameList2").find('li').each(function () { // 传值给input，同时关闭焦点开关
								$(this).hover(function () {
									if($(this).attr("name")!='more'){
									$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
									}
									else{
										$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
									}
								});
								$(this).mouseleave(function () {
									if($(this).attr("name")!='more'){
									$(this).attr("style","text-align:left;list-style-type:none;")
									}
									else{
										$(this).attr("style","text-align:center;list-style-type:none;")
									}
								});
								$(this).on("click", function () {

							    	if($(this).attr('name')=='more'){
							    		flag1 = false
							    		for(var i=10;i<ueList.length;i++){
							    			selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"+ueList[i].userName+"</li>";
							    		}
							    		$("#engagementNameList2").empty();
							    		$("#engagementNameList2").html(selectHtml);
							    		$("#engagementNameList2").find('[name="more"]').hide();
							    		setTimeout(function () {
							    			flag1 = true
							    		}, 1000);
							    		$("#engagementNameList2").find('li').each(function () { 
							    			$(this).hover(function () {
												if($(this).attr("name")!='more'){
												$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
												}
												else{
													$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
												}
											});
											$(this).mouseleave(function () {
												if($(this).attr("name")!='more'){
												$(this).attr("style","text-align:left;list-style-type:none;")
												}
												else{
													$(this).attr("style","text-align:center;list-style-type:none;")
												}
											});
							    			$(this).on("click", function () {
							    				flag1 = true
										    	  var text = $(this).text();
							    				 $rootScope.listengName.push(text);
										   		 $rootScope.listengMail.push(obj[text]);
										      });
							    		});
							    	}
							    		/*isBox = false;*/
							    	else{
									      var text = $(this).text();
							   		 $rootScope.listengName.push(text);
							   		 $rootScope.listengMail.push(obj[text]);
							    	}
                                                                       $("#engagementName2").val($rootScope.listengName.join(" , "));
									  $("#engagementLeaderEmail2").val($rootScope.listengMail.join(" , "));

								        $(this).parent().hide();
								        
							      })
							});
						}
					}
				});
				$("#engagementNameList2").show();
				}, 1000);
			}else{
				$("#engagementNameList2").empty();
				$("#engagementNameList2").hide();
				
			}
		});
		
		var flag2 = true
		$(document).click(function(e){ 
			e = window.event || e; 
	        obj = $(e.srcElement || e.target);
	          if ($(obj).is("#requesterDiv2,#requesterDiv2 *")) { 
	        } else {
	        	if(flag2){
	        	$("#requesterList2").hide();
	        	}
	        } 
		});
		
		var requester = "";
		$("#requesterList2").hide();
		
		$("#requester2").on("input propertychange",function(){
			requester = $(this).val();
			if(requester.length != 0){
				$.ajax({
					url: "project/getUserInfoByName",
					data : {
						"userName" : $("#requester2").val()
					},
					type : "json",
					method : "post",
					success : function(result){
						var res = eval("("+result+")");
						if(res.ueList != null){
							var selectHtml = "";
							var ueList = res.ueList;
							var obj= {};
							if(ueList.length == 0){
								if(requester.length != 0){
									selectHtml += "<li class='list-group-item' style='text-align:left;pointer-events: none;list-style-type:none;'>No Records Found</li>";
								}
							}
							for(var i=0;i<ueList.length;i++){
								if(i<10){
									selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"+ueList[i].userName+"</li>";
									obj[ueList[i].userName] = ueList[i].email;
								}
								else if(i==10){
									selectHtml += "<li class='list-group-item' name='more' style='text-align:left;list-style-type:none;'>>>view more</li>";
									obj[ueList[i].userName] = ueList[i].email;
								}
								else{
									obj[ueList[i].userName] = ueList[i].email;
								}
							}
							$("#requesterList2").empty();
							$("#requesterList2").html(selectHtml);
							$("#requesterList2").find('li').each(function () { 
								$(this).hover(function () {
									if($(this).attr("name")!='more'){
									$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
									}
									else{
										$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
									}
								});
								$(this).mouseleave(function () {
									if($(this).attr("name")!='more'){
									$(this).attr("style","text-align:left;list-style-type:none;")
									}
									else{
										$(this).attr("style","text-align:center;list-style-type:none;")
									}
								});
								$(this).on("click", function () {

							    	if($(this).attr('name')=='more'){
							    		flag2 = false
							    		for(var i=10;i<ueList.length;i++){
							    			selectHtml += "<li class='list-group-item' style='text-align:left;list-style-type:none;'>"+ueList[i].userName+"</li>";
							    		}
							    		$("#requesterList2").empty();
							    		$("#requesterList2").html(selectHtml);
							    		$("#requesterList2").find('[name="more"]').hide();
							    		setTimeout(function () {
							    			flag2 = true
							    		}, 1000);
							    		$("#requesterList2").find('li').each(function () { 
							    			$(this).hover(function () {
												if($(this).attr("name")!='more'){
												$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:left;list-style-type:none;")
												}
												else{
													$(this).attr("style","color:#FFFFFF;background:#A21616;text-align:center;list-style-type:none;")
												}
											});
											$(this).mouseleave(function () {
												if($(this).attr("name")!='more'){
												$(this).attr("style","text-align:left;list-style-type:none;")
												}
												else{
													$(this).attr("style","text-align:center;list-style-type:none;")
												}
											});
							    			$(this).on("click", function () {
							    				flag2 = true
										    	  var text = $(this).text();
											        $("#requester2").val(text);
											        $("#requesterEmail2").val(obj[text]);
											        $(this).parent().hide();
										      });
							    		});
							    	}
							    		
							    	else{
									        var text = $(this).text();
									        $("#requester2").val(text);
									        $("#requesterEmail2").val(obj[text]);
									        $(this).parent().hide();
							    	}
								        
							      })
							});
						}
					}
				});
				$("#requesterList2").show();
				
			}else{
				$("#requesterList2").hide();
				
			}
		});
	}]);
})();