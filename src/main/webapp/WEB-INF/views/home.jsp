<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html  ng-app="estimatorApp">
<head>
<link rel="icon" type="image/ico" href="${basePath}/static/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1 , maximum-scale=1.0">
<title>User {{pageTitle}}</title>

   <link href="${basePath}/static/bootstrap/css/bootstrap.css" rel="stylesheet">
   <!--  -->
   <link href="${basePath}/static/custom/cronos_styles.css" rel="stylesheet">
   <link href="${basePath}/static/custom/font-awesome.css" rel="stylesheet">
   <link href="${basePath}/static/custom/jquery.dataTables.min.css" rel="stylesheet">
   <link href="${basePath}/static/custom/left_nav.css" rel="stylesheet">
   <link href="${basePath}/static/bootstrap/css/fileinput.min.css" rel="stylesheet">
   <link href="${basePath}/static/plugin/toastr/toastr.min.css" rel="stylesheet">
   <link href="${basePath}/static/plugin/showLoading/css/showLoading.css" rel="stylesheet">
<%--    <link href="${basePath}/static/plugin/xyscroll.css" rel="stylesheet"> --%>
   <link rel="stylesheet" href="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css" />
   
   <script src="${basePath}/static/jquery/jquery.min.js" type="text/javascript"></script>
   <script src="${basePath}/static/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
   <script src="${basePath}/static/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-touch.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-animate.js"></script>
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

<script src ="https://cdnjs.cloudflare.com/ajax/libs/ng-idle/1.3.2/angular-idle.min.js"></script>
   
   <script src="${basePath}/static/plugin/toastr/toastr.min.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/bootbox.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/home.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/projectManagement.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/projectDetails.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/CSPRatesSetup.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/modules.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/ProjectDuration.js" type="text/javascript"></script>
   <script src="${basePath}/static/bootstrap/js/fileinput.min.js" type="text/javascript"></script>
   <script src="${basePath}/static/plugin/showLoading/js/showLoading.js" type="text/javascript"></script> 
<script src="${basePath}/static/js/staffDriver.js" type="text/javascript"></script>
<script src="${basePath}/static/js/detailSummary.js" type="text/javascript"></script>
   <script src="${basePath}/static/js/SummaryEfforts.js" type="text/javascript"></script>
   <script src="${basePath}/static/plugin/angular-pageslide-directive.min.js"></script>
    <script src="${basePath}/static/plugin/xyscroll.js"></script>
</head>

<body id="zhezhao">
	<div id="glassScreen"></div>
	
	<!-- Fixed navbar -->
	<%@ include file="../../static/html/header.html"%>
	<div class="lockMessage" ng-if="lockMessage != ''">{{lockMessage}}</div>
	<div class="container-fluid">
		<div class="container-fluid" id="dashboard_block">
			<!-- blocks -->
			<%-- 	    <%@ include file="../../static/html/blocks.html" %> --%>

			
			<!-- Left tabs -->
			<div class="row">
				<div class="col-xs-12">
					<div class="container-fluid">
						<div class="row">
						<pageslide ps-auto-close="true" ps-key-listener="true" ps-side="left" ps-open="checked">
							<div class="col-md-12 nav-left-padding">
								<ul id="main-nav" class="nav nav-tabs" style="">
									<li class="active" id="projectManagement" ng-controller="reloadController" ng-click="reload()"><a href="#">Project Management</a></li>
									<li class="main-menu-invisible level-1 main-menu-items" id="Details"><a href="#">Details</a></li>
<!-- 									<li class="main-menu-invisible main-menu-items" id="CSPRate" ng-controller="cspRateInitialController" ng-click="initialCSPRate()"><a href="#">Rate</a></li> -->
									<li class="main-menu-invisible level-1 main-menu-items" id="PwcResponsibility" ng-controller="PwcResponsibilityMainController" ng-click="init()"><a href="#">PwC Responsibility</a></li>
								
									<li class="main-menu-invisible level-2 main-menu-items" ng-controller="cspRatesSetupController" ng-click="loadRates('')" id="CSPRate"><a href="#">Rates</a></li>
									<li  class="main-menu-invisible level-2 main-menu-items" id="staffDriver" ng-controller="staffController" ng-click="loadStaffDrivers()" ><a href="#">Staffing Drivers</a></li>
								
									<li class="main-menu-invisible level-2 main-menu-items" id="Modules" ng-controller="modulesController" ng-click="initModules()"><a href="#">Modules</a></li>
									<li class="main-menu-invisible level-2 main-menu-items" id="Conversions" ng-controller="ConversionsMainController" ng-click="initConversion()"><a href="#">Conversions</a></li>
									<li class="main-menu-invisible level-2 main-menu-items" id="OtherRICE" ng-controller="OtherRICEMainController" ng-click="initRice()"><a href="#">RIE</a></li>
										<li class="main-menu-invisible level-2 main-menu-items" id="AdditionalService" ng-controller="AdditionalServiceMainController" ng-click="initAddEffort()"><a href="#">Additional Services</a></li>
									<li class="main-menu-invisible level-2 main-menu-items" id="AdditionalServiceEffort" ng-controller="AdditionalServiceEffortController" ng-click="initEffort()"><a href="#">Additional Services Effort</a></li>
									<li class="main-menu-invisible level-3 main-menu-items" id="DetailSummary" ng-controller="detailSummaryController" ng-click="initSummary()"><a href="#">Detailed Summary</a></li><!-- 
									<li class="main-menu-invisible main-menu-items" id="Attachment" ng-controller="AttachmentMainController" ng-click="init()"><a href="#">Detailed Summary</a></li> -->
