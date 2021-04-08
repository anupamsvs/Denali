package com.pwc.denali2.estimator.model;

public class ModuleTransactionDataVO extends ModuleTransactionData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7002195372145752139L;
	
	private String businessProcessName;
	
	private String cloudOffering;

	public String getBusinessProcessName() {
		return businessProcessName;
	}

	public void setBusinessProcessName(String businessProcessName) {
		this.businessProcessName = businessProcessName;
	}

	public String getCloudOffering() {
		return cloudOffering;
	}

	public void setCloudOffering(String cloudOffering) {
		this.cloudOffering = cloudOffering;
	}


}
