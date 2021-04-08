package com.pwc.denali2.estimator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.dao.DetailedSummaryDao;
import com.pwc.denali2.estimator.dao.DetailedSummaryDaoImpl;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.DetailedSummary;

@Service("DetailedSummaryService")
@Transactional
public class DetailedSummaryServiceImpl implements DetailedSummaryService {
	/**
	 * Comparatively SQL intensive code
	 * please debug the application to get SQL Query
	 * All Below static Variables are used to less clutter the page and
	 * for repeated usage
	 * **/
	
	@Autowired
	private DetailedSummaryDao detailedSummaryDao;
	
	
	public static final String Input_Module_Data = "27";
	public static final String Input_conversion = "28";
	public static final String Input_Rice  = "29";
	public static final String Input_Pgl  = "126";
	
	public static final String conversion_explain = "CONCAT('CONVEFT(',cost,') X PRTSP(%)(',proto*100,') X DDC(',DDC_Sup_WsWs,') X BUF(',bu_factor,') || "
												  + "CONVEFT(',cost,') X PRTSP(%)(',proto*100,') X DDC(',DDC_Sup_WsDDC,') X BUF(',bu_factor,')') as explanation";
	public static final String rice_explain = "CONCAT('Sum of RICE(',cost,') X PRTSP(%)(',proto*100,') X DDC(',DDC_Sup_WsWs,') X BUF(',bu_factor,') || "
			  									  + "Sum of RICE(',cost,') X PRTSP(%)(',proto*100,') X DDC(',DDC_Sup_WsDDC,') X BUF(',bu_factor,')') as explanation";
	
	public static final String module_explain = "CONCAT('Sum of Modules(',SUBSTRING_INDEX(SUBSTRING_INDEX(cost, '_', 1), '_', -1),') || Sum of Modules(',SUBSTRING_INDEX(SUBSTRING_INDEX(cost, '_', 2), '_', -1),')') AS explanation";	
	public static final String others_explain = "CONCAT(unit_value,'(',cost,') X Driver(',proto,') X DDC(',DDC_Sup_WsWs,') X BUF(',bu_factor,') || "
												+"', unit_value,'(',cost,') X Driver(',proto,') X DDC(',DDC_Sup_WsDDC,') X BUF(',bu_factor,')') as explanation";
	
	public static final String pgl_explain = "CONCAT('(DDC(',DDC_Sup_WsWs,')/ PD Value','(',proto,')) X BUF(',bu_factor,') X P4(',cost_onsite,') || "
												+ " (DDC(',DDC_Sup_WsDDC,') / PD Value','(',proto,')) X BUF(',bu_factor,') X P4(',cost_ddc,')') as explanation";

	
	public static final String rates_select =" costTable.id as activity_id,sdbt_ddc.rate as ddc_csp,sdbt_workstream.rate as onsite_csp, sdbt_ddc_rsr.rate as ddc_rsr,sdbt_workstream_rsr.rate as onsite_rsr";
	public static final String join_rates = "LEFT JOIN staff_driver_blended_rate_transaction sdbt_ddc ON sdbt_ddc.engagement_id = {{engagement_id}} AND sdbt_ddc.workstream_id = costTable.workstream AND sdbt_ddc.rate_type = \"csp\" AND sdbt_ddc.work_type = \"ddc\" "+
											"LEFT JOIN staff_driver_blended_rate_transaction sdbt_workstream ON sdbt_workstream.engagement_id = {{engagement_id}} AND sdbt_workstream.workstream_id = costTable.workstream AND sdbt_workstream.rate_type = \"csp\" and sdbt_workstream.work_type = \"workstream\" "+
											"LEFT JOIN staff_driver_blended_rate_transaction sdbt_ddc_rsr ON sdbt_ddc_rsr.engagement_id  = {{engagement_id}} AND sdbt_ddc_rsr.workstream_id = costTable.workstream AND sdbt_ddc_rsr.rate_type = \"rsr\" and sdbt_ddc_rsr.work_type = \"ddc\" "+
											"LEFT JOIN staff_driver_blended_rate_transaction sdbt_workstream_rsr ON sdbt_workstream_rsr.engagement_id = {{engagement_id}} AND  sdbt_workstream_rsr.workstream_id = costTable.workstream and sdbt_workstream_rsr.rate_type = \"rsr\" and sdbt_workstream_rsr.work_type = \"workstream\") as calc_tbl ON DUPLICATE KEY UPDATE "
											+ " effort_onsite=VALUES(effort_onsite),effort_ddc=VALUES(effort_ddc),effort_onsite_override=VALUES(effort_onsite_override),effort_ddc_override=VALUES(effort_ddc_override),csp_onsite=VALUES(csp_onsite), "+
											  " csp_ddc=VALUES(csp_ddc),rsr_onsite=VALUES(rsr_onsite),rsr_ddc=VALUES(rsr_ddc),margin_onsite=VALUES(margin_onsite),margin_ddc=VALUES(margin_ddc),explanation=VALUES(explanation),updated_at=NOW()";
	
