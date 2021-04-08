package com.pwc.denali2.estimator.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.pwc.denali2.estimator.dao.BusinessProcessDao;
import com.pwc.denali2.estimator.dao.ConversionDataTypeDao;
import com.pwc.denali2.estimator.dao.ConversionsDao;
import com.pwc.denali2.estimator.dao.ModuleMetaDataDao;
import com.pwc.denali2.estimator.model.BusinessProcess;
import com.pwc.denali2.estimator.model.ConversionDataType;
import com.pwc.denali2.estimator.model.ConversionTransactionData;
import com.pwc.denali2.estimator.model.ConversionVo;
import com.pwc.denali2.estimator.model.ModuleMetaData;

@Service("conversionsService")
@Transactional
public class ConversionsServiceImpl implements ConversionsService {
	@Autowired
	private ConversionsDao conversionsDao;
	@Autowired
	private BusinessProcessDao businessProcessDao;
	@Autowired
	private ConversionDataTypeDao conversionDataTypeDao;
	@Autowired
	private ModuleMetaDataDao moduleMetaDataDao;

	@Override
	public List<ConversionVo> findAllByEngagementId(Integer id,String type,String userflag) {
		return conversionsDao.findAllByEngagementId(id,type,userflag);
	}

	@Override
	public String update(List<ConversionTransactionData> ls,int id) {
		if(ls!=null&&ls.size()>0){
			conversionsDao.deleteByEngagementId(id);

			for(ConversionTransactionData conversionTransactionData:ls){
			if(conversionTransactionData.getId()==-1&&conversionTransactionData.getConversion_data_type_id()==0){
			continue;
			}

			conversionTransactionData.setId(null);
			conversionTransactionData.setEngagement_id(id);
			conversionsDao.update(conversionTransactionData,id,null);
			}
			}
			return "";
//		List<String> dupConversionName = new ArrayList<String>();
//		String strInConversionName ="";
//		String dupStatus ="";
//		if(ls!=null&&ls.size()>0){
//			
//			List<String> listConversionName = new ArrayList<String>();
//			Set<String> setConversion = new HashSet<String>();
//			
//			for(ConversionTransactionData conversionTransactionData:ls){
//				if(conversionTransactionData.getId()==-1&&conversionTransactionData.getConversion_data_type_id()==0){
//					continue;
//				}
//				if(!StringUtils.isEmpty(conversionTransactionData.getConversion_name()) && (!StringUtils.isEmpty(conversionTransactionData.getIs_user_create_flag()))){
//					listConversionName.add(conversionTransactionData.getConversion_name());
//					setConversion.add(conversionTransactionData.getConversion_name());
//					
//				}
//			}
//			System.out.println(listConversionName);
//			System.out.println(setConversion);
//				if(setConversion.size() <listConversionName.size()){
//					dupStatus ="Duplicates exists";
//				} 
//				
//				if(setConversion.size() ==listConversionName.size()){
//					conversionsDao.deleteByEngagementId(id);
//					
//					for(ConversionTransactionData conversionTransactionData:ls){
//						if(conversionTransactionData.getId()==-1&&conversionTransactionData.getConversion_data_type_id()==0){
//							continue;
//						}
//					
//						conversionTransactionData.setId(null);
//						conversionTransactionData.setEngagement_id(id);
//						conversionsDao.update(conversionTransactionData,id,dupConversionName);
//						
//					}
//					}
//				}
//				
//			
//			
//		return dupStatus;
	}

	@Override
	public List<ConversionVo> findAllByEngagementIdForUserCreate(Integer id) {
		return conversionsDao.findAllByEngagementIdForUserCreate(id);
	}

	@Override
	public List<BusinessProcess> findAllBusinessProcess() {
		return businessProcessDao.findAll();
	}

	@Override
	public List<ModuleMetaData> findModuleMetaDataByBusinessId(Integer id) {
		return moduleMetaDataDao.findByBusinessId(id);
	}

	@Override
	public List<ConversionDataType> findAllConversionDataType() {
		return conversionDataTypeDao.findAll();
	}

	@Override
	public List<ModuleMetaData> findAllModuleMetaData() {
		return moduleMetaDataDao.findAll();
	}

	@Override
	public Map<Object, Object> loadAllConversion(String idparam) {
		// TODO Auto-generated method stub
		return conversionsDao.loadAllConversion(idparam);
	}



	@Override
	public void saveComplexityMultiplier(Map<String, Map<String, String>> data,
			String type) {
		conversionsDao.saveComplexityMultiplier(data,type);
		
	}

	


	
}