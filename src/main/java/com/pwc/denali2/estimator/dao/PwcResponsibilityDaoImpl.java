package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.PwcResponsibilityTransactionData;
import com.pwc.denali2.estimator.model.PwcResponsibilityVo;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;

@Repository("pwcResponsibilityDao")
public class PwcResponsibilityDaoImpl extends AbstractDao<Integer, Pwc_responsibilityData> implements PwcResponsibilityDao {
	
	@Override
	public Pwc_Responsibility_Vo findResponsibilityByEngagement(String engagement_id,String activity_id) {
		String sql="SELECT * FROM pwc_responsibility WHERE engagement_id = ? AND activity_id = ?";
		Pwc_Responsibility_Vo Vo =  jdbcTemplate.queryForObject(sql,new Object[]{engagement_id,activity_id},new BeanPropertyRowMapper<Pwc_Responsibility_Vo>(Pwc_Responsibility_Vo.class));
		return Vo;
	}
	
	
	public List<Pwc_Responsibility_Vo> findAllByEngagementIdForOther(String id,Pwc_responsibilityData pwcResponsibility) {
		System.out.println(id+"************8");
		String workstream ;
		String Prototype ;
		String Activity ;
		String pwcres,bu_dependency="",bu_dependency_status;
		String pwcrespliststr="";
		
		List<ActivityManagementData> activityList = null;
		List<Pwc_Responsibility_Vo> pwc_res_list =new ArrayList<Pwc_Responsibility_Vo>();
		System.out.println(id);
		String prjIid =id.split("-")[0];
		String versionid =id.split("-")[1];
		int business_units_number = Integer.parseInt(id.split("-")[2]);
		System.out.println(business_units_number);
		String sqlcheckforrecd ="",sqlforBuDepFactor="";
		
		List<Pwc_responsibilityData> pwc_res_data =null;
		String sql="SELECT amdd.* FROM activity_master_data_driver amdd "+
					"LEFT JOIN master_admin_lovs mal ON amdd.workstream  = mal.id "+
					"where mal.code NOT LIKE '%PMO%' ORDER BY workstream,prototype,activity";
//		System.out.println(sql);
		activityList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ActivityManagementData>(
						ActivityManagementData.class));
		System.out.println(activityList);
		
		
		for(int index =0;index<activityList.size();index ++){
			double bu_factor = 1.00;
			Pwc_Responsibility_Vo voobj = new Pwc_Responsibility_Vo();
			
			 workstream =activityList.get(index).getWorkstream();
			 Prototype = activityList.get(index).getPrototype();
			 Activity =activityList.get(index).getActivity();
			 bu_dependency_status =activityList.get(index).getBu_dependency();
			 voobj.setWorkstream(workstream);
			 voobj.setActivity(Activity);
			 voobj.setPrototype(Prototype);
			 
			 
			 if(bu_dependency_status.equalsIgnoreCase("yes")){
				try{	
				 sqlforBuDepFactor="SELECT bu_factor,tag FROM master_admin_lovs where lower(type)='bu dependency factor' and code = (SELECT code FROM master_admin_lovs mal where lower(type)='workstream' and id = "+workstream+")";
//					System.out.println(sqlforBuDepFactor);
					
				 
					Map<String, Object> map = (Map<String, Object>)jdbcTemplate.queryForMap(sqlforBuDepFactor);
					String tag =(String) map.get("tag");
					String BuDepFactor =(String) map.get("bu_factor");
					System.out.println(tag+BuDepFactor);
//					String BuDepFactor =jdbcTemplate.queryForObject(sqlforBuDepFactor, String.class);
					
					if(!BuDepFactor.equals(null) && !tag.equals(null)){
						
							if(tag.equalsIgnoreCase("percentage")){
								System.out.println("into per");
								double BuDepFactordec =Double.parseDouble(BuDepFactor)/100.0;
								bu_factor =1+((business_units_number - 1)*BuDepFactordec);
								
								bu_factor = Math.round(bu_factor*100.0)/100.0;
								System.out.println(BuDepFactordec+bu_factor);
							}
							
						
						
					}
				 }catch(Exception e){
					 System.out.println("**"+e.getMessage());
				 }
					 }
				System.out.println("**"+bu_factor);
			
				voobj.setBu_dependency(bu_factor+"");
			 
			 
			
			 
			
			 
			sqlcheckforrecd ="Select * from pwc_responsibility where engagement_id="+prjIid+"  and Activity_id = "+activityList.get(index).getId();
			System.out.println(sqlcheckforrecd);
			pwc_res_data = jdbcTemplate.query(sqlcheckforrecd,
					new BeanPropertyRowMapper<Pwc_responsibilityData>(
							Pwc_responsibilityData.class));
			if(pwc_res_data.size() <=0){
			String sqlins ="insert into pwc_responsibility(Activity_id, create_date, is_pwc_responsibility_flag, update_date, engagement_id,bu_dependency) values(?, ?, ?, ?, ?,?) ";
			System.out.println(sqlins);
			jdbcTemplate.update(sqlins, new Object[]{activityList.get(index).getId(),new Date(),activityList.get(index).getPwc_responsibility(),new Date(),prjIid,bu_factor});
			pwcres =activityList.get(index).getPwc_responsibility(); 
			voobj.setIs_pwc_responsibility_flag(pwcres);
		
			
			}else{
				 String updsqlbufactor ="update pwc_responsibility set bu_dependency = '"+bu_factor+"'where Activity_id="+activityList.get(index).getId()+" and  engagement_id ="+prjIid;
					System.out.println(updsqlbufactor);
			        jdbcTemplate.update(updsqlbufactor);
				 voobj.setIs_pwc_responsibility_flag(pwc_res_data.get(0).getIs_pwc_responsibility_flag());
			}
			System.out.println(voobj);
			pwc_res_list.add(voobj);
			pwc_res_data = null;    
			voobj=null;
		}
		
