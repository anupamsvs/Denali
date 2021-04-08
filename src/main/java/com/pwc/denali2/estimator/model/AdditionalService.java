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
@Table(name = "additional_service")
public class AdditionalService implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "work_stream_id")
	private Integer work_stream_id;

	@Column(name = "service_owner_id")
	private Integer service_owner_id;

	@Column(name = "engagement_id")
	private Integer engagement_id;
	
	@Column(name = "level_id")
	private Integer level_id;

	public Integer getLevel_id() {
		return level_id;
	}



	public void setLevel_id(Integer level_id) {
		this.level_id = level_id;
	}



	@Column(name = "activity")
	private String activity;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getWork_stream_id() {
		return work_stream_id;
	}



	public AdditionalService(Integer id, Integer work_stream_id,
			Integer service_owner_id, Integer engagement_id, String activity,
			Integer prototype_id, Double rate, Date create_date,
			Date update_date, Integer level_id) {
		super();
		this.id = id;
		this.work_stream_id = work_stream_id;
		this.service_owner_id = service_owner_id;
		this.engagement_id = engagement_id;
		this.activity = activity;
		this.prototype_id = prototype_id;
		this.rate = rate;
		this.create_date = create_date;
		this.update_date = update_date;
		this.level_id=level_id;
	}



	public void setWork_stream_id(Integer work_stream_id) {
		this.work_stream_id = work_stream_id;
	}



	public Integer getService_owner_id() {
		return service_owner_id;
	}



	public void setService_owner_id(Integer service_owner_id) {
		this.service_owner_id = service_owner_id;
	}



	public Integer getEngagement_id() {
		return engagement_id;
	}



	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}



	public String getActivity() {
		return activity;
	}



	public void setActivity(String activity) {
		this.activity = activity;
	}



	public Integer getPrototype_id() {
		return prototype_id;
	}



	public void setPrototype_id(Integer prototype_id) {
		this.prototype_id = prototype_id;
	}



	



	public Double getRate() {
		return rate;
	}



	public void setRate(Double rate) {
		this.rate = rate;
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



	@Column(name = "prototype_id")
	private Integer prototype_id;


	

	@Column(name = "rate")
	private Double rate;

	@Column(name = "create_date")
	private Date create_date;

	@Column(name = "update_date")
	private Date update_date;

	

	public AdditionalService() {
	}

}
