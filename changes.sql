/** 13 JUN 17 **/
CREATE TABLE `denalicloudestimator`.`staff_driver_master` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `work_type` VARCHAR(45) NULL,
  `workstream_id` INT NULL,
  `work_value` FLOAT NULL,
  `created_at` DATETIME,
  `updated_at` DATETIME,
  PRIMARY KEY (`id`));
  
 /** EO 13 JUN 17 **/
   /** EO 16 JUN 17 **/
  CREATE TABLE `denalicloudestimator`.`staff_driver_team_master` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `level_id` INT NULL,
  `workstream_id` INT NULL,
  `team_value` INT NULL,
  PRIMARY KEY (`id`));
 /** EO 13 JUN 17 **/
  
   /**  21 JUN 17 **/
  CREATE TABLE `denalicloudestimator`.`staff_driver_blended_rate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `workstream_id` INT NULL,
  `level_id` INT NULL,
  `rate` DOUBLE NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`id`));
    
   /** EO 21 JUN 17 **/
  
   /** 18JUL Calculate Conversion **/
SELECT *,TRUNCATE((DDC_Sup_WsWs/100)*cost*bu_factor*(proto),2) as onsite_effort,
				TRUNCATE((proto)*bu_factor*cost*(DDC_Sup_WsDDC/100),2) as effort_ddc FROM 

				(SELECT amd.*,mal.code,pr.bu_dependency as bu_factor,
				##Get Sum of the value required by formula
				coalesce((SELECT SUM(wce.effort) FROM conversion_transaction_join_table wce 
				JOIN conversion_transaction_data ctd on ctd.id = wce.conversion_data_id 
				JOIN master_admin_lovs malinner on malinner.id = wce.worktype 
				where ctd.final_scope_flag = "yes" and malinner.code like CONCAT("%",mal.code,"%")  and ctd.engagement_id = 1),0) as cost, 

				(SELECT split_percent FROM prototype_effort_split pes 
				JOIN master_admin_lovs malinner2 on pes.prototype = malinner2.code 
				WHERE  pes.efforttype="conversion" and pes.workstream = mal.code and malinner2.id = amd.prototype)/100 as proto 

				FROM activity_master_data_driver amd 
				LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id 
				LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id 
				WHERE amd.activity_effort_driver = 28 AND pr.is_pwc_responsibility_flag = "yes" AND pr.engagement_id = 1) as costTable
		 /** Calculate Conversion **/
 
/** 18JUL Calculate Rice  **/
SELECT *,TRUNCATE((DDC_Sup_WsWs/100)*cost*bu_factor*(proto),2) as onsite_effort, 
TRUNCATE((proto)*bu_factor*cost*(DDC_Sup_WsDDC/100),2) as effort_ddc FROM 

(SELECT amd.*,mal.code,pr.bu_dependency as bu_factor,
				##Get Sum of the value required by formula
				coalesce((SELECT sum(wre.effort) FROM rice_transaction_join_table wre 
				JOIN rice_transaction_data rtd on rtd.id = wre.rice_data_id
				JOIN master_admin_lovs malinner on malinner.id = wre.worktype
				where malinner.id = amd.workstream and rtd.engagement_id = 1),0) as cost,

				(SELECT split_percent FROM prototype_effort_split pes 
				JOIN master_admin_lovs malinner2 on pes.prototype = malinner2.code 
				WHERE  pes.efforttype="conversion" and pes.workstream = mal.code and malinner2.id = amd.prototype)/100 as proto 

				FROM activity_master_data_driver amd 
				LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id 
				LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id 
				WHERE amd.activity_effort_driver = 29 AND pr.is_pwc_responsibility_flag = "yes" AND pr.engagement_id = 1) as costTable
 /** Calculate Rice **/
				
/** Calculate Module **/
SELECT amd.*,mal.code,pr.bu_dependency as bu_factor, 
					##Get Sum of the value required by formula 
					(SELECT distinct concat(coalesce(sum(mta.onsite),0),"_",coalesce(sum(mta.ddc),0)) FROM module_transaction_activity mta
					JOIN module_transaction_data mtd ON mtd.id = mta.module_transaction_id
					WHERE engagement_id = 13 and activity_id = 3) as cost		 
					FROM activity_master_data_driver amd 
					LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id 
					LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id 
					WHERE amd.activity_effort_driver = 27 AND pr.is_pwc_responsibility_flag = "yes" AND pr.engagement_id = 13
/** Calculate Module **/
/**calculate others **/
				SELECT *,TRUNCATE((DDC_Sup_WsWs/100)*cost*bu_factor*(proto),2) as onsite_effort,
				TRUNCATE((proto)*bu_factor*cost*(DDC_Sup_WsDDC/100),2) as effort_ddc FROM 

				(SELECT amd.*,mal.code,pr.bu_dependency as bu_factor,
				##Get Sum of the value required by formula
				coalesce((SELECT value FROM project_units pu
                JOIN master_units mu ON pu.master_rates_id  = mu.id
                where mu.id = amd.unit and pu.engagement_id = 15), 0) as cost, 

				amd.driver as proto 

				FROM activity_master_data_driver amd 
				LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id 
				LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id 
				WHERE amd.activity_effort_driver NOT IN (28,29,27) AND pr.is_pwc_responsibility_flag = "yes" AND pr.engagement_id = 15) as costTable
/** calculate others **//


--04AUG17

CREATE TABLE `engagement_progress` (
  `id` int(11) NOT NULL,
  `engagement_id` int(11) NOT NULL,
  `level` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `project_duration_transaction_data` 
ADD COLUMN `process_area_multiplier` DOUBLE NULL DEFAULT 1 AFTER `resources_for_plus_weeks`,
ADD COLUMN `module_multiplier` DOUBLE NULL DEFAULT 1 AFTER `process_area_multiplier`;
					
					