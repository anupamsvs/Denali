package com.pwc.denali2.estimator.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.DBCellRecord;
import org.hibernate.mapping.Map;
import org.json.JSONObject;

import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.ConversionMetaData;
import com.pwc.denali2.estimator.model.ConversionTransactionData;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementShare;
import com.pwc.denali2.estimator.model.Engagement_share_permission;
import com.pwc.denali2.estimator.model.LockUser;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.RiceEffortMaster;
import com.pwc.denali2.estimator.model.RiceTransactionData;

@Repository
public class ProjectManagementDaoImpl extends AbstractDao<Integer, Engagement> implements ProjectManagementDao{

	@Autowired
	AdditionalDaoImpl additionalDao;
	
	@Autowired
	ConversionsDaoImpl conversionsDaoImpl;
	
	@Autowired
	ProjectUnitsDaoImpl projectUnitsDaoImpl;
	
	@Override
	public void saveProject(Engagement engagement) {
		
		persist(engagement);
	}
	

	
	@Override
	public List<String> getEngagementStatusById(int engagementId) {
		
		String sql = "select engagement_status from engagement where id = ?";
		
		List<String> engagement_status = jdbcTemplate.queryForList(sql,new Object[]{engagementId},String.class);
		
		return engagement_status;
	}
	
	@Override
	public Engagement getEngagementById(int engagementId) {
		
		String sql = "select * from engagement where id = ?";
		//List<String> engagement = jdbcTemplate.queryForList(sql,new Object[]{engagementId},String.class);
		
		Engagement Engagement = jdbcTemplate.queryForObject(sql,
				new Object[]{engagementId},new BeanPropertyRowMapper<Engagement>(Engagement.class));
		return Engagement;
	}
	
	@Override
	public void saveEnterPriseApplication(int engagementId,String enterPriseApplicationStringList) {
		String[] enterPriseApplicationList= enterPriseApplicationStringList.split(",");
		
		for(int i = 0; i < enterPriseApplicationList.length; i++){
			String application = enterPriseApplicationList[i];
			String sql1 = "select id from enterprise_application where description = '"+application+"'";
			List<String> id = jdbcTemplate.queryForList(sql1,new Object[]{},String.class);
			String applicationId = id.get(0);
			String sql2 = "insert into engagement_enterprise_application(engagement_id,enterprise_application_id) values ("+ engagementId +",'"+ applicationId +"')";
			jdbcTemplate.update(sql2);
		}
	}
	
	@Override
	public void updateEnterPriseApplication(int engagementId,String enterPriseApplicationStringList) {
		String[] enterPriseApplicationList= enterPriseApplicationStringList.split(",");
		
		String sql = "delete from engagement_enterprise_application where engagement_id = "+engagementId+"";
		jdbcTemplate.update(sql);
		if(enterPriseApplicationList.length == 1 && enterPriseApplicationList[0].equals("")){
		}
		else{
			for(int i = 0; i < enterPriseApplicationList.length; i++){
				String application = enterPriseApplicationList[i];
				String sql1 = "select id from enterprise_application where description = '"+application+"'";
				List<String> id = jdbcTemplate.queryForList(sql1,new Object[]{},String.class);
				String applicationId = id.get(0);
				String sql2 = "insert into engagement_enterprise_application(engagement_id,enterprise_application_id) values ("+ engagementId +",'"+ applicationId +"')";
				jdbcTemplate.update(sql2);
			}
		}
	}


