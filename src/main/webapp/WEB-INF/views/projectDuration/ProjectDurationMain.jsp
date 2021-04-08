<div class="panel panel-default"
	ng-controller="projectDurationController">
  <div class="panel-heading accordion-toggle" id="AdditionalServiceHeadingTree" href="#AdditionalServiceDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Project Duration</a>
			
	          <div class="right-head-btn">
	           <button class="btn btn-primary btn-save function-button" 
				ng-disabled="projectDetailsForm.$invalid ||  projectDetails.readOnly"
				ng-click="saveChanges()" ngdisable="projectDetailsForm" id=""
				name="" value="&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;">Save</button>
	             </div>
	
		</h4>
	</div>

	             
	
	
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default" style="height: 150px">
			
			

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<a>Module Complexity Multiplier</a> -->
<!-- 					</h4> -->
<!-- 				</div> -->

				<div id="projectDurationList"
					class="panel-collapse accordion-body collapse in" role="tabpanel"
					aria-labelledby="projectDurationHeadingTree">
					<div class="page_box_content accordion-inner">

						<table
							class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
							<thead>

							</thead>

							<tbody>
                                <tr><th colspan="2" style="text-align: center;">Module Complexity Multiplier</th></tr>
								<tr>
									<td class="col-md-4">Module Complexity Multiplier</td>
									<td><input type= "number" min="0.0" class="form-control" step="0.1"
										ng-model="maxModWtgAndCmplxtyFctr['complexity_multiplier']"></td>
								</tr>

							</tbody>


							</tbody>
						</table>

					</div>
					
				</div>
			</div>
			
				<div class="panel panel-default" style="height: 310px">

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<a>RIE Factor</a> -->
<!-- 					</h4> -->

<!-- 				</div> -->

				<div id="projectDurationList"
					class="panel-collapse accordion-body collapse in" role="tabpanel"
					aria-labelledby="projectDurationHeadingTree">
					<div class="page_box_content accordion-inner">
						<table
							class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true">
							<thead>

							</thead>

							<tbody>
                                <tr><th colspan="2" style="text-align: center;">RIE Factor</th></tr>
								<tr>

									<td class="col-md-4">Planned Weeks</td>
									<td><input type="number" min="0.0" class="form-control"
										ng-model="riceEffortInputMap['planningWeeks']"></td>

								</tr>


								<tr>

									<td class="col-md-4">No of Resources for planned Weeks</td>
									<td><input type="number" min="0.0" class="form-control"
										ng-model="riceEffortInputMap['resourcesForWeeks']"></td>

								</tr>
								<tr>
									<td class="col-md-4">No of Resources for additional Weeks</td>
									<td><input type="number" min="0.0" class="form-control"
										ng-model="riceEffortInputMap['resourcesForplusWeeks']"></td>


								</tr>
								<tr>

									<td class="col-md-4">No of Hours a Week per resource</td>
									<td>40</td>
								</tr>

							</tbody>
						</table>
					</div>
					
				</div>
			</div>
		</div>
		
			<div class="col-md-4" >
			<div class="panel panel-default" style="height: 480px">

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<a>Module Effort Input</a> -->
<!-- 					</h4> -->
<!-- 				</div> -->

				<div id="projectDurationList"
					class="panel-collapse accordion-body collapse in" role="tabpanel"
					aria-labelledby="projectDurationHeadingTree">
					<div class="page_box_content accordion-inner">

						<table
							class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true">
							<thead>

							</thead>

							<tbody>
							<tr><th colspan="2" style="text-align: center;">Module Effort Input</th></tr>
								<td colspan="2" bgcolor="white">Module Weightage</td>
								<tr>
									<td class="col-md-2">Max Module Weightage</td>
									<td>{{maxModWtgAndCmplxtyFctr["max_Module_Weightage"]}}</td>
								</tr>

							</tbody>


							<tbody>
								<td colspan="2" bgcolor="white">Module Multiplier</td>
								<tr>

									<td class="col-md-2">Total No of Module</td>
									<td>{{moduleMultiplierMap["numOfModules"]}}</td>

								</tr>
								<tr>

									<td class="col-md-2">Module Multiplier</td>
									<td>{{moduleMultiplierMap["moduleMultiplier"]}}</td>

								</tr>
							</tbody>

							<tbody>
								<td colspan="2" bgcolor="white">Process Area Multiplier</td>
								<tr>

									<td class="col-md-2">Total In-Scope Process Areas</td>
									<td>{{moduleMultiplierMap["numInScopePA"]}}</td>

								</tr>
								<tr>
									<td class="col-md-2">Process Area Multiplier</td>
									<td>{{moduleMultiplierMap["processAreaMultiplier"]}}</td>

								</tr>
							</tbody>

							<tbody>
								<td colspan="2" bgcolor="white">Implementation-Module Weeks</td>

								<tr>

									<td class="col-md-2">Duration Factor</td>
									<td ng-bind="projDurationFactor"></td>

								</tr>
								<tr>
									<td class="col-md-2">Module Weeks</td>
									<td ng-bind="roundNum(moduleWeeks)"></td>

								</tr>
							</tbody>
						</table>

					</div>
					
				</div>
			</div>
		</div>
		
			<div class="col-md-4">
	   
	             
	             
	             
			<div class="panel panel-default" style="height: 230px">

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<a>Project Implementation Duration(Weeks)/Calculated</a> -->
<!-- 					</h4> -->

