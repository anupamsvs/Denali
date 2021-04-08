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
@Table(name = "staff_driver_transaction")
public class StaffdriverData implements Serializable {
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 8601634204938781089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "type")
	private String type;

	@Column(name = "work_type")
	private String work_type;

	@Column(name = "engagement_id")
	private String engagement_id;
	
	public String getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(String engagement_id) {
		this.engagement_id = engagement_id;
	}

	@Column(name = "workstream_id")
	private String workstream_id;

	@Column(name = "work_value")
	private String work_value;

	@Column(name = "created_at")
	private Date created_at;

	@Column(name = "updated_at")
	private Date updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getWorkstream_id() {
		return workstream_id;
	}

	public void setWorkstream_id(String workstream_id) {
		this.workstream_id = workstream_id;
	}

	public String getWork_value() {
		return work_value;
	}

	public void setWork_value(String work_value) {
		this.work_value = work_value;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public StaffdriverData() {
	}

	public StaffdriverData(int id, String type, String work_type,
			String workstream_id, String work_value, Date created_at,
			Date updated_at) {
		super();
		this.id = id;
		this.type = type;
		this.work_type = work_type;
		this.workstream_id = workstream_id;
		this.work_value = work_value;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.engagement_id = engagement_id;
	}

}