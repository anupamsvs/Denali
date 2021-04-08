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
@Table(name = "rice_effort_master")
public class RiceEffortMaster implements Serializable {
	
	private static final long serialVersionUID = -4748112555405779446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "workstream")
	private String workstream;
	

	@Column(name = "worktype")
	private String worktype;
	

	@Column(name = "effort")
	private String effort;
	
	@Column(name ="rie_type")
	private String rie_type;
	

	public RiceEffortMaster(Integer id, String workstream, String worktype,
			String effort, String rie_type, String complexity, Date create_dt) {
		super();
		this.id = id;
		this.workstream = workstream;
		this.worktype = worktype;
		this.effort = effort;
		this.rie_type = rie_type;
		this.complexity = complexity;
		this.create_dt = create_dt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkstream() {
		return workstream;
	}

	public void setWorkstream(String workstream) {
		this.workstream = workstream;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}

	public String getRie_type() {
		return rie_type;
	}

	public void setRie_type(String rie_type) {
		this.rie_type = rie_type;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public Date getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}

	@Column(name ="complexity")
	private String complexity;
	
	//create_dt
	
	@Column(name ="create_dt")
	private Date create_dt;
	
	
	public RiceEffortMaster(){
		
	}

}
