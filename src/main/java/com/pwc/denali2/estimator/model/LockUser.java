package com.pwc.denali2.estimator.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_lock")
public class LockUser implements Serializable {

	private static final long serialVersionUID = -7289467485454646404L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "engagement_id")
	private String engagement_id;

	@Column(name = "user")
	private String user;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	public String getProject_id() {
		return engagement_id;
	}

	public void setProject_id(String project_id) {
		this.engagement_id = engagement_id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public LockUser() {	
	}

	public LockUser(Integer id, String engagement_id, String user,
			String user_details, Timestamp timestamp) {
		super();
		this.id = id;
		this.engagement_id = engagement_id;
		this.user = user;
		this.timestamp = timestamp;
	}

}