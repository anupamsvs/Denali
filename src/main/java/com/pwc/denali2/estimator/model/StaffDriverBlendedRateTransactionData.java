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
	@Table(name = "staff_driver_blended_rate_transaction")
	public class StaffDriverBlendedRateTransactionData implements Serializable {
	      
		/**
		 * 
		 */
		private static final long serialVersionUID = 8601634204938781089L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		@Column(name = "workstream_id")
		private Integer workstream_id;
		
		@Column(name = "rate_type")
		private String rate_type;

		@Column(name = "work_type")
		private String work_type;

		@Column(name ="rate")
		private Double rate;
		
		@Column(name = "created_dateat")
		private Date created_date;

		@Column(name = "updated_date")
		private Date updated_date;

		
		
		@Column(name ="engagement_id")
		private Integer engagement_id;
		
		public StaffDriverBlendedRateTransactionData() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Integer getWorkstream_id() {
			return workstream_id;
		}

		public void setWorkstream_id(Integer workstream_id) {
			this.workstream_id = workstream_id;
		}

		public String getRate_type() {
			return rate_type;
		}

		public void setRate_type(String rate_type) {
			this.rate_type = rate_type;
		}

		public String getWork_type() {
			return work_type;
		}

		public void setWork_type(String work_type) {
			this.work_type = work_type;
		}

		public Double getRate() {
			return rate;
		}

		public void setRate(Double rate) {
			this.rate = rate;
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

		public Integer getEngagement_id() {
			return engagement_id;
		}

		public void setEngagement_id(Integer engagement_id) {
			this.engagement_id = engagement_id;
		}

		public StaffDriverBlendedRateTransactionData(int id,
				Integer workstream_id, String rate_type, String work_type,
				Double rate, Date created_date, Date updated_date,
				Integer engagement_id) {
			super();
			this.id = id;
			this.workstream_id = workstream_id;
			this.rate_type = rate_type;
			this.work_type = work_type;
			this.rate = rate;
			this.created_date = created_date;
			this.updated_date = updated_date;
			this.engagement_id = engagement_id;
		}
		
		

		
	}