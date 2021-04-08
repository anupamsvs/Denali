<div class="panel panel-default" ng-controller="modulesController">
	<div class="panel-heading accordion-toggle"  role="tab" id="ModuleHeadingTree" href="#ModuleList">
		<h4 class="panel-title dashboard_box_title">
			<a>Modules </a>
			<div class="right-head-btn">
			
			<button ng-disabled="" type="button" class="btn btn-primary" data-toggle="modal" data-target="#UnitProjectModal" ng-click="loadUnitTable()">&nbsp;&nbsp;Units&nbsp;&nbsp;</button>
			<button ng-click="saveModules()" class="btn btn-primary" >Save</button>
			</div>
		</h4>
	</div>
	<div id="ModuleList" class="panel-collapse accordion-body collapse in"  role="tabpanel" aria-labelledby="ModuleHeadingTree">
		<div class="page_box_content accordion-inner" >
<!-- 			<div ui-grid="gridModule" class="grid" ui-grid-edit ui-grid-pagination ui-grid-pinning></div> -->
				<div class="scrollable">
				<table class="summarytable fixed table table-bordered table-sm table-striped table-hover no-footer" id="modulesTable">
					<thead>
						<tr class="small-header">
						<th title="Cloud Offering" width="100px">Cloud Offering</th>
						<th title="Business Process" width="100px">Business Process</th>
						<th title="Module" width="100px">Module</th>
						<th title="Abbreviation" width="100px">Abbreviation</th>
						<th title="In Scope" width="100px">In Scope</th>
						<th title="Complexity Factor" width="100px">Complexity Factor</th>
						<th title="# Of Requirement" width="100px"># Of Requirement</th>
						<th title="# Of Gaps" width="100px"># Of Gaps</th>
						<th title="Duration Factor" width="60px">Duration Factor</th>
						<th title="Weightage" width="60px">Weightage</th>
						<th title="DDC Supported" width="60px">DDC Supported</th>
						<th title="Job Aids" width="60px">Job Aids</th>
						<th title="Workshop" width="60px">Workshop</th>
						
						<th  colspan="3"  width="320px" ><div class="main-head">Totals</div>
						<div class="subheads-container">
						<span class="subheads three-sub sub-left" >On-Site</span>
						<span class="subheads three-sub sub-left"  >DDC</span>
						
						<span class="subheads three-sub sub-right"  >Testing Team</span>
						</div>
						</th>
						
						<th title="{{act.activity}}" width="180px" colspan="2" ng-repeat="act in activities"><div class="main-head">{{act.activity}}</div>
						<div class="subheads-container"><span class="subheads sub-left" >{{(act.workstream == 2)?"Testing Team":"On-Site"}}</span>
						<span class="subheads sub-right"  >DDC</span></div>
						</th>
						
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="moduledata in moduleList">
						<td >{{moduledata.cloudOffering}}</td>
						<td>{{moduledata.businessProcessName}}</td>
						<td>{{moduledata.module_name}}</td>
						<td>{{moduledata.module_abbreviation}}</td>
						
						<td><input type="hidden" class="changed_data" ng-value="$index" /><select class="form-control" ng-model="moduleList[$index].is_in_scope_flag" ><option value="Yes">Yes</option><option value="No">No</option></td>
						<td><input class="form-control" ng-model="moduleList[$index].complexity_factor" type="number" min="1.00" step="0.01"></td>
						<td><input class="form-control" ng-model="moduleList[$index].number_of_requirements" type="number" min="1.00" step="1"></td>
						<td><input class="form-control" ng-model="moduleList[$index].number_of_gaps" type="number" min="1.00" step="1"></td>
					
						<td>{{moduledata.module_duration_factor}}</td>
						<td>{{moduledata.moduleweightage}}</td>
						<td>{{moduledata.ddc_supported_flag}}</td>
						<td>{{moduledata.job_aid}}</td>
						<td>{{moduledata.workshop}}</td>
						
						<td class="total-column" >{{module_totals[moduledata.id].onsite}}</td>
						<td class="total-column" >{{module_totals[moduledata.id].ddc}}</td>
						<td class="total-column" >{{module_totals[moduledata.id].testing}}</td>
						
						<td title="{{activity_values[moduledata.id+'_'+act.id].explain_calc}}" ng-init="(act.workstream == 2)?(module_totals[moduledata.id].testing = sumMe(activity_values[moduledata.id+'_'+act.id].onsite,module_totals[moduledata.id].testing)):(module_totals[moduledata.id].onsite = sumMe(activity_values[moduledata.id+'_'+act.id].onsite,module_totals[moduledata.id].onsite))"  ng-repeat-start="act in activities">{{activity_values[moduledata.id+"_"+act.id].onsite}}</td>
						<td title="{{activity_values[moduledata.id+'_'+act.id].explain_calc}}" ng-init="module_totals[moduledata.id].ddc = sumMe(activity_values[moduledata.id+'_'+act.id].ddc,module_totals[moduledata.id].ddc)" ng-repeat-end >{{activity_values[moduledata.id+"_"+act.id].ddc}}</td>
						
						</tr>
					</tbody>
				</table>
		</div>
	</div>
</div>
</div>