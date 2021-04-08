package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.MasterAdminLovs;

public interface MasterAdminUtilityDao {
	
	List<MasterAdminLovs> getMasterAdminListData(String type,String code);
	String getMasterAdminCode(int id);
	Map<String, List<List>> listByTypeTag(List<String> types, String tag);
	List<MasterAdminLovs> listAvailableAdminLov(
			MasterAdminLovs masterAdminLovs, String types);

}
