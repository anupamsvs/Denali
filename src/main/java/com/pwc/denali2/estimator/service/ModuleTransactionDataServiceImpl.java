package com.pwc.denali2.estimator.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.ModuleTransactionDataDao;
import com.pwc.denali2.estimator.dao.PwcResponsibilityDao;
import com.pwc.denali2.estimator.dao.PwcResponsibilityDaoImpl;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ModuleTransactionActivity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ModuleTransactionDataVO;
import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;

@Service("moduleTransactionDataService")
@Transactional
public class ModuleTransactionDataServiceImpl implements ModuleTransactionDataService{

	@Autowired
	private ModuleTransactionDataDao moduleTransactionDataDao;
	
	@Autowired
	private PwcResponsibilityDao pwcResponsibility;
	
	@Autowired
	private ProjectUnitsServices ProjectUnitsServices;
	
	
	@Override
	public void copyMetaDataForEngagement(String engagementId) {
		
		moduleTransactionDataDao.copyMetaDataForEngagment(engagementId);
	}

	@Override
	public List<ModuleTransactionData> findEngagementTransactionData(String engagementId) {
		
		return moduleTransactionDataDao.findEngagementTransactionData(engagementId);
	}

	@Override
	public void saveModuleTransactionData(ModuleTransactionData moduleTransactionData) {
		
		moduleTransactionDataDao.saveModuleTransactionData(moduleTransactionData);
	}

	@Override
	public List<ActivityManagementData> getActivities() {
		return moduleTransactionDataDao.getAllActivities();
	}
	

	public double getDependantData(String activity_id,String engagement_id,double prevData){
		ActivityManagementData newDepAct =  (ActivityManagementData) moduleTransactionDataDao.findDependantActData(activity_id);
		if(newDepAct == null){
			if(prevData != 0.00){
				return prevData;
			}else{
			return 1.00;
			}
		}
		//If there is dependant activity get its Base Driver
		else{
			double driver = Double.valueOf(newDepAct.getDriver());
			Pwc_Responsibility_Vo pwcResp = this.pwcResponsibility.findResponsibilityByEngagement(engagement_id, newDepAct.getId().toString());
			double BuFactor = Double.parseDouble(pwcResp.getBu_dependency());
			double newData = (driver * BuFactor);
			return this.getDependantData(newDepAct.getId().toString(),engagement_id,newData);
		}
	}
	
	
	
