package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.EnagagementProgress;

public interface EnagagementProgressDao {
	void saveProgress(EnagagementProgress data);
	EnagagementProgress getProgress(int engagement_id);
}