        System.out.println(pwc_res_list);
		return pwc_res_list;
	}



	
	@Override
	public List<Pwc_Responsibility_Vo> findAllByEngagementIdForPMO(String id) {
		System.out.println(id+"************8");
		String workstream ;
		String Prototype ;
		String Activity ;
		String pwcres,bu_dependency="",bu_dependency_status;
		String pwcrespliststr="";
		
		List<ActivityManagementData> activityList = null;
		List<Pwc_Responsibility_Vo> pwc_res_list =new ArrayList<Pwc_Responsibility_Vo>();
		System.out.println(id);
		String prjIid =id.split("-")[0];
		String versionid =id.split("-")[1];
		int business_units_number = Integer.parseInt(id.split("-")[2]);
		System.out.println(business_units_number);
		String sqlcheckforrecd ="",sqlforBuDepFactor="";
		
		List<Pwc_responsibilityData> pwc_res_data =null;
		String sql="SELECT amdd.* FROM activity_master_data_driver amdd "+
				"LEFT JOIN master_admin_lovs mal ON amdd.workstream  = mal.id "+
				"where mal.code LIKE '%PMO%' ORDER BY workstream,prototype,activity";
		System.out.println(sql);
		activityList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ActivityManagementData>(
						ActivityManagementData.class));
		System.out.println(activityList);
		
		
		for(int index =0;index<activityList.size();index ++){
			double bu_factor = 1.00;
			Pwc_Responsibility_Vo voobj = new Pwc_Responsibility_Vo();
			
			 workstream =activityList.get(index).getWorkstream();
			 Prototype = activityList.get(index).getPrototype();
			 Activity =activityList.get(index).getActivity();
			 bu_dependency_status =activityList.get(index).getBu_dependency();
			 voobj.setWorkstream(workstream);
			 voobj.setActivity(Activity);
			 voobj.setPrototype(Prototype);
			 
			 
			 if(bu_dependency_status.equalsIgnoreCase("yes")){
				 sqlforBuDepFactor="SELECT bu_factor,tag FROM master_admin_lovs "
				 		+ "where lower(type)='bu dependency factor' and code = "
				 		+ "(SELECT code FROM master_admin_lovs mal where lower(type)='workstream' and id = "+workstream+")";
//	 System.out.println(sqlforBuDepFactor);
//					sqlforBuDepFactor="SELECT bu_factor FROM master_admin_lovs where lower(type)='bu dependency factor' and code ='"+workstream+"'";
//					System.out.println(sqlforBuDepFactor);
//					BuDepFactor =jdbcTemplate.queryForObject(sqlforBuDepFactor, String.class);
//					System.out.println(BuDepFactor);
//					
//					
//					if(!StringUtils.isEmpty(BuDepFactor)){
//						double BuDepFactordec =Double.parseDouble(BuDepFactor)/100.0;
//						double cal =(1+(business_units_number - 1));
//						bu_factor =cal * BuDepFactordec;
//						System.out.println(BuDepFactordec+bu_factor);
//					}
				    Map<String, Object> map = (Map<String, Object>)jdbcTemplate.queryForMap(sqlforBuDepFactor);
					String tag =(String) map.get("tag");
					String BuDepFactor =(String) map.get("bu_factor");
//					String BuDepFactor =jdbcTemplate.queryForObject(sqlforBuDepFactor, String.class);
					System.out.println(BuDepFactor);
					if(!BuDepFactor.equals(null) && !tag.equals(null)){
						
						if(tag.equals("percentage")){
							System.out.println("into per");
							double BuDepFactordec =Double.parseDouble(BuDepFactor)/100.0;
							 bu_factor =1+((business_units_number - 1)*BuDepFactordec);
							 bu_factor = Math.round(bu_factor*100.0)/100.0;
							System.out.println(BuDepFactordec+bu_factor);
						}
						
					
					
					
				}
					 }
				System.out.print("**"+bu_factor);
		
				voobj.setBu_dependency(bu_factor+"");
			 
			 
			
			 
			
			 
			sqlcheckforrecd ="Select * from pwc_responsibility where engagement_id="+prjIid+" and  Activity_id = "+activityList.get(index).getId();
			System.out.println(sqlcheckforrecd);
			pwc_res_data = jdbcTemplate.query(sqlcheckforrecd,
					new BeanPropertyRowMapper<Pwc_responsibilityData>(
							Pwc_responsibilityData.class));
			if(pwc_res_data.size() <=0){
			String sqlins ="insert into pwc_responsibility(Activity_id, create_date, is_pwc_responsibility_flag, update_date, engagement_id,bu_dependency) values(?, ?, ?, ?, ?,?) ";
			System.out.println(sqlins);
			jdbcTemplate.update(sqlins, new Object[]{activityList.get(index).getId(),new Date(),activityList.get(index).getPwc_responsibility(),new Date(),prjIid,bu_factor});
			pwcres =activityList.get(index).getPwc_responsibility(); 
			voobj.setIs_pwc_responsibility_flag(pwcres);
		
			
			}else{
				 String updsqlbufactor ="update pwc_responsibility set bu_dependency = '"+bu_factor+"'where Activity_id="+activityList.get(index).getId()+" and  engagement_id ="+prjIid;
					System.out.println(updsqlbufactor);
			        jdbcTemplate.update(updsqlbufactor);
				 voobj.setIs_pwc_responsibility_flag(pwc_res_data.get(0).getIs_pwc_responsibility_flag());
			}
			System.out.println(voobj);
			pwc_res_list.add(voobj);
			pwc_res_data = null;    
			voobj=null;
		}
		
        System.out.println(pwc_res_list);
		return pwc_res_list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<Pwc_Responsibility_Vo> findAllByEngagementIdForPMO(String id) {
//		System.out.println(id+"************8");
//		String workstream ;
//		String Prototype ;
//		String Activity ;
//		String bu_dependency="6",pwcres;
//		List<ActivityManagementData> activityListpmo = null;
//		List<Pwc_Responsibility_Vo> pwc_res_listpmo =new ArrayList<Pwc_Responsibility_Vo>();
//		System.out.println(id);
//		String prjIid =id.split("-")[0];
//		String versionid =id.split("-")[1];
//		
//		String sqlcheckforrecd ="";
//		
//		List<Pwc_responsibilityData> pwc_res_data =null;
//		String sql="SELECT * FROM activity_master_data_driver where workstream  LIKE '%PMO%' ORDER BY prototype,workstream,activity";
//		System.out.println(sql);
//		activityListpmo = jdbcTemplate.query(sql,
//				new BeanPropertyRowMapper<ActivityManagementData>(
//						ActivityManagementData.class));
//		System.out.println(activityListpmo);
//		
//		
//		for(int index =0;index<activityListpmo.size();index ++){
//			Pwc_Responsibility_Vo voobjpmo = new Pwc_Responsibility_Vo();
//		
//			 workstream =activityListpmo.get(index).getWorkstream();
//			 Prototype = activityListpmo.get(index).getPrototype();
//			 Activity =activityListpmo.get(index).getActivity();
//			 
//
//			 System.out.println("===================");
//			 voobjpmo.setWorkstream(workstream);
//			 voobjpmo.setActivity(Activity);
//			 voobjpmo.setPrototype(Prototype);
//			 voobjpmo.setBu_dependency("5");
//			 
//			 
//			sqlcheckforrecd ="Select * from pwc_responsibility where engagement_id="+prjIid+" and version_id ="+versionid+" and Activity_id = "+activityListpmo.get(index).getId();
//			System.out.println(sqlcheckforrecd);
//			pwc_res_data = jdbcTemplate.query(sqlcheckforrecd,
//					new BeanPropertyRowMapper<Pwc_responsibilityData>(
//							Pwc_responsibilityData.class));
//			if(pwc_res_data.size() <=0){
//			String sqlins ="insert into pwc_responsibility(Activity_id, create_date, is_pwc_responsibility_flag, update_date, user_id,version_id,bu_dependency) values(?, ?, ?, ?, ?,?,?) ";
//			System.out.println(sqlins);
//			jdbcTemplate.update(sqlins, new Object[]{activityListpmo.get(index).getId(),new Date(),activityListpmo.get(index).getPwc_responsibility(),new Date(),prjIid,versionid,bu_dependency});
//			pwcres =activityListpmo.get(index).getPwc_responsibility();
//			voobjpmo.setIs_pwc_responsibility_flag(pwcres);
//			}else{
//				voobjpmo.setIs_pwc_responsibility_flag(pwc_res_data.get(0).getIs_pwc_responsibility_flag());
//				
//			}
//			
//			pwc_res_listpmo.add(voobjpmo);
//
//			pwc_res_data = null;   
//			voobjpmo =null;
//			
//			
//		}
//		System.out.println(pwc_res_listpmo);
//		System.out.println(pwc_res_listpmo.size());
//		return pwc_res_listpmo;
//	}
//
//	

	@Override
	public Map<String,Object> update(JSONArray jsonarray, String idparam,
			String checkstatusY_N) {
		
		System.out.println("into update");
		Map<String,Object> map = new HashMap();
		String id_str ="";
		int id;
		String updsql ="";
		String prjIid =idparam.split("-")[0];
		String versionid =idparam.split("-")[1];
		String msg ="";
		try {
			List list = (List) JSONArray.toCollection(jsonarray, Pwc_Responsibility_Vo.class);
			Iterator it = list.iterator();
			List<Pwc_Responsibility_Vo> listdata = new ArrayList<Pwc_Responsibility_Vo>();
			while (it.hasNext()) {
				Pwc_Responsibility_Vo pt = (Pwc_Responsibility_Vo) it.next();
				listdata.add(pt);}
			
			System.out.println(listdata);
			
			//Pwc_responsibilityData pwc_res_data =new Pwc_responsibilityData();
			for(Pwc_Responsibility_Vo data:listdata){
				 id_str ="SELECT id FROM activity_master_data_driver where prototype = '"+data.getPrototype()+"'and workstream = '"+data.getWorkstream()+"'and activity = '"+data.getActivity()+"'";
				 System.out.println(id_str);
				 id =jdbcTemplate.queryForObject(id_str, Integer.class) ;

		        updsql ="update pwc_responsibility set is_pwc_responsibility_flag = '"+checkstatusY_N+"',update_date =now() where Activity_id="+id+"  and engagement_id ="+prjIid;
				System.out.println(updsql);
		        jdbcTemplate.update(updsql);
				//pwc_res_data = null;
		       
		        //map.put("dataothers",updateEngagementIdForOther(idparam));
		        //map.put("datapmo",updateEngagementIdForPMO(idparam) );
		       map.put("msg", "Updation done successfully");
				
			}
			
		} catch (Exception e) {
		    map.put("msg", "Updation done successfully");
			msg ="Error occured during updating";
		}
		

		
		
		
		return map;
	}



	public List<Pwc_Responsibility_Vo> updateEngagementIdForOther(String id) {
		System.out.println(id+"************8");
		String workstream ;
		String Prototype ;
		String Activity ;
		String pwcres,bu_dependency;
		String pwcrespliststr="";
		List<ActivityManagementData> activityList = null;
		List<Pwc_Responsibility_Vo> pwc_res_list =new ArrayList<Pwc_Responsibility_Vo>();
		System.out.println(id);
		String prjIid =id.split("-")[0];
		String versionid =id.split("-")[1];
		
		String sqlcheckforrecd ="";
		
		List<Pwc_responsibilityData> pwc_res_data =null;
		String sql="SELECT * FROM activity_master_data_driver where workstream NOT LIKE '%PMO%' ORDER BY prototype,workstream,activity";
		System.out.println(sql);
		activityList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ActivityManagementData>(
						ActivityManagementData.class));
		System.out.println(activityList);
		
		
		for(int index =0;index<activityList.size();index ++){
			Pwc_Responsibility_Vo voobj = new Pwc_Responsibility_Vo();
		
			 workstream =activityList.get(index).getWorkstream();
			 Prototype = activityList.get(index).getPrototype();
			 Activity =activityList.get(index).getActivity();
			 pwcres =activityList.get(index).getPwc_responsibility();
			 voobj.setWorkstream(workstream);
			 voobj.setActivity(Activity);
			 voobj.setPrototype(Prototype);
			 
			 voobj.setBu_dependency("5");
			 voobj.setIs_pwc_responsibility_flag(pwcres);
			
			pwc_res_list.add(voobj);
			pwc_res_data = null;    
			voobj=null;
		}
		
        System.out.println(pwc_res_list);
		return pwc_res_list;
	}




