package com.pwc.denali2.estimator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.ComplexityDao;
import com.pwc.denali2.estimator.dao.ModuleTransactionDataDao;
import com.pwc.denali2.estimator.dao.PaaSAppsTypeDao;
import com.pwc.denali2.estimator.dao.RiceTransactionDataDao;
import com.pwc.denali2.estimator.dao.RiceTypeDao;
import com.pwc.denali2.estimator.model.ClientComplexity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.PaaSAppType;
import com.pwc.denali2.estimator.model.RiceTransactionData;
import com.pwc.denali2.estimator.model.RiceType;

@Service("riceTransactionDataService")
@Transactional
public class RiceTransactionDataServiceImpl implements RiceTransactionDataService {
	@Autowired
	private RiceTypeDao riceTypeDao;
	
	@Autowired
	private RiceTransactionDataDao riceTransactionDataDao;
	@Autowired
	private ModuleTransactionDataDao moduleTransactionDataDao;

	@Override
	public Map<Object, Object> loadAllRice(String idparam) {
		System.out.println(idparam+"asyduagh");
		return riceTransactionDataDao.findAll(idparam);
	}

	

	@Override
	public List<RiceTransactionData> findRiceTransactionDataByEngagementId(Integer id) {
		return riceTransactionDataDao.findAllByEngagementId(id);
	}

	@Override
	public void save(List<RiceTransactionData> as) {
	   List<Integer> idList =new ArrayList<Integer>();
	   List<Integer> list = new ArrayList<Integer>();
	  int engId = 0;
		
		if (as != null && as.size() > 0) {
			
			//riceTransactionDataDao.deleteByID(as.get(0).getEngagement_id());
			for (RiceTransactionData riceTransactionData : as) {
				if(riceTransactionData.getId()==-1&&riceTransactionData.getRice_type_id()==0){
					continue;
				}
				//riceTransactionData.setId(null);
				idList.add(riceTransactionData.getId());
				 list =riceTransactionDataDao.save(riceTransactionData,idList);
                 //engId =riceTransactionData.getId();
				
			}
			System.out.println(list);
			riceTransactionDataDao.deleteByID(list,as.get(0).getEngagement_id());
		
		}
	   
		
	}

	
	@Override
	public List<PaaSAppType> findAllPaaSAppType() {
		return null;
	}
	@Override
	public List<ClientComplexity> findComplexity() {
		return null;
	}

	@Override
	public List<ModuleTransactionData> findModuleDataByEngagementId(Integer id) {
		return moduleTransactionDataDao.findByEngagementId(id);
	}



	@Override
	public void saveComplexityMultiplier(Map<String, Map<String, String>> data,String type) {
		riceTransactionDataDao.saveComplexityMultiplier(data,type);
		
	}

}