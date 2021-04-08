package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rates_master")
public class RatesMaster implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	

	@Column(name = "workstream_id")
	private String workstream_id;
	
	@Column(name = "rate_type")
	private String rate_type;
	
	@Column(name = "workstream")
	private String workstream;
	
	
	@Column(name = "version_id")
	private String version_id;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "worktype")
	private String worktype;
	
	@Column(name = "rate")
	private Double rate;
	
	public String getWorkstream_id() {
		return workstream_id;
	}

	public void setWorkstream_id(String workstream_id) {
		this.workstream_id = workstream_id;
	}

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}


	@Column(name = "level_id")
	private String level_id;
	
	public String getWorkstream() {
		return workstream;
	}

	public void setWorkstream(String workstream) {
		this.workstream = workstream;
	}

	public String getVersion_id() {
		return version_id;
	}

	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}


	@Column(name = "create_date")
	private Date create_date;

	public String getRate_type() {
		return rate_type;
	}

	public void setRate_type(String rate_type) {
		this.rate_type = rate_type;
	}

	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}


	@Column(name = "update_date")
	private Date update_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	

	public RatesMaster(Integer id, String workstream_id, String rate_type,
			String workstream, String version_id, String level,
			String worktype, Double rate, String level_id, Date create_date,
			Date update_date) {
		super();
		this.id = id;
		this.workstream_id = workstream_id;
		this.rate_type = rate_type;
		this.workstream = workstream;
		this.version_id = version_id;
		this.level = level;
		this.worktype = worktype;
		this.rate = rate;
		this.level_id = level_id;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	
	public RatesMaster() {
	}
}