	@Override
	public List<Engagement_share_permission> listAvailableProject(String currentUserGuid,Engagement engagement) {
		//String sql = "SELECT * FROM engagement WHERE (guid = ? OR id IN(SELECT engagement_id FROM engagement_share WHERE guid = ?)) ";
		String sql ="SELECT engshr.release,engshr.edit,engshr.read,eng.* FROM engagement eng"+
				" LEFT JOIN engagement_share engshr on eng.id = engshr.engagement_id and engshr.guid = ?"+
				" WHERE (eng.guid = ? OR eng.id IN(SELECT engagement_id FROM engagement_share WHERE guid = ?))";
						System.out.println(sql);
		if(null != engagement){
			if(!StringUtils.isEmpty(engagement.getClient_name())){
				sql += " and client_name like'%" + engagement.getClient_name() + "%' ";
			}
			if(!StringUtils.isEmpty(engagement.getEngagement_name())){
				sql += " and engagement_name like '%" + engagement.getEngagement_name() + "%' ";
			}
			if(!StringUtils.isEmpty(engagement.getEngagement_status())){
				sql += " and engagement_status like '%" + engagement.getEngagement_status() + "%' ";
			}
		}
		System.out.println(sql);
		List<Engagement_share_permission> projectList = jdbcTemplate.query(sql, 
				new Object[]{currentUserGuid,currentUserGuid,currentUserGuid},new BeanPropertyRowMapper<Engagement_share_permission>(Engagement_share_permission.class));
//		System.out.println(projectList.get(0).getProjectNo());
		
//		String sqlSharedRes="SELECT * FROM engagement_share where guid = '"+currentUserGuid+"' and engagement_id = "+engagement.getId();
//		
//		List<EngagementShare> eng = jdbcTemplate.query(sqlSharedRes, new BeanPropertyRowMapper<EngagementShare>(EngagementShare.class));
//		System.out.println(eng.get(0).getEdit());
//		System.out.println(eng.get(0).getRelease());
//		System.out.println(eng.get(0).getRead());
		
		
		
		return projectList;
	}

	@Override
	public void updateProject(Engagement engagement) {
		update(engagement);
		
	}

	@Override
	public List<String> getEngagementStatusList() {
		String sql = "select description from engagement_status";
		
		List<String> engagementStatusList = jdbcTemplate.queryForList(sql,new Object[]{},String.class);
		
		return engagementStatusList;
	}
	
	@Override
	public List<String> getEnterpriseApplicationListByEngagementId(int engagementId) {
		String sql = "select description from enterprise_application,engagement_enterprise_application where engagement_enterprise_application.engagement_id = ? and engagement_enterprise_application.enterprise_application_id = enterprise_application.id";
		
		List<String> enterpriseApplicationList = jdbcTemplate.queryForList(sql,new Object[]{engagementId},String.class);
		
		return enterpriseApplicationList;
	}
	
	@Override
	public List<String> getEnterpriseApplicationList() {
		String sql = "select description from enterprise_application";
		
		List<String> enterpriseApplicationList = jdbcTemplate.queryForList(sql,new Object[]{},String.class);
		
		return enterpriseApplicationList;
	}

