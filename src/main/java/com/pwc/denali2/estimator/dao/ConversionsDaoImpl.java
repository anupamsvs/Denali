package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pwc.denali2.estimator.model.ConversionMetaData;
import com.pwc.denali2.estimator.model.ConversionModuleData;
import com.pwc.denali2.estimator.model.ConversionTransactionData;
import com.pwc.denali2.estimator.model.ConversionVo;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.RiceTransactionData;

@Repository("ConversionsDao")
public class ConversionsDaoImpl extends
		AbstractDao<Integer, ConversionTransactionData> implements
		ConversionsDao {
	@Autowired
	ModuleMetaDataDaoImpl moduleMetadataDaoimpl;
	@Autowired
	ModuleTransactionDataDaoImpl moduleTransactionDataDaoImpl;
	@Autowired
	RiceTransactionDataDaoImpl riceTransactionDataDaoImpl;

	@Autowired
	MasterAdminUtilityDaoImpl masterAdminUtilityDaoImpl;

	public List<ConversionVo> findAllByEngagementId(Integer id, String type,
			String userflag) {
		List<String> isInScopelist = null;
		String InScopeModule = "";

		String sql = "SELECT distinct module_name FROM module_transaction_data where is_in_scope_flag ='Yes' and engagement_id ="
				+ id;
		try {
			isInScopelist = jdbcTemplate.queryForList(sql, String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (isInScopelist.size() > 0) {
			if (isInScopelist.size() == 1) {
				InScopeModule = "'" + isInScopelist.get(0) + "'";
			} else {
				InScopeModule = "'"
						+ org.apache.commons.lang.StringUtils.join(
								isInScopelist, "','") + "'";
			}
			sql = "SELECT count(*) FROM conversion_transaction_data where engagement_id = "
					+ id + " and lower(is_user_create_flag) is Null";

			int countTransactionTableData = jdbcTemplate.queryForObject(sql,
					Integer.class);
			if (countTransactionTableData == 0 && isInScopelist.size() > 0) {
				initConversionTransaction(id, InScopeModule);
			}

		}
		

		

		// sql="SELECT conversion_transaction_data.id,conversion_transaction_data.conversion_data_type_id,conversion_transaction_data.module_id,conversion_transaction_data.client_complexity_id,conversion_transaction_data.conversion_name,conversion_transaction_data.defaultedScope,conversion_transaction_data.final_scope_flag,conversion_transaction_data.scope_override_flag,conversion_transaction_data.conversion_source,conversion_transaction_data.conversion_volume, "+
		// "master_admin_lovs.code AS businessProcess,module_metadata.module_name AS moduleName,module_metadata.module_abbreviation AS abbreviation,module_metadata.business_process_id from conversion_transaction_data,master_admin_lovs,module_metadata "+
		// "where conversion_transaction_data.module_id = module_metadata.id and  module_metadata.business_process_id =master_admin_lovs.id and conversion_transaction_data.conversion_data_type_id =master_admin_lovs.id and master_admin_lovs.type ='Business Process' and conversion_transaction_data.engagement_id ="+id+"  and (conversion_transaction_data.is_user_create_flag is Null) ";
		String addInScopeMod = "";
		if (isInScopelist.size() > 0) {
			addInScopeMod += "and module_metadata.module_name in("
					+ InScopeModule + ")";
		}

		sql = "SELECT ctd.id,"
				+ "       ctd.conversion_data_type_id,"
				+ "       ctd.module_id,"
				+ "       ctd.client_complexity_id,"
				+ "       ctd.conversion_name,"
				+ "       ctd.defaultedScope,"
				+ "       ctd.final_scope_flag,"
				+ "       ctd.scope_override_flag,"
				+ "       ctd.conversion_source,"
				+ "       ctd.conversion_volume,"
				+ "       master_admin_lovs.code AS businessProcess,"
				+ "       module_metadata.module_name AS moduleName,"
				+ "       module_metadata.module_abbreviation AS abbreviation,"
				+ "       module_metadata.business_process_id"
				+ " FROM conversion_transaction_data ctd,"
				+ "     master_admin_lovs,"
				+ "     master_admin_lovs mal2,"
				+ "     module_metadata"
				+ " WHERE ctd.module_id = module_metadata.id"
				+ "  AND module_metadata.business_process_id = master_admin_lovs.id"
				+ "  AND ctd.conversion_data_type_id = mal2.id"
				+ "  AND master_admin_lovs.type = 'Business Process'"
				+ "  AND ctd.engagement_id = " + id
				+ "  AND (ctd.is_user_create_flag IS NULL)" + addInScopeMod
				+ " ORDER BY mal2.description,moduleName";

		System.out.println(sql);
		List<ConversionVo> conversionList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ConversionVo>(ConversionVo.class));

		return conversionList;
	}

	private void initConversionTransaction(Integer engagementId,
			String InScopeModule) {
		List<MasterAdminLovs> Worktypedata = getworkType();
		System.out.println(engagementId);

		String sqlAppend = "";

		if (!StringUtils.isEmpty(InScopeModule)) {
			sqlAppend = "and module_metadata.module_name in(" + InScopeModule
					+ ")";
		}

		String sql = "SELECT conversion_metadata.* "
				+ " FROM conversion_metadata,module_metadata "
				+ " where conversion_metadata.module = module_metadata.id and conversion_metadata.is_active = 1  "
				+ sqlAppend;
		List<ConversionMetaData> conversionAdminList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ConversionMetaData>(
						ConversionMetaData.class));

		if (conversionAdminList.size() > 0
				&& (!StringUtils.isEmpty(InScopeModule))) {
			System.out.println(sql);

			for (ConversionMetaData data : conversionAdminList) {
				int countRec = 0;

				sql = "select count(*) from conversion_transaction_data where engagement_id ="
						+ engagementId
						+ "  and  conversion_name ='"
						+ data.getConversion_name()
						+ "' and module_id = '"
						+ data.getModule() + "'";
				countRec = jdbcTemplate.queryForObject(sql, Integer.class);

				if (countRec == 0) {

					sql = "INSERT INTO conversion_transaction_data ( conversion_data_type_id,module_id,client_complexity_id,engagement_id,conversion_name,defaultedScope,final_scope_flag,conversion_source,conversion_volume) "
							+ "values('"
							+ data.getConversion_data_type()
							+ "','"
							+ data.getModule()
							+ "','"
							+ data.getClient_complexity()
							+ "','"
							+ engagementId
							+ "','"
							+ data.getConversion_name()
							+ "','"
							+ data.getIs_in_scope_flag()
							+ "','"
							+ data.getIs_in_scope_flag()
							+ "','"
							+ data.getConversion_source()
							+ "',"
							+ data.getConversion_volume() + ")";
					jdbcTemplate.update(sql);
				} else {

				}
			}
		}

		sql = "select *  FROM conversion_transaction_data where  engagement_id = "
				+ engagementId + " and lower(is_user_create_flag) is Null";
		List<ConversionTransactionData> conversionList = jdbcTemplate.query(
				sql, new BeanPropertyRowMapper<ConversionTransactionData>(
						ConversionTransactionData.class));

		if (conversionList.size() > 0) {
			for (ConversionTransactionData singleconversion : conversionList) {
				for (MasterAdminLovs data : Worktypedata) {

					Double result = 0.0;
					if (singleconversion.getFinal_scope_flag()
							.equalsIgnoreCase("no")) {
						result = 0.0;
					} else {

						sql = "SELECT  distinct CONCAT_WS('_',conversion_effort_master.effort,complexity_multiplier.value) FROM conversion_effort_master,complexity_multiplier where conversion_effort_master.workstream_id =complexity_multiplier.workstream and complexity_multiplier.effort_type ='conversion'  and conversion_effort_master.worktype_id ='"
								+ data.getId()
								+ "' and conversion_effort_master.complexity_id='"
								+ singleconversion.getClient_complexity_id()
								+ "' and complexity_multiplier.engagement_id = "
								+ engagementId;

						String effort = jdbcTemplate.queryForObject(sql,
								String.class);
						System.out.println(effort);

						result = (Double.parseDouble(effort.split("_")[0]
								.toString()))
								* (Double.parseDouble(effort.split("_")[1]
										.toString()));

					}

					sql = "insert into conversion_transaction_join_table(worktype,effort,conversion_data_id,engagement_id) values('"
							+ data.getId()
							+ "','"
							+ result
							+ "',"
							+ singleconversion.getId()
							+ ",'"
							+ engagementId
							+ "')"
							+ " ON DUPLICATE KEY UPDATE effort ='"
							+ result + "'";
					jdbcTemplate.update(sql);
				}

			}

		}

	}

	@Override
	public List<String> update(
			ConversionTransactionData conversionTransactionData, int engId,
			List<String> dupConverList) {
		String sql = "";
		int id = 0;



		persist(conversionTransactionData);
		id = conversionTransactionData.getId();
		List<MasterAdminLovs> Worktypedata = getworkType();
		System.out.println(id);

		for (MasterAdminLovs data : Worktypedata) {
			String effort = "";
			Double result = 0.0;

			if (conversionTransactionData.getFinal_scope_flag()
					.equalsIgnoreCase("no")) {
				result = 0.0;
			} else {

				sql = "SELECT  distinct CONCAT_WS('_',conversion_effort_master.effort,complexity_multiplier.value) FROM conversion_effort_master,complexity_multiplier where conversion_effort_master.workstream_id =complexity_multiplier.workstream and complexity_multiplier.effort_type ='conversion'  and conversion_effort_master.worktype_id ='"
						+ data.getId()
						+ "' and conversion_effort_master.complexity_id='"
						+ conversionTransactionData.getClient_complexity_id()
						+ "' and complexity_multiplier.engagement_id = "
						+ conversionTransactionData.getEngagement_id();

				effort = jdbcTemplate.queryForObject(sql, String.class);
				System.out.println(effort);

				result = (Double.parseDouble(effort.split("_")[0].toString()))
						* (Double.parseDouble(effort.split("_")[1].toString()));

			}
			sql = "insert into conversion_transaction_join_table(worktype,effort,conversion_data_id,engagement_id) values('"
					+ data.getId()
					+ "','"
					+ result
					+ "',"
					+ id
					+ ",'"
					+ conversionTransactionData.getEngagement_id() + "') ";

			jdbcTemplate.update(sql);

			// sql
			// ="insert into conversion_transaction_join_table(worktype,effort,conversion_data_id) values('"+data.getId()+"','"+effort+"',"+id+")";
			// jdbcTemplate.update(sql);

		}

		return dupConverList;

	}

	@Override
	public void deleteByEngagementId(Integer id) {
		try {

			String sql = "DELETE FROM conversion_transaction_join_table WHERE conversion_data_id IN (SELECT id FROM conversion_transaction_data where engagement_id = '"
					+ id + "')";
			jdbcTemplate.update(sql);

			jdbcTemplate
					.update("delete from conversion_transaction_data where engagement_id=? ",
							new Object[] { id });
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<ConversionVo> findAllByEngagementIdForUserCreate(Integer id) {
		String sql = "SELECT t1.*,t2.description AS dataType,t3.description AS businessProcess,t4.module_name AS moduleName,t4.module_abbreviation AS abbreviation,'' AS defaultedScope,t5.description AS complexity "
				+ "FROM conversion_transaction_data t1  "
				+ "LEFT JOIN conversion_data_type t2 ON t1.conversion_data_type_id=t2.id  "
				+ "LEFT JOIN business_process t3 ON t1.business_process_id=t3.id "
				+ "LEFT JOIN module_metadata t4 ON t1.module_id=t4.id "
				+ "LEFT JOIN client_complexity t5 ON t1.client_complexity_id=t5.id "
				+ "WHERE t1.engagement_id=? AND t1.is_user_create_flag=?";
		List<ConversionVo> conversionList = jdbcTemplate.query(sql,
				new Object[] { id, "Yes" },
				new BeanPropertyRowMapper<ConversionVo>(ConversionVo.class));

		return conversionList;
	}

	@Override
	public Map<Object, Object> loadAllConversion(String idparam) {

		String InvalidRiceStr = "";
		String listInvalidRice = "Select id FROM conversion_transaction_data where engagement_id='"
				+ idparam
				+ "' and module_id not in(select id from module_transaction_data where engagement_id ='"
				+ idparam + "' and  lower(is_in_scope_flag) ='yes')";
		List<Integer> listInvalidId = jdbcTemplate.queryForList(
				listInvalidRice, Integer.class);

		if (listInvalidId.size() > 0) {
			if (listInvalidId.size() == 1) {
				InvalidRiceStr = "'" + listInvalidId.get(0) + "'";
			} else {
				InvalidRiceStr = "'"
						+ org.apache.commons.lang.StringUtils.join(
								listInvalidId, "','") + "'";
			}

			String SqlDeleteInvalidRecords = "delete FROM conversion_transaction_data where id in("
					+ InvalidRiceStr + ")";
			jdbcTemplate.update(SqlDeleteInvalidRecords);

			String SqlDeleteInvalidRecordsJoinTable = "delete FROM conversion_transaction_join_table  where conversion_data_id in("
					+ InvalidRiceStr + ")";
			jdbcTemplate.update(SqlDeleteInvalidRecordsJoinTable);
		}

		String sql = "";
		Map<Object, Object> conversionDtaMap = new HashMap<Object, Object>();
		int engagementId = Integer.parseInt(idparam);

		sql = "SELECT * FROM master_admin_lovs where lower(type) ='conversion data type'";
		List<MasterAdminLovs> conversiondataTypList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));

		sql = "SELECT * FROM master_admin_lovs where lower(type) ='Workstream'";
		List<MasterAdminLovs> queryForWorkstream = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));

		sql = "SELECT * FROM master_admin_lovs where lower(type) ='complexity'";
		List<MasterAdminLovs> complexityList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));

		sql = "SELECT module_transaction_data.business_process_id,module_transaction_data.module_abbreviation,module_transaction_data.id,module_transaction_data.module_name,module_transaction_data.is_in_scope_flag,master_admin_lovs.code as businessprocess "
				+ " FROM module_transaction_data INNER JOIN master_admin_lovs ON module_transaction_data.business_process_id=master_admin_lovs.id where master_admin_lovs.type ='Business Process' and module_transaction_data.is_in_scope_flag ='Yes' and engagement_id="
				+ engagementId;

		List<ConversionModuleData> moduleDataList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ConversionModuleData>(
						ConversionModuleData.class));

		// sql
		// ="SELECT conversion_transaction_data.* FROM conversion_transaction_data,master_admin_lovs where master_admin_lovs.id =conversion_transaction_data.conversion_data_type_id  and engagement_id ="+engagementId+" and lower(is_user_create_flag)='yes' order by master_admin_lovs.description";

		sql = "SELECT conversion_transaction_data.* FROM conversion_transaction_data,module_transaction_data,master_admin_lovs "
				+ " where module_transaction_data.id = conversion_transaction_data.module_id  "
				+ " and  conversion_transaction_data.engagement_id ="
				+ engagementId
				+ " and conversion_transaction_data.is_user_create_flag='Yes'  "
				+ " and master_admin_lovs.id =conversion_transaction_data.conversion_data_type_id "
				+ " and module_transaction_data.is_in_scope_flag='Yes' "
				+ " order by master_admin_lovs.description,module_transaction_data.module_name";

		// sql
		// ="SELECT conversion_transaction_data .* FROM conversion_transaction_data,module_transaction_data where module_transaction_data.id = conversion_transaction_data.module_id and  conversion_transaction_data.engagement_id ="+engagementId+" and conversion_transaction_data.is_user_create_flag='Yes' and module_transaction_data.is_in_scope_flag='Yes'";
		List<ConversionTransactionData> conversiontrasactionDta = jdbcTemplate
				.query(sql,
						new BeanPropertyRowMapper<ConversionTransactionData>(
								ConversionTransactionData.class));

		System.out.println(sql);
		List<MasterAdminLovs> Worktypedata = getworkType();

		List<ConversionVo> conversionAllAdminList = findAllByEngagementId(
				engagementId, "", "");

		String sqlforRiceJoinTable = "select CONCAT_WS('_',conversion_data_id,worktype) as data ,effort from conversion_transaction_join_table";
		List<Map<String, Object>> data = jdbcTemplate
				.queryForList(sqlforRiceJoinTable);

		Map<Object, Object> listWorktypeConversioniddata = new HashMap<Object, Object>();
		for (Map<String, Object> map : data) {
			listWorktypeConversioniddata
					.put(map.get("data"), map.get("effort"));
		}

		sql = "select workstream,value from complexity_multiplier where effort_type ='conversion' and engagement_id ="
				+ Integer.parseInt(idparam);
		List<Map<String, Object>> Complexity_multiplier = jdbcTemplate
				.queryForList(sql);
		Map<Object, Object> listComplexity_multiplier = new HashMap<Object, Object>();
		if (Complexity_multiplier.size() > 0) {
			for (Map<String, Object> map : Complexity_multiplier) {
				listComplexity_multiplier.put(map.get("workstream"),
						map.get("value"));
			}
		}

		sql = "SELECT count(*) FROM conversion_transaction_data where engagement_id ='"
				+ idparam + "' and final_scope_flag='Yes'";
		int totalConversionCountInScope = jdbcTemplate.queryForObject(sql,
				Integer.class);

		conversionDtaMap.put("totalConversionCountInScope",
				totalConversionCountInScope);

		conversionDtaMap.put("conversiondataTypList", conversiondataTypList);
		conversionDtaMap.put("moduleDataList", moduleDataList);
		conversionDtaMap.put("complexityList", complexityList);
		conversionDtaMap.put("worktypeLov", Worktypedata);
		conversionDtaMap.put("worksteamList", queryForWorkstream);
		conversionDtaMap
				.put("conversiontrasactionDta", conversiontrasactionDta);
		conversionDtaMap.put("conversionAllAdminList", conversionAllAdminList);
		conversionDtaMap.put("listWorktypeConversioniddata",
				listWorktypeConversioniddata);
		conversionDtaMap.put("TotalEffortClient",
				getFinalCount("yes", engagementId));
		conversionDtaMap.put("TotalEffortDefaulted",
				getFinalCount("", engagementId));
		conversionDtaMap
				.put("Complexity_multiplier", listComplexity_multiplier);

		return conversionDtaMap;
	}

	public Map<String, String> getFinalCount(String inputParam, int id) {
		String sql = "";
		Map<String, String> listCount = new HashMap<String, String>();
		if (inputParam.equals("yes")) {
			sql = "SELECT distinct client_complexity_id as complexity,count(final_scope_flag) as count FROM conversion_transaction_data where engagement_id = "
					+ id
					+ " and lower(is_user_create_flag) ='yes' and lower(final_scope_flag) ='yes' group by client_complexity_id";

		} else {
			sql = "SELECT distinct client_complexity_id as complexity ,count(final_scope_flag) as count  FROM conversion_transaction_data where engagement_id = "
					+ id
					+ " and lower(final_scope_flag) ='yes'and lower(is_user_create_flag) is null group by client_complexity_id";
		}
		try {

			List<Map<String, Object>> listOfMap = jdbcTemplate
					.queryForList(sql);
			for (Map<String, Object> map : listOfMap) {
				listCount.put(map.get("complexity").toString(), map
						.get("count").toString());

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listCount;
		// SELECT distinct client_complexity_id,count(final_scope_flag) FROM
		// conversion_transaction_data where lower(is_user_create_flag) is null
		// group by client_complexity_id;
	}

	public void saveConversionComplexityMultiplier(String data) {

		if ((!data.split("_")[1].equals(""))
				&& (!data.split("_")[1].equals(null))) {
			String sql = "insert into complexity_multiplier(workstream,value,effort_type) values('"
					+ data.split("_")[0]
					+ "','"
					+ data.split("_")[1]
					+ "','"
					+ data.split("_")[2]
					+ "')"
					+ " ON DUPLICATE KEY UPDATE value ='"
					+ data.split("_")[1]
					+ "'";
			System.out.println(sql);
			jdbcTemplate.update(sql);
		}

	}

	public List<MasterAdminLovs> getworkType() {
		String sqlForWorktype = "SELECT * FROM master_admin_lovs where lower(type) ='work type' order by description";

		List<MasterAdminLovs> Worktypedata = jdbcTemplate.query(sqlForWorktype,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));

		return Worktypedata;
	}

	@Override
	public void initComplexity(String engagement_id) {
		// {data={2=4, 3=5, 4=5}, id={engId=16}}
		// /init data with 1's for all workstream

		Map<String, Map<String, String>> mapInitComplexityMultiplier = new HashMap<String, Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();

		Map<String, String> engMap = new HashMap<String, String>();
		engMap.put("engId", engagement_id);

		List<MasterAdminLovs> workstreams = masterAdminUtilityDaoImpl
				.getMasterAdminListData("workstream", "");
		for (MasterAdminLovs workstream : workstreams) {
			map.put(workstream.getId().toString(), "1");
			// mapConversion.put(workstream.getId().toString(), "1");

		}
		mapInitComplexityMultiplier.put("data", map);
		mapInitComplexityMultiplier.put("id", engMap);

		saveComplexityMultiplier(mapInitComplexityMultiplier, "rice");
		saveComplexityMultiplier(mapInitComplexityMultiplier, "conversion");
	}

	@Override
	public void saveComplexityMultiplier(Map<String, Map<String, String>> data,
			String type) {
		riceTransactionDataDaoImpl.saveComplexityMultiplier(data, type);

	}

	@Override
	public boolean getDuplicateStatus(String strInConversionName) {

		return false;
	}

}