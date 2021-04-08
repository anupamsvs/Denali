<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default" ng-controller="projectController">
    <div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="projectListHeader" href="#projectList">
        <h4 class="panel-title dashboard_box_title">
            <a>Project Management </a>
        </h4>
    </div>
    
    
    <div id="projectList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="projectListHeader">
        <div class="page_box_content accordion-inner">
            <form class="form-horizontal" role="form">
                <div class="col-md-12">
                    <div class="form-group col-md-5">
                        <label for="name" class="col-md-6 control-label">Client Name:</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" ng-model="searchClientName" id="searchClientName" name="" value="" />
                        </div>
                    </div>
                    <div class="form-group col-md-5">
                        <label for="name" class="col-md-6 control-label">Engagement Name:</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" ng-model="searchEngagementName" id="searchEngagementName" name="" value="" />
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group col-md-5">
                        <label for="name" class="col-md-6 control-label">Status:</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" ng-model="status" id="status" name="" ng-value="" />
                        </div>
                    </div>
                    <div class="form-group col-md-7">
                        <div class="col-md-3"></div>

                        <div class="col-md-9" align="left">
                            <button type="button" class="btn btn-primary" ng-click="searchProject()">Search</button>
                            <button type="button" class="btn btn-primary" ng-click="resetForm()" data-toggle="modal" data-target="#projectModal">&nbsp;&nbsp;New&nbsp;&nbsp;</button>
                            <button type="button" class="btn btn-primary" ng-click="copyProject(selectRadio)">Copy</button>
                            <button type="button" class="btn btn-primary" ng-click="exportProject(selectRadio)">Export</button>
                            <button type="button" class="btn btn-primary" ng-click="calculateSummary(selectRadio)">Calculate / Refresh Summary</button>
                        </div>
                    </div>
                </div>
            </form>
            <div data-backdrop="static" class="modal fade" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
                            <h4 class="modal-title" id="myModalLabel">Create a New Project</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form" ng-submit="saveProject()" name="createEngagementForm">
                                <!--               <div class="form-group">
						         <label for="name" class="col-sm-4 control-label">Version:</label>
						         <div class="col-sm-8">
						             <input type="number" min="1.0" step="0.1" numeric class="form-control" id="" ng-model="version" name="" value=""  />
						         </div>
						     </div> -->
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Project Number<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="text" required class="form-control" id="" ng-model="project_no" name="" value=""  />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Owner<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="text" required class="form-control" id="" ng-model="owner" name="" value=""  />
                                    </div>
                                </div>

                                <datalist id="clientId">
					                <option ng-repeat="clientname in clientList" value="{{clientname}}">{{clientname}}</option>
					            </datalist>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Client Name<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="text" required class="form-control" id="" ng-model="clientName" name="" value=""  list="clientId"/>
                                    </div>
                                </div>
                                <!--  <div class="form-group">
						         <label for="name" class="col-sm-4 control-label">Engagement Name:</label>
						         <div class="col-sm-8">
						             <input type="text" class="form-control" id="" ng-model="engagementName" name="" value=""  />
						         </div>
						     </div> -->
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Engagement Name<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="text" required class="form-control" id="" ng-model="engagementName" name="" value=""  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Engagement Leader<span style="color: red">*</span></label>
                                    <div class="col-sm-8" id="engagementNameDiv" style="position: relative;display: inline-block;">
                                        <input type="text" required class="form-control" id="engagementName" ng-model="engagementLeader" name="" value="" 
                                        />
                                        <ul class="" style="margin:0px;padding:0px;z-index:100;position:absolute;" id="engagementNameList"></ul>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Engagement Leader Email<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="text" required class="form-control" id="engagementLeaderEmail" ng-model="engagementLeaderEmail" name="" value="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Planned Start Date</label>
                                    <div class="col-sm-8">
                                        <input type="date"  class="form-control" id="" ng-model="plannedStartDate" name="" value=""  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Client Sector<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <select class="form-control" ng-model="clientSector" required>
											<option value="CIPS">CIPS</option>
											<option value="Financial Services">Financial Services</option>
											<option value="Forensic Services">Forensic Services</option>
											<option value="GDM">GDM</option>
											<option value="HIA">HIA</option>
											<option value="National-CS">National-CS</option>
											<option value="TICE">TICE</option>
											<option value="TS - Advisory">TS - Advisory</option>
											<option style="display: none" value="">----Select a Client Sector----</option>
										</select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Requester<span style="color: red">*</span></label>
                                    <div class="col-sm-8" id="requesterDiv" style="position: relative;display: inline-block;">
                                        <input type="text" required class="form-control" id="requester" ng-model="requester" name="" value=""  />
                                        <ul class="" style="margin:0px;padding:0px;z-index:100;position:absolute;" id="requesterList"></ul>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Requester Email<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" required id="requesterEmail" ng-model="requesterEmail" name="" value="" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Requested Date<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="date" class="form-control" id="" required ng-model="requestedDate" name="" value=""  />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Number of Business Units<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="number" min="0.0" numeric-only required class="form-control" id="" ng-model="businessUnit" name="" value="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Project duration (Weeks)</label>
                                    <div class="col-sm-8">
                                        <input type="number" min="0" step="1" numeric-only class="form-control" id="" ng-model="duration" name="" value="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Post Go-Live Support (Weeks)<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="number" required min="0" step="1" numeric-only class="form-control" id="" ng-value="0" ng-model="goLiveSupport" name="" value="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">PMO Complexity Factor<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <input type="number" required step="0.01" min="1.0" numeric-only class="form-control" id="" ng-model="client_complexity_factor" name=""
                                            value="" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Seperate Testing Team Deployed<span style="color: red">*</span></label>
                                    <div class="col-sm-8">
                                        <select class="form-control" required ng-model="testing">
                                        	<option ng-checked="Yes" value="Yes">Yes</option>
                                        	<option value="No">No</option>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Comments</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="" ng-model="comments" name="" value="" />
                                        <input type="hidden" ng-model="file_path" />
                                    </div>
                                </div>
						 <div class="form-group">
									<label for="name" class="col-sm-4 control-label">Offerings<span style="color: red">*</span></label>
									<div class="col-sm-8">
										<select ng-disabled="" ng class="form-control offering" id="offering" name="offering" 
												ng-model="offering" 
												ng-options="offering.id as offering.code for offering in offeringList" multiple required>
											<option style="display:none" value="">----Select a offering----</option>
										</select>
										
									</div>
								</div>

                                <!--  
						<form class="form-horizontal" enctype="multipart/form-data" id="uploadForm">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label">UploadFile:</label>
								<div class="col-sm-8">
									<input id="filePath" name="file" type="file">
								</div>
							</div>
						</form>
						-->
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" id="saveProject">Save</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-sm table-striped table-hover no-footer dataTable" ng-init="initialTable(<c:out value="${sessionScope.current_proj_id_token}"/>)" id="engagementTable">
                <thead>
                    <tr>
                        <td style="width:60px;">Select</td>
                        <td style="width:80px;">Project No.</td>
                        <td>Owner</td>
                        <td>Client Name</td>
                        <td>Engagement Name</td>
                        <td>Engagement Leader</td>
                        <td>Planned Start Date</td>
                        <td style="width:80px;">Client Sector</td>
                        <td>Estimation Requester</td>
                        <td>Estimation Requested Date</td>
                        <!-- <td>Number of Business Units</td>
			           <td>Project duration (Weeks)</td>
			           <td>Post Go-Live Support (Weeks)</td> -->
                        <td style="width:80px;">Status</td>
                        <td style="width:80px;">Version</td>
                        <!-- <td>Comments</td> -->
                        <td style="width:160px;">Actions</td>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="engagement in projectTableList | orderBy:'id'">
                        <td><input type="radio" name="selectedproject" ng-model="copyList.selected" value="{{engagement.id}}|{{engagement.engagement_name}}"
                            /></td>
                        <td ng-bind="engagement.project_no"></td>
                        <td ng-bind="engagement.owner"></td>
                        <td ng-bind="engagement.client_name"></td>
                        <td ng-bind="engagement.engagement_name"></td>
                        <td ng-bind="engagement.engagement_leader"></td>
                        <td ng-bind="engagement.planned_project_start_date | date:'MMM-dd-yyyy'"></td>
                        <td ng-bind="engagement.client_sector"></td>
                        <td ng-bind="engagement.estimation_requestor"></td>
                        <td ng-bind="engagement.estimation_requested_date | date:'MMM-dd-yyyy'"></td>
                        <!-- <td ng-bind="engagement.business_units_number"></td>
			            <td ng-bind="engagement.project_duration"></td>
			            <td ng-bind="engagement.post_go_live_support_duration"></td> -->
                        <td ng-bind="engagement.engagement_status"></td>
                        <td ng-bind="engagement.version"></td>
                        <!-- <td ng-bind="engagement.comments"></td> -->
                        <td style="text-align:center;"><button type="button" title="Edit" class="btn btn-primary" ng-click="openDetail(engagement)">
							 <i class="glyphicon glyphicon-edit"></i>
						</button>
                            <button type="button" title="Release" class="btn btn-primary" ng-disabled="(engagement.release == 'true')?false:true" ng-click="showReleaseProjectModal(engagement.id)">
								<i class="glyphicon glyphicon glyphicon-flag"></i>
							</button>
                            <button type="button" title="Share" class="btn btn-primary" ng-disabled="(engagement.release == 'true')?false:true" ng-click="showShareProjectModal(engagement.id)">
							 <i class="glyphicon glyphicon-share-alt"></i>
							</button>
                        </td>
                    </tr>
                    <tr ng-if="projectTableList.length==0">
                        <td colspan='13'><strong>No Record Found!</strong></td>
                    </tr>
                </tbody>
            </table>

            <div data-backdrop="static" class="modal fade" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="shareModal" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
                            <h4 class="modal-title" id="shareToModal">Share This Project</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" ng-submit="shareProject()" role="form">
                                <div class="form-group">
                                    <label for="name" align="left" class="col-sm-2 control-label">Name:</label>
                                    <div class="col-sm-8">
                                        <input type="text"  class="form-control" id="sharee-full-name" name="" value="" />
                                        <input type="hidden" class="form-control" id="sharee" ng-model="shareGUID" name="" value="" />
                                        <ul class="" style="margin:0px;padding:0px;z-index:100;position:absolute;" id="shareeList"></ul>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" align="left" class=" col-sm-2 control-label">Permission:</label>
                                    <div class="col-sm-8">
                                        <select   class="form-control" id="" name="" ng-model="editPermission">
										<option value="read" ng-selected="true">Read</option>
										<option value="edit">Edit</option>
										<option value="release">Release</option>
									</select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="name" align="left" class="col-sm-2 control-label">E-mail:</label>
                                    <div class="col-sm-8">
                                        <input type="text"  class="form-control" id="sharee_name" ng-model="name" name="" value="" />
                                    </div>
                                </div>

                                <div ng-if="(sharednamelist.length < 1	)">Shared with :{{sharednamelist}}</div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" id="saveProject">Share</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>

            <div data-backdrop="static" class="modal fade" id="releaseModal" tabindex="-1" role="dialog" aria-labelledby="releaseModal"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
                            <h4 class="modal-title" id="shareToModal">Are you sure you want to Release the Project ?</h4>
                        </div>
                        <div class="modal-body">
                            <label><input type="checkbox" name="checkbox" ng-checked="true" ng-model="version" value="true">New Version of the project will be created</label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="saveProject" ng-click="releseProject()">Yes</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>

            <div data-backdrop="static" class="modal fade" id="copyProject" tabindex="-1" role="dialog" aria-labelledby="releaseModal"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
                            <h4 class="modal-title" id="shareToModal">What would you like to copy from <b>{{copyList.projName}}</b> ?</h4>
                        </div>

                        <div class="modal-body">
                            <form ng-submit="copyProjectProc()">
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Project No.:</label>
                                    <div class="col-sm-8">
                                        <input required  class="form-control" type="text"   ng-model="copyList.project_no" />
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="form-group" style="padding-top:10px">
	                                <label class="checkbox-inline"><input type="checkbox" value=""  ng-checked="true" ng-model="copyList.module" value="true">Module</label>
									<label class="checkbox-inline"><input type="checkbox" value=""  ng-checked="true" ng-model="copyList.rice" value="true">RIE</label>
									<label class="checkbox-inline"><input type="checkbox" value=""  ng-checked="true" ng-model="copyList.module" value="true">Conversion</label>
									<label class="checkbox-inline"><input type="checkbox" value=""  ng-checked="true" ng-model="copyList.addServices" value="true">Additional Services</label>
	                                <label class="checkbox-inline"><input type="checkbox" value=""  ng-checked="true" ng-model="copyList.rates" value="true">Rates</label>
	                       
	                               
	                              </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary" id="saveProject">Yes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <div data-backdrop="static" class="modal fade" id="exportProjectModal" tabindex="-1" role="dialog" aria-labelledby="exportProjectModal"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
                            <h4 class="modal-title" id="shareToModal">Would you like to export <b>{{copyList.projName}}</b> ?</h4>
                        </div>

                        <div class="modal-body">
                            <form ng-submit="exportProjectProc()">
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary" id="saveProject">Yes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".viewProject").click(function () {
            $("[id^=Portal]").addClass("hide");
            $("#projectManagement").removeClass("active");
            $("#Details").addClass("active");
            $("#Portal-Details").removeClass("hide");
            $(".projectField").attr("readOnly", true);
            $("#EditSaveDiv").remove();

        });
    });

</script>