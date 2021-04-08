<div class="panel panel-default" ng-controller="SummaryEffortController">

	<div class="panel-heading accordion-toggle" data-toggle="collapse"
		role="tab" id="AdditionalServiceHeadingTree"
		href="#AdditionalServiceDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Summary Efforts</a>
		</h4>
	</div>
	<div id="SummaryEffortList"
		class="panel-collapse accordion-body collapse in" role="tabpanel"
		aria-labelledby="SummaryEffortsHeadingTree">
		<div class="page_box_content accordion-inner">
		
		
		

			<div class="row">

				<div class="col-md-12">
					<div class="panel with-nav-tabs panel-default">
						<div class="panel-heading">
							
						</div>
						<div class="panel-body shadow-tab-container">
						<ul id="summary-effort" class="nav nav-tabs shadow-tabs">
							<li class="active"><a href="#tab3default" data-toggle="tab">Total Project Efforts and Margins</a></li>
								<li><a href="#tab1default" data-toggle="tab">Project
										Efforts</a></li>
								<li><a href="#tab2default" data-toggle="tab">Additional
										Service Efforts</a></li>
								
							   			
							</ul>
							<div class="tab-content">
								<div class="tab-pane fade" id="tab1default">
									<div>
										<table
											class="table table-bordered table-sm table-striped table-hover no-footer summarytable"
											id="casetable"
											ng-repeat="list in workstreamLst |orderBy:list.description">

											<thead ng-if="$index < 1">
											   <tr><td colspan="13">Project Efforts</td></tr>
												<tr>

													
                                                    <td class="col-md-1" style="width: 100px;" rowspan="2">WorkStream</td>
													<td class="col-md-1" style="width: 12%;" rowspan="2">Prototype</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">Effort(Hr)</td>

													<td class="col-md-1" style="width: 20%;" colspan="2">CSP
														Price($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">RSR
														Price($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">Margin($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="3">Rate/Hour($)</td>
												</tr>

												<tr>
													
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>

													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Total</td>



												</tr>

											</thead>
											<tbody>

												<tr
													ng-repeat="item in getDta(list.code,resForProjectEfforts)" ng-if="item.totalEffortWorkstreamRates == null">

													<td><span>{{item.workstream}}</span></td>
													<td><span>{{item.prototype}}</span></td>
													<td class="currency"><span>{{item.sumEffortWorkstream.toFixed(1)}}</span></td>
													<td class="currency"><span>{{item.sumEffortDDC.toFixed(1)}}</span></td>
													<td class="currency"><span>{{item.sumCSPRates | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.sumCSPRatesDDC | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.sumRsrRates | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.sumRsrRatesDDC | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.marginWorkstream | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.marginDDc | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.RateHrOnsite | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.RateHrDDC | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.RateHrTotal | currency:'$':0}}</span></td>




												</tr>
												<tr class="total-row">
													<td colspan="2"><span><b>Total</b></span></td>

													<td class="currency"><b><span>{{roundMeToDec(getSumValue(list.code,'totalEffortWorkstreamRates',resForProjectEfforts),1)}}</span></b></td>
													<td class="currency"><b><span>{{roundMeToDec(getSumValue(list.code,'totalEffortDDCRates',resForProjectEfforts),1)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumCspWorkstreamRates',resForProjectEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumCspDDCRates',resForProjectEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumRsrWorkstreamRates',resForProjectEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumRsrDDCRates',resForProjectEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumMarginWorkstream',resForProjectEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumMarginDDC',resForProjectEfforts))}}</span></b></td>
													<td colspan="3"></td>

												</tr>

												<tr ng-if="$index == workstreamLst.length -1" class="total-row">
													<td colspan="2"><span><b>Grand Total</b></span></td>

													<td class="currency"><b><span>{{roundMeToDec(totalProjectEfforts.OverallTotalEffortsOnsite,1)}}</span></b></td>
													<td class="currency"><b><span>{{roundMeToDec(totalProjectEfforts.OverallTotalEffortsDDC,1)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalProjectEfforts.OverallCspRateOnsite)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalProjectEfforts.OverallCspRateDDC)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalProjectEfforts.OverallRsrRateOnsite)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalProjectEfforts.OverallRsrRateDDC)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalProjectEfforts.OverallTotalMarginOnsite)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalProjectEfforts.OverallTotalMarginDDC)}}</span></b></td>
													<td colspan="3"></td>

												</tr>



											</tbody>
										</table></div>
									</div>
								
