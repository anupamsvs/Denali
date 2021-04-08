	<div class="modal fade" id="UnitProjectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ng-controller="projctUnitsController">
	<div class="modal-dialog" role="document" style="width:60%">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"> Project Units</h4>
				
			</div>
			<div class="modal-body">
			      <form class="form-horizontal" role="form"  name="unitForm">
		     <table  cellspacing="0" width="100%" class="table table-bordered table-sm table-striped table-hover no-footer dataTable"  id="masterAdmintable" >
			    <thead>
			        <tr>
			        
			          <th align="center">Unit</th>
			          <th align="center">Base Variable</th>
			          <th align="center">Value</th>
			         
<!-- 			          <th align="center">Action</th> -->
			          
			        </tr>
			    </thead>
			     <tbody>
			       <tr ng-repeat="list in ProjectUnitsData" >

							<td ng-bind="list.columnName.split('####')[0]"></td>
							<td ng-bind="list.columnName.split('####')[1]"></td>

							<td ng-bind="list.value"></td>


					</tr>
                  </tbody>
             </table>
             </form>
		</div>
	</div>
</div></div>
<script>
	estimatorApp.controller('projctUnitsController', [
			'$scope',
			'$http',
			'$rootScope',
		
			function($scope, $http, $rootScope) {
				$rootScope.unitList =[];

				      $rootScope.loadUnitTable = function(){
				    	  console.log("into load units");

				    	  var idparam = $rootScope.currentProjectId;
							console.log(idparam);
							
							$http({
								url:'ProjectUnits/loadUnit',
								responseType:'json',
								method : 'GET',
								params :{
									idparam:idparam
								}
							}).success(function(response){
							    $scope.masterUnitsData =response.masterUnitsData;
							    //$scope.ProjectUnitsData =data.ProjectUnitsData;
							    $scope.ProjectUnitsData =response.ProjectUnitsData;
							    
								
								
							
							});
						
				  	  
				    }
				      
				
				
				
				
			} ]);
	
</script>

 