<!-- 				</div> -->

				<div id="projectDurationList"
					class="panel-collapse accordion-body collapse in" role="tabpanel"
					aria-labelledby="projectDurationHeadingTree">
					<div class="page_box_content accordion-inner">
						<table
							class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true">
							<thead>

							</thead>

							<tbody>
                               <tr><th colspan="2" style="text-align: center;">Project Implementation Duration(Weeks)/Calculated</th></tr>
								<tr>

									<td class="col-md-4">Project Implementation
										Duration(Weeks)</td>
									<td>{{roundNum(riceEffortInputMap["project_Impl_Duration"])}}</td>

								</tr>
								<tr>
									<td class="col-md-4">User Override for Project Duration
										(Weeks)</td>

									<td>{{roundNum(proj_duration)}}</td>
								</tr>
								<tr>
									<td class="col-md-4">Post Go-Live Support (Weeks)</td>

									<td>{{roundNum(post_go_live_support_duration)}}</td>
								</tr>
							</tbody>
						</table>
					</div>

					
				</div>
			</div>
			
		
			<div class="panel panel-default" style="height: 230px">

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<a>Project Implementation Duration(Weeks)/Final</a> -->
<!-- 					</h4> -->

<!-- 				</div> -->

				<div id="projectDurationList"
					class="panel-collapse accordion-body collapse in" role="tabpanel"
					aria-labelledby="projectDurationHeadingTree">
					<div class="page_box_content accordion-inner">
						<table
							class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true">
							<thead>

							</thead>

							<tbody>
                                <tr><th colspan="2" style="text-align: center;">Project Implementation Duration(Weeks)/Final</th></tr>
								<tr>

									<td class="col-md-4">Project Implementation
										Duration(Weeks)</td>
									<td>{{roundNum(riceEffortInputMap["durationWeeks"])}}</td>

								</tr>
								<tr>
									<td class="col-md-4">&nbsp &nbsp &nbsp Post Go-Live Support &nbsp &nbsp &nbsp &nbsp (Weeks) </td>

									<td>{{roundNum(riceEffortInputMap["pgLive_Duration"])}}</td>
								</tr>
								<tr>
									<td class="col-md-4">Total Project Duration</td>

									<td>{{roundNum(riceEffortInputMap["total_Duration_Weeks"])}}</td>
								</tr>
							</tbody>
						</table>
					</div>
                  </div>	</div>
		
			
			
			
		</div>
	
	
	</div>
	
	
	
	
	
	
<!-- 	<div class="form-group"> -->
<!-- 		<label for="name" class="col-sm-4 control-label"></label> -->
<!-- 		<div class="col-sm-5" style='text-align: justify; vertical-align:top; padding-top: 70px; padding-bottom: 70px'> -->
<!-- 			<button class="btn btn-primary" -->
<!-- 				ng-disabled="projectDetailsForm.$invalid ||  projectDetails.readOnly" -->
<!-- 				ng-click="saveChanges()" ngdisable="projectDetailsForm" id="" -->
<!-- 				name="" value="&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;">Save</button> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<div class="row">

		
			<div class="col-md-6">
				<div class="panel panel-default"  style="height: 350px">

<!-- 					<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 						id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 						<h4 class="panel-title dashboard_box_title"> -->
<!-- 							<a>Additional (RIE + CONV) Effort Input</a> -->
<!-- 						</h4> -->

