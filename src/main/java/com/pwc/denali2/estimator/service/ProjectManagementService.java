package com.pwc.denali2.estimator.service;

import java.util.List;




import java.util.Map;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementAttachment;
import com.pwc.denali2.estimator.model.Engagement_share_permission;

public interface ProjectManagementService {
	void saveProject(Engagement engagement);
	
	void updateProject(Engagement engagement);
	
	List<Engagement_share_permission> listAvailableProject(String currentUserGuid,Engagement engagement);
	
	void saveEngagementAttach(EngagementAttachment engagementAttachment);
	
	List<String> getEngagementStatusList();
	
	List<EngagementAttachment> findAllAttachAttachmentByEngagementId(Integer id);

	List<String> getEnterpriseApplicationList();

	List<String> getEnterpriseApplicationListByEngagementId(int engagementId);

	void saveEnterPriseApplication(Integer id, String enterPriseApplicationList);

	void updateEnterPriseApplication(Integer id, String enterPriseApplicationList);

	/*void sendEmail(String newStatus, String toEmail, String fromEmail);*/

	List<String> getEngagementStatusById(Integer id);
	
	Engagement getEngagementById(Integer id);
	/*void send(EmailData data);*/

	Boolean checkIfProjectNoExist(Integer id, String project_no);
	List<Map<String, Object>> getOfferingList();
	List<String> getClientList();
	void copyProjectFrRelease(Integer parent, Integer engid);
	String copyProject(String copyList, int engId);

	void checkForCloudOffering(Engagement engagement, String prevCloudOffering,
			String currentCloudOffering);
	

}
