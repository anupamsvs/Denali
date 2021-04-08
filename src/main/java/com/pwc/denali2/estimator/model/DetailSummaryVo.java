package com.pwc.denali2.estimator.model;

import java.io.Serializable;


public class DetailSummaryVo implements Serializable{
	
	private static final long serialVersionUID = -4666770189399685284L;
	
	String workstream;
	String prototyp;
	String effort_onsite;
	String effort_ddc;
	
	String csp_onsite;
	String csp_ddc;
	String rsr_onsite;
	String rsr_ddc;
	String margin_onsite;
	String margin_ddc;
	
	
	       
	
	
	public String getWorkstream() {
		return workstream;
	}
	public void setWorkstream(String workstream) {
		this.workstream = workstream;
	}
	public String getCsp_onsite() {
		return csp_onsite;
	}
	public void setCsp_onsite(String csp_onsite) {
		this.csp_onsite = csp_onsite;
	}
	public String getCsp_ddc() {
		return csp_ddc;
	}
	public void setCsp_ddc(String csp_ddc) {
		this.csp_ddc = csp_ddc;
	}
	public String getRsr_onsite() {
		return rsr_onsite;
	}
	public void setRsr_onsite(String rsr_onsite) {
		this.rsr_onsite = rsr_onsite;
	}
	public String getRsr_ddc() {
		return rsr_ddc;
	}
	public void setRsr_ddc(String rsr_ddc) {
		this.rsr_ddc = rsr_ddc;
	}
	public String getMargin_onsite() {
		return margin_onsite;
	}
	public void setMargin_onsite(String margin_onsite) {
		this.margin_onsite = margin_onsite;
	}
	public String getMargin_ddc() {
		return margin_ddc;
	}
	public void setMargin_ddc(String margin_ddc) {
		this.margin_ddc = margin_ddc;
	}
	public String getPrototyp() {
		return prototyp;
	}
	public void setPrototyp(String prototyp) {
		this.prototyp = prototyp;
	}
	public String getEffort_onsite() {
		return effort_onsite;
	}
	public void setEffort_onsite(String effort_onsite) {
		this.effort_onsite = effort_onsite;
	}
	public String getEffort_ddc() {
		return effort_ddc;
	}
	public void setEffort_ddc(String effort_ddc) {
		this.effort_ddc = effort_ddc;
	}
	
	
	
	public DetailSummaryVo(String workstream, String prototyp,
			String effort_onsite, String effort_ddc, String csp_onsite,
			String csp_ddc, String rsr_onsite, String rsr_ddc,
			String margin_onsite, String margin_ddc) {
		super();
		this.workstream = workstream;
		this.prototyp = prototyp;
		this.effort_onsite = effort_onsite;
		this.effort_ddc = effort_ddc;
		this.csp_onsite = csp_onsite;
		this.csp_ddc = csp_ddc;
		this.rsr_onsite = rsr_onsite;
		this.rsr_ddc = rsr_ddc;
		this.margin_onsite = margin_onsite;
		this.margin_ddc = margin_ddc;
	}
	public DetailSummaryVo(){
		
	}
	
	
	   

}