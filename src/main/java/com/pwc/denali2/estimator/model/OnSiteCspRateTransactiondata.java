package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "onsite_csp_rate_transaction_data")
public class OnSiteCspRateTransactiondata implements Serializable {
	private static final long serialVersionUID = 7466990213002793299L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "work_stream_id")
	private Integer work_stream_id;

	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "partner_cost")
	private Double partner_cost;

	@Column(name = "director_cost")
	private Double director_cost;

	@Column(name = "manager_cost")
	private Double manager_cost;

	@Column(name = "senior_associate_cost")
	private Double senior_associate_cost;

	@Column(name = "associate_cost")
	private Double associate_cost;

	@Column(name = "update_date")
	private Date update_date;

	@Column(name = "version")
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWork_stream_id() {
		return work_stream_id;
	}

	public void setWork_stream_id(Integer work_stream_id) {
		this.work_stream_id = work_stream_id;
	}

	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public Double getPartner_cost() {
		return partner_cost;
	}

	public void setPartner_cost(Double partner_cost) {
		this.partner_cost = partner_cost;
	}

	public Double getDirector_cost() {
		return director_cost;
	}

	public void setDirector_cost(Double director_cost) {
		this.director_cost = director_cost;
	}

	public Double getManager_cost() {
		return manager_cost;
	}

	public void setManager_cost(Double manager_cost) {
		this.manager_cost = manager_cost;
	}

	public Double getSenior_associate_cost() {
		return senior_associate_cost;
	}

	public void setSenior_associate_cost(Double senior_associate_cost) {
		this.senior_associate_cost = senior_associate_cost;
	}

	public Double getAssociate_cost() {
		return associate_cost;
	}

	public void setAssociate_cost(Double associate_cost) {
		this.associate_cost = associate_cost;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public OnSiteCspRateTransactiondata(Integer id, Integer work_stream_id, Integer engagement_id, Double partner_cost,
			Double director_cost, Double manager_cost, Double senior_associate_cost, Double associate_cost,
			Date update_date, Integer version) {
		this.id = id;
		this.work_stream_id = work_stream_id;
		this.engagement_id = engagement_id;
		this.partner_cost = partner_cost;
		this.director_cost = director_cost;
		this.manager_cost = manager_cost;
		this.senior_associate_cost = senior_associate_cost;
		this.associate_cost = associate_cost;
		this.update_date = update_date;
		this.version = version;
	}

	public OnSiteCspRateTransactiondata() {
	}
}
