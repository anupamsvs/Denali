<div class="panel panel-default">
	<div class="panel-heading accordion-toggle" data-toggle="collapse" role="tab" id="attachmentListHeader" href="#attachmentList">
		<h4 class="panel-title dashboard_box_title">
			<a>Attachment </a>
		</h4>
	</div>
	<div id="attachmentList" class="panel-collapse accordion-body collapse in" role="tabpanel" aria-labelledby="attachmentListHeader">
		<div class="page_box_content accordion-inner">
			<form class="form-horizontal">
				<div class="form-group col-md-4">
					<input id="inputfile" name="file" type="file">
				</div>
			</form>

			<table class="table table-bordered table-sm table-striped table-hover no-footer dataTable" ng-init="initialTable()">
				<thead>
					<tr>
						<td class="col-sm-9">File Name</td>
						<td class="col-sm-2">Upload Date</td>
						<td class="col-sm-1">Actions</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="item in attachmentitems">
						<td ng-bind="item.file_name_original"></td>
						<td ng-bind="item.upload_time"></td>
						<td><a ng-href="project/download?filepath={{item.file_path}}" ng-model="item.file_path" class="btn btn-primary"> <span class="glyphicon glyphicon-download"></span>
						</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
	estimatorApp
			.controller(
					'AttachmentMainController',
					[
							'$scope',
							'$http',
							'$rootScope',
							function($scope, $http, $rootScope) {
								
								
								$scope.init = function() {
									$("#zhezhao").showLoading();
									var currentProjectId = $rootScope.currentProjectId;
									$http
											.get(
													"project/findAllAttachAttachment?id="
															+ currentProjectId)
											.success(
													function(response) {
														$rootScope.attachmentitems = response;
														$("#zhezhao").hideLoading();
													});
									$("#inputfile").fileinput('destroy');
									$("#inputfile")
											.fileinput(
													{
														uploadUrl : "project/upload?id="
																+ currentProjectId,

														showPreview : false,

														elErrorContainer : "#fileError",

														browseClass : "btn btn-primary",

														browseLabel : "Upload",

														browseIcon : '<i ></i>',

														removeClass : "btn btn-primary",

														removeLabel : "Remove",

														removeIcon : '<i ></i>',

														showUpload : false,

														uploadClass : "btn btn-info",

														uploadIcon : '<i ></i>',

														enctype : 'multipart/form-data',

														maxFileSize : 0

													})
											.on(
													"filebatchselected",
													function(event, files) {
														$(this).fileinput(
																"upload");
													})
											.on(
													"fileuploaded",
													function(event, data) {
														if (data.response == "success") {
															$http
																	.get(
																			"project/findAllAttachAttachment?id="
																					+ currentProjectId)
																	.success(
																			function(
																					response) {
																				$rootScope.attachmentitems = response;
																			});
														} else {
															toastr
																	.error(
																			"Errors occurred while uploading file. Please try again later.")
																	.css(
																			"width",
																			"600px");
														}

													});
								}
							} ]);
</script>
