package com.pwc.denali2.estimator.dao;

import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.LockUser;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.User;

@Repository("accountOpsDao")
public class AccountOpsDaoImpl extends AbstractDao<Integer, User> implements
		AccountOpsDao {

	static final Logger logger = LoggerFactory
			.getLogger(AccountOpsDaoImpl.class);

	// Thirty minutes
	private static final long LOCK_TIMEOUT = 30 * 60 * 1000;

	public User findUserBySsoId(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();

		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}

		return user;
	}

	@Override
	public boolean removeLock(String userId) {
		String sqlDelete = "DELETE FROM user_lock WHERE user = '" + userId+"'";

		jdbcTemplate.execute(sqlDelete);
		return true;
	}
	
	@Override
	public LockUser getLockUser(String engagementId){
		String sql = "SELECT * FROM user_lock WHERE engagement_id = ?";
		LockUser user = null;
		try {
			user = jdbcTemplate.queryForObject(sql,
					new Object[]{engagementId},new BeanPropertyRowMapper<LockUser>(LockUser.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	

	@Override
	public boolean checkLock(String userId, String engagementId) {
		String sql = "SELECT * FROM user_lock WHERE engagement_id = ?";
		LockUser user = null;
		try {
			user = jdbcTemplate.queryForObject(sql,
					new Object[]{engagementId},new BeanPropertyRowMapper<LockUser>(LockUser.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		long lockTimeout = this.LOCK_TIMEOUT;
		
		//If same user don't do anything
		
		
		
		if (user != null) {
			if(userId.equals(user.getUser())){
				return true;
			}
			long check = (now.getTime() - user.getTimestamp().getTime());
			boolean isBetweenTimout = (now.getTime() - user.getTimestamp()
					.getTime()) < lockTimeout;
			boolean isAfterTimout = (now.getTime() - user.getTimestamp()
					.getTime()) > lockTimeout;
			if (isBetweenTimout) {
				return false;
			} else if (isAfterTimout) {
				//delete old user
				String sqlDelete = "DELETE FROM user_lock WHERE user = '"
						+ user.getUser() + "' AND engagement_id = "
						+ user.getProject_id();

				jdbcTemplate.execute(sqlDelete);
				
				//insert new user
				String sqlInsert = "INSERT INTO user_lock (`engagement_id`,`user`,`timestamp`) "
						+ " VALUES('"
						+ engagementId
						+ "','"
						+ userId
						+ "','"
						+ now.toString() + "') ";
				jdbcTemplate.execute(sqlDelete);
				return true;
			}
		} else {
			String sqlInsert = "INSERT INTO user_lock (engagement_id,user,timestamp) "
					+ " VALUES('"
					+ engagementId
					+ "','"
					+ userId
					+ "','"
					+ now.toString() + "') ";
			jdbcTemplate.execute(sqlInsert);
			return true;
		}
		return false;
	}
}