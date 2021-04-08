package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

	public class Engagement_share_permission implements Serializable {
		private static final long serialVersionUID = -4666770189399685284L;

		
		private Integer id;

		
		private String guid;
		
		
		private String owner;

		
		private Double version;
		
		private String released_by;
		private String offering;
		private String testing;
		public String getOffering() {
			return offering;
		}

		public String getTesting() {
			return testing;
		}

		public void setTesting(String testing) {
			this.testing = testing;
		}

		public void setOffering(String offering) {
			this.offering = offering;
		}

		private String project_no;
		
		public String getReleased_by() {
			return released_by;
		}

		public void setReleased_by(String released_by) {
			this.released_by = released_by;
		}

		public String getProject_no() {
			return project_no;
		}

		public void setProject_no(String project_no) {
			this.project_no = project_no;
		}

		private String engagement_status;

	
		private String client_name;


		private String engagement_name;
		
		private String read;
		

		private String edit;
		
		
		private String release;

		private String engagement_leader;
		
		private String engagement_leader_email;

		

		private Date planned_project_start_date;

		private String client_sector;

		private String estimation_requestor;
		
		private String estimation_requestor_email;

	

		private Date estimation_requested_date;

		private String comments;

		private Integer business_units_number;

		private Double project_duration;

		private Double post_go_live_support_duration;

		private Date create_date;

		private Date update_date;
	
		private Double client_complexity_factor;
		


		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public Double getVersion() {
			return version;
		}

		public void setVersion(Double version) {
			this.version = version;
		}

	

		public Double getClient_complexity_factor() {
			return client_complexity_factor;
		}

		public void setClient_complexity_factor(Double client_complexity_factor) {
			this.client_complexity_factor = client_complexity_factor;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}
		
		
		public String getClient_name() {
			return client_name;
		}

		public void setClient_name(String client_name) {
			this.client_name = client_name;
		}

		public String getEngagement_name() {
			return engagement_name;
		}

		public void setEngagement_name(String engagement_name) {
			this.engagement_name = engagement_name;
		}

		public String getEngagement_leader() {
			return engagement_leader;
		}

		public void setEngagement_leader(String engagement_leader) {
			this.engagement_leader = engagement_leader;
		}
		
		public String getEngagement_leader_email() {
			return engagement_leader_email;
		}

		public void setEngagement_leader_email(String engagement_leader_email) {
			this.engagement_leader_email = engagement_leader_email;
		}

		public Date getPlanned_project_start_date() {
			return planned_project_start_date;
		}

		public void setPlanned_project_start_date(Date planned_project_start_date) {
			this.planned_project_start_date = planned_project_start_date;
		}

		public String getClient_sector() {
			return client_sector;
		}

		public void setClient_sector(String client_sector) {
			this.client_sector = client_sector;
		}

		public String getEstimation_requestor() {
			return estimation_requestor;
		}

		public void setEstimation_requestor(String estimation_requestor) {
			this.estimation_requestor = estimation_requestor;
		}
		
		public String getEstimation_requestor_email() {
			return estimation_requestor_email;
		}

		public void setEstimation_requestor_email(String estimation_requestor_email) {
			this.estimation_requestor_email = estimation_requestor_email;
		}

		public Date getEstimation_requested_date() {
			return estimation_requested_date;
		}

		public void setEstimation_requested_date(Date estimation_requested_date) {
			this.estimation_requested_date = estimation_requested_date;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public Integer getBusiness_units_number() {
			return business_units_number;
		}

		public void setBusiness_units_number(Integer business_units_number) {
			this.business_units_number = business_units_number;
		}

		public Double getProject_duration() {
			return project_duration;
		}
		
		public void setProject_duration(Double project_duration) {
			this.project_duration = project_duration;
		}

		public Double getPost_go_live_support_duration() {
			return post_go_live_support_duration;
		}

		public void setPost_go_live_support_duration(Double post_go_live_support_duration) {
			this.post_go_live_support_duration = post_go_live_support_duration;
		}

		public Date getCreate_date() {
			return create_date;
		}

		public void setCreate_date(Date create_date) {
			this.create_date = create_date;
		}

		public Date getUpdate_date() {
			return update_date;
		}

		public void setUpdate_date(Date update_date) {
			this.update_date = update_date;
		}

//		public String getFile_path() {
//			return file_path;
//		}
	//
//		public void setFile_path(String file_path) {
//			this.file_path = file_path;
//		}

		public String getEngagement_status() {
			return engagement_status;
		}

		public void setEngagement_status(String engagement_status) {
			this.engagement_status = engagement_status;
		}
		
		public String getRead() {
			return read;
		}

		public void setRead(String read) {
			this.read = read;
		}

		public String getEdit() {
			return edit;
		}

		public void setEdit(String edit) {
			this.edit = edit;
		}

		public String getRelease() {
			return release;
		}

		public void setRelease(String release) {
			this.release = release;
		}

		
		public Engagement_share_permission(Integer id, String guid, String engagement_status,String client_name, String engagement_name, String engagement_leader,
				String engagement_leader_email,
				Date planned_project_start_date, String client_sector, String estimation_requestor,String estimation_requestor_email,
				Date estimation_requested_date, String comments, Integer business_units_number, Double project_duration,
				Double post_go_live_support_duration, Date create_date, Date update_date,String file_path,Double client_complexity_factor,Double version,String owner,String read, String edit, String release,String project_no,String released_by, String offering, String testing) {
			this.id = id;
			this.guid = guid;
			this.version =version;
			this.owner = owner ;
			this.engagement_status = engagement_status;
			this.client_name = client_name;
			this.engagement_name = engagement_name;
			this.engagement_leader = engagement_leader;
			this.engagement_leader_email = engagement_leader_email;
			this.planned_project_start_date = planned_project_start_date;
			this.client_sector = client_sector;
			this.estimation_requestor = estimation_requestor;
			this.estimation_requestor_email = estimation_requestor_email;
			this.estimation_requested_date = estimation_requested_date;
			this.comments = comments;
			this.business_units_number = business_units_number;
			this.project_duration = project_duration;
			this.post_go_live_support_duration = post_go_live_support_duration;
			this.create_date = create_date;
			this.update_date = update_date;
			this.client_complexity_factor = client_complexity_factor;
			this.read = read;
			this.edit = edit;
			this.release = release;
			this.project_no = project_no;
			this.released_by = released_by;
			this.offering = offering;
			this.testing = testing;
		}

		public Engagement_share_permission() {
		}
	}