<!-- 											tab add serv div -->
							




							<div class="tab-pane fade" id="tab2default">
								<div>
									<table
										class="table table-bordered table-sm table-striped table-hover no-footer summarytable"
										id="casetable"
										ng-repeat="list in workstreamLst |orderBy:list.description">

										<thead ng-if="$index < 1">
										    <tr><td colspan="13">Additional Services Efforts</td></tr>
											<tr>

													
                                                    <td class="col-md-1" style="width: 100px;" rowspan="2">WorkStream</td>
													<td class="col-md-1" style="width: 12%;" rowspan="2">Prototype</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">Effort(Hr)</td>

													<td class="col-md-1" style="width: 20%;" colspan="2">CSP
														Price($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">RSR
														Price($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">Margin($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="3">Rate/Hour($)</td>
												</tr>

												<tr>
													
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>

													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Total</td>



												</tr>

										</thead>
										<tbody>

											<tr ng-repeat="item in getDta(list.code,resForAddServices)" ng-if="item.totalEffortWorkstreamRates == null">

												<td><span>{{item.workstream}}</span></td>
												<td><span>{{item.prototype}}</span></td>
												<td class="currency"><span>{{item.sumEffortWorkstream.toFixed(1)}}</span></td>
												<td class="currency"><span>{{item.sumEffortDDC.toFixed(1)}}</span></td>
												<td class="currency"><span>{{item.sumCSPRates | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.sumCSPRatesDDC | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.sumRsrRates | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.sumRsrRatesDDC | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.marginWorkstream | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.marginDDc | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.RateHrOnsite | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.RateHrDDC | currency:'$':0}}</span></td>
												<td class="currency"><span>{{item.RateHrTotal | currency:'$':0}}</span></td>




											</tr>
											<tr class="total-row">
												<td colspan="2"><span><b>Total</b></span></td>

												<td  class="currency"><b><span>{{roundMeToDec(getSumValue(list.code,'totalEffortWorkstreamRates',resForAddServices),1)}}</span></b></td>
												<td class="currency"><b><span>0</span></b></td>
												<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumCspWorkstreamRates',resForAddServices))}}</span></b></td>
												<td class="currency"><b>$<span>0</span></b></td>
												<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumRsrWorkstreamRates',resForAddServices))}}</span></b></td>
												<td class="currency"><b>$<span>0</span></b></td>
												<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumMarginWorkstream',resForAddServices))}}</span></b></td>
												<td class="currency"><b>$<span>0</span></b></td>
												<td colspan="3"></td>

											</tr>

											<tr ng-if="$index == workstreamLst.length -1" class="total-row">
												<td colspan="2"><span><b>Grand Total</b></span></td>

												<td class="currency"><b><span>{{roundMeToDec(totalAdditionalEfforts.OverallTotalEffortsOnsite,2)}}</span></b></td>
												<td class="currency"><b><span>0</span></b></td>
												<td class="currency"><b>$<span>{{roundMe(totalAdditionalEfforts.OverallCspRateOnsite)}}</span></b></td>
												<td class="currency"><b>$<span>0</span></b></td>
												<td class="currency"><b>$<span>{{roundMe(totalAdditionalEfforts.OverallRsrRateOnsite)}}</span></b></td>
												<td class="currency"><b>$<span>0</span></b></td>
												<td class="currency"><b>$<span>{{roundMe(totalAdditionalEfforts.OverallTotalMarginOnsite)}}</span></b></td>
												<td class="currency"><b>$<span>0</span></b></td>
												<td colspan="3"></td>

											</tr>



										</tbody>
									</table>
								</div>
							</div>
<!-- 										tab addservice div -->

                           <div class="tab-pane fade in active" id="tab3default">
                           <table
											class="table table-bordered table-sm table-striped table-hover no-footer summarytable"
											id="casetable"
											ng-repeat="list in workstreamLst |orderBy:list.description">

											<thead ng-if="$index < 1">
											    <tr><td colspan="13">Total Project Efforts and Margins</td></tr>
												<tr>

													
                                                    <td class="col-md-1" style="width: 100px;" rowspan="2">WorkStream</td>
													<td class="col-md-1" style="width: 12%;" rowspan="2">Prototype</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">Effort(Hr)</td>

													<td class="col-md-1" style="width: 20%;" colspan="2">CSP
														Price($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">RSR
														Price($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="2">Margin($)</td>
													<td class="col-md-1" style="width: 20%;" colspan="3">Rate/Hour($)</td>
												</tr>

												<tr>
													
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>

													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Onsite</td>
													<td class="col-md-1" style="width: 12%;">DDC</td>
													<td class="col-md-1" style="width: 100px;">Total</td>



												</tr>
											</thead>
											<tbody>

												<tr
													ng-repeat="item in getDta(list.code,totalResForSumaaryEfforts)" ng-if="item.totalEffortWorkstreamRates == null">

													<td ><span>{{item.workstream}}</span></td>
													<td ><span>{{item.prototype}}</span></td>
													<td class="currency"><span>{{item.sumEffortWorkstream.toFixed(1)}}</span></td>
													<td class="currency"><span>{{item.sumEffortDDC.toFixed(1)}}</span></td>
													<td class="currency"><span>{{item.sumCSPRates | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.sumCSPRatesDDC | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.sumRsrRates | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.sumRsrRatesDDC | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.marginWorkstream | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.marginDDc | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.RateHrOnsite | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.RateHrDDC | currency:'$':0}}</span></td>
													<td class="currency"><span>{{item.RateHrTotal | currency:'$':0}}</span></td>




												</tr>
												<tr class="total-row">
													<td colspan="2"><span><b>Total</b></span></td>

													<td class="currency"><b><span>{{roundMeToDec(getSumValue(list.code,'totalEffortWorkstreamRates',totalResForSumaaryEfforts),1) }}</span></b></td>
													<td class="currency"><b><span>{{roundMeToDec(getSumValue(list.code,'totalEffortDDCRates',totalResForSumaaryEfforts,1))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumCspWorkstreamRates',totalResForSumaaryEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumCspDDCRates',totalResForSumaaryEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumRsrWorkstreamRates',totalResForSumaaryEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumRsrDDCRates',totalResForSumaaryEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumMarginWorkstream',totalResForSumaaryEfforts))}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(getSumValue(list.code,'totalSumMarginDDC',totalResForSumaaryEfforts))}}</span></b></td>
													<td colspan="3"></td>

												</tr>

												<tr ng-if="$index == workstreamLst.length -1" class="total-row">
													<td colspan="2"><span><b>Grand Total</b></span></td>

													<td class="currency"><b><span>{{roundMeToDec(totalSummaryEfforts.OverallTotalEffortsOnsite,1)}}</span></b></td>
													<td class="currency"><b><span>{{roundMeToDec(totalSummaryEfforts.OverallTotalEffortsDDC,1)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalSummaryEfforts.OverallCspRateOnsite)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalSummaryEfforts.OverallCspRateDDC)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalSummaryEfforts.OverallRsrRateOnsite)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalSummaryEfforts.OverallRsrRateDDC)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalSummaryEfforts.OverallTotalMarginOnsite)}}</span></b></td>
													<td class="currency"><b>$<span>{{roundMe(totalSummaryEfforts.OverallTotalMarginDDC)}}</span></b></td>
													<td colspan="3"></td>

												</tr>



											</tbody>
										</table>
									  <div style="margin: 15px;" class="row">						
		                        <table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
									<tr><td style="width: 10%;" rowspan="2"><b>Grand Total</b></td>
									<td style="width: 10%;"><b>Effort (Hr)</b></td>
									<td style="width: 10%;"><b>CSP Total($)</b></td>
									<td style="width: 10%;"><b>RSR Total($)</b></td>
									<td style="width: 10%;"><b>Margin Total($)</b></td>
									<td style="width: 10%;"><b>Rate/hr($)</b></td>
									</tr>
									<tr>
									<td style="width: 10%;">{{roundMeToDec(totalSummaryEfforts.GrandTotalEfforts,1)}}</td>
									<td style="width: 10%;">{{roundMe(totalSummaryEfforts.GrandTotalCSP)}}</td>
									<td style="width: 10%;">{{roundMe(totalSummaryEfforts.GrandTotalRSR)}}</td>
									<td style="width: 10%;">{{roundMe(totalSummaryEfforts.GrandTotalMargin)}}</td>
									<td style="width: 10%;">{{roundMe(totalSummaryEfforts.GrandRatePerHr)}}</td></tr>
									</table>
									</div>
									
									<div style="margin: 15px;" class="row">
									<div class="col-md-6" >
									<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
									<tr><td rowspan="2" style="width: 10%;"><b>Directional Project Margin<br/>Note eFit still required</b></td>
									<td style="width: 10%;"><b>Engagement(%)</b></td>
									<td style="width: 10%;"><b>DDC(%)</b></td>
									
									</tr>
									<tr>
									<td style="width: 10%;">{{totalSummaryEfforts.directionalProjectMarginEng}}</td>
									<td style="width: 10%;">{{totalSummaryEfforts.directionalProjectMarginDdc}}</td>
									
									</tr>
									</table></div>
									
									<div class="col-md-6"><table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
									<tr>
									<td style="width: 10%;"><b>Total Project Margin(%)</b></td></tr>
									<tr><td style="width: 10%;">{{totalSummaryEfforts.project_margin}}</td>
									</tr></table>
									</div></div>
									
									
									
									<div style="margin: 25px;" class="row">
									
									<div class="col-md-4" >
									<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
									<tr>
									<td style="width: 10%;"><b>Prototypes</b></td>
									<td style="width: 10%;"><b>Duration(Weeks)</b></td>
									
									</tr>
									
									<tr ng-repeat ="item in protypeSplitList"">
									<td style="width: 10%;">{{item.code}}</td>
									
									  <td ng-if="item.id == '62'" style="width: 10%;">{{projectDetails.post_go_live_support_duration}}</td>
									<td  ng-if="item.id != '62'" style="width: 10%;">{{roundMe(projectDurationPrototypSplit[item.id])}}</td>
									 </tr>
								
									</table>
									</div>
									
									<div class="col-md-4"><table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
									<tr>
									<td style="width: 10%;"><b>Prototypes</b></td>
									<td style="width: 10%;"><b>Duration(Weeks)</b></td>
									
									</tr>
									<tr><td style="width: 10%;">Project Duration</td>
									
									     <td style="width: 10%;">{{roundMe(sumProtypsplit)}}</td>
									 </tr>
									 <tr><td style="width: 10%;">Post Go Live</td>
									     <td style="width: 10%;">{{projectDetails.post_go_live_support_duration}}</td>
									 </tr>
									
									
									</table>
									</div>
									
									<div class="col-md-4"><table class="table table-bordered table-sm table-striped table-hover no-footer dataTable">
									
									<tr>
									<td style="width: 10%;" colspan="2"><b>Projected Model Duration</b></td>
									</tr>
									
									<tr>
									<td style="width: 10%;"><b>Prototypes</b></td>
									<td style="width: 10%;"><b>Duration(Weeks)</b></td>
									
									</tr>
									<tr><td style="width: 10%;">Project Duration</td>
									     <td style="width: 10%;">{{roundMe(sumProtypsplit)}}</td>
									 </tr>
									
									</table>
									</div>
									
									
									
									</div>
									
										
									</div>
									
									
									
							</div>

								
                      
						</div>
					</div>
					  
                      
				</div>

			</div>







		</div>
	</div>
</div>