package com.pwc.denali2.estimator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "engagement_attachment")
public class EngagementAttachment implements Serializable {
	private static final long serialVersionUID = -4748112555405779446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "engagement_id")
	private Integer engagement_id;

	@Column(name = "file_name_original")
	private String file_name_original;

	@Column(name = "file_name_on_server")
	private String file_name_on_server;

	@Column(name = "file_path")
	private String file_path;
	
	@Column(name = "upload_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
	private Date upload_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFile_name_original() {
		return file_name_original;
	}

	public void setFile_name_original(String file_name_original) {
		this.file_name_original = file_name_original;
	}

	public String getFile_name_on_server() {
		return file_name_on_server;
	}

	public void setFile_name_on_server(String file_name_on_server) {
		this.file_name_on_server = file_name_on_server;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	

	public Integer getEngagement_id() {
		return engagement_id;
	}

	public void setEngagement_id(Integer engagement_id) {
		this.engagement_id = engagement_id;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}



	public EngagementAttachment(Integer id, Integer engagement_id, String file_name_original,
			String file_name_on_server, String file_path, Date upload_time) {
		super();
		this.id = id;
		this.engagement_id = engagement_id;
		this.file_name_original = file_name_original;
		this.file_name_on_server = file_name_on_server;
		this.file_path = file_path;
		this.upload_time = upload_time;
	}

	public EngagementAttachment() {
	}
}
