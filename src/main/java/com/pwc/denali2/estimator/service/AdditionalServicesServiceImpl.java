package com.pwc.denali2.estimator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.AdditionalDao;
import com.pwc.denali2.estimator.dao.AdditionalEffortDao;
import com.pwc.denali2.estimator.dao.ServiceOwnerDao;
import com.pwc.denali2.estimator.dao.WorkStreamDao;
import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.ServiceOwner;
import com.pwc.denali2.estimator.model.WorkStream;

@Service("additionalService")
@Transactional
public class AdditionalServicesServiceImpl implements AdditionalServicesService {
	@Autowired
	private AdditionalDao additionaldao;
	@Autowired
	private WorkStreamDao workStreamdao;
	@Autowired
	private ServiceOwnerDao serviceOwnerdao;
	@Autowired
	private AdditionalEffortDao additionalEffortDao;
	
	@Override
	public List<WorkStream> findAllWorkStream() {
		return workStreamdao.findAll();
	}

	@Override
	public List<ServiceOwner> findAllServiceOwner() {
		return serviceOwnerdao.findAll();
	}

	@Override
	public Map<Object, Object> findAdditionalServiceByEngagementId(Integer id) {
		return additionaldao.findAllByEngagementId(id);
	}

	
	@Override
	public void save(JSONObject jsonarr,String id) {
		
		additionaldao.deleteByID(Integer.parseInt(id));
		
		if(jsonarr.length() >0){
			additionaldao.save(jsonarr,id);
			}		
//			for(AdditionalService additionalService:AddservcesMapList){
//				if(additionalService.getId()==-1&&additionalService.getWork_stream_id()==0){
//					continue;
//				}
//				additionalService.setId(null);
			
//			}
//		}
		
	}

	@Override
	public Map<Object, Object> findAdditionalServiceEffortData(Integer id) {
		// TODO Auto-generated method stub
		return additionalEffortDao.findAdditionalServiceEffortData(id);
	}

	


}