	public static final String common_select = "activity_id, "+
											   "onsite_effort as effort_onsite,ddc_effort as effort_ddc,"
											   + "onsite_effort as effort_onsite_override,ddc_effort as effort_ddc_override,"+
											   "(onsite_csp*onsite_effort) as csp_onsite, "+
												"(ddc_csp*ddc_effort) as csp_ddc, "+
												"(onsite_rsr*onsite_effort) as rsr_onsite, "+
												"(ddc_rsr*ddc_effort) as rsr_ddc, "+
												"(onsite_csp*onsite_effort) - (onsite_rsr*onsite_effort)as margin_onsite, "+
												"(ddc_csp*ddc_effort) - (ddc_rsr*ddc_effort) as margin_ddc, "+
												"explanation,NOW(),NOW()";
	public static final String insert_statement = "INSERT INTO detailed_summary "+
												 "(`engagement_id`,`activity_id`,`effort_onsite`,`effort_ddc`,`effort_onsite_override`,`effort_ddc_override`,`csp_onsite`,`csp_ddc`,`rsr_onsite`,`rsr_ddc`,`margin_onsite`,`margin_ddc`,`explanation`,`created_at`,`updated_at`) ";
	

	
	@Override
	public boolean calculateEffort(int engagement_id) {
		/*// TODO Auto-generated method stub
		switch (activity.getActivity_effort_driver()) {
		//Input module Data
		case Input_Module_Data:
				break;
		
		//Input conversion
		case Input_conversion:
			
			break;
		//Input Rice 
		case Input_Rice:
			
			break;	
		//all other input
		default:
			break;
		}*/

		boolean result = this.detailedSummaryDao.saveByQuery(this.calculateConversion(engagement_id));
		 result = this.detailedSummaryDao.saveByQuery(this.calculateModule(engagement_id));
		 result = this.detailedSummaryDao.saveByQuery(this.calculateOthers(engagement_id));
		 result = this.detailedSummaryDao.saveByQuery(this.calculateRice(engagement_id));
		 result = this.detailedSummaryDao.saveByQuery(this.calculatePgl(engagement_id));
		return result;
	}
	
