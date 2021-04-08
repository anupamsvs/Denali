<!--  ng-controller="AdditionalServiceEffortController" ng-click="initEffort()" -->
<div class="panel panel-default">

	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="AdditionalServiceHeadingTree" href="#AdditionalServiceDataList">
		<h4 class="panel-title dashboard_box_title">
			<a>Additional Services Effort</a>
		</h4>
	</div>
	<div id="AdditionalServiceDataList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="AdditionalServiceHeadingTree">
		<div class="page_box_content accordion-inner">
			<table class="table table-bordered table-sm table-striped table-hover no-footer summarytable" id="casetable" ng-repeat="list in workstreamList |orderBy:list.description">
				<thead ng-if="$index < 1">
					<tr>
					   
						<td class="col-md-1" style="width:10%;" rowspan="2">WorkStream</td>
						<td class="col-md-1" style="width:10%;" rowspan="2">Service Owner</td>
						
						<td class="col-md-1" style="width:20%;" colspan="2">Effort(Hr)</td>
						
						<td class="col-md-1" style="width:20%;" colspan="2">CSP Price($)</td>
						<td class="col-md-1" style="width:20%;" colspan="2">RSR Price($)</td>
						<td class="col-md-1" style="width:20%;"colspan="2">Margin($)</td>
					</tr>
					
					<tr>
<!-- 						<td class="col-md-1" style="width:100px;">WorkStream</td> -->
<!-- 						<td class="col-md-1" style="width:12%;" >Service Owner</td> -->
						<td class="col-md-1" style="width:10%;">WorkStream</td>
						<td class="col-md-1" style="width:10%;" >DDC</td>
						
						<td class="col-md-1" style="width:10%;">WorkStream</td>
						<td class="col-md-1" style="width:10%;" >DDC</td>
						<td class="col-md-1" style="width:10%;">WorkStream</td>
						<td class="col-md-1" style="width:10%;" >DDC</td>
						<td class="col-md-1" style="width:10%;">WorkStream</td>
						<td class="col-md-1" style="width:10%;" >DDC</td>
						
						
					</tr>
					
				</thead>
				<tbody>

					<tr ng-repeat="item in getDta(list.code)" ng-if="item.totalEffortWorkstreamRates == null">
					
					   <td style="width:10%;"><span>{{item.workstream}}</span></td>
					    <td style="width:10%;" ><span>{{item.prototype}}</span></td>
					     <td style="width:10%;"  class="currency"><span>{{item.sumEffortWorkstream.toFixed(1)}}</span></td>
					      <td style="width:10%;" class="currency"><span>{{item.sumEffortDDC}}</span></td>
					       <td style="width:10%;" class="currency"><span>{{item.sumCSPRates| currency:'$':0}}</span></td>
					        <td style="width:10%;"class="currency"><span>{{item.sumCSPRatesDDC| currency:'$':0}}</span></td>
					         <td style="width:10%;" class="currency"><span>{{item.sumRsrRates | currency:'$':0}}</span></td>
					          <td style="width:10%;" class="currency"><span>{{item.sumRsrRatesDDC| currency:'$':0}}</span></td>
					           <td style="width:10%;" class="currency"><span>{{item.marginWorkstream | currency:'$':0}}</span></td>
					            <td style="width:10%;" class="currency"><span>{{item.marginDDc | currency:'$':0}}</span></td>
					            
						
					
						
					</tr>
					<tr class="total-row">
					<td colspan="2" style="width:20%;"><span><b>Total</b></span></td>
					   
					     <td style="width:10%;"  class="currency"><b><span>{{getSumValue(list.code,'totalEffortWorkstreamRates').toFixed(1)}}</span></b></td>
					      <td style="width:10%;"  class="currency"><b><span>0</span></b></td>
					       <td style="width:10%;" class="currency"><b><span>{{getSumValue(list.code,'totalSumCspWorkstreamRates') | currency:'$':0}}</span></b></td>
					        <td style="width:10%;" class="currency"><b><span>$0</span></b></td>
					         <td style="width:10%;" class="currency"><b><span>{{getSumValue(list.code,'totalSumRsrWorkstreamRates') | currency:'$':0}}</span></b></td>
					          <td style="width:10%;" class="currency"><b><span>$0</span></b></td>
					           <td style="width:10%;" class="currency"><b><span>{{getSumValue(list.code,'totalSumMarginWorkstream') | currency:'$':0}}</span></b></td>
					            <td style="width:10%;" class="currency"><b><span>$0</span></b></td>
					
					</tr>
					

					
				</tbody>
			</table>
		</div>
	</div>
</div>

<script>
	estimatorApp
			.controller(
					'AdditionalServiceEffortController',
					[
							'$scope',
							'$http',
							'$rootScope',
							function($scope, $http, $rootScope) {
								
								$scope.initEffort = function() {
									$("#zhezhao").showLoading();
									var currentProjectId = $rootScope.currentProjectId;
									
									$http
											.get(
													"Additional/findAdditionalServiceEffortData?id="
															+ currentProjectId)
											.success(
													function(response) {
														//console.log(response);
														$rootScope.result = response;
														
														$rootScope.workstreamList =response.workstream;
														//console.log($rootScope.workstreamList);
														$("#zhezhao").hideLoading();		
													});

									

								}
								
								$rootScope.getDta =function(stream){
									var data =$rootScope.result;
								
									return data[stream];
								}
								
								$rootScope.roundMe =function(data){
									if(data == undefined){
										return 0;
									}
									return Math.round(data);
									
								}
								
								
								$rootScope.getSumValue= function(stream,field){
									var data =$rootScope.result;
									
									var stramDta= data[stream];
									for(var	i=0;i<stramDta.length;i++){
										
										if(field in stramDta[i]){
											
											return stramDta[i][field];
										}
										
									}
									
									
								}
								
								

							} 
							
							
							
							
							]);

</script>