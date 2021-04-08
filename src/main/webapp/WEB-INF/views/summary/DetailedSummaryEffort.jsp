<div ng-controller="detailSummaryController" class="panel edit-paint panel-default">
	<div class="panel panel-default">
		<div class="panel-heading accordion-toggle" data-toggle="collapse"
			role="tab" id="ConversionCountHeadingTree"
			href="#ConversionCountHeadingDataList">
			<h4 class="panel-title dashboard_box_title">
				<a>Detailed Summary Effort</a>
			</h4>
		</div>
		<div id="ConversionCountHeadingDataList"
			class="panel-collapse accordion-body collapse in" role="tabpanel"
			aria-labelledby="ConversionCountHeadingTree">
			<div ng-repeat="workstream in allEffortSummary.workstream" ng-if="allEffortSummary.groups[workstream.id+''].length > 0" class="page_box_content accordion-inner">
				<hr/>
				<table  class="summarytable table inner-table-container table-bordered table-sm table-striped table-hover no-footer ">
				  <caption>
				    {{workstream.code}}
				  </caption>
								
				<thead>
				<tr >
				<th colspan="3"></th>
				<th colspan="2" ng-repeat="prototype in allEffortSummary.prototype">{{prototype.code}}</th>		
				<th  colspan="3" >Total Revenue</th>
				<th  colspan="3" >Total Hours</th>
				</tr>
				<tr>
				
				<th colspan="3">Activity Group </th>
				
				<th ng-repeat-start="prototype in allEffortSummary.prototype">Onsite</th>
    			<th ng-repeat-end>DDC</th>
				
				<th >Onsite</th>
				<th >DDC</th>
				<th >Total Revenue</th>
				
				<th >Onsite</th>
				<th >DDC</th>
				<th >Total Hours</th>
				</tr>
				</thead>
				<tbody>
				<tr ng-repeat="item in allEffortSummary.groups[workstream.id+'']">
					<td colspan="3" >{{item.activity_group}}</td>
					<td class="currency"  ng-repeat-start="proto in allEffortSummary.prototype" ng-init='detailEffortOnsite[workstream.id][item.activity_group]  = sumMe(trackMe(workstream.id+"_"+proto.id+"_"+item.activity_group,"onsite",workstream.id) , detailEffortOnsite[workstream.id][item.activity_group],"new")' >{{trackMe(workstream.id+"_"+proto.id+"_"+item.activity_group,"onsite",workstream.id) | currency:"$":0}}</td>
					<!-- Sum columns  -->
					<td style="display:none"  ng-init='detailEffortByColumnOnsite[workstream.id][proto.id]=sumMe(trackMe(workstream.id+"_"+proto.id+"_"+item.activity_group,"onsite",workstream.id) , detailEffortByColumnOnsite[workstream.id][proto.id])'></td>
					<td style="display:none"  ng-init='detailEffortByColumnDdc[workstream.id][proto.id]=sumMe(trackMe(workstream.id+"_"+proto.id+"_"+item.activity_group,"ddc",workstream.id) , detailEffortByColumnDdc[workstream.id][proto.id])'></td>
					
					<td  class="currency"  ng-repeat-end ng-init='detailEffortDdc[workstream.id][item.activity_group]  = sumMe(detailEffortDdc[workstream.id][item.activity_group] , trackMe(workstream.id+"_"+proto.id+"_"+item.activity_group,"ddc",workstream.id))' >{{trackMe(workstream.id+"_"+proto.id+"_"+item.activity_group,"ddc",workstream.id) | currency:"$":0}}</td>
					
					
					<!-- Sum rates -->
					<td  class="currency" >{{detailEffortOnsite[workstream.id][item.activity_group] | currency:"$":0}}</td>
					<td  class="currency" >{{detailEffortDdc[workstream.id][item.activity_group] | currency:"$":0}}</td>
					<td  class="currency" >{{sumMe(detailEffortOnsite[workstream.id][item.activity_group] ,detailEffortDdc[workstream.id][item.activity_group]) | currency:"$":0}}</td>
					<!-- SUM hours -->
					<td ng-init="totalEffortByColumnOnsite[workstream.id] = sumMe(totalEffortByColumnOnsite[workstream.id],detailEffortOnsite[item.activity_group],'test');totalHrEffortByColumnOnsite[workstream.id] = sumMe(item.effort_onsite,totalHrEffortByColumnOnsite[workstream.id])">{{sumMe(item.effort_onsite,0)}}</td>
					<td ng-init="totalEffortByColumnDdc[workstream.id] = sumMe(detailEffortDdc[workstream.id][item.activity_group],detailEffortOnsite[item.activity_group],'test');totalHrEffortByColumnDdc[workstream.id] = sumMe(item.effort_ddc,totalHrEffortByColumnDdc[workstream.id])">{{sumMe(item.effort_ddc,0)}}</td>
					<td >{{sumMe(item.effort_onsite,item.effort_ddc)}}</td>
					
				</tr>
				<tr class="total-row">
					<td class="currency" colspan="3">Total</td>
					<td class="currency"  ng-repeat-start="proto in allEffortSummary.prototype"  >{{detailEffortByColumnOnsite[workstream.id][proto.id] | currency:"$":0}}</td>
					<td class="currency"  ng-repeat-end="proto in allEffortSummary.prototype"  >{{detailEffortByColumnDdc[workstream.id][proto.id] | currency:"$":0}}</td>
				
					<td class="currency" >{{totalEffortByColumnOnsite[workstream.id] |currency:"$":0 }}</td>
					<td class="currency" >{{totalEffortByColumnDdc[workstream.id] |currency:"$":0 }}</td>
					<td class="currency" >{{sumMe(totalEffortByColumnOnsite[workstream.id],totalEffortByColumnDdc[workstream.id]) |currency:"$":0}}</td>
					
					<td>{{totalHrEffortByColumnOnsite[workstream.id] }}</td>
					<td>{{totalHrEffortByColumnDdc[workstream.id] }}</td>
					<td>{{sumMe(totalHrEffortByColumnOnsite[workstream.id],totalHrEffortByColumnDdc[workstream.id]) }}</td>
				</tr>
				</tbody>
				</table>
			</div>
		</div>

	</div>
</div>