	public String calculateConversion(int engagement_id){
		String sql = insert_statement+"SELECT "+engagement_id+","+common_select+" FROM "
				+ "(SELECT "+rates_select+","+conversion_explain+",(DDC_Sup_WsWs/100)*cost*bu_factor*(proto) as onsite_effort,"+
				"(proto)*bu_factor*cost*(DDC_Sup_WsDDC/100) as ddc_effort FROM  "+

				"(SELECT amd.*,mal.code,pr.bu_dependency as bu_factor,"+
				//Get Sum of the value required by formula
				"coalesce((SELECT sum(wce.effort) FROM conversion_transaction_join_table wce "+
				"JOIN conversion_transaction_data ctd on ctd.id = wce.conversion_data_id "+
				"JOIN master_admin_lovs malinner on malinner.id = wce.worktype "+
				"where  ctd.final_scope_flag = \"Yes\" AND malinner.code like CONCAT(\"%\",mal.code,\"%\")  and ctd.engagement_id = "+engagement_id+"),0) as cost, "+

				"(SELECT split_percent FROM prototype_effort_split pes "+
				"WHERE  pes.efforttype=\"conversion\" and  pes.workstream_id = amd.workstream and pes.prototype_id = amd.prototype limit 0,1)/100 as proto "+

				"FROM activity_master_data_driver amd "+
				"LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id "+
				"LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id "+
				"WHERE amd.activity_effort_driver = "+Input_conversion+" AND pr.is_pwc_responsibility_flag = \"yes\" AND pr.engagement_id = "+engagement_id+") as costTable "+join_rates.replaceAll("\\{\\{engagement_id\\}\\}", engagement_id+"");
		return sql;
	}
	
	public String calculateRice(int engagement_id){
		String sql =  insert_statement+"SELECT "+engagement_id+","+common_select+" FROM (SELECT "+rates_select+","+rice_explain+",(DDC_Sup_WsWs/100*cost*bu_factor*proto) as onsite_effort, "+
					"(proto*bu_factor*cost*DDC_Sup_WsDDC/100) as ddc_effort FROM "+
					
					"(SELECT amd.*,mal.code,pr.bu_dependency as bu_factor, "+
					//##Get Sum of the value required by formula 
					"coalesce((SELECT sum(wre.effort) FROM rice_transaction_join_table  wre "+
					"JOIN rice_transaction_data rtd on rtd.id = wre.rice_data_id "+
					"JOIN master_admin_lovs malinner on malinner.id = wre.worktype "+
					"where malinner.code like CONCAT(\"%\",mal.code,\"%\")  and rtd.engagement_id = "+engagement_id+"),0) as cost, "+
					
					"(SELECT split_percent FROM prototype_effort_split pes "+
					"JOIN master_admin_lovs malinner2 on pes.prototype = malinner2.code "+
					"WHERE  pes.efforttype=\"rice\" and pes.workstream = mal.code and malinner2.id = amd.prototype limit 0,1)/100 as proto "+
					 
					"FROM activity_master_data_driver amd "+
					"LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id "+
					"LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id "+
					"WHERE amd.activity_effort_driver = "+Input_Rice+" AND pr.is_pwc_responsibility_flag = \"yes\" AND pr.engagement_id = "+engagement_id+") as costTable "+join_rates.replaceAll("\\{\\{engagement_id\\}\\}", engagement_id+"");
		return sql;
	}
	
	public String calculateModule(int engagement_id){
		String sql = insert_statement+"SELECT "+engagement_id+","+common_select+" FROM (SELECT "+rates_select+","+module_explain+",SUBSTRING_INDEX(SUBSTRING_INDEX(cost, '_', 1), '_', -1) as onsite_effort,SUBSTRING_INDEX(SUBSTRING_INDEX(cost, '_', 2), '_', -1) as ddc_effort FROM "+
					"(SELECT amd.*,mal.code,pr.bu_dependency as bu_factor, "+
					//Get Sum of the value required by formula "+
					"(SELECT distinct concat(coalesce(sum(mta.onsite),0),\"_\",coalesce(sum(mta.ddc),0)) "+
					"FROM module_transaction_activity mta "+
					"JOIN module_transaction_data mtd ON mtd.id = mta.module_transaction_id "+
					"WHERE engagement_id = "+engagement_id+" and activity_id = amd.id) as cost "+
					"FROM activity_master_data_driver amd "+
					"LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id "+
					"LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id "+
					"WHERE amd.activity_effort_driver = "+Input_Module_Data+" AND pr.is_pwc_responsibility_flag = \"yes\" AND pr.engagement_id = "+engagement_id+") as costTable "+join_rates.replaceAll("\\{\\{engagement_id\\}\\}", engagement_id+"");
		return sql;
	}
	
