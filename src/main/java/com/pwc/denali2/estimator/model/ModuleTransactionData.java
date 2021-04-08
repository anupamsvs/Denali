package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "module_transaction_data")
public class ModuleTransactionData implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "business_process_id")
	private Integer business_process_id;
	
	@Column(name = "cloud_offering")
	private Integer cloud_offering;
	
	
	@Column(name = "job_aid")
	private String job_aid;

	@Column(name = "workshop")
	private String workshop;
	
	
	public String getJob_aid() {
		return job_aid;
	}

	public void setJob_aid(String job_aid) {
		this.job_aid = job_aid;
	}

	public String getWorkshop() {
		return workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "complexity_factor")
	private Float complexity_factor;

	@Column(name = "module_name")
	private String module_name;

	@Column(name = "module_abbreviation")
	private String module_abbreviation;
	
	@Column(name = "module_duration_factor")
	private Double module_duration_factor;
	
	@Column(name = "moduleweightage")
	private Double moduleweightage;

	@Column(name = "ddc_supported_flag")
	private String ddc_supported_flag;

	@Column(name = "is_in_scope_flag")
	private String is_in_scope_flag;

	@Column(name = "number_of_requirements")
	private Integer number_of_requirements;

	@Column(name = "number_of_gaps")
	private Integer number_of_gaps;

	@Column(name = "version")
	private Integer version;

	@Column(name = "update_date")
	private Date update_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusiness_process_id() {
		return business_process_id;
	}
	
	public void setBusiness_process_id(Integer business_process_id) {
		this.business_process_id = business_process_id;
	}
	
	public Integer getCloud_offering() {
		return cloud_offering;
	}

	public void setCloud_offering(Integer cloud_offering) {
		this.cloud_offering = cloud_offering;
	}

	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public Float getComplexity_factor() {
		return complexity_factor;
	}

	public void setComplexity_factor(Float complexity_factor) {
		this.complexity_factor = complexity_factor;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getModule_abbreviation() {
		return module_abbreviation;
	}

	public void setModule_abbreviation(String module_abbreviation) {
		this.module_abbreviation = module_abbreviation;
	}

	public String getDdc_supported_flag() {
		return ddc_supported_flag;
	}

	public void setDdc_supported_flag(String ddc_supported_flag) {
		this.ddc_supported_flag = ddc_supported_flag;
	}

	public String getIs_in_scope_flag() {
		return is_in_scope_flag;
	}

	public void setIs_in_scope_flag(String is_in_scope_flag) {
		this.is_in_scope_flag = is_in_scope_flag;
	}

	public Integer getNumber_of_requirements() {
		return number_of_requirements;
	}

	public void setNumber_of_requirements(Integer number_of_requirements) {
		this.number_of_requirements = number_of_requirements;
	}

	public Integer getNumber_of_gaps() {
		return number_of_gaps;
	}

	public void setNumber_of_gaps(Integer number_of_gaps) {
		this.number_of_gaps = number_of_gaps;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Double getModule_duration_factor() {
		return module_duration_factor;
	}

	public void setModule_duration_factor(Double module_duration_factor) {
		this.module_duration_factor = module_duration_factor;
	}

	public Double getModuleweightage() {
		return moduleweightage;
	}

	public void setModuleweightage(Double moduleweightage) {
		this.moduleweightage = moduleweightage;
	}

	public ModuleTransactionData(Integer id, Integer business_process_id, Integer engagement_id,
			Float complexity_factor, String module_name, String module_abbreviation, String ddc_supported_flag,
			Double module_duration_factor,Double moduleweightage,String is_in_scope_flag, Integer number_of_requirements, Integer number_of_gaps, Integer version,
			Date update_date) {
		this.id = id;
		this.business_process_id = business_process_id;
		this.engagement_id = engagement_id;
		this.complexity_factor = complexity_factor;
		this.module_name = module_name;
		this.module_abbreviation = module_abbreviation;
		this.ddc_supported_flag = ddc_supported_flag;
		this.is_in_scope_flag = is_in_scope_flag;
		this.number_of_requirements = number_of_requirements;
		this.number_of_gaps = number_of_gaps;
		this.version = version;
		this.update_date = update_date;
		this.module_duration_factor = module_duration_factor;
		this.moduleweightage = moduleweightage;
	}

	public ModuleTransactionData() {
	}
}
