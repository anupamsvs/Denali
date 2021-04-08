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
import javax.persistence.Table;

@Entity
@Table(name = "offshore_csp_rate_metadata")
public class OffshoreCspRateMetadata implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "work_stream_id")
	private WorkStream workStream;

	@Column(name = "offshore_landed_cost")
	private Double offshore_landed_cost;

	@Column(name = "manager_cost")
	private Double manager_cost;

	@Column(name = "staff_cost")
	private Double staff_cost;

	@Column(name = "create_date")
	private Date create_date;

	@Column(name = "update_date")
	private Date update_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WorkStream getWorkStream() {
		return workStream;
	}

	public void setWorkStream(WorkStream workStream) {
		this.workStream = workStream;
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

	public OffshoreCspRateMetadata(Integer id, WorkStream workStream, Double offshore_landed_cost, Double manager_cost,
			Double staff_cost, Date create_date, Date update_date) {
		this.id = id;
		this.workStream = workStream;
		this.offshore_landed_cost = offshore_landed_cost;
		this.manager_cost = manager_cost;
		this.staff_cost = staff_cost;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	public OffshoreCspRateMetadata() {
	}
}
