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
@Table(name = "conversion_metadata")
public class ConversionMetaData implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	@Column(name = "conversion_data_type")
	private String conversion_data_type;
	
	
	@Column(name ="is_in_scope_flag")
	private String is_in_scope_flag;

//	
//	public String getAbbreviation() {
//		return abbreviation;
//	}
//
//	public void setAbbreviation(String abbreviation) {
//		this.abbreviation = abbreviation;
//	}

	public String getIs_in_scope_flag() {
		return is_in_scope_flag;
	}

	public void setIs_in_scope_flag(String is_in_scope_flag) {
		this.is_in_scope_flag = is_in_scope_flag;
	}

	public ConversionMetaData(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

	public String getConversion_name() {
		return conversion_name;
	}

	public void setConversion_name(String conversion_name) {
		this.conversion_name = conversion_name;
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

	public String getScope_override_flag() {
		return scope_override_flag;
	}

	public void setScope_override_flag(String scope_override_flag) {
		this.scope_override_flag = scope_override_flag;
	}

	public String getConversion_source() {
		return conversion_source;
	}

	public void setConversion_source(String conversion_source) {
		this.conversion_source = conversion_source;
	}

	public Double getConversion_volume() {
		return conversion_volume;
	}

	public void setConversion_volume(Double conversion_volume) {
		this.conversion_volume = conversion_volume;
	}

	@Column(name = "module")
	private String module;
	
	
	
	@Column(name = "client_complexity")
	private String client_complexity;

	@Column(name = "conversion_name")
	private String conversion_name;

	@Column(name = "create_date")
	private Date create_date;

	@Column(name = "update_date")
	private Date update_date;

	
	public String getConversion_data_type() {
		return conversion_data_type;
	}

	public void setConversion_data_type(String conversion_data_type) {
		this.conversion_data_type = conversion_data_type;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getClient_complexity() {
		return client_complexity;
	}

	public void setClient_complexity(String client_complexity) {
		this.client_complexity = client_complexity;
	}

//	public String getBusinessprocess() {
//		return businessprocess;
//	}
//
//	public void setBusinessprocess(String businessprocess) {
//		this.businessprocess = businessprocess;
//	}

	public ConversionMetaData(Integer id, String conversion_data_type,
			String module, String client_complexity,
			String conversion_name, Date create_date, Date update_date,
			 String scope_override_flag,
			String conversion_source, Double conversion_volume,String is_in_scope_flag) {
		super();
		this.id = id;
		this.conversion_data_type = conversion_data_type;
		//this.abbreviation = abbreviation;
		this.module = module;
		this.client_complexity = client_complexity;
		this.conversion_name = conversion_name;
		this.create_date = create_date;
		this.update_date = update_date;
		//this.businessprocess = businessprocess;
		this.scope_override_flag = scope_override_flag;
		this.conversion_source = conversion_source;
		this.conversion_volume = conversion_volume;
		this.is_in_scope_flag =is_in_scope_flag;
	}

//	@Column(name ="businessprocess")
//	private String businessprocess;

	@Column(name = "scope_override_flag")
	private String scope_override_flag;
	
	@Column(name = "conversion_source")
	private String conversion_source;

	@Column(name = "conversion_volume")
	private Double conversion_volume;
	


}