//
//	
//	
//	public List<Pwc_Responsibility_Vo> updateEngagementIdForPMO(String id) {
//		System.out.println(id+"************8");
//		String workstream ;
//		String Prototype ;
//		String Activity ;
//		String bu_dependency="6",pwcres;
//		List<ActivityManagementData> activityListpmo = null;
//		List<Pwc_Responsibility_Vo> pwc_res_listpmo =new ArrayList<Pwc_Responsibility_Vo>();
//		System.out.println(id);
//		String prjIid =id.split("-")[0];
//		String versionid =id.split("-")[1];
//		
//		String sqlcheckforrecd ="";
//		
//		List<Pwc_responsibilityData> pwc_res_data =null;
//		String sql="SELECT * FROM activity_master_data_driver where workstream  LIKE '%PMO%' ORDER BY prototype,workstream,activity";
//		System.out.println(sql);
//		activityListpmo = jdbcTemplate.query(sql,
//				new BeanPropertyRowMapper<ActivityManagementData>(
//						ActivityManagementData.class));
//		System.out.println(activityListpmo);
//		
//		
//		for(int index =0;index<activityListpmo.size();index ++){
//			Pwc_Responsibility_Vo voobjpmo = new Pwc_Responsibility_Vo();
//		
//			 workstream =activityListpmo.get(index).getWorkstream();
//			 Prototype = activityListpmo.get(index).getPrototype();
//			 Activity =activityListpmo.get(index).getActivity();
//			 pwcres =activityListpmo.get(index).getPwc_responsibility();
//			 System.out.println(workstream+Prototype+pwcres);
//			 System.out.println("===================");
//			 voobjpmo.setWorkstream(workstream);
//			 voobjpmo.setActivity(Activity);
//			 voobjpmo.setPrototype(Prototype);
//			 voobjpmo.setBu_dependency("5");
//			 voobjpmo.setIs_pwc_responsibility_flag(pwcres);
//			 
//			
//			pwc_res_listpmo.add(voobjpmo);
//
//			pwc_res_data = null;   
//			voobjpmo =null;
//			
//			
//		}
//		System.out.println(pwc_res_listpmo);
//		System.out.println(pwc_res_listpmo.size());
//		return pwc_res_listpmo;
//	}
	
	

	
}