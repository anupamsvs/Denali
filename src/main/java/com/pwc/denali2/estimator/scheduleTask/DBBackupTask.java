package com.pwc.denali2.estimator.scheduleTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DBBackupTask {
	static final Logger logger = LoggerFactory.getLogger(DBBackupTask.class);
	
	@Value("${db.backup.mysqldump.command.prefix}")
	String commandBase;
	
	@Value("${db.backup.target.folder}")
	String backupFolder;
	
	@Value("${db.backup.target.file.prefix}")
	String backupFilePrefix;
	
	@Value("${db.backup.target.file.extension}")
	String backupFileExtension;
	
	@Scheduled(cron= "${scheduling.job.cron}")
	public void backDB() {
		String backupDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		Runtime rt = Runtime.getRuntime();
		
		String[] command = new String[] { "/bin/sh", "-c", commandBase + backupFolder + backupFilePrefix + backupDate + backupFileExtension };
		
		try {
			logger.debug("Start to backup DenaliCloudEstimator database at: " + new Date());
			rt.exec(command);
			logger.debug("Finished DenaliCloudEstimator database backup task at: " + new Date());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
