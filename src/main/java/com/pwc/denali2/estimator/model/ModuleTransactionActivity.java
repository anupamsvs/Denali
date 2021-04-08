package com.pwc.denali2.estimator.model;

public class ModuleTransactionActivity extends ModuleTransactionDataVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5686047174220277961L;
	private String activity_ddc = "--";
	private String activity_onsite = "--";
	private String activity_name = "--";
	
	public String getActivity_ddc() {
		return activity_ddc;
	}
	
	public void setActivity_ddc(String activity_ddc) {
		this.activity_ddc = activity_ddc;
	}
	public String getActivity_onsite() {
		return activity_onsite;
	}
	public void setActivity_onsite(String activity_onsite) {
		this.activity_onsite = activity_onsite;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
}
