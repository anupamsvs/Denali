package com.pwc.denali2.estimator.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.MasterAdminLovs;

@Repository("MasterAdminUtilityDao")
public class MasterAdminUtilityDaoImpl  extends AbstractDao<Integer, MasterAdminLovs> implements MasterAdminUtilityDao{

	@Override
	public List<MasterAdminLovs> getMasterAdminListData(String type, String code) {
		List<MasterAdminLovs> masterAdminDtaList =null;
		String sql = "SELECT * from master_admin_lovs where lower(type) ='"+type+"'";
												
		if(!code.equals("")){
			sql += "and lower(code) like '"+code+"'";
		}
		sql +="order by description";
		System.out.println(sql);
		masterAdminDtaList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));
		return masterAdminDtaList ;

	}
	@Override
	@SuppressWarnings("null")
	public Map<String, List<List>> listByTypeTag(List<String> types,String tag) {
		
		Map<String, List<List>> map = new HashMap<String, List<List>>();
		for(String type:types){
		String sqlByType="SELECT distinct * FROM  master_admin_lovs where lower(type)='"+type+"'";
		if(tag != null){
			sqlByType = sqlByType + " and bu_factor = '"+tag+"'";
		}
		List typeList = jdbcTemplate.queryForList(sqlByType+" order by description");
		if(typeList.isEmpty()){
			 typeList = jdbcTemplate.queryForList("SELECT distinct * FROM  master_admin_lovs where lower(type)='"+type+"' order by description");
		}
		map.put(type, typeList);
		
		}
		return map;
		
	}
	
	@Override
	public List<MasterAdminLovs> listAvailableAdminLov(
			MasterAdminLovs masterAdminLovs,String types) {
		List<MasterAdminLovs> masterAdminList = null;
//		Map map;
//		List<String> filter = types==null?[""]:types.split(",");
		try {
			String sql = "SELECT * FROM master_admin_lovs";
//			for
			String extra = " where lower(type) IN (";
			if(types == ""){
			}else if(types.split(",").length > 0){
				sql = sql  + extra +"\"" + types+"\" ) order by description";
			}
			masterAdminList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<MasterAdminLovs>(
							MasterAdminLovs.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return masterAdminList;
	}
	@Override
	public String getMasterAdminCode(int id){
		
		String sql ="SELECT code FROM master_admin_lovs where id ="+id;
		String code =jdbcTemplate.queryForObject(sql, String.class);
		return code;
		
		
	}
	
	
	

}
