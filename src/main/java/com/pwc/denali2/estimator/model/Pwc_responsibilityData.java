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
@Table(name = "pwc_responsibility")
public class Pwc_responsibilityData implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@Column(name = "is_pwc_responsibility_flag")
	private String is_pwc_responsibility_flag;

	@Column(name="bu_dependency")
	private String bu_dependency;
	
	

	@Column(name = "create_date")
	private Date create_date;

	@Column(name = "update_date")
	private Date update_date;
	
	
	@Column(name = "engagement_id")
	private Integer engagement_id;
	
	@Column(name = "Activity_id")
	private Integer Activity_id;
	
	@Column(name = "version_id")
	private Integer version_id;
	

	public Integer getVersion_id() {
		return version_id;
	}

	public void setVersion_id(Integer version_id) {
		this.version_id = version_id;
	}


	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public Integer getActivity_id() {
		return Activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		Activity_id = activity_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public Pwc_responsibilityData(Integer id,
			String is_pwc_responsibility_flag, Date create_date,
			Date update_date, Integer engagement_id, Integer Activity_id,Integer version_id,String bu_dependency) {
		super();
		this.id = id;
		this.is_pwc_responsibility_flag = is_pwc_responsibility_flag;
		this.create_date = create_date;
		this.update_date = update_date;
		this.engagement_id = engagement_id;
		this.Activity_id = Activity_id;
		this.version_id =version_id;
		this.bu_dependency =bu_dependency;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Pwc_responsibilityData() {
	}

	

}
