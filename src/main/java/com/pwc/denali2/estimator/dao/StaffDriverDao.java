package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.StaffDriverVo;
import com.pwc.denali2.estimator.model.StaffdriverData;


public interface StaffDriverDao {


	Map<String, List<List>> getAllEfforts(String engagement_id);

	Map<String, List<List>> getAllTeam(String engagement_id);

	Map<String, List<List>> getAllTeamRates(String engagement_id);


	Boolean saveAllEfforts(Map<String, Number> data,String engagement_id);
	Boolean saveAllBlendedrates(Map<String, Number> data,String engagement_id);

	Boolean saveAllTeam(Map<String, Number> data, String engagement_id);

	List<StaffDriverVo> getAllData(String engagement_id, String workstream,
			String workT, String rateT);

	Boolean saveBlendedrates(int workstream_id, String rate_type,
			String work_type, double rate, String engagement_id);

	Boolean copyFromAdmin(String engagement_id);
}
