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
@Table(name = "pwc_responsibility_transaction_data")
public class PwcResponsibilityTransactionData implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "work_stream_id")
	private Integer work_stream_id;

	@Column(name = "prototype_id")
	private Integer prototype_id;

	@Column(name = "activity")
	private String activity;

	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "is_pwc_responsibility_flag")
	private String is_pwc_responsibility_flag;

	@Column(name = "update_date")
	private Date update_date;

	@Column(name = "version")
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

	public Integer getPrototype_id() {
		return prototype_id;
	}

	public void setPrototype_id(Integer prototype_id) {
		this.prototype_id = prototype_id;
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

	public PwcResponsibilityTransactionData(Integer id, Integer work_stream_id, Integer prototype_id,
			String activity, Integer engagement_id, String is_pwc_responsibility_flag, Date update_date,
			Integer version) {
		this.id = id;
		this.work_stream_id = work_stream_id;
		this.prototype_id = prototype_id;
		this.activity = activity;
		this.engagement_id = engagement_id;
		this.is_pwc_responsibility_flag = is_pwc_responsibility_flag;
		this.update_date = update_date;
		this.version = version;
	}

	public PwcResponsibilityTransactionData() {
	}
}
