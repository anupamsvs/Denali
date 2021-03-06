package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "engagement")
public class Engagement implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "guid")
	private String guid;
	//Added by Soumya
	@Column(name = "owner")
	private String owner;
	
	//Added by Ananth
	@Column(name = "version")
	private Integer version;
	
	@Column(name = "released_by")
	private String released_by;
	
	@Column(name = "project_no")
	private String project_no;
	
	@Column(name = "parent")
	private Integer parent;
	// EoA
	
	@Column(name = "engagement_status")
	private String engagement_status;

	@Column(name = "client_name")
	private String client_name;

	@Column(name = "engagement_name")
	private String engagement_name;

	@Column(name = "engagement_leader")
	private String engagement_leader;
	
	@Column(name = "engagement_leader_email")
	private String engagement_leader_email;

	@Column(name = "planned_project_start_date")
	@DateTimeFormat(pattern = "MMM-dd-yyyy")
	private Date planned_project_start_date;

	@Column(name = "client_sector")
	private String client_sector;

	@Column(name = "estimation_requestor")
	private String estimation_requestor;
	
	@Column(name = "estimation_requestor_email")
	private String estimation_requestor_email;

	@Column(name = "estimation_requested_date")
	@DateTimeFormat(pattern = "MMM-dd-yyyy")
	private Date estimation_requested_date;

	@Column(name = "comments")
	private String comments;

	@Column(name = "business_units_number")
	private Integer business_units_number;

	@Column(name = "project_duration")
	private Double project_duration;

	@Column(name = "post_go_live_support_duration")
	private Double post_go_live_support_duration;

	@Column(name = "create_date", nullable = false)
	@DateTimeFormat(pattern = "MMM-dd-yyyy")
	private Date create_date;

	@Column(name = "update_date", nullable = false)
	@DateTimeFormat(pattern = "MMM-dd-yyyy")
	private Date update_date;
	
	
	@Column(name ="client_complexity_factor")
	private Double client_complexity_factor;
	
	@Column(name ="testing")
	private String testing;
	
	public String getTesting() {
		return testing;
	}

	public void setTesting(String testing) {
		this.testing = testing;
	}

	@Column(name = "offering")
	private String offering;
	
	//@Column(name = "file_path")
	//private String file_path;
	

	public String getOffering() {
		return offering;
	}

	public void setOffering(String offering) {
		this.offering = offering;
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
	
	public void setOwner(String owner) {
		this.owner =  owner;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setVersion(Integer version) {
		this.version =  version;
	}

	public Integer getVersion() {
		return version;
	}
	
	
	
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

	public void setReleasedBy(String released_by) {
		this.released_by =  released_by;
	}

	public String getReleasedBy() {
		return released_by;
	}
	
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
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

//	public String getFile_path() {
//		return file_path;
//	}
//
//	public void setFile_path(String file_path) {
//		this.file_path = file_path;
//	}

	public String getEngagement_status() {
		return engagement_status;
	}

	public void setEngagement_status(String engagement_status) {
		this.engagement_status = engagement_status;
	}

	public Engagement(Integer id, String guid, String engagement_status,String client_name, String engagement_name, String engagement_leader,
			String engagement_leader_email,
			Date planned_project_start_date, String client_sector, String estimation_requestor,String estimation_requestor_email,
			Date estimation_requested_date, String comments, Integer business_units_number, Double project_duration,
			Double post_go_live_support_duration, Date create_date, Date update_date,String file_path, String owner, String released_by, Integer version, Integer parent,Double client_complexity_factor,String offering, String project_no, String testing) {
		this.id = id;
		this.guid = guid;
		this.owner = owner;
		this.released_by = released_by;
		this.parent = parent;
		this.version = version;
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
		//this.file_path = file_path;
		this.testing = testing;
		this.client_complexity_factor = client_complexity_factor;
		this.project_no = project_no;
		this.offering =offering;
	}

	public Engagement() {
	}
}