<!-- 					</div> -->

					<div id="projectDurationList"
						class="panel-collapse accordion-body collapse in" role="tabpanel"
						aria-labelledby="projectDurationHeadingTree">
						<div class="page_box_content accordion-inner">

							<table
								class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true">
								<thead>

								</thead>

								<tbody>
                                   <tr><th colspan="2" style="text-align: center;">Additional (RIE + CONV) Effort Input</th></tr>
									<tr>

										<td class="col-md-4">Total (RIE + CONV) Hours</td>
										<td>{{riceEffortInputMap["total_rice_hours"]}}</td>

									</tr>
									<tr>

										<td class="col-md-4">P2 Prototype Weeks</td>
										<td>{{roundNum(riceEffortInputMap["p2_Prototype_Weeks"])}}</td>

									</tr>
									<tr>

										<td class="col-md-4">Max Effort without Impact (Hrs)</td>
										<td>{{riceEffortInputMap["max_Rice_Effort_Hours"]}}</td>

									</tr>
									<tr>

										<td class="col-md-4" draggable="true" >Extra Effort (Hrs)</td>
										<td>{{riceEffortInputMap["rice_Extra_Hours"]}}</td>

									</tr>
									<tr>

										<td class="col-md-4">Extra Weeks</td>
										<td>{{roundNum(riceEffortInputMap["rice_Extra_Weeks"])}}</td>

									</tr>
								</tbody>
							</table>

						</div>
						
					</div>
				</div>
			</div>

<!-- 		<div class="col-md-4"> -->
<!-- 			<div class="panel panel-default"> -->

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<a>Project Implementation Duration(Weeks)/Final</a> -->
<!-- 					</h4> -->

<!-- 				</div> -->

<!-- 				<div id="projectDurationList" -->
<!-- 					class="panel-collapse accordion-body collapse in" role="tabpanel" -->
<!-- 					aria-labelledby="projectDurationHeadingTree"> -->
<!-- 					<div class="page_box_content accordion-inner"> -->
<!-- 						<table -->
<!-- 							class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true"> -->
<!-- 							<thead> -->

<!-- 							</thead> -->

<!-- 							<tbody> -->

<!-- 								<tr> -->

<!-- 									<td class="col-md-4">Project Implementation -->
<!-- 										Duration(Weeks)</td> -->
<!-- 									<td>{{roundNum(riceEffortInputMap["durationWeeks"])}}</td> -->

<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="col-md-4">&nbsp &nbsp &nbsp Post Go-Live Support &nbsp &nbsp &nbsp &nbsp (Weeks) </td> -->

<!-- 									<td>{{roundNum(riceEffortInputMap["pgLive_Duration"])}}</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="col-md-4">Total Project Duration</td> -->

<!-- 									<td>{{roundNum(riceEffortInputMap["total_Duration_Weeks"])}}</td> -->
<!-- 								</tr> -->
<!-- 							</tbody> -->
<!-- 						</table> -->
<!-- 					</div> -->
<!--                   </div>	</div> -->
<!-- 		</div> -->
					<div class="col-md-6">
								<div class="panel panel-default" style="height: 350px">

<!-- 				<div class="panel-heading accordion-toggle" role="tab" -->
<!-- 					id="projectDurationHeadingTree" href="#projectDurationList"> -->
<!-- 					<h4 class="panel-title dashboard_box_title"> -->
<!-- 						<td><a>Prototypes</a></td> -->
						
<!-- 					</h4> -->

<!-- 				</div> -->

				<div id="projectDurationList"
					class="panel-collapse accordion-body collapse in" role="tabpanel"
					aria-labelledby="projectDurationHeadingTree">
					<div class="page_box_content accordion-inner">
						<table
							class="table table-bordered table-sm table-striped table-hover no-footer dataTable" draggable="true">
							<thead>
							<tr><th colspan="2" style="text-align: center;">Prototypes</th></tr>
								<tr>
									<th style="text-align: center;">Prototypes</th>
									<th style="text-align: center;">Weeks</th>
								</tr>
							</thead>

							<tbody>
							
								<tr ng-repeat ="item in PrototypSplitListMasterList">
								<td>{{item.code}}</td>

									<td  ng-if="item.id != '62'">{{prototypSplitValue[item.id+'']}}</td>
									
									<td ng-if="item.id == '62'">{{projectDetails.post_go_live_support_duration}}</td>
								
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
					</div>
				
		
	</div>



</div>
