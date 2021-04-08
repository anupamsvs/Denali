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
@Table(name = "rice_transaction_data")
public class RiceTransactionData implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "rice_type_id")
	private Integer rice_type_id;

	@Column(name = "module_id")
	private Integer module_id;

	@Column(name = "client_complexity_id")
	private Integer client_complexity_id;

	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "rice_name")
	private String rice_name;

	@Column(name = "rice_source_system")
	private String rice_source_system;

	@Column(name = "rice_target_system")
	private String rice_target_system;

	@Column(name = "create_date")
	private Date create_date;

	@Column(name = "update_date")
	private Date update_date;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRice_type_id() {
		return rice_type_id;
	}

	public void setRice_type_id(Integer rice_type_id) {
		this.rice_type_id = rice_type_id;
	}



	public Integer getModule_id() {
		return module_id;
	}

	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	public Integer getClient_complexity_id() {
		return client_complexity_id;
	}

	public void setClient_complexity_id(Integer client_complexity_id) {
		this.client_complexity_id = client_complexity_id;
	}

	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public String getRice_name() {
		return rice_name;
	}

	public void setRice_name(String rice_name) {
		this.rice_name = rice_name;
	}

	public String getRice_source_system() {
		return rice_source_system;
	}

	public void setRice_source_system(String rice_source_system) {
		this.rice_source_system = rice_source_system;
	}

	public String getRice_target_system() {
		return rice_target_system;
	}

	public void setRice_target_system(String rice_target_system) {
		this.rice_target_system = rice_target_system;
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

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public RiceTransactionData(Integer id, Integer rice_type_id, Integer module_id,
			Integer client_complexity_id, Integer engagement_id, String rice_name, String rice_source_system,
			String rice_target_system, Date create_date, Date update_date) {
		this.id = id;
		this.rice_type_id = rice_type_id;
		this.module_id = module_id;
		this.client_complexity_id = client_complexity_id;
		this.engagement_id = engagement_id;
		this.rice_name = rice_name;
		this.rice_source_system = rice_source_system;
		this.rice_target_system = rice_target_system;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	public RiceTransactionData() {
	}
}
