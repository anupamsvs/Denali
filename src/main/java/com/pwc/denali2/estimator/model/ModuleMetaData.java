package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "module_metadata")
public class ModuleMetaData implements Serializable {

	private static final long serialVersionUID = -7289467485454646404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "business_process_id")
	private Integer business_process_id;

	@Column(name = "module_name")
	private String module_name;

	@Column(name = "job_aid")
	private String job_aid;

	@Column(name = "workshop")
	private String workshop;

	public String getWorkshop() {
		return workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	@Column(name = "module_abbreviation")
	private String module_abbreviation;

	@Column(name = "ddc_supported_flag")
	private String ddc_supported_flag;

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

	

	public Integer getBusiness_process_id() {
		return business_process_id;
	}

	public void setBusiness_process_id(Integer business_process_id) {
		this.business_process_id = business_process_id;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(java.util.Date date) {
		this.create_date = date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public ModuleMetaData() {
	}

	public ModuleMetaData(Integer id, Integer business_process_id, String module_name, String module_abbreviation,
			String ddc_supported_flag, Date create_date, Date update_date) {
		super();
		this.id = id;
		this.business_process_id = business_process_id;
		this.module_name = module_name;
		this.module_abbreviation = module_abbreviation;
		this.ddc_supported_flag = ddc_supported_flag;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	
}
