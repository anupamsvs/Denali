<div class="panel panel-default" ng-controller="projectDetailController">
	<div class="panel-heading" role="tab" id="headingThree">
		<h4 class="panel-title dashboard_box_title">
			<a data-toggle="collapse" href="#caselist" aria-expanded="true" aria-controls="caselist">Details </a>
			<div class="right-head-btn"><button class="btn btn-primary" ng-disabled="projectDetailsForm.$invalid ||  projectDetails.readOnly" ng-click="saveChanges(projectDetails,engagementEnterpriseApplicationList)" ngdisable="projectDetailsForm" id="" name="" >Save & Continue</button></div>
		</h4>
	</div>
	<div id="caselist" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
		<div class="page_box_content">
			<form class="form-horizontal col-md-8" role="form" id="projectDetailsForm" name="projectDetailsForm">
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Project Number<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input required  type="text" ng-disabled="true" class="projectField form-control" ng-model="projectDetails.project_no" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group hide">
					<label for="name" class="col-sm-4 control-label">id:</label>
					<div class="col-sm-8">
						<input required type="text" ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.id" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group hide">
					<label for="name" class="col-sm-4 control-label">GUID:</label>
					<div class="col-sm-8">
						<input required type="text" ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.guid" id="" name="" value="" />
					</div>
				</div>
				
				<datalist id="clientId">
					                <option ng-repeat="clientname in clientList" value="{{clientname}}">{{clientname}}</option>
			   </datalist>
			   
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Client Name<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input required type="text" ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.client_name" id="" name="" value="" list="clientId" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Engagement Name<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input required type="text" ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.engagement_name" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Engagement Leader<span style="color: red">*</span></label>
					<div class="col-sm-8" id="engagementNameDiv2" style="position: relative;display: inline-block;">
						<input required type="text"  ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.engagement_leader" id="engagementName2" name="" value="" />
						<ul class="" style="margin:0px;padding:0px;z-index:100;position:absolute;" id="engagementNameList2"></ul>
					</div>
				</div>
				
				<div class="form-group ">
					<label for="name" class="col-sm-4 control-label">Engagement Leader Email<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input type="text"  required ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.engagement_leader_email" id="engagementLeaderEmail2" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Planned Start Date</label>
					<div class="col-sm-8">
						<input  type="date"  ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.planned_project_start_date" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Client Sector<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<select  ng-disabled="projectDetails.readOnly"  class="form-control" ng-model="projectDetails.client_sector" ng-change="" required>
							<option ng-repeat="x in clientSector" ng-selected="projectDetails.client_sector==x">{{x}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Requester<span style="color: red">*</span></label>
					<div class="col-sm-8" id="requesterDiv2" style="position: relative;display: inline-block;">
						<input  ng-disabled="projectDetails.readOnly" required type="text" class="projectField form-control" ng-model="projectDetails.estimation_requestor" id="requester2" name="" value="" />
						<ul class="" style="margin:0px;padding:0px;z-index:100;position:absolute;" id="requesterList2"></ul>
					</div>
				</div>
				<div class="form-group ">
					<label for="name" class="col-sm-4 control-label">Requester Email<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input type="text" required ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.estimation_requestor_email" id="requesterEmail2" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Requested Date<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input required type="date"  ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.estimation_requested_date" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Number of Business Units<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input type="number" required ng-disabled="projectDetails.readOnly" min="0.0" numeric-only class="projectField form-control" ng-model="projectDetails.business_units_number" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Project duration (Weeks)</label>
					<div class="col-sm-8">
						<input type="number"  ng-disabled="projectDetails.readOnly" min="0" step="1" numeric-only class="projectField form-control" ng-model="projectDetails.project_duration" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Post Go-Live Support (Weeks)<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<input type="number"  required ng-disabled="projectDetails.readOnly" step="1" ng-value="0" min="0" numeric-only class="projectField form-control" ng-model="projectDetails.post_go_live_support_duration" id="" name="" value="" />
					</div>
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 				<label for="name" class="col-sm-4 control-label">Enterprise Application:</label> -->
<!-- 				<div  ng-repeat="application_name in enterpriseApplications"> -->
<!-- 					<div class="col-sm-8" style="text-align:left"> -->
<!-- 						<input -->
<!-- 						 ng-disabled="projectDetails.readOnly" -->
<!-- 						    type="checkbox" -->
<!-- 						    name="selectednames[]" -->
<!-- 						    value="{{application_name}}" -->
<!-- 						    ng-checked="engagementEnterpriseApplicationList.indexOf(application_name) > -1" -->
<!-- 						    ng-click="toggleSelection(application_name,engagementEnterpriseApplicationList)" -->
<!-- 						  > -->
<!-- 						<label ng-bind="application_name" ></label> -->
<!-- 					</div> -->
<!-- 					<div class="col-sm-4" ></div> -->
<!-- 				</div> -->
<!-- 				</div> -->
				
				
				 <div class="form-group">
						<label for="name" class="col-sm-4  control-label">Cloud Offering<span style="color: red">*</span></label>
						<div class="col-sm-8">
							<select required ng-disabled="" ng class="form-control offering" id="offering" name="offering" 
									ng-model="projectDetails.offering" 
									ng-options="offering.id as offering.code for offering in offeringList"  multiple>
								<option style="display:none" value="">----Select a offering----</option>
							</select>
						
					</div>		
				</div>
					
				    <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Seperate Testing Team Deployed<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                  	<select required class="form-control" ng-model="projectDetails.testing" ng-options="item for item in ['Yes', 'No']">
                                  	</select>
                                    </div>
                                </div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Status<span style="color: red">*</span></label>
					<div class="col-sm-8">
						<select required ng-disabled="projectDetails.readOnly" required class="form-control" ng-model="projectDetails.engagement_status" ng-change="">
							<option ng-repeat="x in engagementStatus" ng-selected="projectDetails.engagement_status==x">{{x}}</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label">Comments</label>
					<div class="col-sm-8">
						<input type="text"  ng-disabled="projectDetails.readOnly" class="projectField form-control" ng-model="projectDetails.comments" id="" name="" value="" />
					</div>
				</div>
				<div class="form-group hide">
					<label for="name" class="col-sm-4 control-label"></label>
					<div class="col-sm-8">
						<input type="date" ng-disabled="projectDetails.readOnly" class="form-control" id="" ng-model="projectDetails.create_date" name="" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-4 control-label"></label>
					<div class="col-sm-8" style='text-align: left;'>
						<button class="btn btn-primary" ng-disabled="projectDetailsForm.$invalid ||  projectDetails.readOnly" ng-click="saveChanges(projectDetails,engagementEnterpriseApplicationList)" ngdisable="projectDetailsForm" id="" name="" >Save & Continue</button>
					</div>
				</div>
			</form>


		</div>
	</div>
</div>