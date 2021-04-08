package com.pwc.denali2.estimator.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ConversionMetaData;
import com.pwc.denali2.estimator.model.DetailedSummary;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.RatesMaster;


@Repository("DetailedSummaryDao")
public class DetailedSummaryDaoImpl extends AbstractDao<Integer, DetailedSummary> implements DetailedSummaryDao {
	
	@Autowired
	MasterAdminUtilityDaoImpl masterAdminUtilityDaoImpl;
	
	@Override
	public boolean save(List<DetailedSummary> summaryList){
		boolean success = true;
		for(DetailedSummary summary: summaryList){
			try{
				persist(summary);
			}catch(Exception e){
				success = false;
			}
		}
		return success;
	}
	
	
	@Override
	public Map<String, List> list(int engagement_id){
		Map<String,List> mapped = new HashMap<String, List>();
		try{
		
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		for(MasterAdminLovs work : worstreamList){
		String sql = "SELECT ds.*,mal.code as worktream,mal2.code as prototype,amdd.activity FROM detailed_summary ds "+
					"LEFT JOIN activity_master_data_driver amdd ON amdd.id = ds.activity_id "+
					"LEFT JOIN master_admin_lovs mal ON mal.id = amdd.workstream "+
					"LEFT JOIN master_admin_lovs mal2 ON mal2.id = amdd.prototype "+
					"where amdd.workstream = "+work.getId()+" AND engagement_id = "+engagement_id +
					" order by amdd.prototype,amdd.activity ";
		List summaries = jdbcTemplate.queryForList(sql);
		if(summaries.isEmpty() !=  true){
		mapped.put(work.getCode()+"", summaries);
		}
		}
		return mapped;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public Map<String, List<DetailedSummary>> listModel(int engagement_id){
		Map<String,List<DetailedSummary>> mapped = new HashMap<String, List<DetailedSummary>>();
		try{
		
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		for(MasterAdminLovs work : worstreamList){
		String sql = "SELECT ds.*,mal.code as worktream,mal2.code as prototype,amdd.activity as act FROM detailed_summary ds "+
					"LEFT JOIN activity_master_data_driver amdd ON amdd.id = ds.activity_id "+
					"LEFT JOIN master_admin_lovs mal ON mal.id = amdd.workstream "+
					"LEFT JOIN master_admin_lovs mal2 ON mal2.id = amdd.prototype "+
					"where amdd.workstream = "+work.getId()+" AND engagement_id = "+engagement_id +
					" order by amdd.prototype,amdd.activity ";
//		List summaries = jdbcTemplate.queryForList(sql,);
		List<DetailedSummary> summaries = jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<DetailedSummary>(DetailedSummary.class));
	
		if(summaries.isEmpty() !=  true){
		mapped.put(work.getCode()+"", summaries);
		}
		}
		return mapped;
		
		}catch(Exception e){
			e.printStackTrace();
			return mapped;
		}
		
	}
	
	@Override
	public boolean saveByQuery(String query){
		try{
			jdbcTemplate.execute(query);
			return true;
		}catch(Exception e){
			return false;
		}
	}


	@Override
	public boolean overrideEfforts(int engagement_id,Map<String,Map<String,String>> data) {
		// TODO Auto-generated method stub
		String calculateQl = "INSERT INTO detailed_summary (`id`,`engagement_id`,`activity_id`,`effort_onsite_override`,`effort_ddc_override`,`csp_onsite`,`csp_ddc`,`rsr_onsite`,`rsr_ddc`,`margin_onsite`,`margin_ddc`)"
				+ "		SELECT  {{id}},"+engagement_id+"," +
				"       activity_id," +
				"       {{onsite}} AS effort_onsite_override," +
				"       {{ddc}} AS effort_ddc_override," +
				"       (onsite_csp*{{onsite}}) AS csp_onsite," +
				"       (ddc_csp*{{ddc}}) AS csp_ddc," +
				"       (onsite_rsr*{{onsite}}) AS rsr_onsite," +
				"       (ddc_rsr*{{ddc}}) AS rsr_ddc," +
				"       (onsite_csp*{{onsite}}) - (onsite_rsr*{{onsite}})AS margin_onsite," +
				"       (ddc_csp*{{ddc}}) - (ddc_rsr*{{ddc}}) AS margin_ddc" +
				"        FROM (SELECT  ds.effort_ddc_override,ds.effort_onsite_override, ds.activity_id,sdbt_workstream.rate AS ddc_csp," +
				"          sdbt_workstream.rate AS onsite_csp," +
				"          sdbt_ddc_rsr.rate AS ddc_rsr," +
				"          sdbt_workstream_rsr.rate AS onsite_rsr FROM detailed_summary ds" +
				"	LEFT JOIN activity_master_data_driver amdd ON ds.activity_id = amdd.id" +
				"  LEFT JOIN staff_driver_blended_rate_transaction sdbt_ddc ON sdbt_ddc.engagement_id = "+engagement_id+"" +
				"   AND sdbt_ddc.workstream_id = amdd.workstream" +
				"   AND sdbt_ddc.rate_type = \"csp\"" +
				"   AND sdbt_ddc.work_type = \"ddc\"" +
				"   LEFT JOIN staff_driver_blended_rate_transaction sdbt_workstream ON sdbt_workstream.engagement_id = "+engagement_id+"" +
				"   AND sdbt_workstream.workstream_id = amdd.workstream" +
				"   AND sdbt_workstream.rate_type = \"csp\"" +
				"   AND sdbt_workstream.work_type = \"workstream\"" +
				"   LEFT JOIN staff_driver_blended_rate_transaction sdbt_ddc_rsr ON sdbt_ddc_rsr.engagement_id = "+engagement_id+"" +
				"   AND sdbt_ddc_rsr.workstream_id = amdd.workstream" +
				"   AND sdbt_ddc_rsr.rate_type = \"rsr\"" +
				"   AND sdbt_ddc_rsr.work_type = \"ddc\"" +
				"   LEFT JOIN staff_driver_blended_rate_transaction sdbt_workstream_rsr ON sdbt_workstream_rsr.engagement_id = "+engagement_id+"" +
				"   AND sdbt_workstream_rsr.workstream_id = amdd.workstream" +
				"   AND sdbt_workstream_rsr.rate_type = \"rsr\"" +
				"   AND sdbt_workstream_rsr.work_type = \"workstream\"" +
				"   WHERE ds.id = {{id}}) cost ON DUPLICATE KEY"+ 
				"   UPDATE effort_onsite_override= " +
				"   VALUES(effort_onsite_override),effort_ddc_override= " +
				"   VALUES(effort_ddc_override),csp_onsite= " +
				"   VALUES(csp_onsite), csp_ddc= " +
				"   VALUES(csp_ddc),rsr_onsite= " +
				"   VALUES(rsr_onsite),rsr_ddc= " +
				"   VALUES(rsr_ddc),margin_onsite= " +
				"   VALUES(margin_onsite),margin_ddc= " +
				"   VALUES(margin_ddc),updated_at=NOW() ";
		
		boolean result = false;
		for (Entry<String, Map<String, String>> iterator : data.entrySet()) {
			String newSql = calculateQl;
			String id = iterator.getKey();
			Map<String, String> mapMe = iterator.getValue();
			newSql = newSql.replaceAll("\\{\\{id\\}\\}", id);
			//check if empty
			if(mapMe.get("onsite") != null){
				newSql = newSql.replaceAll("\\{\\{onsite\\}\\}",  mapMe.get("onsite"));
			}else{
				newSql = newSql.replaceAll("\\{\\{onsite\\}\\}", "effort_onsite_override");
			}
			
			if(mapMe.get("ddc") != null){
				newSql = newSql.replaceAll("\\{\\{ddc\\}\\}",  mapMe.get("ddc"));
			}else{
				newSql = newSql.replaceAll("\\{\\{ddc\\}\\}",  "effort_ddc_override");
			}
			try{
				jdbcTemplate.execute(newSql);
			}catch(Exception e){
				return false;
			}
		}
		return false;
	}
	
	//Detailed Summary effort
	@Override
	public Map<String,List> listDetailedEffortGroups(int engagement_id) {
		Map<String,List> mapped = new HashMap<String, List>();
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		for(MasterAdminLovs work : worstreamList){
		String sql = "SELECT SUM(ds.effort_onsite) as effort_onsite,SUM(ds.effort_ddc) as effort_ddc, amdd.workstream," +
				"       amdd.activity_group,mal.code" +
				" FROM detailed_summary ds" +
				" LEFT JOIN activity_master_data_driver amdd ON amdd.id = ds.activity_id" +
				" LEFT JOIN master_admin_lovs mal ON amdd.workstream = mal.id "
				+ " WHERE amdd.workstream = "+work.getId()+" AND ds.engagement_id = "+engagement_id+"" +
				" GROUP BY amdd.activity_group" +
				" ORDER BY mal.description";
		List listDetailedEffortGroups = jdbcTemplate.queryForList(sql);
		mapped.put(work.getId()+"", listDetailedEffortGroups);
		}
		return mapped;
	}
	
	@Override
	public Map<String,List> listDetailedEffort(int engagement_id) {
		Map<String,List> mapped = new HashMap<String, List>();
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		for(MasterAdminLovs work : worstreamList){
		String sql = "SELECT cost.*" +
				" FROM master_admin_lovs malOuter" +
				" JOIN" +
				"  (SELECT SUM(ds.csp_ddc) AS ddc," +
				"          SUM(ds.csp_onsite) AS onsite," +
				"          mal.code," +
				"          amdd.activity_group," +
				"          amdd.prototype,amdd.workstream,concat(amdd.workstream,\"_\",amdd.prototype,\"_\",amdd.activity_group) as idkey" +
				"   FROM detailed_summary ds" +
				"   LEFT JOIN activity_master_data_driver amdd ON amdd.id = ds.activity_id" +
				"   LEFT JOIN master_admin_lovs mal ON mal.id = amdd.workstream" +
				"   WHERE amdd.workstream = "+work.getId()+"  AND ds.engagement_id = "+engagement_id+"" +
				"   GROUP BY amdd.activity_group," +
				"            amdd.prototype," +
				"            amdd.workstream" +
				"   ) cost ON cost.prototype = malOuter.id" +
				" WHERE TYPE = \"prototype\" ORDER BY prototype";
		List detailedEffortList = jdbcTemplate.queryForList(sql);
		mapped.put(work.getId()+"", detailedEffortList);
		}
		return mapped;
	}
	
	@Override
	public Map<String,List> listDetailedEffortExport(int engagement_id) {
		Map<String,List> mapped = new HashMap<String, List>();
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		for(MasterAdminLovs work : worstreamList){
		String sql = "SELECT cost.*" +
				" FROM master_admin_lovs malOuter" +
				" JOIN" +
				"  (SELECT SUM(ds.csp_ddc) AS ddc," +
				"          SUM(ds.csp_onsite) AS onsite," +
				"          mal.code," +
				"          amdd.activity_group," +
				"          amdd.prototype,amdd.workstream,concat(amdd.workstream,\"_\",amdd.prototype,\"_\",amdd.activity_group) as idkey" +
				"   FROM detailed_summary ds" +
				"   LEFT JOIN activity_master_data_driver amdd ON amdd.id = ds.activity_id" +
				"   LEFT JOIN master_admin_lovs mal ON mal.id = amdd.workstream" +
				"   WHERE amdd.workstream = "+work.getId()+"  AND ds.engagement_id = "+engagement_id+"" +
				"   GROUP BY amdd.activity_group," +
				"            amdd.prototype," +
				"            amdd.workstream" +
				"   ) cost ON cost.prototype = malOuter.id" +
				" WHERE TYPE = \"prototype\" ORDER BY prototype";
		List detailedEffortList = jdbcTemplate.queryForList(sql);
		mapped.put(work.getCode()+"", detailedEffortList);
		}
		return mapped;
	}
	
	
	
//	end class
}	