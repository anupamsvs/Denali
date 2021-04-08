package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.StaffDriverVo;
import com.pwc.denali2.estimator.model.Unit;
import com.pwc.denali2.estimator.dao.ProjectUnitsDao;

@Repository
public class ProjectUnitsDaoImpl extends
AbstractDao<Integer, ProjectUnit> implements ProjectUnitsDao {

// projec t_units 

	@Override
	public String saveUnit(Unit unit,String idparam) {
		

		
		return "";
	}
	
	@Override
	public Map<String,ProjectUnit> listByUnit(String idparam) {
		Map<String,ProjectUnit> mp = new HashMap<String, ProjectUnit>();
		Map<String,Map<String,Object>> mpgrp = new HashMap<String, Map<String,Object>>();
	/*	String sql = "SELECT mu.unit FROM `project_units` pu" +
				" JOIN master_units mu ON mu.id = pu.master_rates_id" +
				" WHERE engagement_id = "+idparam+"" +
				" ORDER BY  mu.unit";
				*/
		String sql = "SELECT mu.unit,group_concat(pu.value) as unit_con FROM `project_units` pu"+
					" JOIN master_units mu ON mu.id = pu.master_rates_id"+
					" WHERE engagement_id = "+idparam+""+
					" GROUP BY mu.unit"+
					" ORDER BY  pu.id";
		
		String getUnits = "SELECT mu.unit,pu.* FROM `project_units` pu"+
				" JOIN master_units mu ON mu.id = pu.master_rates_id"+
				" WHERE engagement_id = "+idparam+""+
				" ORDER BY  pu.id";
		
		List<Map<String, Object>> units = jdbcTemplate.queryForList(sql);
		
		for(Map<String, Object> group : units){
			mpgrp.put(group.get("unit")+"", group);
		}
		
		
		List<ProjectUnit> result = jdbcTemplate.query(getUnits,
				new BeanPropertyRowMapper<ProjectUnit>(
						ProjectUnit.class));

		for(ProjectUnit pu : result){
			Map<String,Object> group = mpgrp.get(pu.getUnit()+"");
			String str_group = group.get("unit_con").toString();
			Double new_value = arrayProduct(splitToDouble(str_group,","));
			pu.setValue(new_value);
			mp.put(pu.getMaster_rates_id()+"", pu);
		}
		return mp;
	}
	public static Double[] splitToDouble(String str,String delimiter){
		if(str != null){
			String[] strArray = str.split(delimiter);
			Double[] new_array = new Double[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
				new_array[i] = Double.parseDouble(strArray[i]);
			}
	return new_array;
		}else{
			Double[] zero =  new Double[1];
			zero[0] = 0.00;
			return zero;
		}
	}
	
	public static Double arrayProduct(Double[] array){
		Double rtn= 1.00;
	    for(Double i: array){
	        rtn*=i;
	    }
	    return rtn;
	}
	
	@Override
	public Map<String, Object> listUnit(String idparam) {
		List<Unit> unitList = null;
		String sql="",sqlinsert="",resultType ="",msg="";
		
		// and version =''
//		String sqldelete ="delete from project_units where engagement_id ='"+idparam+"'";
//		jdbcTemplate.update(sqldelete);
		
		String sqlSelect = "SELECT * FROM master_units";
		List<Unit> masterunitList = jdbcTemplate.query(sqlSelect,
				new BeanPropertyRowMapper<Unit>(
						Unit.class));
		
		if(masterunitList.size() > 0){
			
			for(Unit masterunit:masterunitList){
				double count = 0.0;
				if(!StringUtils.isEmpty(masterunit.getConstant_value()) && (masterunit.getExpression().toLowerCase().equals("constant"))){
					
					count = masterunit.getConstant_value();
				}else{

				
				if(masterunit.getExpression().toLowerCase().equals("count") && (!StringUtils.isEmpty(masterunit.getSourceColumn())) &&(!StringUtils.isEmpty(masterunit.getSourceTable()))){
					resultType="count(distinct "+masterunit.getSourceColumn()+")";
			    }else if((masterunit.getExpression().toLowerCase().equals("min")) && (!StringUtils.isEmpty(masterunit.getSourceColumn())) && (!StringUtils.isEmpty(masterunit.getSourceTable()))){
			    	resultType = "min("+masterunit.getSourceColumn()+")";
			    }else if((masterunit.getExpression().toLowerCase().equals("max")) && (!StringUtils.isEmpty(masterunit.getSourceColumn())) && (!StringUtils.isEmpty(masterunit.getSourceTable()))){
			    	resultType = "max("+masterunit.getSourceColumn()+")";
			    }else if((masterunit.getExpression().toLowerCase().equals("sum")) && (!StringUtils.isEmpty(masterunit.getSourceColumn())) && (!StringUtils.isEmpty(masterunit.getSourceTable()))){
			    	resultType = "sum("+masterunit.getSourceColumn()+")";
			    }
				System.out.println(resultType);
				
				if(!resultType.equals("")){
//				sql = "SELECT "+resultType+" FROM "+masterunit.getSourceTable()+" where engagement_id ='"+idparam+"'";
				
				
				if(masterunit.getSourceTable().equals("engagement")){
				sql = "SELECT "+resultType+" FROM "+masterunit.getSourceTable()+" where id ='"+idparam+"'";
				}else{
				sql = "SELECT "+resultType+" FROM "+masterunit.getSourceTable()+" where engagement_id ='"+idparam+"'";
				}
				if((!StringUtils.isEmpty(masterunit.getFilter_column())) && (!StringUtils.isEmpty(masterunit.getFilter_value())))
				{
					sql += "and "+masterunit.getFilter_column()+" = '"+masterunit.getFilter_value()+"'";
				}
				
				if((!StringUtils.isEmpty(masterunit.getIs_in_scope())))
				{
					String column ="";
					if(!StringUtils.isEmpty(masterunit.getSourceTable())){
						String tableName = masterunit.getSourceTable();
						if(tableName.equalsIgnoreCase("module_transaction_data")){
							column ="is_in_scope_flag";
						}else if(tableName.equalsIgnoreCase("conversion_transaction_data")){
							column ="final_scope_flag";
						}
						sql += "and "+column+" = '"+masterunit.getIs_in_scope()+"'";
					}
					
				}
				
				
				
				System.out.println(sql);
				
				 try {
					  count = jdbcTemplate.queryForObject(sql, Double.class);
					 
					 
					
				} catch (Exception e) {
					msg ="Error Occured while saving Project Units";
				}
				
				}
				}				
				
				 sqlinsert = "insert into project_units(master_rates_id,value,engagement_id) values("+masterunit.getId()+","+count+","+idparam+") ON DUPLICATE KEY UPDATE value = "+count; 
				
				 jdbcTemplate.update(sqlinsert);
				
			}
			
		}
		
		sql ="select project_units.value,CONCAT_WS('####',master_units.unit,master_units.basevariable) as columnName from project_units,master_units where project_units.engagement_id ='"+idparam+"' and project_units.master_rates_id=master_units.id";
        List<Map<String, Object>> listProjectUnits = jdbcTemplate.queryForList(sql);
//		Map<String,String> mapProjectUnits = new HashMap<String, String>();
//		for(Map<String, Object> map :list){
//			mapProjectUnits.put(map.get("columnName").toString(), map.get("value").toString());
//			
//		}
		Map<String, Object> mapUnitDetailDta =new HashMap<String, Object>();
		mapUnitDetailDta.put("masterUnitsData", masterunitList);
		//mapUnitDetailDta.put("ProjectUnitsData", mapProjectUnits);
		mapUnitDetailDta.put("msg", msg);
		mapUnitDetailDta.put("ProjectUnitsData", listProjectUnits);
		
		return mapUnitDetailDta;
	}

	@Override
	public String updateUnit(Unit unit,String idparam) {
		
		
		return "";
		
	}






}
