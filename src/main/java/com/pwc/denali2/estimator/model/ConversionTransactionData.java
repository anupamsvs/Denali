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
@Table(name = "conversion_transaction_data")
public class ConversionTransactionData implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "conversion_data_type_id")
	private Integer conversion_data_type_id;

	@Column(name = "business_process_id")
	private Integer business_process_id;

	@Column(name = "module_id")
	private Integer module_id;

	@Column(name = "client_complexity_id")
	private Integer client_complexity_id;

	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "conversion_name")
	private String conversion_name;

	@Column(name = "scope_override_flag")
	private String scope_override_flag;

	@Column(name = "final_scope_flag")
	private String final_scope_flag;

	@Column(name = "conversion_source")
	private String conversion_source;

	@Column(name = "conversion_volume")
	private Double conversion_volume;

	@Column(name = "update_date")
	private Date update_date;

	@Column(name = "version")
	private Integer version;
	
	@Column(name = "is_user_create_flag")
	private String is_user_create_flag;
	

	@Column(name = "defaultedScope")
	private String defaultedScope;
	
	

	

	public String getIs_user_create_flag() {
		return is_user_create_flag;
	}

	public void setIs_user_create_flag(String is_user_create_flag) {
		this.is_user_create_flag = is_user_create_flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConversion_data_type_id() {
		return conversion_data_type_id;
	}

	public void setConversion_data_type_id(Integer conversion_data_type_id) {
		this.conversion_data_type_id = conversion_data_type_id;
	}

	public Integer getBusiness_process_id() {
		return business_process_id;
	}

	public void setBusiness_process_id(Integer business_process_id) {
		this.business_process_id = business_process_id;
	}

	public Integer getModule_id() {
		return module_id;
	}

	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	public Integer getClient_complexity_id() {
		return client_complexity_id;
	}

	public void setClient_complexity_id(Integer client_complexity_id) {
		this.client_complexity_id = client_complexity_id;
	}

	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public String getConversion_name() {
		return conversion_name;
	}

	public void setConversion_name(String conversion_name) {
		this.conversion_name = conversion_name;
	}

	public String getScope_override_flag() {
		return scope_override_flag;
	}

	public void setScope_override_flag(String scope_override_flag) {
		this.scope_override_flag = scope_override_flag;
	}

	public String getFinal_scope_flag() {
		return final_scope_flag;
	}

	public void setFinal_scope_flag(String final_scope_flag) {
		this.final_scope_flag = final_scope_flag;
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



	public ConversionTransactionData(Integer id, Integer conversion_data_type_id, Integer business_process_id,
			Integer module_id, Integer client_complexity_id, Integer engagement_id, String conversion_name,
			String scope_override_flag, String final_scope_flag, String conversion_source, Double conversion_volume,
			Date update_date, Integer version, String is_user_create_flag,String defaultedScope) {
		super();
		this.id = id;
		this.conversion_data_type_id = conversion_data_type_id;
		this.business_process_id = business_process_id;
		this.module_id = module_id;
		this.client_complexity_id = client_complexity_id;
		this.engagement_id = engagement_id;
		this.conversion_name = conversion_name;
		this.scope_override_flag = scope_override_flag;
		this.final_scope_flag = final_scope_flag;
		this.conversion_source = conversion_source;
		this.conversion_volume = conversion_volume;
		this.update_date = update_date;
		this.version = version;
		this.is_user_create_flag = is_user_create_flag;
		this.defaultedScope = defaultedScope;
	}

	public String getDefaultedScope() {
		return defaultedScope;
	}

	public void setDefaultedScope(String defaultedScope) {
		this.defaultedScope = defaultedScope;
	}

	public ConversionTransactionData() {
	}
}
