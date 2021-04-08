<div class="pull-right">
	<b style="color: #a32020;">Version {{version_rates}} &nbsp;&nbsp;
		Last Saved on : {{create_dt | date : 'MM/dd/yyyy' }}</b>
</div>
</br>
<div class="clearfix"></div>
<div class="panel panel-default">

	<div class="panel-heading accordion-toggle"
		role="tab" id="CSPRateHeadingTree" href="#cspRateList">
		<h4 class="panel-title dashboard_box_title">
			<a>CSP Rate </a>
			<div class="right-head-btn">
			<div class="pull-left" >Please select version to generate reports</div>
					<div class="pull-left" ><select ng-change="loadRates(version)" ng
					class="form-control" id="version" name="version"
					ng-model="version">

					<option style="display: none">--Please select version to
						generate reports--</option>
					<option ng-repeat="list in versionlist" ng-value="list.version_id">{{'Ver.
						'+list.version_id+' | Date: '+list.create_dt}}</option>
				</select>	</div>
				<div class="pull-left"><button class="btn btn-primary btn-save function-button"
	data-toggle="modal" data-target="#openModal">&nbsp;&nbsp;Save&nbsp;&nbsp;</button></div>
			</div>
		</h4>
	</div>
	<div id="cspRateList" class="panel-collapse accordion-body collapse in"
		role="tabpanel" aria-labelledby="CSPRateHeadingTree">

		<div class="row">
			<div class="col-md-7">
				<b>Onsite</b>
				<div id="lovCspOnsitegrid" ui-grid="gridAdminOnsiteRatesOptions"
					ui-grid-auto-resize ui-grid-edit class="grid"></div>
			</div>

			<div class="col-md-5">
				<b>Offshore</b>
				<div id="lovCspOffsitegrid" ui-grid="gridAdminOffsiteRatesOptions"
					ui-grid-auto-resize ui-grid-edit class="grid"></div>
			</div>

		</div>


	</div>

	<div class="panel-heading accordion-toggle" data-toggle="collapse"
		role="tab" id="CSPRateHeadingTree" href="#cspRateList">
		<h4 class="panel-title dashboard_box_title">
			<a>RSR Rate </a>
		</h4>
	</div>
	<div id="rspRateList" class="panel-collapse accordion-body collapse in"
		role="tabpanel" aria-labelledby="CSPRateHeadingTree">

		<div class="row ">
			<div class="col-md-7">
				<b>Onsite</b>
				<div id="lovRspOnsitegrid" ui-grid="gridAdminOnsiteRatesOptionsRSP"
					ui-grid-auto-resize ui-grid-edit class="grid"></div>
			</div>

			<div class="col-md-5">
				<b>Offshore</b>
				<div id="lovRspOffsitegrid"
					ui-grid="gridAdminOffsiteRatesOptionsRSP" ui-grid-auto-resize n
					ui-grid-edit class="grid"></div>
			</div>

		</div>


	</div>



	<div class="row">

		<div data-backdrop="static" class="modal fade" id="openModal"
			tabindex="-1" role="dialog" aria-labelledby="releaseModal"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="shareToModal">Are you sure you
							want to create version ?</h4>
					</div>
					<div class="modal-body">
						<label><input type="checkbox" name="checkbox"
							ng-checked="false" ng-model="status" value="false"> Check
							to create new version.</label>
					</div>

					<div class="modal-footer">

						<button type="button" class="btn btn-primary" id="saveProject"
							data-dismiss="modal" ng-click="saveRate()">save</button>
						<button type="button" class="btn btn-secondary" id="saveProject"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 			 save 	 -->
				       <div class="pull-right col-md-2">
						<button type="button" data-toggle="modal" data-target="#openModal" class="col-md-10 btn btn-primary">&nbsp;&nbsp;Save&nbsp;&nbsp;</button>
<!-- 						<button type="button" class="btn btn-primary btn-save function-button" ng-click="refreshRate()" >Admin Rates Refresh </button> -->
		 


						</div>


	</div>

</div>








</div>
<div></div>
</div>