<!-- 								 ng-controller="AdditionalServiceEffortController" ng-click="initEffort()" -->
									<li class="main-menu-invisible level-3 main-menu-items" id="SummaryEffort"  ng-controller="SummaryEffortController" ng-click ="initSummaryEfforts()"><a href="#">Summary Efforts</a></li>
									<li class="main-menu-invisible level-3 main-menu-items" id="DetailEffortSummary" ng-controller="detailEffortSummaryController" ng-click="initSummary()"><a href="#">Detailed Effort Summary</a></li>
									
									
									
									<li class="main-menu-invisible level-2 main-menu-items" id="ProjectDuration" ng-controller="projectDurationController" ng-click="init()"><a href="#">Project Duration</a></li>
<!-- 									<li class="main-menu-invisible level-2 main-menu-items" id="Attachment" ng-controller="AttachmentMainController" ng-click="init()"><a href="#">Attachment</a></li> -->
<!-- 								 ng-controller="AdditionalServiceEffortController" ng-click="initEffort()" -->
								</ul>
							</div>
							 </pageslide>
						</div>
						<div class="row">
							<div class="col-md-12 nav-right-padding" id="Portal-projectManagement">
								<%@ include file="details/projectManagement.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-Details">
								<%@ include file="details/detailsMain.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-CSPRate">
								<%@ include file="cspRate/CSPRatesSetup.jsp"%>
							</div>
							
							<div class="col-md-12 nav-right-padding hide" id="Portal-staffDriver">
								<%@ include file="staffDriver/staffDriver.jsp"%>
							</div>
							
							<div class="col-md-12 nav-right-padding hide" id="Portal-Modules">
								<%@ include file="modules/modulesMain.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-Conversions">
								<%@ include file="conversions/conversionsMain.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-OtherRICE">
								<%@ include file="otherRICE/otherRICEMain.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-PwcResponsibility">
								<%@ include file="pwcResponsibility/pwcResponsibilityMain.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-AdditionalService">
								<%@ include file="additionalService/additionalServiceMain.jsp"%>
							</div>
							
							<div class="col-md-12 nav-right-padding hide" id="Portal-AdditionalServiceEffort">
								<%@ include file="additionalService/additionalServicesEffort.jsp"%>
							</div>
							
							<div class="col-md-12 nav-right-padding hide" id="Portal-ProjectDuration">
								<%@ include file="projectDuration/ProjectDurationMain.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-DetailSummary">
								<%@ include file="summary/detailed.jsp"%>
							</div>
							<div class="col-md-12 nav-right-padding hide" id="Portal-DetailEffortSummary">
								<%@ include file="summary/DetailedSummaryEffort.jsp"%>
							</div>
							
							<div class="col-md-12 nav-right-padding hide" id="Portal-Attachment">
								<%@ include file="attachment/attachmentMain.jsp"%>
							</div>
							
							<div class="col-md-12 nav-right-padding hide" id="Portal-SummaryEffort">
								<%@ include file="summary/SummaryEffort.jsp"%>
							
							</div>
							
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@include file="units/units.jsp"%>
	<%@ include file="../../static/html/footer.html"%>
	<script type="text/javascript">
		$(function() {
			toastr.options = {
				"closeButton" : false,
				"debug" : false,
				"newestOnTop" : false,
				"progressBar" : false,
				"positionClass" : "toast-top-center",
				"preventDuplicates" : true,
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "8000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "slideDown",
				"hideMethod" : "slideUp",
				'body-output-type' : 'trustedHtml'
			}

			$('#main-nav  li').click(function(e) {
				$('#main-nav  li').removeClass('active');
				//$(e.target).addClass('active');
				$(this).addClass('active');
				$("[id^=Portal]").addClass("hide");
				var targetId = $(this).attr("id");
				$("#Portal-" + targetId).removeClass("hide");
			});

		});

		//hide menu 
		$(function() {
			debugger;
			
			$('#projectManagement').click(function() {
				$('.main-menu-items').each(function() {
					$(this).addClass('main-menu-invisible');
				})
			});

		});
	</script>

</body>
</html>
