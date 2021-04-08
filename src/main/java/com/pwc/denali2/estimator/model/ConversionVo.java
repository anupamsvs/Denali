package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;


public class ConversionVo implements Serializable {
	private static final long serialVersionUID = -4666788189399685284L;

	private Integer id;
	private Integer conversion_data_type_id;
	//private String conversion_data_type_id;
	//private Integer business_process_id;
	private String businessProcess;
	private Integer module_id;
	private String moduleName;






	public ConversionVo(Integer id, Integer conversion_data_type_id,
			String businessProcess, Integer module_id, String moduleName,
			String abbreviation, String defaultedScope,
			Integer client_complexity_id, String final_scope_flag,
			String conversion_name, String scope_override_flag,
			String conversion_source, Double conversion_volume) {
		super();
		this.id = id;
		this.conversion_data_type_id = conversion_data_type_id;
		this.businessProcess = businessProcess;
		this.module_id = module_id;
		this.moduleName = moduleName;
		this.abbreviation = abbreviation;
		this.defaultedScope = defaultedScope;
		this.client_complexity_id = client_complexity_id;
		this.final_scope_flag = final_scope_flag;
		this.conversion_name = conversion_name;
		this.scope_override_flag = scope_override_flag;
		this.conversion_source = conversion_source;
		this.conversion_volume = conversion_volume;
	}










	private String abbreviation;
	public String getDefaultedScope() {
		return defaultedScope;
	}





	public void setDefaultedScope(String defaultedScope) {
		this.defaultedScope = defaultedScope;
	}





	public String getFinal_scope_flag() {
		return final_scope_flag;
	}





	public void setFinal_scope_flag(String final_scope_flag) {
		this.final_scope_flag = final_scope_flag;
	}





	public String getScope_override_flag() {
		return scope_override_flag;
	}





	public void setScope_override_flag(String scope_override_flag) {
		this.scope_override_flag = scope_override_flag;
	}










	private String defaultedScope;
	private Integer client_complexity_id;
	private String final_scope_flag;
	//private Integer engagement_id;
	private String conversion_name;
	
	private String scope_override_flag;
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





	public String getBusinessProcess() {
		return businessProcess;
	}





	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}





	public Integer getModule_id() {
		return module_id;
	}





	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}





	public String getModuleName() {
		return moduleName;
	}





	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}





	public String getAbbreviation() {
		return abbreviation;
	}





	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}










	public Integer getClient_complexity_id() {
		return client_complexity_id;
	}





	public void setClient_complexity_id(Integer client_complexity_id) {
		this.client_complexity_id = client_complexity_id;
	}





	public String getConversion_name() {
		return conversion_name;
	}





	public void setConversion_name(String conversion_name) {
		this.conversion_name = conversion_name;
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










	//private String final_scope_flag;
	private String conversion_source;
	private Double conversion_volume;
	//private Date update_date;
	//private Integer version;
	//private String is_user_create_flag;
	


	
	
	public ConversionVo() {

	}

	
	
}
