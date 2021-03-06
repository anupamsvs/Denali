package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.EngagementAttachmentDao;
import com.pwc.denali2.estimator.dao.ModuleMetaDataDao;
import com.pwc.denali2.estimator.dao.ModuleTransactionDataDao;
import com.pwc.denali2.estimator.dao.ProjectManagementDao;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementAttachment;
import com.pwc.denali2.estimator.model.Engagement_share_permission;

@Service("projectMangementService")
@Transactional
public class ProjectManagementServiceImpl implements ProjectManagementService{
	
	@Autowired
	private ProjectManagementDao projectManagementDao;
	
	@Autowired
	private ModuleTransactionDataDao moduleTransactionDataDao;
	
	@Autowired
	private EngagementAttachmentDao engagementAttachmentDao;

	@Override
	public void saveProject(Engagement engagement) {
		projectManagementDao.saveProject(engagement);
	}
	@Override
	public void updateProject(Engagement engagement) {
		projectManagementDao.updateProject(engagement);
	}
	@Override
	public List<Engagement_share_permission> listAvailableProject(String currentUserGuid,Engagement engagement) {

		return projectManagementDao.listAvailableProject(currentUserGuid,engagement);
	}
	@Override
	public void saveEngagementAttach(EngagementAttachment engagementAttachment) {
		engagementAttachmentDao.save(engagementAttachment);
	}

	@Override
	public List<String> getEngagementStatusList() {
		
		return projectManagementDao.getEngagementStatusList();
	}
	
	@Override
	public List<String> getEnterpriseApplicationList() {
		
		return projectManagementDao.getEnterpriseApplicationList();
	}

	@Override
	public List<EngagementAttachment> findAllAttachAttachmentByEngagementId(Integer id) {
		return engagementAttachmentDao.findAllByEngagementId(id);
	}
	
	@Override
	public List<String> getEnterpriseApplicationListByEngagementId(int engagementId) {
		return projectManagementDao.getEnterpriseApplicationListByEngagementId(engagementId);
	}
	
	@Override
	public void saveEnterPriseApplication(Integer id, String enterPriseApplicationList) {
		projectManagementDao.saveEnterPriseApplication(id,enterPriseApplicationList);
	}
	
	@Override
	public void updateEnterPriseApplication(Integer id, String enterPriseApplicationList) {
		projectManagementDao.updateEnterPriseApplication(id,enterPriseApplicationList);
	}
	
	/*@Override
	public void sendEmail(String newStatus, String toEmail, String fromEmail){
		projectManagementDao.sendEmail(newStatus,toEmail,fromEmail);
	}*/
	
	@Override
	public List<String> getEngagementStatusById(Integer id){
		return projectManagementDao.getEngagementStatusById(id);
	}
	
	@Override
	public  Engagement getEngagementById(Integer id){
		return projectManagementDao.getEngagementById(id);
	}
	
	
	@Override
	public  Boolean checkIfProjectNoExist(Integer id,String project_no){
		try{
		return projectManagementDao.checkEngagementNo(id,project_no) > 0 ;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getOfferingList() {
		// TODO Auto-generated method stub
		return projectManagementDao.getOfferingList();
	}
	/*@Override
	public void send(EmailData data){
		projectManagementDao.send(data);
	}*/
	@Override
	public List<String> getClientList() {
		// TODO Auto-generated method stub
		return projectManagementDao.getClientList();
	}
	
	@Override
	public void copyProjectFrRelease(Integer parent,Integer engId) {
	projectManagementDao.releaseProject(parent,engId);

	}

	@Override
	public String copyProject(String copyList,int engagementId) {
		
		return projectManagementDao.copyProject(copyList,engagementId);
	}
	@Override
	public void checkForCloudOffering(Engagement engagement,
			String prevCloudOffering, String currentCloudOffering) {
		 moduleTransactionDataDao.checkForCloudOffering(engagement, prevCloudOffering, currentCloudOffering);
	}

	
}
