package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff_driver_team_transaction")
public class StaffdriverTeam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8561244329111257881L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "work_type")
	private String work_type;

	
	@Column(name = "level_id")
	private int level_id;

	
	@Column(name = "workstream_id")
	private int workstream_id;

	@Column(name = "engagement_id")
	private int engagement_id;

	
	@Column(name = "team_value")
	private int team_value;

	
	@Column(name = "team_type")
	private String team_type;
	
	@Column(name = "created_by",nullable=true)
	private String created_by;
	
	@Column(name = "updated_by",nullable=true)
	private String updated_by;
	
	@Column(name = "created_date",nullable=true)
	private Date created_date;
	
	@Column(name = "updated_date",nullable=true)
	private Date updated_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public int getLevel_id() {
		return level_id;
	}

	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}

	public int getWorkstream_id() {
		return workstream_id;
	}

	public void setWorkstream_id(int workstream_id) {
		this.workstream_id = workstream_id;
	}

	public int getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(int engagement_id) {
		this.engagement_id = engagement_id;
	}

	public int getTeam_value() {
		return team_value;
	}

	public void setTeam_value(int team_value) {
		this.team_value = team_value;
	}

	public String getTeam_type() {
		return team_type;
	}

	public void setTeam_type(String team_type) {
		this.team_type = team_type;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public StaffdriverTeam(){
		
	}

	public StaffdriverTeam(int id, String work_type, int level_id,
			int workstream_id, int engagement_id, int team_value,
			String team_type, String created_by, String updated_by,
			Date created_date, Date updated_date) {
		super();
		this.id = id;
		this.work_type = work_type;
		this.level_id = level_id;
		this.workstream_id = workstream_id;
		this.engagement_id = engagement_id;
		this.team_value = team_value;
		this.team_type = team_type;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_date = created_date;
		this.updated_date = updated_date;
	}
	
	
	
}