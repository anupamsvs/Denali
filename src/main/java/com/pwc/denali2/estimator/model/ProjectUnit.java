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
@Table(name = "project_units")
public class ProjectUnit implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "engagement_id")
	private Integer engagement_id;
	
	@Column(name = "master_rates_id")
	private Integer master_rates_id;
	
	@Column(name = "value")
	private Double value;
	

	@Column(name = "unit")
	private String unit;

	



	
	public String getUnit() {
		return unit;
	}







	public void setUnit(String unit) {
		this.unit = unit;
	}







	public ProjectUnit(Integer id, Integer engagement_id,
			Integer master_rates_id, Double value) {
		super();
		this.id = id;
		this.engagement_id = engagement_id;
		this.master_rates_id = master_rates_id;
		this.value = value;
	}







	public Integer getId() {
		return id;
	}







	public void setId(Integer id) {
		this.id = id;
	}







	public Integer getEngagement_id() {
		return engagement_id;
	}







	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}







	public Integer getMaster_rates_id() {
		return master_rates_id;
	}







	public void setMaster_rates_id(Integer master_rates_id) {
		this.master_rates_id = master_rates_id;
	}







	public Double getValue() {
		return value;
	}







	public void setValue(Double value) {
		this.value = value;
	}







	public ProjectUnit() {
	}
}
