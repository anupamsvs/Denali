<div ng-controller="detailSummaryController" class="panel edit-paint panel-default">
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse"
			role="tab" id="ConversionCountHeadingTree"
			href="#ConversionCountHeadingDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Detailed Summary </a>
			</h4>
		</div>
		<div id="ConversionCountHeadingDataList"
			class="panel-collapse accordion-body collapse in" role="tabpanel"
			aria-labelledby="ConversionCountHeadingTree">
			<div class="pull-right"><Button ng-click="saveOverride()" class="btn btn-primary">Save</Button></div>
			<div class="page_box_content accordion-inner">
			<div class="scrollheader">	
			<table id="detail-summary" class="summarytable table table-bordered table-sm table-striped table-hover no-footer ">
				<thead>
				<tr>
				<th colspan="3"></th>
					<th colspan="2">Effort (Hr)</th>
					<th colspan="2">Override Effort (Hr)</th>
					<th colspan="2">CSP Price</th>
					<th colspan="2">RSR Price</th>
					<th colspan="2">Margin Price</th>			
				</tr>
				<tr>
				
				<th width="120px">Workstream</th>
				<th>Prototype</th>
				<th>Activity</th>
				
				<th>Onsite</th>
				<th>DDC</th>
				
				<th>Onsite</th>
				<th>DDC</th>
				
				<th>Onsite</th>
				<th>DDC</th>
				
				<th>Onsite</th>
				<th>DDC</th>
				
				<th>Onsite</th>
				<th>DDC</th>
				
				</tr>
				</thead>
				<tbody class="split-table"  ng-repeat="(key,val) in allSummary">
				<tr  ng-class="((effort.effort_ddc_override != effort.effort_ddc) ||(effort.effort_onsite_override != effort.effort_onsite ) )?'overriden':''" ng-repeat="effort in val">
					<td>{{effort.worktream}}</td>
					<td>{{effort.prototype}}</td>
					<td>{{effort.activity}}</td>
				
					<td ng-init="summaryTotal[key].effort_onsite = sumMe(summaryTotal[key].effort_onsite,effort.effort_onsite)" title="{{effort.explanation.split('||')[0]}}">{{twoDecimal(effort.effort_onsite)}}</td>
					<td ng-init="summaryTotal[key].effort_ddc = sumMe(summaryTotal[key].effort_ddc,effort.effort_ddc)" title="{{effort.explanation.split('||')[1]}}">{{twoDecimal(effort.effort_ddc)}}</td>
					
					<td><input class="form-control" type="number" ng-init="summaryTotal[key].over_onsite = sumMe(summaryTotal[key].over_onsite,data[effort.id]['onsite'])" ng-model="data[effort.id]['onsite']" ng-value="twoDecimal(effort.effort_onsite_override)" min="0" step="0.01" /></td>
					<td><input class="form-control" type="number" ng-init="summaryTotal[key].over_ddc= sumMe(summaryTotal[key].over_ddc,data[effort.id]['ddc'])" ng-model="data[effort.id]['ddc']" ng-value="twoDecimal(effort.effort_ddc_override)" min="0" step="0.01" /></td>
					
					<td class="currency" ng-init="summaryTotal[key].csp_onsite = sumMe(summaryTotal[key].csp_onsite,effort.csp_onsite,key)">{{effort.csp_onsite | currency:"$":0}}</td>
					<td class="currency" ng-init="summaryTotal[key].csp_ddc = sumMe(summaryTotal[key].csp_ddc,effort.csp_ddc)" >{{effort.csp_ddc | currency:"$":0}}</td>
					
					<td class="currency" ng-init="summaryTotal[key].rsr_onsite = sumMe(summaryTotal[key].rsr_onsite ,effort.rsr_onsite)" >{{effort.rsr_onsite | currency:"$":0}}</td>
					<td class="currency" ng-init="summaryTotal[key].rsr_ddc = sumMe(summaryTotal[key].rsr_ddc  ,effort.rsr_ddc)" >{{effort.rsr_ddc | currency:"$":0}}</td>
					
					<td class="currency" ng-init="summaryTotal[key].margin_onsite = sumMe(summaryTotal[key].margin_onsite  ,marginMe(effort.csp_onsite,effort.rsr_onsite))" >{{marginMe(effort.csp_onsite,effort.rsr_onsite) | currency:"$":0}}</td>
					<td class="currency" ng-init="summaryTotal[key].margin_ddc = sumMe(summaryTotal[key].margin_ddc ,marginMe(effort.csp_ddc,effort.rsr_ddc))" >{{marginMe(effort.csp_ddc,effort.rsr_ddc) | currency:"$":0}}</td>
				</tr>
				<tr class="total-row">
						<td colspan="3">Total</td>
						<td>{{summaryTotal[key].effort_onsite}}</td>
						<td>{{summaryTotal[key].effort_ddc}}</td>
						<td>{{summaryTotal[key].over_onsite}}</td>
						<td>{{summaryTotal[key].over_ddc}}</td>			
						<td class="currency">{{summaryTotal[key].csp_onsite | currency:"$":0}}</td>
						<td class="currency">{{summaryTotal[key].csp_ddc | currency:"$":0}}</td>
						<td class="currency">{{summaryTotal[key].rsr_onsite | currency:"$":0}}</td>
						<td class="currency">{{summaryTotal[key].rsr_ddc | currency:"$":0}}</td>
						<td class="currency">{{summaryTotal[key].margin_onsite | currency:"$":0}}</td>
						<td class="currency">{{summaryTotal[key].margin_ddc | currency:"$":0}}</td>
					</tr>
				
				</tbody>
				</table>
				</div>
			</div>
		</div>

	</div>
</div>