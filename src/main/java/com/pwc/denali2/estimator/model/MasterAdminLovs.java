package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "master_admin_lovs")
public class MasterAdminLovs implements Serializable {

	private static final long serialVersionUID = -7289467485454646404L;
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "tag")
	private String tag;
	
	@Column(name = "bu_factor")
	private String bu_factor;
	
	@Column(name = "from_date",nullable=true)
	private Date from_date;
	
	@Column(name = "to_date",nullable=true)
	private Date to_date;
	
	@Column(name = "active")
	private String active;

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	
	public MasterAdminLovs() {
	}

	public MasterAdminLovs(Integer id, String type, String code,
			String description, Date from_date, Date to_date, String active,String bu_factor,String tag) {
		
		this.id = id;
		this.type = type;
		this.code = code;
		this.description = description;
		this.from_date = from_date;
		this.to_date = to_date;
		this.active = active;
		this.bu_factor=bu_factor;
		this.tag=tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBu_factor() {
		return bu_factor;
	}

	public void setBu_factor(String bu_factor) {
		this.bu_factor = bu_factor;
	}

	

	
}