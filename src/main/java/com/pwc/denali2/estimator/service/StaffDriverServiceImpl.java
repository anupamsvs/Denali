package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.MasterAdminUtilityDao;
import com.pwc.denali2.estimator.dao.StaffDriverDao;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.StaffDriverVo;
import com.pwc.denali2.estimator.model.StaffdriverData;

@Service("staffDriverService")
@Transactional
public class StaffDriverServiceImpl implements staffDriverService {

	@Autowired
	StaffDriverDao staffDriverDao;

	@Autowired
	MasterAdminUtilityDao masterAdminUtilityDao;

	@SuppressWarnings({ "null", "rawtypes" })
	@Override
	public List<Map<String, List>> findAllEfforts() {
		return null;

		/*
		 * // TODO Auto-generated method stub Map<String, List<List>> sdlist =
		 * this.staffDriverDao.getAllEfforts(); //For DDC Map<String,List>
		 * mappedData = new HashedMap<String, List>() ; List<Map<String,
		 * String>> list = null; Object neObj = null; for(List
		 * sd:sdlist.get("ddc")){ mappedData.put("ddc", sd);
		 * list.add(mappedData); }
		 * 
		 * return list;
		 */

	}
	
	@Override
	public boolean copyStaffingDrivers(String engagement_id){
		return staffDriverDao.copyFromAdmin(engagement_id);
	}

	public double calculateOffsite(double sum, double effort, double value,
			double offshore) {
		try {
			double calc = (((100 - offshore) * effort * value) / 100) / sum;
			return calc;
		} catch (Exception e) {
			return 0;
		}
	}

	public double calculateOnsite(double sum, double effort, double value) {
		try {
			double calc = (value / sum) * effort;
			return calc;
		} catch (Exception e) {
			return 0;
		}
	}

	public double calculateOffshore() {
		return 0.00;
	}

	@Override
	public boolean calculateEffort(String engagement_id) {
		List<MasterAdminLovs> workstreams = masterAdminUtilityDao
				.listAvailableAdminLov(null, "workstream");

		String[] workTypes = { "ddc", "workstream" };
		String[] rateTypes = { "csp", "rsr" };

		for (String workt : workTypes) {
			for (String rateT : rateTypes) {
				for (MasterAdminLovs works : workstreams) {
					double blendingRate = 0;
					double offshore = 0;
					List<StaffDriverVo> data = staffDriverDao.getAllData(
							engagement_id, works.getId() + "",workt,rateT);
					for (StaffDriverVo row : data) {
						double rateDouble = 0;
						if ("onsite".equalsIgnoreCase(row.getTeam_type())) {
							rateDouble = this.calculateOnsite(row.getSum(),
									row.getWork_value(), row.getTeam_value());
							blendingRate = blendingRate
									+ (rateDouble * row.getRate());
						} else if (row.getLevel_id() == 55
								&& "offsite".equalsIgnoreCase(row
										.getTeam_type())) {
							blendingRate = blendingRate
									+ (((row.getOffshore() * row
											.getWork_value()) / 100) * row
											.getRate());
							continue;
						} else if ("offsite".equalsIgnoreCase(row
								.getTeam_type())) {
							rateDouble = this.calculateOffsite(row.getSum(),
									row.getWork_value(), row.getTeam_value(),
									row.getOffshore());
							blendingRate = blendingRate
									+ (rateDouble * row.getRate());
						}
						
					}
					System.out.println(blendingRate / 100);
					Double Blended = Double.isNaN(blendingRate)?0:(blendingRate/100);
					staffDriverDao.saveBlendedrates(works.getId(), rateT, workt, Blended, engagement_id);
				}
				
			}
			
		}
		return false;
	}

}