package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondataVO;

@Repository("offshoreCSPRateTransactionDao")
public class OffshoreCSPRateTransactionDaoImpl extends AbstractDao<Integer, OffshoreCspRateTransactiondata> implements OffshoreCSPRateTransactionDao{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void copyOffshoreCSPRateMetaData(String engagementId) {
		String sql = "INSERT INTO offshore_csp_rate_transaction_data"
				+ "(engagement_id,work_stream_id,offshore_landed_cost,manager_cost,staff_cost,update_date,VERSION) "
				+" SELECT " + engagementId 
				+ ", work_stream_id,offshore_landed_cost,manager_cost,staff_cost,CURDATE(),1 FROM offshore_csp_rate_metadata";
		
		jdbcTemplate.execute(sql);
	}


	@Override
	public List<OffshoreCspRateTransactiondataVO> findEngagementTransactionData(String engagementId) {
        String sql = "SELECT w.description AS workstreamName, o.* FROM work_stream w,offshore_csp_rate_transaction_data o "
        		+ "WHERE o.work_stream_id = w.id AND o.engagement_id =? ORDER BY o.work_stream_id";
        
        List<OffshoreCspRateTransactiondataVO> vo = jdbcTemplate.query(sql, new Object[]{engagementId},
        		new BeanPropertyRowMapper<OffshoreCspRateTransactiondataVO>(OffshoreCspRateTransactiondataVO.class));
        
		return vo;
	}


	@Override
	public void saveOffshoreCspRateTransactionData(OffshoreCspRateTransactiondata data) {
		
		update(data);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
