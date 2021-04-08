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
@Table(name = "activity_master_data_driver")
public class ActivityManagementData implements Serializable {

	private static final long serialVersionUID = -7289467485454646404L;
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "workstream")
	private String workstream;
	
	@Column(name = "prototype")
	private String prototype;
	
	@Column(name = "driver_type")
	private String driver_type;
	
	public String getDriver_type() {
		return driver_type;
	}





	public void setDriver_type(String driver_type) {
		this.driver_type = driver_type;
	}





	@Column(name = "driver")
	private String driver;
	
	@Column(name = "dependant_activity")
	private String dependant_activity;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "DDC_Sup_WsWs")
	private String ddc_sup_wsws;
	
	@Column(name = "DDC_Sup_WsDDC")
	private String ddc_sup_wsddc;
	
	@Column(name = "DDC_Not_Sup_WsWs")
	private String ddc_not_sup_wsws;
	
	@Column(name = "DDC_Not_Sup_WsDDC")
	private String ddc_not_sup_wsddc;
	
	@Column(name = "Taas_Ws")
	private String taas_ws;
	
	
	@Column(name = "Taas_DDC")
	private String taas_ddc;
	
	@Column(name = "bu_dependency")
	private String bu_dependency;
	
	@Column(name = "activity_effort_driver")
	private String activity_effort_driver;
	
	@Column(name = "pwc_responsibility")
	private String pwc_responsibility;
	
	@Column(name = "activity")
	private String activity;
	
	  



	public String getActivity() {
		return activity;
	}





	public void setActivity(String activity) {
		this.activity = activity;
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





	public String getPrototype() {
		return prototype;
	}





	public void setPrototype(String prototype) {
		this.prototype = prototype;
	}





	public String getDriver() {
		return driver;
	}





	public void setDriver(String driver) {
		this.driver = driver;
	}





	public String getDependant_activity() {
		return dependant_activity;
	}





	public void setDependant_activity(String dependant_activity) {
		this.dependant_activity = dependant_activity;
	}





	public String getUnit() {
		return unit;
	}





	public void setUnit(String unit) {
		this.unit = unit;
	}





	public String getDdc_sup_wsws() {
		return ddc_sup_wsws;
	}





	public void setDdc_sup_wsws(String ddc_sup_wsws) {
		this.ddc_sup_wsws = ddc_sup_wsws;
	}





	public String getDdc_sup_wsddc() {
		return ddc_sup_wsddc;
	}





	public void setDdc_sup_wsddc(String ddc_sup_wsddc) {
		this.ddc_sup_wsddc = ddc_sup_wsddc;
	}





	public String getDdc_not_sup_wsws() {
		return ddc_not_sup_wsws;
	}





	public void setDdc_not_sup_wsws(String ddc_not_sup_wsws) {
		this.ddc_not_sup_wsws = ddc_not_sup_wsws;
	}





	public String getDdc_not_sup_wsddc() {
		return ddc_not_sup_wsddc;
	}





	public void setDdc_not_sup_wsddc(String ddc_not_sup_wsddc) {
		this.ddc_not_sup_wsddc = ddc_not_sup_wsddc;
	}





	public String getTaas_ws() {
		return taas_ws;
	}





	public void setTaas_ws(String taas_ws) {
		this.taas_ws = taas_ws;
	}





	public String getTaas_ddc() {
		return taas_ddc;
	}





	public void setTaas_ddc(String taas_ddc) {
		this.taas_ddc = taas_ddc;
	}





	public String getBu_dependency() {
		return bu_dependency;
	}





	public void setBu_dependency(String bu_dependency) {
		this.bu_dependency = bu_dependency;
	}





	public String getActivity_effort_driver() {
		return activity_effort_driver;
	}





	public void setActivity_effort_driver(String activity_effort_driver) {
		this.activity_effort_driver = activity_effort_driver;
	}





	public String getPwc_responsibility() {
		return pwc_responsibility;
	}





	public void setPwc_responsibility(String pwc_responsibility) {
		this.pwc_responsibility = pwc_responsibility;
	}





	



	public ActivityManagementData(Integer id, String workstream,
			String prototype, String driver, String dependant_activity,
			String unit, String ddc_sup_wsws, String ddc_sup_wsddc,
			String ddc_not_sup_wsws, String ddc_not_sup_wsddc, String taas_ws,
			String taas_ddc, String bu_dependency,
			String activity_effort_driver, String pwc_responsibility,String activity,String driver_type) {
		super();
		this.id = id;
		this.workstream = workstream;
		this.prototype = prototype;
		this.activity=activity;
		this.driver = driver;
		this.dependant_activity = dependant_activity;
		this.unit = unit;
		this.ddc_sup_wsws = ddc_sup_wsws;
		this.ddc_sup_wsddc = ddc_sup_wsddc;
		this.ddc_not_sup_wsws = ddc_not_sup_wsws;
		this.ddc_not_sup_wsddc = ddc_not_sup_wsddc;
		this.taas_ws = taas_ws;
		this.taas_ddc = taas_ddc;
		this.bu_dependency = bu_dependency;
		this.activity_effort_driver = activity_effort_driver;
		this.pwc_responsibility = pwc_responsibility;
		this.driver_type=driver_type;
		
	}





	public ActivityManagementData() {
		
	}
    
	
	
}