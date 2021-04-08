package com.pwc.denali2.estimator.dao;

import java.util.List;




import java.util.Map;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.Engagement_share_permission;

public interface ProjectManagementDao {
	
	void saveProject(Engagement engagement);
	
	void updateProject(Engagement engagement);
	
	List<Engagement_share_permission> listAvailableProject(String currentUserGuid,Engagement engagement);
	
	List<String> getEngagementStatusList();
	
	List<String> getEnterpriseApplicationList();
	
	List<String> getEnterpriseApplicationListByEngagementId(int engagementId);
	
	void saveEnterPriseApplication(int engagementId, String enterPriseApplicationStringList);
	
	void updateEnterPriseApplication(int engagementId, String enterPriseApplicationStringList);
	
	/*void sendEmail(String newStatus, String toEmail, String fromEmail);*/

	List<String> getEngagementStatusById(int engagementId);
	
	Integer checkEngagementNo(int engagementId,String project_no);
	
	Engagement getEngagementById(int engagementId);
	/*void send(EmailData emailData);*/

	List<Map<String, Object>> getOfferingList();
	List<String> getClientList();

	String copyProject(String copyList, int engagementId);
	String copyAllProjectDta(int engagementId,String copyList);
	String releaseProject( int parent,int engagementId);
	String copyConversion(int engagementId,String status,String copyfromengId);
	String copyModules(int engagementId,String status,String copyfromengId);
	String copyRice(int engagementId,String status,String copyfromengId);
	String copyAddServices(int engagementId,String status,String copyfromengId);
	String copyComplexityMultiplier(int engagementId,String status,String copyfromengId);

	String copyRates(int engagementId, String status, String copyfromengId);

	
	
	
}
