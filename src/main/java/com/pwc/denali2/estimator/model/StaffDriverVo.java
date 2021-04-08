package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;


public class StaffDriverVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1724396591722738429L;

	@Column(name = "rate")
	private int rate;
	
	
	
	@Column(name = "offshore")
	private int offshore;
	
	@Column(name = "offshore_rate")
	private int offshore_rate;

	@Column(name = "level_id")
	private int level_id;

	@Column(name = "work_type")
	private String work_type;

	@Column(name = "team_value")
	private int team_value;

	@Column(name = "team_type")
	private String team_type;
	
	@Column(name = "workstream_id")
	private int workstream_id;
	
	
	@Column(name = "work_value")
	private int work_value;
	
	@Column(name = "sum")
	private int sum;

	public int getOffshore_rate() {
		return offshore_rate;
	}

	public void setOffshore_rate(int offshore_rate) {
		this.offshore_rate = offshore_rate;
	}

	public int getOffshore() {
		return offshore;
	}

	public void setOffshore(int offshore) {
		this.offshore = offshore;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getLevel_id() {
		return level_id;
	}

	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
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

	public int getWorkstream_id() {
		return workstream_id;
	}

	public void setWorkstream_id(int workstream_id) {
		this.workstream_id = workstream_id;
	}

	public int getWork_value() {
		return work_value;
	}

	public void setWork_value(int work_value) {
		this.work_value = work_value;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public StaffDriverVo(){
		
	}
	
	public StaffDriverVo(int rate, int level_id, String work_type,
			int team_value, String team_type, int workstream_id,
			int work_value, int sum, int offshore, int offshore_rate) {
		super();
		this.rate = rate;
		this.level_id = level_id;
		this.work_type = work_type;
		this.team_value = team_value;
		this.team_type = team_type;
		this.workstream_id = workstream_id;
		this.work_value = work_value;
		this.sum = sum;
		this.offshore = offshore;
		this.offshore_rate  = offshore_rate;
	}
	
	
	

	
}