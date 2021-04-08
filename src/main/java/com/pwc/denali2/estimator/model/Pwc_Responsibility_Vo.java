package com.pwc.denali2.estimator.model;

public class Pwc_Responsibility_Vo {
	
	private static final long serialVersionUID = -4666788189399685284L;
	
	//private Integer id;
	public String getWorkstream() {
		return workstream;
	}
	public void setWorkstream(String workstream) {
		this.workstream = workstream;
	}
	public String getPrototype() {
		return prototype;
	}
	public void setPrototype(String prototype) {
		this.prototype = prototype;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

	
	private String bu_dependency;
	private String workstream;
	private String prototype;
	private String activity;
	private String is_pwc_responsibility_flag;
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}

	
	public Pwc_Responsibility_Vo(String bu_dependency,
			String workstream, String prototype, String activity,
			String is_pwc_responsibility_flag) {
		super();
		//this.id = id;
		this.bu_dependency = bu_dependency;
		this.workstream = workstream;
		this.prototype = prototype;
		this.activity = activity;
		this.is_pwc_responsibility_flag = is_pwc_responsibility_flag;
	}
	public String getBu_dependency() {
		return bu_dependency;
	}
	public void setBu_dependency(String bu_dependency) {
		this.bu_dependency = bu_dependency;
	}
	public String getIs_pwc_responsibility_flag() {
		return is_pwc_responsibility_flag;
	}
	public void setIs_pwc_responsibility_flag(String is_pwc_responsibility_flag) {
		this.is_pwc_responsibility_flag = is_pwc_responsibility_flag;
	}
	
	public Pwc_Responsibility_Vo() {

	}
}
