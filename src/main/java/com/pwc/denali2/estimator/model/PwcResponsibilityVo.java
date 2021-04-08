package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

public class PwcResponsibilityVo implements Serializable {
	private static final long serialVersionUID = -4666770189399685254L;
	private Integer id;
	private Integer work_stream_id;
	private String workdescription;
	private Integer prototype_id;
	private String protodescription;
	private String activity;
	private Integer engagement_id;
	private String is_pwc_responsibility_flag;
	private Date update_date;
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWork_stream_id() {
		return work_stream_id;
	}
	public void setWork_stream_id(Integer work_stream_id) {
		this.work_stream_id = work_stream_id;
	}
	public String getWorkdescription() {
		return workdescription;
	}
	public void setWorkdescription(String workdescription) {
		this.workdescription = workdescription;
	}
	public Integer getPrototype_id() {
		return prototype_id;
	}
	public void setPrototype_id(Integer prototype_id) {
		this.prototype_id = prototype_id;
	}
	public String getProtodescription() {
		return protodescription;
	}
	public void setProtodescription(String protodescription) {
		this.protodescription = protodescription;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getEngagement_id() {
		return engagement_id;
	}
	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}
	public String getIs_pwc_responsibility_flag() {
		return is_pwc_responsibility_flag;
	}
	public void setIs_pwc_responsibility_flag(String is_pwc_responsibility_flag) {
		this.is_pwc_responsibility_flag = is_pwc_responsibility_flag;
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
	public PwcResponsibilityVo(Integer id, Integer work_stream_id, String workdescription, Integer prototype_id,
			String protodescription, String activity, Integer engagement_id, String is_pwc_responsibility_flag,
			Date update_date, Integer version) {
		super();
		this.id = id;
		this.work_stream_id = work_stream_id;
		this.workdescription = workdescription;
		this.prototype_id = prototype_id;
		this.protodescription = protodescription;
		this.activity = activity;
		this.engagement_id = engagement_id;
		this.is_pwc_responsibility_flag = is_pwc_responsibility_flag;
		this.update_date = update_date;
		this.version = version;
	}
	public PwcResponsibilityVo() {
	}

	

	
}
