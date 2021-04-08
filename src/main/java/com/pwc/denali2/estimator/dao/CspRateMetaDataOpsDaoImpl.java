package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import java.util.Set;

import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;




import org.springframework.util.StringUtils;

import com.pwc.denali2.estimator.model.ProjectRates;
import com.pwc.denali2.estimator.model.RatesMaster;

@Repository("CspRateMetaDataOpsDao")
public class CspRateMetaDataOpsDaoImpl extends AbstractDao<Integer, ProjectRates>
		implements CspRateMetaDataOpsDao {

	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> findAllOffSiteCspRate(String ver_id,String idparam) {
		String engid =idparam.split("-")[0];
		String projectversionid =idparam.split("-")[1];
		int version;
		if(ver_id.equals("")){
			version = getMaxVersionId(idparam,"project_rates");
			}else{
				version =Integer.parseInt(ver_id);
			}
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		//int version = getMaxVersionId();
//		String distinctlevelsql="select distinct level from project_rates where rate_type = 'csp' and worktype= 'offsite'";
//		List<String> levelList = jdbcTemplate.queryForList(distinctlevelsql, String.class);
		//maprateList.put("levelOffsite", levelList);
		
		String distinctWorkstream="select distinct workstream from project_rates where rate_type = 'csp' and worktype= 'offsite' and engagement_id ='"+engid+"' and version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM project_rates where rate_type ='csp' and worktype ='offsite' and version_id ='"+version+"' and engagement_id ='"+engid+"' and project_version_id='"+projectversionid+"' and workstream ='"+list+"'";
			String sql ="SELECT level,rate FROM project_rates rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ "where rate_type ='csp' and worktype ='offsite' and version_id ='"+version+"' and workstream ='"+list+"' and engagement_id ='"+engid+"'"
					+ "order by mal.description";
			
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), (int) (Double.parseDouble(map.get("rate").toString())));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("offsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	public Map<Object, Object> findAllOnSiteCspRate(String ver_id,String idparam) {
		System.out.println("into onsite csp");
		String engid =idparam.split("-")[0];
		String projectversionid =idparam.split("-")[1];
		initRatesTable(engid,projectversionid);
		int version;
		if(ver_id.equals("")){
			version = getMaxVersionId(idparam,"project_rates");
			}else{
				version =Integer.parseInt(ver_id);
			}
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		
		String distinctWorkstream="select distinct workstream from project_rates where rate_type = 'csp' and worktype= 'onsite' and engagement_id ='"+engid+"' and version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM project_rates where rate_type ='csp' and worktype ='onsite' and version_id ='"+version+"' and engagement_id ='"+engid+"' and project_version_id='"+projectversionid+"' and workstream ='"+list+"'";
			String sql ="SELECT level,rate FROM project_rates rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ "where rate_type ='csp' and worktype ='onsite' and version_id ='"+version+"' and workstream ='"+list+"' and engagement_id ='"+engid+"'"
					+ "order by mal.description";
			
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), (int) (Double.parseDouble(map.get("rate").toString())));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("onsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	public Map<Object, Object> findAllOffSiteRspRate(String ver_id,String idparam) {
		String engid =idparam.split("-")[0];
		String projectversionid =idparam.split("-")[1];
		int version;
		if(ver_id.equals("")){
			version = getMaxVersionId(idparam,"project_rates");
			}else{
				version =Integer.parseInt(ver_id);
			}
		
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
	

		
		String distinctWorkstream="select distinct workstream from project_rates where rate_type = 'rsr' and worktype= 'offsite' and engagement_id ='"+engid+"' and version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM project_rates where rate_type ='rsp' and worktype ='offsite' and version_id ='"+version+"' and engagement_id ='"+engid+"' and project_version_id='"+projectversionid+"' and workstream ='"+list+"'";
			String sql ="SELECT level,rate FROM project_rates rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ "where rate_type ='rsr' and worktype ='offsite' and version_id ='"+version+"' and workstream ='"+list+"' and engagement_id ='"+engid+"'"
					+ "order by mal.description";
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), (int) (Double.parseDouble(map.get("rate").toString())));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("offsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	public Map<Object, Object> findAllOnSiteRspRate(String ver_id,String idparam) {
		String engid =idparam.split("-")[0];
		String projectversionid =idparam.split("-")[1];
		int version;
		if(ver_id.equals("")){
			version = getMaxVersionId(idparam,"project_rates");
			}else{
				version =Integer.parseInt(ver_id);
			}
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		
		String distinctWorkstream="select distinct workstream from project_rates where rate_type = 'rsr' and worktype= 'onsite' and engagement_id ='"+engid+"' and version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM project_rates where rate_type ='rsp' and worktype ='onsite' and version_id ='"+version+"' and engagement_id ='"+engid+"' and project_version_id='"+projectversionid+"' and workstream ='"+list+"'";
			String sql ="SELECT level,rate FROM project_rates rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ "where rate_type ='rsr' and worktype ='onsite' and version_id ='"+version+"' and workstream ='"+list+"' and engagement_id ='"+engid+"'"
					+ "order by mal.description";
			
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"),(int) (Double.parseDouble(map.get("rate").toString())));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("onsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	
	
	
	
	
	
	
	
	public void initRatesTable(String engid, String projectversionid){
		System.out.println("into init");
		int count =0;
		String sqlInsert="";
		String sql ="select count(*) from project_rates where engagement_id ='"+engid+"'";	
		System.out.println(sql);
		 count =jdbcTemplate.queryForObject(sql, Integer.class);
		 if(count == 0){
			 
		 
			 String sqlratesmaster ="SELECT * FROM rates_master where version_id =(select max(version_id) from rates_master);";	
				 
				List<RatesMaster> rateList = jdbcTemplate.query(sqlratesmaster,
						new BeanPropertyRowMapper<RatesMaster>(
								RatesMaster.class));
				for(RatesMaster rates:rateList){
					sqlInsert ="insert into project_rates (rate_type,rate,workstream,level,worktype,level_id,workstream_id,version_id,engagement_id,create_dt,update_dt) "
							+ "values('"+rates.getRate_type()+"','"+rates.getRate()+"','"+rates.getWorkstream()+"','"+rates.getLevel()+"','"+rates.getWorktype()+"','"+rates.getLevel_id()+"','"+rates.getWorkstream_id()+"','0','"+engid+"',now(),now());";
					jdbcTemplate.update(sqlInsert);
				}
			 
			
		 }
		
		

		
	}
	
	


	public int getMaxVersionId(String idparam,String table){
		
		String max_version_idSql ="";
		int version =0;
		if(table.equals("project_rates")){
			String engid =idparam.split("-")[0];
			String projectversionid =idparam.split("-")[1];
			max_version_idSql ="SELECT max(version_id) FROM project_rates where engagement_id ='"+engid+"'";
			version =jdbcTemplate.queryForObject(max_version_idSql, Integer.class);
		}
		if (table.equals("rates_master")) {
			 max_version_idSql ="SELECT max(version_id) FROM rates_master";
			version =jdbcTemplate.queryForObject(max_version_idSql, Integer.class);
		}
		
		
		
		return version;
	}

	@Override
	public String updateRates(String onsiteDataCSP, String offsitedataCsp, String onsiteDataRSP, String offsitedataRsp,String idparam,String savetype) {
		String msg ="";
		String engid =idparam.split("-")[0];
		String projectversionid =idparam.split("-")[1];
		String  newversion="";
		int version = getMaxVersionId(idparam,"project_rates");
		if(savetype.equals("save")){
			newversion =version+"";
		}else if(savetype.equals("version")){
			
			int maxver =(version + 1);
			newversion = maxver +"";
		
		String sqlInsert ="";
		String sql ="select * from project_rates where engagement_id ='"+engid+"' and version_id ='"+version+"'";	
		List<ProjectRates> rateList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ProjectRates>(
						ProjectRates.class));
		for(ProjectRates rates:rateList){
			sqlInsert ="insert into project_rates (rate_type,rate,workstream,level,worktype,level_id,workstream_id,create_dt,update_dt,version_id,engagement_id) values('"+rates.getRate_type()+"','"+rates.getRate()+"','"+rates.getWorkstream()+"','"+rates.getLevel()+"','"+rates.getWorktype()+"','"+rates.getLevel_id()+"','"+rates.getWorkstream_id()+"',now(),now(),'"+newversion+"','"+engid+"');";
//			rates.setCreate_date(new Date());
//			rates.setUpdate_date(new Date());
//			rates.setVersion_id(newversion+"");
//			persist(rates);
			jdbcTemplate.update(sqlInsert);
		}
		}
//		jdbcTemplate.update(sqlInsert);
		try {
			if(!StringUtils.isEmpty(onsiteDataCSP)){
				System.out.println("into onsite csp");
				updateRateCspOnSite(onsiteDataCSP,newversion,engid,projectversionid);
			}
			if(!StringUtils.isEmpty(offsitedataCsp)){
				System.out.println("into csp offsit");
				updateRateCspOffsite(offsitedataCsp,newversion,engid,projectversionid);
			}
			if(!StringUtils.isEmpty(offsitedataRsp)){
				System.out.println("into rsp offsit");
				updateRateRspOffsite(offsitedataRsp,newversion,engid,projectversionid);
			}
			if(!StringUtils.isEmpty(onsiteDataRSP)){
				System.out.println("into rsp onsit");
				updateRateRspOnsite(onsiteDataRSP,newversion,engid,projectversionid);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		msg ="data updated with creation of new version";
		return msg;
	}

	private void updateRateRspOnsite(String onsiteDataRSP,String newversion,String engid,String projectversionid) {
		JSONObject jsonarr = new JSONObject(onsiteDataRSP);
        String sql="";
         Iterator itr = jsonarr.keys();
         Set<String> keyset=jsonarr.keySet();
         
         for(String i:keyset){
        	 JSONObject ratesobj = jsonarr.getJSONObject(i);
//        	 System.out.println(ratesobj.get("Workstream"));
//        	 System.out.println(ratesobj.keySet());
        	 for(String key:ratesobj.keySet()){
        	
        		 if(!key.equals("Workstream") && !key.equals("id")){
        		 System.out.println(key+"====>"+ratesobj.get(key));
        		 
        		 sql ="update project_rates set rate ='"+ratesobj.get(key)+"' ,update_dt=now() where engagement_id ='"+engid+"' and rate_type ='rsr' and workstream ='"+ratesobj.get("Workstream")+"' and level ='"+key+"' and version_id='"+newversion+"' and worktype ='onsite';";
        	 	
        		 jdbcTemplate.update(sql);
        		 System.out.println(sql);
        		 }
        	 }
       
        	 //jdbcTemplate.update(sql);
         }
		
	}

	private void updateRateRspOffsite(String offsitedataRsp,String newversion,String engid,String projectversionid) {
		JSONObject jsonarr = new JSONObject(offsitedataRsp);
        String sql="";
         Iterator itr = jsonarr.keys();
         Set<String> keyset=jsonarr.keySet();
         
         for(String i:keyset){
        	 JSONObject ratesobj = jsonarr.getJSONObject(i);
//        	 System.out.println(ratesobj.get("Workstream"));
//        	 System.out.println(ratesobj.keySet());
        	 for(String key:ratesobj.keySet()){
        		
        		 if(!key.equals("Workstream") && !key.equals("id")){
        		 System.out.println(key+"====>"+ratesobj.get(key));
        		 sql ="update project_rates set rate ='"+ratesobj.get(key)+"' ,update_dt=now() where engagement_id ='"+engid+"' and rate_type ='rsr' and workstream ='"+ratesobj.get("Workstream")+"' and level ='"+key+"' and version_id='"+newversion+"' and worktype ='offsite';";
        		 jdbcTemplate.update(sql);
        	 	 System.out.println(sql);
        		 }
        	 }
        	
        
         }
		
		
	}

	private void updateRateCspOffsite(String offsitedataCsp,String newversion,String engid,String projectversionid) {
		
		
		JSONObject jsonarr = new JSONObject(offsitedataCsp);
        String sql="";
         Iterator itr = jsonarr.keys();
         Set<String> keyset=jsonarr.keySet();
         
         for(String i:keyset){
        	 JSONObject ratesobj = jsonarr.getJSONObject(i);
//        	 System.out.println(ratesobj.get("Workstream"));
//        	 System.out.println(ratesobj.keySet());
        	 for(String key:ratesobj.keySet()){
        	
        		 if(!key.equals("Workstream") && !key.equals("id")){
        		 System.out.println(key+"====>"+ratesobj.get(key));
        		 sql ="update project_rates set rate ='"+ratesobj.get(key)+"' ,update_dt=now() where engagement_id ='"+engid+"' and rate_type ='csp' and workstream ='"+ratesobj.get("Workstream")+"' and level ='"+key+"' and version_id='"+newversion+"' and worktype ='offsite';";
        		 jdbcTemplate.update(sql);
        	 	 System.out.println(sql);
        		 
        		 }
        	 }
        	 
        	 
         }
		
	}

	private void updateRateCspOnSite(String onsiteDataCSP, String newversion,String engid,String projectversionid) {
		
		JSONObject jsonarr = new JSONObject(onsiteDataCSP);
        String sql="";
         Iterator itr = jsonarr.keys();
         Set<String> keyset=jsonarr.keySet();
         
         for(String i:keyset){
        	 JSONObject ratesobj = jsonarr.getJSONObject(i);
//        	 System.out.println(ratesobj.get("Workstream"));
//        	 System.out.println(ratesobj.keySet());
        	 for(String key:ratesobj.keySet()){
        		 if(!key.equals("Workstream") && !key.equals("id")){
        		 System.out.println(key+"====>"+ratesobj.get(key));
        		 sql ="update project_rates set rate ='"+ratesobj.get(key)+"' ,update_dt=now() where engagement_id ='"+engid+"'   and rate_type ='csp' and workstream ='"+ratesobj.get("Workstream")+"' and level ='"+key+"' and version_id='"+newversion+"' and worktype ='onsite';";
        		 jdbcTemplate.update(sql);
        	 	 System.out.println(sql);
        		 }
        	 }
        	 
         }
		
	}
	@Override
	public List<Map<String, Object>> getVersionList(String idparam){
		String engid =idparam.split("-")[0];
		//String projectversionid =idparam.split("-")[1];
		String sql ="select distinct version_id as version_id,date(update_dt) as create_dt from project_rates where engagement_id ='"+engid+"'  order by version_id desc";

		///List<String> versionList =jdbcTemplate.queryForList(sql,String.class);
		System.out.println(sql);
		//List<String> ver_dataList = jdbcTemplate.queryForList(sql, String.class);
		List<Map<String, Object>> versn_dataList = jdbcTemplate.queryForList(sql);
		System.out.println(versn_dataList);
		
		Map<Object, Object> versnlistWithTym = new HashMap<Object, Object>();
		
		
		for(Map<String, Object> map:versn_dataList){
			versnlistWithTym.put(map.get("version_id"),map.get("update_dt"));
		}
		return versn_dataList;
		
	}

	
//	**********************************************
	
	
	
	public Map<Object, Object> getOffSiteCspRateAdmin() {
		
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		 int version = getMaxVersionId("","rates_master");
//		String distinctlevelsql="select distinct level from project_rates where rate_type = 'csp' and worktype= 'offsite'";
//		List<String> levelList = jdbcTemplate.queryForList(distinctlevelsql, String.class);
		//maprateList.put("levelOffsite", levelList);
		
		String distinctWorkstream="select distinct workstream from rates_master where rate_type = 'csp' and worktype= 'offsite' and version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM rates_master where rate_type ='csp' and worktype ='offsite' and version_id ='"+version+"' and  workstream ='"+list+"'";
			String sql ="SELECT level,rate FROM rates_master rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ "where rate_type ='csp' and worktype ='offsite' and version_id ='"+version+"' and workstream ='"+list+"' "
					+ "order by mal.description";
			
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), map.get("rate"));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("offsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	public Map<Object, Object> getOnSiteCspRateAdmin() {
		
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		int version = getMaxVersionId("","rates_master");
		String distinctWorkstream="select distinct workstream from rates_master where rate_type = 'csp' and worktype= 'onsite' and  version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM rates_master where rate_type ='csp' and worktype ='onsite' and version_id ='"+version+"' and workstream ='"+list+"'";
			
			String sql ="SELECT level,rate FROM rates_master rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ " where rate_type ='csp' and worktype ='onsite' and version_id ='"+version+"' and workstream ='"+list+"' "
					+ "order by mal.description";
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), map.get("rate"));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("onsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	public Map<Object, Object> getOffSiteRspRateAdmin() {
		
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		int version = getMaxVersionId("","rates_master");
//		String distinctlevelsql="select distinct level from project_rates where rate_type = 'csp' and worktype= 'offsite'";
//		List<String> levelList = jdbcTemplate.queryForList(distinctlevelsql, String.class);
		//maprateList.put("levelOffsite", levelList);
		
		String distinctWorkstream="select distinct workstream from rates_master where rate_type = 'rsr' and worktype= 'offsite' and  version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			
			//String sql ="SELECT level,rate FROM rates_master where rate_type ='rsp' and worktype ='offsite' and version_id ='"+version+"' and workstream ='"+list+"'";
			String sql ="SELECT level,rate FROM rates_master rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ " where rate_type ='rsr' and worktype ='offsite' and version_id ='"+version+"' and workstream ='"+list+"' "
					+ "order by mal.description";
			
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), map.get("rate"));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("offsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}
	
	public Map<Object, Object> getOnSiteRspRateAdmin() {
		
		Map<Object, Object> maprateList = new HashMap<Object, Object>();
		int version = getMaxVersionId("","rates_master");
		String distinctWorkstream="select distinct workstream from rates_master where rate_type = 'rsr' and worktype= 'onsite' and version_id ='"+version+"'";
		List<String> workstreamList = jdbcTemplate.queryForList(distinctWorkstream, String.class);
		List<Map<Object, Object>> listrates = new ArrayList<Map<Object,Object>>();
		int count =1;
		for(String list:workstreamList){
			String sql ="SELECT level,rate FROM rates_master rm "
					+ "join master_admin_lovs mal ON mal.id = rm.level_id  "
					+ "where rate_type ='rsr' and worktype ='onsite' and version_id ='"+version+"' and workstream ='"+list+"' "
					+ "order by mal.description";
			//String sql ="SELECT level,rate FROM rates_master where rate_type ='rsp' and worktype ='onsite' and version_id ='"+version+"' and  workstream ='"+list+"'";
			System.out.println(sql);
			List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
			
			Map<Object, Object> level_rate_data = new LinkedHashMap<Object, Object>();
			
			for(Map<String, Object> map :data){
				level_rate_data.put(map.get("level"), map.get("rate"));
			}
			level_rate_data.put("Workstream",list);
			level_rate_data.put("id",count);
			listrates.add(level_rate_data);
			count ++;
			//maprateList.put("Workstream",list);
		}
		maprateList.put("onsite", listrates);
		System.out.println(maprateList);
		return maprateList;
	}

	@Override
	public Map<String, Object> refreshAdminRates() {
		
		Map<String, Object> returnmap = new HashMap<String, Object>();
		Map<Object, Object> allOnSite = getOnSiteCspRateAdmin();
		Map<Object, Object> allOffSite = getOffSiteCspRateAdmin();
		Map<Object, Object> allOnSiteRsp = getOnSiteRspRateAdmin();
		Map<Object, Object> allOffSiteRsp = getOffSiteRspRateAdmin();
		returnmap.put("allOnSite", allOnSite.get("onsite"));
		returnmap.put("allOffSite", allOffSite.get("offsite"));
		returnmap.put("onsiteRsp", allOnSiteRsp.get("onsite"));
		returnmap.put("offsiteRsp", allOffSiteRsp.get("offsite"));
		return returnmap;
	}
	
	@Override
	public String getCreateDate(String idparam,String ratesVersion){
		String engid =idparam.split("-")[0];
		
		String projectversionid =idparam.split("-")[1];
		int version ;
		if(ratesVersion.equals("")){
			version = getMaxVersionId(idparam,"project_rates");
			}else{
				version =Integer.parseInt(ratesVersion);
			}
		String create_dt ="";
		
		String creatDtSql ="SELECT distinct date(update_dt) FROM project_rates where  engagement_id ='"+engid+"' and version_id='"+getMaxVersionId(idparam,"project_rates")+"' order by update_dt desc limit 1";
		Date date =jdbcTemplate.queryForObject(creatDtSql,Date.class);
		
		if(!StringUtils.isEmpty(date)){
			
			create_dt=date.toString();
		}
		System.out.println(create_dt);	
		return create_dt +"**"+version;
		
		
	}
	
	
	
	
	

}