	@Override
	public Integer checkEngagementNo(int engagementId,String project_no) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from engagement where id = ? and parent = ? and project_no = ?";
		Integer count = 0;
		//List<String> engagement = jdbcTemplate.queryForList(sql,new Object[]{engagementId},String.class);
		try{
		 count = jdbcTemplate.queryForObject(sql,
				new Object[]{engagementId,engagementId,project_no}, Integer.class );
		}catch(Exception e){
			return 0;
		}
		return count;
	}
	
		@Override
	public List<java.util.Map<String, Object>> getOfferingList() {
		// TODO Auto-generated method stub
		String sql = "SELECT distinct id,code FROM master_admin_lovs where lower(type) ='cloud offering'";
		
		List<java.util.Map<String, Object>> offeringList = jdbcTemplate.queryForList(sql);
		return offeringList;
	}

		@Override
		public List<String> getClientList() {
			String sql = "SELECT distinct client_name FROM engagement";
			List<String> clientList =null;
			clientList = jdbcTemplate.queryForList(sql,new Object[]{},String.class);
			 
			
			return clientList;
		}

		@Override
		public String copyProject(String copyList,int engagementId) {
			JSONObject obj = new JSONObject(copyList);
			System.out.println(obj.get("module").toString());
			
			String status =obj.get("module").toString();
			String statusRice =obj.get("addServices").toString();
			String statusAddSer =obj.get("rice").toString();
			String statusRates =obj.get("rates").toString();
			String copyfromengId =obj.get("selected").toString().split("\\|")[0];
			System.out.println(copyfromengId);
			
			
			copyComplexityMultiplier(engagementId, status, copyfromengId);
			copyModules(engagementId,status,copyfromengId);
			copyRice(engagementId, statusRice,copyfromengId);
			copyAddServices(engagementId,statusAddSer,copyfromengId);
			copyRates(engagementId, statusRates, copyfromengId);
			
			copyAllProjectDta(engagementId,copyfromengId);
			projectUnitsDaoImpl.listUnit(engagementId+"");
			
			
			return null;
		}
		@Override
		public String releaseProject(int parent,int engagementId) {


		copyComplexityMultiplier(engagementId, "true", parent+"");
		copyModules(engagementId, "true", parent+"");
		copyRice(engagementId, "true", parent+"");
		copyAddServices(engagementId, "true", parent+"");
		copyRates(engagementId, "true", parent+"");

		copyAllProjectDta(engagementId, parent+"");

		return null;
		}
		
		@Override
		public String copyAllProjectDta(int engagementId,String copyFromengId) {
			
			String sql ="";
			// copy pwc_responsibillity
			sql ="INSERT INTO `pwc_responsibility` (`Activity_id`, `is_pwc_responsibility_flag`, `engagement_id`, `update_date`, `create_date`, `bu_dependency`)"
				 +" SELECT `Activity_id`, `is_pwc_responsibility_flag`,'"+engagementId+"', now(),now(), `bu_dependency` FROM pwc_responsibility where engagement_id ='"+copyFromengId+"'";
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			
			//copy ProjectDuration
			sql ="INSERT INTO `project_duration_transaction_data` (`engagement_id`, `mod_cplex_multiplier`, `module_weeks`, `overide_weeks`, `rice_extra_weeks`, `conversion_extra_weeks`, `proj_impl_duration`, `total_proj_duration`, `planning_weeks`, `resources_for_planned_weeks`, `resources_for_plus_weeks`, `process_area_multiplier`, `module_multiplier`)"
			+" SELECT '"+engagementId+"', `mod_cplex_multiplier`, `module_weeks`, `overide_weeks`, `rice_extra_weeks`, `conversion_extra_weeks`, `proj_impl_duration`, `total_proj_duration`, `planning_weeks`, `resources_for_planned_weeks`, `resources_for_plus_weeks`, `process_area_multiplier`, `module_multiplier` FROM project_duration_transaction_data where engagement_id ='"+copyFromengId+"'";
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			
			sql ="INSERT INTO `project_duration_transaction_join_table` (`prototyp_id`, `engagement_id`, `value`)"
			+" SELECT `prototyp_id`, '"+engagementId+"', `value` FROM project_duration_transaction_join_table where engagement_id ='"+copyFromengId+"'";
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			
			
			// copy Detailed summary
//			sql ="INSERT INTO `detailed_summary` (`engagement_id`, `activity_id`, `effort_onsite`, `effort_ddc`, `effort_onsite_override`, `effort_ddc_override`, `csp_onsite`, `csp_ddc`, `rsr_onsite`, `rsr_ddc`, `margin_onsite`, `margin_ddc`, `explanation`, `created_at`, `updated_at`)"
//					+" SELECT '"+engagementId+"', `activity_id`, `effort_onsite`, `effort_ddc`, `effort_onsite_override`, `effort_ddc_override`, `csp_onsite`, `csp_ddc`, `rsr_onsite`, `rsr_ddc`, `margin_onsite`, `margin_ddc`, `explanation`, now(), now() FROM detailed_summary where engagement_id ='"+copyFromengId+"'";
//			System.out.println(sql);
//			jdbcTemplate.execute(sql);
			
			//staffing drivers
			sql ="INSERT INTO `staff_driver_blended_rate_transaction` (`workstream_id`, `engagement_id`, `rate_type`, `work_type`, `rate`)"
					+" SELECT `workstream_id`,'"+engagementId+"', `rate_type`, `work_type`, `rate` FROM staff_driver_blended_rate_transaction where engagement_id ='"+copyFromengId+"'";
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			
			sql ="INSERT INTO `staff_driver_team_transaction` (`work_type`, `level_id`, `workstream_id`, `engagement_id`, `team_value`, `team_type`)"
					+" SELECT `work_type`, `level_id`, `workstream_id`,'"+engagementId+"', `team_value`, `team_type` FROM staff_driver_team_transaction where engagement_id='"+copyFromengId+"'";
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			
			sql ="INSERT INTO `staff_driver_transaction` (`type`, `work_type`, `workstream_id`, `engagement_id`, `work_value`) "
							+" SELECT `type`, `work_type`, `workstream_id`,'"+engagementId+"', `work_value` FROM staff_driver_transaction where engagement_id='"+copyFromengId+"'";
			System.out.println(sql);
			jdbcTemplate.execute(sql);
			
			return null;
		}

		@Override
		public String copyConversion(int engagementId, String status,String copyfromengId) {
			// TODO Auto-generated method stub
			String sql ="";
			if(status.equals("true")){
				
				sql ="insert into conversion_transaction_data (conversion_data_type_id,module_id,client_complexity_id,engagement_id,conversion_name,scope_override_flag,final_scope_flag,conversion_source,conversion_volume,is_user_create_flag,defaultedScope)"
					  +" SELECT conversion_data_type_id,module_id,client_complexity_id,'"+engagementId+"',conversion_name,scope_override_flag,final_scope_flag,conversion_source,conversion_volume,is_user_create_flag,defaultedScope FROM conversion_transaction_data where engagement_id ='"+copyfromengId+"'";
				jdbcTemplate.execute(sql);
				System.out.println(sql);
//				
//				 sql ="INSERT INTO `conversion_transaction_join_table` (`worktype`, `effort`, `conversion_data_id`, `engagement_id`)"
//							+"SELECT worktype,effort,conversion_data_id,'"+engagementId+"' FROM conversion_transaction_join_table where engagement_id ='"+copyfromengId+"'";
//				 System.out.println(sql);
//				jdbcTemplate.execute(sql);
				
			
			}else if(status.equals("false")){
				List<String> isInScopelist =null;
				 sql ="SELECT distinct module_name FROM module_transaction_data where is_in_scope_flag ='Yes' and engagement_id ='"+copyfromengId+"'";
				
				try {
					 isInScopelist  = jdbcTemplate.queryForList(sql, String.class);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String InScopeModule="" , sqlAppend="";
				if(isInScopelist.size()>0){
					
					if(isInScopelist.size()==1){
						InScopeModule ="'"+isInScopelist.get(0)+"'";
					}else{
						InScopeModule ="'"+org.apache.commons.lang.StringUtils.join(isInScopelist, "','")+"'";
					}
					
				
				if(!StringUtils.isEmpty(InScopeModule)){
					  sqlAppend = "and module_metadata.module_name in("+InScopeModule+")";
				}
				 sql ="SELECT conversion_metadata.* "
						+ " FROM conversion_metadata,module_metadata "
						+ " where conversion_metadata.module = module_metadata.id and conversion_metadata.is_active = 1  "+sqlAppend ;
				 System.out.println(sql);
				jdbcTemplate.execute(sql);
				List<ConversionMetaData> conversionAdminList = jdbcTemplate.query(sql, 
						new BeanPropertyRowMapper<ConversionMetaData>(ConversionMetaData.class));
				
				if(conversionAdminList.size()>0){
				System.out.println(sql);
				
					for(ConversionMetaData data:conversionAdminList){
						int countRec =0;
						sql ="select count(*) from conversion_transaction_data where engagement_id ="+engagementId+"  and  conversion_name ='"+data.getConversion_name()+"' and module_id = '"+data.getModule()+"'";
						countRec= jdbcTemplate.queryForObject(sql, Integer.class);
						
						if(countRec ==0){
							
							sql ="INSERT INTO conversion_transaction_data ( conversion_data_type_id,module_id,client_complexity_id,engagement_id,conversion_name,defaultedScope,final_scope_flag,conversion_source,conversion_volume,is_user_create_flag) "+
									  "values('"+data.getConversion_data_type()+"','"+data.getModule()+"','"+data.getClient_complexity()+"','"+engagementId+"','"+data.getConversion_name()+"','"+data.getIs_in_scope_flag()+"','"+data.getIs_in_scope_flag()+"','"+data.getConversion_source()+"',"+data.getConversion_volume()+",null)";
						jdbcTemplate.update(sql);	
						}else{
							
						}
					}
				}
				}
							}
			
			String sqlForWorktype ="SELECT * FROM master_admin_lovs where lower(type) ='work type'";
			
			List<MasterAdminLovs> Worktypedata = jdbcTemplate.query(sqlForWorktype,
					new BeanPropertyRowMapper<MasterAdminLovs>(
							MasterAdminLovs.class));

			
			sql ="select *  FROM conversion_transaction_data where  engagement_id = "+engagementId;
			List<ConversionTransactionData> conversionList = jdbcTemplate.query(sql, 
					new BeanPropertyRowMapper<ConversionTransactionData>(ConversionTransactionData.class));
			

			for(ConversionTransactionData singleconversion:conversionList){
				for(MasterAdminLovs data:Worktypedata){

					Double result =0.0;
					if (singleconversion.getFinal_scope_flag().equalsIgnoreCase("no")) {
						result =0.0;
					}
					else{
					
					sql ="SELECT  distinct CONCAT_WS('_',conversion_effort_master.effort,complexity_multiplier.value) FROM conversion_effort_master,complexity_multiplier where conversion_effort_master.workstream_id =complexity_multiplier.workstream and complexity_multiplier.effort_type ='conversion'  and conversion_effort_master.worktype_id ='"+data.getId()+"' and conversion_effort_master.complexity_id='"+singleconversion.getClient_complexity_id()+"' and complexity_multiplier.engagement_id = "+engagementId;

					String effort =jdbcTemplate.queryForObject(sql, String.class);
					System.out.println(effort);

					 result =(Double.parseDouble(effort.split("_")[0].toString()))*(Double.parseDouble(effort.split("_")[1].toString()));
					
				}
		
					sql ="insert into conversion_transaction_join_table(worktype,effort,conversion_data_id,engagement_id) values('"+data.getId()+"','"+result+"',"+singleconversion.getId()+",'"+engagementId+"')";
					jdbcTemplate.update(sql);
			}
		
			}

			return null;
		}

		@Override
		public String copyModules(int engagementId, String status,String copyfromengId) {
			// TODO Auto-generated method stub
			String sql ="";
           if(status.equals("true")){
        	   
        	   sql ="INSERT INTO module_transaction_data(engagement_id,business_process_id,module_name,module_abbreviation,module_duration_factor,moduleweightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps,update_date,version,cloud_offering,job_aid,workshop)"
        			  +" select '"+engagementId+"',business_process_id,module_name,module_abbreviation,module_duration_factor,moduleweightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps,update_date,version,cloud_offering,job_aid,workshop from module_transaction_data where engagement_id='"+copyfromengId+"'";
				
			}else if(status.equals("false")){
				 sql = "INSERT INTO module_transaction_data(engagement_id,business_process_id,module_name,module_abbreviation,module_duration_factor,moduleweightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps,update_date,version,cloud_offering,job_aid,workshop)  "
				+ "SELECT "
				+ engagementId
				+ ", business_process_id, module_name,module_abbreviation,module_duration_factor,module_weightage,ddc_supported_flag,is_in_scope_flag,complexity_factor,number_of_requirements,number_of_gaps,CURDATE(),1,cloud_offering,job_aid,workshop FROM module_metadata WHERE  FIND_IN_SET(cloud_offering,(SELECT offering FROM engagement where id = "
				+ engagementId + "))";
				
			}
           System.out.println(sql);
           jdbcTemplate.execute(sql);
           // copy Conversion
           copyConversion(engagementId,status,copyfromengId);
			return null;
		}

		@Override
		public String copyRice(int engagementId, String status,String copyfromengId) {
			// TODO Auto-generated method stub
			if(status.equals("true")){
				
				String sql ="INSERT INTO `rice_transaction_data` (`rice_type_id`, `module_id`, `client_complexity_id`, `engagement_id`, `rice_name`, `rice_source_system`, `rice_target_system`,`create_date`,`update_date`)"
					+" SELECT rice_type_id,module_id,client_complexity_id,'"+engagementId+"',rice_name,rice_source_system,rice_target_system,now(),now() FROM rice_transaction_data where engagement_id ='"+copyfromengId+"'";
				
				System.out.println(sql);
				jdbcTemplate.execute(sql);

				
				String sqlForWorktype ="SELECT * FROM master_admin_lovs where lower(type) ='work type'";
				List<MasterAdminLovs> Worktypedata = jdbcTemplate.query(sqlForWorktype,
						new BeanPropertyRowMapper<MasterAdminLovs>(
								MasterAdminLovs.class));
				
				sql ="SELECT * FROM rice_transaction_data where engagement_id ='"+engagementId+"'";
				List<RiceTransactionData> riceTransactionDataList = jdbcTemplate.query(sql,
						new BeanPropertyRowMapper<RiceTransactionData>(
								RiceTransactionData.class));
				
				for(RiceTransactionData riceTransactionData:riceTransactionDataList){
				for(MasterAdminLovs data:Worktypedata){
					
					sql ="SELECT  distinct CONCAT_WS('_',rice_effort_master.effort,complexity_multiplier.value) FROM rice_effort_master,complexity_multiplier where rice_effort_master.workstream =complexity_multiplier.workstream and complexity_multiplier.effort_type ='rice'  and rice_effort_master.worktype ='"+data.getId()+"' and rice_effort_master.complexity='"+riceTransactionData.getClient_complexity_id()+"' and rice_effort_master.rie_type ='"+riceTransactionData.getRice_type_id()+"' and complexity_multiplier.engagement_id = "+riceTransactionData.getEngagement_id();
					System.out.println(sql);
					String effort =jdbcTemplate.queryForObject(sql, String.class);
					
					
					Double result =(Double.parseDouble(effort.split("_")[0].toString()))*(Double.parseDouble(effort.split("_")[1].toString()));
					
					
			
					sql ="insert into rice_transaction_join_table(worktype,effort,rice_data_id,engagement_id) values('"+data.getId()+"','"+result+"',"+riceTransactionData.getId()+",'"+riceTransactionData.getEngagement_id()+"')";
							
					jdbcTemplate.update(sql);
					
				}}
			}
			return null;
		}

		@Override
		public String copyAddServices(int engagementId, String status,String copyfromengId) {
			
			if(status.equals("true")){
				additionalDao.copyAddServices( engagementId,status,copyfromengId);
			}
			return null;
		}
		
		@Override
		public String copyRates(int engagementId, String status,String copyfromengId){
			String sql ="";
			if(status.equals("true")){
				sql ="INSERT INTO `project_rates` (`rate_type`, `workstream`, `level`, `worktype`, `rate`, `create_dt`, `update_dt`, `engagement_id`, `version_id`, `workstream_id`, `level_id`)"
				+" SELECT `rate_type`, `workstream`, `level`, `worktype`, `rate`,now(),now(),'"+engagementId+"', `version_id`, `workstream_id`, `level_id` FROM project_rates where engagement_id ='"+copyfromengId+"'";
			 
				System.out.println(sql);
				jdbcTemplate.execute(sql);
			}else if(status.equals("false")){
				sql ="INSERT INTO `project_rates` (`rate_type`, `workstream`, `level`, `worktype`, `rate`, `create_dt`, `update_dt`, `engagement_id`, `version_id`, `workstream_id`, `level_id`)" 
						+" SELECT `rate_type`, `workstream`, `level`, `worktype`, `rate`,now(),now(),'"+engagementId+"',0, `workstream_id`, `level_id` FROM rates_master";
				System.out.println(sql);
				jdbcTemplate.execute(sql);
			}
			return null;
		}

		@Override
		public String copyComplexityMultiplier(int engagementId, String status,
				String copyfromengId) {
			if(status.equals("true")){
				String sql ="INSERT INTO `complexity_multiplier` (`workstream`, `value`, `effort_type`,`engagement_id`)"
							+" SELECT workstream,value,effort_type,'"+engagementId+"' FROM complexity_multiplier where engagement_id = "+copyfromengId;
				jdbcTemplate.execute(sql);
				
			}else if(status.equals("false")){
				conversionsDaoImpl.initComplexity(engagementId+"");
			}
			
			return null;
		}
}
