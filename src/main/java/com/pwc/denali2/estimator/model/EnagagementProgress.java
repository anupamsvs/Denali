//detailed_summary
package com.pwc.denali2.estimator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "engagement_progress")
public class EnagagementProgress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025115768365354804L;

	//Class Starts	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "engagement_id",nullable=false)
	private int engagement_id;
	
	@Column(name = "level",nullable=true)
	private int level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(int engagement_id) {
		this.engagement_id = engagement_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public EnagagementProgress(){
		
	};
	public EnagagementProgress(int id, int engagement_id, int level) {
		super();
		this.id = id;
		this.engagement_id = engagement_id;
		this.level = level;
	}
}