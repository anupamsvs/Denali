package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementShare;

@Repository
public class EngagementShareDaoImpl extends AbstractDao<Integer, EngagementShare> implements EngagementShareDao{

	@Override
	public List<String> shareProject(EngagementShare engagementShare) {
		List<String> listworkstream =null;
		
		String sqlSharedRes="SELECT * FROM engagement_share where guid = '"+engagementShare.getGuid()+"' and engagement_id = "+engagementShare.getEngagement_id();
		List<EngagementShare> list = jdbcTemplate.query(sqlSharedRes, new BeanPropertyRowMapper<EngagementShare>(EngagementShare.class));
		
		if(list.size() >0){
			
		}else{
			String sql = "insert into engagement_share values("+engagementShare.getId()+","+engagementShare.getEngagement_id()+",'"+engagementShare.getGuid()+"','"+engagementShare.getRelease()+"','"+engagementShare.getEdit()+"','"+engagementShare.getRead()+"','"+engagementShare.getName()+"')";
			System.out.println(sql);
			jdbcTemplate.update(sql);
			listworkstream = getSharedNames(engagementShare);
			
		}
		
		//System.out.println(engagementShare.getEdit()+" "+engagementShare.getGuid()+" "+engagementShare.getRead()+engagementShare.getRelease()+" "+engagementShare.getName());
		
		
	 
		
		return listworkstream ;
		
		
		
	}
	
	@Override
	public List<String> getSharedNames(EngagementShare engagementShare) {
		System.out.println("into shared names");
		List<String> listworkstream =null;
		
		String sqlSharedRes="SELECT distinct name FROM engagement_share where engagement_id = "+engagementShare.getEngagement_id();
		
		listworkstream = jdbcTemplate.queryForList(sqlSharedRes, String.class);
		 System.out.println(listworkstream);
		return listworkstream ;
		
		
		
	}
	
	
	@Override
	public String getPermissionofUser(String currentUserGuid,Engagement engagement,int id) {
		

		String sqlSharedRes="SELECT * FROM engagement_share where guid = '"+currentUserGuid+"' and engagement_id = "+id;
		
		List<EngagementShare> eng = jdbcTemplate.query(sqlSharedRes, new BeanPropertyRowMapper<EngagementShare>(EngagementShare.class));
		
		
		System.out.println("into controller");
//		System.out.println(eng.get(0).getEdit());
//		System.out.println(eng.get(0).getRelease());
//		System.out.println(eng.get(0).getRead());
		String permission ="";
		
	
		
		if(eng.get(0).getEdit().equals("true"))
		{
			permission= "edit";
		}else if(eng.get(0).getRead().equals("true"))
		{
			permission= "read";
		}if(eng.get(0).getRelease().equals("true"))
		{
			permission= "release";
		}
		
		System.out.println(permission);
		return permission ;
		
		
		
	}
	

}
