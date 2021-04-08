<style>
<!--
#modulegrid {
	height: 530px;
}
-->
</style>
<div ng-controller="staffController" class="panel panel-default">
	<div class="panel-heading accordion-toggle" data-toggle="collapse"
		role="tab" id="ModuleHeadingTree" href="#ModuleList">
		<h4 class="panel-title dashboard_box_title">
			<a>Staffing Drivers</a>
		</h4>
	</div>
	<!--  Grid Start  -->
	<!--  check /static/html/stafftemplate.html for source below -->
	<div id="staffTabContainer" class="shadow-tab-container">
		<ul id="staffTabs"  class="shadow-tabs nav nav-tabs">
			<li class="active"><a href="#ddc" data-toggle="tab">DDC</a></li>
			<li  ng-click="controlInside.callInit('workstream')"><a href="#onsite" data-toggle="tab">On Site</a></li>
		</ul>
		<div class="tab-content clearfix">
			<div ng-if="ready" class="tab-pane in active" id="ddc">
				<div >
					<staff-driver type="ddc" data="staffData"></staff-driver>
				</div>
			</div>
			<div class="tab-pane fade" id="onsite">
				<div>
					<staff-driver control="controlInside" type="workstream" data="staffData"></staff-driver>
				</div>
			</div>
		</div>
	</div>
	<!--  Grid End -->
</div>

<script type="text/javascript">
<!--
	var stafftablength = $("#staffTabs li").length;
	var tabWidth = 100 / stafftablength;
	console.log(tabWidth);
	$("#staffTabs li").css('width', tabWidth - 1 + '%');
//-->
</script>