	/*
	@SuppressWarnings("null")
	@Override
	public Map<String,Double> calculateAct(ActivityManagementData activity,String unit,ModuleTransactionActivity module_trans,Engagement engagement,Map<String,Double> dependantData){
		  DecimalFormat twoDForm = new DecimalFormat("#.#");
		//Initialize
		Double out_ddc = 0.00;
		Double out_onsite = 0.00;
		Double out_testing= 0.00;
		Integer driver = 1;
		Double BuFactor = 1.00;
		//calculation
		Double calc_unit = ProjectUnitsServices.listByUnit(unit, engagement.getId().toString());
		
		//Get driver 
		try{
		 driver = Integer.parseInt(activity.getDriver());
		}catch(Exception e){}
		
		Boolean ddcSupport = (module_trans.getDdc_supported_flag().equalsIgnoreCase("Yes"));
		Boolean Testing = (engagement.getTesting().equals("1"));
		boolean workstream = (activity.getWorkstream().equals("2"));
		if(!workstream){
			Testing = false;
		}
		Pwc_Responsibility_Vo pwcResp = this.pwcResponsibility.findResponsibilityByEngagement(engagement.getId().toString(), activity.getId().toString(), engagement.getVersion().toString());
		BuFactor = Double.parseDouble(pwcResp.getBu_dependency());
//		System.out.println(BuFactor + " | "+ activity.getDdc_sup_wsws() +" | "+ driver +" | "+ calc_unit + "");
		
		if(Testing){
			out_onsite = (Integer.parseInt(activity.getTaas_ws()) * calc_unit * module_trans.getModuleweightage() * module_trans.getComplexity_factor() * driver*BuFactor)/100;
			out_ddc	   = (Integer.parseInt(activity.getTaas_ddc())* calc_unit * module_trans.getModuleweightage() * module_trans.getComplexity_factor() *  driver*BuFactor)/100;
		}else if(!Testing){
			if(ddcSupport){
				out_onsite = (Integer.parseInt(activity.getDdc_sup_wsws()) * calc_unit * module_trans.getModuleweightage() * module_trans.getComplexity_factor() * driver*BuFactor)/100;
				out_ddc	   = (Integer.parseInt(activity.getDdc_sup_wsddc())* calc_unit * module_trans.getModuleweightage() * module_trans.getComplexity_factor() * driver*BuFactor)/100;
			}else {
				out_onsite = (Integer.parseInt(activity.getDdc_not_sup_wsws()) * calc_unit * module_trans.getModuleweightage() * module_trans.getComplexity_factor() *  driver*BuFactor)/100;
				out_ddc	   = (Integer.parseInt(activity.getDdc_not_sup_wsddc())* calc_unit * module_trans.getModuleweightage() * module_trans.getComplexity_factor() * driver*BuFactor)/100;
			}
		}
		out_onsite =  Double.valueOf(twoDForm.format(out_onsite));
		out_ddc =  Double.valueOf(twoDForm.format(out_ddc));
		if(activity.getDriver_type().equalsIgnoreCase("50")){
			if(dependantData != null){
				out_onsite = dependantData.get("onsite") * out_onsite;
				out_ddc = dependantData.get("ddc") * out_ddc;
			}
			Map<String,Double> mappedData = new HashMap<String, Double>();
			mappedData.put("onsite", out_onsite);
			mappedData.put("parent_id", activity.getId()*1.0);
			mappedData.put("ddc", out_ddc);
			ActivityManagementData newDepAct =  (ActivityManagementData) moduleTransactionDataDao.findDependantActData(activity.getDependant_activity());
			return this.calculateAct(newDepAct, newDepAct.getUnit(), module_trans, engagement, mappedData);
		}
		else{
			Integer newAct = activity.getId();
			if(dependantData != null){
//				out_onsite = dependantData.get("onsite");
//				out_ddc = dependantData.get("ddc");
				out_onsite = dependantData.get("onsite") * out_onsite;
				out_ddc = dependantData.get("ddc") * out_ddc;
				newAct = dependantData.get("parent_id").intValue();
			}
			out_onsite =  Double.valueOf(twoDForm.format(out_onsite));
			out_ddc =  Double.valueOf(twoDForm.format(out_ddc));
			moduleTransactionDataDao.setActValues(out_ddc, out_onsite, module_trans.getId().toString(), newAct.toString());
		
		}
		return null;
	}*/
	@SuppressWarnings("null")
	@Override
	public Map<String,Object> calculateAct(Map<String,ProjectUnit> AllUnits,ActivityManagementData activity,String unit,ModuleTransactionActivity module_trans,Engagement engagement,Map<String,Double> dependantData){
		  DecimalFormat twoDForm = new DecimalFormat("#.#");
		//Initialize
		Double out_ddc = 0.00;
		Double out_onsite = 0.00;
		Double out_testing= 0.00;
		Double driver = 1.00;
		Double BuFactor = 1.00;
		Double Secondary_Driver = 1.00;
		String explain = "";
		String act_driver_type = activity.getDriver_type();
		Map<String,Object> mapObj = new HashMap<String, Object>();
		
		if("Yes".equalsIgnoreCase(module_trans.getIs_in_scope_flag()) == false){
			mapObj.put("ddc", 0);
			mapObj.put("onsite", 0);
			mapObj.put("module_transaction_id", module_trans.getId().toString());
			mapObj.put("activity_id", activity.getId());
			mapObj.put("explain", "--");
			return mapObj;
		}
		
		double depData = 1.00;
		Double calc_unit = AllUnits.get(activity.getUnit()).getValue();
		if(calc_unit == null || calc_unit < 0){
			calc_unit  = 1.00;
		}
	
		
		Double moduleWeight = module_trans.getModuleweightage();
		
		explain = explain + "UNT("+calc_unit+") X ";
		//Get driver 
		try{
		 driver = Double.parseDouble(activity.getDriver());
		}catch(Exception e){}
		boolean checkConstant = act_driver_type.equals("49");
		boolean checkDep = act_driver_type.equals("50");

		if(checkDep){
			 depData = getDependantData(activity.getId().toString(),engagement.getId().toString(),0.00);
			 explain = explain + "DABD("+depData+") X ";
		}
		
		if(!(checkConstant ||  checkDep)){
			Secondary_Driver = 	moduleTransactionDataDao.getSecondaryDriver(module_trans.getId(),engagement.getId().toString(), activity.getId().toString(), act_driver_type);
			if(Secondary_Driver == null){
				Secondary_Driver = 1.00;
			}
			moduleWeight = 1.00;
			explain = explain + "SD("+Secondary_Driver+") X ";
		}else {
			explain = explain + "MWT("+moduleWeight+") X ";
		}
		
		
		Boolean ddcSupport = (module_trans.getDdc_supported_flag().equalsIgnoreCase("Yes"));
		Boolean Testing = (engagement.getTesting().equalsIgnoreCase("yes"));
		boolean workstream = (activity.getWorkstream().equals("2"));
		if(!workstream){
			Testing = false;
		}
		Pwc_Responsibility_Vo pwcResp = this.pwcResponsibility.findResponsibilityByEngagement(engagement.getId().toString(), activity.getId().toString());
		BuFactor = Double.parseDouble(pwcResp.getBu_dependency());
		
//		System.out.println("module--- "+activity.getActivity()+" -- SD -"+Secondary_Driver+" - Unit - "+calc_unit+" - weightage - "+moduleWeight+" - Driver - "+driver+" - BuF - "+BuFactor);
		
		//Read excel for the below logic
		if(Testing){
			explain = explain + "TAAS("+activity.getTaas_ws()+"/"+activity.getTaas_ddc()+") X ";
			out_onsite = (Secondary_Driver*Integer.parseInt(activity.getTaas_ws()) * calc_unit * moduleWeight * module_trans.getComplexity_factor() * driver*BuFactor)/100;
			out_ddc	   = (Secondary_Driver*Integer.parseInt(activity.getTaas_ddc())* calc_unit * moduleWeight * module_trans.getComplexity_factor() *  driver*BuFactor)/100;
		}else if(!Testing){
			if(ddcSupport){
				explain = explain + "DDC("+activity.getDdc_sup_wsws()+"/"+activity.getDdc_sup_wsddc()+") X ";
				out_onsite = (Secondary_Driver*Integer.parseInt(activity.getDdc_sup_wsws()) * calc_unit * moduleWeight * module_trans.getComplexity_factor() * driver*BuFactor)/100;
				out_ddc	   = (Secondary_Driver*Integer.parseInt(activity.getDdc_sup_wsddc())* calc_unit * moduleWeight * module_trans.getComplexity_factor() * driver*BuFactor)/100;
			}else {
				explain = explain + "DDCT("+activity.getDdc_not_sup_wsws()+"/"+activity.getDdc_not_sup_wsddc()+") X ";
				out_onsite = (Secondary_Driver*Integer.parseInt(activity.getDdc_not_sup_wsws()) * calc_unit * moduleWeight * module_trans.getComplexity_factor() *  driver*BuFactor)/100;
				out_ddc	   = (Secondary_Driver*Integer.parseInt(activity.getDdc_not_sup_wsddc())* calc_unit * moduleWeight * module_trans.getComplexity_factor() * driver*BuFactor)/100;
			}
		}
		explain = explain + "BUF("+BuFactor+") X "+"BD("+driver+") X CF("+module_trans.getComplexity_factor()+")";
//		out_onsite =  Double.valueOf(twoDForm.format(out_onsite));
//		out_ddc =  Double.valueOf(twoDForm.format(out_ddc));
		//calculate Dependent
		out_onsite = depData*out_onsite;
		out_ddc = depData*out_ddc;
		//EoF calculate Dependent
		out_onsite =  Double.valueOf(twoDForm.format(out_onsite));
		out_ddc =  Double.valueOf(twoDForm.format(out_ddc));
		
		mapObj.put("ddc", out_ddc);
		mapObj.put("onsite", out_onsite);
		mapObj.put("module_transaction_id", module_trans.getId().toString());
		mapObj.put("activity_id", activity.getId());
		mapObj.put("explain", explain);
		
//		moduleTransactionDataDao.setActValues(out_ddc, out_onsite, module_trans.getId().toString(), activity.getId().toString(),explain);
		return mapObj;
	}
	
}
