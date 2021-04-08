package com.pwc.denali2.estimator.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.EnagagementProgress;
import com.pwc.denali2.estimator.model.StaffDriverBlendedRateTransactionData;
import com.pwc.denali2.estimator.model.StaffDriverVo;
import com.pwc.denali2.estimator.model.StaffdriverData;
import com.pwc.denali2.estimator.model.StaffdriverTeam;

@Repository
public class StaffDriverDaoImpl extends AbstractDao<Integer, StaffdriverData>
		implements StaffDriverDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, List<List>> getAllEfforts(String engagement_id) {
		// TODO Auto-generated method stub
		String ddcSql = "SELECT id, CONCAT_WS(\"_\",type,workstream_id) as id_couple,work_value,workstream_id "
				      + "FROM staff_driver_transaction where work_type=\"ddc\" and engagement_id='"+engagement_id+"'";

		List ddcData = jdbcTemplate.queryForList(ddcSql);
		
		if(ddcData.isEmpty()){
			 ddcSql = "SELECT id, CONCAT_WS(\"_\",type,workstream_id) as id_couple,work_value,workstream_id "
				      + "FROM staff_driver_master where work_type=\"ddc\"";

			 ddcData = jdbcTemplate.queryForList(ddcSql);
		}
		
		
		String workSql = "SELECT id, CONCAT_WS(\"_\",type,workstream_id) as id_couple,work_value,workstream_id "
			      + "FROM staff_driver_transaction where work_type=\"workstream\" and engagement_id='"+engagement_id+"'";

		List workData = jdbcTemplate.queryForList(workSql);
		
		if(workData.isEmpty()){
			 workSql = "SELECT id, CONCAT_WS(\"_\",type,workstream_id) as id_couple,work_value,workstream_id "
				      + "FROM staff_driver_master where work_type=\"workstream\"";

			 workData = jdbcTemplate.queryForList(workSql);
		}
		Map<String, List<List>> map = new HashMap<String, List<List>>();
		map.put("ddc", ddcData);
		map.put("workstream", workData);

		return map;

	}

	@Override
	public Map<String, List<List>> getAllTeam(String engagement_id) {
		// TODO Auto-generated method stub
		String ddcSql = "SELECT id, CONCAT_WS(\"_\",team_type,workstream_id,level_id) as id_couple,team_value,level_id,workstream_id,created_by,updated_by "
				+ "FROM staff_driver_team_transaction where work_type=\"ddc\" and engagement_id='"+engagement_id+"'";

		List ddcData = jdbcTemplate.queryForList(ddcSql);
		//If no data for engagement get ffrom admin
		if(ddcData.isEmpty()){
		 ddcSql = "SELECT id, CONCAT_WS(\"_\",team_type,workstream_id,level_id) as id_couple,team_value,level_id,workstream_id,created_by,updated_by "
				+ "FROM staff_driver_team_master where work_type=\"ddc\"";

		 ddcData = jdbcTemplate.queryForList(ddcSql);
		}
		String workSql = "SELECT id, CONCAT_WS(\"_\",team_type,workstream_id,level_id)  as id_couple,team_value,level_id,workstream_id,created_by,updated_by "
				+ "FROM staff_driver_team_transaction where work_type=\"workstream\" and engagement_id='"+engagement_id+"'";

		List workData = jdbcTemplate.queryForList(workSql);
		
		//If no data for engagement get ffrom admin
		if(workData.isEmpty()){
			
		 workSql = "SELECT id, CONCAT_WS(\"_\",team_type,workstream_id,level_id)  as id_couple,team_value,level_id,workstream_id,created_by,updated_by "
				+ "FROM staff_driver_team_master where work_type=\"workstream\"" ;

		 workData = jdbcTemplate.queryForList(workSql);
		}
		
		Map<String, List<List>> map = new HashMap<String, List<List>>();
		map.put("ddc", ddcData);
		map.put("workstream", workData);

		return map;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<List>> getAllTeamRates(String engagement_id) {
		String cspOnsiteQry = "SELECT CONCAT_WS(\"_\",'onsite',rm.workstream_id,rm.level_id) as id_couple,rm.workstream_id,rm.level_id,sdm.created_by,sdm.updated_by,rm.rate"
				+ " FROM project_rates rm "
				+ "LEFT JOIN  staff_driver_team_master sdm ON sdm.level_id = rm.level_id and sdm.workstream_id = rm.level_id "
				+ "WHERE rm.engagement_id = "+engagement_id+" AND rm.rate_type = \"csp\" and rm.worktype = \"onsite\" ORDER BY rm.version_id DESC";
		String cspOffsiteQry = "SELECT CONCAT_WS(\"_\",'offsite',rm.workstream_id,rm.level_id) as id_couple,rm.workstream_id,rm.level_id,sdm.created_by,sdm.updated_by,rm.rate"
				+ " FROM project_rates rm "
				+ "LEFT JOIN  staff_driver_team_master sdm ON sdm.level_id = rm.level_id and sdm.workstream_id = rm.level_id "
				+ "WHERE rm.engagement_id = "+engagement_id+" AND rm.rate_type = \"csp\" and rm.worktype = \"offsite\" ORDER BY rm.version_id DESC";
		String rsrOnsiteQry = "SELECT CONCAT_WS(\"_\",'onsite',rm.workstream_id,rm.level_id) as id_couple,rm.workstream_id,rm.level_id,sdm.created_by,sdm.updated_by,rm.rate"
				+ " FROM project_rates rm "
				+ "LEFT JOIN  staff_driver_team_master sdm ON sdm.level_id = rm.level_id and sdm.workstream_id = rm.level_id "
				+ "WHERE rm.engagement_id = "+engagement_id+" AND rm.rate_type = \"rsr\" and rm.worktype = \"onsite\" ORDER BY rm.version_id DESC";

		String rsrOffsiteQry = "SELECT CONCAT_WS(\"_\",'offsite',rm.workstream_id,rm.level_id) as id_couple,rm.workstream_id,rm.level_id,sdm.created_by,sdm.updated_by,rm.rate"
				+ " FROM project_rates rm "
				+ "LEFT JOIN  staff_driver_team_master sdm ON sdm.level_id = rm.level_id and sdm.workstream_id = rm.level_id "
				+ "WHERE rm.engagement_id = "+engagement_id+" AND rm.rate_type = \"rsr\" and rm.worktype = \"offsite\" ORDER BY rm.version_id DESC";

		List cspOnsiteData = jdbcTemplate.queryForList(cspOnsiteQry);
		List cspOffsiteData = jdbcTemplate.queryForList(cspOffsiteQry);
		List rsrOnsiteData = jdbcTemplate.queryForList(rsrOnsiteQry);
		List rsrOffsiteData = jdbcTemplate.queryForList(rsrOffsiteQry);

		Map<String, List<List>> map = new HashMap<String, List<List>>();
		map.put("csp_onsite", cspOnsiteData);
		map.put("csp_offsite", cspOffsiteData);
		map.put("rsr_onsite", rsrOnsiteData);
		map.put("rsr_offsite", rsrOffsiteData);

		return map;

	}

	@Override
	public Boolean saveAllTeam(Map<String, Number> data,String engagement_id) {

		String teamInsertQry = "INSERT INTO staff_driver_team_transaction "
				+ "(engagement_id,work_type,level_id,workstream_id,team_value,team_type) VALUES ";
		for (Map.Entry<String, Number> team : data.entrySet()) {
			String[] splitKey = team.getKey().split("_");
			String work_type = splitKey[0];
			String team_type = splitKey[1];
			String workstream_id = splitKey[2];
			String level_id = splitKey[3];
			Number team_value = team.getValue();
			teamInsertQry = teamInsertQry + "("+engagement_id+", '" + work_type + "'," + level_id
					+ "," + workstream_id + "," + team_value + ",'" + team_type
					+ "') ,";
		}
		teamInsertQry = teamInsertQry.substring(0, teamInsertQry.length() - 1)
				+ "ON DUPLICATE KEY UPDATE team_value=VALUES(team_value)";
		System.out.println(teamInsertQry);
		try {
			jdbcTemplate.execute(teamInsertQry);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Boolean saveAllEfforts(Map<String, Number> data,String engagement_id) {
		
		String effortInsertQry = "INSERT INTO staff_driver_transaction "
				+ "(engagement_id,type,work_type,workstream_id,work_value) VALUES ";
		for (Map.Entry<String, Number> effort : data.entrySet()) {
			String[] splitKey = effort.getKey().split("_");
			String work_type = splitKey[0];
			String type = splitKey[1];
			String workstream_id = splitKey[2];
			Number work_value = effort.getValue();
			effortInsertQry = effortInsertQry + "(" +engagement_id+",  '" + type + "','" + work_type + "'"
					+ "," + workstream_id + "," + work_value +") ,";
		}
		effortInsertQry = effortInsertQry.substring(0, effortInsertQry.length() - 1)
				+ "ON DUPLICATE KEY UPDATE work_value=VALUES(work_value)";
		
		try {
			jdbcTemplate.execute(effortInsertQry);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean saveBlendedrates(int workstream_id,String rate_type,String work_type,double rate,String engagement_id) {
		String effortInsertQry = "INSERT INTO `staff_driver_blended_rate_transaction` "
				+ "(engagement_id,workstream_id,work_type,rate_type,rate) VALUES ("+engagement_id+","+workstream_id+",'"+work_type+"','"+rate_type+"',"+rate+")"
						+ " ON DUPLICATE KEY UPDATE rate=VALUES(rate)";
		try {
			jdbcTemplate.execute(effortInsertQry);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean copyFromAdmin(String engagement_id) {
		String blendedRates =  "INSERT INTO `staff_driver_blended_rate_transaction`  " + 
				 "   (`engagement_id`,`workstream_id`,`rate_type`,`work_type`,`rate`,  "  + 
				 "   `created_date`,`updated_date`)  "  + 
				 "  SELECT "+engagement_id+",sdbr.workstream_id,sdbr.rate_type,sdbr.work_type,sdbr.rate,sdbr.created_date,sdbr.updated_date FROM staff_driver_blended_rate sdbr  " ; 
		String teamRates = "INSERT INTO `cloud_after`.`staff_driver_team_transaction`" +
				"(`engagement_id`,`work_type`,`level_id`,`workstream_id`," +
				"`team_value`,`team_type`,`created_date`,`updated_date`)" +
				"SELECT  "+engagement_id+",sdtm.work_type,sdtm.level_id,sdtm.workstream_id," +
				"sdtm.team_value,sdtm.team_type,now(),now() FROM `staff_driver_team_master` sdtm";
		
		String effortPerc = "INSERT INTO `cloud_after`.`staff_driver_transaction`" +
				"(`engagement_id`,`type`,`work_type`,`workstream_id`,`work_value`," +
				"`created_at`,`updated_at`)" +
				"SELECT "+engagement_id+",sdtm.type,sdtm.work_type,sdtm.workstream_id," +
				"sdtm.work_value,now(),now() FROM `staff_driver_master` sdtm";
		try {
			jdbcTemplate.execute(blendedRates);
			jdbcTemplate.execute(teamRates);
			jdbcTemplate.execute(effortPerc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean saveAllBlendedrates(Map<String, Number> data,String engagement_id) {
		
		String effortInsertQry = "INSERT INTO `staff_driver_blended_rate_transaction` "
				+ "(engagement_id,workstream_id,work_type,rate_type,rate) VALUES ";
		//Data from js `1_csp_ddc`
		for (Map.Entry<String, Number> blend : data.entrySet()) {
			String[] splitKey = blend.getKey().split("_");
			String workstream_id = splitKey[0];
			String rate_type = splitKey[1];
			String work_type = splitKey[2];
			Number rate = blend.getValue();
			effortInsertQry = effortInsertQry + "( " +engagement_id+", '" + workstream_id + "' ,'" + work_type + "', '"+rate_type+"'"
					+ "," + rate + " ) ,";
		}
		effortInsertQry = effortInsertQry.substring(0, effortInsertQry.length() - 1)
				+ "ON DUPLICATE KEY UPDATE rate=VALUES(rate)";
		
		try {
			jdbcTemplate.execute(effortInsertQry);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<StaffDriverVo> getAllData(String engagement_id,String workstream,String workT,String rateT) {
		
		 String sb = "SELECT Distinct pr.rate,sdtt.level_id,sdtt.work_type,sdtt.team_value,sdtt.team_type,sdtt.workstream_id,(SELECT SUM(team_value) FROM `staff_driver_team_transaction`" +
				" WHERE workstream_id = sdtt.workstream_id AND work_type = \""+workT+"\" AND engagement_id = "+engagement_id+" AND team_type = sdtt.team_type) as sum, " +
				" (SELECT sdt.work_value  FROM staff_driver_transaction sdt WHERE " +
				" engagement_id = "+engagement_id+" and sdt.workstream_id = sdtt.workstream_id and sdt.work_type= sdtt.work_type and sdt.type=sdtt.team_type) as work_value, "
			  + " (SELECT sdt.work_value  FROM staff_driver_transaction sdt WHERE engagement_id = "+engagement_id+" and sdt.workstream_id = sdtt.workstream_id and sdt.work_type= sdtt.work_type and sdt.type=\"offshore\") as offshore, " +
				" (SELECT rate FROM project_rates WHERE level_id = 55 AND workstream_id = "+workstream+" AND engagement_id = "+engagement_id+" AND rate_type = \""+rateT+"\" ORDER by version_id DESC LIMIT 0,1) as offshore_rate "
				+ "FROM `staff_driver_team_transaction` sdtt" +
				" LEFT JOIN project_rates pr ON pr.workstream_id = sdtt.workstream_id AND pr.level_id = sdtt.level_id " +
				" WHERE sdtt.engagement_id = "+engagement_id+" AND pr.rate_type = \""+rateT+"\" AND pr.engagement_id = "+engagement_id+" AND  sdtt.work_type = \""+workT+"\" AND sdtt.workstream_id = "+workstream+"" +
				" order by sdtt.team_type DESC,sdtt.workstream_id,pr.version_id DESC";
		 
		 List<StaffDriverVo> StaffDriverVo = jdbcTemplate.query(sb,
					new BeanPropertyRowMapper<StaffDriverVo>(
							StaffDriverVo.class));
		 
		return StaffDriverVo;
	}

}