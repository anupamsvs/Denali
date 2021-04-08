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
@Table(name = "master_units")
public class Unit implements Serializable {
	private static final long serialVersionUID = -4666770189399685284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "unit")
	private String unit;
	
	public Double getConstant_value() {
		return constant_value;
	}



	public void setConstant_value(Double constant_value) {
		this.constant_value = constant_value;
	}



	@Column(name ="constant_value")
	private Double constant_value;

	
	@Column(name = "is_in_scope")
	private String is_in_scope;
	
	

	public Unit(Integer id, String unit, String basevariable, String sourceTable,
			String sourceColumn, Integer value,String expression, String filter_column, String filter_value, String is_in_scope, Double constant_value) {
		super();
		this.id = id;
		this.unit = unit;
		this.basevariable = basevariable;
		this.sourceTable = sourceTable;
		this.sourceColumn = sourceColumn;
		//this.value = value;
		this.expression = expression;
		this.filter_column = filter_column;
		this.filter_value = filter_value;
		this.is_in_scope =is_in_scope;
		this.constant_value =constant_value;
	}



	public String getIs_in_scope() {
		return is_in_scope;
	}



	public void setIs_in_scope(String is_in_scope) {
		this.is_in_scope = is_in_scope;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getBasevariable() {
		return basevariable;
	}



	public void setBasevariable(String basevariable) {
		this.basevariable = basevariable;
	}



	public String getSourceTable() {
		return sourceTable;
	}



	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}



	public String getSourceColumn() {
		return sourceColumn;
	}



	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}



//	public Integer getValue() {
//		return value;
//	}
//
//
//
//	public void setValue(Integer value) {
//		this.value = value;
//	}
//
//

	@Column(name = "basevariable")
	private String basevariable;
	
	@Column(name = "sourceTable")
	private String sourceTable;

	@Column(name = "sourceColumn")
	private String sourceColumn;
//
//	@Column(name = "value")
//	private Integer value;

	public String getExpression() {
		return expression;
	}



	public void setExpression(String expression) {
		this.expression = expression;
	}



	@Column(name = "expression")
	private String expression;
	

	@Column(name = "filter_column")
	private String filter_column;
	

	@Column(name = "filter_value")
	private String filter_value;

	
	public String getFilter_column() {
		return filter_column;
	}



	public void setFilter_column(String filter_column) {
		this.filter_column = filter_column;
	}



	public String getFilter_value() {
		return filter_value;
	}



	public void setFilter_value(String filter_value) {
		this.filter_value = filter_value;
	}



	public Unit() {
	}
}
