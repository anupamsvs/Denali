package com.pwc.denali2.estimator.model;

public class ConversionModuleData {
	
	private  int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String module_name;
	private String businessprocess;
	private String is_in_scope_flag;

	

	public String getIs_in_scope_flag() {
		return is_in_scope_flag;
	}

	public void setIs_in_scope_flag(String is_in_scope_flag) {
		this.is_in_scope_flag = is_in_scope_flag;
	}

	private String module_abbreviation;

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

	public ConversionModuleData(String module_name, String module_abbreviation, int id,String businessprocess,String is_in_scope_flag) {
		super();
		this.module_name = module_name;
		this.module_abbreviation = module_abbreviation;
		this.id =id;
		this.businessprocess=businessprocess;
		this.is_in_scope_flag =is_in_scope_flag;
	}
	
	public String getBusinessprocess() {
		return businessprocess;
	}

	public void setBusinessprocess(String businessprocess) {
		this.businessprocess = businessprocess;
	}

	public ConversionModuleData() {
	
	}
	
	


}
