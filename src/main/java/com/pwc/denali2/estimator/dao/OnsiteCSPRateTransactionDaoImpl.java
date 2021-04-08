package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.OnSiteCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OnsiteCspRateTransactionDataVO;

@Repository("onsiteCSPRateTransactionDao")
public class OnsiteCSPRateTransactionDaoImpl extends AbstractDao<Integer, OnSiteCspRateTransactiondata> implements OnsiteCSPRateTransactionDao{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void copyOnsiteCSPRateMetaData(String engagementId) {
		String sql = "INSERT INTO onsite_csp_rate_transaction_data"
				+ "(engagement_id,work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost,associate_cost,update_date,VERSION) "
				+" SELECT " + engagementId 
				+ ", work_stream_id,partner_cost,director_cost,manager_cost,senior_associate_cost,associate_cost,CURDATE(),1 FROM onsite_csp_rate_metadata";
		
		jdbcTemplate.execute(sql);

	}

	@Override
	public List<OnsiteCspRateTransactionDataVO> findEngagementTransactionData(String engagementId) {
		String sql = "SELECT w.description AS workstreamName, o.* FROM work_stream w,onsite_csp_rate_transaction_data o "
				+ "WHERE o.work_stream_id = w.id AND o.engagement_id = ? ORDER BY o.work_stream_id";
		
		List<OnsiteCspRateTransactionDataVO> vo = jdbcTemplate.query(sql, new Object[]{engagementId},
				new BeanPropertyRowMapper<OnsiteCspRateTransactionDataVO>(OnsiteCspRateTransactionDataVO.class));
		
		return vo;
	}

	@Override
	public void saveOnsiteCspRateTransactionData(OnSiteCspRateTransactiondata data) {
		
		update(data);
	}
	
}
