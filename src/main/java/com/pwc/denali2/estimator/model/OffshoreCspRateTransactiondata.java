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
@Table(name = "offshore_csp_rate_transaction_data")
public class OffshoreCspRateTransactiondata implements Serializable {
	private static final long serialVersionUID = 7466990213002793299L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "work_stream_id")
	private Integer work_stream_id;

	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "offshore_landed_cost")
	private Double offshore_landed_cost;

	@Column(name = "manager_cost")
	private Double manager_cost;

	@Column(name = "staff_cost")
	private Double staff_cost;

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

	public Double getOffshore_landed_cost() {
		return offshore_landed_cost;
	}

	public void setOffshore_landed_cost(Double offshore_landed_cost) {
		this.offshore_landed_cost = offshore_landed_cost;
	}

	public Double getManager_cost() {
		return manager_cost;
	}

	public void setManager_cost(Double manager_cost) {
		this.manager_cost = manager_cost;
	}

	public Double getStaff_cost() {
		return staff_cost;
	}

	public void setStaff_cost(Double staff_cost) {
		this.staff_cost = staff_cost;
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

	public OffshoreCspRateTransactiondata(Integer id, Integer work_stream_id, Integer engagement_id,
			Double offshore_landed_cost, Double manager_cost, Double staff_cost, Date update_date, Integer version) {
		this.id = id;
		this.work_stream_id = work_stream_id;
		this.engagement_id = engagement_id;
		this.offshore_landed_cost = offshore_landed_cost;
		this.manager_cost = manager_cost;
		this.staff_cost = staff_cost;
		this.update_date = update_date;
		this.version = version;
	}

	public OffshoreCspRateTransactiondata() {
	}
}
