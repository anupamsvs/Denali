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
@Table(name = "project_duration_transaction_data")
public class ProjectDurationData implements Serializable{
	private static final long serialVersionUID = -4666770189399685288L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@Column(name = "mod_cplex_multiplier")
	private Double mod_cplex_multiplier;

	@Column(name = "engagement_id")
	private Integer engagement_id;
	
	@Column(name="mod_weeks")
	private Double mod_weeks;


	@Column(name = "ovride_weeks")
	private Double ovride_weeks;

	@Column(name = "rice_extra_weeks")
	private Double rice_extra_weeks;


	@Column(name = "conversion_extra_weeks")
	private Double conversion_extra_weeks;

	@Column(name = "proj_impl_duration")
	private Double proj_impl_duration;

	@Column(name = "tot_proj_duration")
	private Double tot_proj_duration;

	@Column(name = "planning_weeks")
	private Double planning_weeks;

	@Column(name = "resources_for_planned_weeks")
	private Double resources_for_planned_weeks;

	@Column(name = "resources_for_plus_weeks")
	private Double resources_for_plus_weeks;



	public Double getMod_cplex_multiplier() {
		return mod_cplex_multiplier;
	}

	public void setMod_cplex_multiplier(Double mod_cplex_multiplier) {
		this.mod_cplex_multiplier = mod_cplex_multiplier;
	}

	public Double getMod_weeks() {
		return mod_weeks;
	}

	public void setMod_weeks(Double mod_weeks) {
		this.mod_weeks = mod_weeks;
	}

	public Double getOvride_weeks() {
		return ovride_weeks;
	}

	public void setOvride_weeks(Double ovride_weeks) {
		this.ovride_weeks = ovride_weeks;
	}

	public Double getRice_extra_weeks() {
		return rice_extra_weeks;
	}

	public void setRice_extra_weeks(Double rice_extra_weeks) {
		this.rice_extra_weeks = rice_extra_weeks;
	}

	public Double getConversion_extra_weeks() {
		return conversion_extra_weeks;
	}

	public void setConversion_extra_weeks(Double conversion_extra_weeks) {
		this.conversion_extra_weeks = conversion_extra_weeks;
	}

	public Double getProj_impl_duration() {
		return proj_impl_duration;
	}

	public void setProj_impl_duration(Double proj_impl_duration) {
		this.proj_impl_duration = proj_impl_duration;
	}

	public Double getTot_proj_duration() {
		return tot_proj_duration;
	}

	public void setTot_proj_duration(Double tot_proj_duration) {
		this.tot_proj_duration = tot_proj_duration;
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

	public Double getPlanning_weeks() {
		return planning_weeks;
	}

	public void setPlanning_weeks(Double planning_weeks) {
		this.planning_weeks = planning_weeks;
	}

	public Double getResources_for_planned_weeks() {
		return resources_for_planned_weeks;
	}

	public void setResources_for_planned_weeks(Double resources_for_planned_weeks) {
		this.resources_for_planned_weeks = resources_for_planned_weeks;
	}

	

	public ProjectDurationData(Integer id,
			Double mod_cplex_multiplier, Double mod_weeks,
			Double ovride_weeks, Double rice_extra_weeks,
			Double conversion_extra_weeks, Double proj_impl_duration,
			Double tot_proj_duration, Double p1_duration,
			Double p2_duration, Double p3_duration, Double p4_duration,
			Double planning_weeks, Double resources_for_planned_weeks,
			Double resources_after_planned_weeks, Integer engagement_id, Double resources_for_plus_weeks) {
		super();
		this.id =id;
		this.mod_cplex_multiplier = mod_cplex_multiplier;
		this.mod_weeks = mod_weeks;
		this.ovride_weeks = ovride_weeks;
		this.rice_extra_weeks = rice_extra_weeks;
		this.conversion_extra_weeks = conversion_extra_weeks;
		this.proj_impl_duration = proj_impl_duration;
		this.tot_proj_duration = tot_proj_duration;
		this.engagement_id =engagement_id;
		this.planning_weeks = planning_weeks;
		this.resources_for_planned_weeks = resources_for_planned_weeks;
		this.resources_for_plus_weeks = resources_for_plus_weeks;
	}
	public Double getResources_for_plus_weeks() {
		return resources_for_plus_weeks;
	}

	public void setResources_for_plus_weeks(Double resources_for_plus_weeks) {
		this.resources_for_plus_weeks = resources_for_plus_weeks;
	}

	public ProjectDurationData() {
		// TODO Auto-generated constructor stub
	}


}

