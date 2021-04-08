<div class="panel panel-default" ng-controller="engagementCSPRateController">
	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="CSPRateHeadingTree" href="#cspRateList">
		<h4 class="panel-title dashboard_box_title">
			<a>CSP Rate </a>
		</h4>
	</div>
	<div id="cspRateList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="CSPRateHeadingTree">
		<div class="page_box_content accordion-inner">
		   <form name="cspRateForm"><div class="col-md-8" style="padding-right: 0;">
			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable table-onsite">
				<thead>
					<tr>
						<td class="rates-title"></td>
						<td colspan="5" class="rates-title">Onsite</td>
					</tr>
					<tr>
						<td>Workstream</td>
						<td>Partner</td>
						<td>Director</td>
						<td>Manager</td>
						<td>Senior</td>
						<td>Associate</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="data in onsiteData">
						<td ng-bind="data.workstreamName"></td>
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.partner_cost"/></td>
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.director_cost"/></td>
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.manager_cost"/></td>
						<td><input required type="number" min="0.0 "numeric-only class="form-control" name=""  ng-model="data.senior_associate_cost"/></td>
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.associate_cost"/></td>
					</tr>
				</tbody>
			</table>
		   </div>
		   <div class="col-md-4" style="padding-left: 0;">
		       <table class="table table-bordered table-sm table-striped table-hover no-footer dataTable table-offshore">
				<thead>
					<tr>
						<td colspan="3" class="rates-title">Offshore</td>
					</tr>
					<tr>
						<td>Offshore Landed</td>
						<td>Manager</td>
						<td>Staff</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="data in offshoreData">
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.offshore_landed_cost"/></td>
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.manager_cost"/></td>
						<td><input required type="number" min="0.0" numeric-only class="form-control" name=""  ng-model="data.staff_cost"/></td>
					</tr>
				</tbody>
			</table>
		   </div></form>
		   <div class="col-md-12">
		      <table class="table table-sm no-footer">
		          <tbody>
		             <tr>
		                <td colspan="8"></td>
		                <td align="right"><input type="button" class="btn btn-primary" value="&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;" ng-click="saveCspRate(this)" ng-disabled="cspRateForm.$invaild"/></td>
		             </tr>
		          </tbody>
		      </table>
		   </div>
		</div>
	</div>
</div>