	public String calculateOthers(int engagement_id){
		String sql =  insert_statement+"SELECT "+engagement_id+","+common_select+" FROM (SELECT "+rates_select+","+others_explain+",(DDC_Sup_WsWs/100)*cost*bu_factor*(proto) as onsite_effort, "+
				"(proto)*bu_factor*cost*(DDC_Sup_WsDDC/100) as ddc_effort FROM  "+

				"(SELECT amd.*,mu.unit as unit_value,mal.code,pr.bu_dependency as bu_factor, "+
				//##Get Sum of the value required by formula
				"coalesce((SELECT ROUND(exp(sum(log(coalesce(pu.value,0)))),2) as value FROM project_units pu "+
						   " JOIN master_units mu ON pu.master_rates_id  = mu.id "+
						   " where mu.unit IN (select mu.unit from master_units mu where mu.id = amd.unit) and pu.engagement_id = "+engagement_id+" "+
						   " GROUP BY mu.unit), 0) as cost,  "+
                "amd.driver as proto  "+
				"FROM activity_master_data_driver amd  "+
				"LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id  "+
				"LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id  "+
				"LEFT JOIN master_units mu on mu.id = amd.unit  "+
				"WHERE amd.activity_effort_driver NOT IN ("+Input_Module_Data+","+Input_Rice+","+Input_conversion+") AND pr.is_pwc_responsibility_flag = \"yes\" AND pr.engagement_id = "+engagement_id+") as costTable "+join_rates.replaceAll("\\{\\{engagement_id\\}\\}", engagement_id+"");
		return sql;
	}
	
	public String calculatePgl(int engagement_id){
		String sql =  insert_statement+"SELECT "+engagement_id+","+common_select+" FROM (SELECT "+rates_select+","+pgl_explain+",coalesce(((cost_onsite*bu_factor)*(DDC_Sup_WsWs/proto))/100,0) as onsite_effort, "+
				"coalesce(((cost_ddc*bu_factor)*(DDC_Sup_WsDDC/proto))/100,0) as ddc_effort FROM  "+

				"(SELECT amd.*,mu.unit as unit_value,mal.code,pr.bu_dependency as bu_factor, "+
				//##Get Sum of the value required by formula
				"coalesce((SELECT effort_ddc_override from detailed_summary du "+
				"WHERE du.activity_id = amd.dependant_activity AND engagement_id = "+engagement_id+" LIMIT 0,1), 0) as cost_ddc,  "+
				"coalesce((SELECT effort_onsite_override from detailed_summary du "+
				"WHERE du.activity_id = amd.dependant_activity AND engagement_id = "+engagement_id+" LIMIT 0,1), 0) as cost_onsite,  "+
                "(SELECT VALUE FROM project_duration_transaction_join_table where prototyp_id = 61 "
                + "and engagement_id = "+engagement_id+") as proto  "+
				"FROM activity_master_data_driver amd  "+
				"LEFT JOIN master_admin_lovs mal on amd.workstream = mal.id  "+
				"LEFT JOIN pwc_responsibility pr on pr.Activity_id = amd.id  "+
				"LEFT JOIN master_units mu on mu.id = amd.unit  "+
				"WHERE amd.activity_effort_driver = "+Input_Pgl+" AND pr.is_pwc_responsibility_flag = \"yes\" AND pr.engagement_id = "+engagement_id+") as costTable "+join_rates.replaceAll("\\{\\{engagement_id\\}\\}", engagement_id+"");
		return sql;
	}

	@Override
	public Map<String, List> listAllEfforts(int engagement_id) {
		// TODO Auto-generated method stub
		return detailedSummaryDao.list(engagement_id);
	}
	
	@Override
	public boolean overrideEfforts(int engagement_id,Map<String,Map<String,String>> data) {
		// TODO Auto-generated method stub
		return detailedSummaryDao.overrideEfforts(engagement_id,data);
	}

	@Override
	public Map<String,List> detailedEffortSummary(int engagement_id) {

		return this.detailedSummaryDao.listDetailedEffort(engagement_id);
	}

}
