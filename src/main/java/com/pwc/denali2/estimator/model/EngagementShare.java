package com.pwc.denali2.estimator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="engagement_share")
public class EngagementShare implements Serializable{

	private static final long serialVersionUID = 9165424138229594777L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "engagement_id")
	private Integer engagement_id;
	
	@Column(name = "guid")
	private String guid;
	
	
	@Column(name = "read")
	private String read;
	
	@Column(name = "edit")
	private String edit;
	
	@Column(name = "release")
	private String release;
	
	@Column(name = "name")
	private String name;

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public EngagementShare(Integer id, Integer engagement_id, String guid,
			String read, String edit, String release, String name) {
		super();
		this.id = id;
		this.engagement_id = engagement_id;
		this.guid = guid;
		this.read = read;
		this.edit = edit;
		this.release = release;
		this.name = name;
	}

	public EngagementShare(){
		
	}
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
