package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Spring;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.ModuleTransactionActivity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ModuleTransactionDataVO;

@Repository("moduleTransactionDataDao")
public class ModuleTransactionDataDaoImpl extends
		AbstractDao<Integer, ModuleTransactionData> implements
		ModuleTransactionDataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<ModuleTransactionData> findByEngagementId(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("engagement_id", id));
		crit.add(Restrictions.eq("is_in_scope_flag", "Yes"));
		crit.addOrder(Order.asc("module_name"));
		return (List<ModuleTransactionData>) crit.list();
	}
	
	private void chekIfCloudremoved(String cloud_offering,String engagementId){
		String sql2moduleActivityDelete = "DELETE FROM module_transaction_activity where module_transaction_id in ( "+
		"select id FROM module_transaction_data where engagement_id = "+engagementId+" and cloud_offering NOT IN "+
		" ("+cloud_offering+"))";
		jdbcTemplate.execute(sql2moduleActivityDelete);
		String sql2moduleDelete = "DELETE FROM module_transaction_data where engagement_id = "+engagementId+" and cloud_offering NOT IN "+
				" ("+cloud_offering+")";
		jdbcTemplate.execute(sql2moduleDelete);
	}
	
	private void chekIfCloudadded(String cloud_offering,String engagementId){
		String initial_version = "1";
		String sqlFromAdminModules = "INSERT INTO module_transaction_data"
				+ " (engagement_id,business_process_id,module_name,module_abbreviation,module_duration_factor,"
				+ " moduleweightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,"
				+ "number_of_gaps,update_date,version,cloud_offering,job_aid,workshop)  "
				+ "SELECT "
				+ engagementId
				+ ", business_process_id, module_name,module_abbreviation,module_duration_factor,"
				+ "module_weightage,ddc_supported_flag,is_in_scope_flag,"
				+ "complexity_factor,number_of_requirements,number_of_gaps,CURDATE(),"+initial_version+",cloud_offering,job_aid,workshop "
						+ "FROM module_metadata WHERE cloud_offering IN ("+cloud_offering+")";
		jdbcTemplate.execute(sqlFromAdminModules);
	}
	
	@Override
	public void checkForCloudOffering(Engagement engagement,
			String prevCloudOffering, String currentCloudOffering) {
		List prevCloudlist = new  ArrayList<String>();
		List currentCloudList = new  ArrayList<String>();
		//List list= new  ArrayList<String>();
		if(!prevCloudOffering.equals(currentCloudOffering)){
			
			if(prevCloudOffering.contains(",")){
				 prevCloudlist = Arrays.asList(prevCloudOffering.split(","));
			}else{
				prevCloudlist.add(prevCloudOffering);
			}
			if(currentCloudOffering.contains(",")){
				 currentCloudList = Arrays.asList(currentCloudOffering.split(","));
			}else{
				currentCloudList.add(currentCloudOffering);
			}
			Collection<String> deleted = (Collection<String>)CollectionUtils.subtract(prevCloudlist, currentCloudList);
			Collection<String> updated = (Collection<String>)CollectionUtils.intersection(prevCloudlist, currentCloudList);
			Collection<String> newResult = (Collection<String>)CollectionUtils.subtract(currentCloudList,prevCloudlist);
			System.out.println(newResult);
			
				chekIfCloudremoved(currentCloudOffering,engagement.getId()+"");
				if(newResult != null){
				chekIfCloudadded(org.apache.commons.lang.StringUtils.join(newResult, ","),engagement.getId().toString());
				}
			
		}
			
			
			
	}

	
	@Override
	public void copyMetaDataForEngagment(String engagementId) {
		String initial_version = "1";
		String sql = "INSERT INTO module_transaction_data"
				+ " (engagement_id,business_process_id,module_name,module_abbreviation,module_duration_factor,"
				+ " moduleweightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,"
				+ "number_of_gaps,update_date,version,cloud_offering,job_aid,workshop)  "
				+ "SELECT "
				+ engagementId
				+ ", business_process_id, module_name,module_abbreviation,module_duration_factor,module_weightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps,CURDATE(),"+initial_version+",cloud_offering,job_aid,workshop FROM module_metadata WHERE  FIND_IN_SET(cloud_offering,(SELECT offering FROM engagement where id = "
				+ engagementId + "))";

		String sql1 = "INSERT INTO pwc_responsibility_transaction_data ( work_stream_id,prototype_id,activity,engagement_id,is_pwc_responsibility_flag) "
				+ "(SELECT work_stream_id,prototype_id,activity,"
				+ engagementId
				+ ",is_pwc_responsibility_flag FROM pwc_responsibility_metadata ) ";
		String sql2 = "INSERT INTO conversion_transaction_data (conversion_data_type_id,business_process_id,module_id,engagement_id,conversion_name,client_complexity_id,is_user_create_flag,scope_override_flag,conversion_source,conversion_volume)"
				+ "SELECT t1.conversion_data_type,t2.business_process_id,t1.module,"
				+ engagementId
				+ ",t1.conversion_name,t1.client_complexity,'No',t1.scope_override_flag,t1.conversion_source,t1.conversion_volume FROM conversion_metadata t1,module_metadata t2 WHERE t1.module=t2.id ";
		jdbcTemplate.execute(sql);
//		jdbcTemplate.execute(sql1);
//		jdbcTemplate.execute(sql2);
	}

	@Override
	public List<ModuleTransactionData> findEngagementTransactionData(
			String engagementId) {
		String sql = "SELECT  mal2.code as cloudOffering,mal.code as businessProcessName,mtd.*,IFNULL(group_concat(mta.ddc),'') as activity_ddc,IFNULL(group_concat(mta.onsite),'') as activity_onsite,IFNULL(group_concat(act.activity),'') as activity_name "
				+ "FROM master_admin_lovs mal ,module_transaction_data mtd "
				+ "LEFT JOIN module_transaction_activity mta on mtd.id = mta.module_transaction_id  "
				+ "LEFT JOIN activity_master_data_driver act on act.id = mtd.activity_id  "
				+ "LEFT JOIN master_admin_lovs mal2 on mal2.id = mtd.cloud_offering  "
				+ "WHERE mtd.business_process_id = mal.id AND mtd.engagement_id = ?  "
				+ "group by mtd.id "
				+ "ORDER BY businessProcessName,mtd.module_name";

		List<ModuleTransactionData> vo = jdbcTemplate.query(sql,
				new Object[] { engagementId },
				new BeanPropertyRowMapper<ModuleTransactionData>(
						ModuleTransactionData.class));
		// System.out.println(vo.get(0).getActivity_ddc());
		return vo;
	}

	@Override
	public Double getSecondaryDriver(int module_id, String engagementId,
			String activity_id, String driver) {
		String getDriver = "SELECT bu_factor from `master_admin_lovs` WHERE id = "
				+ driver;
		String columnNames = jdbcTemplate.queryForObject(getDriver,
				String.class);
		Double SD = 1.00;
		for (String column : columnNames.split(",")) {
			String sdSql = "SELECT " + column
					+ " FROM module_transaction_data WHERE id= " + module_id
					+ "  and engagement_id = " + engagementId;
			try {
				Double sSD = jdbcTemplate.queryForObject(sdSql, Double.class);
				SD = SD * sSD;
			} catch (Exception e) {
				SD = SD * 1.00;
			}
		}
		return SD;
	}

	// get activities and module txn data
	@Override
	public List<ModuleTransactionActivity> findModuleTransactionActivityData(
			String engagementId) {
		String sql = "SELECT mal2.code as cloudOffering, mal.code as businessProcessName,mtd.*,group_concat(IFNULL(mta.ddc,\"--\")) as activity_ddc,group_concat(IFNULL(mta.onsite,\"--\")) as activity_onsite,group_concat(mta.explain_calc) as activity_name "
				+ "FROM master_admin_lovs mal ,module_transaction_data mtd "
				+ "LEFT JOIN module_transaction_activity mta on mtd.id = mta.module_transaction_id  "
				+ "LEFT JOIN master_admin_lovs mal2 on mal2.id = mtd.cloud_offering  "
				+ "LEFT JOIN activity_master_data_driver act on act.id = mta.activity_id  "
				+ "WHERE mtd.business_process_id = mal.id AND mtd.engagement_id = ?  "
				+ "group by mtd.id "
				+ "ORDER BY mtd.cloud_offering,businessProcessName,mtd.module_name";

		List<ModuleTransactionActivity> vo = jdbcTemplate.query(sql,
				new Object[] { engagementId },
				new BeanPropertyRowMapper<ModuleTransactionActivity>(
						ModuleTransactionActivity.class));
		// System.out.println(vo.get(0).getActivity_ddc());
		return vo;
	}
	
	@Override
	public List actValues(String engagementId) {
		String sql = "SELECT concat(mta.module_transaction_id,\"_\",mta.activity_id) as id" +
				" ,mta.explain_calc,mta.ddc,mta.onsite FROM module_transaction_activity mta" +
				" JOIN module_transaction_data mtd ON mtd.id = mta.module_transaction_id" +
				" WHERE mtd.engagement_id = "+engagementId;

		List vo = jdbcTemplate.queryForList(sql);
		// System.out.println(vo.get(0).getActivity_ddc());
		return vo;
	}
	
	@Override
	public ActivityManagementData findDependantActData(String activity_id) {
		String driver_type_depandant = "50";
		String sql = "SELECT amd2.* FROM activity_master_data_driver amd1 "
				+ "JOIN activity_master_data_driver amd2 ON amd2.id = amd1.dependant_activity "
				+ "where amd1.driver_type = '" + driver_type_depandant
				+ "' and amd1.id = " + activity_id;

		try {
			ActivityManagementData activities = jdbcTemplate.queryForObject(
					sql, new BeanPropertyRowMapper<ActivityManagementData>(
							ActivityManagementData.class));
			// System.out.print(sql);
			// ignore if activity depend on itself

			if (activity_id.equalsIgnoreCase(activities.getId().toString())) {
				return null;
			}

			return activities;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void saveModuleTransactionData(
			ModuleTransactionData moduleTransactionData) {

		update(moduleTransactionData);

	}

	@Override
	public List<ActivityManagementData> getAllActivities() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM activity_master_data_driver where pwc_responsibility = 'yes' and activity_effort_driver='27' ORDER BY prototype";

		List<ActivityManagementData> activities = jdbcTemplate.query(sql,
				new Object[] {},
				new BeanPropertyRowMapper<ActivityManagementData>(
						ActivityManagementData.class));
		System.out.print(sql);
		return activities;
	}

	@Override
	public void setActValues(List<Map<String,Object>> list_op) {
		String sqlins = "INSERT INTO module_transaction_activity(ddc, onsite, module_transaction_id, activity_id,explain_calc) values";
		 String sqlappend ="";
		if(list_op.size()>0){
			
			for(Map<String,Object> map:list_op){
				sqlappend = sqlappend + ", ( '"+map.get("ddc")+"' , '"+map.get("onsite")+"' , '"+map.get("module_transaction_id")+"', '"+map.get("activity_id")+"' ,'"+map.get("explain")+"' )"  ;
			}
			sqlappend = sqlappend.replaceFirst(",", "");
		}
		sqlins = sqlins + sqlappend + " ON DUPLICATE KEY UPDATE onsite=VALUES(onsite),ddc=VALUES(ddc),explain_calc=VALUES(explain_calc)";
		
		
		jdbcTemplate.execute(sqlins);
	}

}
