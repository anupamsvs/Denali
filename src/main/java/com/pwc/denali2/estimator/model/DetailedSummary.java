//detailed_summary
package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "detailed_summary")
public class DetailedSummary implements Serializable {
	//Class Starts	
	
	private static final long serialVersionUID = -5299958714235447525L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//Join engagement_id with engagement
	@Column(name = "engagement_id")
	private int engagement_id;
	
	//Join Activity_id with activity
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "activity_id")
	@PrimaryKeyJoinColumn
	private ActivityManagementData activity_id;

	
	@Column(name = "effort_onsite",nullable=true)
	private double effort_onsite;
	
	@Column(name = "explanation",nullable=true)
	private String explanation;
	
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	@Column(name = "effort_ddc",nullable=true)
	private double effort_ddc;
	@Column(name = "csp_onsite",nullable=true)
	private double csp_onsite;
	@Column(name = "csp_ddc",nullable=true)
	private double csp_ddc;
	@Column(name = "rsr_onsite",nullable=true)
	private double rsr_onsite;
	@Column(name = "rsr_ddc",nullable=true)
	private double rsr_ddc;
	@Column(name = "margin_onsite",nullable=true)
	private double margin_onsite;
	@Column(name = "margin_ddc",nullable=true)
	private double margin_ddc;
	
	
	//Update Dates
	 @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_at", nullable = false)
	    private Date created;
	
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "updated_at", nullable = false)
	    private Date updated;
	
	    @PrePersist
	    protected void onCreate() {
	    updated = created = new Date();
	    }
	
	    @PreUpdate
	    protected void onUpdate() {
	    updated = new Date();
	    }
	
	//Getters & Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public int getEngagement() {
		return engagement_id;
	}
	public void setEngagement(int engagement_id) {
		this.engagement_id = engagement_id;
	}
	public ActivityManagementData getActivity() {
		return activity_id;
	}
	public void setActivity(ActivityManagementData activity) {
		this.activity_id = activity;
	}
	public double getEffort_onsite() {
		return effort_onsite;
	}
	public void setEffort_onsite(double effort_onsite) {
		this.effort_onsite = effort_onsite;
	}
	public double getEffort_ddc() {
		return effort_ddc;
	}
	public void setEffort_ddc(double effort_ddc) {
		this.effort_ddc = effort_ddc;
	}
	public double getCsp_onsite() {
		return csp_onsite;
	}
	public void setCsp_onsite(double csp_onsite) {
		this.csp_onsite = csp_onsite;
	}
	public double getCsp_ddc() {
		return csp_ddc;
	}
	public void setCsp_ddc(double csp_ddc) {
		this.csp_ddc = csp_ddc;
	}
	public double getRsr_onsite() {
		return rsr_onsite;
	}
	public void setRsr_onsite(double rsr_onsite) {
		this.rsr_onsite = rsr_onsite;
	}
	public double getRsr_ddc() {
		return rsr_ddc;
	}
	public void setRsr_ddc(double rsr_ddc) {
		this.rsr_ddc = rsr_ddc;
	}
	public double getMargin_onsite() {
		return margin_onsite;
	}
	public void setMargin_onsite(double margin_onsite) {
		this.margin_onsite = margin_onsite;
	}
	public double getMargin_ddc() {
		return margin_ddc;
	}
	public void setMargin_ddc(double margin_ddc) {
		this.margin_ddc = margin_ddc;
	}
	//Constructor
	
	public DetailedSummary() {
		
		// TODO Auto-generated constructor stub
	}
	public DetailedSummary(Integer id, int engagement_id, ActivityManagementData activity_id,
			double effort_onsite, double effort_ddc, double csp_onsite,
			double csp_ddc, double rsr_onsite, double rsr_ddc,
			double margin_onsite, double margin_ddc, String explanation) {
		super();
		this.id = id;
		this.effort_onsite = effort_onsite;
		this.effort_ddc = effort_ddc;
		this.csp_onsite = csp_onsite;
		this.csp_ddc = csp_ddc;
		this.rsr_onsite = rsr_onsite;
		this.rsr_ddc = rsr_ddc;
		this.margin_onsite = margin_onsite;
		this.margin_ddc = margin_ddc;
		this.explanation = explanation;
		this.activity_id = activity_id;
	}
	
		
//Class Ends	
}