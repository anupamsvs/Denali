package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

public class AdditionalServiceVo implements Serializable{
	
	
		private static final long serialVersionUID = -4666770189399685284L;
		

		private Integer id;

		private Integer work_stream_id;

		
		private Integer service_owner_id;

		
		private Integer engagement_id;
		
		private Date update_date;
		private Date create_date;
		public Integer getId() {
			return id;
		}
		
		public Date getUpdate_date() {
			return update_date;
		}

		public void setUpdate_date(Date update_date) {
			this.update_date = update_date;
		}

		public Date getCreate_date() {
			return create_date;
		}

		public void setCreate_date(Date create_date) {
			this.create_date = create_date;
		}

		public AdditionalServiceVo(){
			
		}

		public AdditionalServiceVo(Integer id, Integer work_stream_id,
				Integer service_owner_id, Integer engagement_id,
				Integer level_id, Integer additonal_service_id,
				String activity, Double value, Integer prototype_id, Double rate) {
			super();
			this.id = id;
			this.work_stream_id = work_stream_id;
			this.service_owner_id = service_owner_id;
			this.engagement_id = engagement_id;
			this.level_id = level_id;
			this.additonal_service_id = additonal_service_id;
			this.activity = activity;
			this.value = value;
			this.prototype_id = prototype_id;
			this.rate = rate;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getWork_stream_id() {
			return work_stream_id;
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

		public Integer getLevel_id() {
			return level_id;
		}

		public void setLevel_id(Integer level_id) {
			this.level_id = level_id;
		}

		public Integer getAdditonal_service_id() {
			return additonal_service_id;
		}

		public void setAdditonal_service_id(Integer additonal_service_id) {
			this.additonal_service_id = additonal_service_id;
		}

		public String getActivity() {
			return activity;
		}

		public void setActivity(String activity) {
			this.activity = activity;
		}

		public Double getValue() {
			return value;
		}

		public void setValue(Double value) {
			this.value = value;
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

		private Integer level_id;
		private Integer additonal_service_id;
	
		private String activity;
		


		private Double value;
		private Integer prototype_id;

		private Double rate;


		

		

	}



